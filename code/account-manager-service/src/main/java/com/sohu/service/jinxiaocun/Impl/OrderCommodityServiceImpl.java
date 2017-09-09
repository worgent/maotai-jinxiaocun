package com.sohu.service.jinxiaocun.Impl;

import com.sohu.dao.TOrderAssociationMapper;
import com.sohu.dao.TOrderCommodityMapper;
import com.sohu.dao.TOrderMapper;
import com.sohu.mrd.domain.beans.*;
import com.sohu.service.jinxiaocun.OrderCommodityService;
import com.sohu.service.jinxiaocun.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by worgen on 2016/6/25.
 */
@Service
public class OrderCommodityServiceImpl implements OrderCommodityService {
    @Autowired
    private TOrderCommodityMapper tOrderMapper;


    @Override
    public int insert(TOrderCommodity tOrder) {
        return tOrderMapper.insertSelective(tOrder);
    }

//    @Override
//    public int delete(int id) {
//        TOrderCommodity tOrder = get(id);
//        if( tOrder == null ) {
//            return -1;
//        }
//        tOrder.setDelFlag(1);
//        return update(id, tOrder);
//    }

    @Override
    public int update(int id, TOrderCommodity tOrder) {
        tOrder.setId(id);
        return tOrderMapper.updateByPrimaryKeySelective(tOrder);
    }
    @Override
    public TOrderCommodity get(int id) {
        TOrderCommodityExample tOrderExample = new TOrderCommodityExample();
        tOrderExample.createCriteria().andIdEqualTo(id);
        List<TOrderCommodity> tOrders = tOrderMapper.selectByExample(tOrderExample);
        return tOrders.size() > 0 ? tOrders.get(0) : null;    }
    @Override
    public List<TOrderCommodity> query(TOrderCommodityExample tOrderExample) {
        return tOrderMapper.selectByExample(tOrderExample);
    }


}
