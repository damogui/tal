package com.gongsibao.rest.controller.v1.user;

import com.gongsibao.entity.Result;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.*;
import com.netsharp.rest.base.user.IAccountService;
import com.netsharp.rest.common.annotation.Api;
import com.netsharp.rest.common.constant.ConstantKey;
import com.netsharp.rest.common.security.SecurityUtils;
import com.netsharp.rest.common.result.ResponseData;
import com.gongsibao.rest.controller.BaseController;
import com.netsharp.rest.dto.user.AccountValidateDTO;
import com.netsharp.rest.dto.user.LoginDTO;
import com.gongsibao.u8.base.IPayService;
import com.gongsibao.u8.base.ISoOrderService;
import com.netsharp.rest.utils.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.NetsharpException;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.wx.mp.api.oauth.OAuthRequest;
import org.netsharp.wx.mp.api.oauth.OAuthResponse;
import org.netsharp.wx.mp.sdk.AesException;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "/wx/{v}")
@Api(1)
public class UserController extends BaseController {
    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    IAccountService accountService;
    @Autowired
    private RedisClient redisClient;
    @Value("${icmppay_domain}")
    private String icompanyDomain;
    ISoOrderService soOrderService=ServiceFactory.create(ISoOrderService.class);
    IPayService payService=ServiceFactory.create(IPayService.class);

