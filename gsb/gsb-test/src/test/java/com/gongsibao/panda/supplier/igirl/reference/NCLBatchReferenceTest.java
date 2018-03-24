package com.gongsibao.panda.supplier.igirl.reference;
import com.gongsibao.entity.igirl.baseinfo.NclBatch;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class NCLBatchReferenceTest extends ReferenceCreationBase{

	@Before
	public void setup() {

		resourceNodeCode = "NCL_All_" + NclBatch.class.getSimpleName();
		datagridName = referenceName = "尼斯期间参照";
		referenceCode = NclBatch.class.getSimpleName();
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
		
//		canNew= true;
//		popupUrl = "/panda/crm/company/form";
		width = 1000;
		height = 600;
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setOrderby("code asc");
		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 150, null, false);
		addColumn(datagrid, "context", "说明", ControlTypes.TEXT_BOX, 150, null, false);
		return datagrid;
	}
}
