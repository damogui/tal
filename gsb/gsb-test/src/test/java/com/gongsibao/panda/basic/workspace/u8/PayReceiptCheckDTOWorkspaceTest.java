package com.gongsibao.panda.basic.workspace.u8;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.dto.PayReceiptCheckDTO;

public class PayReceiptCheckDTOWorkspaceTest extends WorkspaceCreationBase  {

	@Before
	public void setup() {

		entity = PayReceiptCheckDTO.class;//实体
		urlList = "/basic/u8/receiptcheck/list";//列表的url
		urlForm = "/basic/u8/receiptcheck/form";//弹出框的url
		listPartName = formPartName = "账套信息";
		meta = MtableManager.getMtable(entity);//获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Basic_U8_" + PayReceiptCheckDTO.class.getSimpleName();//菜单节点码（名称）
		
		formOpenMode = OpenMode.WINDOW;//编辑框打开的形式
		openWindowHeight=400;
		openWindowWidth=800;
	}

	//默认的grid信息的配置
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setName("回单核对");
		}
		
		PDatagridColumn column = null;
		addColumn(datagrid, "id", "支付编号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "receiptNo", "回单编号", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "orderId", "订单id", ControlTypes.TEXT_BOX, 100);{
			column.setVisible(false);
		}
        addColumn(datagrid, "orderNo", "订单号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "payablePrice", "订单金额", ControlTypes.DECIMAL_BOX, 100);
		addColumn(datagrid, "paidPrice", "订单已支付金额", ControlTypes.DECIMAL_BOX, 100);
		addColumn(datagrid, "amount", "支付金额", ControlTypes.DECIMAL_BOX, 100);
		addColumn(datagrid, "bookName", "付款账套", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "bankName", "支付方式", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "receiptStatus", "回单处理状态", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "addTime", "订单创建日期", ControlTypes.DATETIME_BOX, 100);
		addColumn(datagrid, "returnTime", "回款日期", ControlTypes.DATETIME_BOX, 100);
		return datagrid;
	}
	
	//自定义工具栏
	public void fromToolbar() {

			ResourceNode node = this.resourceService.byCode(resourceNodeCode);
			OperationType ot1 = operationTypeService.byCode(OperationTypes.add);
			OperationType otAdd = operationTypeService.byCode(OperationTypes.add);
			OperationType otUpdate = operationTypeService.byCode(OperationTypes.update);
			PToolbar toolbar = new PToolbar();
			{
				toolbar.toNew();
				//toolbar.setBasePath("panda/form/edit");
				toolbar.setPath(this.formToolbarPath);
				toolbar.setName("供应商表单");
				toolbar.setResourceNode(node);
				toolbar.setToolbarType(ToolbarType.BASE);
			}

			PToolbarItem item = new PToolbarItem();
			{
				item.toNew();
				item.setCode("follow");
				item.setIcon("fa fa-mail-reply-all");
				item.setName("跟进");
				item.setCommand(null);
				item.setOperationType(ot1);
				item.setSeq(5000);
				item.setCommand("{controller}.follow();");
				toolbar.getItems().add(item);
			}

			toolbarService.save(toolbar);
		}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "orderNo", "订单号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "receiptNo", "回单编号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "id", "支付编号", ControlTypes.NUMBER_BOX);
		addQueryItem(queryProject, "receiptStatus", "回单处理状态", ControlTypes.ENUM_BOX);
		return queryProject;
	}
	//默认的表单配置信息
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(2);
		/*addFormField(form, "name", "名称", null, ControlTypes.TEXT_BOX, true, false);		
		addFormField(form, "senderNo", "外部系统编号", null, ControlTypes.TEXT_BOX, true, false);*/
		return form;
	}
	
	//默认的表单操作
	@Override
	protected void doOperation() {
		
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
		operationService.addOperation(node,OperationTypes.delete);
	}
}
