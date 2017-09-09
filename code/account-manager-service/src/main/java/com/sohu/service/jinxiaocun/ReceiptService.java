package com.sohu.service.jinxiaocun;

import com.sohu.mrd.domain.beans.*;
import com.sohu.mrd.domain.beans.TReceipt;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by worgen on 2016/6/25.
 */
public interface ReceiptService {

    //增
    public int insert(TReceipt tReceipt);
    //删
    public int delete(int id);
    //改
    public int update(int id, TReceipt tReceipt);
    //查
    public TReceipt get(int id);
    public List<TReceipt> query(TReceiptExample tReceiptExample);

    public List<TReceipt> query(int storeId);
    public List<TReceipt> queryCustomerReceipts(int storeId);
    public List<TReceiptAssociation> queryAssociation(int storeId);
    //统计门店总收款
    public int queryStoreSumReceipt(int storeId);
    //统计客户总收款
    public int queryCustomerSumReceipt(int customerId);

    //统计门店收款总额,按门店分组
    public List<Map<String, Integer>> queryStoreSumReceipts(int pageNumber, int pageSize);
    //统计门店收款总额,按客户分组
    public List<Map<String, Integer>> queryCustomerSumReceipts(int storeId, int pageNumber, int pageSize);

//    public List<> querySumReceipt(int storeId, int customerId, Date businessDateStart, Date businessDateEnd,
//                               Date createdDateStart, Date createdDateEnd);
    //查询门店收款记录
    public List<TReceipt> query(String serailNumber, int customerId,
                              int handlerId, int listerId, int storeId,
                              Date businessDateStart, Date businessDateEnd,
                              Date createdDateStart, Date createdDateEnd, int pageNumber, int pageSize);

}
