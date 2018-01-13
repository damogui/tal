package com.gongsibao.entity.crm;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

import com.gongsibao.entity.crm.dic.QualityCategory;

@Table(name="n_crm_task_quality",header="客户质量")
public class NCustomerTaskQuality extends BizEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -2257727131244260461L;

	
	@Column(name = "intention_category", header = "质量分类")
	private QualityCategory intentionCategory;
	
	@Column(name = "next_foolow_date_required", header = "下次跟进时间必填")
	private Boolean nextFoolowDateRequired = false;
	
    @Column(name = "required_info",header="必填信息", size = 200)
    private String requiredInfo;
    
    @Column(name = "next_foolow_type",header="下次跟进方式", size = 200)
    private String nextFoolowType;

	public QualityCategory getIntentionCategory() {
		return intentionCategory;
	}

	public void setIntentionCategory(QualityCategory intentionCategory) {
		this.intentionCategory = intentionCategory;
	}

	public Boolean getNextFoolowDateRequired() {
		return nextFoolowDateRequired;
	}

	public void setNextFoolowDateRequired(Boolean nextFoolowDateRequired) {
		this.nextFoolowDateRequired = nextFoolowDateRequired;
	}

	public String getRequiredInfo() {
		return requiredInfo;
	}

	public void setRequiredInfo(String requiredInfo) {
		this.requiredInfo = requiredInfo;
	}

	public String getNextFoolowType() {
		return nextFoolowType;
	}

	public void setNextFoolowType(String nextFoolowType) {
		this.nextFoolowType = nextFoolowType;
	}
}
