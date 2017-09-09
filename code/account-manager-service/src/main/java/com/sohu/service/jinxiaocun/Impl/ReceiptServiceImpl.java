package com.sohu.service.jinxiaocun.Impl;

import com.sohu.dao.TReceiptAssociationMapper;
import com.sohu.dao.TReceiptExtendMapper;
import com.sohu.dao.TReceiptMapper;
import com.sohu.mrd.domain.beans.*;
import com.sohu.mrd.domain.beans.TReceipt;
import com.sohu.service.jinxiaocun.ReceiptService;
import com.sohu.service.jinxiaocun.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by worgen on 2016/6/25.
 */
@Service
public class ReceiptServiceImpl implements ReceiptService{
    @Autowired
    private TReceiptMapper tReceiptMapper;

    @Autowired
    private TReceiptExtendMapper tReceiptExtendMapper;

    @Autowired
    private TReceiptAssociationMapper tReceiptAssociationMapper;

    @Override
    public int insert(TReceipt tReceipt) {
        return tReceiptMapper.insertSelective(tReceipt);
    }

    @Override
    public int delete(int id) {
        TReceipt tReceipt = get(id);
        if( tReceipt == null ) {
            return -1;
        }
        tReceipt.setDelFlag(1);
        return update(id, tReceipt);
    }

    @Override
    public int update(int id, TReceipt tReceipt) {
        tReceipt.setId(id);
        return tReceiptMapper.updateByPrimaryKeySelective(tReceipt);
    }
    @Override
    public TReceipt get(int id) {
        TReceiptExample tReceiptExample = new TReceiptExample();
        tReceiptExample.createCriteria().andDelFlagEqualTo(0).andIdEqualTo(id);
        List<TReceipt> tReceipts = tReceiptMapper.selectByExample(tReceiptExample);
        return tReceipts.size() > 0 ? tReceipts.get(0) : null;    }
    @Override
    public List<TReceipt> query(TReceiptExample tReceiptExample) {
        return tReceiptMapper.selectByExample(tReceiptExample);
    }
    @Override
    public List<TReceipt> query(int storeId) {
        TReceiptExample tReceiptExample = new TReceiptExample();
        tReceiptExample.createCriteria().andDelFlagEqualTo(0).andStoreIdEqualTo(storeId);
        return tReceiptMapper.selectByExample(tReceiptExample);
    }
    @Override
    public List<TReceipt> queryCustomerReceipts(int customerId) {
        TReceiptExample tReceiptExample = new TReceiptExample();
        tReceiptExample.createCriteria().andDelFlagEqualTo(0).andCustomerIdEqualTo(customerId);
        return tReceiptMapper.selectByExample(tReceiptExample);
    }
    @Override
    public List<TReceiptAssociation> queryAssociation(int storeId) {
        TReceiptAssociationExample tReceiptAssociationExample = new TReceiptAssociationExample();
        tReceiptAssociationExample.createCriteria().andDelFlagEqualTo(0).andStoreIdEqualTo(storeId);
        return tReceiptAssociationMapper.selectAssociationByExample(tReceiptAssociationExample);
    }

    @Override
    public int queryStoreSumReceipt(int storeId) {
        TReceiptExample tReceiptExample = new TReceiptExample();
        tReceiptExample.createCriteria().andDelFlagEqualTo(0);

        tReceiptExample.createCriteria().andStoreIdEqualTo(storeId);
        return tReceiptExtendMapper.SumReceiptByExample(tReceiptExample);
    }

    @Override
    public int queryCustomerSumReceipt(int customerId) {
        TReceiptExample tReceiptExample = new TReceiptExample();
        tReceiptExample.createCriteria().andDelFlagEqualTo(0);

        tReceiptExample.createCriteria().andCustomerIdEqualTo(customerId);
        return tReceiptExtendMapper.SumReceiptByExample(tReceiptExample);
    }

    @Override
    public List<Map<String, Integer>> queryStoreSumReceipts(int pageNumber, int pageSize) {
        int limitStart = (pageNumber-1)*pageSize;
        int limitEnd = (pageNumber)*pageSize;
        TReceiptExample tReceiptExample = new TReceiptExample();
        tReceiptExample.createCriteria().andDelFlagEqualTo(0);

        tReceiptExample.setOrderByClause("id limit "+limitStart+","+limitEnd);
        return tReceiptExtendMapper.GroupStoreSumReceiptByExample(tReceiptExample);
    }

    @Override
    public List<Map<String, Integer>> queryCustomerSumReceipts(int storeId, int pageNumber, int pageSize) {
        int limitStart = (pageNumber-1)*pageSize;
        int limitEnd = (pageNumber)*pageSize;
        TReceiptExample tReceiptExample = new TReceiptExample();
        tReceiptExample.createCriteria().andDelFlagEqualTo(0);

        if(storeId != 0){
            tReceiptExample.createCriteria().andStoreIdEqualTo(storeId);
        }
        tReceiptExample.setOrderByClause("id limit " + limitStart + "," + limitEnd);
        return tReceiptExtendMapper.GroupCustomerSumReceiptByExample(tReceiptExample);
    }

    @Override
    public List<TReceipt> query(String serailNumber, int customerId,
                                int handlerId, int listerId, int storeId,
                                Date businessDateStart, Date businessDateEnd,
                                Date createdDateStart, Date createdDateEnd, int pageNumber, int pageSize) {
        TReceiptExample tReceiptExample = new TReceiptExample();
        tReceiptExample.createCriteria().andDelFlagEqualTo(0);

        if( serailNumber != null ){
            tReceiptExample.getOredCriteria().get(0).andSerialNumberEqualTo(serailNumber);
        }
        if( customerId != 0 ){
            tReceiptExample.getOredCriteria().get(0).andCustomerIdEqualTo(customerId);
        }
        if( handlerId != 0 ){
            tReceiptExample.getOredCriteria().get(0).andHandlerEqualTo(handlerId);
        }
        if( listerId != 0 ){
            tReceiptExample.getOredCriteria().get(0).andListerEqualTo(listerId);
        }
        if( storeId != 0 ){
            tReceiptExample.getOredCriteria().get(0).andStoreIdEqualTo(storeId);
        }
        if(businessDateStart != null){
            tReceiptExample.getOredCriteria().get(0).andBusinessDateGreaterThanOrEqualTo(businessDateStart);
        }
        if(businessDateEnd != null){
            tReceiptExample.getOredCriteria().get(0).andBusinessDateLessThanOrEqualTo(businessDateStart);
        }
        if(createdDateStart != null){
            tReceiptExample.getOredCriteria().get(0).andCreatedTimeGreaterThanOrEqualTo(createdDateStart);
        }
        if(createdDateEnd != null){
            tReceiptExample.getOredCriteria().get(0).andCreatedTimeLessThanOrEqualTo(createdDateEnd);
        }
        int limitStart = (pageNumber-1)*pageSize;
        int limitEnd = pageNumber*pageSize;

        tReceiptExample.setOrderByClause("id limit " + limitStart + "," + limitEnd);
        return tReceiptMapper.selectByExample(tReceiptExample);
    }
}
