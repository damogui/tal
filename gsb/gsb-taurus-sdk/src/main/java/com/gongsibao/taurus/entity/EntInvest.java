package com.gongsibao.taurus.entity;


/**   
 * @ClassName:  EntInvest   
 * @Description:TODO 对外投资
 * @author: 韩伟
 * @date:   2017年10月19日 上午11:10:08   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class EntInvest implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -5758940366001346702L;

	/**   
	 * @Fields name : TODO(注册资本)   
	 */   
	private String registeredCapital;
	
	/**   
	 * @Fields name : TODO(公司名称)   
	 */   
	private String companyName;
	
	/**   
	 * @Fields name : TODO(法人)   
	 */   
	private String legalPerson;
	
	/**   
	 * @Fields name : TODO(成立日期)   
	 */   
	private String foundDt;
	
	private String managementState;
	

	public String getManagementState() {
		return managementState;
	}

	public void setManagementState(String managementState) {
		this.managementState = managementState;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getFoundDt() {
		return foundDt;
	}

	public void setFoundDt(String foundDt) {
		this.foundDt = foundDt;
	}
}
