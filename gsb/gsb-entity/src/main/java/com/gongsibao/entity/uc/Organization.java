package com.gongsibao.entity.uc;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.organization.dic.OrganizationType;

import com.gongsibao.entity.BaseCatEntity;
import com.gongsibao.entity.bd.Dict;

@Table(name = "uc_organization",header="组织机构")
public class Organization extends BaseCatEntity {
	
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 5600841016274378577L;

	
	@Column(name = "organization_type", header = "组织类型")
	private OrganizationType organizationType;

	@Column(name = "leader_id", header = "主管人")
	private Integer leaderId = 0;
	
    @Reference(foreignKey="leaderId")
    private User leader;

	@Column(name = "city_id", header = "地区序号，type=101")
	private Integer cityId = 0;
	
    @Reference(foreignKey="cityId")
    private Dict city;

	@Column(name = "is_enabled", header = "是否启用 0否, 1是")
	private Boolean enabled = true;

	@Column(name = "remark", header = "备注")
	private String remark;
	
	@Exclusive
	@JsonIgnore
	private List<Organization> items = new ArrayList<>();

	@Exclusive
	@JsonIgnore
	private Organization parent = null;

	public OrganizationType getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(OrganizationType organizationType) {
		this.organizationType = organizationType;
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

	public List<Organization> getItems() {
		return items;
	}

	public void setItems(List<Organization> items) {
		this.items = items;
	}

	public Organization getParent() {
		return parent;
	}

	public void setParent(Organization parent) {
		this.parent = parent;
	}
}