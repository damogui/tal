package com.gongsibao.panda.supplier.crm.reference;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class SupplierDepartmentRefereneTest extends ReferenceCreationBase{

	@Before
	public void setup() {
		resourceNodeCode = "CRM_SALESMAN_REFERENE_SUPPLIER_DEPART";
		datagridName = referenceName = "分配服务商部门参照";
		referenceCode = "CRM_Supplier_Depart";
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		addColumn(datagrid, "name", "分配服务商部门名称", ControlTypes.TEXT_BOX, 150, null, false);
		return datagrid;
	}
}