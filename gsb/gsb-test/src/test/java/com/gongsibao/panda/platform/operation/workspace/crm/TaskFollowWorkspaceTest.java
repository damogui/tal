package com.gongsibao.panda.platform.operation.workspace.crm;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskQuality;

public class TaskFollowWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {

		super.setup();
		entity = NCustomerTaskFoolow.class;
		listPartName = formPartName = "跟进列表";
		meta = MtableManager.getMtable(entity);
		urlList = "/operation/customer/task/follow/list";
		listPartName = formPartName = "跟进列表";
		resourceNodeCode = "Operation_CRM_Task_Follow";
		listFilter = null;
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PQueryProject queryProject = this.createQueryProject(node);
		PDatagrid datagrid = new PDatagrid(node, listPartName);
		{
			datagrid.setPageSize(20);
			datagrid.setShowCheckbox(true);
			datagrid.setSingleSelect(false);
			datagrid.setReadOnly(true);
			datagrid.setNowrap(false);
			datagrid.setFilter(listFilter);
			datagrid.setQueryProject(queryProject);
		}

		PDatagridColumn column = null;
		column = addColumn(datagrid, "creator", "跟进人", ControlTypes.TEXT_BOX, 100, false);
		{
			column.setAlign(DatagridAlign.CENTER);
		}

		column = addColumn(datagrid, "createTime", "跟进时间", ControlTypes.TEXT_BOX, 130, false);
		{
			column.setOrderbyMode(OrderbyMode.DESC);
		}
		addColumn(datagrid, "customerId", "客户Id", ControlTypes.TEXT_BOX, 60, false);
		addColumn(datagrid, "customer.realName", "客户名称", ControlTypes.TEXT_BOX, 100, false);
		column = addColumn(datagrid, "customer.important", "客户等级", ControlTypes.TEXT_BOX, 100, false);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "content", "跟进内容", ControlTypes.TEXT_BOX, 500, false);
		column = addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.TEXT_BOX, 130, false);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "qualityCategory", "质量分类", ControlTypes.TEXT_BOX, 100, false);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "qualityProgress", "质量进度", ControlTypes.TEXT_BOX, 100, false);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "quality.name", "质量", ControlTypes.TEXT_BOX, 100, false);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "signingAmount", "估计签单金额", ControlTypes.TEXT_BOX, 100, false);
		{
			column.setAlign(DatagridAlign.RIGHT);
		}
		column = addColumn(datagrid, "returnedAmount", "估计回款金额", ControlTypes.TEXT_BOX, 100, false);{
			
			column.setAlign(DatagridAlign.RIGHT);
		}
		addColumn(datagrid, "supplier.name", "跟进服务商", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "department.name", "跟进部门", ControlTypes.TEXT_BOX, 100, false);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		queryProject.setColumnCount(3);
		addQueryItem(queryProject, "createTime", "跟进时间", ControlTypes.DATE_BOX);
		addQueryItem(queryProject, "creator", "跟进人", ControlTypes.TEXT_BOX);
		addRefrenceQueryItem(queryProject, "quality.name", "质量分类", NCustomerTaskQuality.class.getSimpleName());
		addQueryItem(queryProject, "qualityProgress", "质量进度", ControlTypes.ENUM_BOX);
		// PQueryItem item = addQueryItem(queryProject, "unFollowDayCount",
		// "未跟进天数", ControlTypes.NUMBER_BOX);
		// {
		// item.setInterzone(true);
		// }
		return queryProject;
	}

	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
