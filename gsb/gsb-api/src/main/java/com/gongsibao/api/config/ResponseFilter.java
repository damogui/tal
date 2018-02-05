package com.gongsibao.api.config;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.ext.Provider;

import com.gongsibao.api.auth.AuthAnnotation;
import com.gongsibao.api.dto.ma.MaResponseCodeEnum;
import com.gongsibao.api.util.ResponseResult;


/**
 * @author hw
 *         回复过滤
 */
@Provider
@AuthAnnotation
public class ResponseFilter implements ContainerResponseFilter {

    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {

        /**
         * 设置跨域的请求类型（暂时全部允许）
         */
        containerResponseContext.getHeaders().add("Access-Control-Allow-Origin", "*");

        /**
         * 允许的Header值，不支持通配符
         */
        containerResponseContext.getHeaders().add("Access-Control-Allow-Headers", "origin,content-type,accept,authorization,token");

        /**
         * 是否允许请求带有验证信息
         */
        containerResponseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");

        /**
         * 即使只用其中几种，header和options是不能删除的，因为浏览器通过options请求来获取服务的跨域策略
         */
        containerResponseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");

        /**
         * CORS策略的缓存时间
         */
        containerResponseContext.getHeaders().add("Access-Control-Max-Age", "1209600");

        /**
         * 媒体类型（不知道怎么覆盖，先删除掉，再添加）
         */
        containerResponseContext.getHeaders().remove("Content-Type");
        containerResponseContext.getHeaders().add("Content-Type", "application/json");

        /**
         * 跨域 请求时
         */
        String method = containerRequestContext.getMethod().toLowerCase();
        if (method.equals("options")) {

            containerResponseContext.setStatus(200);
            return;
        }

        EntityTag tag = containerResponseContext.getEntityTag();
        if (tag == null)//非null代表已经触发异常,由异常处理
        {
            Object entity = containerResponseContext.getEntity();
            ResponseResult result = new ResponseResult();
            {
                result.setData(entity);
                containerResponseContext.setEntity(result);
            }
        }


        // 可以通过 throw new WebApplicationException(Status.UNAUTHORIZED); 来中断请求
    }
}
