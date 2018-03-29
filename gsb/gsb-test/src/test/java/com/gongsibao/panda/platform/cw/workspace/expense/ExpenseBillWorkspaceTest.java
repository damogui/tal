package com.gongsibao.panda.platform.cw.workspace.expense;

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

import com.gongsibao.cw.web.ExpenseBillListPart;
import com.gongsibao.entity.cw.Expense;
import com.gongsibao.tools.PToolbarHelper;

/**
 * 
* 报销单  
* 项目名称：gsb-test   
* 类名称：ExpenseBillWorkspaceTest   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月20日 下午1:45:10   
* @version
 */
public class ExpenseBillWorkspaceTest extends WorkspaceCreationBase {
	
	@Before
    public void setup() {
        super.setup();
        urlList = "/cw/bill/expense/list";
		urlForm = "/cw/bill/expense/form";
        entity = Expense.class;
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "GSB_CW_Manage_Expense_Bill";
        listToolbarPath = "/cw/bill/expense/toolbar";
        
        listPartImportJs = "/gsb/platform/cw/js/expense-bill-list-part.js";
		listPartJsController = ExpenseBillListPart.class.getName();
		listPartServiceController = ExpenseBillListPart.class.getName();
    }
    
    public PToolbar createListToolbar() {

        ResourceNode node = this.resourceService.byCode(resourceNodeCode);
        PToolbar toolbar = new PToolbar();
        {
            toolbar.toNew();
            toolbar.setPath(listToolbarPath);
            toolbar.setName("报销单");
            toolbar.setResourceNode(node);
            toolbar.setToolbarType(ToolbarType.BASE);
        }

        PToolbarItem item = new PToolbarItem();
        {
            item.toNew();
            item.setCode("createContract");
            item.setIcon(PToolbarHelper.iconAdd);
            item.setName("申请报销");
            item.setSeq(7);
            item.setCommand("{controller}.applyExpense();");
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
			datagrid.setName("报销单");
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setAutoQuery(false);
		}
		PDatagridColumn column = null;
		
		addColumn(datagrid, "type", "报销类型", ControlTypes.ENUM_BOX, 200, true);
		addColumn(datagrid, "code", "报销单号", ControlTypes.TEXT_BOX, 200, true);
		addColumn(datagrid, "amount", "报销金额", ControlTypes.DECIMAL_FEN_BOX, 100);
		addColumn(datagrid, "formNumber", "单据数量", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "reason", "报销理由", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 300);
		addColumn(datagrid, "creator", "创建日期", ControlTypes.DATE_BOX, 300);  
		addColumn(datagrid, "status", "审批状态", ControlTypes.ENUM_BOX, 300);
		return datagrid;
	}
    
    
    
    @Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "accountName", "报销状态", ControlTypes.TEXT_BOX);
		return queryProject;
	}
    
    @Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}


}
