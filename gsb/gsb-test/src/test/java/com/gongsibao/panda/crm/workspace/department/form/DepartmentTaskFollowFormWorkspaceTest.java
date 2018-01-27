package com.gongsibao.panda.crm.workspace.department.form;

import org.junit.Before;

import com.gongsibao.panda.operation.workspace.crm.form.TaskFollowFormWorkspaceTest;

public class DepartmentTaskFollowFormWorkspaceTest extends TaskFollowFormWorkspaceTest {

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlForm = "/crm/department/task/followUp/from";
		resourceNodeCode = "CRM_DEPARTMENT_TASK_EDIT";
	}
}
