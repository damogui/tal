package com.gongsibao.panda.bd.workspace.behavior;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.bd.BdUserBehaviorLog;
@SuppressWarnings("unused")
public class UserBehaviorWorkSpaceTest  extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {
		entity = BdUserBehaviorLog.class;
		urlList = "/behavior/query";
		listPartName = formPartName = "用户行为统计";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "gsb_user_behavior";
		formOpenMode = OpenMode.WINDOW;
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
//		datagrid.setTreeField("showOrganName");
		
		PDatagridColumn column = null;
		
		column = addColumn(datagrid, "ip", "ip地址", ControlTypes.TEXT_BOX, 90, true);
		{
		}
		column = addColumn(datagrid, "behaviorType", "行为类别", ControlTypes.TEXT_BOX, 90, true);
		{
		}
		column = addColumn(datagrid, "behaviorAct", "行为动作", ControlTypes.TEXT_BOX, 90, true);
		{
		}
		column = addColumn(datagrid, "behaviorData", "行为数据", ControlTypes.TEXT_BOX, 90, true);
		{
		}
		column = addColumn(datagrid, "addTime", "创建时间", ControlTypes.DATE_BOX, 90, true);
		{
		}
		column = addColumn(datagrid, "updTime", "更新时间", ControlTypes.DATE_BOX, 90, true);
		{
		}
		return datagrid;
	}
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "behaviorType", "行为类别", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "behaviorAct", "行为动作", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "behaviorData", "行为数据", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "addTime", "创建时间", ControlTypes.DATE_BOX);
		return queryProject;
	}
	public void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
