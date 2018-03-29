package com.gongsibao.panda.platform.cw.workspace.loans;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.EntityState;
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
import com.gongsibao.cw.web.LoansBillFormPart;
import com.gongsibao.cw.web.LoansBillListPart;
import com.gongsibao.entity.cw.Loan;
import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.tools.PToolbarHelper;

/**
 * 
* 借款单
* 项目名称：gsb-test   
* 类名称：LoansBillWorkspaceTest   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月20日 下午1:45:10   
* @version
 */
public class LoansBillWorkspaceTest extends WorkspaceCreationBase {
	
	private final static String costToolbarPath = "/cw/bill/loan/costToolbar";
	public static final String uploadloadToolbarPath = "/cw/bill/load/uploadToolbar";
	public static final String loadFormToolbarPath = "";
    @Before
    public void setup() {
        super.setup();
        urlList = "/cw/bill/loan/list";
		urlForm = "/cw/bill/loan/form";
        entity = Loan.class;
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "GSB_CW_Manage_Loan_Bill";
        listToolbarPath = "/cw/bill/loan/toolbar";
        
        listPartImportJs = "/gsb/platform/cw/js/loan-bill-list-part.js";
		listPartJsController = LoansBillListPart.class.getName();
		listPartServiceController = LoansBillListPart.class.getName();
		
		formJsImport = "/gsb/platform/cw/js/loan-bill-form.part.js|/package/qiniu/plupload.full.min.js";
		formServiceController = LoansBillFormPart.class.getName();
	    formJsController = LoansBillFormPart.class.getName();
	     
    }
    
    @Test
	public void run() {
		createListWorkspace();
		createFormWorkspace();
	}
    
    @Test
    public void saveListToolbar() {
    	//列表toolbar
    	ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listToolbarPath);
            toolbar.setName("借款单");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }

        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("createLoan");
            item.setIcon(PToolbarHelper.iconAdd);
            item.setName("申请借款");
            item.setSeq(7);
            item.setCommand("{controller}.applyLoan();");
            toolbar.getItems().add(item);
        } 
        toolbarService.save(toolbar);
        
        //费用明细toobar
        PToolbar toolbarCost = new PToolbar ();
        {
        	toolbarCost.toNew ();
        	toolbarCost.setPath (costToolbarPath);
        	toolbarCost.setName ("费用明细操作");
        	toolbarCost.setResourceNode (node);
        	toolbarCost.setToolbarType (ToolbarType.BASE);

        }
        PToolbarItem costItem = PToolbarHelper.getPToolbarItem (EntityState.New, "costDetailAdd", PToolbarHelper.iconAdd,"新增", null, 1, "{controller}.add();");
        toolbarCost.getItems ().add (costItem);
        costItem = PToolbarHelper.getPToolbarItem (EntityState.New, "costDetailDel", PToolbarHelper.iconDel, "删除", null, 1, "{controller}.remove();");
        toolbarCost.getItems ().add (costItem);
        toolbarService.save (toolbarCost);
        
        //附件toobar
        PToolbar toolbarFile = new PToolbar ();
        {
        	toolbarFile.toNew ();
        	toolbarFile.setPath (uploadloadToolbarPath);
        	toolbarFile.setName ("上传附件操作");
        	toolbarFile.setResourceNode (node);
        	toolbarFile.setToolbarType (ToolbarType.BASE);

        }
        PToolbarItem fileItem = new PToolbarItem();
		{
			fileItem.toNew();
			fileItem.setCode("uploadAdd");
			fileItem.setIcon("fa fa-cloud-upload");
			fileItem.setName("上传");
			fileItem.setSeq(1);
			toolbarFile.getItems().add(fileItem);
		}
        toolbarService.save (toolbarFile);


    }
    
    @Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setName("借款单");
		}
		PDatagridColumn column = null;
		addColumn(datagrid, "code", "借款单号", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "type", "借款类型", ControlTypes.ENUM_BOX, 150);
		addColumn(datagrid, "amount", "借款金额", ControlTypes.DECIMAL_FEN_BOX, 100);
		addColumn(datagrid, "repaymentAmount", "已还金额", ControlTypes.DECIMAL_FEN_BOX, 100);
		addColumn(datagrid, "arrearsAmount", "未还金额", ControlTypes.DECIMAL_FEN_BOX, 100);
		addColumn(datagrid, "setOfBooks.name", "付款单位", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "creator", "申请人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 200);
		addColumn(datagrid, "status", "审核状态", ControlTypes.ENUM_BOX, 150);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 400);
		return datagrid;
	}
    
    @Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "code", "单据号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "type", "借款类型", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "status", "审批状态", ControlTypes.ENUM_BOX);
		
		return queryProject;
	}
    
    //======================================== form 相关=================================================//
    protected PForm createForm(ResourceNode node) {

    	  PForm form = super.createForm(node);
          String groupName = "";
          PFormField formField = null;
          addFormField(form, "type", "借款类型", ControlTypes.ENUM_BOX, true, false);
          addFormFieldRefrence(form, "setOfBooks.name", "付款单位",null,  SetOfBooks.class.getSimpleName(), true, false);
          formField =  addFormField(form, "paymentMethod", "付款方式", ControlTypes.ENUM_BOX, true, false);
          {
        	  formField.setTroikaTrigger("controllerloan .paymentMethodChange(this);");
          }
          addFormField(form, "companyName", "公司名称", ControlTypes.TEXT_BOX, true, true);
          addFormField(form, "companyBank", "公司开户行", ControlTypes.TEXT_BOX, true, true);
          formField =  addFormField(form, "companyAccount", "公司银行账号", ControlTypes.TEXT_BOX, true, true);
          addFormField(form, "amount", "借款金额", ControlTypes.DECIMAL_FEN_BOX, true, false);
          formField = addFormField(form, "memoto", "备注", ControlTypes.TEXTAREA, true, false);
          {
        	  formField.setColumnSpan(3);
        	  formField.setFullColumn(false);
        	  formField.setWidth(520);
          }
          
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
            PDatagridColumn column = null;
            
        	addColumn(datagrid, "organization.pathName", "费用归属部门", ControlTypes.TEXT_BOX, 250);
            addColumn(datagrid, "costType", "费用类型", ControlTypes.ENUM_BOX, 250);
            addColumn(datagrid, "detailMoney", "金额", ControlTypes.TEXT_BOX, 100);
            addColumn(datagrid, "memoto", "说明", ControlTypes.TEXT_BOX, 300);
        }
        
        PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("新增费用明细");

			PFormField formField = null;
			addFormFieldRefrence(form, "organization.pathName", "费用归属部门",null,"Organization-Department-Filter", true, false);
			addFormField(form, "costType", "费用类型", null, ControlTypes.ENUM_BOX, true, false);
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
    //附件(未完成)
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

		}
		workspace.getParts().add(part);

	}

    
    @Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}

}
