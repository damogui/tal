package com.gongsibao.panda.igirl.workspace;

import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.igirl.web.TradeMarkListPart;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.*;
import org.netsharp.panda.plugin.entity.PToolbar;
import org.netsharp.panda.plugin.entity.PToolbarItem;
import org.netsharp.resourcenode.entity.ResourceNode;

/**   
 * @ClassName:  ProductWorkspaceTest   
 * @Description:TODO 尼斯分类
 * @author: 蒋勇
 * @date:   20181.1.10
 *
 */
public class TradeMarkFollowWorkspaceTest extends WorkspaceCreationBase{

	@Before
	public void setup() {

		super.setup();
		urlList = "/igirl/all/progress/list";
		//urlForm = "/igirl/nclone/form";

		entity = TradeMark.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "IGIRL_All_TradeMark";
		formPartName = listPartName = meta.getName();
		//formOpenMode = OpenMode.WINDOW;
		//openWindowWidth = 800;
	  //openWindowHeight = 600;
		listToolbarPath="/igirl/tradeMark/list";
		listPartJsController=TradeMarkListPart.class.getName();
		listPartImportJs="/gsb/igirl/js/trademark.listpart.js";
		
	}
	
	 @Test
	 public void fromToolbar() {
	
	 ResourceNode node =	 this.resourceService.byCode(resourceNodeCode);
	 OperationType ot1 = operationTypeService.byCode(OperationTypes.add);
	
	 PToolbar toolbar = new PToolbar();
	 {
		 toolbar.toNew();
		 //toolbar.setBasePath("panda/datagrid/edit");
		 toolbar.setPath(listToolbarPath);
		 toolbar.setName("进度跟进工具栏");
		 toolbar.setResourceNode(node);
	
	 }
	 PToolbarItem item = new PToolbarItem();
	 {
	 item.toNew();
	 item.setCode("autoSubmit");
	 item.setIcon("fa fa-link");
	 item.setName("就绪");
	 item.setCommand(null);
	 item.setOperationType(ot1);
	 item.setSeq(3000);
	 item.setCommand("{controller}.autoSubmit();");
	 toolbar.getItems().add(item);
	 }
	 toolbarService.save(toolbar);
	 }

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setName("商标大类");
			datagrid.setShowCheckbox(true);
			datagrid.setSingleSelect(false);
		}
		PDatagridColumn column = null;
		addColumn(datagrid, "proxyCode", "代理号", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "tradeMarkCase.companyName", "公司名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "tradeMarkCase.applier", "申请人", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "memo", "商标说明", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "markState", "状态", ControlTypes.ENUM_BOX, 200);
		return datagrid;
	}

	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "proxyCode", "代理号", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "tradeMarkCase.companyName", "公司名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "markState", "状态", ControlTypes.ENUM_BOX);
//		PQueryItem item =addQueryItem(queryProject, "mobilePhone", "销售方式", ControlTypes.CUSTOMER);{
//			
//			item.setCustomerControlType(DictComboBox.class.getName());
//			item.setRefFilter("type=8");
//		}
		//addQueryItem(queryProject, "enabled", "启用/禁用", ControlTypes.BOOLCOMBO_BOX);
		return queryProject;
	}

	@Override
	protected void doOperation() {
		
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
	}
}
