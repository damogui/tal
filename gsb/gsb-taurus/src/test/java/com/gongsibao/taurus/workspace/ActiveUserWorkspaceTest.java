package com.gongsibao.taurus.workspace;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.taurus.ActiveUserView;



public class ActiveUserWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {
		entity = ActiveUserView.class;
		urlList = "/taurus/user/active/list";
		listPartName = formPartName = "用户活跃度";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_TAURUS_"
				+ ActiveUserView.class.getSimpleName();

		formOpenMode = OpenMode.WINDOW;
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("用户活跃度");
			datagrid.setShowCheckbox(false);
		}
		addColumn(datagrid, "days", "活跃天数", ControlTypes.NUMBER_BOX, 100);
		addColumn(datagrid, "count", "用户数", ControlTypes.NUMBER_BOX, 100);
		return datagrid;
	}

	@Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);

	}
}
