package com.gongsibao.api.conroller.taurus.dto;

import com.gongsibao.taurus.entity.EntRegistry;

public class CompanyRegistryInfoDTO {

	
	private EntRegistry entRegistry;
	
	private int years=0;
	
	private int projectCount = 0;
	
	private int tmCount = 0;
	
	private int patentCount = 0;
	
	private int entInvestCount = 0;
	
	private int worksCopyrightCount = 0;
	
	private int copyrightCount = 0;

	public EntRegistry getEntRegistry() {
		return entRegistry;
	}

	public void setEntRegistry(EntRegistry entRegistry) {
		this.entRegistry = entRegistry;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int years) {
		this.years = years;
	}

	public int getProjectCount() {
		return projectCount;
	}

	public void setProjectCount(int projectCount) {
		this.projectCount = projectCount;
	}

	public int getTmCount() {
		return tmCount;
	}

	public void setTmCount(int tmCount) {
		this.tmCount = tmCount;
	}

	public int getPatentCount() {
		return patentCount;
	}

	public void setPatentCount(int patentCount) {
		this.patentCount = patentCount;
	}

	public int getEntInvestCount() {
		return entInvestCount;
	}

	public void setEntInvestCount(int entInvestCount) {
		this.entInvestCount = entInvestCount;
	}

	public int getWorksCopyrightCount() {
		return worksCopyrightCount;
	}

	public void setWorksCopyrightCount(int worksCopyrightCount) {
		this.worksCopyrightCount = worksCopyrightCount;
	}

	public int getCopyrightCount() {
		return copyrightCount;
	}

	public void setCopyrightCount(int copyrightCount) {
		this.copyrightCount = copyrightCount;
	}
}
