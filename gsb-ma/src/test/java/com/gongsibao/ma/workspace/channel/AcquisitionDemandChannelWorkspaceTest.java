package com.gongsibao.ma.workspace.channel;

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

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.ma.web.AcquisitionDemandFormPart;
import com.gongsibao.ma.workspace.acquisition.AcquisitionDemandWorkspaceTest;

public class AcquisitionDemandChannelWorkspaceTest extends AcquisitionDemandWorkspaceTest {

	@Override
	@Before
	public void setup() {

		urlList = "/ma/acquisition/channel/list";
		urlForm = "/ma/acquisition/channel/form";
		entity = AcquisitionDemand.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "收购需求";
		listFilter = "soldOutState=1";
		resourceNodeCode = "Channel" + AcquisitionDemand.class.getSimpleName();
		formServiceController = AcquisitionDemandFormPart.class.getName();
		formJsController = AcquisitionDemandFormPart.class.getName();
		formJsImport = "/gsb/ma/js/acquisitionDemand.form.part.js";

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
		column = addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);

		addColumn(datagrid, "code", "编号", ControlTypes.TEXT_BOX, 100, true);
//		addColumn(datagrid, "name", "收购人", ControlTypes.TEXT_BOX, 80);
//		addColumn(datagrid, "mobile", "收购电话", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "createTime", "登记时间", ControlTypes.TEXTAREA, 130);
		{
			column.setOrderbyMode(OrderbyMode.DESC);
		}

		addColumn(datagrid, "creator", "登记人员", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "expectedPrice", "预期价格", ControlTypes.DECIMAL_BOX, 100);
		addColumn(datagrid, "valuationPrice", "指导价格", ControlTypes.DECIMAL_BOX, 100);
		addColumn(datagrid, "hasEntrust", "委托协议", ControlTypes.BOOLCOMBO_BOX, 80);
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

		String groupName = "意向信息";

		addFormField(form, "companyType", "公司类型", groupName, ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "companyNature", "公司性质", groupName, ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "turnoverGrade", "流水", groupName, ControlTypes.ENUM_BOX, false, false);
		formField = addFormField(form, "industryFeatureDetails", "意向行业特点", groupName, ControlTypes.CHECK_BOX_GROUP, false, false);
		{

			formField.setConvertor("industryFeature");
			formField.setDataOptions("rowCount:6,itemMinWidth:90");
			formField.setFullColumn(true);
		}

		addFormField(form, "registDateBegin", "成立年限(开始)", groupName, ControlTypes.DATE_BOX, false, false);
		addFormField(form, "registDateEnd", "成立年限(结束)", groupName, ControlTypes.DATE_BOX, false, false);

		addFormField(form, "taxMode", "纳税人", groupName, ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "profitType", "企业盈利", groupName, ControlTypes.ENUM_BOX, false, false);
		formField = addFormField(form, "qualificationDetails", "企业资质", groupName, ControlTypes.CHECK_BOX_GROUP, false, false);
		{

			formField.setConvertor("enterpriseQualification");
			formField.setDataOptions("rowCount:3,itemMinWidth:200");
			formField.setFullColumn(true);
		}

		formField = addFormField(form, "hasBankAccount", "银行账户", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		{
			formField.setDataOptions("有|无");
		}
		formField = addFormField(form, "taxStatus", "报税", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		{
			formField.setWidth(80);
			formField.setDataOptions("正常|非正常");
		}
		formField = addFormField(form, "taxRegister", "国地税报到", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		{
			formField.setDataOptions("有|无");
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
		addFormField(form, "expectedPrice", "期望价格(元)", groupName, ControlTypes.CURRENCY_BOX, false, false);
		addFormField(form, "valuationPrice", "评估价格(元)", groupName, ControlTypes.CURRENCY_BOX, false, false);
		addFormField(form, "hasEntrust", "委托出售协议", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		formField = addFormField(form, "hasDeposit", "出售订金", groupName, ControlTypes.SWITCH_BUTTON, false, false);
		{
			formField.setTroikaTrigger("controlleracquisitionDemand.hasDepositChange(checked);");
		}
		addFormField(form, "depositAmount", "订金金额(元)", groupName, ControlTypes.CURRENCY_BOX, true, true);
		return form;
	}

	protected void addDetailGridPart(PWorkspace workspace) {

	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = new PQueryProject();
		queryProject.setResourceNode(node);
		queryProject.setColumnCount(3);
		queryProject.setName(listPartName);
		queryProject.toNew();
		addQueryItem(queryProject, "industryFeatureDetails.industryFeature", "行业特点", ControlTypes.ENUM_BOX);
		return queryProject;
	}

	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
