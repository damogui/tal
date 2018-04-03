package com.gongsibao.entity.trade;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;

import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.entity.trade.dic.AuditStatusType;

import java.util.Date;

/**
 * Created by win on 2018/2/26.
 */
@Table(name = "n_dep_receivable", header = "订单业绩")
public class NDepReceivable extends Entity {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 3765188133382227841L;
    @Column(name = "amount", header = "订单业绩分配金额")
    private Integer amount;
    @Column(name = "department_id", header = "部门Id")
    private Integer departmentId = 0;

    @Reference(foreignKey = "departmentId", header = "部门")
    private SupplierDepartment department;

    @Column(name = "supplier_id", header = "服务商Id")
    private Integer supplierId = 0;

    @Reference(foreignKey = "supplierId", header = "服务商")
    private Supplier supplier;


    @Column(name = "order_id", header = "订单Id")
    private Integer orderId;


    @JsonIgnore
    @Reference(foreignKey = "orderId", primaryKey = "pkid")
    private SoOrder soOrder;

    @Column(name = "salesman_id", header = "员工Id")
    private Integer salesmanId;

    @Reference(foreignKey = "salesmanId")
    private Employee salesman;

    @Column(name = "status", header = "审核状态")
    private AuditStatusType statusType = AuditStatusType.Dsh;

    @Column(name = "audit_time", header = "审核通过时间")
    private Date auditTime;


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

    public AuditStatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(AuditStatusType statusType) {
        this.statusType = statusType;
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

    public SoOrder getSoOrder() {
        return soOrder;
    }

    public void setSoOrder(SoOrder soOrder) {
        this.soOrder = soOrder;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
}
