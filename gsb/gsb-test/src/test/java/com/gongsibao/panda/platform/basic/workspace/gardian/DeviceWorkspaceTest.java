package com.gongsibao.panda.platform.basic.workspace.gardian;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.gardian.baseinfo.Device;
import com.gongsibao.gardian.web.DeviceFormPart;

/**   
 * @ClassName:  ProductWorkspaceTest   
 * @Description:TODO
 * @author: 蒋勇
 * @date:   20181.1.10
 *
 */
public class DeviceWorkspaceTest extends WorkspaceCreationBase{

	@Before
	public void setup() {

		super.setup();
		urlList = "/gardian/device/list";
		urlForm = "/gardian/device/form";

		entity = Device.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "GARDIAN_BASE_Device";
		formPartName = listPartName = meta.getName();
		//formOpenMode = OpenMode.WINDOW;

		formServiceController = DeviceFormPart.class.getName();
		formJsController = DeviceFormPart.class.getName();
		formJsImport = "/gsb/platform/gardian/js/device.form.part.js";

		openWindowWidth = 800;
		openWindowHeight = 600;
		listToolbarPath = "/gardian/list/toolbar";
	}

	@Test
	public void fromToolbar() {

		ResourceNode node =	 this.resourceService.byCode(resourceNodeCode);
		OperationType ot1 = operationTypeService.byCode(OperationTypes.add);

		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			//toolbar.setBasePath("panda/datagrid/edit");
			toolbar.setPath(listToolbarPath);
			toolbar.setName("工具栏");
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
			item.setIcon("fa fa-trash-o");
			item.setName("删除");
			item.setCommand(null);
			item.setOperationType(ot1);
			item.setSeq(4000);
			item.setCommand("{controller}.remove();");
			toolbar.getItems().add(item);
		}


		toolbarService.save(toolbar);
	}


	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setToolbar("panda/datagrid/row/edit"); //系统默认的工具栏
			datagrid.setName("设备列表");
		}
//		PDatagridColumn column = null;
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "deviceType", "设备类型", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "deviceStatus", "设备状态", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "buydate", "设备购入日期", ControlTypes.DATETIME_BOX, 200);
		addColumn(datagrid, "alertdays", "设备预警天数", ControlTypes.NUMBER_BOX, 100);
		addColumn(datagrid, "lifemonths", "设备寿命（月）", ControlTypes.NUMBER_BOX, 100);
		addColumn(datagrid, "cost", "购置金额（元）", ControlTypes.NUMBER_BOX, 120);

//		addColumn(datagrid, "hdddate", "硬盘装机日期", ControlTypes.DATETIME_BOX, 200);
//		addColumn(datagrid, "memory", "内存数量（G）", ControlTypes.NUMBER_BOX, 200);
//		addColumn(datagrid, "core", "CPU个数（个）", ControlTypes.NUMBER_BOX, 200);
//		addColumn(datagrid, "hdd", "硬盘容量（G）", ControlTypes.NUMBER_BOX, 200);
//		addColumn(datagrid, "cloudhdd", "云盘容量（G）", ControlTypes.NUMBER_BOX, 200);
//		addColumn(datagrid, "privateip", "内网ip", ControlTypes.TEXT_BOX, 200);
//		addColumn(datagrid, "publicip", "公网IP", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "purpose", "用途", ControlTypes.TEXT_BOX, 200);
//		addColumn(datagrid, "memo", "备注", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "id",   "操作", ControlTypes.OPERATION_COLUMN, 100);
		return datagrid;
	}

	//
	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm(node, this.formPartName);
		{
			form.setColumnCount(2);
		}

		PFormField formField = null;
		addFormField(form, "code", "编码", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
		addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true,false).setWidth(200);
		formField = addFormField(form, "deviceType", "设备类型", "设备基础信息", ControlTypes.ENUM_BOX, true,false);
		{
			formField.setTroikaTrigger("controllerdevice.deviceTypeChange(newValue, oldValue);");
			formField.setWidth(200);
		}
		addFormField(form, "buydate", "设备购入日期", "设备基础信息", ControlTypes.DATETIME_BOX, true,false).setWidth(200);
		addFormField(form, "deviceStatus", "设备状态", "设备基础信息", ControlTypes.ENUM_BOX, true,false).setWidth(200);
		addFormField(form, "cost", "购置金额（元）", "设备基础信息", ControlTypes.NUMBER_BOX, false,false).setWidth(200);

		addFormField(form, "lifemonths", "设备寿命（月）", "设备基础信息", ControlTypes.NUMBER_BOX, false,false).setWidth(200);
		addFormField(form, "alertdays", "设备预警天数", "设备基础信息", ControlTypes.NUMBER_BOX, false,false).setWidth(200);

		addFormField(form, "memory", "内存数量（G）", "主机配置", ControlTypes.NUMBER_BOX, false,false).setWidth(200);
		addFormField(form, "core", "CPU数（个）", "主机配置", ControlTypes.NUMBER_BOX, false,false).setWidth(200);
		addFormField(form, "hdd", "硬盘容量（G）", "主机配置", ControlTypes.NUMBER_BOX, false,false).setWidth(200);
		addFormField(form, "cloudhdd", "云盘容量（G）", "主机配置", ControlTypes.NUMBER_BOX, false,false).setWidth(200);

		addFormField(form, "hdddate", "硬盘装机日期", "附加信息", ControlTypes.DATETIME_BOX, false,false).setWidth(200);
		addFormField(form, "privateip", "内网ip", "附加信息", ControlTypes.TEXT_BOX, false,false).setWidth(200);
		addFormField(form, "publicip", "公网IP", "附加信息", ControlTypes.TEXT_BOX, false,false).setWidth(200);
		addFormField(form, "purpose", "用途", "附加信息", ControlTypes.TEXTAREA, false,false).setWidth(200);
		addFormField(form, "memo", "备注", "附加信息", ControlTypes.TEXTAREA, false,false).setWidth(200);
		return form;
	}
	
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "deviceType", "设备类型", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "deviceStatus", "设备状态", ControlTypes.ENUM_BOX);
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
