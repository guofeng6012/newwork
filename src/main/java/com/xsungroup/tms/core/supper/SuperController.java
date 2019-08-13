package com.xsungroup.tms.core.supper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xsungroup.tms.core.common.PageCons;
import com.xsungroup.tms.utils.TypeUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 基础控制层封装
 * @Author: kingJing
 * @Date: 2019/7/5 13:38
 **/
@Api
@Slf4j
public class SuperController {

    @Autowired
    protected HttpServletRequest request;



    protected <T> Page<T> getPage(boolean openSort){

        int index = 1;
        // 页数
        Integer cursor = TypeUtils.castToInt(request.getParameter(PageCons.PAGE_PAGE), index);
        // 分页大小
        Integer limit = TypeUtils.castToInt(request.getParameter(PageCons.PAGE_ROWS), PageCons.DEFAULT_LIMIT);
        // 是否查询分页
        Boolean searchCount = TypeUtils.castToBoolean(request.getParameter(PageCons.SEARCH_COUNT), true);
        limit = limit > PageCons.MAX_LIMIT ? PageCons.MAX_LIMIT : limit;
        Page<T> page = new Page<>(cursor, limit, searchCount);
//        if (openSort) {
//            page.setAsc(getParameterSafeValues(PageCons.PAGE_ASCS));
//            page.setDesc(getParameterSafeValues(PageCons.PAGE_DESCS));
//        }
        return page;

    }





}
