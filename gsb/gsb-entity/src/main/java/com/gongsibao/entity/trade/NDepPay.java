package com.gongsibao.entity.trade;

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
@Table(name = "n_dep_pay", header = "部门支付表")
public class NDepPay extends Entity {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -233203413500559037L;
    @Column(name = "amount", header = "支付额")
    private Integer amount;

    @Column(name = "supplier_id", header = "服务商Id")
    private Integer supplierId = 0;

    @Reference(foreignKey = "supplierId", header = "服务商")
    private Supplier supplier;
    @Column(name = "department_id", header = "部门Id")
    private Integer departmentId = 0;

    @Reference(foreignKey = "departmentId", header = "部门")
    private SupplierDepartment department;

    @Column(name = "employee_id", header = "员工Id")
    private Integer employeeId;

    @Reference(foreignKey = "employeeId")
    private Employee employee;


    @Column(name = "order_pay_map_id", header = "支付明细Id")
    private Integer orderPayMapId;


    /*new beg*/

    @Reference(foreignKey = "orderPayMapId", header = "支付明细")
    private OrderPayMap orderPayMap;

    /*new end*/
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getOrderPayMapId() {
        return orderPayMapId;
    }

    public void setOrderPayMapId(Integer orderPayMapId) {
        this.orderPayMapId = orderPayMapId;
    }

    public OrderPayMap getOrderPayMap() {
        return orderPayMap;
    }

    public void setOrderPayMap(OrderPayMap orderPayMap) {
        this.orderPayMap = orderPayMap;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
