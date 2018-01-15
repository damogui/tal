package com.gongsibao.panda.crm.reference;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class EmployeeRefereneTest extends ReferenceCreationBase{

	@Before
	public void setup() {
		resourceNodeCode = "GSB_CRM_MY_REFERENE_EMPLOYEE";
		datagridName = referenceName = "员工信息参照";
		referenceCode = "CRM_Employee";
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		addColumn(datagrid, "name", "员工名称", ControlTypes.TEXT_BOX, 150, null, false);
		return datagrid;
	}
}
