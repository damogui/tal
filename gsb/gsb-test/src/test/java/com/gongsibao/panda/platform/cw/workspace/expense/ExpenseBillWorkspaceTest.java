package com.gongsibao.panda.platform.cw.workspace.expense;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.cw.web.CostDetailListPart;
import com.gongsibao.cw.web.ExpenseBillFormPart;
import com.gongsibao.cw.web.ExpenseBillListPart;
import com.gongsibao.cw.web.SubsidyRecordListPart;
import com.gongsibao.cw.web.TripRecordListPart;
import com.gongsibao.entity.cw.CostType;
import com.gongsibao.entity.cw.Expense;
import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.tools.PToolbarHelper;

/**
 * 
* 报销单  
* 项目名称：gsb-test   
* 类名称：ExpenseBillWorkspaceTest   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月20日 下午1:45:10   
* @version
 */
public class ExpenseBillWorkspaceTest extends WorkspaceCreationBase {
	
	private final static String costToolbarPath = "/cw/bill/loan/costToolbar";
	public static final String uploadloadToolbarPath = "/cw/bill/load/uploadToolbar";
	
	@Before
    public void setup() {
        super.setup();
        urlList = "/cw/bill/expense/list";
		urlForm = "/cw/bill/expense/form";
        entity = Expense.class;
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "GSB_CW_Manage_Expense_Bill";
        listToolbarPath = "/cw/bill/expense/toolbar";
        
        listPartImportJs = "/gsb/platform/cw/js/expense-bill-list-part.js";
		listPartJsController = ExpenseBillListPart.class.getName();
		listPartServiceController = ExpenseBillListPart.class.getName();
		listFilter = " creator_id = '{userId}'";
		
		formJsImport = "/gsb/platform/cw/js/expense-bill-form.part.js|/package/qiniu/plupload.full.min.js";
		formServiceController = ExpenseBillFormPart.class.getName();
	    formJsController = ExpenseBillFormPart.class.getName();
	    formToolbarPath = "";
    }
    
