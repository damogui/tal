package com.gongsibao.entity.other;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.bd.Dict;

@Table(name="sys_permission_employee_city",header="员工影响城市")
public class EmployeeExtendCity extends Entity{


	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1372209588548717506L;

    @JsonIgnore
    @Reference(foreignKey="employeeId")
    private Employee employee;
    
    @Column(name="employee_id",header="员工Id")
    private Integer employeeId;
    
	@Column(name="f_province_id")
	private Integer fProvinceId;
	
	@Reference(foreignKey="fProvinceId",header="省份")
	private Dict fProvince;
	
	@Column(name="f_city_id")
	private Integer fCityId;
	
	@Reference(foreignKey="fCityId",header="城市")
	private Dict fCity;
	
	@Column(name="f_county_id")
	private Integer fCountyId;
	
	@Reference(foreignKey="fCountyId",header="区/县")
	private Dict fCounty;

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getfProvinceId() {
		return fProvinceId;
	}

	public void setfProvinceId(Integer fProvinceId) {
		this.fProvinceId = fProvinceId;
	}

	public Dict getfProvince() {
		return fProvince;
	}

	public void setfProvince(Dict fProvince) {
		this.fProvince = fProvince;
	}

	public Integer getfCityId() {
		return fCityId;
	}

	public void setfCityId(Integer fCityId) {
		this.fCityId = fCityId;
	}

	public Dict getfCity() {
		return fCity;
	}

	public void setfCity(Dict fCity) {
		this.fCity = fCity;
	}

	public Integer getfCountyId() {
		return fCountyId;
	}

	public void setfCountyId(Integer fCountyId) {
		this.fCountyId = fCountyId;
	}

	public Dict getfCounty() {
		return fCounty;
	}

	public void setfCounty(Dict fCounty) {
		this.fCounty = fCounty;
	}
}
