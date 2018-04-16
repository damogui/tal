package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.trade.OrderProdOrganizationMap;
import com.gongsibao.trade.base.IOrderProdOrganizationMapService;

@Service
public class OrderProdOrganizationMapService extends PersistableService<OrderProdOrganizationMap> implements IOrderProdOrganizationMapService {

    public OrderProdOrganizationMapService(){
        super();
        this.type=OrderProdOrganizationMap.class;
    }

	@Override
	public Boolean updateOrganizationMap(Integer orderProdId, Integer supplierId) {
		UpdateBuilder updateBuilder = UpdateBuilder.getInstance();
		{
			updateBuilder.update("so_order_prod_organization_map");
			updateBuilder.set("supplier_id", supplierId);
			updateBuilder.where("order_prod_id =" + orderProdId );
		}
		String sql = updateBuilder.toSQL();
		return this.pm.executeNonQuery(sql, null)>0;
	}
}