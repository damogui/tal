package com.gongsibao.trade.service.action.order.pay;

import com.gongsibao.entity.trade.NU8BankSoPayMap;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.dic.PayWayType;
import com.gongsibao.entity.u8.U8Bank;
import com.gongsibao.trade.base.INU8BankSoPayMapService;
import com.gongsibao.u8.base.IU8BankService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import java.util.Date;

/*创建回款通知*/
public class ActionOnlinePayU8 implements IAction{

	IU8BankService u8BankService = ServiceFactory.create(IU8BankService.class);
	INU8BankSoPayMapService inu8BankSoPayMapService = ServiceFactory.create(INU8BankSoPayMapService.class);

	@Override
	public void execute(ActionContext ctx) {

		Pay pay = (Pay) ctx.getStatus().get("pay");
		if (pay.getPayWayType().equals(PayWayType.ONLINE_PAYMENT)) {
			U8Bank u8Bank = u8BankService.getByOnlineBankCode(pay.getOnlineBankCodeId());
			if (null != u8Bank) {
				NU8BankSoPayMap u8BankSoPayMap = new NU8BankSoPayMap();
				u8BankSoPayMap.toNew();
				u8BankSoPayMap.setPrice(pay.getAmount());
				u8BankSoPayMap.setPayId(pay.getId());
				u8BankSoPayMap.setType(0);//类别（0：支付 1：退款）
				u8BankSoPayMap.setU8BankId(u8Bank.getId());
				u8BankSoPayMap.setSetOfBooksId(u8Bank.getSetOfBooksId());
				u8BankSoPayMap.setCreateTime(new Date());
				inu8BankSoPayMapService.save(u8BankSoPayMap);
			}
		}
	}

}