    public PToolbar createListToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listToolbarPath);
            toolbar.setName("报销单");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }

        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("createContract");
            item.setIcon(PToolbarHelper.iconAdd);
            item.setName("申请报销");
            item.setSeq(7);
            item.setCommand("{controller}.applyExpense();");
            toolbar.getItems().add(item);
        } 
        item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("createContract");
            item.setIcon(PToolbarHelper.iconEdit);
            item.setName("修改报销");
            item.setSeq(7);
            item.setCommand("{controller}.updateExpense();");
            toolbar.getItems().add(item);
        } 
        return toolbar;
    }
    @Test
    public void saveListToolbar() {
        PToolbar toolbar = createListToolbar();
        if (toolbar != null) {
            toolbarService.save(toolbar);
        }
    }
    
    @Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setName("报销单");
			datagrid.setToolbar("panda/datagrid/row/edit");
		}
		
		addColumn(datagrid, "type", "报销类型", ControlTypes.ENUM_BOX, 200);
		addColumn(datagrid, "code", "报销单号", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "totalAmount", "报销合计", ControlTypes.DECIMAL_FEN_BOX, 200);
		addColumn(datagrid, "loanAmount", "借款金额", ControlTypes.DECIMAL_FEN_BOX, 200);
		addColumn(datagrid, "amount", "报销金额", ControlTypes.DECIMAL_FEN_BOX, 200);
		addColumn(datagrid, "createTime", "创建日期", ControlTypes.DATE_BOX, 200);  
		addColumn(datagrid, "status", "审批状态", ControlTypes.ENUM_BOX, 200);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 300);
		return datagrid;
	}
    
    protected PForm createForm(ResourceNode node) {

  	  PForm form = super.createForm(node);
        PFormField formField = null;
        String groupName = "基本信息";
        formField =  addFormField(form, "type", "报销类型",groupName, ControlTypes.ENUM_BOX, true, false);
        {
        	formField.setTroikaTrigger("controllerexpense.expenseChange(this);");
        }
        addFormFieldRefrence(form, "setOfBooks.name", "付款单位",groupName,  SetOfBooks.class.getSimpleName(), true, false);
        formField =  addFormField(form, "paymentMethod", "付款方式",groupName, ControlTypes.ENUM_BOX, true, false);
        {
      	  formField.setTroikaTrigger("controllerexpense.paymentMethodChange(this);");
        }
        addFormField(form, "totalAmount", "报销合计", groupName,ControlTypes.DECIMAL_FEN_BOX, false, false);
        addFormField(form, "loanAmount", "借款金额",groupName, ControlTypes.DECIMAL_FEN_BOX, false, false);
        addFormField(form, "amount", "报销金额",groupName, ControlTypes.DECIMAL_FEN_BOX, false, false);
        addFormField(form, "totalTaxation", "税费合计",groupName, ControlTypes.DECIMAL_FEN_BOX, true, true);
        formField =   addFormField(form, "isOffset", "冲抵借款",groupName, ControlTypes.SWITCH_BUTTON, true, false);
        {
        	 formField.setTroikaTrigger("controllerexpense.correctChange(this);");
        }
        
        addFormField(form, "creator", "经办人",groupName, ControlTypes.TEXT_BOX, false, false);
        addFormFieldRefrence(form, "expenseEmployee.name", "报销人",groupName, "CRM_Employee" , true, false);
        groupName = "收款信息";
        addFormField(form, "payeeType", "收款类型",groupName, ControlTypes.ENUM_BOX, true, false);
        addFormField(form, "payeeName", "收款人", groupName,ControlTypes.TEXT_BOX, true, false);
        addFormField(form, "companyBank", "开户行",groupName, ControlTypes.TEXT_BOX, true, true);
        addFormField(form, "companyAccount", "银行账号",groupName, ControlTypes.TEXT_BOX, true, true);
        groupName = "招待信息";
        addFormField(form, "entertainDate", "招待时间",groupName, ControlTypes.DATE_BOX, true, true);
        addFormField(form, "entertainCompany", "招待公司名",groupName, ControlTypes.TEXT_BOX, true, true);
        addFormField(form, "entertainCustomer", "招待客户姓名",groupName, ControlTypes.TEXT_BOX, true, true);
        addFormField(form, "entertainPlace", "招待地点",groupName, ControlTypes.TEXT_BOX, true, true);
        groupName = "备注信息";
        addFormField(form, "memoto", "备注",groupName, ControlTypes.TEXTAREA, true, false);
   
		return form;
	}
    
    //重写父类方法
    protected void addDetailGridPart(PWorkspace workspace) {
    	createCostDetailPart(workspace);
    	createTripDetailPart(workspace);
    	createSubsidyDetailPart(workspace);
    	createUploadAttamentDetailPart(workspace);
	}
    
   //费用明细
    private void createCostDetailPart(PWorkspace workspace){
    	ResourceNode node = this.resourceService.byCode("GSB_CW_Manage_Cost_Detail");
        PDatagrid datagrid = new PDatagrid(node, "费用明细");
        {
            datagrid.setReadOnly(true);
            datagrid.setResourceNode(node);
            datagrid.setShowCheckbox(false);
            datagrid.setShowTitle(false);
            PDatagridColumn column = null;
        	addColumn(datagrid, "organization.pathName", "费用归属部门", ControlTypes.TEXT_BOX, 150);
        	addColumn(datagrid, "costType.name", "费用类型", ControlTypes.ENUM_BOX, 150);
        
            column = addColumn(datagrid, "invoiceType", "发票类型", ControlTypes.ENUM_BOX, 100);
            {
            	column.setFormatter("return controllercostDetailItem.invoiceTypeFormatter(value,row,index);");
            }
            column =  addColumn(datagrid, "taxRate", "税率", ControlTypes.ENUM_BOX, 100);
            {
            	column.setFormatter("return controllercostDetailItem.taxRateTypeFormatter(value,row,index);");
            }
            addColumn(datagrid, "detailMoney", "金额", ControlTypes.DECIMAL_FEN_BOX, 100);
            addColumn(datagrid, "detailTaxation", "税费", ControlTypes.DECIMAL_FEN_BOX, 100);
            addColumn(datagrid, "memoto", "说明", ControlTypes.TEXT_BOX, 300);
        }
        
        PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("新增费用明细");
			PFormField field = null;
			addFormFieldRefrence(form, "organization.pathName", "费用归属部门",null,"Organization-Department", true, false);
			field =  addFormFieldRefrence(form, "costType.name", "费用类型",null,  CostType.class.getSimpleName(), true, false);
	        {
	        	field.setRefFilter("form_type = 4"); //设置参照参数
	        }
			field = addFormField(form, "invoiceType", "发票类型", null, ControlTypes.ENUM_BOX, true, false);
			{
				field.setTroikaTrigger("controllercostDetailItem.invoiceTypeChange(this);");
			}
			field = addFormField(form, "detailMoney", "金额", null, ControlTypes.DECIMAL_FEN_BOX, true, false);
			{
				field.setTroikaTrigger("controllercostDetailItem.detailMoneyChange(this);");
			}
			field = addFormField(form, "taxRate", "费率", null, ControlTypes.ENUM_BOX, true, true);
			{
				field.setTroikaTrigger("controllercostDetailItem.taxRateTypeChange(this);");
			}
			addFormField(form, "detailTaxation", "税费", ControlTypes.DECIMAL_FEN_BOX, true, true);
			addFormField(form, "memoto", "说明", null, ControlTypes.TEXTAREA, true, false);
		}
		
        PPart part = new PPart();
        {
            part.toNew();
            part.setName("费用明细");
            part.setCode("costDetailItem");
            part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
            part.setRelationRole("costDetailItem");
            part.setResourceNode(node);
            part.setPartTypeId(PartType.DETAIL_PART.getId());
            part.setDatagrid(datagrid);
            part.setDockStyle(DockType.DOCUMENTHOST);
            part.setJsController(CostDetailListPart.class.getName());
            part.setToolbar(costToolbarPath);
            part.setWindowWidth(400);
			part.setWindowHeight(450);
            part.setForm(form);
        }
      
        workspace.getParts().add(part);
        PPart topPart = workspace.getParts().get(0);
        topPart.setDockStyle(DockType.TOP);
        topPart.setStyle("height:300px");
        
        
    }


    //行程明细
	private void createTripDetailPart(PWorkspace workspace){
		ResourceNode node = this.resourceService.byCode("GSB_CW_Manage_Trip_Record");
		PDatagrid datagrid = new PDatagrid(node, "行程明细");
		{
			datagrid.setReadOnly(true);
			datagrid.setResourceNode(node);
			datagrid.setShowCheckbox(false);
		
			addColumn(datagrid, "origin", "出发地", ControlTypes.TEXT_BOX, 200);
			addColumn(datagrid, "destination", "目的地", ControlTypes.TEXT_BOX, 200);
			addColumn(datagrid, "startTime", "开始时间", ControlTypes.DATE_BOX, 200);
			addColumn(datagrid, "endTime", "结束时间", ControlTypes.DATE_BOX, 200);
			addColumn(datagrid, "memoto", "说明", ControlTypes.TEXT_BOX, 300);
	
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("新增行程明细");

			addFormField(form, "origin", "出发地", null, ControlTypes.TEXT_BOX, true, false);
			addFormField(form, "destination", "目的地", null, ControlTypes.TEXT_BOX, true, false);
			addFormField(form, "startTime", "开始时间", null, ControlTypes.DATE_BOX, true, false);
			addFormField(form, "endTime", "结束时间", null, ControlTypes.DATE_BOX, true, false);
			addFormField(form, "memoto", "说明", null, ControlTypes.TEXTAREA, true, false);
		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("行程明细");
			part.setCode("tripItem");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("tripItem");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(costToolbarPath);
			part.setJsController(TripRecordListPart.class.getName());
			part.setWindowWidth(400);
			part.setWindowHeight(400);
            part.setForm(form);

		}
		workspace.getParts().add(part);
	}
	//补贴明细
	private void createSubsidyDetailPart(PWorkspace workspace){
		ResourceNode node = this.resourceService.byCode("GSB_CW_Manage_Subsidy_Record");
		PDatagrid datagrid = new PDatagrid(node, "补助明细");
		{
			datagrid.setReadOnly(true);
			datagrid.setResourceNode(node);
			datagrid.setShowCheckbox(false);
			PDatagridColumn column = null;
			column = addColumn(datagrid, "type", "补贴类型", ControlTypes.ENUM_BOX, 200);
			{
				column.setFormatter("return controllersubsidyItem.subsidyTypeFormatter(value,row,index);");
			}
			addColumn(datagrid, "countDay", "补贴天数", ControlTypes.NUMBER_BOX, 100);
			addColumn(datagrid, "standard", "补贴标准", ControlTypes.DECIMAL_FEN_BOX, 100);
			addColumn(datagrid, "subsidyAmount", "金额", ControlTypes.DECIMAL_FEN_BOX, 100);
			addColumn(datagrid, "memoto", "说明", ControlTypes.TEXT_BOX, 300);
	
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("新增补助明细");
			PFormField field = null;
			addFormField(form, "type", "补贴类型", null, ControlTypes.ENUM_BOX, true, false);
			field = addFormField(form, "countDay", "补贴天数", null, ControlTypes.NUMBER_BOX, true, false);
			{
				field.setTroikaTrigger("controllersubsidyItem.dayChange(this);");
			}
			field = addFormField(form, "standard", "补贴标准", null, ControlTypes.DECIMAL_FEN_BOX, true, false);
			{
				field.setTroikaTrigger("controllersubsidyItem.standardChange(this);");
			}
			addFormField(form, "subsidyAmount", "金额", null, ControlTypes.DECIMAL_FEN_BOX, true, false);
			addFormField(form, "memoto", "说明", null, ControlTypes.TEXTAREA, true, false);
		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("补贴明细");
			part.setCode("subsidyItem");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("subsidyItem");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(costToolbarPath);
			part.setJsController(SubsidyRecordListPart.class.getName());
			part.setWindowWidth(400);
			part.setWindowHeight(400);
            part.setForm(form);

		}
		workspace.getParts().add(part);
	}
	
    //附件
	private void createUploadAttamentDetailPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("GSB_CW_Manage_Cost_Detail");
		PDatagrid datagrid = new PDatagrid(node, "上传附件");
		{
			datagrid.setReadOnly(true);
			datagrid.setResourceNode(node);
			datagrid.setShowCheckbox(false);
			PDatagridColumn column = null;
			column = addColumn(datagrid, "url", "操作", ControlTypes.TEXT_BOX, 80);
			{
				column.setFormatter("return controllerfiles.urlFormatter(value,row,index);");
			}
			addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
	
		}
		PPart part = new PPart();
		{
			part.toNew();
			part.setName("附件");
			part.setCode("files");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("files");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar(uploadloadToolbarPath);
			part.setJsController("com.gongsibao.cw.web.AttachmentListPart");

		}
		workspace.getParts().add(part);

	}
   
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "code", "单据号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "type", "报销类型", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "status", "审批状态", ControlTypes.ENUM_BOX);
		return queryProject;
	}
    
    @Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}


}
