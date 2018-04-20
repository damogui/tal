package com.gongsibao.rest.servlet.response;

import org.netsharp.wx.mp.message.ResponseMessage;
import org.netsharp.wx.mp.message.request.event.EventRequest;
import org.netsharp.wx.mp.message.response.Article;
import org.netsharp.wx.mp.message.response.NewsResponse;
import org.netsharp.wx.mp.message.response.TextResponse;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.response.subscribe.IWeixinSubscriber;

public class SubscribeTrademark implements IWeixinSubscriber {
    public boolean validate(EventRequest request, Fans fans, PublicAccount publicAccount){
//        String sceneStr=fans.getMemoto();
//        if(sceneStr==null){
//            return false;
//        }else{
//            String[] param=sceneStr.split("|");
//            if(param[2].equals("SB")){
//                return true;
//            }else{
//                return false;
//            }
//        }
        return true;

    }
    public ResponseMessage reply(EventRequest request, Fans fans, PublicAccount publicAccount, int sceneId){
        NewsResponse news = new NewsResponse();
        {
            news.setToUserName(request.getFromUserName());
            news.setFromUserName(request.getToUserName());
        }
        Article article = new Article();
        {
            article.setTitle( "商标进度");
            article.setDescription("查看进度");
            article.setPicUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1524215171441&di=898f1b96ff4caad290f171666e725985&imgtype=0&src=http%3A%2F%2Fscimg.jb51.net%2Fallimg%2F151219%2F13-15121922361b92.jpg");
            article.setUrl( "http://icompany.gongsibao.net/index.html#/mine/couponlist");
        }
        news.getArticles().add(article);

        return news;
    }
}
