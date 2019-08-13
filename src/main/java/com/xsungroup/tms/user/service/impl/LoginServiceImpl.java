package com.xsungroup.tms.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.core.common.CachePre;
import com.xsungroup.tms.core.common.Constant;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.config.properties.JwtProperties;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.shiro.JWTUtil;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.user.common.UserEnum;
import com.xsungroup.tms.user.dto.LoginDto;
import com.xsungroup.tms.user.entity.PermEntity;
import com.xsungroup.tms.user.entity.UserEntity;
import com.xsungroup.tms.user.mapper.OrgDao;
import com.xsungroup.tms.user.mapper.OrgExtendDao;
import com.xsungroup.tms.user.mapper.PermDao;
import com.xsungroup.tms.user.mapper.UserDao;
import com.xsungroup.tms.user.service.LoginService;
import com.xsungroup.tms.user.vo.CurrentUser;
import com.xsungroup.tms.utils.HttpServletUtil;
import com.xsungroup.tms.utils.MD5Util;
import com.xsungroup.tms.utils.NumUtil;
import com.xsungroup.tms.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service("loginService")
public class LoginServiceImpl extends ServiceImpl<UserDao, UserEntity> implements LoginService {

    private static final String REDIS_LOGIN_NUMBER = "pass:loginNumber:";
    private static final String REDIS_LOGIN_LIMIT = "pass:loginLimit:";
    public static final String REDIS_CHECK_CODE = "pass:code:";
    public static final String REDIS_CHECK_CODE_LIMIT = "pass:codeLimit:";

    @Value("${comm.switch.sms}")
    private boolean smsCodeSwitch;


    @Autowired
    private OrgExtendDao orgExtendDao;

    @Autowired
    private OrgDao orgDao;

    @Autowired
    private PermDao permDao;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public CurrentUser login(LoginDto dto) throws RuntimeException {

        UserEntity user = baseMapper.selectOne(new QueryWrapper<UserEntity>()
                .eq("user_name", dto.getUserName()));

        if (Objects.isNull(user)) {
            throw new BussException(UserEnum.Code.LOGIN_ACCOUNT_ERROR);
        }

        if (Objects.equals(Constant.Status.DISABLED.getValue(),user.getUserStatus())) {
            throw new BussException(UserEnum.Code.LOGIN_USER_DISABLED);
        }
        if (!dto.getPassword().equals(user.getPassword())) {
            if (loginNumber(user.getUserId())) {
                throw new BussException(UserEnum.Code.LOGIN_USER_PWD_LIMIT);
            } else {
                throw new BussException(UserEnum.Code.LOGIN_ACCOUNT_ERROR);
            }
        }


        CurrentUser currentUser = baseMapper.findLoginUserById(user.getUserId());

        //获取角色权限
//        List<PermEntity> permList = permDao.selectList(null);
        List<PermEntity> permList = permDao.findUserPermList(user.getUserId(),user.getGroupId());
        if(!CollectionUtils.isEmpty(permList)){
            List<PermEntity> menuList = permList.stream()
                    .filter(p -> Objects.equals(UserEnum.PERM_TYPE_MENU,p.getType()) || Objects.equals(UserEnum.PERM_TYPE_MENUOPT,p.getType()))
                    .collect(Collectors.toList());
            List<PermEntity> optList = permList.stream()
                    .filter(p -> Objects.equals(UserEnum.PERM_TYPE_OPT,p.getType()) || Objects.equals(UserEnum.PERM_TYPE_MENUOPT,p.getType()))
                    .collect(Collectors.toList());
            currentUser.setMenus(menuList);
            currentUser.setOpts(optList);
        }

        String hostName = HttpServletUtil.getRequest().getRemoteHost();
        String ip = HttpServletUtil.getIpAddress();
        String token = jwtUtil.generateToken(ip,hostName,user.getUserId(),jwtProperties.getExpiration());
        String md5TOken = MD5Util.MD5Encode(token);//使用MD5加密 压缩token的长度
//        RedisUtil.set(md5TOken, currentUser, jwtProperties.getExpiration());
        RedisUtil.putObject(CachePre.LOING_SHIRO_JWT_ID + user.getUserId(), currentUser, jwtProperties.getExpiration());
        currentUser.setToken(token);
        return currentUser;
    }

    /**
     * 发送短信验证码
     *
     * @param mobile
     * @return
     */
    @Override
    public ResponseInfo smsSend(String mobile) {
        log.info("发送验证码参数：{}",mobile);
        if (StringUtils.isEmpty(mobile)) {
            return ResponseUtil.error("手机号不能为空");
        }
        int smsCode = 888888;
        if(smsCodeSwitch){
            smsCode = NumUtil.random();
            //TODO 需要调用短信发送接口
            /**
             * *************************
             * *************************
             * *************************
             */
        }
        Boolean boo = RedisUtil.set(mobile,smsCode,1200);
        if(boo){
            return ResponseUtil.success("短信验证码发送成功");
        }else{
            return ResponseUtil.success("短信验证码发送失败");
        }
    }

    /**
     * @param : [keys]
     * @return : boolean
     * @Description : 登录次数控制
     * @auther : 李雷
     * @date : 2019/4/8 19:05
     */
    private boolean loginNumber(String userCode) {
        String loginKey = REDIS_LOGIN_LIMIT + userCode;

        String key = REDIS_LOGIN_NUMBER + userCode;
        Object o = RedisUtil.get(key);
        Integer num = o == null ? 0 : (Integer) o;

        RedisUtil.set(key, ++num, -1);
        if (num >= 6) {
            RedisUtil.set(loginKey, 0, 20 * 60);
            RedisUtil.expire(key, 1);
            return true;
        }
        return false;
    }

    /**
     * @param : [digits]
     * @return : java.lang.String
     * @Description : 生成验证码
     * @auther : 李雷
     * @date : 2019/4/16 10:30
     */
    private String getCode(int digits) {
        StringBuffer code = new StringBuffer();
        for (int i = 0; i < digits; i++) {
            code.append(new Random().nextInt(10));
        }
        return code.toString();
    }


}
