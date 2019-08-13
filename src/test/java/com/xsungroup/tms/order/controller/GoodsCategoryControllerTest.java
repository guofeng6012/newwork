package com.xsungroup.tms.order.controller;


import com.xsungroup.tms.order.api.GoodsCategoryApi;
import com.xsungroup.tms.order.common.GoodsCategoryType;
import com.xsungroup.tms.order.dto.GoodsCategoryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 梁建军
 * 创建日期： 2019/8/5
 * 创建时间： 16:39
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest("spring.profiles.active=dev")
public class GoodsCategoryControllerTest {

    @Autowired
    private GoodsCategoryApi goodsCategoryApi;

    @Test
    @Transactional
    public void create() {
//        ResponseInfo<List<GoodsCategoryVo>> list = goodsCategoryApi.list(GoodsCategoryType.COMMON.getType());
//        for (GoodsCategoryVo datum : list.getData()) {
//
//            for (int i = 0; i < 1; i++) {

        GoodsCategoryDto goodsCategoryDto = new GoodsCategoryDto();
        goodsCategoryDto.setGoodsCategoryName("分类名称");
        goodsCategoryDto.setGoodsCategoryType(GoodsCategoryType.COMMON.getType());
//        goodsCategoryDto.setGoodsCategoryParentId(datum.getGoodsCategoryId());
        goodsCategoryApi.create(goodsCategoryDto);
//            }
//        }
    }

    @Test
    @Transactional
    public void updateData() {
        GoodsCategoryDto goodsCategoryDto = new GoodsCategoryDto();
        goodsCategoryDto.setGoodsCategoryName("分类名称00000");
        goodsCategoryDto.setGoodsCategoryType(GoodsCategoryType.COMMON.getType());
        goodsCategoryApi.updateData("0a9ddb3f175c852a5239161b48e3da5d", goodsCategoryDto);
    }

//    @Test
//    @Transactional
//    public void list() {
//        goodsCategoryApi.list(GoodsCategoryType.COMMON.getType());
//    }

    @Test
    @Transactional
    public void delete() {
        goodsCategoryApi.delete("0283448fda88530f7e9011e689bddd85");
    }

//    @Test
//    public void page() {
//        goodsCategoryApi.page(null, null, 1, 1);
//    }

    @Test
    public void tree() {
        goodsCategoryApi.tree();
    }
}
