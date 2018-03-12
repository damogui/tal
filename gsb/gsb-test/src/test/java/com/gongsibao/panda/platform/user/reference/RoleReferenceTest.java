package com.gongsibao.panda.platform.user.reference;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.uc.Role;

public class RoleReferenceTest extends ReferenceCreationBase{

	@Before
	public void setup() {
		
		resourceNodeCode =  "User_Center_" +Role.class.getSimpleName();
		datagridName = referenceName = "组织参照";
		referenceCode = "Gsb_Role";
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setFilter("enabled=1");
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,200,null,false);
		return datagrid;
	}
}
