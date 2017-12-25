package com.gongsibao.panda.product.workspace;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.DatagridAlign;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PQueryProject;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.product.ProductPackage;
import com.gongsibao.product.web.PackageListPart;

/**   
 * @ClassName:  ProductPackageWorkspaceTest   
 * @Description:TODO 产品套餐
 * @author: 韩伟
 * @date:   2017年12月7日 下午8:15:59   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class ProductPackageWorkspaceTest  extends WorkspaceCreationBase{

	@Before
	public void setup() {

		super.setup();
		urlList = "/prod/package/list";
		urlForm ="/nav/gsb/product/packageForm";
		entity = ProductPackage.class;
		meta = MtableManager.getMtable(entity);
		resourceNodeCode = "GSB_Product_Manage_Package";
		formPartName = listPartName = meta.getName();
		listPartImportJs = "/gsb/product/js/package-list-part.js";
		listPartJsController = PackageListPart.class.getName();
		listPartServiceController = PackageListPart.class.getName();
	}

	@Test
	public void run() {
		createListWorkspace();
	}


	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		{
			datagrid.setToolbar("panda/datagrid/row/edit");
			datagrid.setName("产品方案列表");
		}
		
		PDatagridColumn column = null;
		addColumn(datagrid, "sort", "操作", ControlTypes.OPERATION_COLUMN, 100);
		addColumn(datagrid, "id", "编号", ControlTypes.TEXT_BOX, 100);
		addColumn(datagrid, "name", "套餐名称", ControlTypes.TEXT_BOX, 200);
		addColumn(datagrid, "content", "套餐内容", ControlTypes.TEXT_BOX, 400);
		addColumn(datagrid, "desc", "描述", ControlTypes.TEXT_BOX, 200);
		column = addColumn(datagrid, "enabled", "状态", ControlTypes.TEXT_BOX, 100);{
			column.setAlign(DatagridAlign.CENTER);
			column.setFormatter("return controllerproductPackageList.enabledFormatter(value,row,index);");
		}
		return datagrid;
	}

	
	@Override
	protected PQueryProject createQueryProject(ResourceNode node) {

		PQueryProject queryProject = super.createQueryProject(node);
		queryProject.toNew();
		addQueryItem(queryProject, "name", "套餐名称", ControlTypes.TEXT_BOX);
		return queryProject;
	}

	@Override
	protected void doOperation() {
		
		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node,OperationTypes.view);
		operationService.addOperation(node,OperationTypes.add);
		operationService.addOperation(node,OperationTypes.update);
		operationService.addOperation(node,OperationTypes.delete);
	}
}