    // 客户服务，为了事务，按照netsharp service的注解重写一个
    com.netsharp.rest.base.account.IAccountService netSharpAccountService = ServiceFactory.create(com.netsharp.rest.base.account.IAccountService.class);
    /**
     * @Description:TODO 登录验证
     * @param  openId
     * @return com.netsharp.rest.common.web.ResponseData
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

    @RequestMapping(value = "/openId/oid/match", method = RequestMethod.GET)
    public ResponseData match(HttpServletRequest request){
        ResponseData data = new ResponseData();
        try {
            IPublicAccountService wcService = ServiceFactory.create(IPublicAccountService.class);
            PublicAccount pa = wcService.byOriginalId(originalId(request));
            if (pa == null) {
                throw new NetsharpException("没有找到公众号，原始id：" + originalId(request));
            }
            if(!accountService.matchOpenIdOid(openId(request),pa.getId())){
                data.setCode(-1);
                data.setMsg("不匹配！");
            }else{
                data.setCode(200);
                data.setData("匹配！");
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
     * @return com.netsharp.rest.common.web.ResponseData
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
     * @return com.netsharp.rest.common.web.ResponseData
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
     * @return com.netsharp.rest.common.web.ResponseData
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
            data.setCode(500);
            data.setMsg("没有找到公众号，原始id："+ originalId(request));
            return data;
        }
        OAuthRequest oauth = new OAuthRequest();
        {
            oauth.setAppId(pa.getAppId());
            oauth.setAppSecret(pa.getAppSecret());
            oauth.setScope(OAuthRequest.OauthScope.snsapi_base);
            oauth.setCode(code);
        }
        try {
            OAuthResponse response = oauth.getResponse();
            String openId = response.getOpenid();
            data.setCode(200);
            data.setData(openId);
            data.setMsg("获取成功");
        } catch (Exception ex) {
            data.setCode(500);
            data.setMsg("获取失败："+ ex.getMessage());
            return data;
        }
        return data;
    }
    
    /**
     * @Description:TODO 获取签名 支付前
     * @param  url
     * @return com.netsharp.rest.common.web.ResponseData
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
    @RequestMapping(value = "/getWxPayMP",method = RequestMethod.GET)
    public ResponseData getWxPayMP(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ipAddress = null;
        try {
            ipAddress = getIpAddr(request);
        } catch (Exception e) {
            logger.error("get server host Exception e:", e);
        }
        ResponseData data = new ResponseData();
        //微信授权回调的code凭证，用来获取openid的
        String openId = StringUtils.trimToEmpty(request.getParameter("openId"));
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
        if (originalId(request) == null) {
            data.setCode(-1);
            data.setMsg("公众号不存在");
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
        SortedMap<String, String> resMap = new TreeMap<String, String>();
        Integer resId = accountService.getWxPayH5Param(ipAddress,originalId(request),openId, orderNoStr, totalFee, body, 0, resMap);
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

    /**
     * @Description:TODO 支付調起
     * @param
     * @return
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/19 20:21
     */
    @SuppressWarnings({ "unchecked" })
    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    public ResponseData pay(HttpServletRequest request,
                            HttpServletResponse response,

                            @RequestBody String json) {
        String oid=originalId(request);
        ResponseData data = new ResponseData();
        //region 获取参数
        if (StringUtils.isBlank(json)) {
            data.setCode(-1);
            data.setMsg("参数为空");
            return data;
        }

        Map<String, Object> map = (Map<String, Object>) JsonUtils.jsonToObject(json, Map.class);
        if (MapUtils.isEmpty(map)) {
            data.setCode(-1);
            data.setMsg("参数错误");
            return data;
        }
        // 订单ID
        String orderIdStr = StringUtils.trimToEmpty((String) map.get("orderIdStr"));
        Integer orderId = NumberUtils.toInt(SecurityUtils.rc4Decrypt(orderIdStr), -1);
        if (orderId <= 0) {
            data.setCode(-1);
            data.setMsg("订单ID错误");
            return data;
        }
        // 支付方式 1：快捷支付 2：网银支付 3：企业网银支付 4：线下支付
        String payMethod = StringUtils.trimToEmpty((String) map.get("payMethod"));
        if (NumberUtils.toInt(payMethod, -1) <= 0 || NumberUtils.toInt(payMethod, -1) > 4) {
            data.setCode(-1);
            data.setMsg("支付方式错误");
            return data;
        }
        // 支付金额
        Integer totalFee = NumberUtils.toInt(StringUtils.trimToEmpty(map.get("totalFee").toString()));
        if (NumberUtils.toInt(totalFee, -1) <= 0) {
            data.setCode(-1);
            data.setMsg("支付金额错误");
            return data;
        }
        // 支付途径（1:微信；2:支付宝；3：个人网银;4：企业网银）
        Integer paymentChannels = NumberUtils.toInt(StringUtils.trimToEmpty(map.get("paymentChannels").toString()));
        // 支付渠道 wx zfbwy zfbjs
        String payChannels = StringUtils.trimToEmpty((String) map.get("payChannels"));
        // 支付渠道 wx zfbwy zfbjs
        // 银行代码
        String bankCode = payChannels.equals("wx") ? "微信支付-WXPAY" : "支付宝-ALIPAY";
        // 线下付款方名称
        String offlinePayerName = "";
        // 线下付款银行帐号
        String offlineBankNo = "";
        // 线下支付留言
        String offlineRemark = "";
        // 上传付款凭证
        String uploadPayVoucher = "";
        // 客户端类别（0:网页端；1:H5（公众号）端；2：APP端）
        Integer clientType = 1;
        // 是否是中关村银行（0:不是、1:是）
        Integer isZgcBank = 0;
        // 调用方（0:原H5、1:万达、2:ICompany）
        //===============================万达=============================================
        Integer callType = NumberUtils.toInt(StringUtils.trimToEmpty(map.get("callType").toString()));
        //万达传过来的openId,不为空给它返回去，为空不处理
        String openId = StringUtils.trimToEmpty((String) map.get("openId"));
        Account account=accountService.queryByOpenId(openId);
        // endregion
        if (openId == null) {
            data.setCode(-1);
            data.setMsg("openid 不存在");
            return data;
        }
        //region 订单信息的验证
        // 查询订单并验证
        SoOrder order = soOrderService.getByOrderId(orderId);
        if (order == null) {
            data.setCode(-1);
            data.setMsg("订单不存在");
            return data;
        }
        /** 301 订单付款状态：3011 待付款、3012 已付部分款（根据“是否分期”判断处理流程）、3013 已付款 */
        if (order.getPayStatus().getValue() == 3013) {
            data.setCode(-1);
            data.setMsg("订单已付款");
            return data;
        }
        if (NumberUtils.toInt(totalFee) > NumberUtils.toInt(order.getPayablePrice()) || NumberUtils.toInt(order.getPaidPrice()) + NumberUtils.toInt(totalFee) > NumberUtils.toInt(order.getPayablePrice())) {
            data.setCode(-1);
            data.setMsg("订单价格有变动，请刷新重试");
            return data;
        }
        /** 当该订单是改价订单时，但是改价审核未通过时，禁止付款 */
        if (order.getIsChangePrice().equals(1) && order.getChangePriceAuditStatus().getValue()==1054) {
            data.setCode(-1);
            data.setMsg("改价审核未通过，禁止付款");
            return data;
        }
        //合同订单不让付款
        if (order.getType().equals(2)) {
            data.setCode(-1);
            data.setMsg("合同订单，禁止付款");
            return data;
        }
        //非付款状态，禁止付款
        if (order.getProcessStatus().getValue() == 3023 || order.getProcessStatus().getValue() == 3024 || order.getPayStatus().getValue() == 3013) {
            data.setCode(-1);
            data.setMsg("非付款状态，禁止付款");
            return data;
        }
        // endregion
        //region 生成支付信息
        Map<String, String> resultMap = new HashMap<>();
        try {
            //region 生成支付记录
            //订单名称，必填
            String subject = StringUtils.trimToEmpty(order.getProdName()).replace("/", " ");
            //商品描述，可空
            String body = StringUtils.trimToEmpty(order.getProdName());
            Pay soPay = new Pay();{
                soPay.toNew();
                soPay.setNo("");
                soPay.setAmount(NumberUtils.toInt(totalFee));
                /** 311 线下付款方式：3111 对公转账、3112 现金、3113 刷卡、3114 个人转账 */
                soPay.setOfflineWayType(OfflineWayType.getItem(3114));
                soPay.setOfflineInstallmentType(PayOfflineInstallmentType.getItem(1));
                /** 310 支付付款方式：3101 在线支付、3102 线下支付、3103 内部结转 */
                soPay.setPayWayType(PayWayType.ONLINE_PAYMENT);
                soPay.setOnlineBankCodeId(bankCode);
                /** 312 支付成功状态：3121 未支付、3122 待审核、3123 成功、3124 失败 */
                soPay.setSuccessStatus(PaySuccessStatus.getItem(3121));
                soPay.setConfirmTime(new Date());
                soPay.setOnlineTradeNo("");
                soPay.setOfflineBankNo(offlineBankNo);
                soPay.setOfflinePayerName(offlinePayerName);
                soPay.setOfflineRemark(offlineRemark);
                soPay.setOfflineAuditStatus(AuditStatusType.Dsh);
                soPay.setOfflineAddUserId(account.getId());
                soPay.setCreateTime(new Date());
            }
            Integer payId = payService.addPay(soPay, orderId, uploadPayVoucher);
            resultMap.put("payIdStr", SecurityUtils.rc4Encrypt(payId));
            //region 调用支付第三方接口，获取返回值
            //调用第三方支付接口
            Map<String, Object> payDataMap = getPayData(oid,payChannels, clientType, order, orderIdStr, payId, totalFee, body, subject, bankCode, paymentChannels, isZgcBank,callType,openId);
            if (NumberUtils.toInt(payDataMap.get("code")) != 200) {
                data.setCode(-1);
                data.setMsg(StringUtils.trimToEmpty(payDataMap.get("msg").toString()));
                return data;
            }
            String result = StringUtils.trimToEmpty(payDataMap.get("result").toString());
            if (!("4".equals(payMethod)) && StringUtils.isBlank(result)) {
                data.setCode(-1);
                data.setMsg("请求第三方支付接口失败！");
                return data;
            }
            resultMap.put("result", result);
            data.setData(resultMap);
        } catch (Exception e) {
            logger.error("==========pay error orderId is:==========" + orderId);
            e.getMessage();
        }
        return data;
    }


