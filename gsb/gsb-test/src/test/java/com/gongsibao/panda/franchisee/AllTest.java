package com.gongsibao.panda.franchisee;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.franchisee.workspace.department.DepartmentFranchiseeWorkspaceTest;
import com.gongsibao.panda.franchisee.workspace.department.DepartmentTrackWorkspaceTest;
import com.gongsibao.panda.franchisee.workspace.department.DepartmentUnTrackWorkspaceTest;
import com.gongsibao.panda.franchisee.workspace.my.MyFranchiseeWorkspaceTest;
import com.gongsibao.panda.franchisee.workspace.my.UnTrackFranchiseeWorkspaceTest;
import com.gongsibao.panda.franchisee.workspace.operation.OperationFranchiseeWorkspaceTest;
import com.gongsibao.panda.franchisee.workspace.operation.OperationTrackWorkspaceTest;
import com.gongsibao.panda.franchisee.workspace.operation.OperationUnTrackWorkspaceTest;


@RunWith(Suite.class)
@SuiteClasses({
	ResourceTest.class, 
	
	UnTrackFranchiseeWorkspaceTest.class,
	MyFranchiseeWorkspaceTest.class,
//	WorkbenchWorkspaceTest.class,
	
	DepartmentFranchiseeWorkspaceTest.class,
	DepartmentTrackWorkspaceTest.class,
	DepartmentUnTrackWorkspaceTest.class,
//	DepartmentTrackReportWorkspaceTest.class,
//	DepartmentDayReportWorkspaceTest.class,
//	DepartmentWeekReportWorkspaceTest.class,
//	DepartmentMonthReportWorkspaceTest.class,
//
//
	OperationFranchiseeWorkspaceTest.class,
	OperationTrackWorkspaceTest.class,
	OperationUnTrackWorkspaceTest.class,
//	OperationTrackReportWorkspaceTest.class,
//	OperationDayReportWorkspaceTest.class,
//	OperationWeekReportWorkspaceTest.class,
//	OperationMonthReportWorkspaceTest.class,
	
	NavigationTest.class
})

public class AllTest {
}
