package com.gongsibao.product.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.gongsibao.entity.product.Income;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.Price;
import com.gongsibao.product.base.IPriceService;
import org.netsharp.util.StringManager;

@Service
public class PriceService extends PersistableService<Price> implements IPriceService {

    public PriceService(){
        super();
        this.type=Price.class;
    }

	@Override
	public List<Price> queryServicePriceItem(Integer productId, Integer cityId) {
		Oql oql = new Oql();
		{
			oql.setType(Price.class);
			oql.setSelects("Price.*,Price.service.*,Price.service.unit.{id,name},Price.service.type.{id,name},Price.service.property.{id,name}");
			oql.setFilter("service.productId=? and cityId=? and onSale=1");
			oql.getParameters().add("productId", productId, Types.INTEGER);
			oql.getParameters().add("cityId", cityId, Types.INTEGER);
		}
		List<Price> list = this.queryList(oql);
		return list;
	}

	@Override
	public List<Integer> findCityIdsByProductIdAndOrganizationIds(int productId, Collection<Integer> organizationIds) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT prod_price.city_id as cityId FROM `prod_price` ");
		sql.append("INNER JOIN `prod_service` ON prod_price.`service_id` = prod_service.`pkid` ");
		sql.append("INNER JOIN `prod_price_audit` ON prod_price.`price_audit_id` = prod_price_audit.`pkid` ");
		sql.append("WHERE prod_price.`is_on_sale` = 1  AND prod_service.`is_enabled` = 1 ");
		sql.append("AND prod_price_audit.product_id = "+productId+" AND prod_price.`city_id` > 0 ");
		sql.append("AND prod_price_audit.audit_status_id = 1054 ");
		if (CollectionUtils.isNotEmpty(organizationIds)) {
			String isList = StringManager.join(",", Arrays.asList(organizationIds.toArray()));
			sql.append("AND prod_price_audit.organization_id IN ("+isList+") ");
		}
		DataTable dataTable = this.pm.executeTable(sql.toString(), null);
		List<Integer> list=new ArrayList<>();
		for (IRow row : dataTable) {
			list.add(row.getInteger("cityId"));
		}
		return list;
	}
}