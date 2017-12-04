package com.gongsibao.crm.reference;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.product.Product;

public class ProductReferenceTest extends ReferenceCreationBase {

	@Before
	public void setup() {

		resourceNodeCode = "CRM_" + Product.class.getSimpleName();
		datagridName = referenceName = "产品参照";
		referenceCode = "CRM_" + Product.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setFilter("enabled=1");
		PDatagridColumn column = null;
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150, null, false);
		
		column = addColumn(datagrid, "sort", "排序", ControlTypes.TEXT_BOX, 60, null, false);{
			
			column.setOrderbyMode(OrderbyMode.ASC);
			column.setVisible(false);
			column.setSystem(false);
		}
		return datagrid;
	}
}
