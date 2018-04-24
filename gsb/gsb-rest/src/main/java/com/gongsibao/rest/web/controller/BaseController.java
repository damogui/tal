package com.gongsibao.rest.web.controller;

import com.gongsibao.account.base.IAccountWeiXinService;
import com.gongsibao.entity.acount.Account;
import com.gongsibao.rest.web.common.util.Assert;
import com.gongsibao.rest.web.common.util.StringUtils;
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
        return StringUtils.trimToEmpty(request.getHeader(UserHeaders.openId));
    }
    protected String originalId(HttpServletRequest request){
        return StringUtils.trimToEmpty(request.getHeader(UserHeaders.originalId));
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

    protected   String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
