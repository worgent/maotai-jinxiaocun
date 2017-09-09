package com.sohu.mrd.domain.util;

/**
 * 带分页查询基本组件
 * User: yangsiyong@360buy.com
 * Date: 2010-4-16
 * Time: 13:59:19
 */
public interface Query {

    /**
     * 基本查询的ID
     * @return id
     */
    int getId();

    /**
     * 基本查询的字符串值
     * @return value
     */
    String getValue();

    /**
     * 起始记录号
     * @return row
     */
    int getStartRow();

    /**
     * 结束记录号
     * @return row
     */
    int getEndRow();
}
