package com.xsungroup.tms.order.controller;

import com.xsungroup.tms.order.api.GoodsApi;
import com.xsungroup.tms.order.dto.GoodsAndPackDto;
import com.xsungroup.tms.order.dto.GoodsDto;
import com.xsungroup.tms.order.dto.GoodsPackDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 梁建军
 * 创建日期： 2019/8/9
 * 创建时间： 10:55
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest("spring.profiles.active=dev")
public class GoodsControllerTest {

    @Autowired
    private GoodsApi goodsApi;

    @Test
    @Transactional
    public void create() {
        GoodsDto goodsDto = new GoodsDto();
        goodsDto.setGoodsName("商品名称");
        goodsDto.setGoodsType(1);
        goodsDto.setGoodsCategoryId("11111111");
        goodsApi.create(goodsDto);
    }

    @Test
    @Transactional
    public void createAndPack() {
        GoodsAndPackDto goodsDto = new GoodsAndPackDto();
        goodsDto.setGoodsName("商品名称");
        goodsDto.setGoodsType(1);
        goodsDto.setGoodsCategoryId("11111111");

        List<GoodsPackDto> packList = new ArrayList<>();

        GoodsPackDto goodsPackDto = new GoodsPackDto();
        goodsPackDto.setGoodsPackName("160201");
        goodsPackDto.setPackUnitId("00");
        goodsPackDto.setPackUnitCode("000");
        goodsPackDto.setPackUnitName("0000");
        goodsPackDto.setGoodsPackWeight(2000);
        goodsPackDto.setGoodsPackWeightShowUnit("g");
        packList.add(goodsPackDto);
        goodsDto.setPackList(packList);
        goodsApi.createAndPack(goodsDto);

    }

    @Test
    @Transactional
    public void updateData() {
        GoodsDto goodsDto = new GoodsDto();
        goodsDto.setGoodsName("商品名称");
        goodsDto.setGoodsType(1);
        goodsDto.setGoodsCategoryId("11111111");
        goodsApi.updateData("de1b0c155ecb91fc8e77973da5af09fb", goodsDto);
    }

    @Test
    @Transactional
    public void delete() {
        goodsApi.delete("de1b0c155ecb91fc8e77973da5af09fb");
    }

    @Test
    public void page() {
        goodsApi.page(null, null);
    }
}
