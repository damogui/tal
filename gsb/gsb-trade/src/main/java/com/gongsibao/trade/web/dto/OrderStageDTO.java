package com.gongsibao.trade.web.dto;

import java.util.List;

import com.gongsibao.entity.trade.NOrderStage;
import com.gongsibao.entity.trade.dic.OrderStageNum;

public class OrderStageDTO {
	private Integer orderId;
	private Integer payablePrice;
	private Integer paidPrice;
	private Integer stageNum; 
	private List<NOrderStage> stages;
	
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getPayablePrice() {
		return payablePrice;
	}
	public void setPayablePrice(Integer payablePrice) {
		this.payablePrice = payablePrice;
	}
	public Integer getPaidPrice() {
		return paidPrice;
	}
	public void setPaidPrice(Integer paidPrice) {
		this.paidPrice = paidPrice;
	}
	public Integer getStageNum() {
		return stageNum;
	}
	public void setStageNum(Integer stageNum) {
		this.stageNum = stageNum;
	}
	public List<NOrderStage> getStages() {
		return stages;
	}
	public void setStages(List<NOrderStage> stages) {
		this.stages = stages;
	}
}
