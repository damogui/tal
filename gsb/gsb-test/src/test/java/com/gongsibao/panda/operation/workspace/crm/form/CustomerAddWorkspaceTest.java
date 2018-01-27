package com.gongsibao.panda.operation.workspace.crm.form;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.utils.EnumUtil;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.controls.CityComboBox;
import com.gongsibao.crm.web.NCustomerAddFormPart;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.crm.dic.TaskCustomerType;

public class CustomerAddWorkspaceTest extends WorkspaceCreationBase {

	protected String taskDetailResourceNodeCode = "Operation_CRM_Task_ALL";
	protected String taskDetailJsController = "com.gongsibao.crm.web.NCustomerTaskDetailPart";
	
	@Before
	public void setup() {
		super.setup();
		entity = NCustomer.class;
		urlForm = "/operation/customer/add";
		listPartName = formPartName = "新增客户";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "Operation_CRM_Customer_Add";
		formJsImport = "/gsb/crm/platform/js/customer-add-form.part.js|/gsb/gsb.customer.controls.js";
		formJsController = NCustomerAddFormPart.class.getName();
		formServiceController = NCustomerAddFormPart.class.getName();
	}
	

	@Test
	public void run() {

		createFormWorkspace();
	}

	// 默认的表单配置信息
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(3);
		PFormField formField = null;
		
		String groupName = "基本信息";
		addFormField(form, "realName", "姓名", groupName, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "sex", "性别", groupName, ControlTypes.ENUM_BOX, false, false);
		formField = addFormField(form, "mobile", "手机", groupName, ControlTypes.TEXT_BOX, true, false);{
			
			formField.setTroikaTrigger("controllernCustomer.contactWayChange(this);");
			formField.setTroikaValidation("validationContactWay['mobile','手机']");
		}
		formField = addFormField(form, "telephone", "座机", groupName, ControlTypes.TEXT_BOX, true, false);{
			
			formField.setTroikaTrigger("controllernCustomer.contactWayChange(this);");
			formField.setTroikaValidation("validationContactWay['telephone','座机']");
		}
		formField = addFormField(form, "qq", "QQ", groupName, ControlTypes.TEXT_BOX, true, false);{
			
			formField.setTroikaTrigger("controllernCustomer.contactWayChange(this);");
			formField.setTroikaValidation("validationContactWay['weixin','微信']");
		}
		formField = addFormField(form, "weixin", "微信", groupName, ControlTypes.TEXT_BOX, true, false);{
			
			formField.setTroikaTrigger("controllernCustomer.contactWayChange(this);");
			formField.setTroikaValidation("validationContactWay['qq','QQ']");
		}
		addFormField(form, "email", "邮箱", groupName, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "birdthday", "生日", groupName, ControlTypes.DATE_BOX, false, false);
		addFormField(form, "important", "重要程度", groupName, ControlTypes.ENUM_BOX, false, false);
		formField = addFormField(form, "province.name", "省份",groupName, ControlTypes.CUSTOM, false, false);
		{
			formField.setCustomControlType(CityComboBox.class.getName());
			formField.setDataOptions("level:1,changeCtrlId:'city_name'");
		}
		formField = addFormField(form, "city.name", "城市", groupName,ControlTypes.CUSTOM, false, false);
		{
			formField.setCustomControlType(CityComboBox.class.getName());
			formField.setDataOptions("level:2,changeCtrlId:'county_name'");
		}

		formField = addFormField(form, "county.name", "区/县", groupName,ControlTypes.CUSTOM, false, false);
		{
			formField.setCustomControlType(CityComboBox.class.getName());
			formField.setDataOptions("level:3");
		}

		formField = addFormField(form, "remark", "备注", groupName, ControlTypes.TEXTAREA, false, false);{
			formField.setFullColumn(true);
			formField.setHeight(100);
		}
		
		return form;
	}

	protected void addDetailGridPart(PWorkspace workspace) {

		// 客户任务
		createTasksPart(workspace);
	}

	// 客户任务
	public void createTasksPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(taskDetailResourceNodeCode);
		PDatagrid datagrid = new PDatagrid(node, "任务信息");
		{
			datagrid.setShowCheckbox(false);
			datagrid.setSingleSelect(true);
			datagrid.setReadOnly(true);
			datagrid.setShowTitle(true);
			datagrid.setName("任务信息");
			PDatagridColumn column = addColumn(datagrid, "taskType", "类型", ControlTypes.ENUM_BOX, 100, false);{
				
				String formatter = EnumUtil.getColumnFormatter(TaskCustomerType.class);
				column.setFormatter(formatter);
			}
			addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200, false);
			addColumn(datagrid, "supplier.name", "分配服务商", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "department.name", "分配部门", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "owner.name", "分配业务员", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "foolowStatus", "跟进状态", ControlTypes.ENUM_BOX, 100, false);
			addColumn(datagrid, "remark", "售前备注", ControlTypes.TEXT_BOX, 300, false);
			addColumn(datagrid, "smsRemark", "短信备注", ControlTypes.TEXT_BOX, 300, false);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("任务信息");
			part.setCode("tasks");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("tasks");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setJsController(taskDetailJsController);
		}
		workspace.getParts().add(part);
		
		part = workspace.getParts().get(0);
		{
			part.setName("新增客户");
			part.setDockStyle(DockType.TOP);
			part.setHeight(500);
		}
	}

	// 默认的表单操作
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
	}
}
