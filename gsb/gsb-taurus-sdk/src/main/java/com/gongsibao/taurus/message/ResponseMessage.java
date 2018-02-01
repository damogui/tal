package com.gongsibao.taurus.message;

import java.util.ArrayList;
import java.util.List;

import com.gongsibao.taurus.entity.IEntity;

public class ResponseMessage<T extends IEntity> {

	private int result = 0;
	
	private int totalSize = 0;
	
	private int pageSize = 0;
	
	private int currentPage = 0;
	
	private String resultMsg;
	
	private List<T> list = new ArrayList<T>();

	private T data;
	
	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
