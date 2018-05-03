package com.gongsibao.panda.platform.operation.workspace.supplier.data.setaccount;

import com.gongsibao.crm.base.INCustomerTaskQualityService;
import com.gongsibao.entity.crm.dic.TaskCustomerType;
import com.gongsibao.entity.supplier.*;
import com.gongsibao.entity.supplier.dict.SupplierStatus;
import com.gongsibao.entity.supplier.dict.SupplierType;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.supplier.base.ISupplierDepartmentService;
import com.gongsibao.supplier.base.ISupplierService;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.base.IRoleService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Role;
import org.netsharp.organization.entity.RoleEmployee;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by win on 2018/3/24.
 */
/*1.预置2个服务商，开户、创建部门（创建一个部门）、业务员，法务专员帐号、财务帐号（收退款专员、发票专员）。
2.通过测试用例，自动创建。
3.每个帐号需对应上角色。
4.完成后给出一个简单文档(excel)：主要是帐号信息。*/
public class AddSupplieAccountsTest {
    IRoleService roleService = ServiceFactory.create (IRoleService.class);//根据角色

    //删除的sql
    //加条件删除  删除供应商 部门 登录人
    //DELETE  FROM  sp_supplier  WHERE  id>3
    //DELETE  FROM   sp_department  WHERE  id>55
    //DELETE  FROM  sp_salesman WHERE  id>76
    //DELETE  FROM  sys_permission_employee  WHERE  id>3625


    @Test
    public void run() {
        List<Employee> employees = addEmployees (1); //添加职能角色的先关账号(不需要服务商属性)暂时生成两套账号


        List<Supplier> suppliers = addSuppliers ();//添加供应商(存在的话就不更新，不存在插入)
        int num = 1;
        for (Supplier item : suppliers
                ) {

            addDepartmentforSupplier (item, num);

            num += 1;

        }

        String msg = String.format ("添加供应商%s条", num);
        System.out.println (msg);


    }

    /*添加职能平台账号 暂时只提供两套*/
    private List<Employee> addEmployees(Integer num) {
        List<Employee> employees = new ArrayList<> ();

        String tel = "";
        if (num == 1) {

            tel = "1351158529";


        } else {
            tel = "1451158529";

        }

        Employee employee1 = getEmployee ("Platform_PreSales_Leader", tel + "1", "售前经理");
        Employee employee2 = getEmployee ("Platform_PreSales_Service", tel + "2", "售前客服");
        Employee employee3 = getEmployee ("Platform_Finance_STKZY", tel + "3", "收退款专员");
        Employee employee4 = getEmployee ("Platform_Law_FWZY", tel + "4", "法务专员");//
        Employee employee5 = getEmployee ("Platform_Finance_HTCGZY", tel + "5", "合同采购专员");//
        Employee employee6 = getEmployee ("Platform_Finance_FPZY", tel + "6", "发票专员");

        employees.add (employee1);
        employees.add (employee2);
        employees.add (employee3);
        employees.add (employee4);
        employees.add (employee5);
        employees.add (employee6);
        IEmployeeService ems = ServiceFactory.create (IEmployeeService.class);//进行添加登录用户
        List<Employee> employeesSaves = ems.saves (employees);
        return employeesSaves;


    }

    /*进行构造Employee实体*/
    private Employee getEmployee(String roleCode, String tel, String name) {
        Employee employee = new Employee ();
        Role role1 = byCode (roleCode);
        employee.setName (name);
        employee.setLoginName (tel);
        RoleEmployee role = new RoleEmployee ();
        role.setRoleId (role1.getId ());
        role.setEmployeeId (employee.getId ());
        List<RoleEmployee> listRole = new ArrayList<> ();
        listRole.add (role);
        employee.setRoles (listRole);
        employee.toNew ();
        return employee;


    }

    /*添加部门为供应商*/
    private void addDepartmentforSupplier(Supplier item, Integer num) {
        ISupplierDepartmentService departmnet = ServiceFactory.create (ISupplierDepartmentService.class);//供应商部门

        Integer depFId = departmnet.getBegDepartmentId (item.getId ());//服务商创建的时候自动创建的部门
//        List<SupplierDepartment> listDep = new ArrayList<> ();
//        SupplierDepartment dep1 = new SupplierDepartment ();
//        dep1.setEntityState (EntityState.New);
//        dep1.setIsLeaf (false);
//        dep1.setParentId (depFId);
//        dep1.setName ("二级部门");
//        dep1.setSupplierId (item.getId ());
//        dep1.setCustomerType (TaskCustomerType.OLD);
//        dep1.setType (SupplierType.SELFSUPPORT);
//        SupplierDepartment  depSave1=departmnet.save (dep1);
        SupplierDepartment dep2 = new SupplierDepartment ();
        dep2.setEntityState (EntityState.New);
        dep2.setName ("二级部门");
        dep2.setIsLeaf (true);
        dep2.setParentId (depFId);
        dep2.setSupplierId (item.getId ());
        dep2.setCustomerType (TaskCustomerType.OLD);
        dep2.setType (SupplierType.SELFSUPPORT);
//        dep2.setParentId (depSave1.getId ());

        SupplierDepartment  depSave2=departmnet.save (dep2);

        List<SupplierDepartment> saveDeps = new ArrayList<> ();
        //saveDeps.add (depSave1);
        saveDeps.add (depSave2);
        if (saveDeps != null && saveDeps.size () > 0) {

            AddSalesmanByDepartment (saveDeps.get (saveDeps.size () - 1), num);
        }


    }

