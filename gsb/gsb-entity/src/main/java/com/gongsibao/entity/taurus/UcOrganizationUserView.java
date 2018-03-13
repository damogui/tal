package com.gongsibao.entity.taurus;

import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="uc_organization_user_view",isView=true)
public class UcOrganizationUserView extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1280243436955421308L;

	private String firstLevelDepartment;
	
	private String twoLevelDepartment;
	
	private String threeLevelDepartment;
	
	private String name;
	
	private String mobile;

	private String loginCount;
	
	private String operationCount;
	
	private String monthLoginCount;
	
	private String monthOperationCount;
	
	private String weekLoginCount;
	
	private String weekOperationCount;
	
	public String getFirstLevelDepartment() {
		return firstLevelDepartment;
	}

	public void setFirstLevelDepartment(String firstLevelDepartment) {
		this.firstLevelDepartment = firstLevelDepartment;
	}

	public String getTwoLevelDepartment() {
		return twoLevelDepartment;
	}

	public void setTwoLevelDepartment(String twoLevelDepartment) {
		this.twoLevelDepartment = twoLevelDepartment;
	}

	public String getThreeLevelDepartment() {
		return threeLevelDepartment;
	}

	public void setThreeLevelDepartment(String threeLevelDepartment) {
		this.threeLevelDepartment = threeLevelDepartment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(String loginCount) {
		this.loginCount = loginCount;
	}

	public String getOperationCount() {
		return operationCount;
	}

	public void setOperationCount(String operationCount) {
		this.operationCount = operationCount;
	}

	public String getMonthLoginCount() {
		return monthLoginCount;
	}

	public void setMonthLoginCount(String monthLoginCount) {
		this.monthLoginCount = monthLoginCount;
	}

	public String getMonthOperationCount() {
		return monthOperationCount;
	}

	public void setMonthOperationCount(String monthOperationCount) {
		this.monthOperationCount = monthOperationCount;
	}

	public String getWeekLoginCount() {
		return weekLoginCount;
	}

	public void setWeekLoginCount(String weekLoginCount) {
		this.weekLoginCount = weekLoginCount;
	}

	public String getWeekOperationCount() {
		return weekOperationCount;
	}

	public void setWeekOperationCount(String weekOperationCount) {
		this.weekOperationCount = weekOperationCount;
	}
	
	
	
}
