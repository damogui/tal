package com.gongsibao.crm.service;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.util.StringManager;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerProductService;
import com.gongsibao.entity.crm.NCustomerProduct;

@Service
public class NCustomerProductService extends SupplierPersistableService<NCustomerProduct> implements INCustomerProductService {

	public NCustomerProductService() {
		super();
		this.type = NCustomerProduct.class;
	}

	@Override
	public BigDecimal getSigningAmount(Integer taskId) {

		BigDecimal amount = BigDecimal.ZERO;
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("taskId=?");
			oql.getParameters().add("taskId", taskId, Types.INTEGER);
		}

		List<NCustomerProduct> list = this.queryList(oql);
		if (list.size() == 0) {

			return amount;
		}

		List<Integer> productIdList = new ArrayList<Integer>();
		List<Integer> cityIdList = new ArrayList<Integer>();
		for (NCustomerProduct cp : list) {

			productIdList.add(cp.getProductId());
			if (cp.getProvinceId() != null) {

				cityIdList.add(cp.getProvinceId());
			}

			if (cp.getCityId() != null) {

				cityIdList.add(cp.getCityId());
			}

			if (cp.getCountyId() != null) {

				cityIdList.add(cp.getCountyId());
			}
		}

		String productIds = StringManager.join(",", productIdList);
		String cityIds = StringManager.join(",", cityIdList);
		
		// 涉及的表,这里查询逻辑可能有问题。
		// prod_product,prod_service,prod_price
		// return new BigDecimal(100);

		// SELECT SUM(price) as amount FROM prod_price price
		// left join prod_service service on price.service_id = service.pkid
		// LEFT JOIN prod_product product on service.product_id = product.pkid
		// where product_id = 2012 and (price.city_id = 101900101 or
		// price.city_id in (101110000,101110100,101110102))
		// 101900101:大陆地区 
		StringBuilder selectBuilder = new StringBuilder();
		{
			selectBuilder.append(" SELECT  ifnull(SUM(price),0) as amount FROM prod_price price ");
			selectBuilder.append(" left join prod_service service on price.service_id = service.pkid ");
			selectBuilder.append(" LEFT JOIN prod_product product on service.product_id = product.pkid ");
			selectBuilder.append(" where ");
			selectBuilder.append(" product_id in (" + productIds + ") ");
			selectBuilder.append(" and (price.city_id = 101900101 ");

			if (!StringManager.isNullOrEmpty(cityIds)) {
				selectBuilder.append(" or  price.city_id in (" + cityIds + ")");
			}
			selectBuilder.append(" ) ");
		}
		
		DataTable dataTable = this.pm.executeTable(selectBuilder.toString(), null);
		for (IRow row : dataTable) {
			
			 amount = new BigDecimal(row.getString("amount"));
		}

		//数据库中是分，要除以100
		amount = amount.divide(new BigDecimal(100));
		return amount;
	}
}