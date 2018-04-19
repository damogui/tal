package com.gongsibao.rest.web.controller;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.rest.web.common.apiversion.Api;
import com.gongsibao.rest.web.common.security.SecurityUtils;
import com.gongsibao.rest.web.common.util.JsSdkManager;
import com.gongsibao.rest.web.common.util.RedisClient;
import com.gongsibao.rest.web.common.web.Pager;
import com.gongsibao.rest.web.common.web.ResponseData;
import com.gongsibao.rest.base.user.IAccountService;
import com.gongsibao.rest.web.dto.order.OrderPayMapDTO;
import com.gongsibao.u8.base.IPayService;
import com.gongsibao.u8.base.ISoOrderService;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.NetsharpException;
import org.netsharp.wx.mp.api.oauth.OAuthRequest;
import org.netsharp.wx.mp.api.oauth.OAuthResponse;
import org.netsharp.wx.mp.sdk.AesException;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "/wx/{v}")
@Api(1)
public class UserController extends BaseController{
    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    IAccountService accountService;
    @Autowired
    private RedisClient redisClient;
    /**
     * @Description:TODO 登录验证
     * @param  openId
     * @return com.gongsibao.rest.web.common.web.ResponseData
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
     * @return com.gongsibao.rest.web.common.web.ResponseData
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/12 19:17
     */
    @RequestMapping(value = "/sendCode", method = RequestMethod.GET)
    public ResponseData sendCode(@RequestParam("mobilePhone") String mobilePhone) {
        ResponseData data = new ResponseData();
        if (StringUtils.isBlank(mobilePhone)) {
            data.setCode(500);
            data.setMsg("手机号不能为空!");
            return data;
        }
        //手机号校验
        if (!checkMobilePhone(mobilePhone)) {
            data.setCode(500);
            data.setMsg("手机号格式错误!");
            return data;
        }
        //生成验证码并保存至缓存中;
        String code = RandomStringUtils.randomNumeric(6);
        redisClient.remove(mobilePhone);
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
                accountService.sendSms(mobilePhone.trim(), smsString);
            }
        }.start();
        return data;
    }

    /**
     * @Description:TODO 账号绑定手机
     * @param  mobilePhone, openId, code
     * @return com.gongsibao.rest.web.common.web.ResponseData
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/12 19:18
     */
    @RequestMapping(value = "/bandMobile", method = RequestMethod.GET)
    public ResponseData bandMobile(
            @RequestParam("mobilePhone") String mobilePhone,
            @RequestParam("openId") String openId,
            @RequestParam("code") String code) {

        ResponseData data = new ResponseData();
        if (StringUtils.isBlank(mobilePhone)) {
            data.setCode(500);
            data.setMsg("手机号不能为空!");
            return data;
        }
        //手机号校验
        if (!checkMobilePhone(mobilePhone)) {
            data.setCode(500);
            data.setMsg("手机号格式错误!");
            return data;
        }
        //验证码校验
        if (null==redisClient.get(mobilePhone)||!redisClient.get(mobilePhone).contains(code)) {
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
        }else if(mobilePhone.length()!=11){
            return false;
        }else{
            String regex = "^((16[6])|(13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
            if(mobilePhone.length() != 11){
                System.out.println("手机号应为11位数");
            }else{
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(mobilePhone);
                boolean isMatch = m.matches();
                if(isMatch){
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @Description:TODO 获取code
     * @param   code
     * @return com.gongsibao.rest.web.common.web.ResponseData
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/12 19:18
     */
    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public ResponseData code(
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
    public ResponseData getOpenIdByCode(HttpServletRequest request,@RequestParam("code") String code){
        ResponseData data = new ResponseData();
        IPublicAccountService wcService = ServiceFactory.create(IPublicAccountService.class);
        PublicAccount pa = wcService.byOriginalId(originalId(request));
        if (pa == null) {
            throw new NetsharpException("没有找到公众号，原始id：" + originalId(request));
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
            data.setCode(200);
            data.setData(openId);
            data.setMsg("获取成功");
        } catch (Exception ex) {
            logger.error("", ex);
        }
        return data;
    }
    
    /**
     * @Description:TODO 获取签名 支付前
     * @param  url
     * @return com.gongsibao.rest.web.common.web.ResponseData
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/18 10:43
     */
    @RequestMapping(value = "/jsSignature", method = RequestMethod.GET)
    public ResponseData jsSignature(
            HttpServletRequest request,
            @RequestParam("url") String url
            )  {
        ResponseData data = new ResponseData();
        try{
            if(null==url){
                data.setCode(500);
                data.setMsg("url 为空!");
                return data;
            }
            if(null==originalId(request)){
                data.setCode(500);
                data.setMsg("originalId 为空!");
                return data;
            }
            data.setData(JsSdkManager.getConfig(url,originalId(request)));
            data.setCode(200);
        }catch (AesException e ){
            logger.info("获取签名失败" + e.getMessage());
            data.setCode(500);
        }
        return data;
    }

    /*获取微信公众号支付（H5）的参数*/
    @RequestMapping("/getWxPayMP")
    public ResponseData getWxPayMP(HttpServletRequest request, HttpServletResponse response) {
        ResponseData data = new ResponseData();
        //微信授权回调的code凭证，用来获取openid的
        String openId = StringUtils.trimToEmpty(request.getParameter("openId"));
        String state = StringUtils.trimToEmpty(request.getParameter("state"));
        //订单编号
        String orderNoStr = StringUtils.trimToEmpty(request.getParameter("orderNoStr"));
        if (StringUtils.isBlank(openId)) {
            data.setCode(-1);
            data.setMsg("openId参数不能为空");
            return data;
        }
        ISoOrderService soOrderService=ServiceFactory.create(ISoOrderService.class);
        String orderNo = StringUtils.trimToEmpty(orderNoStr.split("_")[0]);
        Integer orderId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(orderNoStr.substring(orderNoStr.indexOf("_") + 1, orderNoStr.lastIndexOf("_"))));
        Integer payId = NumberUtils.toInt(orderNoStr.substring(orderNoStr.lastIndexOf("_") + 1, orderNoStr.length()));
        SoOrder order = soOrderService.byId(orderId);
        if (order == null) {
            data.setCode(-1);
            data.setMsg("该订单不存在");
            return data;
        }
        if (!order.getNo().equals(orderNo)) {
            data.setCode(-1);
            data.setMsg("该订单id与订单编号不相符");
            return data;
        }
        List<OrderPayMap> listOrder = accountService.pageByProperties(orderId,payId);
        if (listOrder.isEmpty()) {
            data.setCode(-1);
            data.setMsg("该支付信息不属于该订单");
            return data;
        }
        //回调传过来的参数
        int orderIdParam = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("orderIdStr")));
        //回调传过来的参数
        int payIdParam = NumberUtils.toInt(SecurityUtils.rc4Decrypt(request.getParameter("payIdStr")));
        if (!orderId.equals(orderIdParam)) {
            data.setCode(-1);
            data.setMsg("回调订单id参数不一致");
            return data;
        }
        if (!payId.equals(payIdParam)) {
            data.setCode(-1);
            data.setMsg("回调支付id参数不一致");
            return data;
        }
        IPayService payService=ServiceFactory.create(IPayService.class);
        // 支付信息
        Pay pay = payService.byId(payId);
        //本次付款金额
        Integer totalFee = pay.getAmount();
        //付款内容（产品名称等）
        String body = order.getProdName();
        SortedMap<Object, Object> resMap = new TreeMap<Object, Object>();
        Integer resId = accountService.getWxPayH5Param(originalId(request),openId, orderNoStr, totalFee, body, 0, resMap);
        if (resId.equals(-1)) {
            data.setCode(-1);
            data.setMsg("获取openid失败");
            return data;
        }
        if (resId.equals(-2)) {
            data.setCode(-1);
            data.setMsg("获取prepay_id失败");
            return data;
        }
        data.setData(resMap);
        return data;
    }
}
