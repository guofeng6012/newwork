package com.xsungroup.tms.matedata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xsungroup.tms.matedata.entity.AuditTypeEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 审核方式表 Mapper 接口
 * </p>
 *
 * @author Alex
 * @since 2019-07-22
 */
public interface AuditTypeDao extends BaseMapper<AuditTypeEntity> {


    @Update("UPDATE c_audit_type set is_auto = #{isAuto}  where audit_type_id = #{auditTypeId} ")
    void isAutoHandle(@Param("isAuto") int isAuto, @Param("auditTypeId") String auditTypeId);

}
