package org.netsharp.organization.dic;

import java.util.Date;

import org.netsharp.core.annotations.Auto;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

@Table(name = "bd_dict")//临时方案 hw 2017-10-16
public class Dict extends Persistable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 6580689696329040721L;
	@Id
	@Auto
	@Column(name = "pkid", header = "id")
	private Integer id;
	@Column(name = "pid", header = "父子关系")
	private Integer parentId;

	@Column(header = "类型")
	private Integer type;
	@Column(header = "名称")
	private String name;
	@Column(header = "别名")
	private String alias;
	@Column(header = "编码")
	private Integer code;
	@Column(header = "sort")
	private Double sort;
	@Column(name = "is_enabled", header = "使用中")
	private Integer isEnabled;

	@Column(header = "备注")
    private String remark;
	@Column(name = "add_user_id", header = "添加人")
	private Integer creatorId = 0;

	@Column(name = "add_time", header = "创建时间")
	private Date createTime;

	@Column(name = "upd_user_id", header = "修改人")
	private Integer updatorId = 0;

	@Column(name = "upd_time", header = "修改时间")
	private Date updateTime;

	@Column(name = "creator", header = "创建人名称")
	private String creator;

	@Column(name = "updator", header = "更新人名称")
	private String updator;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Double getSort() {
		return sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public Integer getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Integer isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(Integer updatorId) {
		this.updatorId = updatorId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}
}
