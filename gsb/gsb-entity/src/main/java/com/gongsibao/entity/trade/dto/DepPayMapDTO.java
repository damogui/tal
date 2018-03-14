package com.gongsibao.entity.trade.dto;

import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.OrderPayMap;

import java.util.List;

/**
 * Created by win on 2018/3/13.
 */
public class DepPayMapDTO {

    private Integer orderNo;
  /*付款账套*/
    private Integer setOfBooks;
    /*u8Bank*/
    private Integer u8Bank;
    /*付款账号名称*/
    private String offlinePayerName;
    /*付款账号*/
    private String offlineBankNo;
    /*是否一笔多单*/
    private Boolean payForOrderCount;
    /*付款金额*/
    private Integer amount;
    /*付款凭证*/
    private String files;
    /*付款说明*/
    private String offlineRemark;
    /*回款订单关联*/
    private List<OrderRelationDTO> orderRelations;


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

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
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

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
}


