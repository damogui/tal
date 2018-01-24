package com.gongsibao.panda.gardian.workspace;

import com.gongsibao.entity.gardian.baseinfo.Device;
import com.gongsibao.entity.gardian.baseinfo.Provides;
import com.gongsibao.igirl.web.TradeMarkDetailPart;
import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.*;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

/**   
 * @ClassName:  ProductWorkspaceTest   
 * @Description:TODO 尼斯分类
 * @author: 蒋勇
 * @date:   20181.1.10
 *
 */
public class ProvidesWorkspaceTest extends WorkspaceCreationBase{

	@Before
	public void setup() {

		super.setup();
		urlList = "/gardian/provides/list";
		urlForm = "/gardian/provides/form";

		entity = Provides.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = "服务列表";
		resourceNodeCode = "GARDIAN_BASE_Provides";
		formPartName = listPartName = meta.getName();
		formOpenMode = OpenMode.WINDOW;
		openWindowWidth = 800;
		openWindowHeight = 600;
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setToolbar("panda/datagrid/row/edit"); //系统默认的工具栏
			datagrid.setName("服务列表");
		}
		PDatagridColumn column = null;
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "useage", "用途", ControlTypes.TEXTAREA, 100);

		addColumn(datagrid, "occurrences", "预计并发数", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "domainname", "使用域名", ControlTypes.TEXT_BOX, 200);

		addColumn(datagrid, "id",   "操作", ControlTypes.OPERATION_COLUMN, 100);
		return datagrid;
	}

	//
	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, this.formPartName);
		{
			//form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(2);//每行列数
		}

		PFormField field = null;
		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
		addFormField(form, "useage", "用途", null, ControlTypes.TEXTAREA, true,false).setWidth(200);
		addFormField(form, "occurrences", "预计并发数", null, ControlTypes.NUMBER_BOX, false,false).setWidth(200);
		addFormField(form, "domainname", "使用域名", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
		return form;
	}
	
	
//	@Override
//	protected PQueryProject createQueryProject(ResourceNode node) {
//
//		PQueryProject queryProject = super.createQueryProject(node);
//		queryProject.toNew();
//		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
//		addQueryItem(queryProject, "useage", "用途", ControlTypes.TEXT_BOX);
////		PQueryItem item =addQueryItem(queryProject, "mobilePhone", "销售方式", ControlTypes.CUSTOMER);{
////
////			item.setCustomerControlType(DictComboBox.class.getName());
////			item.setRefFilter("type=8");
////		}
//		//addQueryItem(queryProject, "enabled", "启用/禁用", ControlTypes.BOOLCOMBO_BOX);
//		return queryProject;
//	}
	private void createProvidesEnvPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("GARDIAN_BASE_ProvidesEnv");
		PDatagrid datagrid = new PDatagrid(node, "服务环境");
		{
			addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
			addColumn(datagrid, "privateip", "内网IP", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "privateport", "内网端口", ControlTypes.NUMBER_BOX, 150);
			addColumn(datagrid, "publicip", "外网ip", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "publicport", "外网端口", ControlTypes.NUMBER_BOX, 150);



		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("服务环境");
			String groupName = null;
			PFormField formField = null;
			addFormField(form, "name", "名称", groupName, ControlTypes.TEXT_BOX, true, false);
			addFormField(form, "privateip", "内网IP", groupName, ControlTypes.TEXT_BOX, true, false);
			addFormField(form, "privateport", "内网端口", groupName, ControlTypes.NUMBER_BOX, true, false);
			addFormField(form, "publicip", "外网ip", groupName, ControlTypes.TEXT_BOX, true, false);
			addFormField(form, "publicport", "外网端口", groupName, ControlTypes.NUMBER_BOX, true, false);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("服务环境");
			part.setCode("providesEnv");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("providesEnv");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			//part.setJsController("com.gongsibao.igirl.web.TradeMarkDetailPart");
			//part.setServiceController(TradeMarkDetailPart.class.getName());
			part.setWindowWidth(800);
			part.setWindowHeight(600);
			part.setForm(form);

		}
		workspace.getParts().add(part);
		part = workspace.getParts().get(0);
		{
			part.setName("服务信息");
			part.setStyle("height:500px;");
			part.setDockStyle(DockType.DOCUMENTHOST);
		}



	}

	protected void addDetailGridPart(PWorkspace workspace) {

		createProvidesEnvPart(workspace);

	}




	@Override
	protected void doOperation() {
		
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
	}
}
