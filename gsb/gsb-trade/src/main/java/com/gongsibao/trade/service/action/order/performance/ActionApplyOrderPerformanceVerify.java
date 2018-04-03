package com.gongsibao.trade.service.action.order.performance;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.dto.DepPayMapDTO;
import com.gongsibao.entity.trade.dto.OrderRelationDTO;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.trade.service.action.order.utils.AuditHelper;
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

        Integer execNum = AuditHelper.getRecode (entity.getId (), AuditLogType.DdYjSq.getValue ());
        if (execNum > 0) {

            throw new BusinessException ("订单业绩已经创建");//execNum

        }



        List<NDepReceivable> depList = entity.getDepReceivable ();
        int totalAmount = 0;
        for (NDepReceivable item : depList
                ) {
            if (!item.getEntityState ().equals (EntityState.Deleted)) {

                totalAmount += item.getAmount ();
            }
            item.setStatusType (AuditStatusType.Dsh);

        }
        //totalAmount = totalAmount / 100;//数据库以分进行保存

        if (totalAmount == 0) {
            throw new BusinessException ("订单业绩必须分配！");
        }

        if (entity.getTotalPrice () < totalAmount) {


            throw new BusinessException ("订单业绩必须小于订单额！");
        }
        //entity.setPerformancePrice (totalAmount);
        ctx.setItem (entity);


    }

}
