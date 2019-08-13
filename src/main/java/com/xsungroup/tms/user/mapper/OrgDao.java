package com.xsungroup.tms.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsungroup.tms.user.dto.OrgByPageSelectDTO;
import com.xsungroup.tms.user.entity.OrgEntity;
import com.xsungroup.tms.user.vo.OrgAuditViewVO;
import com.xsungroup.tms.user.vo.OrgDetailVO;
import com.xsungroup.tms.user.vo.OrgViewVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 组织表 Mapper 接口
 * </p>
 *
 * @author Alex
 * @since 2019-07-23
 */
@Repository
public interface OrgDao extends BaseMapper<OrgEntity> {

    List<OrgViewVO> findByPageOrg(@Param("org") OrgByPageSelectDTO orgByPageSelectDTO, Page page);

    OrgDetailVO getByDetailOrg(String orgId);

    List<OrgAuditViewVO> findByAuditOrgPage(@Param("phoneNo") String phoneNo,@Param("auditStatus") Integer auditStatus,
                                            @Param("orgName") String orgName,Page page);

    Integer countIdCardUser(String idCard);
}
