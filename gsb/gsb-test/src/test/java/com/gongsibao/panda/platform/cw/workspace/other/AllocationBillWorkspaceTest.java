package com.gongsibao.panda.platform.cw.workspace.other;

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
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.cw.web.AllocationBillFormPart;
import com.gongsibao.cw.web.AllocationBillListPart;
import com.gongsibao.entity.cw.Allocation;
import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.tools.PToolbarHelper;

/**
 * 
* 资金调拨单 
* 项目名称：gsb-test   
* 类名称：AllocationBillWorkspaceTest   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月20日 下午1:45:10   
* @version
 */
public class AllocationBillWorkspaceTest extends WorkspaceCreationBase {
	
	public static final String uploadloadToolbarPath = "/cw/bill/allocation/uploadToolbar";
	
    @Before
    public void setup() {

        super.setup();
        urlList = "/cw/bill/allocation/list";
        urlForm = "/cw/bill/allocation/form";
        
        entity = Allocation.class;
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "GSB_CW_Manage_Allocation_Bill";
        
        listToolbarPath = "/cw/bill/allocation/toolbar";
        
        listPartImportJs = "/gsb/platform/cw/js/allocation-bill-list-part.js";
		listPartJsController = AllocationBillListPart.class.getName();
		listPartServiceController = AllocationBillListPart.class.getName();
		
		formToolbarPath = "";
		formJsImport = "/gsb/platform/cw/js/allocation-bill-form.part.js|/package/qiniu/plupload.full.min.js";
		formServiceController = AllocationBillFormPart.class.getName();
	    formJsController = AllocationBillFormPart.class.getName();
    }
    
    @Test
	public void run() {
		createListWorkspace();
		createFormWorkspace();
	}
	
    @Test
    public void saveListToolbar() {
    	ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listToolbarPath);
            toolbar.setName("调拨单");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }

        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("createAllocation");
            item.setIcon(PToolbarHelper.iconAdd);
            item.setName("申请调拨单");
            item.setSeq(7);
            item.setCommand("{controller}.applyAllocation();");
            toolbar.getItems().add(item);
        } 
        toolbarService.save (toolbar);
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
			datagrid.setName("调拨单");
		}
		addColumn(datagrid, "code", "调拨单号", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "purpose", "用途", ControlTypes.ENUM_BOX, 150);
		addColumn(datagrid, "amount", "调拨金额", ControlTypes.DECIMAL_FEN_BOX, 150);
		addColumn(datagrid, "payerCompany.name", "付款单位", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "payeeCompany.name", "收款单位", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 200);
		addColumn(datagrid, "status", "审核状态", ControlTypes.ENUM_BOX, 150);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 400);
		return datagrid;
	}
    
    protected PForm createForm(ResourceNode node) {

  	    PForm form = super.createForm(node);
        addFormFieldRefrence(form, "payerCompany.name", "付款单位",null,  SetOfBooks.class.getSimpleName(), true, false);
        addFormField(form, "purpose", "用途", ControlTypes.ENUM_BOX, true, false);
        addFormField(form, "amount", "调拨金额", ControlTypes.DECIMAL_FEN_BOX, true, false);
        addFormFieldRefrence(form, "payeeCompany.name", "收款单位",null,  SetOfBooks.class.getSimpleName(), true, false);
        addFormField(form, "companyBank", "公司开户行", ControlTypes.TEXT_BOX, true, false);
        addFormField(form, "companyAccount", "公司银行账号", ControlTypes.TEXT_BOX, true, false);
        addFormField(form, "memoto", "备注", ControlTypes.TEXTAREA, true, false);
		return form;
	}
    //重写父类方法
    protected void addDetailGridPart(PWorkspace workspace) {
    	createUploadAttamentDetailPart(workspace);
	}
    //附件
	private void createUploadAttamentDetailPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("GSB_CW_Manage_Allocation_Bill");
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
			part.setHeaderVisible(true);
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
		PPart topPart = workspace.getParts().get(0);
	    topPart.setDockStyle(DockType.TOP);
	    topPart.setStyle("height:280px");
	}
    
    @Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "code", "单据号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "purpose", "用途", ControlTypes.ENUM_BOX);
		return queryProject;
	}
    
    @Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}
    

}
