package com.gongsibao.product.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.Product;
import com.gongsibao.product.base.IProductService;

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
    
}