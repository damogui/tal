package com.gongsibao.taurus.entity;


/**   
 * @ClassName:  CourtExecutive   
 * @Description:TODO 被执行人信息
 * @author: 韩伟
 * @date:   2017年10月19日 上午11:10:23   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class CourtExecutive implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 4910060843163099913L;

	/**   
	 * @Fields name : TODO(案号)   
	 */   
	private String caseCode;
	
	/**   
	 * @Fields name : TODO(状态)   
	 */   
	private String caseState;
	
	/**   
	 * @Fields name : TODO(时间)   
	 */   
	private String caseCreateTime;
	
	/**   
	 * @Fields name : TODO(执行法院)   
	 */   
	private String execCourtName;
	
	/**   
	 * @Fields name : TODO(执行标的)   
	 */   
	private String execMoney;

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getCaseState() {
		return caseState;
	}

	public void setCaseState(String caseState) {
		this.caseState = caseState;
	}

	public String getCaseCreateTime() {
		return caseCreateTime;
	}

	public void setCaseCreateTime(String caseCreateTime) {
		this.caseCreateTime = caseCreateTime;
	}

	public String getExecCourtName() {
		return execCourtName;
	}

	public void setExecCourtName(String execCourtName) {
		this.execCourtName = execCourtName;
	}

	public String getExecMoney() {
		return execMoney;
	}

	public void setExecMoney(String execMoney) {
		this.execMoney = execMoney;
	}
	 
}
