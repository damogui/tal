package com.gongsibao.account.base;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountWeiXin;
import org.netsharp.base.IPersistableService;
import org.netsharp.wx.pa.entity.Fans;

public interface IAccountWeiXinService extends IPersistableService<AccountWeiXin> {
	Boolean bandMobile(int accountId,String openId);
	AccountWeiXin queryByOpenId(String openId);
	AccountWeiXin queryByAccountId(String accountId);
	Fans queryFansByOpenId(String openId);
	/**
	 * @Description:TODO 订单状态变化推送微信消息
	 * @param   mobile 手机号   orderProductId 对应so_order_prod.`pkid`
	 * @return
	 * @author hbpeng <hbpeng@gongsibao.com>
	 * @date 2018/4/18 16:47
	 */
	void pushOrderStateMsg(String mobile,Integer orderPorudctId );
}