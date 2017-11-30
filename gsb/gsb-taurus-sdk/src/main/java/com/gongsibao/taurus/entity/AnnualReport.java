package com.gongsibao.taurus.entity;


/**   
 * @ClassName:  AnnualReport   
 * @Description:TODO 年报汇总
 * @author: 韩伟
 * @date:   2017年10月19日 上午11:13:18   
 *     
 * @Copyright: 2017 www.netsharp.org Inc. All rights reserved. 
 */
public class AnnualReport implements IEntity {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7849180497505908733L;

	
	/**   
	 * @Fields name : TODO(从业人数)   
	 */   
	private String employeeNum;
	
	/**   
	 * @Fields name : TODO(资产总额)   
	 */   
	private String totalAssets;
	
	/**   
	 * @Fields name : TODO(利润总额)   
	 */   
	private String totalProfit;
	
	/**   
	 * @Fields name : TODO(负债总额)   
	 */   
	private String totalLiability;
	 
	
	/**   
	 * @Fields name : TODO(企业名称)   
	 */   
	private String companyName;
	
	/**   
	 * @Fields name : TODO(邮政编码)   
	 */   
	private String postcode;
	
	/**   
	 * @Fields name : TODO(销售总额(营业总收入))   
	 */   
	private String totalSales;

	/**   
	 * @Fields name : TODO(经营者名称)   
	 */   
	private String operatorName;
	
	/**   
	 * @Fields name : TODO(净利润)   
	 */   
	private String retainedProfit;
	
	/**   
	 * @Fields name : TODO(纳税总额)   
	 */   
	private String totalTax;
	
	/**   
	 * @Fields name : TODO(年报年度)   
	 */   
	private String reportYear;
	 
	/**   
	 * @Fields name : TODO(注册号)   
	 */   
	private String regNumber;
	
	/**   
	 * @Fields name : TODO(所有者权益合计)   
	 */   
	private String totalEquity;
	
	/**   
	 * @Fields name : TODO(统一社会信用代码)   
	 */   
	private String creditCode;
	
	/**   
	 * @Fields name : TODO(企业联系电话)   
	 */   
	private String phoneNumber;
	 
	/**   
	 * @Fields name : TODO(企业通信地址)   
	 */   
	private String postalAddress;
	
	/**   
	 * @Fields name : TODO(主营业务收入)   
	 */   
	private String primeBusProfit;
	
	/**   
	 * @Fields name : TODO(id)   
	 */   
	private String id;
	
	/**   
	 * @Fields name : TODO(企业经营状态)   
	 */   
	private String manageState;
	
	/**   
	 * @Fields name : TODO(电子邮箱)   
	 */   
	private String email;
	

	public String getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}

	public String getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(String totalAssets) {
		this.totalAssets = totalAssets;
	}

	public String getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(String totalProfit) {
		this.totalProfit = totalProfit;
	}

	public String getTotalLiability() {
		return totalLiability;
	}

	public void setTotalLiability(String totalLiability) {
		this.totalLiability = totalLiability;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTotalSales() {
		return totalSales;
	}

	public void setTotalSales(String totalSales) {
		this.totalSales = totalSales;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getRetainedProfit() {
		return retainedProfit;
	}

	public void setRetainedProfit(String retainedProfit) {
		this.retainedProfit = retainedProfit;
	}

	public String getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(String totalTax) {
		this.totalTax = totalTax;
	}

	public String getReportYear() {
		return reportYear;
	}

	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getTotalEquity() {
		return totalEquity;
	}

	public void setTotalEquity(String totalEquity) {
		this.totalEquity = totalEquity;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getPrimeBusProfit() {
		return primeBusProfit;
	}

	public void setPrimeBusProfit(String primeBusProfit) {
		this.primeBusProfit = primeBusProfit;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getManageState() {
		return manageState;
	}

	public void setManageState(String manageState) {
		this.manageState = manageState;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
