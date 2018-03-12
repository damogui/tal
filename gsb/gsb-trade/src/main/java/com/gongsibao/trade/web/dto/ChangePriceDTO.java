package com.gongsibao.trade.web.dto;

import java.util.Date;

import com.gongsibao.entity.trade.dic.AuditStatusType;

public class ChangePriceDTO {

	private Integer id;

	private String no;

	private Integer originalPrice;

	private Integer payablePrice;

	private Integer differencePrice;

	private String creator;

	private Date createTime;

	private AuditStatusType status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Integer getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Integer getPayablePrice() {
		return payablePrice;
	}

	public void setPayablePrice(Integer payablePrice) {
		this.payablePrice = payablePrice;
	}

	public Integer getDifferencePrice() {
		return differencePrice;
	}

	public void setDifferencePrice(Integer differencePrice) {
		this.differencePrice = differencePrice;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public AuditStatusType getStatus() {
		return status;
	}

	public void setStatus(AuditStatusType status) {
		this.status = status;
	}
}
