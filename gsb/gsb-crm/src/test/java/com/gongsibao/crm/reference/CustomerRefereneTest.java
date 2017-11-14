package com.gongsibao.crm.reference;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.crm.Customer;

public class CustomerRefereneTest  extends ReferenceCreationBase{

	@Before
	public void setup() {

		resourceNodeCode = "CRM_All_" + Customer.class.getSimpleName();
		datagridName = referenceName = "客户参照";
		referenceCode = "CRM_" + Customer.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "realName";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn(datagrid, "realName", "姓名", ControlTypes.TEXT_BOX, 150, null, false);
		return datagrid;
	}
}
