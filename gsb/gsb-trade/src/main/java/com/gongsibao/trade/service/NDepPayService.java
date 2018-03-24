package com.gongsibao.trade.service;

import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.trade.base.INDepPayService;

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
}
