package com.gongsibao.stat.dto;


import com.gongsibao.utils.AmountUtils;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

public class StatOfficeOrder implements Serializable {
    private static final long serialVersionUID = -8756589634018062278L;

    private int officeId;
    private String officeName;
    private int quantity;
    private int unEffectiveQuantity;
    private double amount;
    private Integer intAmount;
    private List<Integer> businessIds;
    private Integer supply_type;
    private Long sumPrice;
    private Long sumTime;
    private int customerCount;
    private int newCustomerCount;
    //财务专业查账ID
    private StringBuffer idStatusTime;

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return AmountUtils.round(amount, 2);
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void addAmount(double amount) {
        setAmount(AmountUtils.add(this.amount, amount));
    }

    public Integer getIntAmount() {
        return NumberUtils.doubleRoundInt(this.amount);
    }

    public void addQuantity(int quantity) {
        setQuantity(this.quantity + quantity);
    }

    public void addunEffectiveQuantity(int quantity) {
        setUnEffectiveQuantity(this.unEffectiveQuantity + quantity);
    }

    public List<Integer> getBusinessIds() {
        return businessIds;
    }

    public void setBusinessIds(List<Integer> businessIds) {
        this.businessIds = businessIds;
    }

    public Integer getSupply_type() {
        return supply_type;
    }

    public void setSupply_type(Integer supply_type) {
        this.supply_type = supply_type;
    }

    public Long getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Long sumPrice) {
        this.sumPrice = sumPrice;
    }

    public int getUnEffectiveQuantity() {
        return unEffectiveQuantity;
    }

    public void setUnEffectiveQuantity(int unEffectiveQuantity) {
        this.unEffectiveQuantity = unEffectiveQuantity;
    }

    public long getSumTime() {
        return sumTime==null?0:sumTime;
    }

    public void setSumTime(long sumTime) {
        this.sumTime = sumTime;
    }

    public void addSumTime(long Time) {
        setSumTime(this.sumTime==null?Time:this.sumTime+Time);
    }

    public long getAvgTime(){
        if(this.sumTime != null && this.sumTime != 0 && this.customerCount != 0){
            return this.sumTime / this.customerCount;
        }

        return 0;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public void addCustomerCount(int person) {
        setCustomerCount(this.customerCount + person);
    }

    public int getNewCustomerCount() {
        return newCustomerCount;
    }

    public void setNewCustomerCount(int newCustomerCount) {
        this.newCustomerCount = newCustomerCount;
    }

    public void addNewCustomerCount(int person) {
        setNewCustomerCount(this.newCustomerCount + person);
    }

    public StringBuffer getIdStatusTime() {
        return idStatusTime;
    }

    public void setIdStatusTime(StringBuffer idStatusTime) {
        this.idStatusTime = idStatusTime;
    }

    public void addIdStatusTime(String idStatusTime) {
        setIdStatusTime(StringUtils.isBlank(this.getIdStatusTime())?new StringBuffer().append(idStatusTime):this.getIdStatusTime().append(idStatusTime));
    }
}
