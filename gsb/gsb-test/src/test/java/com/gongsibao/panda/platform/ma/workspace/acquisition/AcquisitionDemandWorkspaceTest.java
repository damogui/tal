package com.gongsibao.panda.platform.ma.workspace.acquisition;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
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
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.utils.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.entity.ma.AcquisitionDemandMatchingDetail;
import com.gongsibao.entity.ma.AcquisitionDemandPccDetail;
import com.gongsibao.entity.ma.dic.SelingStatus;
import com.gongsibao.ma.web.AcquisitionDemandFormPart;
import com.gongsibao.ma.web.AcquisitionDemandListPart;
import com.gongsibao.ma.web.AcquisitionDemandMatchingDetailPart;
/**
 * @author hw
 * 收购需求
 */
/**   
	 * @Title: createSubdiaryieCompanyDetailPart   
	 * @Description: TODO(创建子公司明细)   
	 * @param: @param workspace      
	 * @return: void      
	 * @throws   
	 */
/**   
	 * @Title: createSubdiaryieCompanyDetailPart   
	 * @Description: TODO(创建匹配明细)   
	 * @param: @param workspace      
	 * @return: void      
	 * @throws   
	 */
/**
 * @author hw
 * 收购需求
 */
/**   
	 * @Title: createSubdiaryieCompanyDetailPart   
	 * @Description: TODO(创建子公司明细)   
	 * @param: @param workspace      
	 * @return: void      
	 * @throws   
	 */
/**   
	 * @Title: createSubdiaryieCompanyDetailPart   
	 * @Description: TODO(创建匹配明细)   
	 * @param: @param workspace      
	 * @return: void      
	 * @throws   
	 */
/**
 * @author hw
 * 收购需求
 */
public class AcquisitionDemandWorkspaceTest extends WorkspaceCreationBase{
	@Override
	@Before
	public void setup() {

		urlList = "/ma/acquisition/list";
		urlForm = "/ma/acquisition/form";
		entity = AcquisitionDemand.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName =  "收购需求";
		resourceNodeCode = AcquisitionDemand.class.getSimpleName();
		formServiceController = AcquisitionDemandFormPart.class.getName();
		formJsController = AcquisitionDemandFormPart.class.getName();
		formJsImport = "/gsb/platform/ma/js/acquisitionDemand.form.part.js";
		

		listPartImportJs = "/gsb/platform/ma/js/acquisitionDemand.list.part.js";
		listPartJsController = AcquisitionDemandListPart.class.getName();
		listPartServiceController = AcquisitionDemandListPart.class.getName();
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
		addFormField(form, "creator", "登记人员",groupName, ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "name", "收购人",groupName,ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "mobile", "收购人电话",groupName,ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "weixin", "收购人微信号",groupName,ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "qq", "收购人QQ",groupName,ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "email", "收购人邮箱",groupName,ControlTypes.TEXT_BOX, false, false);

		groupName="意向信息";
		
		addFormField(form, "companyType", "公司类型",groupName,ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "companyNature", "公司性质",groupName,ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "turnoverGrade", "流水",groupName,ControlTypes.ENUM_BOX, true, false);
		formField = addFormField(form, "registDateBegin", "成立日期(开始)",groupName,ControlTypes.DATE_BOX, true, false);{
			
			formField.setTroikaTrigger("controlleracquisitionDemand.registDateBeginChange(date);");
		}
		formField = addFormField(form, "registDateEnd", "成立日期(结束)",groupName,ControlTypes.DATE_BOX, true, false);{
			
			formField.setTroikaTrigger("controlleracquisitionDemand.registDateEndChange(date);");
		}
		addFormField(form, "registYear", "成立年限",groupName,ControlTypes.NUMBER_BOX, true, true);
		formField = addFormField(form, "industryFeatureDetails", "意向行业特点",groupName, ControlTypes.CHECK_BOX_GROUP, true, false);{
			
			formField.setConvertor("industryFeature");
			formField.setDataOptions("rowCount:6,itemMinWidth:90");
			formField.setFullColumn(true);
		}
		
		addFormField(form, "taxMode", "纳税人",groupName,ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "profitType", "企业盈利",groupName,ControlTypes.ENUM_BOX, true, false);
		formField = addFormField(form, "qualificationDetails", "企业资质",groupName, ControlTypes.CHECK_BOX_GROUP, false, false);{
			
			formField.setConvertor("enterpriseQualification");
			formField.setDataOptions("rowCount:3,itemMinWidth:200");
			formField.setFullColumn(true);
		}
		
		formField = addFormField(form, "hasBankAccount", "银行账户",groupName,ControlTypes.SWITCH_BUTTON, false, false);{
			formField.setDataOptions("有|无");
		}
		formField = addFormField(form, "taxStatus", "报税",groupName,ControlTypes.SWITCH_BUTTON, false, false);{
			formField.setWidth(80);
			formField.setDataOptions("正常|非正常");
		}
		formField = addFormField(form, "taxRegister", "国地税报到",groupName,ControlTypes.SWITCH_BUTTON, false, false);{
			formField.setDataOptions("有|无");
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
			formField.setTroikaTrigger("controlleracquisitionDemand.hasDepositChange(checked);");
		}
		addFormField(form, "depositAmount", "订金金额(元)",groupName,ControlTypes.CURRENCY_BOX, true, true);
		return form;
	}
	
