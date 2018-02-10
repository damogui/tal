package com.gongsibao.panda.operation.workspace.crm.form;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.controls.CityComboBox;
import com.gongsibao.controls.DictComboBox;
import com.gongsibao.crm.web.NCustomerTaskAddFormPart;
import com.gongsibao.crm.web.TaskProductDetailPart;
import com.gongsibao.entity.crm.NCustomerTask;
import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;

public class TaskAddWorkspaceTest extends WorkspaceCreationBase {

	protected String productsDetailResourceNodeCode = "Operation_CRM_Customer_Products";
	protected boolean showDataGridTitle = true;
	@Before
	public void setup() {
		super.setup();
		entity = NCustomerTask.class;
		urlForm = "/crm/platform/task/add";
		listPartName = formPartName = "新增任务";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "Operation_CRM_Task_Add";
		formJsImport = "/gsb/crm/platform/js/task-add-form.part.js|/gsb/gsb.customer.controls.js";
		formJsController = NCustomerTaskAddFormPart.class.getName();
		formServiceController = NCustomerTaskAddFormPart.class.getName();

		
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
		
		String groupName = null;
		addFormField(form, "taskType", "新/老客户", groupName, ControlTypes.ENUM_BOX, true, false);
		
		formField = addFormField(form, "source.name", "来源", null, ControlTypes.CUSTOM, true, false);
		{
			formField.setCustomControlType(DictComboBox.class.getName());
			formField.setTroikaTrigger("controllernCustomerTask.sourceSelect(newValue,oldValue);");
			formField.setRefFilter("type=411");
		}
		
		formField = addFormField(form, "consultWay.name", "咨询途径", null, ControlTypes.CUSTOM, true, false);
		{
			formField.setCustomControlType(DictComboBox.class.getName());
			formField.setTroikaTrigger("controllernCustomerTask.consultWaySelect(newValue,oldValue);");
			formField.setRefFilter("type=421");
		}
		formField = addFormField(form, "allocationDispositon", "自营/平台", groupName, ControlTypes.ENUM_BOX, true, false);{
			
			formField.setTroikaTrigger("controllernCustomerTask.allocationDispositonChange(newValue,oldValue);");
		}

		addFormField(form, "sourceOther", "其它来源", groupName, ControlTypes.TEXT_BOX, false, true);

		addFormField(form, "consultWayOther", "其它咨询途径", groupName, ControlTypes.TEXT_BOX, false, true);

		formField = addFormField(form, "allocationType", "分配方式", groupName, ControlTypes.ENUM_BOX, true, false);{
			
			formField.setTroikaTrigger("controllernCustomerTask.allocationTypeChange(newValue,oldValue);");
		}

		addFormField(form, "allocationState", "分配状态", groupName, ControlTypes.ENUM_BOX, true, true);
		formField = addFormField(form, "costed", "市场投放费用",groupName,ControlTypes.SWITCH_BUTTON, false, false);{
			formField.setDataOptions("有|无");
			formField.setTroikaTrigger("controllernCustomerTask.costedChange(checked);");
		}
		
		formField = addFormFieldRefrence(form, "supplier.name", "分配服务商", null, Supplier.class.getSimpleName(), false, true);
		{
			formField.setTroikaTrigger("controllernCustomerTask.supplierChange(newValue,oldValue);");
		}
		
		formField = addFormFieldRefrence(form, "department.name", "分配部门", null, SupplierDepartment.class.getSimpleName(), false, true);
		{
			formField.setTroikaTrigger("controllernCustomerTask.departmentChange(newValue,oldValue);");
		}
		
		formField = addFormFieldRefrence(form, "owner.name", "分配业务员", null, Employee.class.getSimpleName(), false, true);

		formField = addFormField(form, "remark", "售前备注", groupName, ControlTypes.TEXTAREA, true, false);{
			formField.setHeight(50);
			formField.setFullColumn(false);
		}
		formField = addFormField(form, "smsRemark", "短信备注", groupName, ControlTypes.TEXTAREA, false, false);{
			formField.setHeight(50);
			formField.setFullColumn(false);
		}
		return form; 
	}

	protected void addDetailGridPart(PWorkspace workspace) {

		createProductsPart(workspace);
	}

	// 意向产品
	public void createProductsPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(productsDetailResourceNodeCode);
		PDatagrid datagrid = new PDatagrid(node, "意向产品");
		{
			datagrid.setShowCheckbox(false);
			datagrid.setSingleSelect(true);
			datagrid.setReadOnly(true);
			
			if(showDataGridTitle){

				datagrid.setShowTitle(true);
				datagrid.setName("意向产品");
			}
			addColumn(datagrid, "productCategory1.name", "一级分类", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "productCategory2.name", "二级分类", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "product.name", "产品", ControlTypes.TEXT_BOX, 200, false);
			addColumn(datagrid, "province.name", "省", ControlTypes.TEXT_BOX, 150, false);
			addColumn(datagrid, "city.name", "市", ControlTypes.TEXT_BOX, 150, false);
			addColumn(datagrid, "county.name", "区", ControlTypes.TEXT_BOX, 150, false);
		}
		
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("意向产品");

			PFormField formField = null;
			formField = addFormField(form, "productCategory1.name", "一级分类", null, ControlTypes.CUSTOM, false, true);
			{
				formField.setWidth(200);
				formField.setCustomControlType(DictComboBox.class.getName());
				formField.setTroikaTrigger("controllerproducts.productCategory1Select(newValue,oldValue);");
				formField.setRefFilter("type=201 and pid=0");
			}

			formField = addFormField(form, "productCategory2.name", "二级分类", null, ControlTypes.CUSTOM, false, true);
			{
				formField.setWidth(200);
				formField.setCustomControlType(DictComboBox.class.getName());
				formField.setTroikaTrigger("controllerproducts.productCategory2Select(newValue,oldValue);");
				formField.setRefFilter("type=201 and pid<>0");
			}

			formField = addFormFieldRefrence(form, "product.name", "产品", null, "CRM_" + Product.class.getSimpleName(), true, false);{
				formField.setWidth(200);
				formField.setRefFilter("enabled=1");
				formField.setTroikaTrigger("controllerproducts.productChange(newValue,oldValue);");
			}
			formField = addFormField(form, "province.name", "省份", ControlTypes.CUSTOM, false, false);
			{
				formField.setWidth(200);
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:1,changeCtrlId:'city_name'");
			}
			formField = addFormField(form, "city.name", "城市", ControlTypes.CUSTOM, false, false);
			{
				formField.setWidth(200);
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:2,changeCtrlId:'county_name'");
			}
			formField = addFormField(form, "county.name", "区/县", ControlTypes.CUSTOM, false, false);
			{
				formField.setWidth(200);
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:3,parentCtrlId:'city_name'");
			}
		}



		PPart part = new PPart();
		{
			part.toNew();
			part.setName("意向产品");
			part.setCode("products");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("products");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			part.setJsController(TaskProductDetailPart.class.getName());
			part.setServiceController(TaskProductDetailPart.class.getName());
			part.setWindowWidth(400);
			part.setWindowHeight(450);
			part.setForm(form);
		}
		
		
		workspace.getParts().add(part);
		
		part = workspace.getParts().get(0);
		{
			part.setName("新增任务");
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
