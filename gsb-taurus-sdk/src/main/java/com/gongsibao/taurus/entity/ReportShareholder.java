package com.gongsibao.taurus.entity;

/**   
 * @ClassName:  ReportShareholder   
 * @Description:TODO 股东出资
 * @author: 韩伟
 * @date:   2017年10月20日 下午3:27:05   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class ReportShareholder implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -385191465162107304L;

	
	/**   
	 * @Fields investorName : 股东名称
	 */   
	private String investorName; 
	
	/**   
	 * @Fields subscribeAmount : 认缴出资额
	 */   
	private String subscribeAmount; 
	
	/**   
	 * @Fields subscribeTime : 认缴出资时间  
	 */   
	private String subscribeTime; 
	
	/**   
	 * @Fields subscribeType :认缴出资方式
	 */   
	private String subscribeType; 
	
	/**   
	 * @Fields paidAmount : 实缴出资额
	 */   
	private String paidAmount; 
	
	/**   
	 * @Fields paidTime : 实缴出资时间
	 */   
	private String paidTime; 
	
	/**   
	 * @Fields paidType : 实缴出资方式
	 */   
	private String paidType;

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public String getSubscribeAmount() {
		return subscribeAmount;
	}

	public void setSubscribeAmount(String subscribeAmount) {
		this.subscribeAmount = subscribeAmount;
	}

	public String getSubscribeTime() {
		return subscribeTime;
	}

	public void setSubscribeTime(String subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public String getSubscribeType() {
		return subscribeType;
	}

	public void setSubscribeType(String subscribeType) {
		this.subscribeType = subscribeType;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getPaidTime() {
		return paidTime;
	}

	public void setPaidTime(String paidTime) {
		this.paidTime = paidTime;
	}

	public String getPaidType() {
		return paidType;
	}

	public void setPaidType(String paidType) {
		this.paidType = paidType;
	} 
}
