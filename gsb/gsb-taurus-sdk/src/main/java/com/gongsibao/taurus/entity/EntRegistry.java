package com.gongsibao.taurus.entity;


/**   
 * @ClassName:  Entregistry   
 * @Description:TODO 企业注册信息
 * @author: 韩伟
 * @date:   2017年10月19日 上午11:07:04   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class EntRegistry implements IEntity {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5827692360688469671L;

	/**   
	 * @Fields name : TODO(企业名称)   
	 */   
	private String name;
	
	/**   
	 * @Fields name : TODO(统一社会信用代码)   
	 */   
	private String creditCode;
	
	/**   
	 * @Fields name : TODO(工商注册号)   
	 */   
	private String registrID; 
	
	/**   
	 * @Fields name : TODO(企业名公司类型)   
	 */   
	private String companyType; 
	
	/**   
	 * @Fields name : TODO(企业名法定代表人)   
	 */   
	private String legalRepresentative; 
	
	/**   
	 * @Fields name : TODO(注册资本)   
	 */   
	private String registeredCapital; 
	
	/**   
	 * @Fields name : TODO(登记机关)   
	 */   
	private String registerOffice; 
	
	/**   
	 * @Fields name : TODO(企业地址)   
	 */   
	private String businessAddress;
	
	/**   
	 * @Fields name : TODO(经营范围)   
	 */   
	private String scope;
	
	
	/**   
	 * @Fields name : TODO(组织机构代码)   
	 */   
	private String organizationCode;
	 
	
	/**   
	 * @Fields name : TODO(经营状态)
	 */   
	private String managementState;
	
	/**   
	 * @Fields name : TODO(成立日期)   
	 */   
	private String foundation;
	
	/**   
	 * @Fields name : TODO(发照日期)   
	 */   
	private String dateIssue;
	
	/**   
	 * @Fields name : TODO(营业期限)   
	 */   
	private String businessTerm;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getRegistrID() {
		return registrID;
	}

	public void setRegistrID(String registrID) {
		this.registrID = registrID;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getLegalRepresentative() {
		return legalRepresentative;
	}

	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getRegisterOffice() {
		return registerOffice;
	}

	public void setRegisterOffice(String registerOffice) {
		this.registerOffice = registerOffice;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getManagementState() {
		return managementState;
	}

	public void setManagementState(String managementState) {
		this.managementState = managementState;
	}

	public String getFoundation() {
		return foundation;
	}

	public void setFoundation(String foundation) {
		this.foundation = foundation;
	}

	public String getDateIssue() {
		return dateIssue;
	}

	public void setDateIssue(String dateIssue) {
		this.dateIssue = dateIssue;
	}

	public String getBusinessTerm() {
		return businessTerm;
	}

	public void setBusinessTerm(String businessTerm) {
		this.businessTerm = businessTerm;
	}
}
