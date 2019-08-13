package com.xsungroup.tms.order.controller;

import com.xsungroup.tms.order.api.PackApi;
import com.xsungroup.tms.order.dto.PackDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 梁建军
 * 创建日期： 2019/8/7
 * 创建时间： 19:28
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest("spring.profiles.active=dev")
public class PackControllerTest {


    @Autowired
    private PackApi packApi;

    @Test
    @Transactional
    public void create() {
        PackDto packDto = new PackDto();
        packDto.setGoodsPackName("包装规格名称");
        packDto.setGoodsPackWeight(2000);
        packDto.setGoodsPackWeightShowUnit("g");
        packDto.setPackUnitId("00");
        packDto.setPackUnitCode("000");
        packDto.setPackUnitName("0000");
        packApi.create(packDto);
    }

    @Test
    @Transactional
    public void updateData() {

        PackDto packDto = new PackDto();
        packDto.setGoodsPackName("包装规格名称");
        packDto.setGoodsPackWeight(2000);
        packDto.setGoodsPackWeightShowUnit("g");
        packDto.setPackUnitId("00");
        packDto.setPackUnitCode("000");
        packDto.setPackUnitName("0000");
        packApi.updateData("360701df7da7ed5170298ecc0ab0dfcf", packDto);
    }

    @Test
    @Transactional
    public void delete() {
        packApi.delete("360701df7da7ed5170298ecc0ab0dfcf");
    }

    @Test
    @Transactional
    public void page() {
        packApi.page();
    }
}
