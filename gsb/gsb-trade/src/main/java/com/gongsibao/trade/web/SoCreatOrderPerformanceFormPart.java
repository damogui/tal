package com.gongsibao.trade.web;

import com.gongsibao.entity.supplier.FunctionModuleRole;
import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.INDepReceivableService;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.u8.base.ISoOrderService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.QueryParameters;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import java.sql.Types;
import java.util.List;

/**
 * Created by win on 2018/3/5.
 */
/*创建订单业绩*/
public class SoCreatOrderPerformanceFormPart extends FormPart {


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
        int numObjectRoleGroup = orderService.executeNonQuery(sql, qps);
        return obj;
    }

}
