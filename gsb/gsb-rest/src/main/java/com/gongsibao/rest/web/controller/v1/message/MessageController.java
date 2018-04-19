package com.gongsibao.rest.web.controller.v1.message;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.rest.web.common.apiversion.Api;
import com.gongsibao.rest.web.common.web.Constant;
import com.gongsibao.rest.web.common.web.ResponseData;
import com.gongsibao.rest.base.user.IAccountService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.entity.PublicAccount;
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
            @RequestParam("money") String money,
            @RequestParam("productName") String productName,
            @RequestParam("orderNo") String orderNo
    ) {
        ResponseData data = new ResponseData();
        if (StringUtils.isBlank(openId)) {
            data.setCode(500);
            data.setMsg("openId 为空！");
            return data;
        }  if (StringUtils.isBlank(money)) {
            data.setCode(500);
            data.setMsg("money 为空！");
            return data;
        }
        if (StringUtils.isBlank(orderNo)) {
            data.setCode(500);
            data.setMsg("订单号为空！");
            return data;
        }
        if (StringUtils.isBlank(productName)) {
            data.setCode(500);
            data.setMsg("productName为空！");
            return data;
        }
        Account account=accountService.queryByOpenId(openId);
        accountService.buySuccessSendMsg(account.getId(),money,productName,"您的订单"+orderNo+"支付成功,我们将立即为您办理","/index.html#/mine/order/2");
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
