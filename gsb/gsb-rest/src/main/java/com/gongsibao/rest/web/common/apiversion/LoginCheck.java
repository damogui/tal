package com.gongsibao.rest.web.common.apiversion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * ClassName: LoginCheck
 * @Description: TODO 验证登录
 * @author hbpeng <hbpeng@gongsibao.com>
 * @date 2018/4/17 18:54
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginCheck {
}
