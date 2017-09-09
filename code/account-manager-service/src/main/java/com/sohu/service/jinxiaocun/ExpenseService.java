package com.sohu.service.jinxiaocun;

import com.sohu.mrd.domain.beans.*;
import com.sohu.mrd.domain.beans.TExpense;

import java.util.Date;
import java.util.List;

/**
 * Created by worgen on 2016/6/25.
 */
public interface ExpenseService {

    //增
    public int insert(TExpense tExpense);
    //删
    public int delete(int id);
    //改
    public int update(int id, TExpense tExpense);
    //查
    public TExpense get(int id);
    public List<TExpense> query(TExpenseExample tExpenseExample);

    //

    public List<TExpenseAssociation> query(String serailNumber, int type,
                              int handlerId, int listerId, int storeId,
                              Date businessDateStart, Date businessDateEnd,
                              Date createdDateStart, Date createdDateEnd, int pageNumber, int pageSize);

    }
