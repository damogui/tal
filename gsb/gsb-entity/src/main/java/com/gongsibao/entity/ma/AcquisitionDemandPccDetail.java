package com.gongsibao.entity.ma;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.pcc.entity.ProvinceCityCounty;

@Table(name="ma_acquisition_demand_pcd_detail",header="意向区域")
public class AcquisitionDemandPccDetail extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6417302992158120416L;
	

	@JsonIgnore
    @Reference(foreignKey="acquisitionDemandId")
    private AcquisitionDemand acquisitionDemand;
    
	@Column(name="acquisition_demand_id",header="收购需求")
	private Integer acquisitionDemandId;

	@Column(name="province_id")
	private Integer provinceId;
	
	@Reference(foreignKey="provinceId",header="省份")
	private ProvinceCityCounty province;
	
	@Column(name="city_id")
	private Integer cityId;
	
	@Reference(foreignKey="cityId",header="城市")
	private ProvinceCityCounty city;
	
	@Column(name="county_id")
	private Integer countyId;
	
	@Reference(foreignKey="countyId",header="区/县")
	private ProvinceCityCounty county;

	public AcquisitionDemand getAcquisitionDemand() {
		return acquisitionDemand;
	}

	public void setAcquisitionDemand(AcquisitionDemand acquisitionDemand) {
		this.acquisitionDemand = acquisitionDemand;
	}

	public Integer getAcquisitionDemandId() {
		return acquisitionDemandId;
	}

	public void setAcquisitionDemandId(Integer acquisitionDemandId) {
		this.acquisitionDemandId = acquisitionDemandId;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public ProvinceCityCounty getProvince() {
		return province;
	}

	public void setProvince(ProvinceCityCounty province) {
		this.province = province;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public ProvinceCityCounty getCity() {
		return city;
	}

	public void setCity(ProvinceCityCounty city) {
		this.city = city;
	}

	public Integer getCountyId() {
		return countyId;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}

	public ProvinceCityCounty getCounty() {
		return county;
	}

	public void setCounty(ProvinceCityCounty county) {
		this.county = county;
	}

}
