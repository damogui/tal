package com.gongsibao.panda.user.reference;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.uc.Organization;

public class OrganizationReferenceTest extends ReferenceCreationBase{

	@Before
	public void setup() {
		
		resourceNodeCode =  "User_Center_" +Organization.class.getSimpleName();
		datagridName = referenceName = "组织参照";
		referenceCode = "Gsb_Organization";
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "shortName";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"shortName", "名称", ControlTypes.TEXT_BOX,200,null,false);
		return datagrid;
	}
}
