package com.gongsibao.entity.taurus;

import java.math.BigDecimal;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="jnz_userRenewalStatistic_view",isView=true)
public class UserRenewalStatisticView extends Persistable{

	/**
	 * 金牛座用户续费统计
	 */
	private static final long serialVersionUID = 1L;

	/*id必须存在否者运行失败*/
	@Id
	@Auto
	private Integer id;
	private String dates;
	private BigDecimal totalAmount;
	private Long renewalTimes;
	
	
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
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Long getRenewalTimes() {
		return renewalTimes;
	}
	public void setRenewalTimes(Long renewalTimes) {
		this.renewalTimes = renewalTimes;
	}
}
