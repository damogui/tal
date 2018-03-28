package com.gongsibao.panda.supplier.igirl.reference;

import com.gongsibao.entity.igirl.tm.baseinfo.NCLOne;
import com.gongsibao.entity.igirl.tm.baseinfo.NCLTwo;
import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class NCLTwoReferenceTest extends ReferenceCreationBase{

	@Before
	public void setup() {

		resourceNodeCode = "IGRIL_BASE_" + NCLTwo.class.getSimpleName();
		datagridName = referenceName = "商标小类参照";
		referenceCode = NCLTwo.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
		
//		canNew= true;
//		popupUrl = "/panda/crm/company/form";
		width = 1000;
		height = 600;
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 150, null, false);
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 150, null, false);
		return datagrid;
	}
}
