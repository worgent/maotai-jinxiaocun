package com.sohu.dao;

import com.sohu.mrd.domain.beans.TExpenseAssociation;
import com.sohu.mrd.domain.beans.TExpenseAssociationExample;

import java.util.List;

public interface TExpenseAssociationMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer
     *
     * @mbggenerated
     */
    TExpenseAssociation selectAssociationByPrimaryKey(int id);
    List<TExpenseAssociation> selectAssociationByExample(TExpenseAssociationExample tExpenseAssociationExample);

}