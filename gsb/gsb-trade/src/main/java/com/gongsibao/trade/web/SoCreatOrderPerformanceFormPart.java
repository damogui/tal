package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.List;

import com.gongsibao.trade.base.INOrderAndPerformanceService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameter;
import org.netsharp.core.QueryParameters;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormNavigation;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.INDepReceivableService;

/**
 * Created by win on 2018/3/5.
 */
/*创建订单业绩*/
public class SoCreatOrderPerformanceFormPart extends FormPart {


    @Override
    public IPersistable save(IPersistable obj) {
        SoOrder entity = (SoOrder) obj;
        INOrderAndPerformanceService nOrderAndPerformanceService = ServiceFactory.create (INOrderAndPerformanceService.class);//服务
        return nOrderAndPerformanceService.saveOrderPerformance (entity);

    }

    @Override
    public FormNavigation byId(Object id) {
        if (id == null) {

            id = "0";
        }
        Oql oql = new Oql ();
        this.getService ();
        StringBuilder sb = new StringBuilder ();
        sb.append ("SoOrder.*");
        oql.setSelects (sb.toString ());
        oql.setType (SoOrder.class);
        oql.setFilter ("pkid=?");
        oql.getParameters ().add ("@pkid", id, Types.INTEGER);
        IPersistable obj = service.queryFirst (oql);
        FormNavigation navigation = this.createFormNavigation (id);

        if (obj == null) {
            navigation.Entity = this.newInstance (null);
        } else {
            navigation.Entity = obj;
        }

        return navigation;


    }


    public IPersistable saveold(IPersistable obj) {
        INDepReceivableService nDepReceivableService = ServiceFactory.create (INDepReceivableService.class);//订单业绩服务
        IPersister<SoOrder> orderService = PersisterFactory.create ();
        SoOrder entity = (SoOrder) obj;
        List<NDepReceivable> depList = entity.getDepReceivable ();
        int totalAmount = 0;
        for (NDepReceivable item : depList
                ) {
            if (!item.getEntityState ().equals (EntityState.Deleted)) {

                totalAmount += item.getAmount ();
            }

        }
        totalAmount = totalAmount / 100;
        nDepReceivableService.saves (depList);
        String sql = "UPDATE  so_order  SET  performance_price=?  WHERE  pkid=? ;";
        QueryParameters qps = new QueryParameters ();
        qps.add ("@performance_price", totalAmount, Types.INTEGER);
        qps.add ("@pkid", entity.getId (), Types.INTEGER);
        orderService.executeNonQuery (sql, qps);
        return obj;
    }

}
