package com.gongsibao.panda.igirl.workspace;

import com.gongsibao.entity.igirl.baseinfo.NCLTwo;
import com.gongsibao.entity.product.Product;
import com.gongsibao.igirl.web.NclOneTreePart;
import com.gongsibao.product.web.ProductTypeTreePart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.*;
import org.netsharp.resourcenode.entity.ResourceNode;

/**   
 * @ClassName:  ProductWorkspaceTest   
 * @Description:TODO 尼斯分类
 * @author: 蒋勇
 * @date:   2017年12月7日 下午8:17:21   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class NclTwoWorkspaceTest extends WorkspaceCreationBase{

	@Before
	public void setup() {

		super.setup();
		urlList = "/igirl/ncltwo/all/list";
		urlForm = "/igirl/ncltwo/form";

		entity = NCLTwo.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "IGRIL_BASE_NCLTwo";
		formPartName = listPartName = meta.getName();
		formOpenMode = OpenMode.WINDOW;
		openWindowWidth = 800;
		openWindowHeight = 600;
	}
	
//	@Test
//	@Override
//	public void run() {
//
//		this.createTreeWorkspace();
//		this.createFormWorkspace();
//	}
//
//	public void createTreeWorkspace() {
//
//		ResourceNode node = resourceService.byCode("IGRIL_BASE_NCLOne");
//		ResourceNode node2 = resourceService.byCode(resourceNodeCode);
//		PWorkspace workspace = new PWorkspace();
//		{
//			workspace.toNew();
//			workspace.setResourceNode(node2);
//			workspace.setName("商品大类工作区");
//			workspace.setUrl(urlList);
//		}
//
//		PPart part = new PPart();
//		{
//			part.toNew();
//			part.setCode("IGRIL_BASE_NCLOne");
//			part.setPartTypeId(org.netsharp.panda.dic.PartType.TREE_PART.getId());
//			part.setDockStyle(DockType.LEFT);
//			part.setStyle("width:280px;");
//			part.setResourceNode(node);
//			part.setToolbar("panda/tree");
//			part.setUrl("/igirl/nclone/form");
//			part.setServiceController(NclOneTreePart.class.getName());
//		}
//		workspace.getParts().add(part);
//
//		PDatagrid datagrid = this.createDatagrid(node2);
//		part = new PPart();
//		{
//			part.toNew();
//			part.setCode("nclTwos");
//			part.setParentCode("IGRIL_BASE_NCLOne");
//			part.setPartTypeId(org.netsharp.panda.dic.PartType.DATAGRID_PART.getId());
//			part.setDatagrid(datagrid);
//			part.setDockStyle(DockType.DOCUMENTHOST);
//			part.setRelationRole("nclOneId");
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
			datagrid.setName("小类列表");
		}
		
		PDatagridColumn column = null;
		addColumn(datagrid, "nclOne.name", "所属大类", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "code", "二级编码", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "thirdCode", "小类编码", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "内容", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 100);
		return datagrid;
	}

	//
	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, this.formPartName);
		{
			form.setColumnCount(1);
		}
		
		PFormField field = null;

		//addFormField(form, "nclOne.name", "商标大类", null, ControlTypes.re,false);
		field = addFormFieldRefrence(form, "nclOne.name", "商标大类", null,"NCLOne", true, false);
		{
			field.setWidth(100);
		}
		addFormField(form, "code", "二级编码", null, ControlTypes.TEXT_BOX, true,false);
		addFormField(form, "thirdCode", "小类编码", null, ControlTypes.TEXT_BOX, false,false);
		addFormField(form, "name", "内容", null, ControlTypes.TEXTAREA, true,false);
		return form;
	}
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addRefrenceQueryItem(queryProject,"nclOne.name","商标大类","NCLOne");
		addQueryItem(queryProject, "code", "二级编码", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "name", "内容", ControlTypes.TEXT_BOX);
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
