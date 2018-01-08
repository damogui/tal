package com.gongsibao.panda.crm.workspace;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.crm.ServiceFile;

public class ServiceFileWorkspaceTest extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {
		entity = ServiceFile.class;
		urlList = "/crm/service/file/list";
		listPartName = formPartName = "服务商档案";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "Service_File_"+ServiceFile.class.getSimpleName();
	}
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		//datagrid.setToolbar("panda/datagrid/row/edit");
		PDatagridColumn column = null;
		addColumn(datagrid, "serviceName", "服务商名称", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "address", "地址", ControlTypes.TEXT_BOX, 100, true);
		column = addColumn(datagrid, "mobilePhone", "账号", ControlTypes.TEXT_BOX, 100);{			
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "isProprietary", "是否自营", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "customerPoolNumber", "客户池数量", ControlTypes.NUMBER_BOX, 80);		
		addColumn(datagrid, "isPushReport", "是否推送报表", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "messageNotifiedType", "消息通知类型 ", ControlTypes.NUMBER_BOX, 80);
		addColumn(datagrid, "isAutoAssign", "是否推自动分配", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "isAutoRelease", "是否推自动释放", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "noFollowDays", "未跟进天数释放 ", ControlTypes.NUMBER_BOX, 80);
		
		column = addColumn(datagrid, "isEnableDepart", "是否启用部门", ControlTypes.BOOLCOMBO_BOX, 80);
		{
			column.setStyler("return row.enabled==false?'color:red;':'color:#5FB878;';");
			column.setFormatter(" return value==false?'启用':'不启用';");
		}
		addColumn(datagrid, "departLevel", "部门级次", ControlTypes.NUMBER_BOX, 80);
		return datagrid;
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "serviceName", "服务商名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "mobilePhone", "账号", ControlTypes.TEXT_BOX);
		return queryProject;
	}
	
	@Test
	public void operation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
		service.addOperation(node, OperationTypes.add);
		service.addOperation(node, OperationTypes.update);
	}
}
