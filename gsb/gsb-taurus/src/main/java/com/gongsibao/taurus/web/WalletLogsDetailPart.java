package com.gongsibao.taurus.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.DetailPart;

import com.gongsibao.entity.taurus.UserWalletLog;
import com.gongsibao.entity.taurus.dic.PaymentType;
import com.gongsibao.taurus.base.IUserWalletLogService;

public class WalletLogsDetailPart extends DetailPart {

	IUserWalletLogService service = ServiceFactory.create(IUserWalletLogService.class);

	public boolean recharge(UserWalletLog walletLog) {

		Integer userId = walletLog.getUserId();
		PaymentType paymentType = walletLog.getPaymentType();
		Integer rechargeAmount = walletLog.getPrice();
		Integer discountAmount = walletLog.getDiscountAmount();
		String remark = walletLog.getRemark();
		return service.recharge(userId, paymentType, rechargeAmount, discountAmount, remark);
	}

	public boolean revocation(Integer logId) {

		return service.revocation(logId);
	}
}
