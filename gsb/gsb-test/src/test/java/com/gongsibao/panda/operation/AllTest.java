package com.gongsibao.panda.operation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.operation.reference.FunctionModuleReferenceTest;
import com.gongsibao.panda.operation.workspace.crm.CustomerALLWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.CustomerAllocatedWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.CustomerAnomalyDetectionWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.CustomerLostOrderWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.CustomerQualityWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.CustomerTaskALLWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.CustomerTaskAllocatedWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.CustomerTaskUndistributedWorkspaceTest;
import com.gongsibao.panda.operation.workspace.crm.CustomerUndistributedWorkspaceTest;
import com.gongsibao.panda.operation.workspace.supplier.FunctionModuleWorkspaceTest;
import com.gongsibao.panda.operation.workspace.supplier.SupplierActionTest;
import com.gongsibao.panda.operation.workspace.supplier.SupplierCategoryWorkspaceTest;
import com.gongsibao.panda.operation.workspace.supplier.SupplierWorkspaceTest;
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
	
	CustomerALLWorkspaceTest.class,
	CustomerAllocatedWorkspaceTest.class,
	CustomerUndistributedWorkspaceTest.class,
	
	CustomerTaskAllocatedWorkspaceTest.class,
	CustomerTaskALLWorkspaceTest.class,
	CustomerTaskUndistributedWorkspaceTest.class,
	
	CustomerLostOrderWorkspaceTest.class,
	CustomerAnomalyDetectionWorkspaceTest.class,
	CustomerQualityWorkspaceTest.class,
	
	SupplierActionTest.class,
	NavigationTest.class
	
})

public class AllTest {

}

