package com.xsungroup.tms.user.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Strings;
import com.xsungroup.tms.core.common.BeanConverter;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.common.Snowflake;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.user.dto.RolePermSaveDTO;
import com.xsungroup.tms.user.entity.PermEntity;
import com.xsungroup.tms.user.service.PermService;
import com.xsungroup.tms.user.vo.PermVo;
import com.xsungroup.tms.utils.TreeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author Alex
 * @since 2019-07-17
 */
@Api(tags = "权限信息")
@RestController
@RequestMapping("/api/perm")
public class PermController extends SuperController {

    @Autowired
    private PermService permService;

    @GetMapping("/tree/{roleId}")
    public ResponseInfo getRolePermList(@PathVariable("roleId") String roleId) {
        return ResponseUtil.success(permService.findByPermList(roleId));
    }

    @GetMapping("/tree")
    public ResponseInfo getRolePermList() {
        List<PermEntity> list = permService.list();
        List<PermVo> listVo = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            PermEntity p = list.get(i);
            PermVo vo = BeanConverter.convert(PermVo.class, p);
            vo.setId(p.getPermissionId());
            listVo.add(vo);
        }
        return ResponseUtil.success(listVo.stream()
                .filter(n -> Objects.equals("0", n.getParentId()))
                .map(e -> TreeUtils.findChildren(e, listVo))
                .collect(Collectors.toList()));
    }


    @PostMapping("/editRolePerm")
    public ResponseInfo savePerm(@RequestBody RolePermSaveDTO list) {
        permService.addRolePerm(list);
        return ResponseUtil.success("新增成功");
    }

    @PostMapping("/create")
    public ResponseInfo create(@RequestBody PermVo dto) {

        QueryWrapper<PermEntity> qw = new QueryWrapper<>();
        qw.eq("permission_code",dto.getPermissionCode());
        qw.eq("type", dto.getType());


        if (Strings.isNullOrEmpty(dto.getId())) {
            dto.setId(String.valueOf(Snowflake.getId()));
        } else {
            qw.ne("permission_id", dto.getId());
        }

        int count = permService.count(qw);
        if (count >= 1) {
            return ResponseUtil.error("路由已存在");
        }

        if (Strings.isNullOrEmpty(dto.getParentId())) {
            dto.setParentId("0");
        }

        PermEntity entity = BeanConverter.convert(PermEntity.class, dto);
        entity.setPermissionId(dto.getId());

        return ResponseUtil.result(permService.saveOrUpdate(entity));
    }

    /**
     * 删除方法
     *
     * @param id
     */
    @ApiOperation(value = "删除")
    @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "String")
    @DeleteMapping("/{id:.+}")
    public ResponseInfo delete(@PathVariable("id") String id) throws BussException {
        PermEntity entity = permService.getById(id);
        Assert.notNull(entity,"无效的ID");
        boolean result  =  permService.removeById(id);
        return  ResponseUtil.result(result);
    }

}
