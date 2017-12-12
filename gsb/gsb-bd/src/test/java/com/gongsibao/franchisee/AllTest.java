package com.gongsibao.franchisee;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.franchisee.workspace.department.DepartmentFranchiseeWorkspaceTest;
import com.gongsibao.franchisee.workspace.department.DepartmentTrackWorkspaceTest;
import com.gongsibao.franchisee.workspace.department.DepartmentUnTrackWorkspaceTest;
import com.gongsibao.franchisee.workspace.my.MyFranchiseeWorkspaceTest;
import com.gongsibao.franchisee.workspace.my.UnTrackFranchiseeWorkspaceTest;
import com.gongsibao.franchisee.workspace.operation.OperationDayReportWorkspaceTest;
import com.gongsibao.franchisee.workspace.operation.OperationFranchiseeWorkspaceTest;
import com.gongsibao.franchisee.workspace.operation.OperationTrackWorkspaceTest;
import com.gongsibao.franchisee.workspace.operation.OperationUnTrackWorkspaceTest;
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
	OperationDayReportWorkspaceTest.class,
//	OperationWeekReportWorkspaceTest.class,
//	OperationMonthReportWorkspaceTest.class,
	
	NavigationTest.class
})

public class AllTest {
}
