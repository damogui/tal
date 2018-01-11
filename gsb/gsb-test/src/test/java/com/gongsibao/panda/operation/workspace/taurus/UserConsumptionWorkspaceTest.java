package com.gongsibao.panda.operation.workspace.taurus;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.taurus.UserConsumptionView;
public class UserConsumptionWorkspaceTest  extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {
		entity = UserConsumptionView.class;
		urlList = "/taurus/user/userConsumption/list";
		listPartName = formPartName = "用户行为数据统计";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_TAURUS_" + UserConsumptionView.class.getSimpleName();
		formOpenMode = OpenMode.WINDOW;
//		listPartServiceController = JnzUserBalanceListPart.class.getName();
	}

	@SuppressWarnings("unused")
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		
		PDatagridColumn column = null;
		column = addColumn(datagrid, "addTime", "日期", ControlTypes.DATETIME_BOX, 300, true);
		{
		}
		column = addColumn(datagrid, "mobile", "手机号", ControlTypes.TEXT_BOX, 150, true);
		{
		}
		column = addColumn(datagrid, "consumptionBehavior", "消费行为", ControlTypes.TEXT_BOX, 70, true);
		{
		}
		column = addColumn(datagrid, "price", "金额", ControlTypes.DECIMAL_FEN_BOX, 70, true);
		{
		}
		
		return datagrid;
	}
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "addTime", "日期", ControlTypes.DATE_BOX);
		addQueryItem(queryProject, "mobile", "手机号", ControlTypes.TEXT_BOX);
		return queryProject;
	}
	public void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
