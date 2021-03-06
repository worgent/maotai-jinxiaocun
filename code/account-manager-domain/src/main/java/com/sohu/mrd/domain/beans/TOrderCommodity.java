package com.sohu.mrd.domain.beans;

import java.util.Date;

public class TOrderCommodity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_commodity.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_commodity.order_id
     *
     * @mbggenerated
     */
    private Integer orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_commodity.commodity_id
     *
     * @mbggenerated
     */
    private Integer commodityId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_commodity.commondity_name
     *
     * @mbggenerated
     */
    private String commondityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_commodity.commodity_num
     *
     * @mbggenerated
     */
    private Integer commodityNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_commodity.commodity_price
     *
     * @mbggenerated
     */
    private Integer commodityPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_commodity.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_commodity.created_time
     *
     * @mbggenerated
     */
    private Date createdTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_commodity.updated_time
     *
     * @mbggenerated
     */
    private Date updatedTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_order_commodity.state
     *
     * @mbggenerated
     */
    private Integer state;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_commodity.id
     *
     * @return the value of t_order_commodity.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_commodity.id
     *
     * @param id the value for t_order_commodity.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_commodity.order_id
     *
     * @return the value of t_order_commodity.order_id
     *
     * @mbggenerated
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_commodity.order_id
     *
     * @param orderId the value for t_order_commodity.order_id
     *
     * @mbggenerated
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_commodity.commodity_id
     *
     * @return the value of t_order_commodity.commodity_id
     *
     * @mbggenerated
     */
    public Integer getCommodityId() {
        return commodityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_commodity.commodity_id
     *
     * @param commodityId the value for t_order_commodity.commodity_id
     *
     * @mbggenerated
     */
    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_commodity.commondity_name
     *
     * @return the value of t_order_commodity.commondity_name
     *
     * @mbggenerated
     */
    public String getCommondityName() {
        return commondityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_commodity.commondity_name
     *
     * @param commondityName the value for t_order_commodity.commondity_name
     *
     * @mbggenerated
     */
    public void setCommondityName(String commondityName) {
        this.commondityName = commondityName == null ? null : commondityName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_commodity.commodity_num
     *
     * @return the value of t_order_commodity.commodity_num
     *
     * @mbggenerated
     */
    public Integer getCommodityNum() {
        return commodityNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_commodity.commodity_num
     *
     * @param commodityNum the value for t_order_commodity.commodity_num
     *
     * @mbggenerated
     */
    public void setCommodityNum(Integer commodityNum) {
        this.commodityNum = commodityNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_commodity.commodity_price
     *
     * @return the value of t_order_commodity.commodity_price
     *
     * @mbggenerated
     */
    public Integer getCommodityPrice() {
        return commodityPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_commodity.commodity_price
     *
     * @param commodityPrice the value for t_order_commodity.commodity_price
     *
     * @mbggenerated
     */
    public void setCommodityPrice(Integer commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_commodity.remark
     *
     * @return the value of t_order_commodity.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_commodity.remark
     *
     * @param remark the value for t_order_commodity.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_commodity.created_time
     *
     * @return the value of t_order_commodity.created_time
     *
     * @mbggenerated
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_commodity.created_time
     *
     * @param createdTime the value for t_order_commodity.created_time
     *
     * @mbggenerated
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_commodity.updated_time
     *
     * @return the value of t_order_commodity.updated_time
     *
     * @mbggenerated
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_commodity.updated_time
     *
     * @param updatedTime the value for t_order_commodity.updated_time
     *
     * @mbggenerated
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_order_commodity.state
     *
     * @return the value of t_order_commodity.state
     *
     * @mbggenerated
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_order_commodity.state
     *
     * @param state the value for t_order_commodity.state
     *
     * @mbggenerated
     */
    public void setState(Integer state) {
        this.state = state;
    }
}