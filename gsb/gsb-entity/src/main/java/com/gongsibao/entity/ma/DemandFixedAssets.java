package com.gongsibao.entity.ma;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.ma.dic.FixedAssets;

@Table(name="ma_demand_fixed_assets",header="固定资产")
public class DemandFixedAssets extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2130342884570462985L;

    @Column(name="demand_id",header="需求Id")
    private Integer demandId;
    
    @Column(name="fixed_assets",header="固定资产")
    private FixedAssets fixedAssets;

	public Integer getDemandId() {
		return demandId;
	}

	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}

	public FixedAssets getFixedAssets() {
		return fixedAssets;
	}

	public void setFixedAssets(FixedAssets fixedAssets) {
		this.fixedAssets = fixedAssets;
	}
}
