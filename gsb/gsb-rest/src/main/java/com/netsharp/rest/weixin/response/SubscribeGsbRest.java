package com.netsharp.rest.weixin.response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.wx.mp.message.ResponseMessage;
import org.netsharp.wx.mp.message.request.event.EventRequest;
import org.netsharp.wx.mp.message.request.event.SubscribeEvent;
import org.netsharp.wx.mp.util.StringHelper;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.NReply;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.response.PublicAccountManager;
import org.netsharp.wx.pa.response.WeixinReplyResponse;
import org.netsharp.wx.pa.response.subscribe.IWeixinSubscriber;
/**
 * ClassName: SubscribeGsbRest
 * @Description: TODO 默认关注
 * @author hbpeng <hbpeng@gongsibao.com>
 * @date 2018/4/20 17:53
 */
public class SubscribeGsbRest implements IWeixinSubscriber {
    protected static Log logger = LogFactory.getLog(SubscribeEvent.class);
    NReply reply = null;
    public boolean validate(EventRequest request, Fans fans, PublicAccount publicAccount){
        String subscribeCode = publicAccount.getSubscribeCode();
        logger.warn("公众号关注回复关键字："+subscribeCode);
        if (StringHelper.isNullOrEmpty(subscribeCode)) {
            return false;
        }

        reply = PublicAccountManager.getInstance().getReply(subscribeCode, request.getToUserName());

        if(reply==null){
            logger.warn("匹配图文：false");
        }else{
            logger.warn("匹配图文："+reply.getKeyword()+","+reply.getClass().getName());
        }

        return reply != null;
    }
    public ResponseMessage reply(EventRequest request, Fans fans, PublicAccount publicAccount, int sceneId){
        logger.warn("rest subcribe send msg!");
        WeixinReplyResponse response = new WeixinReplyResponse();
        return response.response(reply, request);
    }


}
