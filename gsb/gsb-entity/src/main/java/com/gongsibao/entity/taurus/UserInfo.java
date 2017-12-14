package com.gongsibao.entity.taurus;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.bd.Dict;

@Table(name="jnz_user_info",header="用户信息")
public class UserInfo extends Entity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -72659637425287821L;

	@Reference(foreignKey="userId")
    private User user;
	
	@Column(name="user_id",header="用户Id")
	private Integer userId;
	
	@Column(name = "name", header = "姓名")
	private String name;
	
	@Column(name = "company_name", header = "公司")
	private String companyName;
	
	@Reference(foreignKey="provinceId")
    private Dict province;
	
	@Column(name="province_id",header="省份")
	private Integer provinceId;
	
	@Reference(foreignKey="cityId")
    private Dict city;
	
	@Column(name="city_id",header="城市")
	private Integer cityId;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

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
}
