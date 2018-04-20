package com.gongsibao.rest.web.controller;

import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.rest.web.common.util.Assert;
import org.netsharp.communication.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO
 * @date 2018/4/17 16:54
 */
public class BaseController {

    protected final static IAccountWeiXinService $accountWeiXinService = ServiceFactory.create(IAccountWeiXinService.class);

    protected String openId(HttpServletRequest request){
        return request.getHeader(UserHeaders.openId);
    }
    protected String originalId(HttpServletRequest request){
        return request.getHeader(UserHeaders.originalId);
    }

    protected Account accountByOpenId(HttpServletRequest request){
        return accountByOpenId(openId(request));
    }

    protected Integer accountIdByOpenId(HttpServletRequest request){
        return accountIdByOpenId(openId(request));
    }

    protected Account accountByOpenId(String openId){
        Assert.hasText(openId,"尚未登录!");
        Account account = $accountWeiXinService.queryByOpenId(openId);
        Assert.notNull(account,"账号尚未绑定!");
        return account;
    }

    protected Integer accountIdByOpenId(String openId){
        Account account = accountByOpenId(openId);
        Assert.notNull(account.getId(),"账号尚未绑定!");
        return account.getId();
    }

}
