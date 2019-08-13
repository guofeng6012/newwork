package com.xsungroup.tms.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.order.dto.GoodsAndPackDto;
import com.xsungroup.tms.order.dto.GoodsPackDto;
import com.xsungroup.tms.order.entity.GoodsEntity;
import com.xsungroup.tms.order.entity.GoodsPackEntity;
import com.xsungroup.tms.order.mapper.GoodsMapper;
import com.xsungroup.tms.order.service.GoodsPackService;
import com.xsungroup.tms.order.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author admin
 * @since 2019-08-08
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, GoodsEntity> implements GoodsService {


    @Autowired
    private GoodsPackService goodsPackService;

    @Override
    @Transactional
    public void createAndPack(GoodsAndPackDto t) {
        GoodsEntity entity = t.convert(GoodsEntity.class);
        // TODO: 2019/8/6 假数据 等有用户信息时候去掉
        entity.setCreateUserId("setCreateUserId");
        entity.setCreateUserName("setCreateUserName");
        entity.setCreateUserOrgId("setCreateUserOrgId");
        entity.setCreateUserOrgName("setCreateUserOrgName");
        entity.setSubordinateOrgId("setSubordinateOrgId");
        entity.setSubordinateOrgName("setSubordinateOrgName");

        save(entity);
        for (GoodsPackDto goodsPackDto : t.getPackList()) {
            GoodsPackEntity goodsPackEntity = goodsPackDto.convert(GoodsPackEntity.class);
            goodsPackEntity.setGoodsId(entity.getGoodsId());
            if (goodsPackEntity.getGoodsPackLength() != null) {
                goodsPackEntity.setGoodsPackVolume(goodsPackEntity.getGoodsPackLength() * goodsPackEntity.getGoodsPackWidth() * goodsPackEntity.getGoodsPackHeight());
                goodsPackEntity.setGoodsPackVolumeShowUnit("cm³");
            }
            goodsPackService.save(goodsPackEntity);
        }

    }
}
