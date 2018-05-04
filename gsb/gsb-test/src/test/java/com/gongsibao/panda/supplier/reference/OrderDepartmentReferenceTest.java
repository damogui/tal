package com.gongsibao.panda.supplier.reference;

import com.gongsibao.entity.supplier.SupplierDepartment;
import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class OrderDepartmentReferenceTest extends ReferenceCreationBase{

	@Before
	public void setup() {
		
		resourceNodeCode =  "GSB_Order_Supplier_Department";
		datagridName = referenceName = "服务商部门表参照";
		referenceCode = "OrderSupplierDepartment";
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
//		addColumn( datagrid,"supplier.name", "服务商", ControlTypes.TEXT_BOX,150,null,false);
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,150,null,false);
		return datagrid;
	}
}
