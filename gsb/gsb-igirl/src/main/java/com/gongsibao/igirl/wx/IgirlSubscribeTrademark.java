package com.gongsibao.igirl.wx;

import com.gongsibao.account.base.IAccountService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.igirl.tm.TradeMarkCase;
import com.gongsibao.igirl.tm.base.ITradeMarkCaseService;
import com.gongsibao.igirl.tm.service.TradeMarkCaseService;

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
public class IgirlSubscribeTrademark implements IWeixinSubscriber {
    protected static Log logger = LogFactory.getLog(SubscribeEvent.class);
    IAccountService accountService = ServiceFactory.create(IAccountService.class);
    public boolean validate(EventRequest request, Fans fans, PublicAccount publicAccount){
        SubscribeEvent eventRequest = (SubscribeEvent) request;
        String  sceneStr=eventRequest.getSceneStr();
        logger.error("微信关注商标sceneStr：" + sceneStr);
        logger.error("微信关注商标eventKey：" + eventRequest.getEventKey());
        IFansService fansService= ServiceFactory.create(IFansService.class);
        IAccountService accountService=ServiceFactory.create(IAccountService.class);
        //需要业务处理粉丝与账户关联 wx_pa_fans
        if(sceneStr==null){
            return false;
        }else{
            String[] param=sceneStr.split("\\|");
            if(param[2].equals("SB")){
                accountService.updateAccount(param[0],fans.getOpenId());
                return true;
            }else{
                return false;
            }
        }
    }
    public ResponseMessage reply(EventRequest request, Fans fans, PublicAccount publicAccount, int sceneId){
        SubscribeEvent eventRequest = (SubscribeEvent) request;
        String  sceneStr=eventRequest.getSceneStr();
        String caseId =sceneStr.split("\\|")[1];
        ITradeMarkCaseService caseService= ServiceFactory.create(ITradeMarkCaseService.class);
        TradeMarkCase tmc=caseService.byId(caseId);
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
            article.setUrl( "http://beehive.gongsibao.com//gsb/igirl/mobile/main.html#/progresslist?spid="+tmc.getSupplierId()+"&source=case&casecode="+tmc.getCode());
        }
        news.getArticles().add(article);

        return news;
    }


}
