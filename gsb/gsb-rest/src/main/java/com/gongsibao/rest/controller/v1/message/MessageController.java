package com.gongsibao.rest.controller.v1.message;

import com.gongsibao.rest.common.apiversion.Api;
import com.gongsibao.rest.common.util.RedisClient;
import com.gongsibao.rest.common.web.ResponseData;
import com.gongsibao.rest.service.user.IAccountService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wx/{v}/message")
@Api(1)
public class MessageController {
    private Logger logger = Logger.getLogger(MessageController.class);

    @RequestMapping(value = "/buySuccess", method = RequestMethod.GET)
    public ResponseData changeMobile(
            @RequestParam("openId") String openId) {

        ResponseData data = new ResponseData();
        PublicAccount pa = PublicAccountManager.getInstance().get("gh_29f5a8b8da16").getAccount();
        AccessToken at = AccessTokenManage.getTokenByAppId(pa.getAppId());
        TextCustomerMessageRequest request = new TextCustomerMessageRequest();
        request.setContent("成功!");
        request.setOpenId(openId);
        request.setTokenInfo(at);
        sendMessge(request);
        data.setCode(200);
        data.setMsg("修改成功");
        return data;
    }

    public Response sendMessge(CustomMessageRequest messageRequest) {
        Response resp = null;
        try {
            resp = messageRequest.getResponse();
            logger.info(String.format("给用户[%s]发送消息的响应：[%s]", messageRequest.getOpenId(), JsonManage.serialize2(resp)));
        } catch (Exception ex) {
            logger.error(String.format("给用户[%s]发送消息失败：%s", messageRequest.getOpenId(), ex.getMessage()));
        }

        return resp;
    }


}
