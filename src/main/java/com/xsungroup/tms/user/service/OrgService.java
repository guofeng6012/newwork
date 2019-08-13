package com.xsungroup.tms.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.user.dto.OrgByPageSelectDTO;
import com.xsungroup.tms.user.dto.OrgSaveDTO;
import com.xsungroup.tms.user.entity.OrgEntity;

import java.util.List;

/**
 * <p>
 * 组织表 服务类
 * </p>
 *
 * @author Alex
 * @since 2019-07-23
 */
public interface OrgService extends IService<OrgEntity> {

    public List<OrgEntity> getTreeShape(String id);


    public List<OrgEntity> getOrgList(String id);

    public List<OrgEntity> getCreateOrgs(String id);

    public OrgEntity geOrgMsg(String id);

    ResponseInfo saveOrg(OrgSaveDTO orgSaveDTO);

    ResponseInfo checkLicense(String type,String value);

    /**
     * 查询组织详情
     * @param orgId
     * @param orgByPageSelectDTO
     * @return
     */
    ResponseInfo findByPageOrg(OrgByPageSelectDTO orgByPageSelectDTO, Page page);

    /**
     * 查询组织详情
     * @param orgId
     * @return
     */
    ResponseInfo getByDetailOrg(String orgId);

    /**
     * 更新单位信息
     * @param orgSaveDTO
     * @return
     */
    ResponseInfo updateOrg(OrgSaveDTO orgSaveDTO);

    /**
     * 完善个人信息
     * @param orgSaveDTO
     * @return
     */
    ResponseInfo updateOrgUser(OrgSaveDTO orgSaveDTO);

    /**
     * 查询组织架构
     * @param orgId
     * @return
     */
    ResponseInfo findByOrgTree(String id);

    /**
     * 删除组织
     * @param orgId
     * @return
     */
    int delOrg(String orgId);

    /**
     * 企业审核
     * @param phoneNo
     * @param auditStatus
     * @param orgName
     * @param page
     * @return
     */
    Page findByAuditOrgPage(String phoneNo,Integer auditStatus,String orgName,Page page);

}
