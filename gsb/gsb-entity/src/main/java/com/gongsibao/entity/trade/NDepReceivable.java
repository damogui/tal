package com.gongsibao.entity.trade;

import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

/**
 * Created by win on 2018/2/26.
 */
@Table(name = "n_dep_receivable", header = "部门已收款")
public class NDepReceivable  extends Entity {
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 3765188133382227841L;
	@Column(name = "amount", header = "订单业绩分配金额")
    private  Integer amount;
    @Column(name = "department_id", header = "部门Id")
    private Integer departmentId = 0;

    @Reference(foreignKey = "departmentId", header = "部门")
    private SupplierDepartment department;

    @Column(name = "supplier_id", header = "服务商Id")
    private Integer supplierId = 0;

    @Reference(foreignKey = "supplierId", header = "服务商")
    private Supplier supplier;
    @Column(name = "order_id", header = "订单Id")
    private  Integer orderId;
    @JsonIgnore
    @Reference(foreignKey="orderId")
    private SoOrder order;

    @Column(name = "salesman_id", header = "员工Id")
    private  Integer salesmanId;

    @JsonIgnore
    @Reference(foreignKey="salesmanId")
    private Salesman salesman;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Integer salesmanId) {
        this.salesmanId = salesmanId;
    }


    public SoOrder getOrder() {
        return order;
    }

    public void setOrder(SoOrder order) {
        this.order = order;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
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
