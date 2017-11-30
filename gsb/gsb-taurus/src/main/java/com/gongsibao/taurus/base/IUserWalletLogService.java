package com.gongsibao.taurus.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.taurus.UserWalletLog;
import com.gongsibao.entity.taurus.dic.PaymentType;

public interface IUserWalletLogService extends IPersistableService<UserWalletLog> {

	@Transaction
	boolean recharge(Integer userId, PaymentType paymentType, Integer rechargeAmount, Integer discountAmount, String remark);

	@Transaction
	boolean revocation(Integer logId);
}
