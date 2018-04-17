package com.gongsibao.rest.controller;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.rest.common.apiversion.Api;
import com.gongsibao.rest.common.util.RedisClient;
import com.gongsibao.rest.common.util.WebUtils;
import com.gongsibao.rest.common.web.Constant;
import com.gongsibao.rest.common.web.ResponseData;
import com.gongsibao.rest.service.user.IAccountService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.NetsharpException;
import org.netsharp.wx.mp.api.oauth.OAuthRequest;
import org.netsharp.wx.mp.api.oauth.OAuthResponse;
import org.netsharp.wx.pa.base.IFansService;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/wx/{v}")
@Api(1)
public class LoginController {
    private Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    IAccountService accountService;
    @Autowired
    private RedisClient redisClient;

    @Value("${weixin.oid}")
    private String oid;
    /**
     * @Description:TODO 登录验证
     * @param  openId
     * @return com.gongsibao.rest.common.web.ResponseData
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/12 19:17
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseData login(@RequestParam("openId") String openId){
        ResponseData data = new ResponseData();
        try {
            Account account=accountService.login(openId);
            if(null==account){
                data.setCode(-1);
                data.setMsg("未绑定手机号！");
            }else{
                data.setCode(200);
                data.setData(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
            data.setCode(500);
        }
        return data;
    }

    /**
     * @Description:TODO 发送验证码
     * @param  mobilePhone
     * @return com.gongsibao.rest.common.web.ResponseData
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/12 19:17
     */
    @RequestMapping(value = "/sendCode", method = RequestMethod.GET)
    public ResponseData sendCode(@RequestParam("mobilePhone") String mobilePhone) {
        ResponseData data = new ResponseData();
        //手机号校验
        if (!checkMobilePhone(mobilePhone)) {
            data.setCode(500);
            data.setMsg("手机号错误");
            return data;
        }
        //生成验证码并保存至缓存中;
        String code = RandomStringUtils.randomNumeric(6);
        redisClient.set(mobilePhone, code,60*15L);
        logger.info("code=" + code + "| mobilePhone : " + mobilePhone);
        //发送验证码至指定手机号
        data.setCode(200);
        data.setMsg("验证码已发送，请注意查收");
        //发送短信
        String smsString = "【公司宝】您的验证码为：" + code;
        new Thread() {
            @Override
            public void run() {
                accountService.sendSms(mobilePhone, smsString);
            }
        }.start();
        return data;
    }

    /**
     * @Description:TODO 账号绑定手机
     * @param  mobilePhone, openId, code
     * @return com.gongsibao.rest.common.web.ResponseData
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/12 19:18
     */
    @RequestMapping(value = "/bandMobile", method = RequestMethod.GET)
    public ResponseData changeMobile(
            @RequestParam("mobilePhone") String mobilePhone,
            @RequestParam("openId") String openId,
            @RequestParam("code") String code) {

        ResponseData data = new ResponseData();
        //手机号校验
        if (!checkMobilePhone(mobilePhone)) {
            data.setCode(500);
            data.setMsg("手机号错误");
            return data;
        }
        //验证码校验
        if (null==redisClient.get(mobilePhone)||!redisClient.get(mobilePhone).equals(code)) {
            data.setCode(500);
            data.setMsg("验证码错误");
            return data;
        }
        //更新数据库记录
        accountService.updateAccount(mobilePhone,openId);
        data.setCode(200);
        data.setMsg("绑定成功！");
        return data;
    }

    //手机号校验
    private boolean checkMobilePhone(String mobilePhone) {
        if (StringUtils.isBlank(mobilePhone)) {
            return false;
        }
        return true;
    }

    /**
     * @Description:TODO 获取code
     * @param   code
     * @return com.gongsibao.rest.common.web.ResponseData
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/12 19:18
     */
    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public ResponseData changeMobile(
            @RequestParam("code") String code,
            @RequestParam("state") String state
    ) {
        ResponseData data = new ResponseData();
        //手机号校验
        if (StringUtils.isBlank(code)) {
            data.setCode(500);
            data.setMsg("code is null");
            return data;
        }
        data.setCode(200);
        data.setData(code);
        data.setMsg("获取code成功,状态"+state);
        return data;
    }

    @RequestMapping(value = "/user/query/openId", method = RequestMethod.GET)
    public ResponseData getOpenIdByCode(@RequestParam("code") String code){
        ResponseData data = new ResponseData();
        IPublicAccountService wcService = ServiceFactory.create(IPublicAccountService.class);
        IFansService fansService = ServiceFactory.create(IFansService.class);
        PublicAccount pa = wcService.byOriginalId(oid);
        if (pa == null) {
            throw new NetsharpException("没有找到公众号，原始id：" + oid);
        }
        OAuthRequest oauth = new OAuthRequest();
        {
            oauth.setAppId(pa.getAppId());
            oauth.setAppSecret(pa.getAppSecret());
            oauth.setScope(OAuthRequest.OauthScope.snsapi_base);
            oauth.setCode(code);
        }
        Fans fans = null;
        try {
            OAuthResponse response = oauth.getResponse();
            String openId = response.getOpenid();
            fans =  fansService.byOpenId(openId);
            data.setCode(200);
            data.setData(fans);
            data.setMsg("获取成功");
        } catch (Exception ex) {
            logger.error("", ex);
        }
        return data;
    }
    
    @RequestMapping(value = "/login/icompany", method = RequestMethod.GET)
    public ResponseData loginIcompany(
            @RequestParam("openId") String openId,
            HttpServletRequest request, HttpServletResponse response){
        ResponseData data = new ResponseData();
        // 清cookie
        WebUtils.removeCookieCom(response, Constant.COOKIE_ACCOUNT_LOGIN_TICKET);
        Account ucAccount=accountService.queryByOpenId(openId);
        if (null != ucAccount) {
            data.setCode(200);
            String ticket = ucAccount.getTicket();
            // 未单点登录
            if (StringUtils.isBlank(ticket)) {
                ticket = UUID.randomUUID().toString();
                ucAccount.setTicket(ticket);
                accountService.updateTicket(ucAccount.getId(), ticket);
            }
            data.setData(ucAccount);
            WebUtils.setCookieCom(response, Constant.COOKIE_ACCOUNT_LOGIN_TICKET, ticket, Constant.TIME_ONE_YEAR);
        }else{
            data.setCode(-1);
            data.setMsg("未绑定手机号，登录失败！");
        }
        return data;
    }
    
    
}
