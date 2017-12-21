package com.gongsibao.entity.report;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.bd.Dict;

@Table(name="report_product_district",header="产品按地区统计")
public class ProductDistrictStatistics  extends BasePerformanceEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -2999700794065573094L;
	
    @Reference(foreignKey="provinceId",primaryKey="pkid")
	private Dict province;

	@Column(name = "province_id", header = "省份Id")
	private Integer provinceId;
	
    @Reference(foreignKey="cityId",primaryKey="pkid")
	private Dict city;

	@Column(name = "city_id", header = "城市Id")
	private Integer cityId;
	
    @Reference(foreignKey="districtId",primaryKey="pkid")
	private Dict district;

	@Column(name = "district_id", header = "区域Id")
	private Integer districtId;

	public Dict getProvince() {
		return province;
	}

	public void setProvince(Dict province) {
		this.province = province;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Dict getCity() {
		return city;
	}

	public void setCity(Dict city) {
		this.city = city;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Dict getDistrict() {
		return district;
	}

	public void setDistrict(Dict district) {
		this.district = district;
	}

	public Integer getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
}
