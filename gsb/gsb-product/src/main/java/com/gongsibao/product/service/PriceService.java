package com.gongsibao.product.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.Price;
import com.gongsibao.product.base.IPriceService;

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
}