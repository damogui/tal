package com.gongsibao.panda.platform.operation.workspace.supplier;

import org.junit.Test;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.panda.dic.OpenMode;
import org.netsharp.panda.entity.PPart;
import org.netsharp.panda.entity.PWorkspace;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.web.SysALLSalesmanListPart;
import com.gongsibao.crm.web.SysSalesmanListPart;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.panda.supplier.sys.workspace.SysSalesmanWorkspaceTest;

public class AllSupplierSalesmanWorkspaceTest extends SysSalesmanWorkspaceTest{

    public void setup() {
        super.setup();
        urlList = "/operation/supplier/all/salesman/list";
        urlForm = "/operation/supplier/all/salesman/form";
        entity = Salesman.class;
        meta = MtableManager.getMtable(entity);
        formPartName = listPartName = meta.getName();
        resourceNodeCode = "GSB_Operation_Supplier_ALL_Salesman";
		formOpenMode = OpenMode.WINDOW;
		openWindowHeight = 650;
		openWindowWidth = 900;
        listPartImportJs = "/gsb/supplier/sys/organization/js/sys-salesman-list-part.js|/gsb/panda-extend/gsb.custom.query.controls.js";
        listPartJsController = SysSalesmanListPart.class.getName();
        listPartServiceController = SysALLSalesmanListPart.class.getName();

        treeResourceNodeCode = "GSB_Operation_Supplier_Department";
        
        productDetailResourceNodeCode = "GSB_Operation_Supplier_SALESMAN_Product";
        
        roleDetailResourceNodeCode = "GSB_Operation_Supplier_SALESMAN_ADDROLE";
    }
    
    @Test
    public void createRowToolbar() {
    	
    }

    @Test
    @Override
	public void run() {
		createListWorkspace();
		createFormWorkspace();
	}
    
    protected PPart createListWorkspaceParts(ResourceNode node, Mtable meta, PWorkspace workspace) {
    	
    	PPart part = super.createListWorkspaceParts(node, meta, workspace);
    	part.setCode("salesman");
    	return part;
    }
}
