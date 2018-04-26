package com.gongsibao.stat.dto;

import java.io.Serializable;


public class StatMarketingCustomer implements Serializable {
    private static final long serialVersionUID = -8756589634018062278L;

    private Integer pkid;

    private String name;

    private Integer today;

    private Integer week;

    private Integer month;

    private int quantity;

    private double todayRate;

    private double weekRate;

    private double monthRate;

    private Integer orderToday;

    private Integer orderWeek;

    private Integer orderMonth;

    public double getTodayRate() {
        return todayRate;
    }

    public double getWeekRate() {
        return weekRate;
    }

    public double getMonthRate() {
        return monthRate;
    }

    public void setTodayRate(double todayRate) {
        this.todayRate = todayRate;
    }

    public void setWeekRate(double weekRate) {
        this.weekRate = weekRate;
    }

    public void setMonthRate(double monthRate) {
        this.monthRate = monthRate;
    }

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

    public Integer getToday() {
        return today;
    }

    public void setToday(Integer today) {
        this.today = today;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity() {
        setQuantity(this.quantity + 1);
    }

    public Integer getOrderToday() {
        return orderToday;
    }

    public void setOrderToday(Integer orderToday) {
        this.orderToday = orderToday;
    }

    public Integer getOrderWeek() {
        return orderWeek;
    }

    public void setOrderWeek(Integer orderWeek) {
        this.orderWeek = orderWeek;
    }

    public Integer getOrderMonth() {
        return orderMonth;
    }

    public void setOrderMonth(Integer orderMonth) {
        this.orderMonth = orderMonth;
    }

    public void addQuantity(int quantity) {
        setQuantity(this.quantity + quantity);
    }
}