	protected void addDetailGridPart(PWorkspace workspace) {

		createPcdDetailDetailPart(workspace);
		createMatchingDetailDetailPart(workspace);
	}

	
	/**   
	 * @Title: createSubdiaryieCompanyDetailPart   
	 * @Description: TODO(创建子公司明细)   
	 * @param: @param workspace      
	 * @return: void      
	 * @throws   
	 */
	private void createPcdDetailDetailPart(PWorkspace workspace){
		
		ResourceNode node = this.resourceService.byCode(AcquisitionDemandPccDetail.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "意向区域");{
			
			addColumn(datagrid, "province.name", "省份", ControlTypes.PCC_BOX, 150);
			addColumn(datagrid, "city.name", "城市", ControlTypes.PCC_BOX, 150);
			addColumn(datagrid, "county.name", "区/县", ControlTypes.PCC_BOX, 150);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("意向区域");
			
			PFormField formField = null;
			formField = addFormField(form, "province.name", "省份",ControlTypes.PCC_BOX, false, false);{
				formField.setDataOptions("level:1,changeCtrlId:'city_name'");
			}
			formField = addFormField(form, "city.name", "城市",ControlTypes.PCC_BOX, false, false);{
				formField.setDataOptions("level:2,changeCtrlId:'county_name'");
			}
			formField = addFormField(form, "county.name", "区/县",ControlTypes.PCC_BOX, false, false);{
				formField.setDataOptions("level:3");
			}
		}
		
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("意向区域");
			part.setCode("pcdDetails");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("pcdDetails");
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
	public void createToolbar() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath("ma/acquisition/refresh");
			toolbar.setName("收购需求匹配明细工具栏");
			toolbar.setResourceNode(node);
		}

		addToolbarItem(toolbar, "refresh", "刷新匹配信息", "fa-refresh", "refresh()", null, 1);
		toolbarService.save(toolbar);
	}
	
	/**   
	 * @Title: createSubdiaryieCompanyDetailPart   
	 * @Description: TODO(创建匹配明细)   
	 * @param: @param workspace      
	 * @return: void      
	 * @throws   
	 */
	private void createMatchingDetailDetailPart(PWorkspace workspace){
		
		ResourceNode node = this.resourceService.byCode(AcquisitionDemandMatchingDetail.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "匹配明细");{
			
			PDatagridColumn column = null;
			addColumn(datagrid, "companyName", "公司名称", ControlTypes.TEXT_BOX, 300);
			column = addColumn(datagrid, "matchingRate", "匹配度", ControlTypes.DECIMAL_BOX, 80);{
				
				column.setFormatter("return value+'%';");
			}
			column = addColumn(datagrid, "selingStatus", "状态", ControlTypes.ENUM_BOX, 80);{
				
				String formatter = EnumUtil.getColumnFormatter(SelingStatus.class);
				column.setFormatter(formatter);
			}
			
			column = addColumn(datagrid, "id", "操作", ControlTypes.ENUM_BOX, 60);{
				
				String formatter = "return controllermatchingDetails.openComparisonPage(value,row)";
				column.setFormatter(formatter);
			}
		}
		
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("匹配明细");
			part.setCode("matchingDetails");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("matchingDetails");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("ma/acquisition/refresh");
			part.setImports("/gsb/platform/ma/js/acquisition-demand-mtching-detail-part.js");
			part.setServiceController(AcquisitionDemandMatchingDetailPart.class.getName());
			part.setJsController(AcquisitionDemandMatchingDetailPart.class.getName());
		}
		workspace.getParts().add(part);
	}
	
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");
		PDatagridColumn column = null;
		column = addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100, true);
		
		addColumn(datagrid, "code", "编号", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "name", "收购人", ControlTypes.TEXT_BOX, 80);
		column = addColumn(datagrid, "mobile", "收购电话", ControlTypes.TEXT_BOX, 100);{
			column.setFormatter("if(value&&value.length==11){return value.substr(0,3)+'****'+value.substr(7);}");
		}
		column = addColumn(datagrid, "createTime", "登记时间", ControlTypes.TEXTAREA, 130);
		{
			column.setOrderbyMode(OrderbyMode.DESC);
		}
		
		column = addColumn(datagrid, "soldOutState", "上/下架状态", ControlTypes.ENUM_BOX, 100);
		{
			column.setFormatter("return controlleracquisitionDemandList.soldOutStateFormatter(value,row,index);");
		}
		addColumn(datagrid, "creator", "登记人员", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "expectedPrice", "预期价格", ControlTypes.DECIMAL_BOX, 100);
		addColumn(datagrid, "valuationPrice", "指导价格", ControlTypes.DECIMAL_BOX, 100);
		addColumn(datagrid, "hasEntrust", "委托协议", ControlTypes.BOOLCOMBO_BOX, 80);
		return datagrid;
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "收购人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "mobile", "收购电话", ControlTypes.TEXT_BOX);
		return queryProject;
	}
	
	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.update);
		operationService.addOperation(node, OperationTypes.delete);
	}
}

