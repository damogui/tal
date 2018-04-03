package com.gongsibao.panda.supplier.igirl.workspace.ic.common;

import com.gongsibao.entity.igirl.ic.baseinfo.AreaTwo;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.*;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.resourcenode.entity.ResourceNode;

/**
 * @ClassName:  ProductWorkspaceTest
 * @Description:TODO 地市分类
 * @author: 曹玉玺
 * @date:   2018.4.3
 *
 */
public class AreaTwoWorkspaceTest extends WorkspaceCreationBase{

	@Before
	public void setup() {

		super.setup();
		urlList = "/igirl/ic/areatwo/all/list";
		urlForm = "/igirl/areatwo/form";

		entity = AreaTwo.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "IGRIL_IC_BASE_AreaTwo";
		formPartName = listPartName = meta.getName();
		formOpenMode = OpenMode.WINDOW;
		openWindowWidth = 800;
		openWindowHeight = 600;
		listToolbarPath="/igirl/areatwo/list";
	}

	@Test
	public void fromToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("工具栏");
			toolbar.setResourceNode(node);
		}
		toolbarService.save(toolbar);
	}
	

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setToolbar(listToolbarPath);
			datagrid.setName("地市列表");
			datagrid.setOrderby("code");
		}
		
		PDatagridColumn column = null;
		addColumn(datagrid, "areaOne.name", "所属省份", ControlTypes.TEXT_BOX, 100);
		column=addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100);
		column.setOrderbyMode(OrderbyMode.ASC);
		//addColumn(datagrid, "thirdCode", "小类编码", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "地市名称", ControlTypes.TEXT_BOX, 200);
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
		field = addFormFieldRefrence(form, "areaOne.code", "所属省份", null,"AreaOne", true, false);
		{
			field.setWidth(100);
		}
		addFormField(form, "code", "编码", null, ControlTypes.TEXT_BOX, true,false);
//		addFormField(form, "thirdCode", "小类编码", null, ControlTypes.TEXT_BOX, false,false);
		addFormField(form, "name", "地市名称", null, ControlTypes.TEXTAREA, true,false);
		return form;
	}
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		//	addRefrenceQueryItem(queryProject,"areaOne.name","所属省份","AreaOne");
		//addQueryItem(queryProject, "code", "二级编码", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
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