    /**
     * @Description:TODO 微信支付后定时刷新，是否付完全款
     * @param
     * @return
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/19 20:21
     */
    @RequestMapping(value = "/checkOrder",method = RequestMethod.POST)
    public ResponseData igirlCheckOrder(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, Object> req) {
        ResponseData data = new ResponseData();
        // 订单ID
        String orderIdStr = StringUtils.trimToEmpty(req.get("orderIdStr"));
        // 订单支付ID
        String payIdStr = StringUtils.trimToEmpty(req.get("payIdStr"));
//        log.info("==========checkOrder payIdStr==========" + payIdStr);
        payIdStr = SecurityUtils.rc4Decrypt(payIdStr);
        Integer payId = NumberUtils.toInt(payIdStr, -1);
        logger.info("==========checkOrder orderIdStr==========" + orderIdStr);
        if(StringUtils.isNotEmpty(orderIdStr)){
            orderIdStr = SecurityUtils.rc4Decrypt(orderIdStr);
            Integer orderId = NumberUtils.toInt(orderIdStr, -1);
            SoOrder order = soOrderService.getByOrderId(orderId);
            Pay pay = payService.byId(payId);

            /** 301 订单付款状态：3011 待付款、3012 已付部分款（根据“是否分期”判断处理流程）、3013 已付款 */
            if (order == null || NumberUtils.toInt(order.getPayStatus().getValue()) == 3011
                    || pay == null || NumberUtils.toInt(pay.getSuccessStatus().getValue()) != 3123) {
                logger.info("==========checkOrder getPayStatusId==========" + order.getPayStatus().getValue());
                logger.info("==========checkOrder getSuccessStatusId==========" + pay.getSuccessStatus().getValue());
                data.setCode(-1);
                data.setMsg("未付款");
            }else{
                data.setCode(200);
                data.setMsg("已付款");
            }
        }else{
            Pay pay = payService.byId(payId);
            if (pay == null || NumberUtils.toInt(pay.getSuccessStatus().getValue()) != 3123) {
//                log.info("==========checkOrder getPayStatusId==========" + order.getPayStatusId());
//                log.info("==========checkOrder getSuccessStatusId==========" + pay.getSuccessStatusId());
                data.setCode(-1);
                data.setMsg("未付款");
            }else{
                data.setCode(200);
                data.setMsg("已付款");
            }
        }
        return data;
    }

