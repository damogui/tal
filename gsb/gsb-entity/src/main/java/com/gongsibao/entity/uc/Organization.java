package com.gongsibao.entity.uc;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.organization.dic.OrganizationType;

import com.gongsibao.entity.BaseEntity;
import com.gongsibao.entity.bd.Dict;

@Table(name = "uc_organization",header="组织机构")
public class Organization extends BaseEntity {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 5600841016274378577L;

	@Column(name = "pid", header = "父序号，默认0")
	private Integer pid;
	
	@Column(name = "organization_type", header = "组织类型")
	private OrganizationType organizationType;

	@Column(name = "name", header = "注：公司名称")
	private String name;

	@Column(name = "short_name", header = "注：组织名称")
	private String shortName;

	@Column(name = "leader_id", header = "主管人")
	private Integer leaderId = 0;
	
    @Reference(foreignKey="leaderId")
    private User leader;

	@Column(name = "city_id", header = "地区序号，type=101")
	private Integer cityId = 0;
	
    @Reference(foreignKey="cityId")
    private Dict city;

	@Column(name = "sort", header = "排序")
	private Double sort = 0D;

	@Column(name = "level", header = "层级")
	private Integer level = 0;

	@Column(name = "is_leaf", header = "是否叶子节点 0否, 1是")
	private Boolean leaf = true;

	@Column(name = "is_enabled", header = "是否启用 0否, 1是")
	private Boolean enabled = true;

	@Column(name = "remark", header = "备注")
	private String remark;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public OrganizationType getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(OrganizationType organizationType) {
		this.organizationType = organizationType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(Integer leaderId) {
		this.leaderId = leaderId;
	}

	public User getLeader() {
		return leader;
	}

	public void setLeader(User leader) {
		this.leader = leader;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Dict getCity() {
		return city;
	}

	public void setCity(Dict city) {
		this.city = city;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}