package com.gongsibao.entity.bd;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="bd_cooperation_branch",header="入驻广场表")
public class CooperationBranch extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name="cooperation_company_id",header="合作公司 字典type508")
    private Integer cooperationCompanyId;
    @Column(name="branch_code",header="分支机构编码")
    private Integer branchCode;
    @Column(name="branch_name",header="分支机构名称")
    private String branchName;
    @Column(name="province_id",header="省")
    private Integer provinceId;
    @Column(name="city_id",header="市")
    private Integer cityId;
    @Column(name="area_id",header="区")
    private Integer areaId;
    @Column(header="addr")
    private String addr;
    @Column(name="is_enabled",header="1启用 0停用")
    private Integer isEnabled;
    @Column(name="sort",header="排序")
    private Integer sort;
	public Integer getCooperationCompanyId() {
		return cooperationCompanyId;
	}
	public void setCooperationCompanyId(Integer cooperationCompanyId) {
		this.cooperationCompanyId = cooperationCompanyId;
	}
	public Integer getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(Integer branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Integer getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
