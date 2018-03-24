package com.gongsibao.panda.platform.product.workspace;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.product.Product;
import com.gongsibao.product.web.ProductListPart;
import com.gongsibao.tools.PToolbarHelper;

/**   
 * @ClassName:  ProductWorkspaceTest   
 * @Description:TODO 产品列表
 * @author: wwm
 * @date:   2018年3月22日
 *     
 */
public class ProductWorkspaceTest  extends WorkspaceCreationBase{

	@Before
	public void setup() {

		super.setup();
		urlList = "/prod/product/list";
		entity = Product.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "GSB_Product_Manage_Product";
		formPartName = listPartName = meta.getName();
//		formOpenMode = OpenMode.WINDOW;
//		openWindowWidth = 800;
//		openWindowHeight = 600;
		listToolbarPath = "product/detail/all/list";
		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/platform/product/js/add-product-all.part.js");
		ss.add("/gsb/panda-extend/gsb.custom.query.controls.js");
		ss.add("/gsb/panda-extend/gsb.pubcontrol.js");
		listPartImportJs = StringManager.join("|", ss);
		listPartJsController = ProductListPart.class.getName();
		listPartServiceController = ProductListPart.class.getName();

	}
	
//	@Test
//	@Override
//	public void run() {
////		createListWorkspace();
//		this.createTreeWorkspace();
//		this.createFormWorkspace();
//		this.createListToolbar();
//		this.createRowToolbar();
//	}
	
	@Test
	public void createListToolbar() {

		String listToolbarPath = "product/detail/all/list";
		ResourceNode node = resourceService.byCode(resourceNodeCode);
		OperationType ot1 = operationTypeService.byCode(OperationTypes.view);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("产品录入");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}

		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("addProduct");
			item.setIcon(PToolbarHelper.iconAdd);
			item.setName("录入产品");
			item.setOperationType(ot1);
			item.setSeq(2);
			item.setCommand("{controller}.addProducts();");
			toolbar.getItems().add(item);
		}

		toolbarService.save(toolbar);
	}
	
	@Test
	public void createRowToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		String listrowToolbarPath = "product/rowdetail/all/list";
		{
			toolbar.toNew();
			toolbar.setBasePath("panda/datagrid/row/edit");
			toolbar.setPath(listrowToolbarPath);
			toolbar.setName("转移");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("orderTran");
			item.setName("转移");
			item.setSeq(2);
			item.setCommand("{controller}.orderTran();");
			toolbar.getItems().add(item);
		}

		toolbarService.save(toolbar);
	}
	
//	public void createTreeWorkspace() {
//
//		ResourceNode node = resourceService.byCode("GSB_Basic_Config_Dict");
//		ResourceNode node2 = resourceService.byCode(resourceNodeCode);
//		PWorkspace workspace = new PWorkspace();
//		{
//			workspace.toNew();
//			workspace.setResourceNode(node2);
//			workspace.setName("产品列表工作区");
//			workspace.setUrl(urlList);
//		}
//
//		PPart part = new PPart();
//		{
//			part.toNew();
//			part.setCode("ProductTypeTree");
//			part.setPartTypeId(org.netsharp.panda.dic.PartType.TREE_PART.getId());
//			part.setDockStyle(DockType.LEFT);
//			part.setStyle("width:280px;");
//			part.setResourceNode(node);
//			part.setToolbar("panda/tree");
//			part.setUrl(this.urlForm);
//			part.setServiceController(ProductTypeTreePart.class.getName());
//		}
//		workspace.getParts().add(part);
//
//		PDatagrid datagrid = this.createDatagrid(node2);
//		part = new PPart();
//		{
//			part.toNew();
//			part.setCode("items");
//			part.setParentCode("ProductTypeTree");
//			part.setPartTypeId(org.netsharp.panda.dic.PartType.DATAGRID_PART.getId());
//			part.setDatagrid(datagrid);
//			part.setDockStyle(DockType.DOCUMENTHOST);
//			part.setRelationRole("typeId");
//			part.setToolbar(this.listToolbarPath);
//			part.setUrl(this.urlForm);
//			part.setOpenMode(formOpenMode);
//			part.setWindowWidth(openWindowWidth);
//			part.setWindowHeight(openWindowHeight);
//			part.setImports(listPartImportJs);
//			part.setResourceNode(node2);
//		}
//
//		workspace.getParts().add(part);
//		workspaceService.save(workspace);
//	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setName("产品列表");
		}
		
		PDatagridColumn column = null;

		addColumn(datagrid, "no", "操作", ControlTypes.OPERATION_COLUMN, 100);
		addColumn(datagrid, "id", "编号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "产品名称", ControlTypes.TEXT_BOX, 200);
		column = addColumn(datagrid, "type.name", "产品分类", ControlTypes.TEXT_BOX, 100);{
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "name", "归属事业部", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "dealerType.name", "销售方式", ControlTypes.TEXT_BOX, 100);{
			column.setAlign(DatagridAlign.CENTER);
		}
		addColumn(datagrid, "description", "描述", ControlTypes.TEXT_BOX, 200);
		column = addColumn(datagrid, "enabled", "启用/禁用", ControlTypes.TEXT_BOX, 100);{
			column.setAlign(DatagridAlign.CENTER);
			column.setFormatter(" return value==false?'停用':'启用';");
			column.setFormatter("return controllerproductList.enabledFormatter(value,row,index);");
		}								 
		return datagrid;
	}

	//产品名称，销售方式
//	@Override
//	protected PForm createForm(ResourceNode node) {
//
//		PForm form = new PForm(node, this.formPartName);
//		{
//			form.setColumnCount(1);
//		}
//		
//		PFormField field = null;
//
//		addFormField(form, "organizationType", "类型", null, ControlTypes.ENUM_BOX, true);
//		addFormField(form, "name", "公司名称", null, ControlTypes.TEXT_BOX, true,false);
//		addFormField(form, "shortName", "组织名称", null, ControlTypes.TEXT_BOX, true,false);
//		addFormFieldRefrence(form, "leader.name", "主管人", null, "Gsb_User", false,false);
//
//		//city
//		addFormField(form, "enabled", "启用", null, ControlTypes.SWITCH_BUTTON, false,false);
//		addFormField(form, "remark", "备注", null, ControlTypes.TEXTAREA, false,false);
//		return form;
//	}
	
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "产品名称", ControlTypes.TEXT_BOX);
//		PQueryItem item =addQueryItem(queryProject, "mobilePhone", "销售方式", ControlTypes.CUSTOMER);{
//			
//			item.setCustomerControlType(DictComboBox.class.getName());
//			item.setRefFilter("type=8");
//		}
		addQueryItem(queryProject, "enabled", "启用/禁用", ControlTypes.BOOLCOMBO_BOX);
		return queryProject;
	}

//	@Override
//	protected void doOperation() {
//		
//		ResourceNode node = this.getResourceNode();
//		operationService.addOperation(node,OperationTypes.view);
//		operationService.addOperation(node,OperationTypes.add);
//		operationService.addOperation(node,OperationTypes.update);
//	}
}
