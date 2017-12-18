package com.gongsibao.entity.report;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.entity.CatEntity;

import com.gongsibao.entity.report.dic.ReportDateType;

public abstract class BasePerformanceEntity extends CatEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5378919754349857423L;
	
	@Column(name = "date_type", header = "统计时间类型")
	private ReportDateType dateType;

    @Column(name="year",header="年")
    private Integer year;
    
    @Column(name="season",header="季")
    private Integer season;
    
    @Column(name="month",header="月")
    private Integer month;
    
    @Column(name="week",header="周")
    private Integer week;
    
    @Column(name="day",header="日")
    private Integer day;
    
    @Column(name="date",header="日期")
    private Date date;
    
    @Column(name="receivable_amount",header="应收金额")
    private int receivableAmount = 0;
    
    @Column(name="paid_amount",header="实收金额")
    private int paidAmount = 0;

    @Column(name="refund_amount",header="退款金额")
    private int refundAmount = 0;
    
    @Column(name="net_receivables",header="净应收")
    private int netReceivables = 0;

    @Column(name="net_paid_amount",header="净实收")
    private int netPaidAmount = 0;

    @Column(name="product_count",header="销售量")
    private int productCount = 0;
    
    @Column(name="order_count",header="订单量")
    private int orderCount = 0;
    
	public ReportDateType getDateType() {
		return dateType;
	}

	public void setDateType(ReportDateType dateType) {
		this.dateType = dateType;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getReceivableAmount() {
		return receivableAmount;
	}

	public void setReceivableAmount(int receivableAmount) {
		this.receivableAmount = receivableAmount;
	}

	public int getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(int paidAmount) {
		this.paidAmount = paidAmount;
	}

	public int getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(int refundAmount) {
		this.refundAmount = refundAmount;
	}

	public int getNetReceivables() {
		return netReceivables;
	}

	public void setNetReceivables(int netReceivables) {
		this.netReceivables = netReceivables;
	}

	public int getNetPaidAmount() {
		return netPaidAmount;
	}

	public void setNetPaidAmount(int netPaidAmount) {
		this.netPaidAmount = netPaidAmount;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
}