    /*调用支付第三方接口*/
    private Map<String, Object> getPayData(String oid,String payChannels, Integer clientType, SoOrder order, String orderIdStr, Integer payId, Integer totalFee, String body, String subject, String bankCode, Integer paymentChannels, Integer isZgcBank, Integer callType,String openId) throws Exception {
        String result = "";
        Map<String, Object> resultMap = new HashMap<>();
        //默认成功
        resultMap.put("code", 200);
        switch (payChannels) {
            case "wx":
                // 单位是分
                if (clientType.equals(1)) {//当是微信公众号（H5）支付时，返回微信授权的url链接（获取code值）
                    /*String returnUrl = URLEncoder.encode(PayConfigUtil.Call_MP_URL + "?orderNoStr=" + order.getNo() + "_" + orderIdStr + "_" + payId + "&orderIdStr=" + orderIdStr + "&payIdStr=" + SecurityUtils.rc4Encrypt(payId) + "", "UTF-8");
                    result = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + PayConfigUtil.getAppid() + "&redirect_uri=" + returnUrl + "&response_type=code&scope=snsapi_base&state=WeiXin";*/
                    result = getAuthorizeUrl(oid,order.getNo(), orderIdStr, payId, callType,openId);
                } else {
                    //result = wxpay(StringUtils.trimToEmpty(order.getNo()) + "_" + orderIdStr + "_" + payId, NumberUtils.toInt(totalFee), body, clientType, "", 0);
//                    result = weiXinPayService.wxpay(StringUtils.trimToEmpty(order.getNo()) + "_" + orderIdStr + "_" + payId, NumberUtils.toInt(totalFee), body, clientType, "", 0);
                }
                break;
        }
        resultMap.put("result", result);
        return resultMap;
    }

