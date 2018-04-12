package com.gongsibao.panda.supplier.crm.workspace.department;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

import com.gongsibao.controls.PropertyQueryDictComboBox;
import com.gongsibao.crm.web.DepartmentAllCustomerListPart;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.panda.platform.operation.workspace.crm.CustomerALLWorkspaceTest;

public class DepartmentAllCustomerWorkspaceTest extends CustomerALLWorkspaceTest{

	@Override
	@Before
	public void setup() {
		
		super.setup();
		urlList = "/crm/department/customer/list";
		listToolbarPath = "crm/department/customer/edit";
		resourceNodeCode = "CRM_DEPARTMENT_CUSTOMER_ALL";
		
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/supplier/crm/base/js/customer-base-list.part.js");
		ss.add("/gsb/supplier/crm/department/js/customer-all-list.part.js");
		ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
		listPartImportJs = StringManager.join("|", ss);

		listPartJsController = DepartmentAllCustomerListPart.class.getName();
		listPartServiceController = DepartmentAllCustomerListPart.class.getName();

		//当前登录人所在部门的子部门,需要扩展
		
	}
	

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		queryProject.toNew();
		queryProject.setResourceNode(node);
		queryProject.setColumnCount(2);
		queryProject.setName(listPartName);
		PQueryItem item = addQueryItem(queryProject, "keyword", "关键字", ControlTypes.TEXT_BOX);
		{
			item.setTooltip("输入客户ID、客户名称、联系方式");
			item.setWidth(250);
		}
		item = addQueryItem(queryProject, "customerSource.name", "首次来源", ControlTypes.CUSTOM);{
			
			item.setCustomControlType(PropertyQueryDictComboBox.class.getName());
			item.setRefFilter("type=411");
		}
		addQueryItem(queryProject, "important", "客户等级", ControlTypes.NUMBER_BOX);
//		addRefrenceQueryItem(queryProject, "supplier.name", "服务商", Supplier.class.getSimpleName());
		addQueryItem(queryProject, "createTime", "创建时间", ControlTypes.DATE_BOX);
		addQueryItem(queryProject, "creator", "创建人", ControlTypes.TEXT_BOX);
		return queryProject;
	}
}
