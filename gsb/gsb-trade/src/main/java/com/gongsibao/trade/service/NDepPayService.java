package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.Date;
import java.util.List;

import com.gongsibao.entity.trade.dic.AuditStatusType;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.trade.base.INDepPayService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NDepPayService extends PersistableService<NDepPay> implements INDepPayService {

    public NDepPayService(){
        super();
        this.type=NDepPay.class;
    }

	@Override
	public Boolean applyPayPerformance(List<NDepPay> depPayList) {
		
		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/crm/order/pay/performance");
			ctx.setItem(depPayList);
		}
		ActionManager action = new ActionManager();
		action.execute(ctx);
		return true;
	}

    @Override
    public void updateStatus(Integer id, AuditStatusType auditStatusType) {

        UpdateBuilder updateBuilder = new UpdateBuilder();
        {
            updateBuilder.update("n_dep_pay");
            updateBuilder.set("status", auditStatusType.getValue());
            updateBuilder.set ("audit_time",new Date ());
            updateBuilder.where("order_id=?");
        }
        String sql = updateBuilder.toSQL();
        QueryParameters qps = new QueryParameters();
        qps.add("@order_id", id, Types.INTEGER);
        this.pm.executeNonQuery(sql, qps);
    }
}
