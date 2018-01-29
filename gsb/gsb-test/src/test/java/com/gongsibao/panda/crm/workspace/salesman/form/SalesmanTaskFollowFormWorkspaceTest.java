package com.gongsibao.panda.crm.workspace.salesman.form;

import org.junit.Before;

import com.gongsibao.panda.operation.workspace.crm.form.TaskFollowFormWorkspaceTest;

public class SalesmanTaskFollowFormWorkspaceTest extends TaskFollowFormWorkspaceTest {

	@Override
	@Before
	public void setup() {

		super.setup();
		urlForm = "/crm/salesman/task/follow";
		resourceNodeCode = "CRM_SALESMAN_TASK_Foolow";
	}
}
