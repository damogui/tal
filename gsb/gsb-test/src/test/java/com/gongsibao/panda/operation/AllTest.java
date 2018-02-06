package com.gongsibao.panda.operation;

import com.gongsibao.panda.operation.workspace.supplier.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.operation.reference.FunctionModuleReferenceTest;
import com.gongsibao.panda.operation.workspace.crm.CustomerALLWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.CustomerAllocatedWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.CustomerNotTaskWorksapceTest;
import com.gongsibao.panda.operation.workspace.crm.CustomerUndistributedWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.CustomerVerifyWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.TaskALLWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.TaskAllocatedWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.TaskCheckAbnormalWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.TaskDefeatedWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.TaskOpenSeaWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.TaskUndistributedWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.form.CustomerAddWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.form.CustomerEditWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.form.TaskAddWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.form.TaskEditWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.form.TaskFollowFormWorkspaceTest;
import com.gongsibao.panda.operation.workspace.reference.QualityRefeerenceTest;
import com.gongsibao.panda.operation.workspace.reference.SupplierDepartmentReferenceTest;
import com.gongsibao.panda.operation.workspace.reference.SupplierReferenceTest;
import com.gongsibao.panda.operation.workspace.reference.SupplierSalesmanReferenceTest;
import com.gongsibao.panda.operation.workspace.supplier.data.ImportTaskQualityDataTest;
import com.gongsibao.panda.operation.workspace.supplier.data.ImportTaskRoleAndFunctionTest;
import com.gongsibao.panda.operation.workspace.taurus.ActiveUserWorkspaceTest;
import com.gongsibao.panda.operation.workspace.taurus.DayStatisticWorkspaceTest;
import com.gongsibao.panda.operation.workspace.taurus.JnzUserBalanceWorkSpaceTest;
import com.gongsibao.panda.operation.workspace.taurus.NewUserPerDayWorkspaceTest;
import com.gongsibao.panda.operation.workspace.taurus.UserConsumptionWorkspaceTest;
import com.gongsibao.panda.operation.workspace.taurus.UserWorkspaceTest;
import com.gongsibao.panda.operation.workspace.wanda.WanDaProductWorkspaceTest;
import com.gongsibao.panda.operation.workspace.wanda.WanDaSoOrderWorkspaceTest;


@RunWith(Suite.class)
@SuiteClasses({

        ResourceTest.class,

        //参照
        //功能模块
        FunctionModuleReferenceTest.class,
        SupplierReferenceTest.class,
        SupplierDepartmentReferenceTest.class,
        SupplierSalesmanReferenceTest.class,
        QualityRefeerenceTest.class,

        //金牛座
        UserWorkspaceTest.class,
        //UserInfoWorkspaceTest.class,
        NewUserPerDayWorkspaceTest.class,
//	UserConsStatisticWorkspaceTest.class,
//	UserRenewalStatisticWorkspaceTest.class,
        ActiveUserWorkspaceTest.class,
        DayStatisticWorkspaceTest.class,
        JnzUserBalanceWorkSpaceTest.class,
        UserConsumptionWorkspaceTest.class,

        //万达
        WanDaProductWorkspaceTest.class,
        WanDaSoOrderWorkspaceTest.class,

        //CMS
//	ProductPackageWorkspaceTest.class,
//	ProductWorkspaceTest.class,

        //钉钉

        //供应商
//	CPAuditWorkspaceTest.class,
//	SPAuditWorkspaceTest.class,

        FunctionModuleWorkspaceTest.class,
        SupplierWorkspaceTest.class,
        SupplierCategoryWorkspaceTest.class,
        SupplierSalesmanWorkspaceTest.class,
        SupplierDepartmentWorkspaceTest.class,


        CustomerVerifyWorkspaceTest.class,
        CustomerAddWorkspaceTest.class,
        CustomerEditWorkspaceTest.class,
        TaskAddWorkspaceTest.class,
        TaskEditWorkspaceTest.class,
        TaskFollowFormWorkspaceTest.class,

        TaskOpenSeaWorkspaceTest.class,
        CustomerALLWorkspaceTest.class,
        CustomerAllocatedWorkspaceTest.class,
        CustomerUndistributedWorkspaceTest.class,
        CustomerNotTaskWorksapceTest.class,

        TaskAllocatedWorkspaceTest.class,
        TaskALLWorkspaceTest.class,
        TaskUndistributedWorkspaceTest.class,

        TaskDefeatedWorkspaceTest.class,
        TaskCheckAbnormalWorkspaceTest.class,
        QualityWorkspaceTest.class,

        ImportTaskQualityDataTest.class,
        ImportTaskRoleAndFunctionTest.class,
        SupplierActionTest.class,
        NavigationTest.class

})

public class AllTest {

}

