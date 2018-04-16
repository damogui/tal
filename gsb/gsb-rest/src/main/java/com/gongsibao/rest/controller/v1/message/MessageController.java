package com.gongsibao.rest.controller.v1.message;

import com.gongsibao.rest.common.apiversion.Api;
import com.gongsibao.rest.common.util.RedisClient;
import com.gongsibao.rest.common.web.Constant;
import com.gongsibao.rest.common.web.ResponseData;
import com.gongsibao.rest.service.user.IAccountService;
import com.gongsibao.rest.service.user.impl.AccountService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.netsharp.util.JsonManage;
import org.netsharp.wx.mp.api.Response;
import org.netsharp.wx.mp.api.accesstoken.AccessToken;
import org.netsharp.wx.mp.api.accesstoken.AccessTokenManage;
import org.netsharp.wx.mp.api.customessage.CustomMessageRequest;
import org.netsharp.wx.mp.api.customessage.TextCustomerMessageRequest;
import org.netsharp.wx.pa.base.ICustomService;
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
    public ResponseData changeMobile(
            @RequestParam("openId") String openId,
            @RequestParam("productName") String productName
            ) {
        ResponseData data = new ResponseData();
        if(StringUtils.isBlank(openId)){
            data.setCode(500);
            data.setMsg("openId 为空！");
            return data;
        }
        if(StringUtils.isBlank(productName)){
            data.setCode(500);
            data.setMsg("购买产品为空！");
            return data;
        }
        accountService.sendTextMessage(String.format(Constant.ORDER_BUY_SUCCESS,productName),openId,oid);
        data.setCode(200);
        data.setMsg("修改成功");
        return data;
    }

}
