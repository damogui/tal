package com.gongsibao.trade.web.dto;

/**
 * Created by win on 2018/3/23.
 */
public class OrderInfoDTO {
    /*订单号*/
    private String orderNo;
    /*订单分配金额*/
    private String orderCut;
    /*付款类别*/
    private String payType;
    /*回款业绩使用*/
    /*订单金额*/
    private Integer payablePrice;
    /*已付金额*/
    private Integer paidPrice;
    /*客户名称*/
    private String accountName;
    /*客户电话*/
    private String accountMobile;
    /*创建时间*/
    private String addTime;
    /*订单来源*/
    private String platformSource;
    /*付款状态*/
    private String payStatus;
    /*分期次数*/
    private Integer installmentCount;
    /*渠道订单*/
    private String channelOrderNo;
    /*未划分回款金额*/
    private String unAllotPayPrice;
    /*备注*/
    private String remark;


    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderCut() {
        return orderCut;
    }

    public void setOrderCut(String orderCut) {
        this.orderCut = orderCut;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Integer getPayablePrice() {
        return payablePrice;
    }

    public void setPayablePrice(Integer payablePrice) {
        this.payablePrice = payablePrice;
    }

    public Integer getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(Integer paidPrice) {
        this.paidPrice = paidPrice;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountMobile() {
        return accountMobile;
    }

    public void setAccountMobile(String accountMobile) {
        this.accountMobile = accountMobile;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getPlatformSource() {
        return platformSource;
    }

    public void setPlatformSource(String platformSource) {
        this.platformSource = platformSource;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getInstallmentCount() {
        return installmentCount;
    }

    public void setInstallmentCount(Integer installmentCount) {
        this.installmentCount = installmentCount;
    }

    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    public void setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
    }

    public String getUnAllotPayPrice() {
        return unAllotPayPrice;
    }

    public void setUnAllotPayPrice(String unAllotPayPrice) {
        this.unAllotPayPrice = unAllotPayPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
