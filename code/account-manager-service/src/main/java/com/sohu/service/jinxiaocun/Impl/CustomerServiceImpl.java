package com.sohu.service.jinxiaocun.Impl;

import com.sohu.dao.TCustomerAssociationMapper;
import com.sohu.dao.TCustomerMapper;
import com.sohu.mrd.domain.beans.*;
import com.sohu.mrd.domain.beans.TCustomer;
import com.sohu.service.jinxiaocun.CustomerService;
import com.sohu.service.jinxiaocun.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by worgen on 2016/6/25.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private TCustomerMapper tCustomerMapper;

    @Autowired
    private TCustomerAssociationMapper tCustomerAssociationMapper;

    @Override
    public int insert(TCustomer tCustomer) {
        return tCustomerMapper.insertSelective(tCustomer);
    }

    @Override
    public int delete(int id) {
        TCustomer tCustomer = get(id);
        if( tCustomer == null ) {
            return -1;
        }
        tCustomer = new TCustomer();

        tCustomer.setDelFlag(1);
        return update(id, tCustomer);
    }

    @Override
    public int update(int id, TCustomer tCustomer) {
        tCustomer.setId(id);
        return tCustomerMapper.updateByPrimaryKeySelective(tCustomer);
    }
    @Override
    public TCustomer get(int id) {
        TCustomerExample tCustomerExample = new TCustomerExample();
        tCustomerExample.createCriteria().andDelFlagEqualTo(0).andIdEqualTo(id);
        List<TCustomer> tCustomers = tCustomerMapper.selectByExample(tCustomerExample);
        return tCustomers.size() > 0 ? tCustomers.get(0) : null;    }
    @Override
    public List<TCustomer> query(TCustomerExample tCustomerExample) {
        return tCustomerMapper.selectByExample(tCustomerExample);
    }

    @Override
    public List<TCustomer> query(int storeId, int pageNumber, int pageSize) {
        TCustomerExample tCustomerExample = new TCustomerExample();
        tCustomerExample.createCriteria().andDelFlagEqualTo(0);

        if( storeId != 0 ){
            tCustomerExample.createCriteria().andStoreIdEqualTo(storeId);
        }

        int limitStart = (pageNumber-1)*pageSize;
        int limitEnd = pageNumber*pageSize;

        tCustomerExample.setOrderByClause("t_customer.id limit "+limitStart+","+limitEnd);

        return tCustomerMapper.selectByExample(tCustomerExample);

    }

    @Override
    public List<TCustomerAssociation> query(String serialNumber, String name,
                                            String contactPhone, String officePhone,
                                            String contact, int storeId, int pageNumber, int pageSize) {
        TCustomerAssociationExample tCustomerAssociationExample = new TCustomerAssociationExample();
        TStoreExample tStoreExample = new TStoreExample();

        tStoreExample.createCriteria().andDelFlagEqualTo(0);
        tCustomerAssociationExample.createCriteria().andDelFlagEqualTo(0);
        tCustomerAssociationExample.settStoreExample(tStoreExample);

        if( serialNumber != null ){
            tCustomerAssociationExample.getOredCriteria().get(0).andSerailNumberEqualTo(serialNumber);
        }
        if( name != null ){
            tCustomerAssociationExample.getOredCriteria().get(0).andNameEqualTo(name);
        }
        if( contactPhone != null ){
            tCustomerAssociationExample.getOredCriteria().get(0).andContactPhoneEqualTo(contactPhone);
        }
        if( officePhone != null ){
            tCustomerAssociationExample.getOredCriteria().get(0).andOfficePhoneEqualTo(officePhone);
        }
        if( contact != null ){
            tCustomerAssociationExample.getOredCriteria().get(0).andContactEqualTo(contact);
        }
        if( storeId != 0 ){
            tCustomerAssociationExample.getOredCriteria().get(0).andStoreIdEqualTo(storeId);
        }

        int limitStart = (pageNumber-1)*pageSize;
        int limitEnd = pageNumber*pageSize;

        tCustomerAssociationExample.setOrderByClause("t_customer.id limit "+limitStart+","+limitEnd);

        return tCustomerAssociationMapper.selectAssociationByExample(tCustomerAssociationExample);
    }
}
