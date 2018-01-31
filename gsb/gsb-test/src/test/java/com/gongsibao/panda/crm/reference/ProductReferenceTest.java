package com.gongsibao.panda.crm.reference;

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
		panelWidth = 440;
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		String filter = "enabled=1 and pkid in (SELECT DISTINCT "
							+ " (prod_price_audit.product_id) FROM "
							+ " `prod_price_audit` "
							+ " INNER JOIN `prod_price` ON prod_price_audit.`pkid` = prod_price.`price_audit_id` "
							+ " INNER JOIN `prod_service` ON prod_price.`service_id` = prod_service.`pkid` "
							+ " WHERE prod_price.`is_on_sale` = 1 "
							+ " AND prod_price_audit.audit_status_id = 1054 "
							+ " AND prod_price_audit.organization_id <> 1 "
							+ " AND prod_price_audit.organization_id <> 2)";
		
		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setFilter(filter);
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

//原系统逻辑

//1.过滤在线销售的商品

//SELECT DISTINCT
//( 
//	prod_price_audit.product_id
//)
//FROM
//`prod_price_audit`
//INNER JOIN `prod_price` ON prod_price_audit.`pkid` = prod_price.`price_audit_id`
//INNER JOIN `prod_service` ON prod_price.`service_id` = prod_service.`pkid`
//WHERE
//prod_price.`is_on_sale` = 1
//AND prod_price_audit.audit_status_id = 1054
//AND prod_price_audit.organization_id <> 1
//AND prod_price_audit.organization_id <> 2


//2.查询商品实体数据


//3.
//循环商品信息，typeId，dealerTypeId，放到Set里


//4.查询商品分类，销售方类型
//Set<Integer> dictIds = new HashSet<>();
//for (ProdProduct prodProduct : prodList) {
//    dictIds.add(prodProduct.getTypeId());
//    dictIds.add(prodProduct.getDealerTypeId());
//}
//Map<Integer, BdDict> dictMap = bdDictService.findMapByIds(new ArrayList<>(dictIds));

//5.







