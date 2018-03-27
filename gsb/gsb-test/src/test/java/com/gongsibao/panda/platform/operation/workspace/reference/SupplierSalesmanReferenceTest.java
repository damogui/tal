package com.gongsibao.panda.platform.operation.workspace.reference;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.supplier.Salesman;

public class SupplierSalesmanReferenceTest extends ReferenceCreationBase{
	
	@Before
	public void setup() {
		
		resourceNodeCode =  "GSB_Operation_Supplier_Salesman";
		datagridName = referenceName = "服务商业务员参照";
		referenceCode = Salesman.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		
		addColumn( datagrid,"supplier.name", "服务商", ControlTypes.TEXT_BOX,150,null,false);
		addColumn( datagrid,"department.name", "部门", ControlTypes.TEXT_BOX,100,null,false);
		addColumn( datagrid,"employee.name", "名称", ControlTypes.TEXT_BOX,100,null,false);
		PDatagridColumn column = addColumn( datagrid,"receiving", "接收商机", ControlTypes.BOOLCOMBO_BOX,60,null,false);{
			column.setFormatter(" return value===false?'否':'是';");
		}
		column = addColumn( datagrid,"employeeId", "employeeId", ControlTypes.TEXT_BOX,150,null,false);{
			column.setVisible(false);
			column.setSystem(true);
		}
		return datagrid;
	}
}
