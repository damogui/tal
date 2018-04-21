package com.gongsibao.rest.service.user;

import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountWxMsg;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.rest.base.user.IAccountService;
import com.gongsibao.rest.web.common.util.*;
import com.gongsibao.rest.web.common.web.Constant;
import com.gongsibao.u8.base.IOrderPayMapService;
import com.gongsibao.utils.NumberUtils;
import com.gongsibao.utils.sms.SmsHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jdom.JDOMException;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.wx.pa.base.ICustomService;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Service
public class AccountService implements IAccountService{
    com.gongsibao.account.base.IAccountService accountService= ServiceFactory.create(com.gongsibao.account.base.IAccountService.class);
    IAccountWeiXinService accountWeiXinService=ServiceFactory.create(IAccountWeiXinService.class);
    ICustomService customService=ServiceFactory.create(ICustomService.class);
    IOrderPayMapService orderPayMapService=ServiceFactory.create(IOrderPayMapService.class);
    @Value("{wx_notify_key}")
    private String notifyKey;
    /*日志*/
    private static Logger log = Logger.getLogger(AccountService.class);
    /**
     * @Description:TODO 登录验证
     * @param  openId
     * @return com.gongsibao.entity.acount.Account
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/16 14:39
     */
    @Override
    public Account login(String openId) {
        Fans weiXin=accountWeiXinService.queryFansByOpenId(openId);
        if(null==weiXin){
            //创建微信账号
            this.createFans(openId);
            return null;
        }else if(null==weiXin.getUserId()){
            return null;
        }else{
            return accountService.byId(weiXin.getUserId());
        }
    }
    /**
     * @Description:TODO 根据openid 获取用户信息
     * @param  openId
     * @return com.gongsibao.entity.acount.Account
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/19 15:06
     */
    @Override
    public Account queryByOpenId(String openId) {
        return accountWeiXinService.queryByOpenId(openId);
    }

    /**
     * @Description:TODO 发送验证码
     * @param  mobile, code
     * @return void
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/12 19:00
     */
    @Override
    public void sendSms(String mobile, String code) {
        // 发送短信
        new Thread() {
            @Override
            public void run() {
                SmsHelper.send(mobile, code);
            }
        }.start();
    }

    @Override
    public void updateAccount(String mobile,String openId) {
        Account accountOld=accountService.byMobile(mobile);
        if(null==accountOld){
            //更新uc_account 新增一条
            Account account = new Account();{
                account.toNew();
                account.setPasswd("");
                account.setCreateTime(new Date());
                account.setMobilePhone(mobile);
                account.setTicket(UUID.randomUUID().toString());
                account.setEmail("");
                account.setIdentityCard("");
                account.setTelephone("");
                account.setRealName("");
                account.setCreateTime(new Date());
                account.setIsBbk("");
                account.setName("");
                //来源微信
                account.setSourceClientId(1036);
                account.setHeadThumbFileId(0);
            }
            Account result = accountService.save(account);
            //更新uc_account_weixin 表 更新 account_id
            accountWeiXinService.bandMobile(result.getId(),openId);
        }else{
            //更新uc_account_weixin 表 更新 account_id
            accountWeiXinService.bandMobile(accountOld.getId(),openId);
        }
    }

    @Override
    public Boolean createFans(String openId) {
        //新增粉丝表数据
        Fans fans=accountWeiXinService.createFans(openId);
        return fans.getId()==null?false:true;
    }

    @Override
    public void sendTextMessage(String content, String openId, String originalId) {
        customService.sendTextMessage(content,openId,originalId);
    }

    @Override
    public Account queryByMobile(String mobile) {
        Account account=accountService.byMobile(mobile);
        if(null==account){
            return null;
        }
        Fans weiXin=accountWeiXinService.queryFansByUserId(account.getId());
        if(null==weiXin){
            return null;
        }
        account.setOpenid(weiXin.getOpenId());
        return account;
    }

    @Override
    public void updateTicket(Integer id, String ticket) {
        accountService.updateTicket(id,ticket);
    }
    /**
     * @Description:TODO
     * @param  mobile, orderPorudctId
     * @return void
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/18 17:25
     */
    @Override
    public void pushOrderStateMsg(String originalId,String mobile, Integer orderPorudctId) {
        accountWeiXinService.pushOrderStateMsg(originalId,mobile,orderPorudctId);
    }

    @Override
    public void buySuccessSendMsg(Integer accountId, String moeny, String productName, String first, String url) {
        accountWeiXinService.pushTextMsg(accountId,first,moeny,productName,null,url,null, AccountWxMsg.BUY_SUCCESS);
    }

