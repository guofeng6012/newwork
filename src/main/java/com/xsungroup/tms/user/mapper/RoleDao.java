package com.xsungroup.tms.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsungroup.tms.user.dto.RoleSelectDTO;
import com.xsungroup.tms.user.entity.RoleEntity;
import com.xsungroup.tms.user.vo.RoleViewVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends BaseMapper<RoleEntity> {

    /**
     *
     * @param roleSelectDTO
     * @param page
     * @return
     */
    List<RoleViewVO> findByPageRole(@Param("role") RoleSelectDTO roleSelectDTO, Page page);

}
