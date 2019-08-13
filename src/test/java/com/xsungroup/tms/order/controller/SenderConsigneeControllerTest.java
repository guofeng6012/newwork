package com.xsungroup.tms.order.controller;

import com.xsungroup.tms.order.api.SenderConsigneeApi;
import com.xsungroup.tms.order.dto.SenderConsigneeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 梁建军
 * 创建日期： 2019/8/6
 * 创建时间： 14:18
 * @version 1.0
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest("spring.profiles.active=dev")
public class SenderConsigneeControllerTest {

    @Autowired
    private SenderConsigneeApi senderConsigneeApi;

    @Test
    @Transactional
    public void create() {
        SenderConsigneeDto senderConsigneeDto = new SenderConsigneeDto();
        senderConsigneeDto.setAreaProvinceId("1");
        senderConsigneeDto.setAreaProvinceName("ssss");
        senderConsigneeDto.setAreaCityId("s");
        senderConsigneeDto.setAreaCityName("sssss");
        senderConsigneeDto.setAreaCountyId("sssss");
        senderConsigneeDto.setAreaCountyName("sssss");
        senderConsigneeDto.setAreaTownId("s");
        senderConsigneeDto.setAreaTownName("s");
        senderConsigneeDto.setAreaDetail("ssssssssss");
        senderConsigneeDto.setOrgName("单位名称");
        senderConsigneeDto.setName("名称");
        senderConsigneeDto.setType(1);
        senderConsigneeDto.setContactPhone("ssssss");

        senderConsigneeApi.create(senderConsigneeDto);
    }

    @Test
    @Transactional
    public void updateData() {
        SenderConsigneeDto senderConsigneeDto = new SenderConsigneeDto();
        senderConsigneeDto.setAreaProvinceId("1");
        senderConsigneeDto.setAreaProvinceName("ssss");
        senderConsigneeDto.setAreaCityId("s");
        senderConsigneeDto.setAreaCityName("sssss");
        senderConsigneeDto.setAreaCountyId("sssss");
        senderConsigneeDto.setAreaCountyName("sssss");
        senderConsigneeDto.setAreaTownId("s");
        senderConsigneeDto.setAreaTownName("s");
        senderConsigneeDto.setAreaDetail("ssssssssss");
        senderConsigneeDto.setOrgName("单位名称");
        senderConsigneeDto.setName("名称");
        senderConsigneeDto.setType(1);
        senderConsigneeDto.setContactPhone("ssssss");

        senderConsigneeApi.updateData("fa3031059a027aecaf6d713431b379fc", senderConsigneeDto);
    }

    @Test
    @Transactional
    public void delete() {
        senderConsigneeApi.delete("0f40781bdaf940205c43dc31938e45de");
    }

    @Test
    public void page() {
        senderConsigneeApi.page(null, null, null, null, null, null);
    }
}
