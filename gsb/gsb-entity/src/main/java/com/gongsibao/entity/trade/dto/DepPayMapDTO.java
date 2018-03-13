package com.gongsibao.entity.trade.dto;

import com.gongsibao.entity.trade.OrderPayMap;

import java.util.List;

/**
 * Created by win on 2018/3/13.
 */
public class DepPayMapDTO {

    //private Integer orderNo;
  /*付款账套*/
    private Integer setOfBooks;
    /*u8Bank*/
    private Integer u8Bank;
    /*付款账号名称*/
    private Integer offlinePayerName;
    /*付款账号*/
    private Integer offlineBankNo;
    /*是否一笔多单*/
    private Boolean payForOrderCount;
    /*付款金额*/
    private Integer amount;
    /*付款凭证*/
    private Integer files;
    /*付款说明*/
    private Integer offlineRemark;
    /*回款订单关联*/
    private List<OrderPayMap> orderPayMaps;


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

    public Integer getOfflinePayerName() {
        return offlinePayerName;
    }

    public void setOfflinePayerName(Integer offlinePayerName) {
        this.offlinePayerName = offlinePayerName;
    }

    public Integer getOfflineBankNo() {
        return offlineBankNo;
    }

    public void setOfflineBankNo(Integer offlineBankNo) {
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

    public Integer getFiles() {
        return files;
    }

    public void setFiles(Integer files) {
        this.files = files;
    }

    public Integer getOfflineRemark() {
        return offlineRemark;
    }

    public void setOfflineRemark(Integer offlineRemark) {
        this.offlineRemark = offlineRemark;
    }

    public List<OrderPayMap> getOrderPayMaps() {
        return orderPayMaps;
    }

    public void setOrderPayMaps(List<OrderPayMap> orderPayMaps) {
        this.orderPayMaps = orderPayMaps;
    }
}
