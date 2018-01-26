package com.gongsibao.panda.crm.workspace.my;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.web.MyCustomerListPart;
import com.gongsibao.entity.crm.NCustomerTask;

public class CustomerWorkspace extends WorkspaceCreationBase{
	
	@Override
	@Before
	public void setup() {
		entity = NCustomerTask.class;
		//配置资源路径
		urlList = "/crm/salesman/customer/list";
		listPartName = formPartName = "我的客户";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_SALESMAN_CUSTOMER";
		//listFilter = "ownerId = '{userId}' and id in (select min(id) from n_crm_customer_task group by customer_id)";
		
		listPartImportJs = "/gsb/crm/js/crm-myCustomer-list.js";
		listPartJsController = MyCustomerListPart.class.getName();

	}
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;
		datagrid.setToolbar("panda/datagrid/row/edit");
		column = addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, false);
		addColumn(datagrid, "realName", "客户名称", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "isMember", "是否会员", ControlTypes.BOOLCOMBO_BOX, 100, false);
		column = addColumn(datagrid, "mobile", "手机号", ControlTypes.TEXT_BOX, 100, false);{
			column.setFormatter("if(value&&value.length==11){return value.substr(0,3)+'****'+value.substr(7);}");
		}
		addColumn(datagrid, "telephone", "座机", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "qq", "QQ", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "weixin", "微信", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "important", "客户等级", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "city.name", "所在地区", ControlTypes.TEXTAREA, 130);
		//addColumn(datagrid, "customerSource.name", "客户来源", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "department.name", "分配部门", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "creator", "客户创建人", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "createTime", "客户创建时间", ControlTypes.DATE_BOX, 100, false);
		return datagrid;
	}
	
	//配置查询条件
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		PQueryItem item = null;
		item = addQueryItem(queryProject, "realName", "客户", ControlTypes.TEXT_BOX);{
			item.setRequired(true);
		}
		return queryProject;
	}
	public void doOperation() {
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}
