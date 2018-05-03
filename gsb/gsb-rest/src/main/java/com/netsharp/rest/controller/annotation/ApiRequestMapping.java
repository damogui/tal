package com.netsharp.rest.controller.annotation;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * 重写handlerMapping
 * 定义api拦截方法
 */
public class ApiRequestMapping extends RequestMappingHandlerMapping {
    /**
     * 映射匹配自定义注解（Api标注在类级别）
     * @param handlerType
     * @return
     */
    protected RequestCondition<ApiCondition> getCustomTypeCondition(Class<?>handlerType) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        return createCondition(apiVersion);
    }

    /**
     * 映射匹配自定义注解（Api标注在方法级别）
     * @param method
     * @return
     */
    protected RequestCondition<ApiCondition>  getCustomMethodCondition(Method method){
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return createCondition(apiVersion);
    }

    /**
     * 获取当前控制器标注的版本，初始化Api版本条件
     * @param apiVersion
     * @return
     */
    private RequestCondition<ApiCondition> createCondition(ApiVersion apiVersion) {
        return apiVersion ==null ?null :new ApiCondition(apiVersion.value());
    }
}
