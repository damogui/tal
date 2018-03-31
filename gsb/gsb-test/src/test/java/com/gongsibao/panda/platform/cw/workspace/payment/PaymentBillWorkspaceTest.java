package com.gongsibao.panda.platform.cw.workspace.payment;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.panda.plugin.dic.ToolbarType;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.cw.web.PaymentBillListPart;
import com.gongsibao.entity.cw.Payment;
import com.gongsibao.tools.PToolbarHelper;

/**
 * 
* 付款单
* 项目名称：gsb-test   
* 类名称：PaymentBillWorkspaceTest   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月20日 下午1:45:10   
* @version
 */
public class PaymentBillWorkspaceTest extends WorkspaceCreationBase {
	
	@Before
    public void setup() {
        super.setup();
        urlList = "/cw/bill/payment/list";
		urlForm = "/cw/bill/payment/form";
        entity = Payment.class;
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "GSB_CW_Manage_Payment_Bill";
        listToolbarPath = "/cw/bill/Payment/toolbar";
        
        listPartImportJs = "/gsb/platform/cw/js/payment-bill-list-part.js";
		listPartJsController = PaymentBillListPart.class.getName();
		listPartServiceController = PaymentBillListPart.class.getName();
    }
    
    public PToolbar createListToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listToolbarPath);
            toolbar.setName("付款单");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }

        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("createContract");
            item.setIcon(PToolbarHelper.iconAdd);
            item.setName("申请付款");
            item.setSeq(7);
            item.setCommand("{controller}.applyPayment();");
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
		{
			datagrid.setName("付款单");
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setAutoQuery(false);
		}
		addColumn(datagrid, "code", "付款单号", ControlTypes.OPERATION_COLUMN, 100, true);
		addColumn(datagrid, "businessType", "业务类型", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "amount", "付款金额", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "setOfBooks.name", "付款单位", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 200);
		addColumn(datagrid, "status", "审核状态", ControlTypes.ENUM_BOX, 150);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 400);
		return datagrid;
	}
    
    
    
    @Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "accountName", "付款状态", ControlTypes.TEXT_BOX);
		return queryProject;
	}
    
    @Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}


}
