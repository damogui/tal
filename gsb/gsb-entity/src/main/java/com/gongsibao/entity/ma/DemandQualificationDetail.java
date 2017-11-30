package com.gongsibao.entity.ma;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.ma.dic.EnterpriseQualification;


@Table(name="ma_demand_qualification_detail",header="企业资质明细")
public class DemandQualificationDetail extends Entity{

	private static final long serialVersionUID = -6722272228081823427L;

	@Column(name="demand_id",header="销售需求")
	private Integer demandId;
	
	@Column(name="Enterprise_qualification",header="企业资质类型")
	private EnterpriseQualification enterpriseQualification;

	public Integer getDemandId() {
		return demandId;
	}

	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}

	public EnterpriseQualification getEnterpriseQualification() {
		return enterpriseQualification;
	}

	public void setEnterpriseQualification(
			EnterpriseQualification enterpriseQualification) {
		this.enterpriseQualification = enterpriseQualification;
	}
}
