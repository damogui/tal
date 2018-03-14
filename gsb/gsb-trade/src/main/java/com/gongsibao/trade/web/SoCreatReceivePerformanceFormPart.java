package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.List;

import com.gongsibao.entity.trade.dto.DepPayMapDTO;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.QueryParameters;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.INDepReceivableService;

/**
 * Created by win on 2018/3/8.
 */
/*回款业绩的save操作*/
public class SoCreatReceivePerformanceFormPart extends FormPart {
    @Override
    public IPersistable save(IPersistable obj) {
        INDepReceivableService nDepReceivableService = ServiceFactory.create (INDepReceivableService.class);//订单业绩服务
        IPersister<SoOrder> orderService = PersisterFactory.create();
        SoOrder entity = (SoOrder) obj;
        List<NDepReceivable> depList = entity.getDepReceivable ();
        int totalAmount=0;
        for (NDepReceivable item:depList
                ) {
            if (!item.getEntityState ().equals (EntityState.Deleted)){

                totalAmount+=item.getAmount ();
            }

        }
        totalAmount=totalAmount/100;
        nDepReceivableService.saves (depList);
        String sql = "UPDATE  so_order  SET  performance_price=?  WHERE  pkid=? ;";
        QueryParameters qps = new QueryParameters();
        qps.add("@performance_price", totalAmount, Types.INTEGER);
        qps.add("@pkid", entity.getId (), Types.INTEGER);
        orderService.executeNonQuery(sql, qps);
        return obj;
    }


    /*回款业绩保存*/
    public int saveNDepReceivableBySoder(DepPayMapDTO entity) {

        return 1;
    }
}
