package com.gongsibao.entity.cw;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.BizEntity;

import com.gongsibao.entity.cw.dict.FinanceDict;

@Table(name="cw_cost_type",header="费用类型字典表")
public class CostType extends BizEntity{

	/**  
	* @Fields field:field:{todo}  
	*/ 
	private static final long serialVersionUID = 2547500374303447046L;

	@Column(name="form_type",header="费用类型分类")
	private FinanceDict.FormType formType ;
	
	@Column(name="cash_item",header="现金流量code")
	private String cashItem;
	
	@Column(name="is_manual",header="是否手动生成")
	private Boolean isManual;

	public FinanceDict.FormType getFormType() {
		return formType;
	}

	public void setFormType(FinanceDict.FormType formType) {
		this.formType = formType;
	}

	public String getCashItem() {
		return cashItem;
	}

	public void setCashItem(String cashItem) {
		this.cashItem = cashItem;
	}

	public Boolean getIsManual() {
		return isManual;
	}

	public void setIsManual(Boolean isManual) {
		this.isManual = isManual;
	}
	
}