    public String getAuthorizeUrl(String oid,String orderNo, String orderIdStr, Integer payId, Integer callType, String openId) {
        String url = "";
        String returnUrl = "";
        String getAppid = "";
        IPublicAccountService wcService = ServiceFactory.create(IPublicAccountService.class);
        PublicAccount pa = wcService.byOriginalId(oid);
        try {
            //万达调用
            if (callType.equals(1)) {
                /*---http://m.gongsibao.com/mwandat1/#/wxPay/my/wxpay.html---*/
                String getWdUrl = icompanyDomain;
                String finalUrl;
                if (openId.equals("")) {
                    finalUrl = getWdUrl;
                } else {
                    finalUrl = getWdUrl+ "?openId=" + openId;
                }
                returnUrl = UrlHelper.encode(finalUrl + "&orderNoStr=" + orderNo + "_" + orderIdStr + "_" + payId + "&orderIdStr=" + orderIdStr + "&payIdStr=" + SecurityUtils.rc4Encrypt(payId) + "");
                getAppid = pa.getAppId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + getAppid + "&redirect_uri=" + returnUrl + "&response_type=code&scope=snsapi_base&state=WeiXin";
        return url;
    }

    /**
     * @param request
     * @return com.netsharp.rest.common.web.ResponseData
     * @Description: 用户验证, 通过openId, 验证用户是否存在，公司是否存在
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/24
     */
    @RequestMapping("/user/account/validate")
    public ResponseData userValidate(HttpServletRequest request) {
        String openId = openId(request);
        try {
            // 验证用户
            AccountValidateDTO dto = accountService.validAccountByOpenId(openId);
            return ResponseData.getSuccess(dto, "");
        } catch (Exception ex) {
            logger.error("", ex);
            return ResponseData.getException();
        }
    }

    /**
     * @param request
     * @return com.netsharp.rest.common.web.ResponseData
     * @Description: 用户验证, 通过openId, 验证用户是否存在，公司是否存在
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/24
     */
    @RequestMapping(value = "/user/account/pkLogin", method = RequestMethod.POST)
    public ResponseData userPkLogin(HttpServletRequest request, @RequestBody Map<String, Object> req) {

        String openId = openId(request);

        String mobile = StringUtils.trimToEmpty(req.get("mobile"));
        String companyName = StringUtils.trimToEmpty(req.get("companyName"));
        String code = StringUtils.trimToEmpty(req.get("code"));

        // 构建dto对象
        LoginDTO dto = new LoginDTO();
        {
            dto.setOpenId(openId);
            dto.setMobile(mobile);
            dto.setCompanyName(companyName);
            dto.setAccountSourceClientId(10301);
            dto.setCustomerSourceId(4110218);
        }

        try {
            // 获取当前用户状态
            AccountValidateDTO validateDTO = accountService.validAccountByOpenId(openId);

            // 缺少手机号
            if (StringUtils.isBlank(validateDTO.getMobile())) {
                if (StringUtils.isBlank(mobile)) {
                    return ResponseData.getError(ResponseData.FAIL, "请填写手机号");
                }
                if (RegexUtils.isNotPhone(mobile)) {
                    return ResponseData.getError(ResponseData.FAIL, "手机号码格式不正确");
                }

                // 验证码判断
                String sendCode = StringUtils.trimToEmpty(redisClient.get(mobile));
                if (StringUtils.isBlank(code)) {
                    return ResponseData.getError(ResponseData.FAIL, "请输入验证码");
                }

                if (!sendCode.equals(code)) {
                    return ResponseData.getError(ResponseData.FAIL, "验证码不正确");
                }
            } else {
                dto.setMobile(validateDTO.getMobile());
            }

            // 缺少公司
            if (StringUtils.isBlank(validateDTO.getCompanyName())) {
                if (StringUtils.isBlank(companyName)) {
                    return ResponseData.getError(ResponseData.FAIL, "请填写公司名称");
                }

                // 验证公司名称是否存在于大数据 TODO 目前测试环境ip不在大数据白名单，先注释掉这里，让测试先测
//                EntRegistry entRegistry = TaurusApiService.getEntRegistry(companyName);
//                if (null == entRegistry) {
//                    return ResponseData.getError(ResponseData.FAIL, "公司[" + companyName + "]不存在");
//                }
            } else {
                dto.setCompanyName(companyName);
            }

            // 验证用户
            Result<Account> result = netSharpAccountService.pkLogin(dto);
            if (Result.isSuccess(result)) {
                // 记录当前选择公司
                String chooseCompanyName = redisClient.get(ConstantKey.ICOMPANY_CHOOSE_KEY + openId);
                if (StringUtils.isBlank(chooseCompanyName)) {
                    redisClient.set(ConstantKey.ICOMPANY_CHOOSE_KEY + openId, dto.getCompanyName());
                }

                return ResponseData.getSuccess(result.getObj().getMobilePhone(), "");
            } else {
                return ResponseData.getError(ResponseData.FAIL, result.getMsg());
            }
        } catch (Exception ex) {
            logger.error("", ex);
            return ResponseData.getException();
        }
    }
}
