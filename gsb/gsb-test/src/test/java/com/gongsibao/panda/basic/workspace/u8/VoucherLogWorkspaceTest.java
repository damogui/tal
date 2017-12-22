package com.gongsibao.panda.basic.workspace.u8;

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
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.u8.SetOfBooks;
import com.gongsibao.entity.u8.U8Bank;
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
			datagrid.setName("凭证日志记录");
		}

		PDatagridColumn column = null;
		column = addColumn(datagrid, "sender", "eai配置系统注册码", ControlTypes.TEXT_BOX, 80);
		{
			column.setVisible(false);
		}
		;
		addColumn(datagrid, "orderNo", "订单编号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "payId", "支付编号", ControlTypes.NUMBER_BOX, 80);
		addColumn(datagrid, "refundId", "退单退款编号", ControlTypes.NUMBER_BOX, 80);
		addColumn(datagrid, "inVoucherLogId", "确认收入凭证源记录Id", ControlTypes.TEXT_BOX, 180);
		addColumn(datagrid, "type", "类型", ControlTypes.ENUM_BOX, 100);
		addColumn(datagrid, "voucherId", "u8生成的凭证号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "dsc", "u8返回信息说明", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "succeed", "成功标识", ControlTypes.TEXT_BOX, 80);
		{
			column.setFormatter("return value=='0'?'成功':'失败'; ");
		}
		addColumn(datagrid, "refundItemId", "退产品单退款编号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "setOfBooks.name", "账套名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "createTime", "添加时间", ControlTypes.DATETIME_BOX, 100);
		// addColumn(datagrid, "xmlParam", "传入的参数", ControlTypes.TEXT_BOX, 100);
		// addColumn(datagrid, "xmlReturn", "返回值", ControlTypes.TEXT_BOX, 100);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "orderNo", "订单编号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "payId", "支付编号", ControlTypes.NUMBER_BOX);
		addQueryItem(queryProject, "refundId", "退单退款编号", ControlTypes.NUMBER_BOX);
		addQueryItem(queryProject, "type", "类型", ControlTypes.ENUM_BOX);
		// 参照
		addRefrenceQueryItem(queryProject, "setOfBooks.name", "账套", SetOfBooks.class.getSimpleName());
		return queryProject;
	}

	// 默认的表单配置信息
	protected PForm createForm(ResourceNode node) {

		PForm form = super.createForm(node);
		form.setColumnCount(1);

		PFormField field = null;

		field = addFormField(form, "xmlParam", "传入的参数", ControlTypes.TEXTAREA, true, false);
		{
			field.setWidth(600);
			field.setHeight(350);
		}
		field = addFormField(form, "xmlReturn", "返回值", ControlTypes.TEXTAREA, false, false);
		{
			field.setWidth(600);
			field.setHeight(350);
		}

		return form;
	}

	// 默认的表单操作
	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}

}
