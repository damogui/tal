package com.gongsibao.panda.platform.trade.workspace.order;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.DockType;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.entity.PReference;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.ReflectManager;

import com.gongsibao.entity.bd.Dict;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.OrderProdItem;

/**   
 * @ClassName:  OrderPoolWorkspaceTest   
 * @Description:TODO 订单池
 * @author: 韩伟
 * @date:   2017年12月7日 下午8:11:17   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class OrderPoolWorkspaceTest  extends WorkspaceCreationBase{

	@Before
	public void setup() {
		entity = OrderProd.class;
		urlList = "/trade/manage/order/pool/list";
		urlForm = "/trade/manage/order/pool/form";
		listPartName = formPartName = "订单明细";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Trade_Manage_Order_Pool";
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		
		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;
		column = addColumn(datagrid, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, 100,true);
		column = addColumn(datagrid, "soOrder.account.name", "客户创建人", ControlTypes.TEXT_BOX, 100,true);
		column = addColumn(datagrid, "id", "明细编号", ControlTypes.TEXT_BOX, 100,true);
		column = addColumn(datagrid, "productName", "产品名称", ControlTypes.TEXT_BOX, 100,true);
		column = addColumn(datagrid, "soOrder.processStatus.name", "订单状态", ControlTypes.TEXT_BOX, 80);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		column = addColumn(datagrid, "city.name", "产品地区", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "soOrder.companyIntention.name", "明细订单公司", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "companyIntention.name", "订单关联公司", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "soOrder.platformSourceDict.name", "订单来源", ControlTypes.NUMBER_BOX, 90);		
		column = addColumn(datagrid, "soOrder.addUser.name", "下单人", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "soOrder.accountMobile", "下单人电话", ControlTypes.NUMBER_BOX, 90);{
			column.setFormatter("if(value&&value.length==11){return value.substr(0,3)+'****'+value.substr(7);}");
		}
		column = addColumn(datagrid, "soOrder.prodName", "服务名称", ControlTypes.TEXT_BOX, 100);
		{
			column.setFormatter("return '<span title='+value+'>'+value+'</span>'");
		}
		column = addColumn(datagrid, "isRefund", "是否退单", ControlTypes.NUMBER_BOX, 90);{
			column.setFormatter("return value==0?'否':'是'");
		}
		
		column = addColumn(datagrid, "price", "价格", ControlTypes.DECIMAL_FEN_BOX, 90);	
		column = addColumn(datagrid, "priceOriginal", "原价", ControlTypes.DECIMAL_FEN_BOX, 90);	
		column = addColumn(datagrid, "settlePrice", "结算价格", ControlTypes.DECIMAL_FEN_BOX, 90);	
		column = addColumn(datagrid, "settleTime", "结算时间", ControlTypes.DATE_BOX, 90);	
		
		column = addColumn(datagrid, "isComplaint", "客户抱怨", ControlTypes.CHECK_BOX, 90);	
		column = addColumn(datagrid, "isAssign", "已分配", ControlTypes.CHECK_BOX, 90);	
		column = addColumn(datagrid, "processedDays", "已处理天数", ControlTypes.NUMBER_BOX, 90);	
		column = addColumn(datagrid, "needDays", "待处理天数", ControlTypes.NUMBER_BOX, 90);	
		column = addColumn(datagrid, "timeoutDays", "过期时间", ControlTypes.NUMBER_BOX, 90);	
		
		column = addColumn(datagrid, "invoiceTitle", "发票抬头", ControlTypes.TEXT_BOX, 90);
		column = addColumn(datagrid, "applyNo", "支付账号", ControlTypes.TEXT_BOX, 90);	
				
		column = addColumn(datagrid, "createTime", "下单时间", ControlTypes.DATE_BOX, 90);
		column = addColumn(datagrid, "soOrder.payTime", "支付时间", ControlTypes.DATE_BOX, 90);
		//通过left多个表关联,目前随便添加的
		column = addColumn(datagrid, "soOrder.accountName", "业务员", ControlTypes.NUMBER_BOX, 90);
		
		return datagrid;
	}
	
	@Override
	protected PForm createForm(ResourceNode node) {

		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(3);
			form.setReadOnly(true);
		}

		PFormField formField = null;

	
		formField = addFormField(form, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX, false,true);
		formField = addFormField(form, "soOrder.account.name", "客户创建人", ControlTypes.TEXT_BOX, false,true);
		formField = addFormField(form, "id", "明细编号", ControlTypes.TEXT_BOX, false,true);
		formField = addFormField(form, "productName", "产品名称", ControlTypes.TEXT_BOX, false,true);
		formField = addFormField(form, "soOrder.processStatus.name", "订单状态", ControlTypes.TEXT_BOX, false,true);
		formField = addFormField(form, "city.name", "产品地区", ControlTypes.NUMBER_BOX, false,true);
		formField = addFormField(form, "soOrder.companyIntention.name", "明细订单公司", ControlTypes.NUMBER_BOX, false,true);
		formField = addFormField(form, "companyIntention.name", "订单关联公司", ControlTypes.NUMBER_BOX, false,true);
		formField = addFormField(form, "soOrder.platformSourceDict.name", "订单来源", ControlTypes.NUMBER_BOX, false,true);		
		formField = addFormField(form, "soOrder.addUser.name", "下单人", ControlTypes.NUMBER_BOX, false,true);
		formField = addFormField(form, "soOrder.accountMobile", "下单人电话", ControlTypes.NUMBER_BOX, false,true);
		formField = addFormField(form, "soOrder.prodName", "服务名称", ControlTypes.TEXT_BOX, false,true);
		formField = addFormField(form, "isRefund", "是否退单", ControlTypes.NUMBER_BOX, false,true);
		formField = addFormField(form, "price", "价格", ControlTypes.DECIMAL_FEN_BOX, false,true);	
		formField = addFormField(form, "priceOriginal", "原价", ControlTypes.DECIMAL_FEN_BOX, false,true);	
		formField = addFormField(form, "settlePrice", "结算价格", ControlTypes.DECIMAL_FEN_BOX, false,true);	
		formField = addFormField(form, "settleTime", "结算时间", ControlTypes.DATE_BOX, false,true);
		formField = addFormField(form, "isComplaint", "客户抱怨", ControlTypes.CHECK_BOX, false,true);	
		formField = addFormField(form, "isAssign", "已分配", ControlTypes.CHECK_BOX, false,true);	
		formField = addFormField(form, "processedDays", "已处理天数", ControlTypes.NUMBER_BOX, false,true);	
		formField = addFormField(form, "needDays", "待处理天数", ControlTypes.NUMBER_BOX, false,true);	
		formField = addFormField(form, "timeoutDays", "过期时间", ControlTypes.NUMBER_BOX, false,true);
		formField = addFormField(form, "invoiceTitle", "发票抬头", ControlTypes.TEXT_BOX, false,true);
		formField = addFormField(form, "applyNo", "支付账号", ControlTypes.TEXT_BOX, false,true);	
		formField = addFormField(form, "createTime", "下单时间", ControlTypes.DATE_BOX, false,true);
		formField = addFormField(form, "soOrder.payTime", "支付时间", ControlTypes.DATE_BOX, false,true);
		formField = addFormField(form, "soOrder.accountName", "业务员", ControlTypes.NUMBER_BOX, false,true);
		
		
		return form;
	}
	
	protected void addDetailGridPart(PWorkspace workspace) {

		createOrderProdItemDetailPart(workspace);
//		createCompanysDetailPart(workspace);
//		//createOrderDetailPart(workspace);
//		createFlowDetailPart(workspace);
	}
	
	//表单的服务明细字表
	private void createOrderProdItemDetailPart(PWorkspace workspace) {

		ResourceNode node = this.resourceService.byCode(OrderProdItem.class.getSimpleName());
		PDatagrid datagrid = new PDatagrid(node, "服务明细");
		{
			datagrid.setReadOnly(true);
			datagrid.setResourceNode(node);
			addColumn(datagrid, "unitName", "单位名称", ControlTypes.TEXT_BOX, 300);
			addColumn(datagrid, "serviceName", "服务名称", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "quantity", "数量", ControlTypes.TEXT_BOX, 150);
			addColumn(datagrid, "price", "单价", ControlTypes.DECIMAL_FEN_BOX, 150);
			addColumn(datagrid, "priceOriginal", "原价", ControlTypes.DECIMAL_FEN_BOX, 150);
			addColumn(datagrid, "priceRefund", "已退款价", ControlTypes.DECIMAL_FEN_BOX, 150);
		}

		PPart part = new PPart();
		{
			part.toNew();
			part.setName("服务明细");
			part.setCode("items");
			part.setParentCode(ReflectManager.getFieldName(meta.getCode()));
			part.setRelationRole("items");
			part.setResourceNode(node);
			part.setPartTypeId(PartType.DETAIL_PART.getId());
			part.setDatagrid(datagrid);
			part.setDockStyle(DockType.DOCUMENTHOST);
			part.setToolbar("panda/datagrid/detail");
//			part.setJsController("com.gongsibao.crm.web.ProdMapDetailPart");
			part.setWindowWidth(550);
			part.setWindowHeight(350);
//			part.setForm(form);
		}
		workspace.getParts().add(part);

		part = workspace.getParts().get(0);
		{
			part.setName("基本信息");
			part.setStyle("height:500px;");
			part.setDockStyle(DockType.DOCUMENTHOST);
		}
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		
		addQueryItem(queryProject, "soOrder.no", "订单编号", ControlTypes.TEXT_BOX);
		PQueryItem item = addQueryItem(queryProject, "city.name", "城市", ControlTypes.REFERENCE_BOX);
		{
			PReference ref = referenceService.byCode(Dict.class.getSimpleName());
			item.setReference(ref);
		}
		
		addQueryItem(queryProject, "accountName", "客户名称", ControlTypes.TEXT_BOX);
		
		addQueryItem(queryProject, "createTime", "下单时间", ControlTypes.DATE_BOX);
		addQueryItem(queryProject, "prodName", "产品名称", ControlTypes.TEXT_BOX);

//		addQueryItem(queryProject, "payTime", "支付时间", ControlTypes.DATE_BOX);
		
		return queryProject;
	}

	@Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
	}
}
