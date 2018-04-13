package com.gongsibao.rest.service.user;

import com.gongsibao.entity.acount.Account;
/**
 * ClassName: AccountService
 * @Description: TODO 微信用户登录验证
 * @author bhpeng <bhpeng@gongsibao.com>
 * @date 2018/4/12 18:33
 */
public interface IAccountService {
    Account login(String openId);

    void sendSms(String mobile,String code);

    void updateAccount(String mobile,String openId);

    void createAccount(String openId);
}
