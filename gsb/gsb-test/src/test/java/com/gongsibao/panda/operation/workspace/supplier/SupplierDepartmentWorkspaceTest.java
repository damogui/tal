package com.gongsibao.panda.operation.workspace.supplier;

import org.netsharp.panda.dic.OpenMode;

import com.gongsibao.crm.web.SysDepartmentTreeGridPart;
import com.gongsibao.panda.crm.workspace.sys.SysDepartmentWorkspaceTest;

public class SupplierDepartmentWorkspaceTest extends SysDepartmentWorkspaceTest{

	public void setup() {
		
		super.setup();
		urlList = "/operation/supplier/department/list";
		urlForm = "/operation/supplier/department/form";
		resourceNodeCode = "GSB_Operation_Supplier_Department";
		formOpenMode = OpenMode.WINDOW;
		openWindowWidth = 1000;
		openWindowHeight = 600;
		listPartImportJs="/gsb/crm/sys/js/sys-department-list-part.js|/gsb/gsb.custom.query.controls.js";
		listPartJsController = SysDepartmentTreeGridPart.class.getName();
		listPartServiceController = SysDepartmentTreeGridPart.class.getName();
		
		listToolbarPath = "/operation/supplier/department/toolbar";
	}
}
