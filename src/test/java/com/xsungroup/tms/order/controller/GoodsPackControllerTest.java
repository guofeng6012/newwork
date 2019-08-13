package com.xsungroup.tms.order.controller;

import com.xsungroup.tms.order.api.GoodsPackApi;
import com.xsungroup.tms.order.dto.GoodsPackDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 梁建军
 * 创建日期： 2019/8/9
 * 创建时间： 14:28
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest("spring.profiles.active=dev")
public class GoodsPackControllerTest {

    @Autowired
    private GoodsPackApi goodsPackApi;

    @Test
    @Transactional
    public void create() {
        GoodsPackDto goodsPackDto = new GoodsPackDto();
        goodsPackDto.setGoodsId("ssss");
        goodsPackDto.setGoodsPackName("160201");
        goodsPackDto.setPackUnitId("00");
        goodsPackDto.setPackUnitCode("000");
        goodsPackDto.setPackUnitName("0000");
        goodsPackDto.setGoodsPackWeight(2000);
        goodsPackDto.setGoodsPackWeightShowUnit("g");
        goodsPackDto.setGoodsPackHeight(1);
        goodsPackDto.setGoodsPackWidth(1);
        goodsPackDto.setGoodsPackLength(1);
        goodsPackDto.setGoodsPackLengthShowUnit("mm");
        goodsPackApi.create(goodsPackDto);
    }

    @Test
    @Transactional
    public void updateData() {
        GoodsPackDto goodsPackDto = new GoodsPackDto();
        goodsPackDto.setGoodsId("ssss");
        goodsPackDto.setGoodsPackName("160201");
        goodsPackDto.setPackUnitId("00");
        goodsPackDto.setPackUnitCode("000");
        goodsPackDto.setGoodsPackName("0000");
        goodsPackDto.setGoodsPackWeight(2000);
        goodsPackDto.setGoodsPackWeightShowUnit("g");
        goodsPackApi.updateData("3c28989807a5c9d4c86c30059a36e0cc", goodsPackDto);
    }

    @Test
    @Transactional
    public void delete() {
        goodsPackApi.delete("3c28989807a5c9d4c86c30059a36e0cc");
    }

    @Test
    public void list() {
        goodsPackApi.list("ssss");
    }
}
