package com.sohu.mrd.common.query;

/**
 * User: chenghaixing
 * Date: 2014-7-16
 * Time: 14:00:01
 */
public class BaseQuery implements Query {
    /**
     * 基本ID
     */
    private int id;
    /**
     * 基本值
     */
    private String value;
    /**
     * 分页后的记录开始的地方
     * 第一条记录是1
     */
    private int startRow;
    /**
     * 分页后的记录结束的地方
     */
    private int endRow;

    public BaseQuery() {
    }

    /**
     * 通过list得到的startrow和endrow来构造查询的。
     * @param paginatedList 分页
     */
    public BaseQuery(PaginatedList paginatedList) {
        this.startRow = paginatedList.getStartRow();
        this.endRow = paginatedList.getEndRow();
    }
    /**
     * 取mysql分页时的起始行,mysql从0开始
     * @param startRow
     * @return
     */
    public int getMySqlStartRow(int startRow) {
        return startRow > 1 ? startRow - 1 : 0;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }
}
