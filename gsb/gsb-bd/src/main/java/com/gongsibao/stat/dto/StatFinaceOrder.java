package com.gongsibao.stat.dto;

import com.gongsibao.utils.AmountUtils;

import java.io.Serializable;


public class StatFinaceOrder implements Serializable {
    private static final long serialVersionUID = -8756589634018062278L;

    private Integer pkid;
    private String name;
    //财务专业查账ID
    private String idStatusTime;

    private double today;
    private double yesterday;
    private double month;
    private double lastWeek;
    private double lastMonth;
    private double year;

    private Integer monthQuantity;

    private Integer lastMonthQuantity;

    public Integer getPkid() {
        return pkid;
    }

    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getToday() {
        return today;
    }

    public void setToday(double today) {
        this.today = today;
    }

    public double getYesterday() {
        return yesterday;
    }

    public void setYesterday(double yesterday) {
        this.yesterday = yesterday;
    }

    public double getLastWeek() {
        return lastWeek;
    }

    public void setLastWeek(double lastWeek) {
        this.lastWeek = lastWeek;
    }

    public double getMonth() {
        return month;
    }

    public void setMonth(double month) {
        this.month = month;
    }

    public double getLastMonth() {
        return lastMonth;
    }

    public void setLastMonth(double lastMonth) {
        this.lastMonth = lastMonth;
    }

    public double getYear() {
        return year;
    }

    public void setYear(double year) {
        this.year = year;
    }

    public void addToday(double amount) {
        setToday(AmountUtils.add(this.today, amount));
    }

    public void addYesterday(double amount) {
        setYesterday(AmountUtils.add(this.yesterday, amount));
    }

    public void addLastWeek(double amount) {
        setLastWeek(AmountUtils.add(this.lastWeek, amount));
    }

    public void addMonth(double amount) {
        setMonth(AmountUtils.add(this.month, amount));
    }

    public void addLastMonth(double amount) {
        setLastMonth(AmountUtils.add(this.lastMonth, amount));
    }

    public void addYear(double amount) {
        setYear(AmountUtils.add(this.year, amount));
    }

    public Integer getMonthQuantity() {
        return monthQuantity;
    }

    public void setMonthQuantity(Integer monthQuantity) {
        this.monthQuantity = monthQuantity;
    }

    public Integer getLastMonthQuantity() {
        return lastMonthQuantity;
    }

    public void setLastMonthQuantity(Integer lastMonthQuantity) {
        this.lastMonthQuantity = lastMonthQuantity;
    }

    public String getIdStatusTime() {
        return idStatusTime;
    }

    public void setIdStatusTime(String idStatusTime) {
        this.idStatusTime = idStatusTime;
    }
}
