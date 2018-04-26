package com.gongsibao.rest.servlet.response;

import com.gongsibao.account.base.IAccountService;
import com.gongsibao.entity.acount.Account;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.wx.mp.message.ResponseMessage;
import org.netsharp.wx.mp.message.request.event.EventRequest;
import org.netsharp.wx.mp.message.request.event.SubscribeEvent;
import org.netsharp.wx.mp.message.response.Article;
import org.netsharp.wx.mp.message.response.NewsResponse;
import org.netsharp.wx.mp.message.response.TextResponse;
import org.netsharp.wx.mp.util.StringHelper;
import org.netsharp.wx.pa.base.IFansService;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.NReply;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.response.PublicAccountManager;
import org.netsharp.wx.pa.response.WeixinReplyResponse;
import org.netsharp.wx.pa.response.subscribe.IWeixinSubscriber;
/**
 * ClassName: SubscribeTrademark
 * @Description: TODO 商标用户扫码关注处理
 * @author hbpeng <hbpeng@gongsibao.com>
 * @date 2018/4/20 17:53
 */
public class SubscribeGsbRest implements IWeixinSubscriber {
    protected static Log logger = LogFactory.getLog(SubscribeEvent.class);
    NReply reply = null;
    public boolean validate(EventRequest request, Fans fans, PublicAccount publicAccount){
        String subscribeCode = publicAccount.getSubscribeCode();
        logger.error("公众号关注回复关键字："+subscribeCode);
        if (StringHelper.isNullOrEmpty(subscribeCode)) {
            return false;
        }

        reply = PublicAccountManager.getInstance().getReply(subscribeCode, request.getToUserName());

        if(reply==null){
            logger.error("匹配图文：false");
        }else{
            logger.error("匹配图文："+reply.getKeyword()+","+reply.getClass().getName());
        }

        return reply != null;
    }
    public ResponseMessage reply(EventRequest request, Fans fans, PublicAccount publicAccount, int sceneId){
        logger.error("rest subcribe send msg!");
        WeixinReplyResponse response = new WeixinReplyResponse();
        return response.response(reply, request);
    }


}
