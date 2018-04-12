package com.gongsibao.panda.platform.cw.workspace.other;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.cw.web.FinanceBillListPart;
import com.gongsibao.cw.web.TodoBillListPart;
import com.gongsibao.entity.cw.dict.FinanceDict;
import com.gongsibao.entity.cw.dto.BillAuditDTO;

/**
 * 
* 财务办理  
* 项目名称：gsb-test   
* 类名称：FinanceBillWorkspaceTest   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月27日 下午8:22:59   
* @version
 */
public class FinanceBillWorkspaceTest extends WorkspaceCreationBase {

	@Before
    public void setup() {
        super.setup();
        urlList = "/cw/bill/finance/list";
		//urlForm = "/cw/bill/todo/form";
        entity = BillAuditDTO.class;
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "GSB_CW_Manage_Finance_Bills";
	     
        listPartImportJs = "/gsb/platform/cw/js/finance-bill-list-part.js";
		listPartJsController = FinanceBillListPart.class.getName();
		listPartServiceController = FinanceBillListPart.class.getName();
		listToolbarPath = "";
		
		//待财务办理
	    listFilter = " t.status = "+FinanceDict.AuditStatus.Status_4.getValue() +" ";
    }

 @Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setName("财务办理");
		}
		
		PDatagridColumn column = null;
		column = addColumn(datagrid, "formId", "单据", ControlTypes.TEXT_BOX, 150);
		{
			column.setSystem(true);
			column.setVisible(false);
		}
		column = addColumn(datagrid, "formTypeValue", "单据类型值", ControlTypes.TEXT_BOX, 150);
		{
			column.setSystem(true);
			column.setVisible(false);
		}
		column = addColumn(datagrid, "operation", "操作", ControlTypes.TEXT_BOX, 150);
		{
			column.setFormatter("return controllerbillAuditDTOList.operationFormatter(value,row,index);");
		}
		addColumn(datagrid, "formType", "单据类型", ControlTypes.ENUM_BOX, 200);
		addColumn(datagrid, "code", "单据号", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "amount", "金额", ControlTypes.DECIMAL_FEN_BOX, 200);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 400);
		return datagrid;
	}
    
    @Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "code", "单据号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "formType", "单据类型", ControlTypes.ENUM_BOX);
		return queryProject;
	}
    
    @Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}
}
