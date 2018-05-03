package com.gongsibao.entity.trade.dto;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

/**
 * 订单统计pojo
 * @author libinghua
 *
 */
@Table(name="",isView=true)
public class OrderStatisticsDTO extends Persistable implements java.util.Comparator<OrderStatisticsDTO>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Auto
	private Integer id;
	
	private String orderId;
	private String productId;
	
	private String productName;
	private int price;
	private String addTime;
	
	private int year;
	private int month;
	private int week;     //当年第几周
	private String ymd;   //年月日
	private String weekDay; //ymd对应的当前周的起始结束时间
	
	private int count=0;  //销售数量
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
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
	public String getYmd() {
		return ymd;
	}
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}
	
	@Override
	public int compare(OrderStatisticsDTO arg0, OrderStatisticsDTO arg1) {
		int p0=arg0.getPrice();
		int p1=arg1.getPrice();
		if(p0>p1)
			return -1;
		else if(p0<p1)
			return 1;
		return 0;
	}
}
