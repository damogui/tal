package com.gongsibao.entity.supplier;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Role;

/**
 * Created by win on 2018/1/18.
 */
@Table(name="sys_permission_role_employee",isView = true,header="员工角色")
public class RoleSalesman extends Entity {

    @Reference(foreignKey = "roleId")
    private Role role;

    @Column(name = "role_id", header = "角色")
    private Integer roleId;

    @JsonIgnore
    @Reference(foreignKey="employeeId")
    private Salesman salesman;

    @Column(name="employee_id",header="员工Id")
    private Integer employeeId;


    public Integer getSalesmanId() {
        return employeeId;
    }

    public void setSalesmanId(Integer salesmanId) {
        this.employeeId = salesmanId;
    }

    public Salesman getSalesman() {
        return salesman;
    }

    public void setSalesman(Salesman salesman) {
        this.salesman = salesman;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
