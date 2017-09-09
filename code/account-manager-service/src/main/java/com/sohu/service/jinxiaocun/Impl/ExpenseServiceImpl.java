package com.sohu.service.jinxiaocun.Impl;

import com.sohu.dao.TExpenseAssociationMapper;
import com.sohu.dao.TExpenseMapper;
//import com.sohu.mrd.domain.enums.DelFlagEnum;
import com.sohu.mrd.domain.beans.*;
import com.sohu.mrd.domain.beans.TExpense;
import com.sohu.service.jinxiaocun.ExpenseService;
import com.sohu.service.jinxiaocun.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by worgen on 2016/6/25.
 */
@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    private TExpenseMapper tExpenseMapper;
    @Autowired
    private TExpenseAssociationMapper tExpenseAssociationMapper;

    @Override
    public int insert(TExpense tExpense) {
        return tExpenseMapper.insertSelective(tExpense);
    }

    @Override
    public int delete(int id) {
        TExpense tExpense = get(id);
        if( tExpense == null ) {
            return -1;
        }
        tExpense = new TExpense();
        tExpense.setDelFlag(1);
        return update(id, tExpense);
    }

    @Override
    public int update(int id, TExpense tExpense) {
        tExpense.setId(id);
        return tExpenseMapper.updateByPrimaryKeySelective(tExpense);
    }
    @Override
    public TExpense get(int id) {
        TExpenseExample tExpenseExample = new TExpenseExample();
        tExpenseExample.createCriteria().andDelFlagEqualTo(0).andIdEqualTo(id);
        List<TExpense> tExpenses = tExpenseMapper.selectByExample(tExpenseExample);
        return tExpenses.size() > 0 ? tExpenses.get(0) : null;
    }
    @Override
    public List<TExpense> query(TExpenseExample tExpenseExample) {
        return tExpenseMapper.selectByExample(tExpenseExample);
    }

    @Override
    public List<TExpenseAssociation> query(String serailNumber, int type,
                              int handlerId, int listerId, int storeId,
                              Date businessDateStart, Date businessDateEnd,
                              Date createdDateStart, Date createdDateEnd, int pageNumber, int pageSize) {
        TExpenseAssociationExample tExpenseExample = new TExpenseAssociationExample();
        TStoreExample tStoreExample = new TStoreExample();

        tStoreExample.createCriteria().andDelFlagEqualTo(0);
        tExpenseExample.createCriteria().andDelFlagEqualTo(0);
        tExpenseExample.settStoreExample(tStoreExample);
        if( storeId != 0 ){
            tExpenseExample.getOredCriteria().get(0).andStoreIdEqualTo(storeId);
        }
        if( serailNumber != null ){
            tExpenseExample.getOredCriteria().get(0).andSerialNumberEqualTo(serailNumber);
        }
        if( type != 0 ){
            tExpenseExample.getOredCriteria().get(0).andTypeEqualTo(type);
        }
        if( handlerId != 0 ){
            tExpenseExample.getOredCriteria().get(0).andHandlerEqualTo(handlerId);
        }
        if( listerId != 0 ){
            tExpenseExample.getOredCriteria().get(0).andListerEqualTo(listerId);
        }
        if( storeId != 0 ){
            tExpenseExample.getOredCriteria().get(0).andStoreIdEqualTo(storeId);
        }
        if(businessDateStart != null){
            tExpenseExample.getOredCriteria().get(0).andBusinessDateGreaterThanOrEqualTo(businessDateStart);
        }
        if(businessDateEnd != null){
            tExpenseExample.getOredCriteria().get(0).andBusinessDateLessThanOrEqualTo(businessDateStart);
        }
        if(createdDateStart != null){
            tExpenseExample.getOredCriteria().get(0).andCreatedTimeGreaterThanOrEqualTo(createdDateStart);
        }
        if(createdDateEnd != null){
            tExpenseExample.getOredCriteria().get(0).andCreatedTimeLessThanOrEqualTo(createdDateEnd);
        }

        int limitStart = (pageNumber-1)*pageSize;
        int limitEnd = pageNumber*pageSize;

        tExpenseExample.setOrderByClause("t_expense.id limit " + limitStart + "," + limitEnd);
        return tExpenseAssociationMapper.selectAssociationByExample(tExpenseExample);
    }
}
