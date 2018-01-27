package com.gongsibao.panda.crm.reference;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class NCustomerRefereneTest extends ReferenceCreationBase{

	@Before
	public void setup() {
		resourceNodeCode = "CRM_SALESMAN_REFERENE_NCUSTOMER";
		datagridName = referenceName = "客户参照";
		referenceCode = "CRM_NCustomer";
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "realName";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn(datagrid, "realName", "姓名", ControlTypes.TEXT_BOX, 150, null, false);
		return datagrid;
	}
}
