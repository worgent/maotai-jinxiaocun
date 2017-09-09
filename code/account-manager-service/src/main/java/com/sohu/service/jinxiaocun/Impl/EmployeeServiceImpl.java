package com.sohu.service.jinxiaocun.Impl;

import com.sohu.dao.TEmployeeMapper;
import com.sohu.mrd.domain.beans.*;
import com.sohu.mrd.domain.beans.TEmployee;
import com.sohu.service.jinxiaocun.EmployeeService;
import com.sohu.service.jinxiaocun.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by worgen on 2016/6/25.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private TEmployeeMapper tEmployeeMapper;

    @Override
    public int insert(TEmployee tEmployee) {
        return tEmployeeMapper.insertSelective(tEmployee);
    }

    @Override
    public int delete(int id) {
        TEmployee tEmployee = get(id);
        if( tEmployee == null ) {
            return -1;
        }
        tEmployee = new TEmployee();

        tEmployee.setDelFlag(1);
        return update(id, tEmployee);
    }

    @Override
    public int update(int id, TEmployee tEmployee) {
        tEmployee.setId(id);
        return tEmployeeMapper.updateByPrimaryKeySelective(tEmployee);
    }
    @Override
    public TEmployee get(int id) {
        TEmployeeExample tEmployeeExample = new TEmployeeExample();
        tEmployeeExample.createCriteria().andDelFlagEqualTo(0).andIdEqualTo(id);
        List<TEmployee> tEmployees = tEmployeeMapper.selectByExample(tEmployeeExample);
        return tEmployees.size() > 0 ? tEmployees.get(0) : null;    }
    @Override
    public List<TEmployee> query(TEmployeeExample tEmployeeExample) {
        return tEmployeeMapper.selectByExample(tEmployeeExample);
    }

    @Override
    public List<TEmployee> query(int storeId, int pageNumber, int pageSize) {
        TEmployeeExample tEmployeeExample = new TEmployeeExample();
        tEmployeeExample.createCriteria().andDelFlagEqualTo(0);

        if( storeId != 0 ){
            tEmployeeExample.createCriteria().andStoreIdEqualTo(storeId);
        }

        int limitStart = (pageNumber-1)*pageSize;
        int limitEnd = pageNumber*pageSize;

        tEmployeeExample.setOrderByClause("id limit "+limitStart+","+limitEnd);

        return tEmployeeMapper.selectByExample(tEmployeeExample);

    }
}
