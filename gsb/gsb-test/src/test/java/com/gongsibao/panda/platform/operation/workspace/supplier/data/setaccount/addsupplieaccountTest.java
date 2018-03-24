package com.gongsibao.panda.platform.operation.workspace.supplier.data.setaccount;

import com.gongsibao.crm.base.INCustomerTaskQualityService;
import com.gongsibao.entity.crm.dic.TaskCustomerType;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.entity.supplier.dict.SupplierStatus;
import com.gongsibao.entity.supplier.dict.SupplierType;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.supplier.base.ISupplierDepartmentService;
import com.gongsibao.supplier.base.ISupplierService;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win on 2018/3/24.
 */
/*1.预置2个服务商，开户、创建部门（2级）、业务员，法务专员帐号、财务帐号（收退款专员、发票专员）。
2.通过测试用例，自动创建。
3.每个帐号需对应上角色。
4.完成后给出一个简单文档(excel)：主要是帐号信息。*/
public class AddsupplieaccountTest {

    @Test
    public void run() {

        List<Supplier> suppliers = addSuppliers ();//添加供应商
        int num = 0;
        for (Supplier item : suppliers
                ) {

            addDepartmentforSupplier (item);

            num += 1;

        }

        String msg = String.format ("添加供应商%s条", num);
        System.out.println (msg);


    }

    /*添加部门为供应商*/
    private void addDepartmentforSupplier(Supplier item) {
        ISupplierDepartmentService departmnet = ServiceFactory.create (ISupplierDepartmentService.class);//供应商部门
        List<SupplierDepartment> listDep = new ArrayList<> ();
        SupplierDepartment dep1 = new SupplierDepartment ();
        dep1.setEntityState (EntityState.New);
        dep1.setName ("一级部门");
        dep1.setSupplierId (item.getId ());
        dep1.setCustomerType (TaskCustomerType.OLD);
        dep1.setType (SupplierType.SELFSUPPORT);

        SupplierDepartment dep2 = new SupplierDepartment ();
        dep2.setEntityState (EntityState.New);
        dep2.setName ("二级部门");
        dep2.setSupplierId (item.getId ());
        dep2.setCustomerType (TaskCustomerType.OLD);
        dep2.setType (SupplierType.SELFSUPPORT);
        dep2.setParentId (dep1.getId ());
        listDep.add (dep1);
        listDep.add (dep2);
        List<SupplierDepartment> saveDeps = departmnet.saves (listDep);
        AddSalesmanByDepartment (saveDeps);

    }
/*添加对应的用户*/
    private void AddSalesmanByDepartment(List<SupplierDepartment> saveDeps) {

        ISalesmanService salesmanService = ServiceFactory.create (ISalesmanService.class);//添加用户
    }

    /*添加供应商*/
    private List<Supplier> addSuppliers() {
        ISupplierService supplierService = ServiceFactory.create (ISupplierService.class);//供应商
        List<Supplier> suppliers = new ArrayList<> ();

        Supplier su1 = new Supplier ();
        su1.setName ("北京快无忧财务咨询有限公司");
        su1.setContact ("王桂峰");
        su1.setMobilePhone ("15010087928");//18576794946 1135
        su1.setAdminId (1124);
        su1.setProvinceId (101110000);
        su1.setCityId (101110100);
        su1.setCountyId (101110101);
        su1.setType (SupplierType.SELFSUPPORT);
        su1.setStatus (SupplierStatus.OPEND);
        su1.setEntityState (EntityState.New);
//        su1.setAdminId (1124);
//
        Supplier su2 = new Supplier ();
        su2.setName ("北京快无忧财务咨询有限公司");
        su2.setContact ("王桂峰");
        su2.setMobilePhone ("18576794946");// 1135
        su2.setAdminId (1135);
        su2.setProvinceId (101110000);
        su2.setCityId (101110100);
        su2.setCountyId (101110101);
        su2.setType (SupplierType.SELFSUPPORT);
        su2.setStatus (SupplierStatus.OPEND);
        su2.setEntityState (EntityState.New);
        List<Supplier> saveSuppliers = supplierService.saves (suppliers);

        return saveSuppliers;

    }


}
