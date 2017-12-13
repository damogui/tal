package com.gongsibao.panda.ma.workspace.order;

import org.junit.Before;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.base.IOperationService;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.Employee;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.ma.MaOrder;
import com.gongsibao.ma.web.MaOrderFormPart;

public class MaOrderWorkspaceTest extends WorkspaceCreationBase{
	@Override
	@Before
	public void setup() {
		
		urlList = "/ma/order/list";
		urlForm = "/ma/order/form";
		entity = MaOrder.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName =  "订单管理";
		resourceNodeCode = MaOrder.class.getSimpleName();
		
		formServiceController = MaOrderFormPart.class.getName();
		formJsController = MaOrderFormPart.class.getName();
		formJsImport = "/gsb/ma/js/order.form.part.js";
	}
	
	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(3);
		}

		PFormField formField = null;

		String groupName = null;
		addFormField(form, "code", "订单编号",groupName,ControlTypes.TEXT_BOX, false, true);
		addFormField(form, "companyName", "公司名称",groupName, ControlTypes.TEXT_BOX, false, true);
		addFormFieldRefrence(form, "seller.name", "出售业务员", null, Employee.class.getSimpleName(), true, true);
		addFormFieldRefrence(form, "buyer.name", "收购业务员", null, Employee.class.getSimpleName(), true, true);
		addFormField(form, "cost", "成本",groupName,ControlTypes.CURRENCY_BOX, false, false);
		formField = addFormField(form, "signPrice", "签单价",groupName,ControlTypes.CURRENCY_BOX, false, false);{
			
			formField.setTroikaTrigger("controllermaOrder.signPriceeChange(newValue,oldValue);");
		}
		formField = addFormField(form, "hasExpense", "有招待费",groupName,ControlTypes.SWITCH_BUTTON, false, false);{
			
			formField.setTroikaTrigger("controllermaOrder.hasExpenseChange(checked);");
		}
		formField = addFormField(form, "expense", "招待费",groupName,ControlTypes.CURRENCY_BOX, true, true);{
			
			formField.setTroikaTrigger("controllermaOrder.expenseChange(newValue,oldValue);");
		}
		addFormField(form, "hasPenalty", "有违约金",groupName,ControlTypes.SWITCH_BUTTON, false, false);
		addFormField(form, "performance", "业绩额",groupName,ControlTypes.CURRENCY_BOX, false, true);
		addFormField(form, "signDate", "签单时间",groupName,ControlTypes.DATE_BOX, false, false);
		addFormField(form, "consignmentSellAgreement", "委托出售协议",groupName,ControlTypes.PICTURE_FILE_BOX, false, false);
		addFormField(form, "consignmentAcquisitionsAgreement", "委托收购协议",groupName,ControlTypes.PICTURE_FILE_BOX, false, false);
		addFormField(form, "equityTransferAgreement", "股权转让协议",groupName,ControlTypes.PICTURE_FILE_BOX, false, false);
		addFormField(form, "depositReceipt", "订金收据",groupName,ControlTypes.PICTURE_FILE_BOX, false, false);
		addFormField(form, "downPaymentReceipt", "首款收据",groupName,ControlTypes.PICTURE_FILE_BOX, false, false);
		addFormField(form, "finalPaymentReceipt", "尾款收据",groupName,ControlTypes.PICTURE_FILE_BOX, false, false);
		addFormField(form, "finishDate", "完成时间",groupName,ControlTypes.DATE_BOX, true, false);
		addFormField(form, "handoverDate", "交接时间",groupName,ControlTypes.DATE_BOX, false, false);
		addFormFieldRefrence(form, "handover.name", "交接人", null, Employee.class.getSimpleName(), false, false);
		formField = addFormField(form, "state", "订单状态",groupName,ControlTypes.ENUM_BOX, false, false);{
			
			formField.setTroikaTrigger("controllermaOrder.stateChange(newValue,oldValue);");
		}
		addFormField(form, "finishVoucher", "完成凭证",groupName,ControlTypes.PICTURE_FILE_BOX, true, false);
		addFormField(form, "memoto", "备注",groupName,ControlTypes.TEXTAREA, false, false);
		
		return form;
	}
	
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setToolbar("panda/datagrid/row/edit");
		//PDatagridColumn column = null;
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 150, true);
		addColumn(datagrid, "code", "编号", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "companyName", "企业名称", ControlTypes.TEXT_BOX, 150, true);
		addColumn(datagrid, "sellingDemand.name", "出售人", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "sellingDemand.mobile", "出售电话", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "acquisitionDemand.name", "收购人", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "acquisitionDemand.mobile", "收购电话", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "buyer.name", "业务人员", ControlTypes.TEXT_BOX, 80);
		addColumn(datagrid, "state", "订单状态", ControlTypes.ENUM_BOX, 100);
		return datagrid;
	}
	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "companyName", "企业名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "sellingDemand.name", "出售人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "sellingDemand.mobile", "出售电话", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "acquisitionDemand.name", "出售人", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "acquisitionDemand.mobile", "出售电话", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "buyer.name", "业务人员", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "state", "订单状态", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "groupLeaderAuditState", "组长审核状态", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "vpAuditState", "VP审核状态", ControlTypes.ENUM_BOX);
		return queryProject;
	}
	
	@Override
	protected void doOperation() {

		ResourceNode node = resourceService.byCode(entity.getSimpleName());
		IOperationService service = ServiceFactory.create(IOperationService.class);
		service.addOperation(node, OperationTypes.view);
		service.addOperation(node, OperationTypes.update);
		service.addOperation(node, OperationTypes.delete);
	}
}
