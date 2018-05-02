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
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.web.OrderProdItemDetailPart;
import com.gongsibao.trade.web.OrderStageDetailPart;
import com.gongsibao.trade.web.SalesmanAddOrderFormPart;

public class SalesmanOrderAddWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {
		super.setup();
		entity = SoOrder.class;
		urlForm = "/crm/order/salesman/add";
		listPartName = formPartName = "新增订单";
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "Gsb_Supplier_Order_Salesman_Add";

		List<String> ss = new ArrayList<String>();
		ss.add("/package/easyui/datagrid-cellediting.js");
		ss.add("/gsb/platform/trade/js/salesman-order-add-form.part.js");
		ss.add("/package/qiniu/plupload.full.min.js");
		ss.add("/gsb/panda-extend/gsb.customer.controls.js");
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
		// addFormFieldRefrence(form, "supplier.name", "服务商", null,
		// Supplier.class.getSimpleName(), false, true);
		// addFormFieldRefrence(form, "department.name", "部门", null,
		// SupplierDepartment.class.getSimpleName(), false, true);
		addFormFieldRefrence(form, "owner.name", "业务员", null, Employee.class.getSimpleName(), false, true);
		addFormField(form, "channelOrderNo", "渠道订单号", groupName, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "platformSource", "订单来源", groupName, ControlTypes.ENUM_BOX, false, false);

		addFormFieldRefrence(form, "companyIntention.companyName", "关联企业", null, CompanyIntention.class.getSimpleName(), false, false);
		formField = addFormField(form, "accountMobile", "手机", groupName, ControlTypes.TEXT_BOX, true, false);
		{
			formField.setTroikaTrigger("controllersoOrder.accountMobileChange(this);");
		}
		addFormField(form, "accountName", "联系人", groupName, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "important", "客户等级", groupName, ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "email", "邮箱", groupName, ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "accountType", "新/老客户", groupName, ControlTypes.ENUM_BOX, true, false);
		formField = addFormField(form, "stageNum", "分期", groupName, ControlTypes.ENUM_BOX, true, false);{
			
			formField.setTroikaTrigger("controllersoOrder.stageNumChange(newValue,oldValue);");
		}
		// addFormField(form, "couponCode", "优惠劵", groupName,
		// ControlTypes.TEXT_BOX, false, false);
		addFormField(form, "totalPrice", "原价金额", groupName, ControlTypes.DECIMAL_FEN_BOX, false, true);
		// addFormField(form, "discountPrice", "优惠金额", groupName,
		// ControlTypes.DECIMAL_FEN_BOX, false, true);
		addFormField(form, "payablePrice", "应付金额", groupName, ControlTypes.DECIMAL_FEN_BOX, false, true);

		formField = addFormField(form, "remark", "备注", groupName, ControlTypes.TEXTAREA, false, false);
		{
			formField.setFullColumn(false);
			formField.setHeight(50);
		}

