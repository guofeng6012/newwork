package com.xsungroup.tms.user.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.shiro.ShiroUtil;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.user.dto.OrgByPageSelectDTO;
import com.xsungroup.tms.user.dto.OrgSaveDTO;
import com.xsungroup.tms.user.service.OrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 组织表 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2019-07-23
 */
@Api(tags = "组织信息")
@RestController
@RequestMapping("/api/org")
public class OrgController extends SuperController {

    @Autowired
    private OrgService orgService;


    @ApiOperation(value="获取树形结构")
    @PostMapping("/getTreeShape")
    public ResponseInfo getTreeShape(@RequestHeader(value = "token") String token) throws BussException {
        //从token中获取登录用户的id
        String id = ShiroUtil.currentUserId();
        return  ResponseUtil.success(orgService.getTreeShape(id));
    }


    @ApiOperation(value="查询条件中的选择组织数据来源")
    @PostMapping("/getOrgList")
    public ResponseInfo getOrgList(@RequestHeader(value = "token") String token){
        //从token中获取登录用户的id
        String id = ShiroUtil.currentUserId();
        return  ResponseUtil.success(orgService.getOrgList(id));
    }


    @ApiOperation(value="新增时的创建组织数据来源")
    @PostMapping("/getCreateOrgs")
    public ResponseInfo getCreateOrgs(@RequestHeader(value = "token") String token){
        //从token中获取登录用户的id
        String id = ShiroUtil.currentUserId();
        return  ResponseUtil.success(orgService.getCreateOrgs(id));
    }


    @ApiOperation(value="列表查询所属单位数据来源")
    @GetMapping("/ownOrgs")
    public ResponseInfo ownOrgs(@RequestHeader(value = "token") String token){
        //从token中获取登录用户的id
        String id = ShiroUtil.currentUserId();
        return  ResponseUtil.success(orgService.getCreateOrgs(id));
    }


    @ApiOperation(value="新增时带出来的单位信息")
    @PostMapping("/getOrgMsg")
    public ResponseInfo getOrgMsg(@RequestHeader(value = "token") String token){
        //从token中获取登录用户的id
        String id = ShiroUtil.currentUserId();
        return  ResponseUtil.success(orgService.geOrgMsg(id));
    }

    /**
     * 校验
     * @param key
     * @param dateValue
     * @return
     */
    @GetMapping("/checkOrg")
    public ResponseInfo checkOrg(@RequestParam String key,@RequestParam String dateValue){
        return orgService.checkLicense(key,dateValue);
    }

    /**
     * 保存组织信息
     * @param orgSaveDTO
     * @return
     */
    @PostMapping("/add")
    public ResponseInfo saveOrg(@RequestBody OrgSaveDTO orgSaveDTO){
        return orgService.saveOrg(orgSaveDTO);
    }

    /**
     * 完善个人用户信息
     * @param orgSaveDTO
     * @return
     */
    @PostMapping("/perfect/user")
    public ResponseInfo updateOrgUser(@RequestBody OrgSaveDTO orgSaveDTO){
        return orgService.updateOrgUser(orgSaveDTO);
    }

    /**
     * 分页查询组织企业信息
     * @param token
     * @param orgByPageSelectDTO
     * @return
     */
    @GetMapping("/list")
    public ResponseInfo findByPageOrg(@RequestHeader(value = "token") String token, OrgByPageSelectDTO orgByPageSelectDTO){
        //从token中获取登录用户的id
        String id = ShiroUtil.currentUserId();
        orgByPageSelectDTO.setOrgId(id);
        return orgService.findByPageOrg(orgByPageSelectDTO,this.getPage(false));
    }

    /**
     * 查询组织企业详情
     * @param orgId
     * @return
     */
    @GetMapping("/getByDetailOrg/{orgId}")
    public ResponseInfo getByDetailOrg(@PathVariable("orgId") String orgId){
        return orgService.getByDetailOrg(orgId);
    }

    /**
     * 编辑组织企业详情
     * @param orgSaveDTO
     * @return
     */
    @PostMapping("/edit")
    public ResponseInfo updateORg(@RequestBody OrgSaveDTO orgSaveDTO){
        return orgService.updateOrg(orgSaveDTO);
    }

    @DeleteMapping("/del/{orgId}")
    public ResponseInfo delOrg(@PathVariable("orgId") String orgId){
        if(orgService.delOrg(orgId) > 0){
            return ResponseUtil.success("删除成功");
        }else {
            return ResponseUtil.error();
        }
    }

    /**
     * 查询组织架构 图形
     * @return
     */
    @GetMapping("/tree")
    public ResponseInfo getOrgTree(@RequestHeader(value = "token") String token){
        String id = ShiroUtil.currentUserId();
        return orgService.findByOrgTree(id);
    }

    /**
     * 查询企业审核列表
     * @param phoneNo
     * @param auditStatus
     * @param orgName
     * @return
     */
    @GetMapping("/audit/list")
    public ResponseInfo findByAuditOrgPage(String phoneNo,Integer auditStatus,String orgName){
        Page page = this.getPage(false);
        return ResponseUtil.success(orgService.findByAuditOrgPage(phoneNo,auditStatus,orgName,page));
    }
}
