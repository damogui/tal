package com.gongsibao.entity.supplier;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.CatEntity;
import org.netsharp.organization.entity.Employee;

@Table(name="sp_supplier_category",header="服务商分类")
public class SupplierCategory extends CatEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 5425316668163544708L;

	@Column(name = "owner_id", header = "运营员工Id")
	private Integer ownerId = 0;

	@Reference(foreignKey = "ownerId", header = "运营员工")
	private Employee owner;

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
