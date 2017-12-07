package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IProductViewService;
import com.gongsibao.entity.cms.ProductView;

@Service
public class ProductViewService extends PersistableService<ProductView>
		implements IProductViewService {

	public ProductViewService() {
		super();
		this.type = ProductView.class;
	}
/*
	@Override
	public List<ProductView> queryList(Oql oql) {

		List<ProductView> pvList = new ArrayList<ProductView>();
		IProductService productService = ServiceFactory
				.create(IProductService.class);
		Oql productOql = new Oql();
		{
			productOql.setType(Product.class);
			productOql.setSelects("*");
			productOql.setFilter(oql.getFilter());
			productOql.setPaging(oql.getPaging());
			
		}
		List<Product> list = productService.queryList(productOql);
		//oql.setPaging(productOql.getPaging());

		List<Integer> getIds = new ArrayList<Integer>();

		ProductView pv = null;
		// 获取产品id集合
		for (Product product : list) {
			getIds.add(product.getId());
		}

		Map<Integer, Integer> getMap = getProductInOrderCount(getIds);
		// 赋值，累计销量
		for (Product item : list) {
			pv = new ProductView();
			pv.setTotal(getMap.get(item.getId()));
			pv.setProdName(item.getProdName());
			pv.setShowprice(item.getShowprice());
			switch (item.getAttributeCategory()) {
			case 0:
				pv.setServiceClass("单品");
				break;
			case 3:
				pv.setServiceClass("套餐");
				break;
			default:
				pv.setServiceClass("其他");
				break;
			}
			pvList.add(pv);
		}
		return pvList;
	}

	private Map<Integer, Integer> getProductInOrderCount(
			List<Integer> productIdList) {
		Map<Integer, Integer> getMap = new HashMap<>();
		String ids = StringManager.join(",", productIdList);
		String cmdText = "SELECT product_id as productId,COUNT(1) as count from so_order_prod where product_id in ("
				+ ids + ") GROUP BY product_id";
		DataTable dataTable = this.pm.executeTable(cmdText, null);
		for (IRow row : dataTable) {
			Integer productId = Integer.parseInt(row.getString("product_id"));
			Integer count = Integer.parseInt(row.getString("count"));
			getMap.put(productId, count);
		}
		return getMap;
	}*/
}