package com.gongsibao.panda.platform.basic.workspace.u8;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.entity.u8.VoucherLog;

public class VoucherLogWorkspaceTest extends WorkspaceCreationBase {

	@Before
	public void setup() {

		entity = VoucherLog.class;// 实体
		urlList = "/basic/u8/voucherLog/list";// 列表的url
		urlForm = "/basic/u8/voucherLog/form";// 弹出框的url
		listPartName = formPartName = "科目银行信息";
		meta = MtableManager.getMtable(entity);// 获取实体元数据
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Basic_U8_" + VoucherLog.class.getSimpleName();// 菜单节点码（名称）

		formOpenMode = OpenMode.WINDOW;// 编辑框打开的形式
		openWindowWidth = 800;
		openWindowHeight = 650;
	}

	// 默认的grid信息的配置
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.toNew();
			datagrid.setResourceNode(node);
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setName("凭证日志记录");
		}

		PDatagridColumn column = null;
		addColumn(datagrid, "id", "操作", ControlTypes.OPERATION_COLUMN, 80);
		column = addColumn(datagrid, "sender", "eai配置系统注册码", ControlTypes.TEXT_BOX, 80);
		{
			column.setVisible(false);
		}
		column =addColumn(datagrid, "orderNo", "订单编号", ControlTypes.TEXT_BOX, 100);
		{
			column.setVisible(false);
		}
		column = addColumn(datagrid, "ordernoCustname", "订单编号和客户名称", ControlTypes.TEXT_BOX, 280);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "abstractInfo", "摘要", ControlTypes.TEXT_BOX, 300);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "payId", "支付编号", ControlTypes.NUMBER_BOX, 80);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "refundId", "退单退款编号", ControlTypes.NUMBER_BOX, 80);
		{
			column.setImported(true);
		}
		/*column = addColumn(datagrid, "inVoucherLogId", "确认收入凭证源记录Id", ControlTypes.TEXT_BOX, 180);
		{
			column.setImported(true);
		}*/
		column = addColumn(datagrid, "type", "类型", ControlTypes.ENUM_BOX, 100);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "voucherId", "u8生成的凭证号", ControlTypes.TEXT_BOX, 100);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "dsc", "u8返回信息说明", ControlTypes.TEXT_BOX, 260);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "succeed", "成功标识", ControlTypes.TEXT_BOX, 80);
		{
			column.setFormatter("return value=='0'?'成功':'失败'; ");
			column.setImported(true);
		}
		column = addColumn(datagrid, "refundItemId", "退产品单退款编号", ControlTypes.TEXT_BOX, 100);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "setOfBooks.name", "账套名称", ControlTypes.TEXT_BOX, 200);
		{
			column.setImported(true);
		}
		column = addColumn(datagrid, "createTime", "添加时间", ControlTypes.DATETIME_BOX, 100);
		{
			column.setImported(true);
		}
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "ordernoCustname", "订单编号和客户名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "payId", "支付编号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "refundId", "退单退款编号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "voucherId", "u8生成凭证号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "type", "类型", ControlTypes.ENUM_BOX);
		addQueryItem(queryProject, "dsc", "u8返回信息说明", ControlTypes.TEXT_BOX);
		// 参照
		addRefrenceQueryItem(queryProject, "setOfBooks.name", "账套", SetOfBooks.class.getSimpleName());
		addQueryItem(queryProject, "createTime", "添加时间", ControlTypes.DATE_BOX);
		return queryProject;
	}

	// 默认的表单配置信息
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(1);

		PFormField field = null;

		field = addFormField(form, "xmlParam", "传入的参数", ControlTypes.TEXTAREA, true, false);
		{

			field.setHeight(100);
			field.setFullColumn(true);
		}
		field = addFormField(form, "xmlReturn", "返回值", ControlTypes.TEXTAREA, false, false);
		{

			field.setHeight(50);
			field.setFullColumn(true);
		}

		return form;
	}

	// 默认的表单操作
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.exportExcel);
	}

}
