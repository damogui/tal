package com.gongsibao.trade.service.action.order.performance;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dto.DepPayMapDTO;
import com.gongsibao.entity.trade.dto.OrderRelationDTO;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.u8.base.ISoOrderService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import java.sql.Types;
import java.util.List;

/*创建订单业绩校验*/
public class ActionApplyOrderPerformanceVerify implements IAction {

    @Override
    public void execute(ActionContext ctx) {
        // TODO Auto-generated method stub

        SoOrder entity = (SoOrder) ctx.getItem ();//进行校验金额
        //根据订单Id获取订单实体
        //IOrderService orderService = ServiceFactory.create (IOrderService.class);
        IPersister<AuditLog> auditLogService = PersisterFactory.create ();

/*处于审核状态的订单不能再被审核*/
        String sql = "SELECT  IFNULL(MAX(form_id),0) FROM  bd_audit_log  WHERE  type_id=1050  AND     form_id=?";//查询是否存在订单业绩审核状态
        QueryParameters qps = new QueryParameters ();
        qps.add ("@form_id", entity.getId (), Types.INTEGER);
        Integer execNum = auditLogService.executeInt (sql, qps);
        if (execNum > 0) {

            throw new BusinessException (String.format ("订单号:%s正处于订单业绩审核状态", execNum));

        }
        if (entity.getDepReceivable ().size () == 0) {
            throw new BusinessException ("订单业绩必须没分配！");
        }


        List<NDepReceivable> depList = entity.getDepReceivable ();
        int totalAmount = 0;
        for (NDepReceivable item : depList
                ) {
            if (!item.getEntityState ().equals (EntityState.Deleted)) {

                totalAmount += item.getAmount ();
            }

        }
        totalAmount = totalAmount / 100;

        if (entity.getDepReceivable ().size () == 0) {
            throw new BusinessException ("订单业绩必须没分配！");
        }

        if (entity.getTotalPrice () < totalAmount) {


            throw new BusinessException ("订单业绩必须小于订单额！");
        }
        entity.setPerformancePrice (totalAmount);
        ctx.setItem (entity);


    }

}
