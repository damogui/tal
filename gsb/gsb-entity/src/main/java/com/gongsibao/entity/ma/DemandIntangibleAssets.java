package com.gongsibao.entity.ma;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.ma.dic.IntangibleAssets;

@Table(name="ma_demand_intangible_assets",header="无形资产")
public class  DemandIntangibleAssets extends Entity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 634357380395300248L;

//    @JsonIgnore
//    @Reference(foreignKey="demandId")
//    private Demand demand;
    
    @Column(name="demand_id",header="需求Id")
    private Integer demandId;
    
    @Column(name="intangible_assets",header="无形资产")
    private IntangibleAssets intangibleAssets;

//	public Demand getDemand() {
//		return demand;
//	}
//
//	public void setDemand(Demand demand) {
//		this.demand = demand;
//	}

	public Integer getDemandId() {
		return demandId;
	}

	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}

	public IntangibleAssets getIntangibleAssets() {
		return intangibleAssets;
	}

	public void setIntangibleAssets(IntangibleAssets intangibleAssets) {
		this.intangibleAssets = intangibleAssets;
	}

}
