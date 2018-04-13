package com.gongsibao.rest.service.user.impl;

import com.gongsibao.account.base.IAccountDingtalkCompanyService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.rest.service.user.IAccountService;
import com.gongsibao.utils.sms.SmsHelper;
import org.netsharp.communication.ServiceFactory;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService{
    com.gongsibao.account.base.IAccountService accountService= ServiceFactory.create(com.gongsibao.account.base.IAccountService.class);
    @Override
    public Account login(String openId) {

        return null;
    }
    /**
     * @Description:TODO 发送验证码
     * @param  [mobile, code]
     * @return void
     * @author bhpeng <bhpeng@gongsibao.com>
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
        //更新uc_account 新增一条
        //更新uc_account_weixin 表 更新mobile字段和account_id
    }

    @Override
    public void createAccount(String openId) {
        //创建微信用户在表中新增记录 uc_account_weixin
    }
}
