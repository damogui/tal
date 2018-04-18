package com.gongsibao.rest.controller.v1.message;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.rest.common.apiversion.Api;
import com.gongsibao.rest.common.util.RedisClient;
import com.gongsibao.rest.common.web.Constant;
import com.gongsibao.rest.common.web.ResponseData;
import com.gongsibao.rest.service.user.IAccountService;
import com.gongsibao.rest.service.user.impl.AccountService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.util.EncodeUtil;
import org.netsharp.util.JsonManage;
import org.netsharp.wx.mp.api.Response;
import org.netsharp.wx.mp.api.accesstoken.AccessToken;
import org.netsharp.wx.mp.api.accesstoken.AccessTokenManage;
import org.netsharp.wx.mp.api.customessage.CustomMessageRequest;
import org.netsharp.wx.mp.api.customessage.TextCustomerMessageRequest;
import org.netsharp.wx.pa.base.ICustomService;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.netsharp.wx.pa.response.PublicAccountManager;
import org.netsharp.wx.pa.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wx/{v}/message")
@Api(1)
public class MessageController {
    private Logger logger = Logger.getLogger(MessageController.class);
    @Value("${weixin.oid}")
    private String oid;
    @Autowired
    IAccountService accountService;

    @RequestMapping(value = "/buySuccess", method = RequestMethod.GET)
    public ResponseData buySuccess(
            @RequestParam("openId") String openId,
            @RequestParam("orderNo") String orderNo
    ) {
        ResponseData data = new ResponseData();
        if (StringUtils.isBlank(openId)) {
            data.setCode(500);
            data.setMsg("openId 为空！");
            return data;
        }
        if (StringUtils.isBlank(orderNo)) {
            data.setCode(500);
            data.setMsg("订单号为空！");
            return data;
        }
        IPublicAccountService publicAccountService = ServiceFactory.create(IPublicAccountService.class);
        PublicAccount weixinConfig = publicAccountService.byOriginalId(oid);
        String redirectUrl =UrlHelper.encode( "http://"+weixinConfig.getHost() + UrlHelper.join("/index.html#/mine/order/2", "originalId=" + oid));
        String url = Constant.SYSINQUIRY_CONTINUE_CALLBACK_URL_PREFIX;
        url = String.format(url, weixinConfig.getAppId(), redirectUrl, "snsapi_base", "123");
        accountService.sendTextMessage(String.format(Constant.ORDER_BUY_SUCCESS, orderNo, "<a href=\"" + url + "\">点此查看详情>></a>"), openId, oid);
        data.setCode(200);
        data.setMsg("发送成功！");
        return data;
    }

    @RequestMapping(value = "/stateChange", method = RequestMethod.GET)
    public ResponseData stateChange(
            @RequestParam("mobile") String mobile,
            @RequestParam("orderPorudctId") Integer orderPorudctId
    ) {
        ResponseData data = new ResponseData();
        if (null == orderPorudctId) {
            data.setCode(500);
            data.setMsg("orderId 为空！");
            return data;
        }
        if (StringUtils.isBlank(mobile)) {
            data.setCode(500);
            data.setMsg("mobile 为空！");
            return data;
        }
        accountService.pushOrderStateMsg(mobile,orderPorudctId);
        data.setCode(200);
        data.setMsg("发送成功");
        return data;
    }

}
