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

import com.gongsibao.cw.web.AllBillsListPart;
import com.gongsibao.entity.cw.dict.FinanceDict;
import com.gongsibao.entity.cw.dto.BillAuditDTO;
import com.gongsibao.entity.u8.SetOfBooks;

/**
 * 
* 单据查询  
* 项目名称：gsb-test   
* 类名称：AllBillsWorkspaceTest   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月20日 下午1:45:10   
* @version
 */
public class AllBillsWorkspaceTest extends WorkspaceCreationBase {
	
    @Before
    public void setup() {

    	super.setup();
        urlList = "/cw/bill/all/list";
		//urlForm = "/cw/bill/todo/form";
        entity = BillAuditDTO.class;
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "GSB_CW_Manage_All_Bills";
        
        listPartImportJs = "/gsb/platform/cw/js/all-bill-list-part.js";
		listPartJsController = AllBillsListPart.class.getName();
		listPartServiceController = AllBillsListPart.class.getName();
		//待办理
	    listFilter = " t.status = " +FinanceDict.AuditStatus.Status_5.getValue() +" ";
	    
    }
	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setName("全部订单");
			datagrid.setShowCheckbox(true);
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
		column = addColumn(datagrid, "operation", "操作", ControlTypes.ENUM_BOX, 150);
		{
			column.setFormatter("return controllerbillAuditDTOList.operationFormatter(value,row,index);");
		}
		addColumn(datagrid, "formType", "单据类型", ControlTypes.ENUM_BOX, 150);
		addColumn(datagrid, "code", "单据编号", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "amount", "金额", ControlTypes.DECIMAL_FEN_BOX, 150);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "status", "单据状态", ControlTypes.ENUM_BOX, 200);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 400);
		return datagrid;
	}
    
    @Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "code", "单据号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "formType", "单据类型", ControlTypes.ENUM_BOX);
		addRefrenceQueryItem(queryProject, "setOfBooks.name", "付款单位", SetOfBooks.class.getSimpleName());
		return queryProject;
	}
    
    @Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}

}
