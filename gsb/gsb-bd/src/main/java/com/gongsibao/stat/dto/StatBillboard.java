package com.gongsibao.stat.dto;

import com.gongsibao.utils.AmountUtils;

import java.io.Serializable;
import java.math.BigInteger;

public class StatBillboard implements Serializable {


    //排名
    private Integer rank;

    //排名url
    private String rankUrl;

    private String name;

    //部门
    private String department;

    //订单数
    private Integer orderCount;

    //头像
    private String headPicUrl;

    private String userIdStr;

    private Integer userId;

    //合作伙伴
    private String partner;

    private BigInteger price;

    //业绩(万)
    private Double amount;

    //客户数量
    private Integer customerCount;

    public String getRankUrl() {
        return rankUrl;
    }

    public void setRankUrl(String rankUrl) {
        this.rankUrl = rankUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public String getHeadPicUrl() {
        return headPicUrl;
    }

    public void setHeadPicUrl(String headPicUrl) {
        this.headPicUrl = headPicUrl;
    }

    public String getUserIdStr() {
        return userIdStr;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public void addPrice(BigInteger addPrice) {
        setPrice(this.price == null ? addPrice : this.price.add(addPrice));
    }

    public Double getAmount() {
        if (this.amount == null) {
            return 0.0;
        } else {
            return AmountUtils.round(amount, 2);
        }
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getCustomerCount() {
        return customerCount == null ? 0 : customerCount;
    }

    public void setCustomerCount(Integer customerCount) {
        this.customerCount = customerCount;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