    @Override
    public void buySuccessSendMsg(String originalId, Integer accountId, String moeny, String productName, String first, String url) {
        accountWeiXinService.pushTextMsgByOriginalId(originalId,accountId,first,moeny,productName,null,url,null, AccountWxMsg.BUY_SUCCESS);
    }

    @Override
    public Integer getWxPayH5Param(String oid,String openId, String orderNoStr, Integer totalFee, String body, Integer userChannel, SortedMap<Object, Object> resMap) {
        //获取openID
        String openid =openId;
        IPublicAccountService publicAccountService=ServiceFactory.create(IPublicAccountService.class);
        PublicAccount publicAccount=publicAccountService.byOriginalId(oid);
        if (StringUtils.isBlank(openid)) {
            return -1;//获取openid失败
        }
        //预支付id
        String prepay_id;
        try {
            prepay_id = wxpay(publicAccount,orderNoStr, NumberUtils.toInt(totalFee), body, 1, openid, userChannel);
        } catch (Exception e) {
            prepay_id = "";
        }
        if (StringUtils.isBlank(prepay_id)) {
            return -2;//获取prepay_id失败
        }
        String noncestr = Sha1Util.getNonceStr();//生成随机字符串
        String timestamp = Sha1Util.getTimeStamp();//生成1970年到现在的秒数.
        resMap.put("appId", publicAccount.getAppId());
        resMap.put("timeStamp", timestamp);
        resMap.put("nonceStr", noncestr);
        resMap.put("package", "prepay_id=" + prepay_id);
        resMap.put("signType", "MD5");
        //生成支付签名,这个签名给 微信支付的调用使用
        String paySign = PayCommonUtil.createSign("UTF-8", resMap, notifyKey);
        resMap.put("paySign", paySign);
        return 1;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public String wxpay(PublicAccount account, String out_trade_no, Integer order_price, String body, Integer clientType, String openId, Integer userChannel) throws JDOMException, IOException {
        // 账号信息
        String appid = account.getAppId();
        //String appsecret = PayConfigUtil.APP_SECRET; // appsecret
        String mch_id = account.getMch_id();// 商业号
        String key = account.getToken();// key PayConfigUtil.getKey()
        //随机字符串
        String nonce_str = getNonceStr();

//        String order_price = "1"; // 价格   注意：价格的单位是分
//        String body = "goodssssss";   // 商品名称
//        String out_trade_no = "11338"; // 订单号

        // 获取发起电脑 ip
//        String spbill_create_ip = PayConfigUtil.getIP();
        // 回调接口
        String notify_url =account.getMchNotifyUrl();

        // clientType 客户端类别（0:网页端（扫码：NATIVE）；1:H5（公众号）端（JSAPI）；2：APP端（APP））
        String trade_type = "JSAPI";
        // body 类型：String(128),当body长度过长时，会报错"return_msg=body参数长度有误, return_code=FAIL"
        body = StringUtils.substring(body, 100);

        SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("out_trade_no", StringUtils.trimToEmpty(out_trade_no));
        packageParams.put("total_fee", StringUtils.trimToEmpty(order_price.toString()));
        packageParams.put("spbill_create_ip", "");
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        //当是公众号支付时“openid”必传
        if (trade_type == "JSAPI")
            packageParams.put("openid", openId);
        log.info("==========out_trade_no is:==========" + out_trade_no);
        String sign = PayCommonUtil.createSign("UTF-8", packageParams, key);
        packageParams.put("sign", sign);
        String requestXML = PayCommonUtil.getRequestXml(packageParams);

        String resXml = HttpUtil.postData(Constant.PAY_API, requestXML);

        Map map = XMLUtil.doXMLParse(resXml);
        log.info("==========map:==========" + map);
        String return_msg = new String(((String) map.get("return_msg")).getBytes("ISO-8859-1"), "UTF-8");
        log.info("==========return_msg:==========" + return_msg);
        //String return_code = (String) map.get("return_code");
        //String prepay_id = (String) map.get("prepay_id");
        // H5支付时:统一下单接口返回支付相关参数给商户后台，如支付跳转url（参数名“mweb_url”，前端访问中转页面“mweb_url”主动唤起微信支付收银台）【此h5支付接口，腾讯暂时不受理了，申请不了了】
        String urlCode = StringUtils.trimToEmpty(map.get("prepay_id").toString()) ;
        log.info("==========urlCode:==========" + urlCode);
        return urlCode;
    }

    //region 是有方法
    //获取随机字符串
    private String getNonceStr() {
        String currTime = PayCommonUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = PayCommonUtil.buildRandom(4) + "";
        return strTime + strRandom;
    }

    @Override
    public List<OrderPayMap> pageByProperties(Integer orderId, Integer payId) {
        return orderPayMapService.queryByOrderIdPayId(orderId,payId);
    }

}
