package com.gongsibao.panda.supplier.igirl.workspace.ic.common;

import com.gongsibao.entity.igirl.ic.baseinfo.AreaOne;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.*;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

/**   
 * @ClassName:  ProductWorkspaceTest   
 * @Description:TODO 省级分类
 * @author: 曹玉玺
 * @date:   2018.4.3
 *
 */
public class AreaOneWorkspaceTest extends WorkspaceCreationBase{

	@Before
	public void setup() {

		super.setup();
		urlList = "/igirl/ic/areaone/all/list";
		urlForm = "/igirl/ic/areaone/form";

		entity = AreaOne.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "IGRIL_IC_BASE_AreaOne";
		formPartName = listPartName = meta.getName();
		formOpenMode = OpenMode.WINDOW;
		openWindowWidth = 800;
		openWindowHeight = 600;
		listToolbarPath="/igirl/areaone/list";
	}

	@Test
	public void fromToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		OperationType ot1 = operationTypeService.byCode(OperationTypes.update);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("工具栏");
			toolbar.setResourceNode(node);

		}

		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("edit");
			item.setIcon("fa fa-edit");
			item.setName("编辑");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(3000);
			item.setCommand("{controller}.edit();");
			toolbar.getItems().add(item);
		}
		toolbarService.save(toolbar);
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setToolbar(listToolbarPath);
			datagrid.setName("省级列表");
		}
		PDatagridColumn column = null;
		column=addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100);
		//column.setOrderbyMode(OrderbyMode.ASC);
		addColumn(datagrid, "name", "省份", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "id",   "操作", ControlTypes.OPERATION_COLUMN, 100);
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
		addFormField(form, "code", "编码", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
		addFormField(form, "name", "省份", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
		return form;
	}
	
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "省份", ControlTypes.TEXT_BOX);
//		PQueryItem item =addQueryItem(queryProject, "mobilePhone", "销售方式", ControlTypes.CUSTOMER);{
//			
//			item.setCustomerControlType(DictComboBox.class.getName());
//			item.setRefFilter("type=8");
//		}
		//addQueryItem(queryProject, "enabled", "启用/禁用", ControlTypes.BOOLCOMBO_BOX);
		return queryProject;
	}

	@Override
	protected void doOperation() {
		
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		//operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
	}
}
