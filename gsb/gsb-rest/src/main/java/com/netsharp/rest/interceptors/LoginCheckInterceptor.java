package com.netsharp.rest.interceptors;

import com.gongsibao.entity.acount.Account;
import com.gongsibao.rest.base.user.IAccountService;
import com.gongsibao.rest.web.common.apiversion.LoginCheck;
import com.gongsibao.rest.web.common.web.ResponseData;
import com.gongsibao.rest.controller.UserHeaders;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ClassName: LoginCheckInterceptor
 * @Description: TODO 登录拦截器
 * @author hbpeng <hbpeng@gongsibao.com>
 * @date 2018/4/17 18:59
 */
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(LoginCheckInterceptor.class);
    @Autowired
    IAccountService accountService;
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            LoginCheck hostCheck = method.getMethodAnnotation(LoginCheck.class);
            if (null != hostCheck) {
                String openId = request.getHeader(UserHeaders.openId);
                if(null==openId){
                    log.info("微信验证登录，openid为null");
                    ResponseData webResult = ResponseData.getError(-1, "openId为空！");
                    response.setHeader("Content-type", "application/json;charset=UTF-8");
                    response.getOutputStream().write(JSONObject.fromObject(webResult).toString().getBytes("utf-8"));
                    return false;
                }else{
                    Account account=accountService.login(openId);
                    if(null==account){
                        ResponseData webResult = ResponseData.getError(403, "用户未登录");
                        response.setHeader("Content-type", "application/json;charset=UTF-8");
                        response.getOutputStream().write(JSONObject.fromObject(webResult).toString().getBytes("utf-8"));
                        log.info("微信验证登录，未登录openid={}", openId);
                        return false;
                    }else{
                        return true;
                    }
                }
            }
            return true;
        }
        return true;
    }
}
