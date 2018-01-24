package com.gongsibao.panda.operation.workspace.crm;

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

import com.gongsibao.crm.web.NCustomerUndistributedListPart;
import com.gongsibao.entity.crm.NCustomer;

public class CustomerUndistributedWorkspaceTest extends WorkspaceCreationBase {
	
	@Before
	public void setup() {

		entity = NCustomer.class;// 实体
		urlList = "/operation/customer/undistributed/list";// 列表的url
		listPartName = formPartName = "未分配客户";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_Customer_Manager_Undistributed";
		listPartImportJs = "/gsb/crm/base/js/customer-base-list.part.js|/gsb/crm/js/customer-undistributed-list.part.js";
		
		listPartJsController = NCustomerUndistributedListPart.class.getName();
		listPartServiceController = NCustomerUndistributedListPart.class.getName();
	}

	// 默认的grid信息的配置
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("未分配客户列表");
		}

		PDatagridColumn column = null;
		addColumn(datagrid, "realName", "名称", ControlTypes.TEXT_BOX, 120);
		addColumn(datagrid, "sex", "性别", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "mobile", "手机号码", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "email", "邮箱", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "qq", "QQ", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "weixin", "微信", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "province.name", "省份", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "city.name", "城市", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "county.name", "区/县", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "consultWay", "CRM咨询途径", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "important", "重要程度", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "intentionCategory", "质量分类", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "lastFollowTime", "最近跟进时间", ControlTypes.DATETIME_BOX, 20);
		addColumn(datagrid, "lastFoolowUser.name", "最后跟进人", ControlTypes.DATETIME_BOX, 100);
		addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATETIME_BOX, 20);
		addColumn(datagrid, "customerSource.name", "客户来源", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "creator", "添加人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "createTime", "添加时间", ControlTypes.DATETIME_BOX, 20);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		PQueryItem item = null;
		addQueryItem(queryProject, "realName", "名称", ControlTypes.TEXT_BOX);
		return queryProject;
	}
	
	// 默认的表单操作
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}