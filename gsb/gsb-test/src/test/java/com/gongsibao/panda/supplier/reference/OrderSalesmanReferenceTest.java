package com.gongsibao.panda.supplier.reference;

import com.gongsibao.entity.supplier.Salesman;
import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.resourcenode.entity.ResourceNode;

public class OrderSalesmanReferenceTest extends ReferenceCreationBase{
	
	@Before
	public void setup() {
		
		resourceNodeCode =  "GSB_Order_Supplier_Salesman";
		datagridName = referenceName = "服务商业务员参照";
		referenceCode = "OrderSalesman";
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
		gridFilter="disabled=0";//停用的不显示
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"employee.name", "名称", ControlTypes.TEXT_BOX,100,null,false);

		return datagrid;
	}
}
