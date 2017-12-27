package com.gongsibao.panda.product.workspace;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.product.Product;

/**   
 * @ClassName:  PriceAuditWorkspaceTest   
 * @Description:TODO 定价审核
 * @author: 韩伟
 * @date:   2017年12月7日 下午8:16:30   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class PriceAuditWorkspaceTest  extends WorkspaceCreationBase{


	@Before
	public void setup() {

		super.setup();
		urlList = "/prod/priceaudit/list";
		entity = Product.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "GSB_Product_Manage_PriceAudit";
		formPartName = listPartName = meta.getName();
	}


	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setName("产品方案列表");
		}
		
		PDatagridColumn column = null;
		addColumn(datagrid, "formName", "操作", ControlTypes.OPERATION_COLUMN, 100);
		addColumn(datagrid, "id", "编号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "product.name", "产品名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "remark", "影响地区", ControlTypes.TEXT_BOX, 200);
		column = addColumn(datagrid, "enabled", "启用/禁用", ControlTypes.TEXT_BOX, 100);{
			
		}
		return datagrid;
	}

	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "产品名称", ControlTypes.TEXT_BOX);
		addQueryItem(queryProject, "id", "产品编号", ControlTypes.TEXT_BOX);
		return queryProject;
	}

	@Override
	protected void doOperation() {
		
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
	}
}
