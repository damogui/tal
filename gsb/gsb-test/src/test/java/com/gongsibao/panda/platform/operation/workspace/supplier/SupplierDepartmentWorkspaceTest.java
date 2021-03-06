package com.gongsibao.panda.platform.operation.workspace.supplier;

import org.netsharp.panda.dic.OpenMode;

import com.gongsibao.crm.web.SysDepartmentTreeGridPart;
import com.gongsibao.panda.supplier.sys.workspace.SysDepartmentWorkspaceTest;

public class SupplierDepartmentWorkspaceTest extends SysDepartmentWorkspaceTest{

	public void setup() {
		
		super.setup();
		urlList = "/operation/supplier/department/list";
		urlForm = "/operation/supplier/department/form";
		resourceNodeCode = "GSB_Operation_Supplier_Department";
		formOpenMode = OpenMode.WINDOW;
		openWindowWidth = 1000;
		openWindowHeight = 600;
		listPartImportJs="/gsb/supplier/sys/organization/js/sys-department-list-part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
		listPartJsController = SysDepartmentTreeGridPart.class.getName();
		listPartServiceController = SysDepartmentTreeGridPart.class.getName();
		
		listToolbarPath = "/operation/supplier/department/toolbar";
		
		productDetailResourceNodeCode = "GSB_Operation_Supplier_Department_Product";
	}
}
