package com.gongsibao.panda.platform.trade.workspace.order;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.tools.PToolbarHelper;
import com.gongsibao.trade.web.OrderAllListPart;
import com.gongsibao.trade.web.OrderOperationController;

/**   
 * @ClassName:  AllOrderWorkspaceTest   
 * @Description:TODO 全部订单
 * @author: 韩伟
 * @date:   2017年12月7日 下午8:11:52   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
/*交易中心全部订单*/
public class AllOrderWorkspaceTest extends WorkspaceCreationBase{

	@Before
	public void setup() {
		entity = SoOrder.class;
		urlList = "/trade/manage/order/all/list";
		listPartName = formPartName = "全部订单";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "GSB_Trade_Manage_All_Order";
		listToolbarPath = "/crm/order/all/edit";
		listPartImportJs = "/gsb/platform/trade/js/salesman-order-all-list.part.js|/gsb/platform/trade/js/order-all-list-part.js";
		listPartJsController = OrderAllListPart.class.getName();
		listPartServiceController = OrderAllListPart.class.getName();
	}
	
    public PToolbar createListToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listToolbarPath);
            toolbar.setName("所有订单操作");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }

        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("createContract");
            item.setIcon(PToolbarHelper.iconAdd);
            item.setName("创建合同");
            item.setSeq(7);
            item.setCommand("{controller}.addContract();");
            toolbar.getItems().add(item);
        }
        item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("applyInvoice");
            item.setIcon(PToolbarHelper.iconAdd);
            item.setName("申请发票");
            item.setSeq(8);
            item.setCommand("{controller}.addInvoice();");
            toolbar.getItems().add(item);
        } 

        return toolbar;
    }

    @Test
    public void saveListToolbar() {
        PToolbar toolbar = createListToolbar();
        if (toolbar != null) {
            toolbarService.save(toolbar);
        }
    }

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		PDatagridColumn column = null;
		column = addColumn(datagrid, "no", "订单编号", ControlTypes.TEXT_BOX, 100,true);
		column = addColumn(datagrid, "channelOrderNo", "渠道订单号", ControlTypes.TEXT_BOX, 100,true);
		column = addColumn(datagrid, "prodName", "服务名称", ControlTypes.TEXT_BOX, 200);
		{
			column.setFormatter("return '<span title='+value+'>'+value+'</span>'");
		}
		column = addColumn(datagrid, "payStatus", "支付状态", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "processStatus", "执行进度", ControlTypes.NUMBER_BOX, 90);
		column = addColumn(datagrid, "refundStatus", "退款状态", ControlTypes.NUMBER_BOX, 90);		
		column = addColumn(datagrid, "totalPrice", "总金额", ControlTypes.DECIMAL_FEN_BOX, 90);
		column = addColumn(datagrid, "payablePrice", "未支付金额", ControlTypes.DECIMAL_FEN_BOX, 90);
		column = addColumn(datagrid, "paidPrice", "已支付金额", ControlTypes.DECIMAL_FEN_BOX, 90);
//		column = addColumn(datagrid, "isInstallment", "分期付款", ControlTypes.NUMBER_BOX, 90);{
//			column.setFormatter("return value==0?'否':'是'");
//		}
		addColumn(datagrid, "accountName", "客户名称", ControlTypes.TEXT_BOX, 100);
		column = addColumn(datagrid, "accountMobile", "手机号", ControlTypes.TEXT_BOX, 90);{
			
			column.setFormatter("return PandaHelper.dimString(value);");
		}
		//column = addColumn(datagrid, "platformSourceDict.name", "订单来源", ControlTypes.NUMBER_BOX, 90);
		addColumn(datagrid, "payTime", "支付时间", ControlTypes.DATE_BOX, 130);
		//addColumn(datagrid, "addTime", "创建时间", ControlTypes.DATE_BOX, 130);
		column = addColumn(datagrid, "type", "订单类型", ControlTypes.TEXT_BOX, 100, true);
		column = addColumn(datagrid, "sourceType", "来源类型", ControlTypes.NUMBER_BOX, 90);
		//订单状态目前是取字典，但实际情况是通过其他的几个字段计算出来（暂时不处理）
		column = addColumn(datagrid, "processStatus", "订单状态", ControlTypes.TEXT_BOX, 80);
		{
			column.setAlign(DatagridAlign.CENTER);
		}
		//通过left多个表关联,目前随便添加的
		//column = addColumn(datagrid, "accountName", "业务员", ControlTypes.NUMBER_BOX, 90);
		return datagrid;
	}

	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "accountName", "客户名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "prodName", "服务名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "payTime", "订单支付时间", ControlTypes.DATE_BOX);
		return queryProject;
	}

	@Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}
}
