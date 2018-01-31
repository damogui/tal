package com.gongsibao.panda.operation.workspace.supplier;

import org.netsharp.core.MtableManager;
import org.netsharp.panda.dic.OpenMode;

import com.gongsibao.crm.web.SysSalesmanListPart;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.panda.crm.workspace.sys.SysSalesmanWorkspaceTest;

public class SupplierSalesmanWorkspaceTest  extends SysSalesmanWorkspaceTest {

    public void setup() {
        super.setup();
        urlList = "/operation/supplier/salesman/list";
        urlForm = "/operation/supplier/salesman/form";
        entity = Salesman.class;
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "GSB_Operation_Supplier_Salesman";
		formOpenMode = OpenMode.WINDOW;
		openWindowHeight = 650;
		openWindowWidth = 900;
        listPartImportJs = "/gsb/crm/sys/js/sys-salesman-list-part.js|/gsb/gsb.custom.query.controls.js";
        listPartJsController = SysSalesmanListPart.class.getName();
        listPartServiceController = SysSalesmanListPart.class.getName();

        treeResourceNodeCode = "GSB_Operation_Supplier_Department";
    }
}
