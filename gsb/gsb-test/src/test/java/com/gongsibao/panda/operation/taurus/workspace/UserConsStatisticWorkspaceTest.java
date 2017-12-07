package com.gongsibao.panda.operation.taurus.workspace;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.taurus.UserConsStatisticView;

public class UserConsStatisticWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {
		entity = UserConsStatisticView.class;
		urlList = "/taurus/user/consStatistic/list";
		listPartName = formPartName = "消费统计";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_TAURUS_"
				+ UserConsStatisticView.class.getSimpleName();

		formOpenMode = OpenMode.WINDOW;
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("消费统计");
			datagrid.setShowCheckbox(false);
		}
		addColumn(datagrid, "buyUserCount", "购买用户数", ControlTypes.NUMBER_BOX, 100);
		addColumn(datagrid, "buyTimes", "购买次数", ControlTypes.NUMBER_BOX, 100);
		addColumn(datagrid, "dates", "日期", ControlTypes.TEXT_BOX, 100);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "dates", "日期", ControlTypes.DATE_BOX);
		return queryProject;
	}

	@Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);

	}
}
