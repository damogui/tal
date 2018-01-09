package com.gongsibao.entity.bd;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.IEntity;
import org.netsharp.entity.Persistable;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.bd.dic.DictType;

@Table(name = "bd_dict")
public class Dict extends Persistable {
	private static final long serialVersionUID = -1666319042067350999L;
	
	@Id
	@Column(name="pkid",header="id")
	private Integer id;
	
    @Column(name="add_user_id",header="添加人")
    private Integer creatorId = 0;
    
	@Column(name="add_time",header="创建时间")
    private Date createTime;
    
    @Column(name="upd_user_id",header="修改人")
    private Integer updatorId = 0;
    
    @Column(name="upd_time",header="修改时间")
    private Date updateTime;
    
	@Column(name = "creator", header = "创建人名称")
	private String creator;

	@Column(name = "updator", header = "更新人名称")
	private String updator;
	
	@Column(name = "pid", header = "父子关系")
	private Integer parentId;

	@Column(header = "类型")
	private DictType type = DictType.Diqu;

	@Column(header = "名称")
	private String name;

	@Column(header = "别名")
	private String alias;

	@Column(header = "编码")
	private Integer code;

	@Column(header = "sort")
	private Double sort;

	@Column(name = "is_enabled", header = "是否启用")
	private Boolean enabled = true;

	@Column(header = "备注")
	private String remark;

	public Integer getParentId() {
		return parentId == null ? 0 : parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public DictType getType() {
		return type;
	}

	public void setType(DictType type) {
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
		return code == null ? 0 : code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Double getSort() {
		return sort == null ? 0 : sort;
	}

	public void setSort(Double sort) {
		this.sort = sort;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRemark() {
		return StringManager.isNullOrEmpty(remark) ? "" : remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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