package com.gongsibao.panda.supplier.crm.workspace.salesman;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskQuality;
import com.gongsibao.panda.platform.operation.workspace.crm.TaskFollowWorkspaceTest;

public class SalesmanTaskFollowWorkspaceTest extends TaskFollowWorkspaceTest{

	@Override
	@Before
	public void setup() {

		super.setup();
        entity = NCustomerTaskFoolow.class;
        listPartName = formPartName = "跟进列表";
        meta = MtableManager.getMtable(entity);
		urlList = "/crm/salesman/task/follow/list";
		listPartName = formPartName = "跟进列表";
		resourceNodeCode = "CRM_SALESMAN_TASK_Follow";
		listFilter = "creator_id = '{userId}'";
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		queryProject.setColumnCount(3);
		addQueryItem(queryProject, "createTime", "跟进时间", ControlTypes.DATE_BOX);
		addRefrenceQueryItem(queryProject, "quality.name", "质量分类", NCustomerTaskQuality.class.getSimpleName());
		addQueryItem(queryProject, "qualityProgress", "质量进度", ControlTypes.ENUM_BOX);
		return queryProject;
	}
}
