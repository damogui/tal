package com.gongsibao.trade.web.dto;

import java.util.ArrayList;
import java.util.List;

import com.gongsibao.entity.bd.File;

public class AddTraceFileDTO {

	private Integer orderId;
	
	private Integer orderNo;
	
	private Integer orderProdId = 0;
	
	private Integer processStatusId = 0;
	
	private Integer workflowFileId = 0;
	
	private String workflowFileName = "";
	
	private String info = "";
	
	private List<File> files = new ArrayList<File>();
	
	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderProdId() {
		return orderProdId;
	}

	public void setOrderProdId(Integer orderProdId) {
		this.orderProdId = orderProdId;
	}
	
	public Integer getProcessStatusId() {
		return processStatusId;
	}

	public void setProcessStatusId(Integer processStatusId) {
		this.processStatusId = processStatusId;
	}

	public Integer getWorkflowFileId() {
		return workflowFileId;
	}

	public void setWorkflowFileId(Integer workflowFileId) {
		this.workflowFileId = workflowFileId;
	}

	public String getWorkflowFileName() {
		return workflowFileName;
	}

	public void setWorkflowFileName(String workflowFileName) {
		this.workflowFileName = workflowFileName;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}
}
