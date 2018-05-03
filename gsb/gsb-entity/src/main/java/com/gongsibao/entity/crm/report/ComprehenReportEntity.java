package com.gongsibao.entity.crm.report;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.bd.Dict;

@Table(isView=true, name = "")
public class ComprehenReportEntity extends BaseReportEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**   
	 * @Fields customerCount : TODO(全部客户数)   
	 */   
	private Integer customerCount = 0;
	
	/**   
	 * @Fields taskCount : TODO(全部商机数)   
	 */   
	private Integer taskCount = 0;
	
	/**   
	 * @Fields selfCustomerCount : TODO(自拓客户数)   
	 */   
	private Integer selfCustomerCount = 0;
	
	/**   
	 * @Fields selfTaskCount : TODO(自拓商机数)   
	 */   
	private Integer selfTaskCount = 0;
	
	/**   
	 * @Fields allocationTaskCount : TODO(分配商机数)   
	 */   
	private Integer allocationTaskCount = 0;
	
	/**   
	 * @Fields intoTaskCount : TODO(转入商机数)   
	 */   
	private Integer intoTaskCount = 0;
	
	/**   
	 * @Fields rollOutTaskCount : TODO(转出商机数)   
	 */   
	private Integer rollOutTaskCount = 0;
	
	/**   
	 * @Fields returnTaskCount : TODO(退回商机数)   
	 */   
	private Integer returnTaskCount = 0;
	
	/**   
	 * @Fields withdrawTaskCount : TODO(收回商机数)   
	 */   
	private Integer withdrawTaskCount = 0;
	
	/**   
	 * @Fields followTaskCount : TODO(跟进商机数)   
	 */   
	private Integer followTaskCount = 0;
	
	/**   
	 * @Fields unSignTaskCount : TODO(无法签单商机数)   
	 */   
	private Integer unSignTaskCount = 0;
	
	/**   
	 * @Fields checkAbnormalTaskCount : TODO(抽查异常商机数)   
	 */   
	private Integer checkAbnormalTaskCount = 0;
	
	/**   
	 * @Fields estimatedAmount : TODO(预估签单额)   
	 */   
	private Integer signingAmount = 0;

	/**   
	 * @Fields estimatedAmount : TODO(预估回款额)   
	 */   
	private Integer returnedAmount = 0;
	
	@Reference(foreignKey = "sourceId", header = "商机来源")
	private Dict source;

	@Column(name = "source_id", header = "商机来源")
	private Integer sourceId;
	
	public Integer getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(Integer customerCount) {
		this.customerCount = customerCount;
	}

	public Integer getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	public Integer getSelfCustomerCount() {
		return selfCustomerCount;
	}

	public void setSelfCustomerCount(Integer selfCustomerCount) {
		this.selfCustomerCount = selfCustomerCount;
	}

	public Integer getSelfTaskCount() {
		return selfTaskCount;
	}

	public void setSelfTaskCount(Integer selfTaskCount) {
		this.selfTaskCount = selfTaskCount;
	}

	public Integer getAllocationTaskCount() {
		return allocationTaskCount;
	}

	public void setAllocationTaskCount(Integer allocationTaskCount) {
		this.allocationTaskCount = allocationTaskCount;
	}

	public Integer getIntoTaskCount() {
		return intoTaskCount;
	}

	public void setIntoTaskCount(Integer intoTaskCount) {
		this.intoTaskCount = intoTaskCount;
	}

	public Integer getRollOutTaskCount() {
		return rollOutTaskCount;
	}

	public void setRollOutTaskCount(Integer rollOutTaskCount) {
		this.rollOutTaskCount = rollOutTaskCount;
	}

	public Integer getReturnTaskCount() {
		return returnTaskCount;
	}

	public void setReturnTaskCount(Integer returnTaskCount) {
		this.returnTaskCount = returnTaskCount;
	}

	public Integer getWithdrawTaskCount() {
		return withdrawTaskCount;
	}

	public void setWithdrawTaskCount(Integer withdrawTaskCount) {
		this.withdrawTaskCount = withdrawTaskCount;
	}

	public Integer getFollowTaskCount() {
		return followTaskCount;
	}

	public void setFollowTaskCount(Integer followTaskCount) {
		this.followTaskCount = followTaskCount;
	}

	public Integer getUnSignTaskCount() {
		return unSignTaskCount;
	}

	public void setUnSignTaskCount(Integer unSignTaskCount) {
		this.unSignTaskCount = unSignTaskCount;
	}

	public Integer getCheckAbnormalTaskCount() {
		return checkAbnormalTaskCount;
	}

	public void setCheckAbnormalTaskCount(Integer checkAbnormalTaskCount) {
		this.checkAbnormalTaskCount = checkAbnormalTaskCount;
	}

	public Integer getSigningAmount() {
		return signingAmount;
	}

	public void setSigningAmount(Integer signingAmount) {
		this.signingAmount = signingAmount;
	}

	public Integer getReturnedAmount() {
		return returnedAmount;
	}

	public void setReturnedAmount(Integer returnedAmount) {
		this.returnedAmount = returnedAmount;
	}

	public Dict getSource() {
		return source;
	}

	public void setSource(Dict source) {
		this.source = source;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	
}
