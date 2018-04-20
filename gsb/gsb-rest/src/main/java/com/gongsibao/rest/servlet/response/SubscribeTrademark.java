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
import org.netsharp.wx.pa.base.IFansService;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.response.subscribe.IWeixinSubscriber;
/**
 * ClassName: SubscribeTrademark
 * @Description: TODO 商标用户扫码关注处理
 * @author hbpeng <hbpeng@gongsibao.com>
 * @date 2018/4/20 17:53
 */
public class SubscribeTrademark implements IWeixinSubscriber {
    protected static Log logger = LogFactory.getLog(SubscribeEvent.class);
    public boolean validate(EventRequest request, Fans fans, PublicAccount publicAccount){
        SubscribeEvent eventRequest = (SubscribeEvent) request;
        String  sceneStr=eventRequest.getSceneStr();
        logger.error("微信关注商标sceneStr：" + sceneStr);
        logger.error("微信关注商标eventKey：" + eventRequest.getEventKey());
        IFansService fansService= ServiceFactory.create(IFansService.class);
        IAccountService accountService=ServiceFactory.create(IAccountService.class);
        //需要业务处理粉丝与账户关联 wx_pa_fans
        if(sceneStr==null){
            return true;
        }else{
            String[] param=sceneStr.split("|");
            if(param[2].equals("SB")){
                Account account=accountService.byMobile(param[1]);
                fans.setUserId(account.getId());
                fansService.updateFans(fans);
                return true;
            }else{
                return false;
            }
        }
    }
    public ResponseMessage reply(EventRequest request, Fans fans, PublicAccount publicAccount, int sceneId){
        SubscribeEvent eventRequest = (SubscribeEvent) request;
        NewsResponse news = new NewsResponse();
        {
            news.setToUserName(request.getFromUserName());
            news.setFromUserName(request.getToUserName());
        }
        Article article = new Article();
        {
            article.setTitle( "商标进度");
            article.setDescription(eventRequest.getSceneStr()+"查看进度"+eventRequest.getEventKey());
            article.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524215171441&di=898f1b96ff4caad290f171666e725985&imgtype=0&src=http%3A%2F%2Fscimg.jb51.net%2Fallimg%2F151219%2F13-15121922361b92.jpg");
            article.setUrl( "http://icompany.gongsibao.net/index.html#/mine/couponlist");
        }
        news.getArticles().add(article);

        return news;
    }
}
