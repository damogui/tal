package com.gongsibao.panda.supplier.reference;

import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.dict.SupplierStatus;
import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class OrderSupplierReferenceTest extends ReferenceCreationBase{

	@Before
	public void setup() {
		
		resourceNodeCode =  "GSB_Order_Supplier";
		datagridName = referenceName = "服务商表参照";
		referenceCode = "OrderSupplier";
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
		gridFilter="status="+SupplierStatus.OPEND.getValue();
	    //panelWidth = 420;
	    //panelHeight = 310;
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
//		addColumn( datagrid,"category.name", "分类", ControlTypes.TEXT_BOX,150,null,false);
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,150,null,false);
		return datagrid;
	}
}
