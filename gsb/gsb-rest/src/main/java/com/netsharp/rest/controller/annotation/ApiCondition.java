package com.netsharp.rest.controller.annotation;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 过滤访问接口v(1-9)
 * 如果访问版本大于控制器版本，直接访问控制器版本
 * 如果版本冲突如有两个同样的，抛出异常
 */
public class ApiCondition implements RequestCondition<ApiCondition> {
    private final static Pattern VERSION_PREFIX_PATTERN =Pattern.compile("v(\\d+)/");
    private int apiVersion;
    public ApiCondition(int apiVersion){
        this.apiVersion = apiVersion;
    }
    public ApiCondition combine(ApiCondition other) {
        return new ApiCondition(other.getApision());
    }

    public ApiCondition getMatchingCondition(HttpServletRequest request) {
        Matcher m = VERSION_PREFIX_PATTERN.matcher(request.getRequestURI());
        if(m.find()){
            Integer version = Integer.valueOf(m.group(1));
            if(version >=this.apiVersion)
                return this;
        }
        return null;
    }

    public int compareTo(ApiCondition other, HttpServletRequest request) {
        return other.getApision() -this.apiVersion;

    }

    public int getApision() {
        return apiVersion;
    }
}
