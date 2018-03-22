package com.gongsibao.trade.service;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.Pay;
import com.gongsibao.trade.base.IPayService;

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

}