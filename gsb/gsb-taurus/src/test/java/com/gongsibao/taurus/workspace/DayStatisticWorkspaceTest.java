package com.gongsibao.taurus.workspace;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.taurus.DayStatisticView;



public class DayStatisticWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {
		entity = DayStatisticView.class;
		urlList = "/taurus/user/dayStatistic/list";
		listPartName = formPartName = "日统计数据";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_TAURUS_"
				+ DayStatisticView.class.getSimpleName();

		formOpenMode = OpenMode.WINDOW;
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("日统计数据");
			datagrid.setShowCheckbox(false);
		}
		addColumn(datagrid, "dates", "日期", ControlTypes.DATE_BOX, 100);
		addColumn(datagrid, "userCount", "新增用户数", ControlTypes.NUMBER_BOX, 100);
		
		addColumn(datagrid, "buyUserCount", "购买用户数", ControlTypes.NUMBER_BOX, 100);
		addColumn(datagrid, "buyTimes", "购买次数", ControlTypes.NUMBER_BOX, 100);
		addColumn(datagrid, "renewalTimes", "续费次数", ControlTypes.NUMBER_BOX, 100);
		addColumn(datagrid, "totalAmount", "总金额", ControlTypes.DECIMAL_BOX, 100);
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
