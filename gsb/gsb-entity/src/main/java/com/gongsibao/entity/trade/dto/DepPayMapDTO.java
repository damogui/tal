package com.gongsibao.entity.trade.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win on 2018/3/13.
 */
public class DepPayMapDTO {

    //    private Integer orderNo;//从下面读取
  /*是否线上支付*/
    private Boolean isOnlinePay = false;
    /*未创建业绩总额*/
    private Integer noCutAmount = 0;
    /*在线支付的payid*/
    private Integer payId = 0;

    /*付款账套*/
    private Integer setOfBooks = 0;
    /*u8Bank*/
    private Integer u8Bank = 0;
    /*付款账号名称*/
    private String offlinePayerName = "";
    /*付款账号*/
    private String offlineBankNo = "";
    /*是否一笔多单*/
    private Boolean payForOrderCount = false;
    /*付款金额*/
    private Integer amount = 0;
//    /*付款凭证*/
//    private String files = "";
    /*付款说明*/
    private String offlineRemark = "";
    /*回款订单关联*/
    private List<OrderRelationDTO> orderRelations = new ArrayList<> ();
    /*付款凭证图片*/
    private List<String> imgs = new ArrayList<> ();


    public Integer getSetOfBooks() {
        return setOfBooks;
    }

    public void setSetOfBooks(Integer setOfBooks) {
        this.setOfBooks = setOfBooks;
    }

    public Integer getU8Bank() {
        return u8Bank;
    }

    public void setU8Bank(Integer u8Bank) {
        this.u8Bank = u8Bank;
    }


    public String getOfflinePayerName() {
        return offlinePayerName;
    }

    public void setOfflinePayerName(String offlinePayerName) {
        this.offlinePayerName = offlinePayerName;
    }

    public String getOfflineBankNo() {
        return offlineBankNo;
    }

    public void setOfflineBankNo(String offlineBankNo) {
        this.offlineBankNo = offlineBankNo;
    }

    public Boolean getPayForOrderCount() {
        return payForOrderCount;
    }

    public void setPayForOrderCount(Boolean payForOrderCount) {
        this.payForOrderCount = payForOrderCount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }



    public String getOfflineRemark() {
        return offlineRemark;
    }

    public void setOfflineRemark(String offlineRemark) {
        this.offlineRemark = offlineRemark;
    }


    public List<OrderRelationDTO> getOrderRelations() {
        return orderRelations;
    }

    public void setOrderRelations(List<OrderRelationDTO> orderRelations) {
        this.orderRelations = orderRelations;
    }

    public Boolean getOnlinePay() {
        return isOnlinePay;
    }

    public void setOnlinePay(Boolean onlinePay) {
        isOnlinePay = onlinePay;
    }

    public Integer getNoCutAmount() {
        return noCutAmount;
    }

    public void setNoCutAmount(Integer noCutAmount) {
        this.noCutAmount = noCutAmount;
    }

    public Integer getPayId() {
        return payId;
    }

    public void setPayId(Integer payId) {
        this.payId = payId;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }
}


