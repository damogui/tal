package com.gongsibao.panda.crm.reference;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.crm.CompanyIntention;

public class CompanyIntentionReferenceTest  extends ReferenceCreationBase{

	@Before
	public void setup() {

		resourceNodeCode = "CRM_"+CompanyIntention.class.getSimpleName();
		datagridName = referenceName = "公司参照";
		referenceCode = CompanyIntention.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "companyName";
		
		canNew= true;
		popupUrl = "/panda/crm/company/form";
		width = 1000;
		height = 600;
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn(datagrid, "companyName", "名称", ControlTypes.TEXT_BOX, 150, null, false);
		return datagrid;
	}
}
