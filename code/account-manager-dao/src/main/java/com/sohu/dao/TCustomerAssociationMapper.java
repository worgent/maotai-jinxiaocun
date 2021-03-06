package com.sohu.dao;

import com.sohu.mrd.domain.beans.TCustomer;
import com.sohu.mrd.domain.beans.TCustomerAssociation;
import com.sohu.mrd.domain.beans.TCustomerAssociationExample;
import com.sohu.mrd.domain.beans.TCustomerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TCustomerAssociationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbggenerated
     */
    TCustomerAssociation selectAssociationByPrimaryKey(int id);
    List<TCustomerAssociation> selectAssociationByExample(TCustomerAssociationExample tCustomerAssociationExample);

}