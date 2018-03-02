package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.QueryParameters;
import org.netsharp.panda.commerce.DetailPart;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.SelectBuilder;

import com.gongsibao.entity.product.Price;
import com.gongsibao.product.base.IPriceService;
import com.gongsibao.utils.pcc.PccDTO;
import com.gongsibao.utils.pcc.PccHelper;

public class OrderProdItemDetailPart extends DetailPart {

	/**
	 * @Title: queryPcc
	 * @Description: TODO(获取当前产品下可售地区)
	 * @param: @param productId
	 * @param: @return
	 * @return: List<PccDTO>
	 * @throws
	 */
	public List<PccDTO> queryPcc(Integer productId) {

		List<Integer> pccIdList = new ArrayList<Integer>();
		SelectBuilder selectBuilder = SelectBuilder.getInstance();
		{
			selectBuilder.select("city_id");
			selectBuilder.from("prod_price");
			selectBuilder.where("service_id IN ( SELECT pkid FROM prod_service WHERE product_id = ? and is_enabled=1)", "is_on_sale=1");
			selectBuilder.groupBy("city_id");
		}

		QueryParameters qps = new QueryParameters();
		{

			qps.add("productId", productId, Types.INTEGER);
		}
		IPersister<Price> pm = PersisterFactory.create();
		DataTable dataTable = pm.executeTable(selectBuilder.toSQL(), qps);
		for (IRow row : dataTable) {

			Integer cityId = row.getInteger("city_id");
			pccIdList.add(cityId);
		}
		List<PccDTO> list = PccHelper.getFullPcc(pccIdList);
		return list;
	}

	/**
	 * @Title: queryServiceItem
	 * @Description: TODO(根据产品Id、地区Id查询服务项目)
	 * @param: @param productId
	 * @param: @param cityId
	 * @param: @return
	 * @return: List<ProductService>
	 * @throws
	 */
	public List<Price> queryServicePriceItem(Integer productId, Integer cityId) {

		IPriceService priceService = ServiceFactory.create(IPriceService.class);
		List<Price> list = priceService.queryServicePriceItem(productId,cityId);
		return list;
	}
}
