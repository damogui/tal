package com.gongsibao.panda.platform.operation.workspace.reference;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.supplier.SupplierCategory;

public class SupplierCategoryRefeerenceTest extends ReferenceCreationBase{
	
	@Before
	public void setup() {
		
		resourceNodeCode =  "GSB_Operation_Supplier_Category";
		datagridName = referenceName = "服务商分组参照";
		referenceCode = SupplierCategory.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	    //panelWidth = 420;
	    //panelHeight = 310;
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,150,null,false);
		return datagrid;
	}
}
