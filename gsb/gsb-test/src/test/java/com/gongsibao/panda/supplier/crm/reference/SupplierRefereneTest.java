package com.gongsibao.panda.supplier.crm.reference;

import com.gongsibao.entity.supplier.dict.SupplierStatus;
import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class SupplierRefereneTest extends ReferenceCreationBase{

	@Before
	public void setup() {
		resourceNodeCode = "CRM_SALESMAN_REFERENE_SUPPLIER";
		datagridName = referenceName = "分配服务商参照";
		referenceCode = "CRM_Supplier";
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
        gridFilter="status="+ SupplierStatus.OPEND.getValue();
	}

	public PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		addColumn(datagrid, "name", "服务商名称", ControlTypes.TEXT_BOX, 150, null, false);
		return datagrid;
	}
}
