package com.gongsibao.panda.platform.franchisee;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gongsibao.panda.platform.franchisee.reference.BdOrganizationFilterReferenceTest;
import com.gongsibao.panda.platform.franchisee.reference.FranchiseeReferenceTest;
import com.gongsibao.panda.platform.franchisee.workspace.department.DepartmentDayReportWorkspaceTest;
import com.gongsibao.panda.platform.franchisee.workspace.department.DepartmentFranchiseeWorkspaceTest;
import com.gongsibao.panda.platform.franchisee.workspace.department.DepartmentMonthReportWorkspaceTest;
import com.gongsibao.panda.platform.franchisee.workspace.department.DepartmentNotTrackWorkspaceTest;
import com.gongsibao.panda.platform.franchisee.workspace.department.DepartmentTrackWorkspaceTest;
import com.gongsibao.panda.platform.franchisee.workspace.department.DepartmentUnTrackWorkspaceTest;
import com.gongsibao.panda.platform.franchisee.workspace.department.DepartmentYearReportWorkspaceTest;
import com.gongsibao.panda.platform.franchisee.workspace.my.MyFranchiseeWorkspaceTest;
import com.gongsibao.panda.platform.franchisee.workspace.my.NotTrackFranchiseeWorkspaceTest;
import com.gongsibao.panda.platform.franchisee.workspace.my.UnTrackFranchiseeWorkspaceTest;
import com.gongsibao.panda.platform.franchisee.workspace.operation.OperationDayReportWorkspaceTest;
import com.gongsibao.panda.platform.franchisee.workspace.operation.OperationFranchiseeWorkspaceTest;
import com.gongsibao.panda.platform.franchisee.workspace.operation.OperationMonthReportWorkspaceTest;
import com.gongsibao.panda.platform.franchisee.workspace.operation.OperationNotTrackWorkspaceTest;
import com.gongsibao.panda.platform.franchisee.workspace.operation.OperationTrackWorkspaceTest;
import com.gongsibao.panda.platform.franchisee.workspace.operation.OperationUnTrackWorkspaceTest;
import com.gongsibao.panda.platform.franchisee.workspace.operation.OperationYearReportWorkspaceTest;


@RunWith(Suite.class)
@SuiteClasses({
	ResourceTest.class, 
	
	FranchiseeReferenceTest.class,
	BdOrganizationFilterReferenceTest.class,
	
	UnTrackFranchiseeWorkspaceTest.class,
	MyFranchiseeWorkspaceTest.class,
	NotTrackFranchiseeWorkspaceTest.class,
	
	DepartmentFranchiseeWorkspaceTest.class,
	DepartmentTrackWorkspaceTest.class,
	DepartmentNotTrackWorkspaceTest.class,
	DepartmentUnTrackWorkspaceTest.class,
	DepartmentDayReportWorkspaceTest.class,
//	DepartmentWeekReportWorkspaceTest.class,
	DepartmentMonthReportWorkspaceTest.class,
	DepartmentYearReportWorkspaceTest.class,
	
	
	OperationFranchiseeWorkspaceTest.class,
	OperationTrackWorkspaceTest.class,
	OperationUnTrackWorkspaceTest.class,
	OperationNotTrackWorkspaceTest.class,
	OperationDayReportWorkspaceTest.class,
	OperationMonthReportWorkspaceTest.class,
	OperationYearReportWorkspaceTest.class,
	
	NavigationTest.class
})

public class FranchiseeAllTest {
}
