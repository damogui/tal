package com.gongsibao.panda.supplier.igirl.workspace.tm.common;

import com.gongsibao.entity.igirl.tm.baseinfo.NCLTwo;
import com.gongsibao.entity.product.Product;
import com.gongsibao.igirl.tm.web.NclOneTreePart;
import com.gongsibao.product.web.ProductTypeTreePart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.*;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
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
		listToolbarPath="/igirl/ncltwo/list";
	}

	@Test
	public void fromToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("案件工具栏");
			toolbar.setResourceNode(node);
		}
		toolbarService.save(toolbar);
	}
	

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setToolbar(listToolbarPath);
			datagrid.setName("小类列表");
			datagrid.setOrderby("code");
		}
		
		PDatagridColumn column = null;
		addColumn(datagrid, "nclOne.code", "大类编码", ControlTypes.TEXT_BOX, 100);
		column=addColumn(datagrid, "code", "二级编码", ControlTypes.TEXT_BOX, 100);
		column.setOrderbyMode(OrderbyMode.ASC);
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
		field = addFormFieldRefrence(form, "nclOne.code", "大类编码", null,"NCLOne", true, false);
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
