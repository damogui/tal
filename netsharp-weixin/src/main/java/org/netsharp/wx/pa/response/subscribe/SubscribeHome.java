package org.netsharp.wx.pa.response.subscribe;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.wx.mp.message.ResponseMessage;
import org.netsharp.wx.mp.message.request.event.EventRequest;
import org.netsharp.wx.mp.util.StringHelper;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.NReply;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.response.PublicAccountManager;
import org.netsharp.wx.pa.response.WeixinReplyResponse;

//缺省回复消息的关键字，默认是“首页”
public class SubscribeHome implements IWeixinSubscriber {

	protected static Log logger = LogFactory.getLog("weixin."+SubscribeHome.class);
    NReply reply = null;

    @Override
    public boolean validate(EventRequest request, Fans fans, PublicAccount publicAccount) {
        //
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

    @Override
    public ResponseMessage reply(EventRequest request, Fans fans, PublicAccount publicAccount,int sceneId) {

        WeixinReplyResponse response = new WeixinReplyResponse();
        return response.response(reply, request);
    }
}
