package org.netsharp.rest.weixin.response;

import com.gongsibao.account.base.IAccountService;
import com.gongsibao.entity.acount.Account;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.NetsharpException;
import org.netsharp.wx.mp.message.RequestMessage;
import org.netsharp.wx.mp.message.ResponseMessage;
import org.netsharp.wx.mp.message.request.event.EventRequest;
import org.netsharp.wx.pa.IWeixinResponsor;
import org.netsharp.wx.pa.base.IFansService;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.PublicAccount;

/**
 * 微信取消关注,禁止回复消息
 */
public class WxUnSubscribeResponse implements IWeixinResponsor {
    private static IFansService service = ServiceFactory.create(IFansService.class);
    private static IAccountService serviceAccount = ServiceFactory.create(IAccountService.class);

    private String key = "unsubscribe";

    public final String getKey() {
        return key;
    }

    public final void setKey(String value) {
        key = value;
    }

    public final boolean validate(RequestMessage request) {
        String eventKey = "";
        if (request instanceof EventRequest) {
            EventRequest evt = (EventRequest) request;
            eventKey = evt.getEvent();
        }

        return key.equalsIgnoreCase(eventKey);
    }

    /**
     * 微信取消关注,禁止回复消息（微信放档规定）
     * @param request
     * @return
     * @throws NetsharpException
     */
    public final ResponseMessage response(RequestMessage request) throws NetsharpException {
        IPublicAccountService publicAccountService=ServiceFactory.create(IPublicAccountService.class);
        PublicAccount publicAccount = publicAccountService.byOriginalId(request.getToUserName());
        service.unsubscribe(request.getFromUserName(), publicAccount);
        Fans fans=service.byOpenId(request.getFromUserName());{
            if(null!=fans.getUserId()){
                Account account=serviceAccount.byId(fans.getUserId());
                account.setIsWeiXin("0");
                account.toPersist();
                serviceAccount.save(account);
                fans.toPersist();
                fans.setUserId(null);
                service.save(fans);
            }
        }
        return null;
    }
}