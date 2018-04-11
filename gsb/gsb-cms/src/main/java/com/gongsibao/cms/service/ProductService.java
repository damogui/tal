package com.gongsibao.cms.service;

import com.gongsibao.entity.bd.BdService;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IProductService;
import com.gongsibao.entity.cms.Product;
import org.netsharp.util.StringManager;

import java.sql.Types;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Service
public class ProductService extends PersistableService<Product> implements IProductService {

    public ProductService(){
        super();
        this.type=Product.class;
    }

    @Override
    public List<Product> getOnlineByProductIds(Collection<Integer> productIds) {
        Oql oql = new Oql();
        {
            String isList = StringManager.join(",", Arrays.asList(productIds.toArray()));
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("productId in   (" + isList + ")");
        }
        return this.queryList(oql);
    }
}