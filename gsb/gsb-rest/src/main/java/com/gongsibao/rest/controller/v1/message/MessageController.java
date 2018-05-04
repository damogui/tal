package com.gongsibao.rest.controller.v1.message;

import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountWxMsg;
import com.gongsibao.rest.controller.BaseController;
import com.netsharp.rest.controller.annotation.ApiVersion;
import com.netsharp.rest.controller.exception.WxException;
import com.netsharp.rest.controller.result.RestResult;
import com.netsharp.rest.base.user.IAccountService;
import com.netsharp.rest.utils.StringUtils;
import org.apache.log4j.Logger;
import org.netsharp.communication.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/wx/{v}/message")
@ApiVersion(1)
public class MessageController extends BaseController {
    private Logger logger = Logger.getLogger(MessageController.class);

    @Autowired
    IAccountService accountService;

    @RequestMapping(value = "/buySuccess", method = RequestMethod.POST)
    public String buySuccess(
            HttpServletRequest request, @RequestBody Map<String, Object> req
    ) {
        String openId = StringUtils.trimToEmpty(req.get("openId"));
        String money = StringUtils.trimToEmpty(req.get("money"));
        String productName = StringUtils.trimToEmpty(req.get("productName"));
        String payStatus = StringUtils.trimToEmpty(req.get("payStatus"));
        String orderNo = StringUtils.trimToEmpty(req.get("orderNo"));
        String remainMoney = StringUtils.trimToEmpty(req.get("remainMoney"));
        RestResult data = new RestResult();
        if (StringUtils.isBlank(openId)) {
            throw new WxException(RestResult.EXCEPTION, "openId 为空！");
        }
        if (StringUtils.isBlank(money)) {
            throw new WxException(RestResult.EXCEPTION, "money 为空！");
        }
        if (StringUtils.isBlank(orderNo)) {
            throw new WxException(RestResult.EXCEPTION, "订单号为空！");
        }
        if (StringUtils.isBlank(productName)) {
            throw new WxException(RestResult.EXCEPTION, "productName为空！");
        }
        if (StringUtils.isBlank(originalId(request))) {
            throw new WxException(RestResult.EXCEPTION, "originalId 为空！");
        }
        if (StringUtils.isBlank(payStatus)) {
            throw new WxException(RestResult.EXCEPTION, "payStatus 付款信息 1全部 0部分付 ！");
        }
        Account account = accountService.queryByOpenId(openId);
        String memo = "您的订单" + orderNo + "支付成功,我们将立即为您办理。";
        if (!StringUtils.isBlank(remainMoney)) {
            memo = "您的订单" + orderNo + " 已支付" + money + ",还需支付" + remainMoney + "。";
        }
        accountService.buySuccessSendMsg(originalId(request), account.getId(), money, productName, memo, "1".equals(payStatus) ? "/index.html#/mine/order/2" : "/index.html#/mine/order/1");

        return "发送成功！";
    }

    @RequestMapping(value = "/stateChange", method = RequestMethod.GET)
    public String stateChange(
            HttpServletRequest request,
            @RequestParam("mobile") String mobile,
            @RequestParam("orderPorudctId") Integer orderPorudctId
    ) {
        RestResult data = new RestResult();
        if (null == orderPorudctId) {
            throw new WxException(RestResult.EXCEPTION, "orderId 为空！");
        }
        if (StringUtils.isBlank(mobile)) {
            throw new WxException(RestResult.EXCEPTION, "mobile 为空！");
        }
        accountService.pushOrderStateMsg(mobile, orderPorudctId);
        return "发送成功";
    }


    @RequestMapping(value = "/saveOrderMsg", method = RequestMethod.GET)
    public String saveOrderMsg(
            HttpServletRequest request,
            @RequestParam("mobile") String mobile,
            @RequestParam("orderPorudctId") Integer orderPorudctId
    ) {
        RestResult data = new RestResult();
        if (null == orderPorudctId) {
            throw new WxException(RestResult.EXCEPTION, "orderId 为空！");
        }
        if (StringUtils.isBlank(mobile)) {
            throw new WxException(RestResult.EXCEPTION, "mobile 为空！");
        }
        accountService.saveOrderMsg(mobile, orderPorudctId);
        return "发送成功";
    }

    @RequestMapping(value = "/sendMsg", method = RequestMethod.GET)
    public String saveOrderMsg(
            HttpServletRequest request,
            @RequestParam("accountId") int accountId
    ) {
        IAccountWeiXinService weiXinService = ServiceFactory.create(IAccountWeiXinService.class);
        //商标进度提醒
        weiXinService.pushTextMsg(accountId, "您的商标进度有变化点击查看", "办理事项", "办理进度", "2018-04-27", "baidu.com", null, AccountWxMsg.WORK_PROCESS_CHANGE);
        return "发送成功";
    }

}
