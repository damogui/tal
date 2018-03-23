package com.gongsibao.trade.web.dto;

import com.gongsibao.entity.bd.File;

import java.util.List;

/**
 * Created by win on 2018/3/23.
 */
/*订单回款审核用*/
public class OrderPayInfoDTO {
    /*账套*/
    private String accountName;
    /*付款方式*/
    private String payWay;
    /*付款账户名称*/
    private String bankName;
    /*付款账号*/
    private String bankNo;
    /*是否一笔多单*/
    private String isMoreOrder;
    /*付款金额*/
    private String amount;
    /*备注*/
    private String mark;
    /*凭证*/
    private List<File> files;
    private List<OrderInfoDTO> orderInfos;


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getIsMoreOrder() {
        return isMoreOrder;
    }

    public void setIsMoreOrder(String isMoreOrder) {
        this.isMoreOrder = isMoreOrder;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<OrderInfoDTO> getOrderInfos() {
        return orderInfos;
    }

    public void setOrderInfos(List<OrderInfoDTO> orderInfos) {
        this.orderInfos = orderInfos;
    }
}



