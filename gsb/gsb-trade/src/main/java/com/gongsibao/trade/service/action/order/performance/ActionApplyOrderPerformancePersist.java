package com.gongsibao.trade.service.action.order.performance;

import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.INDepReceivableService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import java.sql.Types;
import java.util.List;

/*订单业绩的保存*/
public class ActionApplyOrderPerformancePersist  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		// TODO Auto-generated method stub
        SoOrder entity = (SoOrder) ctx.getItem();//进行校验金额

        INDepReceivableService nDepReceivableService = ServiceFactory.create (INDepReceivableService.class);//订单业绩服务
        IPersister<SoOrder> orderService = PersisterFactory.create();

        List<NDepReceivable> depList = entity.getDepReceivable ();
//        int totalAmount=0;
//        for (NDepReceivable item:depList
//                ) {
//            if (!item.getEntityState ().equals (EntityState.Deleted)){
//
//                totalAmount+=item.getAmount ();
//            }
//
//        }
//        totalAmount=totalAmount/100;
        nDepReceivableService.saves (depList);
        String sql = "UPDATE  so_order  SET  performance_price=?  WHERE  pkid=? ;";//订单业绩只能一次
        QueryParameters qps = new QueryParameters();
        qps.add("@performance_price", entity.getPerformancePrice (), Types.INTEGER);//订单业绩校验已经处理过累加
        qps.add("@pkid", entity.getId (), Types.INTEGER);
        orderService.executeNonQuery(sql, qps);
       // return entity;

	}

}
