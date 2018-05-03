package com.gongsibao.entity.taurus;

import java.util.Date;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name="",isView=true)
public class UserConsumptionView  extends Persistable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Auto
	private Integer Id;
	private Date addTime;
	private String mobile;
	private String consumptionBehavior;
	private Integer price;
	
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getConsumptionBehavior() {
		return consumptionBehavior;
	}
	public void setConsumptionBehavior(String consumptionBehavior) {
		this.consumptionBehavior = consumptionBehavior;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
	
}
