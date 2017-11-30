package com.gongsibao.entity.ma;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.ma.dic.SelingStatus;

@Table(name="ma_acquisition_demand_matching_detail",orderBy="matching_rate DESC",header="收购需求匹配明细")
public class AcquisitionDemandMatchingDetail extends Entity{

	

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -115440202329174639L;

	@Column(name="acquisition_demand_id",header="收购需求Id")
    private Integer acquisitionDemandId;
	
    @Column(name="selling_demand_id",header="出售需求Id")
    private Integer sellingDemandId;
    
    @Column(name="matching_rate",header="匹配度")
    private int matchingRate = 0;
    
	@Column(name="company_name",size=200,header="公司名称")
	private String companyName;
    
    @Column(name = "seling_status",header="状态")
    private SelingStatus selingStatus = SelingStatus.UNSOLD;

	public Integer getAcquisitionDemandId() {
		return acquisitionDemandId;
	}

	public void setAcquisitionDemandId(Integer acquisitionDemandId) {
		this.acquisitionDemandId = acquisitionDemandId;
	}

	public Integer getSellingDemandId() {
		return sellingDemandId;
	}

	public void setSellingDemandId(Integer sellingDemandId) {
		this.sellingDemandId = sellingDemandId;
	}

	public int getMatchingRate() {
		return matchingRate;
	}

	public void setMatchingRate(int matchingRate) {
		this.matchingRate = matchingRate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public SelingStatus getSelingStatus() {
		return selingStatus;
	}

	public void setSelingStatus(SelingStatus selingStatus) {
		this.selingStatus = selingStatus;
	}
}
