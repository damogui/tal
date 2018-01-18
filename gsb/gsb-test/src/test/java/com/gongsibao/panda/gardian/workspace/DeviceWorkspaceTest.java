package com.gongsibao.panda.gardian.workspace;

import com.gongsibao.entity.gardian.baseinfo.Device;
import com.gongsibao.entity.igirl.baseinfo.NCLOne;
import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.*;
import org.netsharp.resourcenode.entity.ResourceNode;

/**   
 * @ClassName:  ProductWorkspaceTest   
 * @Description:TODO 尼斯分类
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
		formOpenMode = OpenMode.WINDOW;
		openWindowWidth = 800;
		openWindowHeight = 600;
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setToolbar("panda/datagrid/row/edit"); //系统默认的工具栏
			datagrid.setName("设备列表");
		}
		PDatagridColumn column = null;
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "deviceType", "设备类型", ControlTypes.ENUM_BOX, 200);
		addColumn(datagrid, "buydate", "设备购入日期", ControlTypes.DATETIME_BOX, 200);
		addColumn(datagrid, "hdddate", "硬盘装机日期", ControlTypes.DATETIME_BOX, 200);
		addColumn(datagrid, "memory", "内存", ControlTypes.NUMBER_BOX, 200);
		addColumn(datagrid, "core", "CPU", ControlTypes.NUMBER_BOX, 200);
		addColumn(datagrid, "hdd", "硬盘", ControlTypes.NUMBER_BOX, 200);
		addColumn(datagrid, "cloudhdd", "云盘", ControlTypes.NUMBER_BOX, 200);
		addColumn(datagrid, "privateip", "内网ip", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "publicip", "公网IP", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "purpose", "用途", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "memo", "备注", ControlTypes.TEXT_BOX, 200);
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
		addFormField(form, "deviceType", "设备类型", null, ControlTypes.ENUM_BOX, true,false).setWidth(200);
		addFormField(form, "buydate", "设备购入日期", null, ControlTypes.DATETIME_BOX, true,false).setWidth(200);
		addFormField(form, "hdddate", "硬盘装机日期", null, ControlTypes.DATETIME_BOX, true,false).setWidth(200);

		addFormField(form, "memory", "内存", null, ControlTypes.NUMBER_BOX, false,false).setWidth(200);
		addFormField(form, "core", "CPU", null, ControlTypes.NUMBER_BOX, false,false).setWidth(200);
		addFormField(form, "hdd", "硬盘", null, ControlTypes.NUMBER_BOX, false,false).setWidth(200);
		addFormField(form, "cloudhdd", "云盘", null, ControlTypes.NUMBER_BOX, false,false).setWidth(200);
		addFormField(form, "privateip", "内网ip", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
		addFormField(form, "publicip", "公网IP", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
		addFormField(form, "publicip", "公网IP", null, ControlTypes.TEXT_BOX, false,false).setWidth(200);
		addFormField(form, "purpose", "用途", null, ControlTypes.TEXTAREA, false,false).setWidth(200);
		addFormField(form, "memo", "备注", null, ControlTypes.TEXTAREA, false,false).setWidth(200);
		return form;
	}
	
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "deviceType", "设备类型", ControlTypes.ENUM_BOX);
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
