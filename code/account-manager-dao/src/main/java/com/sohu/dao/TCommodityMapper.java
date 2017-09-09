package com.sohu.dao;

import com.sohu.mrd.domain.beans.TCommodity;
import com.sohu.mrd.domain.beans.TCommodityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCommodityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_commodity
     *
     * @mbggenerated
     */
    int countByExample(TCommodityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_commodity
     *
     * @mbggenerated
     */
    int deleteByExample(TCommodityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_commodity
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_commodity
     *
     * @mbggenerated
     */
    int insert(TCommodity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_commodity
     *
     * @mbggenerated
     */
    int insertSelective(TCommodity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_commodity
     *
     * @mbggenerated
     */
    List<TCommodity> selectByExample(TCommodityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_commodity
     *
     * @mbggenerated
     */
    TCommodity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_commodity
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TCommodity record, @Param("example") TCommodityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_commodity
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TCommodity record, @Param("example") TCommodityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_commodity
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TCommodity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_commodity
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TCommodity record);
}