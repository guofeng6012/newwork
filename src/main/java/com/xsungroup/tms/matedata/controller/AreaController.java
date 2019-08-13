package com.xsungroup.tms.matedata.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xsungroup.tms.core.common.ResponseUtil;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.core.common.BeanConverter;
import com.xsungroup.tms.core.supper.ResponseInfo;
import com.xsungroup.tms.core.supper.SuperController;
import com.xsungroup.tms.matedata.dto.AreaDto;
import com.xsungroup.tms.matedata.entity.AreaEntity;
import com.xsungroup.tms.matedata.service.AreaService;
import com.xsungroup.tms.matedata.vo.AreaVo;
import com.xsungroup.tms.utils.TreeUtils;
import com.xsungroup.tms.utils.TypeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 何立辉
 * @since 2019-07-25
 */
@RestController
@Api(tags = "行政区域")
@RequestMapping("/api/area")
@Validated
@Slf4j
public class AreaController extends SuperController {
    @Autowired
    private AreaService areaService;

    /**
     * 获取行政区域树
     * @return
     * @throws BussException
     */
    @ApiOperation(value="获取行政区域树")
    @ApiImplicitParam(name = "name", value = "区域名称/代码", paramType = "query")
    @GetMapping("/tree")
    public ResponseInfo tree(@RequestParam(required = false)
                                         String name) throws BussException {
        QueryWrapper<AreaEntity> qw = new QueryWrapper<>();
        qw.eq("is_able",1);
        qw.in("area_level",4,5,6);
        if(StringUtils.isNotBlank(name)) {
            qw.and(wrapper -> wrapper.like("code", name).or().like("name", name));
        }
        List<AreaEntity> list = areaService.list(qw);
        if(!CollectionUtils.isEmpty(list)){
            List<AreaVo> listVo = BeanConverter.convert(AreaVo.class,list);
            return ResponseUtil.success(listVo.stream()
                    .filter(n-> Objects.equals("0",n.getParentId()))
                    .map(e -> TreeUtils.findChildren(e, listVo))
                    .collect(Collectors.toList()));
        }
        return  ResponseUtil.success();
    }

    /**
     * 获取行政区域树- 最后节点
     * @return
     * @throws BussException
     */
    @ApiOperation(value="获取行政区域树- 最后节点")
    @ApiImplicitParam(name = "parentId", value = "父节点ID", paramType = "query")
    @GetMapping("/lastNodes")
    public ResponseInfo lastNodes(@RequestParam String parentId) throws BussException {
        QueryWrapper<AreaEntity> qw = new QueryWrapper<>();
        qw.eq("is_able",1);
        qw.eq("parent_id",parentId);
        List<AreaEntity> list = areaService.list(qw);
        List<AreaVo> listVo = BeanConverter.convert(AreaVo.class,list);
        return  ResponseUtil.success(listVo);
    }

    /**
     * 分页demo
     * @return
     * @throws BussException
     */
    @ApiOperation(value="分页demo")
    @GetMapping("/page")
    public ResponseInfo page(AreaDto dto){
        QueryWrapper<AreaEntity> qw = new QueryWrapper<>();
        qw.eq("is_able",1);
        qw.likeRight("name", TypeUtils.castToString(dto.getName(),""));
        IPage<AreaEntity> iPage = this.getPage(false);
        iPage = areaService.page(iPage,qw);
        return  ResponseUtil.success(iPage);
    }

    @ApiOperation(value="分页demo")
    @GetMapping("/pageMore")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "编码", paramType = "query"),
            @ApiImplicitParam(name = "level", value = "等级", paramType = "query")
    })
    public ResponseInfo pageMore(@RequestParam(value = "name",required = false) String name,
                             @RequestParam(value = "code",required = false) String code,
                             @RequestParam(value = "level",required = false,defaultValue = "4") int level){
        log.info(name +"||"+ code +"||"+ level);
        return  ResponseUtil.success();
    }



    /**
     * 新增方法.
     *
     * @param t
     */
    @ApiOperation(value = "新增")
    @PostMapping("/create")
    public ResponseInfo create(@RequestBody @Validated(AreaDto.Create.class) AreaDto t){
        AreaEntity entity = BeanConverter.convert(AreaEntity.class,t);
        return ResponseUtil.result(areaService.save(entity));
    }


    /**
     * 修改方法.
     *
     * @param t
     */
    @ApiOperation(value = "修改")
    @PutMapping("/{id}")
    public ResponseInfo updateData(@PathVariable("id") String id, @RequestBody  @Validated(AreaDto.Update.class) AreaDto t){
        AreaEntity entity = BeanConverter.convert(AreaEntity.class,t);
        entity.setId(id);
        return ResponseUtil.result(areaService.updateById(entity));
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
        AreaEntity entity = areaService.getById(id);
        Assert.notNull(entity,"无效的ID");
        boolean result  =  areaService.removeById(id);
        return  ResponseUtil.result(result);
    }




}
