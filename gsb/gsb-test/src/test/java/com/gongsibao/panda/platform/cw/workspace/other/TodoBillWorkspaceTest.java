package com.gongsibao.panda.platform.cw.workspace.other;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.cw.web.TodoBillListPart;
import com.gongsibao.entity.cw.dto.BillAuditDTO;

/**
 * 
*  我的待办
* 项目名称：gsb-test   
* 类名称：TodoBillWorkspaceTest   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月27日 下午8:23:48   
* @version
 */
public class TodoBillWorkspaceTest extends WorkspaceCreationBase {

	 @Before
	    public void setup() {
	        super.setup();
	        urlList = "/cw/bill/todo/list";
			//urlForm = "/cw/bill/todo/form";
	        entity = BillAuditDTO.class;
	        meta = MtableManager.getMtable(entity);
	        formPartName = listPartName = meta.getName();
	        resourceNodeCode = "GSB_CW_Manage_Todo_Bills";
	        
	        listPartImportJs = "/gsb/platform/cw/js/todo-bill-list-part.js";
			listPartJsController = TodoBillListPart.class.getName();
			listPartServiceController = TodoBillListPart.class.getName();
			//待办理
		    listFilter = " a.status = 1  ";
	    }
	 
	 @Override
		protected PDatagrid createDatagrid(ResourceNode node) {

			PDatagrid datagrid = super.createDatagrid(node);
			{
				datagrid.setName("我的待办");
			}
			PDatagridColumn column = null;
			column = addColumn(datagrid, "formId", "单据", ControlTypes.ENUM_BOX, 150);
			{
				column.setSystem(true);
				column.setVisible(false);
			}
			column = addColumn(datagrid, "operation", "操作", ControlTypes.ENUM_BOX, 150);
			{
				column.setFormatter("return controllerbillAuditDTOList.operationFormatter(value,row,index);");
			}
			addColumn(datagrid, "formType", "单据类型", ControlTypes.ENUM_BOX, 150);
			addColumn(datagrid, "code", "借款单号", ControlTypes.TEXT_BOX, 250);
			addColumn(datagrid, "amount", "金额", ControlTypes.TEXT_BOX, 150);
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
