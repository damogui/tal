package com.gongsibao.panda.ma.workspace.channel;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.web.SellingDemandFormPart;
import com.gongsibao.panda.ma.workspace.selling.SellingDemandWorkspaceTest;

public class SellingDemandChannelWorkspaceTest extends SellingDemandWorkspaceTest {

	@Override
	@Before
	public void setup() {

		urlList = "/ma/selling/channel/list";
		urlForm = "/ma/selling/channel/form";
		entity = SellingDemand.class;
		meta = MtableManager.getMtable(entity);
		listPartName = formPartName = "出售需求";
		listFilter = "soldOutState=1";
		resourceNodeCode = "Channel" + SellingDemand.class.getSimpleName();
		formServiceController = SellingDemandFormPart.class.getName();
		formJsController = SellingDemandFormPart.class.getName();
		formJsImport = "/gsb/platform/ma/js/sellingDemand.form.part.js";

	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PQueryProject queryProject = this.createQueryProject(node);
		PQueryProject advancedQueryProject = this.createAdvancedQueryProject(node);
		PDatagrid datagrid = new PDatagrid(node, listPartName);
		{
			datagrid.setPageSize(10);
			datagrid.setShowCheckbox(true);
			datagrid.setSingleSelect(false);
			datagrid.setReadOnly(true);
			datagrid.setFilter(listFilter);
			datagrid.setQueryProject(queryProject);
			datagrid.setAdvancedQueryProject(advancedQueryProject);
		}

		createRowStyler(datagrid);
		datagrid.setToolbar("panda/datagrid/row/edit");
		PDatagridColumn column = null;
		column = addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 150, true);
		addColumn(datagrid, "state", "审核状态", ControlTypes.ENUM_BOX, 80, true);
		addColumn(datagrid, "code", "编号", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "companyName", "企业名称", ControlTypes.TEXT_BOX, 150, true);
		addColumn(datagrid, "province.name", "注册省份", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "city.name", "注册城市", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "county.name", "注册区/县", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "valuationPrice", "售价", ControlTypes.TEXT_BOX, 100);
//		addColumn(datagrid, "name", "出售人", ControlTypes.TEXT_BOX, 80);
//		addColumn(datagrid, "mobile", "出售电话", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "createTime", "登记时间", ControlTypes.TEXTAREA, 130);
		{
			column.setOrderbyMode(OrderbyMode.DESC);
		}

