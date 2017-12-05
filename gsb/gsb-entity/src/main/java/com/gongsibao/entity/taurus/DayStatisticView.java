package com.gongsibao.entity.taurus;

import java.math.BigDecimal;
import java.util.Date;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="jnz_dayStatistic_view",isView=true)
public class DayStatisticView extends Persistable{

	/**
	 * 金牛座日统计数据
	 */
	private static final long serialVersionUID = 1L;
	
	/*id必须存在否者运行失败*/
	@Id
	@Auto
	private Integer id;
	private String dates;
	private Long userCount;
	private Long buyUserCount;
	private Long buyTimes;
	private Long renewalTimes;
	private BigDecimal totalAmount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public Long getUserCount() {
		return userCount;
	}
	public void setUserCount(Long userCount) {
		this.userCount = userCount;
	}
	public Long getBuyUserCount() {
		return buyUserCount;
	}
	public void setBuyUserCount(Long buyUserCount) {
		this.buyUserCount = buyUserCount;
	}
	public Long getBuyTimes() {
		return buyTimes;
	}
	public void setBuyTimes(Long buyTimes) {
		this.buyTimes = buyTimes;
	}
	public Long getRenewalTimes() {
		return renewalTimes;
	}
	public void setRenewalTimes(Long renewalTimes) {
		this.renewalTimes = renewalTimes;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
}
