package com.gongsibao.rest.service.user;

import com.gongsibao.account.base.IAccountCompanyService;
import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.acount.AccountCompany;
import com.gongsibao.entity.acount.AccountWxMsg;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.rest.base.user.IAccountService;
import com.gongsibao.rest.web.common.util.*;
import com.gongsibao.rest.web.common.web.Constant;
import com.gongsibao.rest.web.dto.user.AccountValidateDTO;
import com.gongsibao.u8.base.IOrderPayMapService;
import com.gongsibao.utils.NumberUtils;
import com.gongsibao.utils.sms.SmsHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.jdom.JDOMException;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.wx.pa.base.ICustomService;
import org.netsharp.wx.pa.base.IFansService;
import org.netsharp.wx.pa.base.IPublicAccountService;
import org.netsharp.wx.pa.entity.Fans;
import org.netsharp.wx.pa.entity.PublicAccount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.Types;
import java.util.*;

@Service
public class AccountService implements IAccountService {
    com.gongsibao.account.base.IAccountService accountService = ServiceFactory.create(com.gongsibao.account.base.IAccountService.class);
    IAccountWeiXinService accountWeiXinService = ServiceFactory.create(IAccountWeiXinService.class);
    ICustomService customService = ServiceFactory.create(ICustomService.class);
    IOrderPayMapService orderPayMapService = ServiceFactory.create(IOrderPayMapService.class);

    // 会员绑定公司服务
    IAccountCompanyService accountCompanyService = ServiceFactory.create(IAccountCompanyService.class);

    @Value("${wx_notify_key}")
    private String notifyKey;
    /*日志*/
    private static Logger log = Logger.getLogger(AccountService.class);

    /**
     * @param openId
     * @return com.gongsibao.entity.acount.Account
     * @Description:TODO 登录验证
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/16 14:39
     */
    @Override
    public Account login(String openId) {
        Fans weiXin = accountWeiXinService.queryFansByOpenId(openId);
        if (null == weiXin) {
            //创建微信账号
            this.createFans(openId);
            return null;
        } else if (null == weiXin.getUserId()) {
            return null;
        } else {
            return accountService.byId(weiXin.getUserId());
        }
    }

    /**
     * @param openId
     * @return com.gongsibao.entity.acount.Account
     * @Description:TODO 根据openid 获取用户信息
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/19 15:06
     */
    @Override
    public Account queryByOpenId(String openId) {
        return accountWeiXinService.queryByOpenId(openId);
    }

    /**
     * @param mobile, code
     * @return void
     * @Description:TODO 发送验证码
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
    public void updateAccount(String mobile, String openId) {
        Account accountOld = accountService.byMobile(mobile);
        Fans fans=accountWeiXinService.queryFansByOpenId(openId);
        if (null == accountOld) {
            //更新uc_account 新增一条
            Account account = new Account();
            {
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
                if(fans!=null){
                    account.setIsWeiXin(Constant.SUBSCRIBE);
                    account.setFansId(fans.getId());
                }else{
                    account.setIsWeiXin(Constant.UNSUBSCRIBE);
                    account.setFansId(0);
                }
                //来源微信
                account.setSourceClientId(1036);
                account.setHeadThumbFileId(0);
            }
            Account result = accountService.save(account);
            //更新uc_account_weixin 表 更新 account_id
            accountWeiXinService.bandMobile(result.getId(), openId);
        } else {
            Fans oldFans=accountWeiXinService.queryFansByUserId(accountOld.getId());
            //判断是否绑定过手机号
            if(null!=oldFans&&oldFans.getUserId()!=null){
                //解绑
                oldFans.setUserId(0);
                IFansService fansService=ServiceFactory.create(IFansService.class);
                fansService.updateFans(oldFans);
            }
            if(fans!=null){
                accountOld.setIsWeiXin(Constant.SUBSCRIBE);
                accountOld.setFansId(fans.getId());
            }else{
                accountOld.setIsWeiXin(Constant.UNSUBSCRIBE);
                accountOld.setFansId(0);
            }
            accountOld.toPersist();
            accountService.save(accountOld);
            //更新uc_account_weixin 表 更新 account_id
            accountWeiXinService.bandMobile(accountOld.getId(), openId);
        }
    }

    @Override
    public Boolean createFans(String openId) {
        //新增粉丝表数据
        Fans fans = accountWeiXinService.createFans(openId);
        return fans.getId() == null ? false : true;
    }

    @Override
    public void sendTextMessage(String content, String openId, String originalId) {
        customService.sendTextMessage(content, openId, originalId);
    }

    @Override
    public Account queryByMobile(String mobile) {
        Account account = accountService.byMobile(mobile);
        if (null == account) {
            return null;
        }
        Fans weiXin = accountWeiXinService.queryFansByUserId(account.getId());
        if (null == weiXin) {
            return null;
        }
        account.setOpenid(weiXin.getOpenId());
        return account;
    }

    @Override
    public void updateTicket(Integer id, String ticket) {
        accountService.updateTicket(id, ticket);
    }

    /**
     * @param mobile, orderPorudctId
     * @return void
     * @Description:TODO
     * @author hbpeng <hbpeng@gongsibao.com>
     * @date 2018/4/18 17:25
     */
    @Override
    public void pushOrderStateMsg(String originalId, String mobile, Integer orderPorudctId) {
        accountWeiXinService.pushOrderStateMsg(originalId, mobile, orderPorudctId);
    }

