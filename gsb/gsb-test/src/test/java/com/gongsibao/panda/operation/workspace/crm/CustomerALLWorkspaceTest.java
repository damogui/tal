package com.gongsibao.panda.operation.workspace.crm;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.controls.CityComboBox;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.product.Product;
import com.gongsibao.entity.supplier.FunctionModule;

public class CustomerALLWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		entity = NCustomer.class;// 实体
		urlList = "/operation/customer/all/list";// 列表的url
		urlForm = "/operation/customer/all/form";// 弹出框的url
		listPartName = formPartName = "全部客户";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_CRM_Customer_Manager_ALL";
		formJsImport = "/gsb/gsb.customer.controls.js";
		formOpenMode = OpenMode.OPEN;// 编辑框打开的形式
	}

	// 默认的grid信息的配置
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("全部客户列表");
		}

		PDatagridColumn column = null;
		addColumn(datagrid, "realName", "名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "sex", "性别", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "mobile", "手机号码", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "email", "邮箱", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "qq", "QQ", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "weixin", "微信", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "province.name", "省份", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "city.name", "城市", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "county.name", "区/县", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "ConsultWay", "CRM咨询途径", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "important", "重要程度", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "intentionCategory", "质量分类", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "lastFollowTime", "最近跟进时间", ControlTypes.DATETIME_BOX, 20);
		addColumn(datagrid, "lastFoolowUser.name", "最后跟进人", ControlTypes.DATETIME_BOX, 100);
		addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATETIME_BOX, 20);
		addColumn(datagrid, "backNum", "退回次数", ControlTypes.NUMBER_BOX, 100);
		addColumn(datagrid, "customerSource", "客户来源", ControlTypes.ENUM_BOX, 80);
		addColumn(datagrid, "creator", "添加人", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "createTime", "添加时间", ControlTypes.DATETIME_BOX, 20);
		return datagrid;
	}

	// 默认的表单配置信息
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(2);
		PFormField formField = null;
		addFormField(form, "realName", "名称", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "sex", "性别", null, ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "mobile", "手机号码", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "email", "邮箱", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "qq", "QQ", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "weixin", "微信", null, ControlTypes.TEXT_BOX, true, false);

		formField = addFormField(form, "province.name", "省份", ControlTypes.CUSTOM, false, false);
		{
			formField.setCustomControlType(CityComboBox.class.getName());
			formField.setDataOptions("level:1,changeCtrlId:'city_name'");
			formField.setWidth(300);
		}
		formField = addFormField(form, "city.name", "城市", ControlTypes.CUSTOM, false, false);
		{
			formField.setCustomControlType(CityComboBox.class.getName());
			formField.setDataOptions("level:2,changeCtrlId:'county_name'");
			formField.setWidth(300);
		}
		// 自定义控件(公用的，里面有一些逻辑)
		formField = addFormField(form, "county.name", "区/县", ControlTypes.CUSTOM, false, false);
		{
			formField.setCustomControlType(CityComboBox.class.getName());
			formField.setDataOptions("level:3");
			formField.setWidth(300);
		}

		addFormField(form, "ConsultWay", "CRM咨询途径", null, ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "important", "重要程度", null, ControlTypes.ENUM_BOX, true, false);
		// addFormField(form, "SupplierDepartment", "分配服务商部门", null,
		// ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "intentionCategory", "质量分类", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "lastFollowTime", "最近跟进时间", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "lastFoolowUser.name", "最后跟进人", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "qq", "QQ", null, ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "lastContent", "最后跟进内容", null, ControlTypes.TEXT_BOX, true, true);
		addFormField(form, "nextFoolowTime", "下次跟进时间", null, ControlTypes.DATETIME_BOX, false, true);
		addFormField(form, "backNum", "退回次数", null, ControlTypes.NUMBER_BOX, true, true);
		addFormField(form, "customerSource", "客户来源", null, ControlTypes.ENUM_BOX, true, false);
		return form;
	}

	protected void addDetailGridPart(PWorkspace workspace) {
		// 客户任务
		createTasksPart(workspace);
		// 意向产品
		addIntenProductPart(workspace);
	}

	// 客户任务
	private void createTasksPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("GSB_CRM_MY_TASK_ALL");
		PDatagrid datagrid = new PDatagrid(node, "客户任务信息");
		{
			addColumn(datagrid, "customer.realName", "客户", ControlTypes.TEXT_BOX, 100, true);
			addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 100, true);
			addColumn(datagrid, "supplier.name", "分配服务商", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "department.name", "分配服务商部门", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "lastFollowTime", "最近跟进时间", ControlTypes.DATE_BOX, 100, false);
			addColumn(datagrid, "foolowStatus", "跟进状态", ControlTypes.ENUM_BOX, 100, false);
			addColumn(datagrid, "intentionCategory", "质量分类", ControlTypes.ENUM_BOX, 100, false);
			addColumn(datagrid, "nextFoolowTime", "下次跟进时间", ControlTypes.DATE_BOX, 100, false);
			addColumn(datagrid, "lastFoolowUser.name", "最后跟进人", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "lastContent", "最后跟进内容", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "old", "是否老客户", ControlTypes.TEXT_BOX, 100, false);
			addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 100, false);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("客户任务信息");
			part.setCode("tasks");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("tasks");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			//part.setToolbar("panda/datagrid/detail");
		}
		workspace.getParts().add(part);

		part = workspace.getParts().get(0);
		{
			part.setName("客户任务信息");
			part.setDockStyle(DockType.TOP);
		}
	}

	// 服务范围
	private void addIntenProductPart(PWorkspace workspace) {
		// 需要配置NCustomerProduct资源
		ResourceNode node = this.resourceService.byCode("GSB_Operation_Supplier_Service_Scope");
		PDatagrid datagrid = new PDatagrid(node, "意向产品");
		{
			addColumn(datagrid, "product.name", "产品", ControlTypes.TEXT_BOX, 300);
			addColumn(datagrid, "province.name", "省份", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "city.name", "城市", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "county.name", "区/县", ControlTypes.TEXT_BOX, 150);
		}
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setColumnCount(1);
			form.setName("意向产品");

			PFormField formField = null;
			formField = addFormFieldRefrence(form, "product.name", "意向产品", null, "CRM_" + Product.class.getSimpleName(), true, false);
			/*
			 * { formField.setTroikaTrigger(
			 * "controllerproducts.productChange(newValue,oldValue);");
			 * formField.setWidth(300); }
			 */
			formField = addFormField(form, "province.name", "省份", ControlTypes.CUSTOM, false, false);
			{
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:1,changeCtrlId:'city_name'");
				formField.setWidth(300);
			}
			formField = addFormField(form, "city.name", "城市", ControlTypes.CUSTOM, false, false);
			{
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:2,changeCtrlId:'county_name'");
				formField.setWidth(300);
			}
			// 自定义控件(公用的，里面有一些逻辑)
			formField = addFormField(form, "county.name", "区/县", ControlTypes.CUSTOM, false, false);
			{
				formField.setCustomControlType(CityComboBox.class.getName());
				formField.setDataOptions("level:3");
				formField.setWidth(300);
			}
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("意向产品");
			part.setCode("serviceScopes");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("serviceScopes");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
			// part.setJsController("com.gongsibao.crm.web.ProdMapDetailPart");
			part.setWindowWidth(550);
			part.setWindowHeight(350);
			part.setForm(form);
		}
		workspace.getParts().add(part);

		part = workspace.getParts().get(0);
		{
			part.setName("基本信息");
			part.setStyle("height:500px;");
			part.setDockStyle(DockType.TOP);
		}
	}

	// 默认的表单操作
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
		operationService.addOperation(node, OperationTypes.delete);
	}
}
