package com.gongsibao.reporting;


import com.gongsibao.entity.trade.OrderProd;
import org.junit.Test;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.Persister;
import org.netsharp.persistence.PersisterFactory;

import java.util.List;

public class SalesOrderTest {

    @Test
    public void ttttt(){


        IPersister<OrderProd> pm = PersisterFactory.create();

        Oql oql = new Oql();
        {
            oql.setType(OrderProd.class);
            oql.setSelects("*,soOrder.*,product.*");
            oql.setPaging(new Paging(1,10));
            oql.setOrderby("id desc");
        }

        List<OrderProd> items = pm.queryList(oql);

        for(OrderProd item : items){
//            System.out.println(item.getProductName());
//            System.out.println(item.getPrice());
            System.out.println(item.getId());
            System.out.println(item.getProduct().getName());
        }

    }

}
