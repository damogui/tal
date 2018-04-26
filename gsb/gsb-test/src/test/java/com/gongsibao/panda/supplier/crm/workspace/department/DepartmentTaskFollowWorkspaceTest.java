package com.gongsibao.panda.supplier.crm.workspace.department;

import org.junit.Before;
import org.netsharp.core.MtableManager;

import com.gongsibao.crm.web.DepartmentTaskFollowListPart;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.panda.platform.operation.workspace.crm.TaskFollowWorkspaceTest;

public class DepartmentTaskFollowWorkspaceTest extends TaskFollowWorkspaceTest{
	
	@Override
	@Before
	public void setup() {

		super.setup();
        entity = NCustomerTaskFoolow.class;
        listPartName = formPartName = "跟进列表";
        meta = MtableManager.getMtable(entity);
		urlList = "/crm/department/task/follow/list";
		listPartName = formPartName = "跟进列表";
		resourceNodeCode = "CRM_DEPARTMENT_TASK_Follow";
		listFilter = null;
		listPartServiceController = DepartmentTaskFollowListPart.class.getName();
	}
}
