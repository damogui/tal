package com.gongsibao.panda.auth.role;

import java.sql.Types;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IRoleGroupService;
import org.netsharp.organization.base.IRoleService;
import org.netsharp.organization.entity.Role;
import org.netsharp.organization.entity.RoleGroup;

/**
 * @author hw 预置角色
 */
public class RoleTest {

    IRoleGroupService roleGroupService = ServiceFactory.create(IRoleGroupService.class);
    IRoleService roleService = ServiceFactory.create(IRoleService.class);

    @Test
    public void run() {

        // 1.平台
        String workbench = "/panda/workbench";
        // 1.1 平台-售前
        this.create("Platform_PreSales_Service", "售前_客服", "Platform_PreSales", workbench);
        this.create("Platform_PreSales_Leader", "售前_经理", "Platform_PreSales", workbench);

        // 1.2 平台-招商
        this.create("Platform_Franchisee_Admin", "招商_部门管理员", "Platform_Franchisee", workbench);
        this.create("Platform_Franchisee_Leader", "招商_部门负责人", "Platform_Franchisee", workbench);
        this.create("Platform_Franchisee_Salesman", "招商_业务员", "Platform_Franchisee", workbench);

        // 1.3 平台-股转
        this.create("Platform_Ma_Admin", "股转_部门管理员", "Platform_Ma", workbench);
        this.create("Platform_Ma_Leader", "股转_部门负责人", "Platform_Ma", workbench);
        this.create("Platform_Ma_Salesman", "股转_业务员", "Platform_Ma", workbench);

        // 1.3 平台-财务
        this.create("Platform_Finance_Admin", "财务_总监", "Platform_Finance", workbench);
        this.create("Platform_Finance_Leader", "财务_经理", "Platform_Finance", workbench);
        this.create("Platform_Finance_Salesman", "财务_出纳", "Platform_Finance", workbench);
        this.create("Platform_Finance_STKZY", "财务_收退款专员", "Platform_Finance", workbench);

        // 1.3 平台-法务
        this.create("Platform_Law_FWZY", "法务_法务专员", "Platform_Law", workbench);

        // 1.3 平台-运营
        this.create("Platform_Operation_Admin", "运营_总监", "Platform_Operation", workbench);
        this.create("Platform_Operation_Leader", "运营_经理", "Platform_Operation", workbench);
        this.create("Platform_Operation_Legal", "运营_法务", "Platform_Operation", workbench);

        // 2.服务商
        workbench = "/panda/supplier/workbench";
        // 2.1 服务商-CRM
        this.create("Supplier_Admin", "CRM_管理员", "Supplier_CRM", workbench);
        this.create("Supplier_Leader", "CRM_部门负责人", "Supplier_CRM", workbench);
        this.create("Supplier_Salesman", "CRM_业务员", "Supplier_CRM", workbench);

        // 2.1 服务商-订单
        this.create("Supplier_Order_Admin", "订单_管理员", "Supplier_Order", workbench);
        this.create("Supplier_Order_Leader", "订单_部门负责人", "Supplier_Order", workbench);
        this.create("Supplier_Order_Salesman", "订单_业务员", "Supplier_Order", workbench);

        // 2.3 服务商-商标
        this.create("IGIRL_Admin", "Igirl_管理员", "Supplier_Trademark", workbench);
        this.create("IGIRL_Leader", "Igirl_部门负责人", "Supplier_Trademark", workbench);
        this.create("IGIRL_Salesman", "Igirl_业务员", "Supplier_Trademark", workbench);
        this.create("IGIRL_Audit", "Igirl_后期", "Supplier_Trademark", workbench);

    }

    private void create(String code, String name, String groupCode, String workbenchPtah) {

        Role entity = this.byCode(code);
        if (entity == null) {

            entity = new Role();
            entity.toNew();
        }

        RoleGroup group = this.getGroupByCode(groupCode);
        if (group == null) {

            System.err.println(groupCode + "不存在");

        }

        entity.setRoleGroupId(group.getId());
        entity.setCode(code);
        entity.setName(name);

        roleService.save(entity);
    }

    private Role byCode(String code) {

        Oql oql = new Oql();
        {
            oql.setType(Role.class);
            oql.setSelects("*");
            oql.setFilter("code=?");
            oql.getParameters().add("@code", code, Types.VARCHAR);
        }

        Role group = roleService.queryFirst(oql);
        return group;
    }

    private RoleGroup getGroupByCode(String groupCode) {

        Oql oql = new Oql();
        {
            oql.setType(RoleGroup.class);
            oql.setSelects("*");
            oql.setFilter("code=?");
            oql.getParameters().add("@code", groupCode, Types.VARCHAR);
        }

        RoleGroup group = roleGroupService.queryFirst(oql);
        return group;
    }
}
