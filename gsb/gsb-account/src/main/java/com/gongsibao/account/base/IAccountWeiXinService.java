package com.gongsibao.account.base;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountWeiXin;
import org.netsharp.base.IPersistableService;
import org.netsharp.wx.pa.entity.Fans;

public interface IAccountWeiXinService extends IPersistableService<AccountWeiXin> {
	Boolean bandMobile(int accountId,String openId);
	AccountWeiXin queryByOpenId(String openId);
	Fans queryFansByOpenId(String openId);
}