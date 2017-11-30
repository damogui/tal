package com.gongsibao.api.conroller.taurus.dto;

public class CompanyDTO {

	/**   
	 * @Fields companyName : TODO(公司名称)   
	 */   
	private String companyName;
	
	/**   
	 * @Fields totalAmount : TODO(总潜在金额)   
	 */   
	private int totalAmount=0;
	
	/**   
	 * @Fields potentialDemandCount : TODO(潜在需求数量)   
	 */   
	private int potentialDemandCount = 0;
	
	/**   
	 * @Fields decisionMakerCount : TODO(决策人数量)   
	 */   
	private int decisionMakerCount = 0;
	
	private String runPhase = "初创期";

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getPotentialDemandCount() {
		return potentialDemandCount;
	}

	public void setPotentialDemandCount(int potentialDemandCount) {
		this.potentialDemandCount = potentialDemandCount;
	}

	public int getDecisionMakerCount() {
		return decisionMakerCount;
	}

	public void setDecisionMakerCount(int decisionMakerCount) {
		this.decisionMakerCount = decisionMakerCount;
	}

	public String getRunPhase() {
		return runPhase;
	}

	public void setRunPhase(String runPhase) {
		this.runPhase = runPhase;
	}
}
