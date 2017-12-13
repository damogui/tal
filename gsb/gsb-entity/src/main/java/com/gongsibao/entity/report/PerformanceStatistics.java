package com.gongsibao.entity.report;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.CatEntity;

import com.gongsibao.entity.report.dic.ReportDateType;
import com.gongsibao.entity.uc.Organization;
import com.gongsibao.entity.uc.User;

@Table(name="report_performance_statistics",header="业绩统计报表")
public class PerformanceStatistics extends CatEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -63320755248597454L;
	
	
	@Column(name = "type", header = "统计时间类型")
	private ReportDateType type;
	
	@Column(name = "organizationId", header = "部门")
	private Organization organization;

	@Column(name = "organization_id", header = "部门Id")
	private Integer organizationId;
	
    @Column(name="owner_id",header="业务员Id")
    private Integer ownerId;
    
    @Reference(foreignKey="ownerId")
    private User owner;
    
    @Column(name="year",header="年")
    private int year;
    
    @Column(name="quarter",header="季")
    private int quarter;
    
    @Column(name="month",header="月")
    private int month;
    
    @Column(name="week",header="周")
    private int week;
    
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

	public ReportDateType getType() {
		return type;
	}

	public void setType(ReportDateType type) {
		this.type = type;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getQuarter() {
		return quarter;
	}

	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
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
