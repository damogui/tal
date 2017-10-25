package org.netsharp.wx.pa.base;

import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.NetsharpException;
import org.netsharp.wx.pa.entity.NWeixinSubscriber;
import org.netsharp.wx.pa.entity.PublicAccount;

public interface IPublicAccountService extends IPersistableService<PublicAccount> {
	
    PublicAccount byOriginalId(String oid) throws NetsharpException;
    PublicAccount byAppId(String appId) throws NetsharpException;
    List<NWeixinSubscriber> querySubscribers(Integer publicAccountId);
}
