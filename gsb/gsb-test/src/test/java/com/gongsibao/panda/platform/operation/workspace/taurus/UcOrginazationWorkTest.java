package com.gongsibao.panda.platform.operation.workspace.taurus;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.taurus.UcOrganizationUserView;


public class UcOrginazationWorkTest extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {
		entity = UcOrganizationUserView.class;
		urlList = "/taurus/user/userOrganization/list";
		listPartName = formPartName = "业务用户";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_TAURUS_" + UcOrganizationUserView.class.getSimpleName();
		formOpenMode = OpenMode.WINDOW;
	}
	
	@Override
	@SuppressWarnings("unused")
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setShowCheckbox(false);
//		datagrid.setToolbar("panda/datagrid/row/edit");
//		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		PDatagridColumn column = null;
		column = addColumn(datagrid, "firstLevelDepartment", "一级部门", ControlTypes.TEXT_BOX, 90, true);
		{
		}
		column = addColumn(datagrid, "twoLevelDepartment", "二级部门", ControlTypes.TEXT_BOX, 90, true);
		{
		}
		column = addColumn(datagrid, "threeLevelDepartment", "三级部门", ControlTypes.TEXT_BOX, 90, true);
		{
		}
		column = addColumn(datagrid, "name", "员工姓名", ControlTypes.TEXT_BOX, 90, true);
		{
		}
		column = addColumn(datagrid, "mobile", "手机号", ControlTypes.TEXT_BOX, 90, true);
		{
		}
		column = addColumn(datagrid, "loginCount", "总登录次数", ControlTypes.TEXT_BOX, 90, true);
		{
		}
		column = addColumn(datagrid, "operationCount", "总操作次数", ControlTypes.TEXT_BOX, 90, true);
		{
		}
		column = addColumn(datagrid, "monthLoginCount", "上月登录次数", ControlTypes.TEXT_BOX, 100, true);
		{
		}
		column = addColumn(datagrid, "monthOperationCount", "上月操作次数", ControlTypes.TEXT_BOX, 100, true);
		{
		}
		column = addColumn(datagrid, "weekLoginCount", "上周登录次数", ControlTypes.TEXT_BOX, 100, true);
		{
		}
		column = addColumn(datagrid, "weekOperationCount", "上周操作次数", ControlTypes.TEXT_BOX, 100, true);
		{
		}
		return datagrid;
	}
	
//	@Override
//	protected PQueryProject createQueryProject(ResourceNode node) {
//		PQueryProject queryProject = super.createQueryProject(node);
//		queryProject.toNew();
//		addQueryItem(queryProject, "name", "员工姓名", ControlTypes.TEXT_BOX);
//		addQueryItem(queryProject, "mobile", "手机号", ControlTypes.TEXT_BOX);
//		return queryProject;
//	}
	
	public void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
	
}
