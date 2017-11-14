package com.gongsibao.taurus.service;

import java.util.Date;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.taurus.User;
import com.gongsibao.entity.taurus.UserWalletLog;
import com.gongsibao.entity.taurus.dic.PaymentType;
import com.gongsibao.entity.taurus.dic.WalletType;
import com.gongsibao.taurus.base.IUserService;
import com.gongsibao.taurus.base.IUserWalletLogService;

@Service
public class UserWalletLogService extends GsbPersistableService<UserWalletLog> implements IUserWalletLogService {

	IUserService userService = ServiceFactory.create(IUserService.class);

	public UserWalletLogService() {
		super();
		this.type = UserWalletLog.class;
	}

	@Override
	public boolean recharge(Integer userId, PaymentType paymentType, Integer rechargeAmount, Integer discountAmount, String remark) {

		User user = userService.byId(userId);
		if (user == null) {

			throw new BusinessException("用户不存在");
		}

//		if (rechargeAmount == null || rechargeAmount.compareTo(0) == 0) {
//
//			throw new BusinessException("充值金额不能为0");
//		}

		rechargeAmount = rechargeAmount * 100;// 转换为分
		discountAmount = discountAmount == null ? 0 : discountAmount * 100;// 赠送金额

		// 充值日志
		UserWalletLog rechargeLog = this.newInstance();
		{
			rechargeLog.toNew();
			rechargeLog.setUserId(userId);
			rechargeLog.setType(WalletType.RECHARGE);
			rechargeLog.setPrice(rechargeAmount);
			rechargeLog.setPaymentType(paymentType);
			rechargeLog.setRemark(remark);
			this.save(rechargeLog);
		}

		// 赠送日志
		
		if(discountAmount >0){

			UserWalletLog discountLog = this.newInstance();
			{
				discountLog.toNew();
				discountLog.setUserId(userId);
				discountLog.setType(WalletType.DISCOUNT);
				discountLog.setPrice(discountAmount);
				discountLog.setPaymentType(PaymentType.UNKNOWN);
				discountLog.setRemark(remark);
				this.save(discountLog);
			}
		}

		// 更新用户余额
		Integer userAmount = user.getAmount() + rechargeAmount + discountAmount;
		user.setAmount(userAmount);
		userService.save(user);
		return false;
	}

	@Override
	public boolean revocation(Integer logId) {

		UserWalletLog discountLog = this.byId(logId);
		if (discountLog == null) {

			throw new BusinessException("充值记录不存在");
		}

		discountLog.setUndone(true);
		this.save(discountLog);

		User user = userService.byId(discountLog.getUserId());
		if (user != null) {

			Integer amount = user.getAmount() - discountLog.getPrice();
			amount = amount < 0 ? 0 : amount;
			user.setAmount(amount);
			userService.save(user);
		}
		return false;
	}

	public UserWalletLog newInstance() {
		UserWalletLog entity = new UserWalletLog();
		entity.toNew();
		entity.setCreateTime(new Date());
		entity.setCreator(SessionManager.getUserName());
		entity.setCreatorId(SessionManager.getUserId());
		return entity;
	}
}
