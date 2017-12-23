package com.gongsibao.entity.report.customer;

import org.netsharp.core.annotations.Table;

@Table(isView=true, name = "")
public class CustomerStatusReport extends BaseCustomerReportEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 3772153347110609795L;

	
	/**   
	 * @Fields Count : TODO(未跟进：新增用户数)   
	 */   
	private Integer unTrackCount = 0;
	
	/**   
	 * @Fields ShareCount : TODO(未跟进：新增分享数)   
	 */   
	private Integer unTrackShareCount = 0;
	

	/**   
	 * @Fields Count : TODO(初步跟进：新增用户数)   
	 */   
	private Integer initialTrackCount = 0;
	
	/**   
	 * @Fields ShareCount : TODO(初步跟进：新增分享数)   
	 */   
	private Integer initialTrackShareCount = 0;
	
	/**   
	 * @Fields Count : TODO(意向签单：新增用户数)   
	 */   
	private Integer intentionCount = 0;
	
	/**   
	 * @Fields ShareCount : TODO(意向签单：新增分享数)   
	 */   
	private Integer intentionShareCount = 0;

	/**   
	 * @Fields Count : TODO(已签单：新增用户数)   
	 */   
	private Integer signCount = 0;
	
	/**   
	 * @Fields ShareCount : TODO(已签单：新增分享数)   
	 */   
	private Integer signShareCount = 0;
	 
	/**   
	 * @Fields Count : TODO(无效客户：新增用户数)   
	 */   
	private Integer invalidCount = 0;
	
	/**   
	 * @Fields ShareCount : TODO(无效客户：新增分享数)   
	 */   
	private Integer invalidShareCount = 0;
	
	/**   
	 * @Fields Count : TODO(流失客户：新增用户数)   
	 */   
	private Integer loseCount = 0;
	
	/**   
	 * @Fields ShareCount : TODO(流失客户：新增分享数)   
	 */   
	private Integer loseShareCount = 0;
	
	
	/**   
	 * @Fields ShareCount : TODO(渠道合作：新增分享数)   
	 */   
	private Integer channelCount = 0;
	/**   
	 * @Fields ShareCount : TODO(渠道合作：新增分享数)   
	 */   
	private Integer channelShareCount = 0;
	

	/**   
	 * @Fields ShareCount : TODO(潜在客户：新增分享数)   
	 */   
	private Integer potentialCount = 0;
	/**   
	 * @Fields ShareCount : TODO(潜在客户：新增分享数)   
	 */   
	private Integer potentialShareCount = 0;
	public Integer getUnTrackCount() {
		return unTrackCount;
	}
	public void setUnTrackCount(Integer unTrackCount) {
		this.unTrackCount = unTrackCount;
	}
	public Integer getUnTrackShareCount() {
		return unTrackShareCount;
	}
	public void setUnTrackShareCount(Integer unTrackShareCount) {
		this.unTrackShareCount = unTrackShareCount;
	}
	public Integer getInitialTrackCount() {
		return initialTrackCount;
	}
	public void setInitialTrackCount(Integer initialTrackCount) {
		this.initialTrackCount = initialTrackCount;
	}
	public Integer getInitialTrackShareCount() {
		return initialTrackShareCount;
	}
	public void setInitialTrackShareCount(Integer initialTrackShareCount) {
		this.initialTrackShareCount = initialTrackShareCount;
	}
	public Integer getIntentionCount() {
		return intentionCount;
	}
	public void setIntentionCount(Integer intentionCount) {
		this.intentionCount = intentionCount;
	}
	public Integer getIntentionShareCount() {
		return intentionShareCount;
	}
	public void setIntentionShareCount(Integer intentionShareCount) {
		this.intentionShareCount = intentionShareCount;
	}
	public Integer getSignCount() {
		return signCount;
	}
	public void setSignCount(Integer signCount) {
		this.signCount = signCount;
	}
	public Integer getSignShareCount() {
		return signShareCount;
	}
	public void setSignShareCount(Integer signShareCount) {
		this.signShareCount = signShareCount;
	}
	public Integer getInvalidCount() {
		return invalidCount;
	}
	public void setInvalidCount(Integer invalidCount) {
		this.invalidCount = invalidCount;
	}
	public Integer getInvalidShareCount() {
		return invalidShareCount;
	}
	public void setInvalidShareCount(Integer invalidShareCount) {
		this.invalidShareCount = invalidShareCount;
	}
	public Integer getLoseCount() {
		return loseCount;
	}
	public void setLoseCount(Integer loseCount) {
		this.loseCount = loseCount;
	}
	public Integer getLoseShareCount() {
		return loseShareCount;
	}
	public void setLoseShareCount(Integer loseShareCount) {
		this.loseShareCount = loseShareCount;
	}
	public Integer getChannelCount() {
		return channelCount;
	}
	public void setChannelCount(Integer channelCount) {
		this.channelCount = channelCount;
	}
	public Integer getChannelShareCount() {
		return channelShareCount;
	}
	public void setChannelShareCount(Integer channelShareCount) {
		this.channelShareCount = channelShareCount;
	}
	public Integer getPotentialCount() {
		return potentialCount;
	}
	public void setPotentialCount(Integer potentialCount) {
		this.potentialCount = potentialCount;
	}
	public Integer getPotentialShareCount() {
		return potentialShareCount;
	}
	public void setPotentialShareCount(Integer potentialShareCount) {
		this.potentialShareCount = potentialShareCount;
	}
}