    @Override
    public void buySuccessSendMsg(Integer accountId, String moeny, String productName, String first, String url) {
        accountWeiXinService.pushTextMsg(accountId, first, moeny, productName, null, url, null, AccountWxMsg.BUY_SUCCESS);
    }

    @Override
    public void buySuccessSendMsg(String originalId, Integer accountId, String moeny, String productName, String first, String url) {
        accountWeiXinService.pushTextMsgByOriginalId(originalId, accountId, first, moeny, productName, null, url, null, AccountWxMsg.BUY_SUCCESS);
    }

    @Override
    public Integer getWxPayH5Param(String ipAddress, String oid, String openid, String orderNoStr, Integer totalFee, String body, Integer userChannel, SortedMap<String, String> resMap) throws Exception {
        IPublicAccountService publicAccountService = ServiceFactory.create(IPublicAccountService.class);
        //获取公众号信息
        PublicAccount publicAccount = publicAccountService.byOriginalId(oid);
        if (StringUtils.isBlank(openid)) {
            return -1;//获取openid失败
        }
        //调用微信接口获取预支付id
        String prepay_id;
        try {
            prepay_id = wxpay(ipAddress, publicAccount, orderNoStr, NumberUtils.toInt(totalFee), body, 1, openid, userChannel);
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
        String paySign = PayCommonUtil.createSign("utf-8", resMap, notifyKey);
        resMap.put("paySign", paySign);
        return 1;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public String wxpay(String ipAddress, PublicAccount account, String out_trade_no, Integer order_price, String body, Integer clientType, String openId, Integer userChannel) throws JDOMException, IOException {
        if (StringUtils.isBlank(ipAddress)) {
            ipAddress = InetAddress.getLocalHost().getHostAddress();
        }
        // 账号信息
        String appid = account.getAppId();
        // 微信支付商户号
        String mch_id = account.getMch_id();
        // 随机字符串
        String nonce_str = getNonceStr();
        // 支付成功回调接口
        String notify_url = account.getMchNotifyUrl();
        // body 类型：String(128),当body长度过长时，会报错"return_msg=body参数长度有误, return_code=FAIL"
        body = com.gongsibao.rest.web.common.util.StringUtils.getSubStr(body, 100);
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", body);
        packageParams.put("out_trade_no", StringUtils.trimToEmpty(out_trade_no));
        packageParams.put("total_fee", StringUtils.trimToEmpty(order_price.toString()));
        packageParams.put("spbill_create_ip", ipAddress);
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type",  "JSAPI");
        //当是公众号支付时“openid”必传
        packageParams.put("openid", openId);
        log.info("packageParams:" + packageParams);
        String sign = PayCommonUtil.createSign("utf-8", packageParams, notifyKey);
        // 微信支付第一次签名
        packageParams.put("sign", sign);
        String requestXML = PayCommonUtil.getRequestXml(packageParams);
        // 获取预支付id
        String resXml = HttpUtil.postData(Constant.PAY_API, requestXML);
        Map map = XMLUtil.doXMLParse(resXml);
        try {
            String return_msg = new String(((String) map.get("return_msg")).getBytes("ISO-8859-1"), "UTF-8");
            // H5支付时:统一下单接口返回支付相关参数给商户后台，如支付跳转url（参数名“mweb_url”，前端访问中转页面“mweb_url”主动唤起微信支付收银台）【此h5支付接口，腾讯暂时不受理了，申请不了了】
            return  StringUtils.trimToEmpty(map.get("prepay_id").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取随机字符串
    private String getNonceStr() {
        String currTime = PayCommonUtil.getCurrTime();
        String strTime = currTime.substring(8, currTime.length());
        String strRandom = PayCommonUtil.buildRandom(4) + "";
        return strTime + strRandom;
    }

    @Override
    public List<OrderPayMap> pageByProperties(Integer orderId, Integer payId) {
        return orderPayMapService.queryByOrderIdPayId(orderId, payId);
    }

    @Override
    public boolean matchOpenIdOid(String openId, Integer accountId) {
        IFansService fansService= ServiceFactory.create(IFansService.class);
        Oql oql = new Oql();
        {
            oql.setType(Fans.class);
            oql.setSelects("Fans.*");
            oql.setFilter("openId=?");
            oql.setFilter("publicAccountId=?");
            oql.getParameters().add("@openId", openId, Types.VARCHAR);
            oql.getParameters().add("@publicAccountId", accountId, Types.INTEGER);
        }
        return fansService.queryFirst(oql)!=null?true:false;
    }

    @Override
    public AccountValidateDTO validAccountByOpenId(String openId) {
        AccountValidateDTO dto = new AccountValidateDTO();
        if (StringUtils.isBlank(openId)) {
            return dto;
        }

        dto.setOpenId(openId);
        Fans weiXin = accountWeiXinService.queryFansByOpenId(openId);
        if (null == weiXin) {
            // 创建微信账号
            weiXin = accountWeiXinService.createFans(openId);
        }

        if (null != weiXin.getUserId()) {
            // 查询关联会员
            Account account = accountService.getById(weiXin.getUserId());
            if (null != account) {
                dto.setMobile(account.getMobilePhone());
                // 查会员关联企业
                List<AccountCompany> companyList = accountCompanyService.findByAccount(account.getId(), 1);
                if (CollectionUtils.isNotEmpty(companyList)) {
                    // TODO 暂时取第一个公司
                    String companyName = companyList.get(0).getCompanyName();
                    dto.setCompanyName(companyName);
                }
            }
        }
        return dto;
    }
}