		return form;
	}

	protected void addDetailGridPart(PWorkspace workspace) {

		createProductPart(workspace);

		createStagePart(workspace);
		
		 createOrderFilePart(workspace);
	}
	
    private void createOrderFilePart(PWorkspace workspace) {

        ResourceNode node = this.resourceService.byCode("Operation_Order_File");
        PDatagrid datagrid = new PDatagrid(node, "合同附件");
        {
            datagrid.setReadOnly(true);
            datagrid.setResourceNode(node);
            datagrid.setShowCheckbox(false);
            PDatagridColumn column = null;
            column = addColumn(datagrid, "url", "操作", ControlTypes.TEXT_BOX, 80);
            {
            	column.setAlign(DatagridAlign.CENTER);
                column.setFormatter("return controllerfiles.urlFormatter(value,row,index);");
            }
            addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 200);
        }
        PPart part = new PPart();
        {
            part.toNew();
            part.setName("上传附件");
            part.setCode("files");
            part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
            part.setRelationRole("files");
            part.setResourceNode(node);
            part.setPartTypeId(PartType.DETAIL_PART.getId());
            part.setDatagrid(datagrid);
            part.setToolbar("contract/file/toolbar");
            part.setDockStyle(DockType.DOCUMENTHOST);
            part.setJsController("com.gongsibao.trade.web.OrderFileDetailPart");
        }
        workspace.getParts().add(part);
    }

	// 客户任务
	public void createProductPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("Gsb_Supplier_Order_Salesman_OrderProd");
		PDatagrid datagrid = new PDatagrid(node, "产品信息");
		{
			datagrid.setShowCheckbox(false);
			datagrid.setSingleSelect(true);
			datagrid.setReadOnly(true);
			datagrid.setShowTitle(false);
			datagrid.setRownumbers(false);
			PDatagridColumn column = null;
			column = addColumn(datagrid, "index", "", ControlTypes.TEXT_BOX, 40);
			{

				column.setAlign(DatagridAlign.CENTER);
				column.setFormatter("return controllerproducts.indexFormatter(value,row,index);");
			}

			column = addColumn(datagrid, "id", "操作", ControlTypes.TEXT_BOX, 60);
			{

				column.setAlign(DatagridAlign.CENTER);
				column.setFormatter("return controllerproducts.operationFormatter(value,row,index);");
			}
			column = addColumn(datagrid, "productName", "产品名称", ControlTypes.TEXT_BOX, 150);
			column = addColumn(datagrid, "quantity", "数量", ControlTypes.NUMBER_BOX, 60);
			{

				column.setAlign(DatagridAlign.CENTER);
			}
			column = addColumn(datagrid, "cityName", "产品地区", ControlTypes.TEXT_BOX, 150);
			column = addColumn(datagrid, "serviceName", "服务名称", ControlTypes.TEXT_BOX, 150);
			{

				column.setFormatter("return controllerproducts.serviceNameFormatter(value,row,index);");
			}
			column = addColumn(datagrid, "unitName", "单位", ControlTypes.TEXT_BOX, 50);
			{

				column.setAlign(DatagridAlign.CENTER);
				column.setFormatter("return controllerproducts.unitNameFormatter(value,row,index);");
			}
			column = addColumn(datagrid, "priceOriginal", "原售价", ControlTypes.NUMBER_BOX, 100);
			{

				column.setFormatter("return controllerproducts.priceOriginalFormatter(value,row,index);");
			}
			column = addColumn(datagrid, "price", "现售价", ControlTypes.NUMBER_BOX, 100);
			{

				column.setFormatter("return controllerproducts.priceFormatter(value,row,index);");
			}

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
			part.setToolbar("order/product/detail");
			part.setServiceController(OrderProdItemDetailPart.class.getName());
			part.setJsController(OrderProdItemDetailPart.class.getName());
		}
		workspace.getParts().add(part);

		part = workspace.getParts().get(0);
		{
			part.setName("新增订单");
			part.setDockStyle(DockType.TOP);
			part.setStyle("height:270px");
		}
	}

	// 客户任务
	public void createStagePart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode("Operation_Order_Stage");
		PDatagrid datagrid = new PDatagrid(node, "分期信息");
		{
			datagrid.setShowCheckbox(false);
			datagrid.setSingleSelect(true);
			datagrid.setReadOnly(true);
			datagrid.setShowTitle(false);
			datagrid.setRownumbers(false);
			PDatagridColumn column = null;
			column = addColumn(datagrid, "instalmentIndex", "分期期数", ControlTypes.TEXT_BOX, 100);
			{
				column.setAlign(DatagridAlign.CENTER);
				column.setFormatter(" return value + '期';");
			}
			column = addColumn(datagrid, "amount", "分期金额", ControlTypes.NUMBER_BOX, 150);
			{
				column.setAlign(DatagridAlign.RIGHT);
				column.setFormatter("  return System.RMB.fenToYuan(value);");
			}

//			column = addColumn(datagrid, "percentage", "分期百分比", ControlTypes.NUMBER_BOX, 100);
//			{
//				column.setAlign(DatagridAlign.RIGHT);
//				column.setFormatter(" return value + '%';");
//			}
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("分期信息");
			part.setCode("stages");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("stages");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setServiceController(OrderStageDetailPart.class.getName());
			part.setJsController(OrderStageDetailPart.class.getName());
		}
		workspace.getParts().add(part);
	}

	@Test
	public void createRowToolbar() {

		ResourceNode node = this.resourceService.byCode(resourceNodeCode);
		PToolbar toolbar = new PToolbar();
		{
			toolbar.toNew();
			toolbar.setPath("order/product/detail");
			toolbar.setName("添加产品");
			toolbar.setResourceNode(node);
			toolbar.setToolbarType(ToolbarType.BASE);
		}
		PToolbarItem item = new PToolbarItem();
		{
			item.toNew();
			item.setCode("add");
			item.setName("添加产品");
			item.setIcon("fa fa-plus fa-fw");
			item.setSeq(1);
			item.setCommand("{controller}.add();");
			toolbar.getItems().add(item);
		}

		toolbarService.save(toolbar);
	}

	// 默认的表单操作
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
	}
}
