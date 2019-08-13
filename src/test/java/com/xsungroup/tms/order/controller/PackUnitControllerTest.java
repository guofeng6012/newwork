package com.xsungroup.tms.order.controller;

import com.xsungroup.tms.order.api.PackUnitApi;
import com.xsungroup.tms.order.dto.PackUnitDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 梁建军
 * 创建日期： 2019/8/8
 * 创建时间： 14:37
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest("spring.profiles.active=dev")
public class PackUnitControllerTest {


    @Autowired
    private PackUnitApi packUnitApi;

    @Test
    @Transactional
    public void create() {
        PackUnitDto packUnitDto = new PackUnitDto();
        packUnitDto.setPackUnitName("name");
        packUnitDto.setPackUnitCode("code");
        packUnitApi.create(packUnitDto);
    }

    @Test
    @Transactional
    public void updateData() {
        PackUnitDto packUnitDto = new PackUnitDto();
        packUnitDto.setPackUnitName("name1");
        packUnitDto.setPackUnitCode("code1");
        packUnitApi.updateData("b7e64cddaccef9ebc0a4131268924336",packUnitDto);
    }

    @Test
    @Transactional
    public void delete() {
        packUnitApi.delete("b7e64cddaccef9ebc0a4131268924336");
    }

    @Test
    public void page() {
        packUnitApi.page(null,null);
    }
}
