package com.gongsibao.entity.uc;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name = "uc_organization")
public class Organization extends BaseEntity {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 5600841016274378577L;

	@Column(name = "pid", header = "父序号，默认0")
	private Integer pid;

	@Column(name = "name", header = "注：公司名称")
	private String name;

	@Column(name = "short_name", header = "注：组织名称")
	private String shortName;

	@Column(name = "leader_id", header = "主管人")
	private Integer leaderId = 0;

	@Column(name = "city_id", header = "地区序号，type=101")
	private Integer cityId = 0;

	@Column(name = "sort", header = "排序")
	private Double sort = 0D;

	@Column(name = "level", header = "层级")
	private Integer level = 0;

	@Column(name = "is_leaf", header = "是否叶子节点 0否, 1是")
	private Boolean isLeaf = true;

	@Column(name = "is_enabled", header = "是否启用 0否, 1是")
	private Boolean enabled = true;

	@Column(name = "add_time", header = "创建时间")
	private Date addTime;

	@Column(name = "add_user_id", header = "创建人")
	private Integer addUserId;

	@Column(name = "remark", header = "备注")
	private String remark;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
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

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getAddUserId() {
		return addUserId;
	}

	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}