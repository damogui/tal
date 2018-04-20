package com.gongsibao.rest.base.user;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.entity.trade.OrderPayMap;
import org.jdom.JDOMException;
import org.netsharp.wx.pa.entity.PublicAccount;

import java.io.IOException;
import java.util.List;
import java.util.SortedMap;

/**
 * ClassName: AccountService
 * @Description: TODO 微信用户登录验证
 * @author hbpeng <hbpeng@gongsibao.com>
 * @date 2018/4/12 18:33
 */
public interface IAccountService {
    Account login(String openId);

    void sendSms(String mobile,String code);

    void updateAccount(String mobile,String openId);

    Boolean createFans(String openId);

    void sendTextMessage(String content, String openId,String originalId);

    Account queryByMobile(String mobile);

    void updateTicket(Integer id,String ticket);

    void pushOrderStateMsg(String originalId,String mobile, Integer orderPorudctId);

    void buySuccessSendMsg(Integer accountId,String moeny,String productName,String first,String url);

    void buySuccessSendMsg(String originalId,Integer accountId,String moeny,String productName,String first,String url);

    Account queryByOpenId(String openId);

    Integer getWxPayH5Param(String oid,String openId, String orderNoStr, Integer totalFee, String body, Integer userChannel, SortedMap<Object, Object> resMap);

    String wxpay(PublicAccount account, String out_trade_no, Integer order_price, String body, Integer clientType, String openId, Integer userChannel) throws JDOMException, IOException;

    List<OrderPayMap> pageByProperties(Integer orderId, Integer payId);
}
