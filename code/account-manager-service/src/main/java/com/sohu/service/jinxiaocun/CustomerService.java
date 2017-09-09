package com.sohu.service.jinxiaocun;

import com.sohu.mrd.domain.beans.*;
import com.sohu.mrd.domain.beans.TCustomer;

import java.util.List;

/**
 * Created by worgen on 2016/6/25.
 */
public interface CustomerService {

    //增
    public int insert(TCustomer tCustomer);
    //删
    public int delete(int id);
    //改
    public int update(int id, TCustomer tCustomer);
    //查
    public TCustomer get(int id);
    public List<TCustomer> query(TCustomerExample tCustomerExample);


    //以上为同通用接口
    //查询分公司客户
    public List<TCustomer> query(int storeId, int pageNumber, int pageSize);
    //查询客户列表
    public List<TCustomerAssociation> query(String serialNumber, String name,
                                 String contactPhone, String officePhone,
                                 String contact, int storeId,
                                 int pageNumber, int pageSize);


}
