package com.gongsibao.panda.platform.basic.reference;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.u8.U8Bank;

public class U8BankReferenceTest extends ReferenceCreationBase{
	
	@Before
	public void setup() {
		
		resourceNodeCode =  "GSB_Basic_U8_" + U8Bank.class.getSimpleName();
		datagridName = referenceName = "预付科目参照";
		referenceCode = U8Bank.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name,abbreviation";
		//filter = "type='0'";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,150,null,false);
		addColumn( datagrid,"abbreviation", "简称", ControlTypes.TEXT_BOX,100,null,false);
		return datagrid;
	}
}