		addColumn(datagrid, "creator", "登记人员", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "selingStatus", "出售状态", ControlTypes.ENUM_BOX, 80);
		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(3);
			form.setReadOnly(true);
		}

		PFormField formField = null;

		// String groupName="基本信息";
		// addFormField(form, "code", "编号",groupName,ControlTypes.TEXT_BOX,
		// false, true);
		// addFormField(form, "createTime", "登记时间",groupName,
		// ControlTypes.TEXT_BOX, false, true);
		// addFormField(form, "creator", "  登记人员",groupName,
		// ControlTypes.TEXT_BOX, false, true);
		// addFormField(form, "companyName",
		// "企业名称",groupName,ControlTypes.TEXT_BOX, true, false);
		// addFormField(form, "name", "出售人",groupName,ControlTypes.TEXT_BOX,
		// true, false);
		// addFormField(form, "mobile", "出售人电话",groupName,ControlTypes.TEXT_BOX,
		// true, false);
		// addFormField(form, "weixin",
		// "出售人微信号",groupName,ControlTypes.TEXT_BOX, false, false);
		// addFormField(form, "qq", "出售人QQ",groupName,ControlTypes.TEXT_BOX,
		// false, false);
		// addFormField(form, "email", "出售人邮箱",groupName,ControlTypes.TEXT_BOX,
		// false, false);

		String groupName = "尽调信息";
		addFormField(form, "companyType", "公司类型", groupName, ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "companyNature", "公司性质", groupName, ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "businessLicense", "公司证件", groupName, ControlTypes.PICTURE_FILE_BOX, false, false);
		addFormField(form, "registDate", "成立年限(开始)",groupName,ControlTypes.DATE_BOX, false, false);
		addFormField(form, "registDateEnd", "成立年限(结束)",groupName,ControlTypes.DATE_BOX, false, false);
		addFormField(form, "companyFeature", "行业特点", groupName, ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "addressMode", "地址", groupName, ControlTypes.ENUM_BOX, false, false);

		formField = addFormField(form, "province.name", "注册省份", groupName, ControlTypes.PCC_BOX, false, false);
		{
			formField.setDataOptions("level:1,changeCtrlId:'city_name'");
		}
		formField = addFormField(form, "city.name", "注册城市", groupName, ControlTypes.PCC_BOX, false, false);
		{
			formField.setDataOptions("level:2,changeCtrlId:'county_name'");
		}
		formField = addFormField(form, "county.name", "注册区/县", groupName, ControlTypes.PCC_BOX, false, false);
		{
			formField.setDataOptions("level:3");
		}
		addFormField(form, "taxMode", "纳税人", groupName, ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "shareholderCount", "股东人数", groupName, ControlTypes.NUMBER_BOX, false, false);

		formField = addFormField(form, "hasBranchCompany", "分公司", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		{
			formField.setDataOptions("有|无");
			formField.setTroikaTrigger("controllersellingDemand.hasBranchCompanyChange(checked);");
		}
		addFormField(form, "profitable", "企业盈利", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		addFormField(form, "hasYearReport", "年报", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		formField = addFormField(form, "hasSubsidiaryCompany", "子公司", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		{
			formField.setDataOptions("有|无");
			formField.setTroikaTrigger("controllersellingDemand.hasSubsidiaryCompanyChange(checked);");
		}

		formField = addFormField(form, "taxStatus", "报税", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		{
			formField.setDataOptions("正常|非正常");
			formField.setWidth(80);
		}

		formField = addFormField(form, "hasBankAccount", "银行账户", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		{
			formField.setDataOptions("有|无");
		}
		formField = addFormField(form, "taxRegister", "国地税报到", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		{
			formField.setDataOptions("有|无");
		}
		formField = addFormField(form, "qualificationDetails", "企业资质", groupName, ControlTypes.CHECK_BOX_GROUP, false, false);
		{

			formField.setConvertor("enterpriseQualification");
			formField.setDataOptions("rowCount:3,itemMinWidth:200");
			formField.setFullColumn(true);
		}
		formField = addFormField(form, "intangibleAssetss", "无形资产", groupName, ControlTypes.CHECK_BOX_GROUP, false, false);
		{

			formField.setConvertor("intangibleAssets");
			formField.setFullColumn(true);
		}
		formField = addFormField(form, "fixedAssetss", "固定资产", groupName, ControlTypes.CHECK_BOX_GROUP, false, false);
		{

			formField.setConvertor("fixedAssets");
			formField.setFullColumn(true);
		}
		formField = addFormField(form, "otherInfo", "其它", groupName, ControlTypes.TEXT_BOX, false, false);
		{
			formField.setFullColumn(true);
		}

		formField = addFormField(form, "memoto", "备注", groupName, ControlTypes.TEXTAREA, false, false);
		{
			formField.setHeight(50);
			formField.setFullColumn(true);
		}

		groupName = "交易信息";
//		addFormField(form, "expectedPrice", "期望价格(元)", groupName, ControlTypes.CURRENCY_BOX, false, false);
//		addFormField(form, "valuationPrice", "评估价格(元)", groupName, ControlTypes.CURRENCY_BOX, false, false);
		addFormField(form, "hasEntrust", "委托出售协议", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		formField = addFormField(form, "hasDeposit", "出售订金", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		{
			formField.setTroikaTrigger("controllersellingDemand.hasDepositChange(checked);");
		}
		addFormField(form, "depositAmount", "订金金额(元)", groupName, ControlTypes.CURRENCY_BOX, true, true);
		addFormField(form, "selingStatus", "状态", groupName, ControlTypes.ENUM_BOX, false, true);
		return form;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		queryProject.setResourceNode(node);
		queryProject.setColumnCount(3);
		queryProject.setName(listPartName);
		queryProject.toNew();
		addQueryItem(queryProject, "companyFeature", "行业特点", ControlTypes.ENUM_BOX);
		return queryProject;
	}

	protected void addDetailGridPart(PWorkspace workspace) {

	}

	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
