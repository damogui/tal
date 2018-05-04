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

import com.gongsibao.entity.cw.dto.BillAuditDTO;

/**
 * 
* 抄送我的  
* 项目名称：gsb-test   
* 类名称：CCBillWorkspaceTest   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月27日 下午8:24:59   
* @version
 */
public class CCBillWorkspaceTest extends WorkspaceCreationBase {

	@Before
    public void setup() {
        super.setup();
        urlList = "/cw/bill/cc/list";
		//urlForm = "/cw/bill/todo/form";
        entity = BillAuditDTO.class;
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "GSB_CW_Manage_CC_Bills";
	     
    }
 
 @Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setName("我的已办");
		}
		PDatagridColumn column = null;
		addColumn(datagrid, "formType", "单据类型", ControlTypes.ENUM_BOX, 150);
		addColumn(datagrid, "code", "借款单号", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "amount", "金额", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.TEXT_BOX, 150);
		addColumn(datagrid, "memoto", "备注", ControlTypes.TEXT_BOX, 300);
		return datagrid;
	}
    
    @Override
	protected PQueryProject createQueryProject(ResourceNode node) {
		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "code", "单据号", ControlTypes.TEXT_BOX);
		
		return queryProject;
	}
    
    @Override
	protected void doOperation() {
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
	}
}
