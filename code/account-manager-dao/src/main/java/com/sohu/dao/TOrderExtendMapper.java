package com.sohu.dao;

import com.sohu.mrd.domain.beans.TOrderExample;

import java.util.List;
import java.util.Map;

public interface TOrderExtendMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_member
     *
     * @mbggenerated
     */
    Map<String,Integer> SumOrderByExample(TOrderExample tOrderExample);
    List<Map<String,Integer>> GroupStoreSumOrderByExample(TOrderExample tOrderExample);
    List<Map<String,Integer>> GroupCustomerSumOrderByExample(TOrderExample tOrderExample);
//    List<Map<String,Object>> queryForList(Map<String, Object> czMap);
//    int totalCount(Map<String, Object> czMap);
//
//    List<Map<String,Object>> vipQueryForList(Map<String, Object> czMap);
//    int vipTotalCount(Map<String, Object> czMap);
}