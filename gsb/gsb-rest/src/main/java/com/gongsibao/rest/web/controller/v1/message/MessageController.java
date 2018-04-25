package com.gongsibao.rest.web.controller.v1.message;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.rest.web.common.apiversion.Api;
import com.gongsibao.rest.web.common.web.Constant;
import com.gongsibao.rest.web.common.web.ResponseData;
import com.gongsibao.rest.base.user.IAccountService;
import com.gongsibao.rest.web.controller.BaseController;
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

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/wx/{v}/message")
@Api(1)
public class MessageController extends BaseController{
    private Logger logger = Logger.getLogger(MessageController.class);

    @Autowired
    IAccountService accountService;

    @RequestMapping(value = "/buySuccess", method = RequestMethod.GET)
    public ResponseData buySuccess(
            HttpServletRequest request,
            @RequestParam("openId") String openId,
            @RequestParam("money") String money,
            @RequestParam("productName") String productName,
            @RequestParam("payStatus") String payStatus,
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
        if (StringUtils.isBlank(originalId(request))) {
            data.setCode(500);
            data.setMsg("originalId 为空！");
            return data;
        }
        if (StringUtils.isBlank(payStatus)) {
            data.setCode(500);
            data.setMsg("payStatus 为空！");
            return data;
        }
        try{
            Account account=accountService.queryByOpenId(openId);
            accountService.buySuccessSendMsg(originalId(request),account.getId(),money,productName,"您的订单"+orderNo+"支付成功,我们将立即为您办理",payStatus.equals("1")?"/index.html#/mine/order/2":"/index.html#/mine/order/1");
            data.setCode(200);
            data.setMsg("发送成功！");
        }catch (Exception e){
            data.setCode(-1);
            data.setMsg(e.getMessage());
        }
        return data;
    }

    @RequestMapping(value = "/stateChange", method = RequestMethod.GET)
    public ResponseData stateChange(
            HttpServletRequest request,
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
        if (StringUtils.isBlank(originalId(request))) {
            data.setCode(500);
            data.setMsg("originalId 为空！");
            return data;
        }
        accountService.pushOrderStateMsg(originalId(request),mobile,orderPorudctId);
        data.setCode(200);
        data.setMsg("发送成功");
        return data;
    }

}
