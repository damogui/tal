package com.gongsibao.panda.supplier.igirl.workspace;
import com.gongsibao.entity.igirl.baseinfo.SupplierNewInfo;
import com.gongsibao.entity.igirl.baseinfo.SupplierSiteInfo;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
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
public class SupplierSiteInfoWorkspaceTest extends WorkspaceCreationBase{

	@Before
	public void setup() {

		super.setup();
		urlList = "/igirl/siteinfo/list";
		urlForm = "/igirl/siteinfo/form";

		entity = SupplierSiteInfo.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "IGRIL_SITE_"+SupplierSiteInfo.class.getSimpleName();
		formPartName = listPartName = meta.getName();
		formOpenMode = OpenMode.WINDOW;
		openWindowWidth = 800;
		openWindowHeight = 600;
		listToolbarPath="/igirl/siteinfo/list";
	}

	@Test
	public void fromToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		OperationType ot1 = operationTypeService.byCode(OperationTypes.add);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			//toolbar.setBasePath(basePath);
			toolbar.setPath(listToolbarPath);
			toolbar.setName("站点工具栏");
			toolbar.setResourceNode(node);

		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("add");
			item.setIcon("fa fa-plus");
			item.setName("新增");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(3000);
			item.setCommand("{controller}.add();");
			toolbar.getItems().add(item);
		}
     item = new PToolbarItem();
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
		 item = new PToolbarItem();
			{
				item.toNew();
				item.setCode("remove");
				item.setIcon("fa fa-remove");
				item.setName("删除");
				item.setCommand(null);
				item.setOperationType(ot1);
				item.setSeq(3000);
				item.setCommand("{controller}.remove();");
				toolbar.getItems().add(item);
			}
			 item = new PToolbarItem();
				{
					item.toNew();
					item.setCode("attachment");
					item.setIcon("fa fa-link");
					item.setName("轮播图");
					item.setCommand(null);
					item.setOperationType(ot1);
					item.setSeq(3000);
					item.setCommand("{controller}.attachment();");
					toolbar.getItems().add(item);
				}
		toolbarService.save(toolbar);
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setToolbar(listToolbarPath);
			datagrid.setName("最新资讯");
		}
		PDatagridColumn column = null;
		addColumn(datagrid, "title", "标题", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "logoUrl", "logo", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "memo", "关于我们", ControlTypes.TEXT_BOX, 200);
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
		addFormField(form, "title", "标题", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
		addFormField(form, "memo", "关于我们", null, ControlTypes.TEXTAREA, true,false).setWidth(200);
		addFormField(form, "logoUrl", "logo", null, ControlTypes.OSS_UPLOAD, true,false).setWidth(200);
		return form;
	}
	
	
//	@Override
//	protected PQueryProject createQueryProject(ResourceNode node) {
//
//		PQueryProject queryProject = super.createQueryProject(node);
//		queryProject.toNew();
//		addQueryItem(queryProject, "title", "标题", ControlTypes.TEXT_BOX);
//		return queryProject;
//	}

	@Override
	protected void doOperation() {
		
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
	}
}
