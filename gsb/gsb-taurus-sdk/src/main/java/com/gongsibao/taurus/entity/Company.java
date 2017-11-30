package com.gongsibao.taurus.entity;

public class Company implements IEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5623016235634425004L;

	private int entId;
	
	private String  province;
	
	private String domainEntName;
	
	private String entName;
	
	private String capitalUnit;
	
	private String entLegalperson;
	
	private String entStatus;
	
	private String capitalStandard;
	
	private String foundedTime;

	public int getEntId() {
		return entId;
	}

	public void setEntId(int entId) {
		this.entId = entId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDomainEntName() {
		return domainEntName;
	}

	public void setDomainEntName(String domainEntName) {
		this.domainEntName = domainEntName;
	}

	public String getEntName() {
		return entName;
	}

	public void setEntName(String entName) {
		this.entName = entName;
	}

	public String getCapitalUnit() {
		return capitalUnit;
	}

	public void setCapitalUnit(String capitalUnit) {
		this.capitalUnit = capitalUnit;
	}

	public String getEntLegalperson() {
		return entLegalperson;
	}

	public void setEntLegalperson(String entLegalperson) {
		this.entLegalperson = entLegalperson;
	}

	public String getEntStatus() {
		return entStatus;
	}

	public void setEntStatus(String entStatus) {
		this.entStatus = entStatus;
	}

	public String getCapitalStandard() {
		return capitalStandard;
	}

	public void setCapitalStandard(String capitalStandard) {
		this.capitalStandard = capitalStandard;
	}

	public String getFoundedTime() {
		return foundedTime;
	}

	public void setFoundedTime(String foundedTime) {
		this.foundedTime = foundedTime;
	}
}
