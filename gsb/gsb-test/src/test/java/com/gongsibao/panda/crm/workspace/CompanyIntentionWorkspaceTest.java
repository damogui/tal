package com.gongsibao.panda.crm.workspace;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.controls.DictComboBox;
import com.gongsibao.entity.crm.CompanyIntention;

public class CompanyIntentionWorkspaceTest extends WorkspaceCreationBase{

	@Before
	public void setup() {

		entity = CompanyIntention.class;
		urlList = "/crm/company/list";
		urlForm = "/crm/company/form";
		listPartName = formPartName = "企业信息";		
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_" + CompanyIntention.class.getSimpleName();
		formOpenMode = OpenMode.WINDOW;
		openWindowWidth = 1000;
		openWindowHeight = 600;
		
		formJsImport = "/gsb/panda-extend/gsb.customer.controls.js";
	}

	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setName("企业信息列表");
		}

		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100);
		addColumn(datagrid, "orgType", "组织形式", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "companyName", "企业名称", ControlTypes.TEXT_BOX, 250);
		addColumn(datagrid, "isFamous", "知名企业", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "isGroup", "集团企业", ControlTypes.BOOLCOMBO_BOX, 80);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATETIME_BOX, 130);
		return datagrid;
	}

	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(3);
		
		PFormField formField = null;
		String groupName = "基本信息";
		addFormField(form, "companyName", "企业名称", groupName, ControlTypes.TEXT_BOX, true);
		addFormField(form, "orgType", "企业类型", groupName, ControlTypes.ENUM_BOX, false);
		addFormField(form, "fullName", "全称", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "code", "统一信用代码", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "legalPerson", "法人", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "setUpDate", "成立日期", groupName, ControlTypes.DATE_BOX, false);
		
		addFormField(form, "paidYears", "实缴年限", groupName, ControlTypes.NUMBER_BOX, false);
		addFormField(form, "operatingLife", "经营年限", groupName, ControlTypes.NUMBER_BOX, false);
		addFormField(form, "telephone", "固定电话", groupName, ControlTypes.TEXT_BOX, false);
	    
		addFormField(form, "address", "注册地址", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "registerCapitalType", "注册资本类型", groupName, ControlTypes.ENUM_BOX, false);
		addFormField(form, "capitalType", "资金类型", groupName, ControlTypes.ENUM_BOX, false);
		
		addFormField(form, "taxpayer", "纳税人性质", groupName, ControlTypes.ENUM_BOX, false);
		
		groupName = "描述信息";
		addFormField(form, "isFamous", "知名企业", groupName, ControlTypes.SWITCH_BUTTON, false);
		addFormField(form, "isGroup", "集团企业", groupName, ControlTypes.SWITCH_BUTTON, false);
		addFormField(form, "createTime", "添加时间", groupName, ControlTypes.DATETIME_BOX, false,true);
		addFormField(form, "remark", "备注", groupName, ControlTypes.TEXTAREA, false);

		groupName = "核名信息";
		addFormField(form, "name", "字号", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "optionalName", "备选字号", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "companyType", "名称结构", groupName, ControlTypes.ENUM_BOX, false);
		
		formField = addFormField(form, "businessType.name", "经营类型", groupName, ControlTypes.CUSTOM, false, false);
		{
			formField.setCustomControlType(DictComboBox.class.getName());
			formField.setRefFilter("type=431");
		}
		
		addFormField(form, "ownedBusinessType", "经营范围", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "businessScopeSupply", "经营范围补充", groupName, ControlTypes.TEXT_BOX, false);
		

		//扩展信息
		groupName = "扩展信息";
		addFormField(form, "street", "注册地所属街道办事处", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "policeStation", "注册地所属派出所", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "area", "所在地区", groupName, ControlTypes.TEXT_BOX, false);
		
		//实际经营地址
		addFormField(form, "houseOwner", "房屋产权人", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "houseSpace", "房屋面积", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "hasDirectorate", "设立董事会", groupName, ControlTypes.SWITCH_BUTTON, false);

		groupName = "财税信息";
		addFormField(form, "certificateTermValidity", "一证通有效期", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "nationalTaxOffice", "所属国税所", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "nationalTaxCollector", "国税专管员", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "nationalTaxCollectorMobile", "国税专管员电话", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "localTaxOffice", "所属地税所", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "localTaxCollector", "地税专管员", groupName, ControlTypes.TEXT_BOX, false);
		addFormField(form, "localTaxCollectorMobile", "地税专管员电话", groupName, ControlTypes.TEXT_BOX, false);

		groupName = "企业名片";
		
		//参与人信息
		
		//下单记录
		
		//联系人
		
		return form;
	}
	

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "companyName", "企业名称", ControlTypes.TEXT_BOX);

		return queryProject;
	}
	
	@Override
	protected void doOperation() {
		
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
	}
}
