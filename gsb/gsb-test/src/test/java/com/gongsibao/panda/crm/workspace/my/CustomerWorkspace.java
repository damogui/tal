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
		urlList = "/crm/my/customer/list";
		listPartName = formPartName = "我的客户";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_MY_CUSTOMER";
		listFilter = "ownerId = '{userId}' and id in (select min(id) from n_crm_customer_task group by customer_id)";
		
		listPartImportJs = "/gsb/crm/js/crm-myCustomer-list.js";
		listPartJsController = MyCustomerListPart.class.getName();

	}
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;
		datagrid.setToolbar("panda/datagrid/row/edit");
		column = addColumn(datagrid, "customer.id", "操作", ControlTypes.OPERATION_COLUMN, 100, false);
		addColumn(datagrid, "customer.real_name", "客户名称", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "customer.isMember", "是否会员", ControlTypes.BOOLCOMBO_BOX, 100, false);
		column = addColumn(datagrid, "customer.mobile", "手机号", ControlTypes.TEXT_BOX, 100, false);{
			column.setFormatter("if(value&&value.length==11){return value.substr(0,3)+'****'+value.substr(7);}");
		}
		addColumn(datagrid, "customer.telephone", "座机", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "customer.qq", "QQ", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "customer.weixin", "微信", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "customer.important", "客户等级", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "customer.city.name", "所在地区", ControlTypes.TEXTAREA, 130);
		addColumn(datagrid, "customer.customerSource.name", "客户来源", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "customer.department.name", "分配部门", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "customer.creator", "客户创建人", ControlTypes.TEXT_BOX, 100, false);
		addColumn(datagrid, "customer.create_time", "客户创建时间", ControlTypes.DATE_BOX, 100, false);
		return datagrid;
	}
	
	//配置查询条件
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		PQueryItem item = null;
		item = addQueryItem(queryProject, "customer.realName", "客户", ControlTypes.TEXT_BOX);{
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
