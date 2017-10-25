package com.gongsibao.entity.bd;

import java.sql.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="bd_preferential")
public class Preferential extends BaseEntity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 8325195630573994573L;
	@Column(name="payment_method",header="PaymentMethod")
    private String paymentMethod;
    @Column(header="category")
    private Integer category;
    @Column(name="preferential_amount",header="PreferentialAmount")
    private Integer preferentialAmount;
    @Column(header="discount")
    private Double discount;
    @Column(name="use_platform",header="UsePlatform")
    private String usePlatform;
    @Column(name="is_first_order_use",header="IsFirstOrderUse")
    private Integer isFirstOrderUse;
    @Column(name="is_overlay",header="IsOverlay")
    private Integer isOverlay;
    @Column(name="amount_limit",header="AmountLimit")
    private Integer amountLimit;
    @Column(name="goods_type",header="GoodsType")
    private String goodsType;
    @Column(name="start_date",header="StartDate")
    private Date startDate;
    @Column(name="end_date",header="EndDate")
    private Date endDate;
    @Column(header="count")
    private Integer count;
    @Column(name="is_enabled",header="IsEnabled")
    private Integer isEnabled;
    @Column(name="user_description",header="UserDescription")
    private String userDescription;
    @Column(header="explain1")
    private String explain1;
    @Column(header="remark")
    private String remark;
    @Column(name="add_user_id",header="AddUserId")
    private Integer addUserId;
    @Column(name="add_time",header="AddTime")
    private Date addTime;

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public Integer getCategory() {
        return category;
    }
    public void setCategory(Integer category) {
        this.category = category;
    }
    public Integer getPreferentialAmount() {
        return preferentialAmount;
    }
    public void setPreferentialAmount(Integer preferentialAmount) {
        this.preferentialAmount = preferentialAmount;
    }
    public Double getDiscount() {
        return discount;
    }
    public void setDiscount(Double discount) {
        this.discount = discount;
    }
    public String getUsePlatform() {
        return usePlatform;
    }
    public void setUsePlatform(String usePlatform) {
        this.usePlatform = usePlatform;
    }
    public Integer getIsFirstOrderUse() {
        return isFirstOrderUse;
    }
    public void setIsFirstOrderUse(Integer isFirstOrderUse) {
        this.isFirstOrderUse = isFirstOrderUse;
    }
    public Integer getIsOverlay() {
        return isOverlay;
    }
    public void setIsOverlay(Integer isOverlay) {
        this.isOverlay = isOverlay;
    }
    public Integer getAmountLimit() {
        return amountLimit;
    }
    public void setAmountLimit(Integer amountLimit) {
        this.amountLimit = amountLimit;
    }
    public String getGoodsType() {
        return goodsType;
    }
    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public Integer getIsEnabled() {
        return isEnabled;
    }
    public void setIsEnabled(Integer isEnabled) {
        this.isEnabled = isEnabled;
    }
    public String getUserDescription() {
        return userDescription;
    }
    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }
//    public String getExplain() {
//        return explain;
//    }
//    public void setExplain(String explain) {
//        this.explain = explain;
//    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Integer getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(Integer addUserId) {
        this.addUserId = addUserId;
    }
    public Date getAddTime() {
        return addTime;
    }
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}