package com.gongsibao.entity.ma;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.ma.dic.IndustryFeature;

@Table(name="ma_acquisition_demand_industry_feature",header="行业特点")
public class AcquisitionDemandIndustryFeatureDetail extends Entity{

    
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 7398516089519031627L;

	@Column(name="acquisition_demand_id",header="需求Id")
    private Integer acquisitionDemandId;
    
    @Column(name="industry_feature",header="行业特点")
    private IndustryFeature industryFeature;

	public Integer getAcquisitionDemandId() {
		return acquisitionDemandId;
	}

	public void setAcquisitionDemandId(Integer acquisitionDemandId) {
		this.acquisitionDemandId = acquisitionDemandId;
	}

	public IndustryFeature getIndustryFeature() {
		return industryFeature;
	}

	public void setIndustryFeature(IndustryFeature industryFeature) {
		this.industryFeature = industryFeature;
	}
    
}
