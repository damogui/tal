package com.gongsibao.trade.web.dto;

/**
 * Created by win on 2018/3/22.
 */
public class AuditLogDTO {

    private Integer id;
    private String creator;
    private String option;
    private String remark;
    private String createTime;
/*订单id*/
    private Integer orderId;
    //订单详情用
    /*审核编号*/
    private   String auditNo;
    /*原价金额*/
    private Integer totalPrice;
    /*应付金额*/
    private Integer payablePrice;
    /*订单业绩分配额*/
    private Integer amount;
    /*审核状态*/
    private String statusType;
    /*审核通过时间*/
    private String confirmTime;





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAuditNo() {
        return auditNo;
    }

    public void setAuditNo(String auditNo) {
        this.auditNo = auditNo;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getPayablePrice() {
        return payablePrice;
    }

    public void setPayablePrice(Integer payablePrice) {
        this.payablePrice = payablePrice;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }



    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
