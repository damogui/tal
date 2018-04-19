package com.gongsibao.panda.platform.cw.reference;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.cw.CostType;
import com.gongsibao.entity.u8.SetOfBooks;


public class CostTypeReferenceTest extends ReferenceCreationBase{
	
	@Before
	public void setup() {
		
		resourceNodeCode =  "GSB_CW_Manage_Cost_Type";//资源节点
		datagridName = referenceName = "费用类型参照";
		referenceCode = CostType.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		addColumn( datagrid,"name", "名称", ControlTypes.TEXT_BOX,150,null,false);
		return datagrid;
	}
}