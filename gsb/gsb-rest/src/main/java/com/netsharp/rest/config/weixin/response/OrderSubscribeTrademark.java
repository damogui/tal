package com.netsharp.rest.config.weixin.response;

import com.gongsibao.account.base.IAccountService;
import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.trade.base.ICustomerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.wx.mp.message.ResponseMessage;
import org.netsharp.wx.mp.message.request.event.EventRequest;
import org.netsharp.wx.mp.message.request.event.SubscribeEvent;
import org.netsharp.wx.pa.base.IFansService;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.response.subscribe.IWeixinSubscriber;

/**
 * ClassName: OrderSubscribeTrademark
 * @Description: TODO 订单扫码关注支付
 * @author hbpeng <hbpeng@gongsibao.com>
 * @date 2018/4/27 15:03
 */
public class OrderSubscribeTrademark implements IWeixinSubscriber {
    protected static Log logger = LogFactory.getLog(SubscribeEvent.class);
    IAccountService accountService = ServiceFactory.create(IAccountService.class);
    ICustomerService customerService = ServiceFactory.create(ICustomerService.class);

    public boolean validate(EventRequest request, Fans fans, PublicAccount publicAccount){
        SubscribeEvent eventRequest = (SubscribeEvent) request;
        String  sceneStr=eventRequest.getSceneStr();
        logger.error("订单扫码关注sceneStr：" + sceneStr);
        logger.error("订单扫码关注eventKey：" + eventRequest.getEventKey());
        IFansService fansService= ServiceFactory.create(IFansService.class);
        IAccountService accountService=ServiceFactory.create(IAccountService.class);
        //需要业务处理粉丝与账户关联 wx_pa_fans
        if(sceneStr==null){
            return false;
        }else{
            String[] param=sceneStr.split("\\|");
            if(param[2].equals("ORDER")){
                Account account = accountService.updateAccount(param[0], fans.getOpenId(),sceneStr);
                if (null != account) {
                    customerService.saveByAccount(account, 4110218);
                }
                return true;
            }else{
                return false;
            }
        }
    }
    public ResponseMessage reply(EventRequest request, Fans fans, PublicAccount publicAccount, int sceneId){
        SubscribeEvent eventRequest = (SubscribeEvent) request;
        String  sceneStr=eventRequest.getSceneStr();
        String mobile =sceneStr.split("\\|")[0];
        String orderId =sceneStr.split("\\|")[1];
        IAccountWeiXinService weiXinService= ServiceFactory.create(IAccountWeiXinService.class);
        weiXinService.saveOrderMsg(mobile,Integer.valueOf(orderId));
        return null;
    }


}
