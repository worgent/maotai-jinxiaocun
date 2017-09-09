package com.sohu.service.jinxiaocun;

import com.sohu.mrd.domain.beans.*;
import com.sohu.mrd.domain.beans.TOrder;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by worgen on 2016/6/25.
 */
public interface OrderService {
    //增
    public int insert(TOrder tOrder);
    //删
    public int delete(int id);
    //改
    public int update(int id, TOrder tOrder);
    //查
    public TOrder get(int id);
    public List<TOrder> query(TOrderExample tOrderExample);

    //
    public List<TOrder> query(int storeId);
    public List<TOrder> queryCustomerOrders(int customerId);


    //统计门店总收款
    public Map<String, Integer> queryStoreSumOrder(int storeId);
    //统计客户总收款
    public Map<String, Integer> queryCustomerSumOrder(int customerId);

    //统计门店收款总额,按门店分组
    public List<Map<String, Integer>> queryStoreSumOrders(int pageNumber, int pageSize);
    //统计门店收款总额,按客户分组
    public List<Map<String, Integer>> queryCustomerSumOrders(int storeId, int pageNumber, int pageSize);


    public List<TOrder> query(String serailNumber,
                              int customerId, int handlerId,
                              int listerId, int storeId,
                              Date businessDateStart, Date businessDateEnd,
                              Date createdDateStart, Date createdDateEnd,
                              int pageNumber, int pageSize);

    //查询关联，包含门店，订单货品信息
    public List<TOrderAssociation> queryAssociations(String serailNumber,
                              int customerId, int handlerId,
                              int listerId, int storeId,
                              Date businessDateStart, Date businessDateEnd,
                              Date createdDateStart, Date createdDateEnd,
                              int pageNumber, int pageSize);
}
