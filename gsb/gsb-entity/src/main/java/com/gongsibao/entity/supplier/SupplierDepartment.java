package com.gongsibao.entity.supplier;

import java.util.List;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Subs;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.CatEntity;

import com.gongsibao.entity.crm.dic.TaskCustomerType;
import com.gongsibao.entity.supplier.dict.SupplierType;

@Table(name="sp_department",header="服务商部门")
public class SupplierDepartment extends CatEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 6566344416046564027L;

	@Column(name = "supplier_id", header = "服务商Id")
	private Integer supplierId = 0;

	@Reference(foreignKey = "supplierId", header = "服务商")
	private Supplier supplier;
	
    @Column(name = "customer_type", header = "所属分组类别（1：新客户  2：老客户）")
    private TaskCustomerType customerType;

    @Column(name = "type", header = "类型：1自营，2平台;3不限")
    private SupplierType type = SupplierType.UNLIMITED;

    @Subs(foreignKey = "departmentId", header = "部门服务地区范围", subType = DepartmentProduct.class)
    private List<DepartmentProduct> serviceProducts;


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

    public List<DepartmentProduct> getServiceProducts() {
        return serviceProducts;
    }

    public void setServiceProducts(List<DepartmentProduct> serviceProducts) {
        this.serviceProducts = serviceProducts;
    }

    public TaskCustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(TaskCustomerType customerType) {
		this.customerType = customerType;
	}

	public SupplierType getType() {
        return type;
    }

    public void setType(SupplierType type) {
        this.type = type;
    }
}
