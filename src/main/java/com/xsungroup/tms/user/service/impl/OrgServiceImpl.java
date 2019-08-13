package com.xsungroup.tms.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.common.BusCode;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.shiro.ShiroUtil;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.user.common.OrgEnum;
import com.xsungroup.tms.user.dto.OrgByPageSelectDTO;
import com.xsungroup.tms.user.dto.OrgSaveDTO;
import com.xsungroup.tms.user.dto.OrgTreeDTO;
import com.xsungroup.tms.user.entity.OrgEntity;
import com.xsungroup.tms.user.entity.OrgExtendEntity;
import com.xsungroup.tms.user.entity.UserEntity;
import com.xsungroup.tms.user.mapper.OrgDao;
import com.xsungroup.tms.user.mapper.OrgExtendDao;
import com.xsungroup.tms.user.mapper.UserDao;
import com.xsungroup.tms.user.service.OrgService;
import com.xsungroup.tms.user.service.UserOrgService;
import com.xsungroup.tms.user.vo.OrgAuditViewVO;
import com.xsungroup.tms.user.vo.OrgViewVO;
import lombok.extern.slf4j.Slf4j;
import org.aabss.utils.tree.TreeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 组织表 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2019-07-23
 */
@Slf4j
@Service
public class OrgServiceImpl extends ServiceImpl<OrgDao, OrgEntity> implements OrgService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrgExtendDao orgExtendDao;

    @Autowired
    private UserOrgService userOrgService;


    @Override
    public List<OrgEntity> getTreeShape(String id) {
        //先通过id 来获取当前登录用户的信息
        UserEntity user = userDao.selectById(id);
        //通过user中所述集团的ID，找到可以查看的所有组织
        QueryWrapper<OrgEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id", user.getGroupId());
        List<OrgEntity> orgs = baseMapper.selectList(wrapper);
        //找到最上面的组织,并转为TreeVo,最后整个过程返回的就是一棵树，这个是树顶
//        TreeVo tallOrg = findTallOrg(orgs);


//        return recursiveTree(tallOrg, orgs);
        return orgs;
    }

    public TreeVo findTallOrg(List<OrgEntity> orgs) {
        TreeVo result = new TreeVo();
        //最上面的组织的pareng_id字段为"0"
        for (OrgEntity org : orgs) {
            if ("0".equals(org.getParentId())) {
                result.setId(org.getOrgId());
                result.setParentId(org.getParentId());
                result.setName(org.getOrgName());
                break;
            }
        }
        //当找到最上面的组织后，把这个组织从list中去掉，后面循环的时候回少循环一次，没找到就算了
        if (result != null) {
            orgs.remove(result);
        }
        return result;
    }

    public TreeVo recursiveTree(TreeVo tallOrg, List<OrgEntity> orgs) {
        //直接循环
        for (OrgEntity org : orgs) {
            //如果当前父级id等于tallOrg的id  就说明是他的下一级的
            if (tallOrg.getId().equals(org.getParentId())) {
                TreeVo vo = new TreeVo();
                vo.setId(org.getOrgId());
                vo.setParentId(org.getParentId());
                vo.setName(org.getOrgName());
                vo = recursiveTree(vo, orgs);
                tallOrg.getChildren().add(vo);
            }
        }
        return tallOrg;
    }


    @Override
    public List<OrgEntity> getOrgList(String id) {
        List<OrgEntity> orgList = new ArrayList<>();
        if (orgList == null || orgList.size() == 0) {
//            OrgEntity
        }

        return orgList;
    }


    @Override
    public List<OrgEntity> getCreateOrgs(String id) {
        List<OrgEntity> orgs = new ArrayList<>();
        UserEntity user = userDao.selectById(id);
        //通过user中所述集团的ID，找到可以查看的所有组织
        List<String> orgIds = userOrgService.userCreateOrgIds(id);
        if(orgIds == null || orgIds.size() == 0){
            OrgEntity orgEntity = new OrgEntity();
            orgEntity.setOrgId("0001");
            orgEntity.setOrgName("无组织");
//            orgEntity.setCompanyId("0001");
            orgs.add(orgEntity);
            return orgs;
        }
        QueryWrapper<OrgEntity> wrapper = new QueryWrapper<>();
//        wrapper.eq("group_id", user.getGroupId());
        wrapper.eq("audit_status", 2);//审核状态是审核通过的
        wrapper.in("org_id",orgIds);
        orgs = baseMapper.selectList(wrapper);
        if(orgs == null || orgs.size() == 0){
            OrgEntity orgEntity = new OrgEntity();
            orgEntity.setOrgId("0001");
            orgEntity.setOrgName("无组织");
//            orgEntity.setCompanyId("0001");
            orgs.add(orgEntity);
        }
        return orgs;
    }


    @Override
    public OrgEntity geOrgMsg(String id) {
        OrgEntity orgMsg = null;
        UserEntity user = userDao.selectById(id);
        if (user == null) {
            return orgMsg;
        }
        QueryWrapper<OrgEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("group_id", user.getGroupId());
//        wrapper.eq("audit_status", 2);//审核状态是审核通过的
        List<OrgEntity> orgs = baseMapper.selectList(wrapper);
        if (orgs == null || orgs.size() == 0) {
            return orgMsg;
        }
        //找到登录用户的公司信息
        OrgEntity curentOrg = null;
        for (OrgEntity org : orgs) {
            if (user.getOrgId().equals(org.getOrgId())) {
                curentOrg = org;
            }
        }
        if(curentOrg.getIsLegal() != null && 1 == curentOrg.getIsLegal()){
            //说明当前的公司就是法人组织
            return curentOrg;
        }
        List<OrgEntity> orgList = new ArrayList<>();
        orgMsg = getOrgInfo(orgList, curentOrg,orgs);
        return orgMsg;
    }

    protected OrgEntity getOrgInfo(List<OrgEntity> orgs, OrgEntity curentOrg, List<OrgEntity> orgList) {
            if (orgs != null && orgs.size() >0) {
                return orgs.get(0);
            }
            for (OrgEntity orgEntity : orgList) {
                if (curentOrg.getParentId() != null && orgEntity.getOrgId().equals(curentOrg.getParentId())) {
                    if (orgEntity.getIsLegal() != null && 1 == orgEntity.getIsLegal()) {
//                    org.setOrgId(orgEntity.getOrgId());
//                    org.setOrgName(orgEntity.getOrgName());
                        orgs.add(orgEntity);
                        return orgs.get(0);
                    } else {
                        getOrgInfo(orgs, orgEntity, orgList);
                    }
                }
            }
            return orgs.get(0);
    }


    /**
     *  添加企业组织架构
     * @param orgSaveDTO
     * @return
     */
    @Transactional
    @Override
    public ResponseInfo saveOrg(OrgSaveDTO orgSaveDTO) {
        log.info("新增企业组织架构入参：{}",orgSaveDTO);
        checkOrgUser(orgSaveDTO);
        OrgEntity orgEntity = new OrgEntity();
        BeanUtils.copyProperties(orgSaveDTO,orgEntity);
        orgEntity.setOrgSource(OrgEnum.OrgSourceEnum.ORG_SOURCE_1.getType());
        OrgEntity orgEntityParent = baseMapper.selectById(orgSaveDTO.getParentId());
        //设置企业groupId 如果查询企业为根企业 则获取企业id
        orgEntity.setGroupId("0".equals(orgEntityParent.getParentId()) ? orgEntityParent.getOrgId()
                : orgEntityParent.getGroupId());
        //设置组织类型
        orgEntity.setOrgType(OrgEnum.OrgTypeEnum.ORG_TYPE_0.getType());
        //设置企业角色
        orgEntity.setOrgRole( orgSaveDTO.getRoadTransOrgName().isEmpty() ? OrgEnum.OrgRoleEnum.ORG_ROLE_TYPE_1.getType()
                :OrgEnum.OrgRoleEnum.ORG_ROLE_TYPE_2.getType());
        OrgExtendEntity orgExtendEntity = new OrgExtendEntity();
        BeanUtils.copyProperties(orgSaveDTO,orgExtendEntity);
        //新增企业详情 TODO 创建人
        orgExtendEntity.setCreateUser("");
        baseMapper.insert(orgEntity);
        orgExtendEntity.setOrgId(orgEntity.getOrgId());
        orgExtendDao.insert(orgExtendEntity);
        return ResponseUtil.success();
    }

    /**
     * 校验组织架构字段
     * @param orgSaveDTO
     */
    private void checkOrgUser(OrgSaveDTO orgSaveDTO){
        if(OrgEnum.OrgChooseStatusEnum.Org_Choose_Status_on.getKey() == orgSaveDTO.getIsLegal()){
            if(null == orgSaveDTO.getLegalPersonIdCardUrl() || orgSaveDTO.getLegalPersonIdCardUrl().isEmpty()){
                throw new BussException("法人身份证照片不能为空");
            }
            if(null == orgSaveDTO.getLegalPersonIdCardReUrl() || orgSaveDTO.getLegalPersonIdCardReUrl().isEmpty()){
                throw new BussException("法人身份证照片不能为空");
            }
            if(null == orgSaveDTO.getLegalPersonIdNo() || orgSaveDTO.getLegalPersonIdNo().isEmpty()){
                throw new BussException("法人身份证号不能为空");
            }
            if(null == orgSaveDTO.getLegalPersonName() || orgSaveDTO.getLegalPersonName().isEmpty()){
                throw new BussException("法人姓名不能为空");
            }
            if(null == orgSaveDTO.getLicenseUrl() || orgSaveDTO.getLicenseUrl().isEmpty()){
                throw new BussException("营业执照不能为空");
            }
            if(null == orgSaveDTO.getLicenseOrgName() || orgSaveDTO.getLicenseOrgName().isEmpty()) {
                throw new BussException("企业名称不能为空");
            }else{
                //校验企业名称
                ResponseInfo responseInfoOrgName = checkLicense(OrgEnum.OrgCheckEnum.CKECK_ENTERPRISE_ORG_NAME.getKey(),
                        orgSaveDTO.getLicenseOrgName());
                if (responseInfoOrgName.getCode() != BusCode.SUCCESS.getCode()) {
                    throw new BussException(responseInfoOrgName.getMessage());
                }
            }
            if(null == orgSaveDTO.getLicenseOrgCode() || orgSaveDTO.getLicenseOrgCode().isEmpty()){
                throw new BussException("社会统一信用代码不能为空");
            }else{
                //校验社会统一信用代码
                ResponseInfo responseInfo = checkLicense(OrgEnum.OrgCheckEnum.CKECK_ENTERPRISE_ORG_CODE.getKey(),
                        orgSaveDTO.getLicenseOrgCode());
                if(responseInfo.getCode() != BusCode.SUCCESS.getCode()){
                    throw new BussException(responseInfo.getMessage());
                }
            }
            if(null == orgSaveDTO.getLicenseOrgName() || orgSaveDTO.getLicenseOrgName().isEmpty()){
                throw new BussException("企业名称不能为空");
            }
            if(OrgEnum.OrgChooseStatusEnum.Org_Choose_Status_on.getKey() == orgSaveDTO.getIsAccountant()){
                if(null == orgSaveDTO.getOrgTaxNo() || orgSaveDTO.getOrgTaxNo().isEmpty()){
                    throw new BussException("税号不能为空");
                }
                if(null == orgSaveDTO.getOrgContactPhone() || orgSaveDTO.getOrgContactPhone().isEmpty()){
                    throw new BussException("联系方式不能为空");
                }
                if(null == orgSaveDTO.getOrgName() || orgSaveDTO.getOrgName().isEmpty()){
                    throw new BussException("单位名称不能为空");
                }
            }
        }
        /*if(OrgEnum.OrgChooseStatusEnum.Org_Choose_Status_on.getKey() == orgSaveDTO.getIsOperation()){
            if(null == orgSaveDTO.getRoadTransWord() || orgSaveDTO.getRoadTransWord().isEmpty()){
                throw new BussException("道路运输许可证-许可字不能为空");
            }
            if(null == orgSaveDTO.getRoadTransProvince() || orgSaveDTO.getRoadTransProvince().isEmpty()){
                throw new BussException("道路运输许可证-许可省不能为空");
            }
            if(null == orgSaveDTO.getRoadTransNo() || orgSaveDTO.getRoadTransNo().isEmpty()){
                throw new BussException("道路运输许可证-许可号不能为空");
            }
            if(null == orgSaveDTO.getRoadTransOrgName() || orgSaveDTO.getRoadTransOrgName().isEmpty()){
                throw new BussException("道路运输许可证-业户名称不能为空");
            }else{
                //校验道路运输经营许可证 业户名称
                ResponseInfo responseInfoOrgNo = checkLicense(OrgEnum.OrgCheckEnum.CKECK_ENTERPRISE_ORG_NO.getKey(),
                        orgSaveDTO.getRoadTransOrgName());
                if(responseInfoOrgNo.getCode() != BusCode.SUCCESS.getCode()){
                    throw new BussException(responseInfoOrgNo.getMessage());
                }
            }
        }*/
    }

    /**
     * 校验营业执照唯一性
     * @param type
     * @param value
     * @return
     */
    @Override
    public ResponseInfo checkLicense(String type, String value) {
        QueryWrapper<OrgExtendEntity> orgEntityQueryWrapper = new QueryWrapper<>();
        OrgEnum.OrgCheckEnum orgCheckEnum = OrgEnum.OrgCheckEnum.getOrgCheckEnum(type);
        if(null == orgCheckEnum){
            return ResponseUtil.error("未匹配到数据校验类型");
        }
        orgEntityQueryWrapper.eq(orgCheckEnum.getCode(),value);
        List<OrgExtendEntity> list = orgExtendDao.selectList(orgEntityQueryWrapper);
        if(list.isEmpty()){
            return ResponseUtil.success();
        }
        return ResponseUtil.error(orgCheckEnum.getDescription()+"重复");
    }

    /**
     * 企业组织架构
     * @param orgByPageSelectDTO
     * @return
     */
    @Override
    public ResponseInfo findByPageOrg(OrgByPageSelectDTO orgByPageSelectDTO,Page page) {
        UserEntity userEntity = userDao.selectById(orgByPageSelectDTO.getOrgId());
        orgByPageSelectDTO.setGroupId(userEntity.getGroupId());
        List<OrgViewVO> list =baseMapper.findByPageOrg(orgByPageSelectDTO,page);
        page.setRecords(list);
        return ResponseUtil.success(page);
    }

    /**
     * 查询组织详情
     * @param orgId
     * @return
     */
    @Override
    public ResponseInfo getByDetailOrg(String orgId) {
        return ResponseUtil.success(baseMapper.getByDetailOrg(orgId));
    }

    /**
     * 更新企业信息
     * @param orgSaveDTO
     * @return
     */
    @Override
    public ResponseInfo updateOrg(OrgSaveDTO orgSaveDTO) {
        OrgEntity orgEntity = baseMapper.selectById(orgSaveDTO.getOrgId());
        if(null == orgEntity){
            return ResponseUtil.error("未查到企业信息");
        }
        OrgExtendEntity orgExtendEntity = orgExtendDao.selectById(orgSaveDTO.getOrgId());
        if(null == orgExtendEntity){
            return ResponseUtil.error("未查到企业信息");
        }
        BeanUtils.copyProperties(orgSaveDTO,orgEntity);
        BeanUtils.copyProperties(orgSaveDTO,orgExtendEntity);
        baseMapper.updateById(orgEntity);
        orgExtendDao.updateById(orgExtendEntity);
        return ResponseUtil.success();
    }

    /**
     * 完善当前用户个人信息
     * @param orgSaveDTO
     * @return
     */
    @Transactional
    @Override
    public ResponseInfo updateOrgUser(OrgSaveDTO orgSaveDTO) {
        log.info("完善个人信息参数：{}",orgSaveDTO);
        OrgEntity orgEntity = baseMapper.selectById(orgSaveDTO.getOrgId());
        if(null == orgEntity){
            throw new BussException("完善个人信息用户不存在");
        }
        if(!orgEntity.getOrgName().equals(orgSaveDTO.getOrgName())){
            QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_name",orgSaveDTO.getOrgName());
            queryWrapper.eq("is_able","1");
            UserEntity userEntity = userDao.selectOne(queryWrapper);
            if(null != userEntity){
                throw new BussException("用户名重复");
            }
        }
        OrgExtendEntity orgExtendEntity = orgExtendDao.selectById(orgSaveDTO.getOrgId());
        orgExtendEntity.setLegalPersonIdCardUrl(orgSaveDTO.getLegalPersonIdCardUrl());
        orgExtendEntity.setLegalPersonIdCardReUrl(orgSaveDTO.getLegalPersonIdCardReUrl());
        orgExtendEntity.setLegalPersonName(orgSaveDTO.getLegalPersonName());
        orgExtendEntity.setLegalPersonIdNo(orgSaveDTO.getLegalPersonIdNo());
        orgExtendEntity.setOrgEmail(orgSaveDTO.getOrgEmail());
        orgExtendEntity.setOrgBankAccountNo(orgSaveDTO.getOrgBankAccountNo());
        orgExtendDao.updateById(orgExtendEntity);
        String userId = ShiroUtil.currentUserId();
        UserEntity userEntity = userDao.selectById(userId);
        userEntity.setRealName(orgSaveDTO.getLegalPersonName());
        userEntity.setUserName(orgSaveDTO.getOrgName());
        userDao.updateById(userEntity);
        return ResponseUtil.success();
    }


    /**
     * 查询组织关系 tree 图
     * @param id
     * @return
     */
    @Override
    public ResponseInfo findByOrgTree(String id){

        UserEntity userEntity = userDao.selectById(id);
        String orgId = userEntity.getOrgId();
        OrgEntity orgEntity = baseMapper.selectById(orgId);
        QueryWrapper<OrgEntity> orgEntityQueryWrapper = new QueryWrapper<>();
        //判断企业是否为根企业
        if("0".equals(orgEntity.getParentId()) || orgId.equals(orgEntity.getParentId())){
            orgEntityQueryWrapper.eq("org_id",orgId).or().
                    eq("group_id",orgId);
        }else{
            orgEntityQueryWrapper.eq("org_id",orgEntity.getGroupId()).or().
                    eq("group_id",orgEntity.getGroupId());
        }
        orgEntityQueryWrapper.eq("is_able","1");
        List<OrgEntity> orgEntityList = baseMapper.selectList(orgEntityQueryWrapper);
        Optional<OrgEntity> org = orgEntityList.stream().filter(name -> name.getParentId().equals("0")).findFirst();
        List<OrgTreeDTO> jsonArray = getOrgTree(orgEntityList,org.get().getOrgId());
        //Assert.isNull(jsonArray,"不存在子企业");
        OrgTreeDTO orgTreeDTO = new OrgTreeDTO();
        orgTreeDTO.setOrgName(org.get().getOrgName());
        orgTreeDTO.setOrgId(org.get().getOrgId());
        orgTreeDTO.setAuditStatus(org.get().getAuditStatus());
        orgTreeDTO.setOrgRole(org.get().getOrgRole());
        if(!jsonArray.isEmpty()){
            orgTreeDTO.setTreeDTOList(jsonArray);
        }
        return ResponseUtil.success(orgTreeDTO);
    }

    /**
     * 删除组织
     * @param orgId
     * @return
     */
    @Transactional
    @Override
    public int delOrg(String orgId) {
        QueryWrapper<OrgEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",orgId).or().
                eq("group_id",orgId).eq("is_able",1);
        List<OrgEntity> orgEntityList = baseMapper.selectList(queryWrapper);
        if(!orgEntityList.isEmpty()){
            throw new BussException(com.xsungroup.tms.user.common.BusCode.org_check_err_31);
        }
        QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        userEntityQueryWrapper.eq("group_id",orgId).or().eq("top_id",orgId).
                or().eq("org_id",orgId).or().eq("company_id",orgId).eq("is_super",0);
        List<UserEntity> userEntityList = userDao.selectList(userEntityQueryWrapper);
        if(!userEntityList.isEmpty()){
            throw new BussException(com.xsungroup.tms.user.common.BusCode.org_check_err_32);
        }
       // QueryWrapper<OrgEntity> orgEntityQueryWrapper = new QueryWrapper<>();
       // orgEntityQueryWrapper.eq("is_able",1);
       // orgEntityQueryWrapper.eq("org_id",orgId);
        //OrgEntity orgEntity = baseMapper.selectOne(orgEntityQueryWrapper);
        //orgEntity.setIsAble(0);
        baseMapper.deleteById(orgId);
        orgExtendDao.deleteById(orgId);
        QueryWrapper<UserEntity> entityQueryWrapper = new QueryWrapper<>();
        entityQueryWrapper.eq("org_id",orgId);
        entityQueryWrapper.eq("is_super",1);
        UserEntity userEntity = userDao.selectOne(entityQueryWrapper);
        if(null != userEntity){
            userDao.delete(entityQueryWrapper);
        }
        return 1;
    }

    @Override
    public Page findByAuditOrgPage(String phoneNo, Integer auditStatus, String orgName, Page page) {
        log.info("查询企业审核查询：phoneNo:{} auditStatus：{} orgName :{}",phoneNo,auditStatus,orgName);
        List<OrgAuditViewVO> list = baseMapper.findByAuditOrgPage(phoneNo,auditStatus,orgName,page);
        page.setRecords(list);
        return page;
    }



    /**
     * 查询组织架构关系
     * @param list
     * @param orgId
     * @return
     */
    private List<OrgTreeDTO> getOrgTree(List<OrgEntity> list , String orgId){
        List<OrgTreeDTO> orgTreeDTOList = getChildNodes(orgId,list);
        List<OrgTreeDTO> childTree = new ArrayList<>();
        for (OrgTreeDTO orgEntity : orgTreeDTOList) {
            OrgTreeDTO orgTreeDTO = new OrgTreeDTO();
            orgTreeDTO.setOrgId(orgEntity.getOrgId());
            orgTreeDTO.setOrgName(orgEntity.getOrgName());
            orgTreeDTO.setAuditStatus(orgEntity.getAuditStatus());
            orgTreeDTO.setOrgRole(orgEntity.getOrgRole());
            List<OrgTreeDTO> childs =  getOrgTree(list,orgEntity.getOrgId());
            if(!childs.isEmpty()){
                orgTreeDTO.setTreeDTOList(childs);
            }
            childTree.add(orgTreeDTO);
        }
        return childTree;
    }

    /**
     * 获取下级组织
     * @param orgId
     * @param orgEntityList
     * @return
     */
    private List<OrgTreeDTO> getChildNodes(String orgId,List<OrgEntity> orgEntityList){
        List<OrgTreeDTO> treeDTOList = new ArrayList<>();
        for (OrgEntity orgEntity : orgEntityList) {
            if(orgId.equals(orgEntity.getParentId())){
                OrgTreeDTO orgTreeDTO = new OrgTreeDTO();
                orgTreeDTO.setOrgName(orgEntity.getOrgName());
                orgTreeDTO.setOrgId(orgEntity.getOrgId());
                orgTreeDTO.setAuditStatus(orgEntity.getAuditStatus());
                orgTreeDTO.setOrgRole(orgEntity.getOrgRole());
                treeDTOList.add(orgTreeDTO);
            }
        }
        return treeDTOList;
    }

}
