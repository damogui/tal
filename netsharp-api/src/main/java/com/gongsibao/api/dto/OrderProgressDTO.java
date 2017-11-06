package com.gongsibao.api.dto;

import java.util.Date;

/**
 * @author hw
 * 订单进度
 */
public class OrderProgressDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 702153780110868774L;

	/**
	 * 发生时间
	 */
	private Date createTime;
	
	/**
	 * 内容
	 */
	private String content;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