    /*添加对应的用户*/
    private void AddSalesmanByDepartment(SupplierDepartment supplierDepartment, Integer num) {

        ISalesmanService salesmanService = ServiceFactory.create (ISalesmanService.class);//添加用户

        List<Salesman> listSalesman = new ArrayList<> ();


//预置两套服务商账号
        String tel = "";
        if (num == 1) {

            tel = "1651158529";


        } else {
            tel = "1751158529";

        }

//        Salesman salesman1 = getSalesman (supplierDepartment, "Supplier_Order_Admin", tel + "1", String.format ("fs管理员%s",num));//自动初始化
        // salesman1.setIsLeader (true);
        Salesman salesman2 = getSalesman (supplierDepartment, "Supplier_Order_Leader", tel + "2", String.format ("fs部门主管%s", num));
        salesman2.setIsLeader (true);
        Salesman salesman3 = getSalesman (supplierDepartment, "Supplier_Order_Salesman", tel + "3", String.format ("fs业务员%s", num));


        // listSalesman.add (salesman1);
        listSalesman.add (salesman2);
        listSalesman.add (salesman3);
        salesmanService.saves (listSalesman);

    }

    /*构造用户*/
    private Salesman getSalesman(SupplierDepartment item, String roleCode, String loginName, String name) {

        Salesman salesman = new Salesman ();
        salesman.setSupplierId (item.getSupplierId ());
        salesman.setDepartmentId (item.getId ());
        Role role1 = byCode (roleCode);
        salesman.setName (name);
        salesman.setLoginName (loginName);
        SalesmanRole salesmanRole = new SalesmanRole ();
        salesmanRole.setRoleId (role1.getId ());
        salesmanRole.setSalesmanId (salesman.getId ());
        List<SalesmanRole> listSalesmanRole = new ArrayList<> ();
        listSalesmanRole.add (salesmanRole);
        salesman.setRoles (listSalesmanRole);
        salesman.toNew ();
        return salesman;

    }

    /*根据code获取角色*/
    private Role byCode(String code) {

        Oql oql = new Oql ();
        {
            oql.setType (Role.class);
            oql.setSelects ("*");
            oql.setFilter ("code=?");
            oql.getParameters ().add ("@code", code, Types.VARCHAR);
        }

        Role group = roleService.queryFirst (oql);
        return group;
    }

    /*添加供应商*/
    private List<Supplier> addSuppliers() {
        ISupplierService supplierService = ServiceFactory.create (ISupplierService.class);//供应商


        List<Supplier> suppliers = new ArrayList<> ();

        Supplier su1 = new Supplier ();
        su1.setName ("北京快无忧财务咨询有限公司");
        su1.setContact ("fs管理员1");
        su1.setMobilePhone ("16511585291");
//        su1.setAdminId (1124);
        su1.setProvinceId (101110000);
        su1.setCityId (101110100);
        su1.setCountyId (101110101);
        su1.setType (SupplierType.SELFSUPPORT);
        //su1.setStatus (SupplierStatus.OPEND);
        List<SupplierFunctionModule> listFunc = getInitListFuncModule (su1);
        su1.setModules (listFunc);
        su1.toNew ();

        Oql oql = new Oql (Supplier.class.getName (), "id", "mobile_phone='16511585291'", new QueryParameters ());

        List<Supplier> superQuers = supplierService.queryList (oql);

        if (superQuers.size () == 0) {

            suppliers.add (su1);//不存在才插入
        }

        Supplier su2 = new Supplier ();
        su2.setName ("北京快无忧2财务咨询有限公司");
        su2.setContact ("fs管理员2");
        su2.setMobilePhone ("17511585291");// 1135
        //su2.setAdminId (1135);
        su2.setProvinceId (101110000);
        su2.setCityId (101110100);
        su2.setCountyId (101110101);
        su2.setType (SupplierType.SELFSUPPORT);
        //su2.setStatus (SupplierStatus.OPEND);
        listFunc = getInitListFuncModule (su2);
        su2.setModules (listFunc);
        su2.toNew ();//如果存在则不操作
        oql.setFilter ("admin_id='17511585291'");
        List<Supplier> superQuers2 = supplierService.queryList (oql);

        if (superQuers2.size () == 0) {

            suppliers.add (su2);//不存在才插入
        }

        List<Supplier> saveSuppliers = supplierService.saves (suppliers);

        for (Supplier item : saveSuppliers
                ) {
            supplierService.openAccount (item.getId ());//开户自动回写adminid等操作

        }


        return saveSuppliers;

    }

    /*获取预置的服务商角色模块*/
    public List<SupplierFunctionModule> getInitListFuncModule(Supplier su1) {

        List<SupplierFunctionModule> list = new ArrayList<> ();
        SupplierFunctionModule suf1 = new SupplierFunctionModule ();
        // suf.setFunctionModuleId (1);
        suf1.setFunctionModuleId (2);
        suf1.setSupplierId (su1.getId ());
        suf1.toNew ();
        SupplierFunctionModule suf2 = new SupplierFunctionModule ();
        // suf.setFunctionModuleId (1);
        suf2.setFunctionModuleId (2);
        suf2.setSupplierId (suf2.getId ());
        suf2.toNew ();
        list.add (suf1);
        list.add (suf2);

        return list;
    }
}
