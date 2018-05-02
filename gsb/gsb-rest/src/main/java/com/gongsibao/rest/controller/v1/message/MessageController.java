package com.gongsibao.rest.controller.v1.message;

import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountWxMsg;
import com.gongsibao.rest.controller.BaseController;
import com.netsharp.rest.common.annotation.Api;
import com.netsharp.rest.common.result.ResponseData;
import com.gongsibao.rest.base.user.IAccountService;
import com.netsharp.rest.util.StringUtils;
import org.apache.log4j.Logger;
import org.netsharp.communication.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(value = "/wx/{v}/message")
@Api(1)
public class MessageController extends BaseController {
    private Logger logger = Logger.getLogger(MessageController.class);

    @Autowired
    IAccountService accountService;

    @RequestMapping(value = "/buySuccess", method = RequestMethod.POST)
    public ResponseData buySuccess(
            HttpServletRequest request,@RequestBody Map<String, Object> req
    ) {
        String openId =  StringUtils.trimToEmpty(req.get("openId"));
        String money =  StringUtils.trimToEmpty(req.get("money"));
        String productName =  StringUtils.trimToEmpty(req.get("productName"));
        String payStatus =  StringUtils.trimToEmpty(req.get("payStatus"));
        String orderNo = StringUtils.trimToEmpty(req.get("orderNo"));
        String remainMoney = StringUtils.trimToEmpty(req.get("remainMoney"));
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
            data.setMsg("payStatus 付款信息 1全部 0部分付 ！");
            return data;
        }
        try{
            Account account=accountService.queryByOpenId(openId);
            String memo="您的订单"+orderNo+"支付成功,我们将立即为您办理。";
            if(!StringUtils.isBlank(remainMoney)){
                memo="您的订单"+orderNo+ " 已支付"+money+",还需支付"+remainMoney+"。";
            }
            accountService.buySuccessSendMsg(originalId(request),account.getId(),money,productName,memo,payStatus.equals("1")?"/index.html#/mine/order/2":"/index.html#/mine/order/1");
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
        accountService.pushOrderStateMsg(mobile,orderPorudctId);
        data.setCode(200);
        data.setMsg("发送成功");
        return data;
    }


    @RequestMapping(value = "/saveOrderMsg", method = RequestMethod.GET)
    public ResponseData saveOrderMsg(
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
        accountService.saveOrderMsg(mobile,orderPorudctId);
        data.setCode(200);
        data.setMsg("发送成功");
        return data;
    }

    @RequestMapping(value = "/sendMsg", method = RequestMethod.GET)
    public ResponseData saveOrderMsg(
            HttpServletRequest request,
            @RequestParam("accountId") int accountId
    ) {
        ResponseData data = new ResponseData();
        IAccountWeiXinService weiXinService=ServiceFactory.create(IAccountWeiXinService.class);
        //商标进度提醒
        weiXinService.pushTextMsg(accountId,"您的商标进度有变化点击查看","办理事项","办理进度","2018-04-27","baidu.com",null, AccountWxMsg.WORK_PROCESS_CHANGE);
        data.setCode(200);
        data.setMsg("发送成功");
        return data;
    }

}
