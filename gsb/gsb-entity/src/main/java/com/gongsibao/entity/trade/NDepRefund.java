package com.gongsibao.entity.trade;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;

/**
 * Created by win on 2018/2/26.
 */
@Table(name = "n_dep_refund", header = "部门退款")
public class NDepRefund  extends Entity{
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -6906396799119828651L;
	
    
    @Column(name = "order_id", header = "订单Id")
    private Integer orderId;
    
    @JsonIgnore
    @Reference(foreignKey = "orderId")
    private SoOrder order;
    
	@Column(name = "amount", header = "回款额")
    private  Integer amount;
	
    @Column(name = "refund_id", header = "退款Id")
    private  Integer refund_id;

    @Column(name = "supplier_id", header = "服务商Id")
    private Integer supplierId = 0;

    @Reference(foreignKey = "supplierId", header = "服务商")
    private Supplier supplier;
    
    @Column(name = "department_id", header = "部门Id")
    private Integer departmentId = 0;

    @Reference(foreignKey = "departmentId", header = "部门")
    private SupplierDepartment department;

    @Column(name = "salesman_id", header = "业务员Id")
    private Integer salesmanId;

    @JsonIgnore
    @Reference(foreignKey = "salesmanId")
    private Employee salesman;


    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public Integer getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(Integer refund_id) {
        this.refund_id = refund_id;
    }

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

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public SupplierDepartment getDepartment() {
		return department;
	}

	public void setDepartment(SupplierDepartment department) {
		this.department = department;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public SoOrder getOrder() {
		return order;
	}

	public void setOrder(SoOrder order) {
		this.order = order;
	}

	public Integer getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(Integer salesmanId) {
		this.salesmanId = salesmanId;
	}

	public Employee getSalesman() {
		return salesman;
	}

	public void setSalesman(Employee salesman) {
		this.salesman = salesman;
	}

	public Integer getOrderId() {
		return orderId;
	}
}
