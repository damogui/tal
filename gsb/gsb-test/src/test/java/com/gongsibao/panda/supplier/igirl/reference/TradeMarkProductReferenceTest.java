package com.gongsibao.panda.supplier.igirl.reference;
import com.gongsibao.entity.igirl.tm.TradeMarkCase;
import com.gongsibao.entity.igirl.tm.baseinfo.NCLOne;
import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.resourcenode.entity.ResourceNode;

public class TradeMarkProductReferenceTest extends ReferenceCreationBase{

	@Before
	public void setup() {

		resourceNodeCode = "IGIRL_Product_" + TradeMarkCase.class.getSimpleName();
		datagridName = referenceName = "商标注册产品参照";
		// 注册型商标code
		referenceCode = "RegisterTypeTradeMarkList";
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "name";
//		1137 商标注册入门版, 1177 商标注册专业版 1823 商标注册大师版, 1514, 商标注册终极版
		gridFilter = " id IN (1137, 1177, 1823, 1514) ";
//		canNew= true;
//		popupUrl = "/panda/crm/company/form";
		width = 1000;
		height = 600;
	}

	public PDatagrid createDatagrid(ResourceNode node) {
		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setOrderby(" id ASC ");
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 150, null, false);
		return datagrid;
	}
}
