package com.gongsibao.entity.ma;

import org.netsharp.core.annotations.Column;
import org.netsharp.entity.BizEntity;

public abstract class Company extends BizEntity {

	private static final long serialVersionUID = 8311296121900747580L;

	@Column(name="selling_demand_id",header="销售需求")
	private Integer sellingDemandId;

	public Integer getSellingDemandId() {
		return sellingDemandId;
	}

	public void setSellingDemandId(Integer sellingDemandId) {
		this.sellingDemandId = sellingDemandId;
	}
}
