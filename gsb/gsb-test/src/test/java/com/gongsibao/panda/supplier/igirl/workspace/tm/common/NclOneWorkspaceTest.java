package com.gongsibao.panda.supplier.igirl.workspace.tm.common;

import com.gongsibao.entity.igirl.tm.baseinfo.NCLOne;
import com.gongsibao.entity.igirl.tm.baseinfo.NCLTwo;
import com.gongsibao.igirl.tm.web.NclOneTreePart;
import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
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
 * @date:   20181.1.10
 *
 */
public class NclOneWorkspaceTest extends WorkspaceCreationBase{

	@Before
	public void setup() {

		super.setup();
		urlList = "/igirl/nclone/all/list";
		urlForm = "/igirl/nclone/form";

		entity = NCLOne.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "IGRIL_BASE_NCLOne";
		formPartName = listPartName = meta.getName();
		formOpenMode = OpenMode.WINDOW;
		openWindowWidth = 800;
		openWindowHeight = 600;
		listToolbarPath="/igirl/nclone/list";
	}

	@Test
	public void fromToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		OperationType ot1 = operationTypeService.byCode(OperationTypes.add);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath(listToolbarPath);
			toolbar.setName("案件工具栏");
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
			datagrid.setName("商标大类");
		}
		PDatagridColumn column = null;
		addColumn(datagrid, "nclBatch.context", "尼斯期间", ControlTypes.TEXT_BOX, 200);
		column=addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100);
		column.setOrderbyMode(OrderbyMode.ASC);
		addColumn(datagrid, "name", "内容", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "memo", "说明", ControlTypes.TEXT_BOX, 200);
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
		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
		addFormField(form, "memo", "说明", null, ControlTypes.TEXTAREA, true,false).setWidth(200);
		return form;
	}
	
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
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
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
	}
}
