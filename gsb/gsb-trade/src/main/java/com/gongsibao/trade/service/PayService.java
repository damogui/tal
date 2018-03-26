package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.dic.AuditStatusType;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.trade.base.IPayService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import java.sql.Types;

@Service
public class PayService extends PersistableService<Pay> implements IPayService {

	public PayService() {
		super();
		this.type = Pay.class;
	}

	// @Override
	// public List<Pay> queryList(Oql oql) {
	// oql.setSelects
	// ("orderIds,id,payForOrderCount,payWayType,amount,u8BankId,offlineAuditStatus,createTime,creator,orderPayMaps.*,u8Bank.*");
	// // oql.setSelects ("u8Bank.*,");
	// // oql.setSelects ("orderPayMaps.*");
	// //List<Pay> list= super.queryList (oql);
	//
	//
	// List<Pay> list = super.queryList (oql);
	//
	// return list;
	// }

	@Override
	public Boolean applyPay(Pay pay) {

		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/crm/order/pay");
			ctx.setItem(pay);
			ctx.setState(pay.getEntityState());
		}
		ActionManager action = new ActionManager();
		action.execute(ctx);
		return true;
	}

    @Override
    public void updateStatus(Integer id, AuditStatusType auditStatusType) {

        UpdateBuilder updateBuilder = new UpdateBuilder();
        {
            updateBuilder.update("so_contract");
            updateBuilder.set("so_pay", auditStatusType.getValue());
            updateBuilder.where("pkid=?");
        }
        String sql = updateBuilder.toSQL();
        QueryParameters qps = new QueryParameters();
        qps.add("id", id, Types.INTEGER);
        this.pm.executeNonQuery(sql, qps);
    }

}