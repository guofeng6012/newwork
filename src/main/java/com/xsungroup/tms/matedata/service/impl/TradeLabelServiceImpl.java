package com.xsungroup.tms.matedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsungroup.tms.core.exception.BussException;
import com.xsungroup.tms.matedata.entity.TradeLabelBEntity;
import com.xsungroup.tms.matedata.entity.TradeLabelEntity;
import com.xsungroup.tms.matedata.mapper.TradeLabelBDao;
import com.xsungroup.tms.matedata.mapper.TradeLabelDao;
import com.xsungroup.tms.matedata.service.TradeLabelService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 行业标签表 服务实现类
 * </p>
 *
 * @author Alex
 * @since 2019-07-22
 */
@Service
public class TradeLabelServiceImpl extends ServiceImpl<TradeLabelDao, TradeLabelEntity> implements TradeLabelService {

    //把子表的dao层注入进来
    @Autowired
    private TradeLabelBDao tradeLabelBDao;

    @Override
    public Object list(IPage<TradeLabelEntity> page, TradeLabelEntity tradeLabelEntity) {
        QueryWrapper<TradeLabelEntity> wrapper = new QueryWrapper<>();
//        wrapper.setEntity(EventNoticeTypeEntity);
        wrapper.eq("is_able", 1);//精确查询
        if (tradeLabelEntity.getTradeLabelName() != null && tradeLabelEntity.getTradeLabelName().length() > 0) {
            wrapper.like("trade_label_name", tradeLabelEntity.getTradeLabelName());//模糊查询
        }
        if (tradeLabelEntity.getTradeLabelCode() != null && tradeLabelEntity.getTradeLabelCode().length() > 0) {
            wrapper.like("trade_label_code", tradeLabelEntity.getTradeLabelCode());//模糊查询
        }
        wrapper.orderByDesc("trade_label_code");//排序
        IPage<TradeLabelEntity> list = baseMapper.selectPage(page, wrapper);
        return list;


    }


    @Transactional
    @Override
    public String addOrEdit(TradeLabelEntity tradeLabelEntity) throws BussException {
        String id = tradeLabelEntity.getTradeLabelId();
        //情况一：新增的时候
        // 这个时候比较简单了，直接insert即可
        if(StringUtils.isBlank(tradeLabelEntity.getTradeLabelId())){
             //先保存主表信息，后拿主表的ID 放到子表中去，再保存子表
            baseMapper.insert(tradeLabelEntity);
            id = tradeLabelEntity.getTradeLabelId();
            List<TradeLabelBEntity> list = new ArrayList<>();
            for(TradeLabelBEntity tradeLabelBEntity : tradeLabelEntity.getTradeLabelBEntitys()){
                tradeLabelBEntity.setTradeLabelId(id);
                list.add(tradeLabelBEntity);
            }
            tradeLabelBDao.addBatchData(list);
        }else{
            //情况二：修改的时候
            //先保存修改后的主表信息
            baseMapper.updateById(tradeLabelEntity);
            List<TradeLabelBEntity> addList = new ArrayList<>();//需要新增的子表数据
            List<TradeLabelBEntity> editList = new ArrayList<>();//修改的子表信息
            List<String> deleList = new ArrayList<>();//删除的子表信息
            for(TradeLabelBEntity tradeLabelBEntity : tradeLabelEntity.getTradeLabelBEntitys()){
                if(StringUtils.isBlank(tradeLabelBEntity.getTradeLabelBId())){
                    //ID为空，是新增的
                    tradeLabelBEntity.setTradeLabelId(id);
                    addList.add(tradeLabelBEntity);
                }else{
                    if(1 == tradeLabelBEntity.getIsAble()){
                        //这个是要修改的
                        editList.add(tradeLabelBEntity);
                    }else if(0 == tradeLabelBEntity.getIsAble()){
                        //这个是要删除的
                        deleList.add(tradeLabelBEntity.getTradeLabelBId());
                    }
                }
            }
            if(addList != null && addList.size() > 0){
                tradeLabelBDao.addBatchData(addList);//批量新增
            }
            if(editList != null && editList.size() > 0){
                tradeLabelBDao.editBatchData(editList);//批量修改
            }
            if(deleList != null && deleList.size() > 0){
                tradeLabelBDao.deleteBatchIds(deleList);//批量删除
            }
        }
        return id;
    }


    /**
     * 获取详情的接口，头子结构
     * @param tradeLabelEntity
     * @return
     * @throws BussException
     */
    @Override
    public TradeLabelEntity getById(TradeLabelEntity tradeLabelEntity) throws BussException {
        TradeLabelEntity result = baseMapper.selectById(tradeLabelEntity.getTradeLabelId());
        if(result == null){
            throw new BussException("找不到数据信息");
        }
        QueryWrapper<TradeLabelBEntity>  wrapper = new QueryWrapper<>();
        wrapper.eq("trade_label_id",tradeLabelEntity.getTradeLabelId());
        List<TradeLabelBEntity> list = tradeLabelBDao.selectList(wrapper);
        result.setTradeLabelBEntitys(list);
        return result;
    }


    @Transactional
    @Override
    public String deleteBatchIds(List<String> list) throws BussException {
        if(list == null || list.size() == 0){
            throw new BussException("请选择单据");
        }
        //找到这些父级下面的所有子数据的ID
       List<Map<String,String>> subList =  tradeLabelBDao.selectSubIds(list);
       List<String> ids = new ArrayList<>();
       for(Map<String,String> tradeLabelBEntity : subList){
           ids.add(tradeLabelBEntity.get("trade_label_b_id"));
       }
//       baseMapper.deleteBatchIds(list);
       removeByIds(list);
       if(ids != null && ids.size() > 0){
           tradeLabelBDao.deleteBatchIds(ids);
       }
        return null;
    }


}
