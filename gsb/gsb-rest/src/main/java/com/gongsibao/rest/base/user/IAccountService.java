package com.gongsibao.rest.base.user;

import com.gongsibao.entity.acount.Account;
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

    void pushOrderStateMsg(String mobile, Integer orderPorudctId);
}
