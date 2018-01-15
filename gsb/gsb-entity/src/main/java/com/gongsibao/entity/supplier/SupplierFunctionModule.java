package com.gongsibao.entity.supplier;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

@Table(name="sp_supplier_function_module",header="服务商开通功能模块")
public class SupplierFunctionModule extends Entity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -4635831910293623290L;
	
	@Column(name="function_module_id",header="功能主键")
    private Integer functionModuleId;
	
	@Reference(foreignKey="functionModuleId")
    private FunctionModule functionModule;

	@Column(name="supplier_id",header="服务商主键")
    private Integer supplierId;
	
	@JsonIgnore
    @Reference(foreignKey="supplierId")
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

	public Integer getFunctionModuleId() {
		return functionModuleId;
	}

	public void setFunctionModuleId(Integer functionModuleId) {
		this.functionModuleId = functionModuleId;
	}

	public FunctionModule getFunctionModule() {
		return functionModule;
	}

	public void setFunctionModule(FunctionModule functionModule) {
		this.functionModule = functionModule;
	}
	
}
