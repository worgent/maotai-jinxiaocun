package com.sohu.dao;

import com.sohu.mrd.domain.beans.*;

import java.util.List;

public interface TReceiptAssociationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbggenerated
     */
    TReceiptAssociation selectAssociationByPrimaryKey(int id);
    List<TReceiptAssociation> selectAssociationByExample(TReceiptAssociationExample tReceiptAssociationExample);

}