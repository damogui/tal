package com.gongsibao.rest.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ffli <ffli@gongsibao.com>
 * @Description: TODO
 * @date 2018/4/17 16:54
 */
public class BaseController {

    protected String openId(HttpServletRequest request){
        return request.getHeader(UserHeaders.openId);
    }
}
