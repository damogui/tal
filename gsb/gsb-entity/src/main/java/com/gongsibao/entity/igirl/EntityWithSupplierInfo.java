package com.gongsibao.entity.igirl;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.supplier.Supplier;

public class EntityWithSupplierInfo extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5199009628880722071L;
	@Column(name = "supplier_id", header = "服务商Id")
	private Integer supplierId = -1;
	@JsonIgnore
	@Reference(foreignKey = "supplierId", header = "服务商")
	private Supplier supplier;
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	

}
