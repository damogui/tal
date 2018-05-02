package com.gongsibao.entity.supplier;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;

@Table(name="sp_supplier_category_owner_map",header="服务商分组运营人员配置")
public class SupplierCategoryOwnerMap extends Entity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6285010194829918182L;

	@Column(name = "category_id", header = "分类Id")
	private Integer categoryId = 0;

	@JsonIgnore
	@Reference(foreignKey = "categoryId", header = "分类")
	private SupplierCategory category;

	@Column(name = "owner_id", header = "运营员工Id")
	private Integer ownerId = 0;

	@Reference(foreignKey = "ownerId", header = "运营员工")
	private Employee owner;
	
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public SupplierCategory getCategory() {
		return category;
	}

	public void setCategory(SupplierCategory category) {
		this.category = category;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Employee getOwner() {
		return owner;
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
	}
}
