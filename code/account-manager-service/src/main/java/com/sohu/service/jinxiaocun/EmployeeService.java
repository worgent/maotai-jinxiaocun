package com.sohu.service.jinxiaocun;

import com.sohu.mrd.domain.beans.*;
import com.sohu.mrd.domain.beans.TEmployee;

import java.util.List;

/**
 * Created by worgen on 2016/6/25.
 */
public interface EmployeeService {

    //增
    public int insert(TEmployee tEmployee);
    //删
    public int delete(int id);
    //改
    public int update(int id, TEmployee tEmployee);
    //查
    public TEmployee get(int id);
    public List<TEmployee> query(TEmployeeExample tEmployeeExample);

    //查询分公司员工
    public List<TEmployee> query(int storeId, int pageNumber, int pageSize);
}
