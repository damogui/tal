package com.gongsibao.panda.supplier.order.workspace.salesman;

import java.util.ArrayList;
import java.util.List;

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
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import com.gongsibao.controls.DictComboBox;
import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.web.SalesmanAddOrderFormPart;

public class SalesmanOrderAddWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {
		super.setup();
		entity = SoOrder.class;
		urlForm = "/crm/order/salesman/add";
		listPartName = formPartName = "新增订单";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "Gsb_Supplier_Order_Salesman_Add";

		List<String> ss = new ArrayList<String>();
		ss.add("/gsb/trade/js/salesman-order-add-form.part.js");
		ss.add("/gsb/gsb.customer.controls.js");
		formJsImport = StringManager.join("|", ss);
		formJsController = SalesmanAddOrderFormPart.class.getName();
		formServiceController = SalesmanAddOrderFormPart.class.getName();
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
		addFormFieldRefrence(form, "supplier.name", "服务商", null, Supplier.class.getSimpleName(), false, true);
		addFormFieldRefrence(form, "department.name", "部门", null, SupplierDepartment.class.getSimpleName(), false, true);
		addFormFieldRefrence(form, "owner.name", "业务员", null, Employee.class.getSimpleName(), false, true);
		addFormField(form, "channelOrderNo", "渠道订单号", groupName, ControlTypes.TEXT_BOX, false, false);
		formField = addFormField(form, "platformSourceDict.name", "订单来源", groupName, ControlTypes.CUSTOM, false, false);
		{
			formField.setCustomControlType(DictComboBox.class.getName());
			formField.setRefFilter("type=421");
		}
		addFormFieldRefrence(form, "companyIntention.companyName", "关联企业", null, CompanyIntention.class.getSimpleName(), false, false);
		formField = addFormField(form, "accountMobile", "手机", groupName, ControlTypes.TEXT_BOX, true, false);
		{
			formField.setTroikaTrigger("controllersoOrder.accountMobileChange(this);");
		}
		addFormField(form, "accountName", "联系人", groupName, ControlTypes.TEXT_BOX, false, true);
		// addFormField(form, "email", "客户等级", groupName, ControlTypes.TEXT_BOX,
		// false, true);
		addFormField(form, "email", "邮箱", groupName, ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "accountType", "新/老客户", groupName, ControlTypes.ENUM_BOX, true, false);
		addFormField(form, "couponCode", "优惠劵", groupName, ControlTypes.TEXT_BOX, false, false);
		formField = addFormField(form, "remark", "备注", groupName, ControlTypes.TEXTAREA, false, false);
		{
			formField.setFullColumn(true);
			formField.setHeight(50);
		}

		return form;
	}

	protected void addDetailGridPart(PWorkspace workspace) {

		// 客户任务
		createTasksPart(workspace);
	}

	// 客户任务
	public void createTasksPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("Gsb_Supplier_Order_Salesman_OrderProd");
		PDatagrid datagrid = new PDatagrid(node, "产品信息");
		{
			datagrid.setShowCheckbox(false);
			datagrid.setSingleSelect(true);
			datagrid.setReadOnly(true);
			datagrid.setShowTitle(true);
			PDatagridColumn column = null;
			addColumn(datagrid, "productName", "产品名称", ControlTypes.DATETIME_BOX, 200);
			addColumn(datagrid, "city.name", "地区", ControlTypes.TEXT_BOX, 100, false);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("产品信息");
			part.setCode("products");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("products");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
		}
		workspace.getParts().add(part);

		part = workspace.getParts().get(0);
		{
			part.setName("新增订单");
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
