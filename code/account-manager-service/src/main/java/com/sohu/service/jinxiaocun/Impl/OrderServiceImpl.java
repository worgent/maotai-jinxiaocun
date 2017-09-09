package com.sohu.service.jinxiaocun.Impl;

import com.sohu.dao.TOrderAssociationMapper;
import com.sohu.dao.TOrderExtendMapper;
import com.sohu.dao.TOrderMapper;
import com.sohu.mrd.domain.beans.*;
import com.sohu.mrd.domain.beans.TOrder;
import com.sohu.service.jinxiaocun.OrderService;
import com.sohu.service.jinxiaocun.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by worgen on 2016/6/25.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private TOrderMapper tOrderMapper;
    @Autowired
    private TOrderExtendMapper tOrderExtendMapper;
    @Autowired
    private TOrderAssociationMapper tOrderAssociationMapper;

    @Override
    public int insert(TOrder tOrder) {
        return tOrderMapper.insertSelective(tOrder);
    }

    @Override
    public int delete(int id) {
        TOrder tOrder = get(id);
        if( tOrder == null ) {
            return -1;
        }
        tOrder.setDelFlag(1);
        return update(id, tOrder);
    }

    @Override
    public int update(int id, TOrder tOrder) {
        tOrder.setId(id);
        return tOrderMapper.updateByPrimaryKeySelective(tOrder);
    }
    @Override
    public TOrder get(int id) {
        TOrderExample tOrderExample = new TOrderExample();
        tOrderExample.createCriteria().andDelFlagEqualTo(0).andIdEqualTo(id);
        List<TOrder> tOrders = tOrderMapper.selectByExample(tOrderExample);
        return tOrders.size() > 0 ? tOrders.get(0) : null;    }
    @Override
    public List<TOrder> query(TOrderExample tOrderExample) {
        return tOrderMapper.selectByExample(tOrderExample);
    }

    @Override
    public List<TOrder> query(int storeId) {
        TOrderExample tOrderExample = new TOrderExample();
        tOrderExample.createCriteria().andDelFlagEqualTo(0).andStoreIdEqualTo(storeId);
        return tOrderMapper.selectByExample(tOrderExample);
    }
    @Override
    public List<TOrder> queryCustomerOrders(int customerId) {
        TOrderExample tOrderExample = new TOrderExample();
        tOrderExample.createCriteria().andDelFlagEqualTo(0).andCustomerIdEqualTo(customerId);
        return tOrderMapper.selectByExample(tOrderExample);
    }

    @Override
    public Map<String, Integer> queryStoreSumOrder(int storeId) {
        TOrderExample tOrderExample = new TOrderExample();
        tOrderExample.createCriteria().andDelFlagEqualTo(0);

        tOrderExample.createCriteria().andStoreIdEqualTo(storeId);
        return tOrderExtendMapper.SumOrderByExample(tOrderExample);
    }

    @Override
    public Map<String, Integer> queryCustomerSumOrder(int customerId) {
        TOrderExample tOrderExample = new TOrderExample();
        tOrderExample.createCriteria().andDelFlagEqualTo(0);

        tOrderExample.createCriteria().andCustomerIdEqualTo(customerId);
        return tOrderExtendMapper.SumOrderByExample(tOrderExample);
    }

    @Override
    public List<Map<String, Integer>> queryStoreSumOrders(int pageNumber, int pageSize) {
        int limitStart = (pageNumber-1)*pageSize;
        int limitEnd = (pageNumber)*pageSize;
        TOrderExample tOrderExample = new TOrderExample();
        tOrderExample.createCriteria().andDelFlagEqualTo(0);

        tOrderExample.setOrderByClause("id limit "+limitStart+","+limitEnd);
        return tOrderExtendMapper.GroupStoreSumOrderByExample(tOrderExample);
    }

    @Override
    public List<Map<String, Integer>> queryCustomerSumOrders(int storeId, int pageNumber, int pageSize) {
        int limitStart = (pageNumber-1)*pageSize;
        int limitEnd = (pageNumber)*pageSize;
        TOrderExample tOrderExample = new TOrderExample();
        tOrderExample.createCriteria().andDelFlagEqualTo(0);

        if(storeId != 0){
            tOrderExample.createCriteria().andStoreIdEqualTo(storeId);
        }
        tOrderExample.setOrderByClause("id limit " + limitStart + "," + limitEnd);
        return tOrderExtendMapper.GroupCustomerSumOrderByExample(tOrderExample);
    }



    @Override
    public List<TOrder> query(String serailNumber, int customerId,
                              int handlerId, int listerId, int storeId,
                              Date businessDateStart, Date businessDateEnd,
                              Date createdDateStart, Date createdDateEnd, int pageNumber, int pageSize) {
        TOrderExample tOrderExample = new TOrderExample();
        tOrderExample.createCriteria().andDelFlagEqualTo(0);

        if( serailNumber != null ){
            tOrderExample.getOredCriteria().get(0).andSerialNumberEqualTo(serailNumber);
        }
        if( customerId != 0 ){
            tOrderExample.getOredCriteria().get(0).andCustomerIdEqualTo(customerId);
        }
        if( handlerId != 0 ){
            tOrderExample.getOredCriteria().get(0).andHandlerEqualTo(handlerId);
        }
        if( listerId != 0 ){
            tOrderExample.getOredCriteria().get(0).andListerEqualTo(listerId);
        }
        if( storeId != 0 ){
            tOrderExample.getOredCriteria().get(0).andStoreIdEqualTo(storeId);
        }
        if(businessDateStart != null){
            tOrderExample.getOredCriteria().get(0).andBusinessDateGreaterThanOrEqualTo(businessDateStart);
        }
        if(businessDateEnd != null){
            tOrderExample.getOredCriteria().get(0).andBusinessDateLessThanOrEqualTo(businessDateStart);
        }
        if(createdDateStart != null){
            tOrderExample.getOredCriteria().get(0).andCreatedTimeGreaterThanOrEqualTo(createdDateStart);
        }
        if(createdDateEnd != null){
            tOrderExample.getOredCriteria().get(0).andCreatedTimeLessThanOrEqualTo(createdDateEnd);
        }
        int limitStart = (pageNumber-1)*pageSize;
        int limitEnd = pageNumber*pageSize;

        tOrderExample.setOrderByClause("id limit "+limitStart+","+limitEnd);
        return tOrderMapper.selectByExample(tOrderExample);
    }


    @Override
    public List<TOrderAssociation> queryAssociations(String serailNumber, int customerId,
                              int handlerId, int listerId, int storeId,
                              Date businessDateStart, Date businessDateEnd,
                              Date createdDateStart, Date createdDateEnd, int pageNumber, int pageSize) {
        TOrderAssociationExample tOrderExample = new TOrderAssociationExample();
        tOrderExample.createCriteria().andDelFlagEqualTo(0);

        if( serailNumber != null ){
            tOrderExample.getOredCriteria().get(0).andSerialNumberEqualTo(serailNumber);
        }
        if( customerId != 0 ){
            tOrderExample.getOredCriteria().get(0).andCustomerIdEqualTo(customerId);
        }
        if( handlerId != 0 ){
            tOrderExample.getOredCriteria().get(0).andHandlerEqualTo(handlerId);
        }
        if( listerId != 0 ){
            tOrderExample.getOredCriteria().get(0).andListerEqualTo(listerId);
        }
        if( storeId != 0 ){
            tOrderExample.getOredCriteria().get(0).andStoreIdEqualTo(storeId);
        }
        if(businessDateStart != null){
            tOrderExample.getOredCriteria().get(0).andBusinessDateGreaterThanOrEqualTo(businessDateStart);
        }
        if(businessDateEnd != null){
            tOrderExample.getOredCriteria().get(0).andBusinessDateLessThanOrEqualTo(businessDateStart);
        }
        if(createdDateStart != null){
            tOrderExample.getOredCriteria().get(0).andCreatedTimeGreaterThanOrEqualTo(createdDateStart);
        }
        if(createdDateEnd != null){
            tOrderExample.getOredCriteria().get(0).andCreatedTimeLessThanOrEqualTo(createdDateEnd);
        }
        int limitStart = (pageNumber-1)*pageSize;
        int limitEnd = pageNumber*pageSize;

        tOrderExample.setOrderByClause("t_order.id limit "+limitStart+","+limitEnd);
        return tOrderAssociationMapper.selectAssociationByExample(tOrderExample);
    }
}
