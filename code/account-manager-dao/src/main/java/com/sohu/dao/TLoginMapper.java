package com.sohu.dao;

import com.sohu.mrd.domain.beans.TLogin;
import com.sohu.mrd.domain.beans.TLoginExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TLoginMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login
     *
     * @mbggenerated
     */
    int countByExample(TLoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login
     *
     * @mbggenerated
     */
    int deleteByExample(TLoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login
     *
     * @mbggenerated
     */
    int insert(TLogin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login
     *
     * @mbggenerated
     */
    int insertSelective(TLogin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login
     *
     * @mbggenerated
     */
    List<TLogin> selectByExample(TLoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login
     *
     * @mbggenerated
     */
    TLogin selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TLogin record, @Param("example") TLoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TLogin record, @Param("example") TLoginExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TLogin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_login
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TLogin record);
}