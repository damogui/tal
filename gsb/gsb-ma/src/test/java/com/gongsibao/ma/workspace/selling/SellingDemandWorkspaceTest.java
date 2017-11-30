package com.gongsibao.ma.workspace.selling;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.utils.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.entity.ma.SellingDemandBranchCompany;
import com.gongsibao.entity.ma.SellingDemandSubsidiaryCompany;
import com.gongsibao.entity.ma.SellingDemandTurnoverDetail;
import com.gongsibao.entity.ma.dic.TurnoverGrade;
import com.gongsibao.ma.web.SellingDemandFormPart;
import com.gongsibao.ma.web.SellingDemandListPart;

/**
 * @author hw
 * 出售需求
 */
public class SellingDemandWorkspaceTest extends WorkspaceCreationBase{
	
	@Override
	@Before
	public void setup() {

		urlList = "/ma/selling/list";
		urlForm = "/ma/selling/form";
		entity = SellingDemand.class;
		meta = MtableManager.getMtable(entity);
		listPartName =formPartName=  "出售需求";
		resourceNodeCode = SellingDemand.class.getSimpleName();
		
		formServiceController = SellingDemandFormPart.class.getName();
		formJsController = SellingDemandFormPart.class.getName();
		formJsImport = "/gsb/ma/js/sellingDemand.form.part.js";
		
		listPartImportJs = "/gsb/ma/js/sellingDemand.list.part.js";
		listPartJsController = SellingDemandListPart.class.getName();
		listPartServiceController = SellingDemandListPart.class.getName();
	}
	
	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(3);
		}

		PFormField formField = null;

		String groupName="基本信息";
		addFormField(form, "code", "编号",groupName,ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "createTime", "登记时间",groupName, ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "creator", "  登记人员",groupName, ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "companyName", "企业名称",groupName,ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "name", "出售人",groupName,ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "mobile", "出售人电话",groupName,ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "weixin", "出售人微信号",groupName,ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "qq", "出售人QQ",groupName,ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "email", "出售人邮箱",groupName,ControlTypes.TEXT_BOX, false, false);

		groupName="尽调信息";
		addFormField(form, "companyType", "公司类型",groupName,ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "companyNature", "公司性质",groupName,ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "businessLicense", "公司证件",groupName,ControlTypes.PICTURE_FILE_BOX, false, false);
		addFormField(form, "registDate", "成立年限",groupName,ControlTypes.DATE_BOX, false, false);
		addFormField(form, "companyFeature", "行业特点",groupName,ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "addressMode", "地址",groupName,ControlTypes.ENUM_BOX, false, false);
		
		formField = addFormField(form, "province.name", "注册省份",groupName,ControlTypes.PCC_BOX, false, false);{
			formField.setDataOptions("level:1,changeCtrlId:'city_name'");
		}
		formField = addFormField(form, "city.name", "注册城市",groupName,ControlTypes.PCC_BOX, false, false);{
			formField.setDataOptions("level:2,changeCtrlId:'county_name'");
		}
		formField = addFormField(form, "county.name", "注册区/县",groupName,ControlTypes.PCC_BOX, false, false);{
			formField.setDataOptions("level:3");
		}
		addFormField(form, "taxMode", "纳税人",groupName,ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "shareholderCount", "股东人数",groupName,ControlTypes.NUMBER_BOX, false, false);
		
		formField = addFormField(form, "hasBranchCompany", "分公司",groupName,ControlTypes.SWITCH_BUTTON, false, false);{
			formField.setDataOptions("有|无");
			formField.setTroikaTrigger("controllersellingDemand.hasBranchCompanyChange(checked);");
		}
		addFormField(form, "profitable", "企业盈利",groupName,ControlTypes.SWITCH_BUTTON, false, false);
		addFormField(form, "hasYearReport", "年报",groupName,ControlTypes.SWITCH_BUTTON, false, false);
		formField = addFormField(form, "hasSubsidiaryCompany", "子公司",groupName,ControlTypes.SWITCH_BUTTON, false, false);{
			formField.setDataOptions("有|无");
			formField.setTroikaTrigger("controllersellingDemand.hasSubsidiaryCompanyChange(checked);");
		}
		
		formField = addFormField(form, "taxStatus", "报税",groupName,ControlTypes.SWITCH_BUTTON, false, false);{
			formField.setDataOptions("正常|非正常");
			formField.setWidth(80);
		}
		
		formField = addFormField(form, "hasBankAccount", "银行账户",groupName,ControlTypes.SWITCH_BUTTON, false, false);{
			formField.setDataOptions("有|无");
		}
		formField = addFormField(form, "taxRegister", "国地税报到",groupName,ControlTypes.SWITCH_BUTTON, false, false);{
			formField.setDataOptions("有|无");
		}
		formField = addFormField(form, "qualificationDetails", "企业资质",groupName, ControlTypes.CHECK_BOX_GROUP, false, false);{
			
			formField.setConvertor("enterpriseQualification");
			formField.setDataOptions("rowCount:3,itemMinWidth:200");
			formField.setFullColumn(true);
		}
		formField = addFormField(form, "intangibleAssetss", "无形资产",groupName, ControlTypes.CHECK_BOX_GROUP, false, false);{
			
			formField.setConvertor("intangibleAssets");
			formField.setFullColumn(true);
		}
		formField = addFormField(form, "fixedAssetss", "固定资产",groupName, ControlTypes.CHECK_BOX_GROUP, false, false);{
			
			formField.setConvertor("fixedAssets");
			formField.setFullColumn(true);
		}
		formField = addFormField(form, "otherInfo", "其它",groupName,ControlTypes.TEXT_BOX, false, false);{
			formField.setFullColumn(true);
		}


		formField = addFormField(form, "memoto", "备注",groupName, ControlTypes.TEXTAREA, false, false);
		{
			formField.setHeight(50);
			formField.setFullColumn(true);
		}
		
		groupName="交易信息";
		addFormField(form, "expectedPrice", "期望价格(元)",groupName,ControlTypes.CURRENCY_BOX, false, false);
		addFormField(form, "valuationPrice", "评估价格(元)",groupName,ControlTypes.CURRENCY_BOX, false, false);
		addFormField(form, "hasEntrust", "委托出售协议",groupName,ControlTypes.SWITCH_BUTTON, false, false);
		formField = addFormField(form, "hasDeposit", "出售订金",groupName,ControlTypes.SWITCH_BUTTON, false, false);{
			formField.setTroikaTrigger("controllersellingDemand.hasDepositChange(checked);");
		}
		addFormField(form, "depositAmount", "订金金额(元)",groupName,ControlTypes.CURRENCY_BOX, true, true);
		addFormField(form, "selingStatus", "状态",groupName,ControlTypes.ENUM_BOX, false, true);
		return form;
	}
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");
		PDatagridColumn column = null;
		column = addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 150, true);
		addColumn(datagrid, "state", "审核状态", ControlTypes.ENUM_BOX, 80, true);
		addColumn(datagrid, "code", "编号", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "companyName", "企业名称", ControlTypes.TEXT_BOX, 150, true);
		addColumn(datagrid, "province.name", "注册省份", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "city.name", "注册城市", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "county.name", "注册区/县", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "出售人", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "mobile", "出售电话", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "soldOutState", "上/下架状态", ControlTypes.ENUM_BOX, 100);
		{
			column.setFormatter("return controllersellingDemandList.soldOutStateFormatter(value,row,index);");
		}
		column = addColumn(datagrid, "createTime", "登记时间", ControlTypes.TEXTAREA, 130);
		{
			column.setOrderbyMode(OrderbyMode.DESC);
		}

		addColumn(datagrid, "creator", "登记人员", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "selingStatus", "出售状态", ControlTypes.ENUM_BOX, 80);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "companyName", "企业名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "companyName", "注册地区", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "name", "出售人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "mobile", "出售电话", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "selingStatus", "出售状态", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "state", "审核状态", ControlTypes.ENUM_BOX);
		return queryProject;
	}
	

	protected void addDetailGridPart(PWorkspace workspace) {

		createSubdiaryieCompanyDetailPart(workspace);
		createBrancheCompanyDetailPart(workspace);
		createTurnoverDetailPart(workspace);
	}
	
	/**   
	 * @Title: createSubdiaryieCompanyDetailPart   
	 * @Description: TODO(创建子公司明细)   
	 * @param: @param workspace      
	 * @return: void      
	 * @throws   
	 */
	private void createSubdiaryieCompanyDetailPart(PWorkspace workspace){
		
		ResourceNode node = this.resourceService.byCode(SellingDemandSubsidiaryCompany.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "子公司");{
			addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 200);
		}
		
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("子公司");

			addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true, false);
			addFormField(form, "memoto", "备注", null, ControlTypes.TEXTAREA, false, false);
		}
		
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("子公司");
			part.setCode("subdiaryieCompanyDetails");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("subdiaryieCompanyDetails");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(500);
			part.setWindowHeight(350);
			part.setForm(form);
		}
		workspace.getParts().add(part);
		
	}
	
	/**   
	 * @Title: createBrancheCompanyDetailPart   
	 * @Description: TODO(创建分公司明细)   
	 * @param: @param workspace      
	 * @return: void      
	 * @throws   
	 */
	private void createBrancheCompanyDetailPart(PWorkspace workspace){
		
		ResourceNode node = this.resourceService.byCode(SellingDemandBranchCompany.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "分公司");{
			addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 200);
		}
		
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("分公司");
			addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true, false);
			addFormField(form, "memoto", "备注", null, ControlTypes.TEXTAREA, false, false);
		}
		
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("分公司");
			part.setCode("brancheCompanyDetails");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("brancheCompanyDetails");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(500);
			part.setWindowHeight(350);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}
	
	/**   
	 * @Title: createTurnoverDetailPart   
	 * @Description: TODO(创建流水明细)   
	 * @param: @param workspace      
	 * @return: void      
	 * @throws   
	 */
	private void createTurnoverDetailPart(PWorkspace workspace){
		
		ResourceNode node = this.resourceService.byCode(SellingDemandTurnoverDetail.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "流水明细");{
			addColumn(datagrid, "beginDate", "开始时间", ControlTypes.DATE_BOX, 130);
			addColumn(datagrid, "endDate", "结束时间", ControlTypes.DATE_BOX, 130);
			PDatagridColumn column = addColumn(datagrid, "turnoverGrade", "流水", ControlTypes.ENUM_BOX, 200);{
				
				String formatter = EnumUtil.getColumnFormatter(TurnoverGrade.class);
				column.setFormatter(formatter);
			}
		}
		
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("流水明细");
			addFormField(form, "beginDate", "开始时间", null, ControlTypes.DATE_BOX, true, false);
			addFormField(form, "endDate", "结束时间", null, ControlTypes.DATE_BOX, true, false);
			addFormField(form, "turnoverGrade", "流水", null, ControlTypes.ENUM_BOX, true, false);
		}
		
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("流水明细");
			part.setCode("turnoverDetails");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("turnoverDetails");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setWindowWidth(500);
			part.setWindowHeight(350);
			part.setForm(form);
		}
		workspace.getParts().add(part);
	}
	
	@Test
	public void operation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
	}
}
