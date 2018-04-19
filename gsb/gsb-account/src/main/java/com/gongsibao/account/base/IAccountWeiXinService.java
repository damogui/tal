package com.gongsibao.account.base;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountWeiXin;
import com.gongsibao.entity.acount.AccountWxMsg;
import org.netsharp.base.IPersistableService;
import org.netsharp.wx.pa.entity.Fans;

public interface IAccountWeiXinService extends IPersistableService<AccountWeiXin> {
	Boolean bandMobile(int accountId,String openId);
	Account queryByOpenId(String openId);
	Fans queryFansByUserId(Integer userId);
	Fans queryFansByOpenId(String openId);
	Fans createFans(String openId);
	/**
	 * @Description:TODO 订单状态变化推送微信消息
	 * @param   mobile 手机号   orderProductId 对应so_order_prod.`pkid`
	 * @return
	 * @author hbpeng <hbpeng@gongsibao.com>
	 * @date 2018/4/18 16:47
	 */
	void pushOrderStateMsg(String mobile,Integer orderPorudctId );

	void pushTextMsg(Integer accountId, String first, String keyword1, String keyword2, String date,String url, String remark, AccountWxMsg tmpId);
}