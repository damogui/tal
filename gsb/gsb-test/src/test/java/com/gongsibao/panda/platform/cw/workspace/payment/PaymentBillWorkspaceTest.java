package com.gongsibao.panda.platform.cw.workspace.payment;

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
import com.gongsibao.cw.web.PaymentBillFormPart;
import com.gongsibao.cw.web.PaymentBillListPart;
import com.gongsibao.entity.cw.Payment;
import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.tools.PToolbarHelper;

/**
 * 
* 付款单
* 项目名称：gsb-test   
* 类名称：PaymentBillWorkspaceTest   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月20日 下午1:45:10   
* @version
 */
public class PaymentBillWorkspaceTest extends WorkspaceCreationBase {
	
	private final static String costToolbarPath = "/cw/bill/loan/costToolbar";
	public static final String uploadloadToolbarPath = "/cw/bill/load/uploadToolbar";
	
	@Before
    public void setup() {
        super.setup();
        urlList = "/cw/bill/payment/list";
		urlForm = "/cw/bill/payment/form";
        entity = Payment.class;
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "GSB_CW_Manage_Payment_Bill";
        listToolbarPath = "/cw/bill/Payment/toolbar";
        
        listPartImportJs = "/gsb/platform/cw/js/payment-bill-list-part.js";
		listPartJsController = PaymentBillListPart.class.getName();
		listPartServiceController = PaymentBillListPart.class.getName();
		listFilter = " creator_id = '{userId}'";
		
		formJsImport = "/gsb/platform/cw/js/payment-bill-form.part.js|/package/qiniu/plupload.full.min.js";
		formServiceController = PaymentBillFormPart.class.getName();
	    formJsController = PaymentBillFormPart.class.getName();
	    formToolbarPath = "";
    }
    
    
    @Test
	public void run() {
		createListWorkspace();
		createFormWorkspace();
	}
	
    public PToolbar createListToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listToolbarPath);
            toolbar.setName("付款单");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }

        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("createContract");
            item.setIcon(PToolbarHelper.iconAdd);
            item.setName("申请付款");
            item.setSeq(7);
            item.setCommand("{controller}.applyPayment();");
            toolbar.getItems().add(item);
        } 
        item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("createContract");
            item.setIcon(PToolbarHelper.iconEdit);
            item.setName("修改付款");
            item.setSeq(7);
            item.setCommand("{controller}.updatePayment();");
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
			datagrid.setName("付款单");
			datagrid.setToolbar("panda/datagrid/row/edit");
		}
		addColumn(datagrid, "code", "付款单号", ControlTypes.TEXT_BOX, 200, true);
		addColumn(datagrid, "businessType", "业务类型", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "amount", "付款金额", ControlTypes.DECIMAL_FEN_BOX, 200);
		addColumn(datagrid, "setOfBooks.name", "付款单位", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 200);
		addColumn(datagrid, "status", "审核状态", ControlTypes.ENUM_BOX, 200);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 400);
		return datagrid;
	}
    
    protected PForm createForm(ResourceNode node) {

  	    PForm form = super.createForm(node);
        PFormField formField = null;
        addFormField(form, "businessType", "业务类型", ControlTypes.ENUM_BOX, true, false);
        addFormFieldRefrence(form, "setOfBooks.name", "付款单位",null,  SetOfBooks.class.getSimpleName(), true, false);
        formField =  addFormField(form, "paymentMethod", "付款方式", ControlTypes.ENUM_BOX, true, false);
        {
      	  formField.setTroikaTrigger("controllerpayment.paymentMethodChange(this);");
        }
        addFormField(form, "companyName", "公司名称", ControlTypes.TEXT_BOX, true, true);
        addFormField(form, "companyBank", "公司开户行", ControlTypes.TEXT_BOX, true, true);
        formField =  addFormField(form, "companyAccount", "公司银行账号", ControlTypes.TEXT_BOX, true, true);
        
        addFormField(form, "amount", "付款金额", ControlTypes.DECIMAL_FEN_BOX, true, false);
        addFormField(form, "collectInvoiceDate", "收发票日期", ControlTypes.DATE_BOX, false, false);
        addFormField(form, "memoto", "备注", ControlTypes.TEXTAREA, true, false);
        
		  return form;
	}
    
    
    //重写父类方法
    protected void addDetailGridPart(PWorkspace workspace) {
    	createCostDateilPart(workspace);
    	createUploadAttamentDetailPart(workspace);
	}
    //费用明细
    private void createCostDateilPart(PWorkspace workspace){
    	ResourceNode node = this.resourceService.byCode("GSB_CW_Manage_Cost_Detail");
        PDatagrid datagrid = new PDatagrid(node, "费用明细");
        {
            datagrid.setReadOnly(true);
            datagrid.setResourceNode(node);
            datagrid.setShowCheckbox(false);
            datagrid.setShowTitle(false);
            
        	addColumn(datagrid, "organization.pathName", "费用归属部门", ControlTypes.TEXT_BOX, 300);
            addColumn(datagrid, "detailMoney", "金额", ControlTypes.TEXT_BOX, 200);
            addColumn(datagrid, "memoto", "说明", ControlTypes.TEXT_BOX, 300);
        }
        
        PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("新增费用明细");

			addFormFieldRefrence(form, "organization.pathName", "费用归属部门",null,"Organization-Department", true, false);
			addFormField(form, "detailMoney", "金额", null, ControlTypes.DECIMAL_FEN_BOX, true, false);
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
			 part.setWindowHeight(400);
            part.setForm(form);
        }
        
      
        workspace.getParts().add(part);
        PPart topPart = workspace.getParts().get(0);
        topPart.setDockStyle(DockType.TOP);
        topPart.setStyle("height:280px");
        
        
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
		addQueryItem(queryProject, "accountName", "付款状态", ControlTypes.TEXT_BOX);
		return queryProject;
	}
    
    @Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}


}
