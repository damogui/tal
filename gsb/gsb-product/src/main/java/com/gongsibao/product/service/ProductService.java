package com.gongsibao.product.service;

import java.sql.Types;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.Product;
import com.gongsibao.product.base.IProductService;
import org.netsharp.util.StringManager;

@Service
public class ProductService extends PersistableService<Product> implements IProductService {

    public ProductService(){
        super();
        this.type=Product.class;
    }
    
    @Override
	public Boolean updateEnabled(Integer id, Boolean state) {

		String cmdText = "UPDATE prod_product set is_enabled=? where pkid=?";
		QueryParameters qps = new QueryParameters();
//		int enabled = 0;
//		if(state == true){
//			enabled = 1;
//			qps.add("enabled",enabled,Types.INTEGER);
//		}else{
//			qps.add("enabled",enabled,Types.INTEGER);
//		}
		qps.add("enabled",state,Types.BOOLEAN);
		qps.add("id", id, Types.INTEGER);
		return this.pm.executeNonQuery(cmdText, qps) > 0;
	}

	@Override
	public List<Product> byIds(Collection<Integer> ids) {
		if (null == ids || ids.isEmpty()) {
			return null;
		}
		Oql oql = new Oql();
		oql.setType(this.type);
		oql.setSelects("*");
		oql.setFilter("id IN (" + StringManager.join(",", Arrays.asList(ids.toArray())) +") ");
		return this.queryList(oql);
	}

}