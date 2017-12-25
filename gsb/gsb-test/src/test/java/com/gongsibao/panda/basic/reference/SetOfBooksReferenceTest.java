package com.gongsibao.panda.basic.reference;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.u8.SetOfBooks;


public class SetOfBooksReferenceTest extends ReferenceCreationBase{
	
	@Before
	public void setup() {
		
		resourceNodeCode =  "GSB_Basic_U8_" + SetOfBooks.class.getSimpleName();//资源节点
		datagridName = referenceName = "账套参照";
		referenceCode = SetOfBooks.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
		filter = "enabled=1";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,150,null,false);
		return datagrid;
	}
}