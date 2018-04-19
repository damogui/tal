package com.gongsibao.rest.web.controller;

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
    protected String originalId(HttpServletRequest request){
        return request.getHeader(UserHeaders.originalId);
    }
}
