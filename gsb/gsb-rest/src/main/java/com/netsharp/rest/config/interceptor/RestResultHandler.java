package com.netsharp.rest.config.interceptor;

import com.netsharp.rest.controller.result.DateJsonValueProcessor;
import com.netsharp.rest.controller.result.RestResult;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * ClassName: ReturnHandler
 * @Description: TODO 返回结果统一处理
 * @author hbpeng <hbpeng@gongsibao.com>
 * @date 2018/5/2 16:10
 */
public class RestResultHandler implements HandlerMethodReturnValueHandler {
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return true;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        if (returnValue == null) {
            mavContainer.setRequestHandled(true);
            return;
        }
        mavContainer.setRequestHandled(true);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        response.setContentType("application/json;charset=UTF-8");
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Timestamp.class,new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        if(returnValue instanceof String){
            JSONObject returnVal=JSONObject.fromObject(RestResult.getSuccess(returnValue,returnValue.toString()),config);
            response.getWriter().append(returnVal.toString()).flush();
        }else{
            JSONObject returnVal=JSONObject.fromObject(RestResult.getSuccess(returnValue,""),config);
            response.getWriter().append(returnVal.toString()).flush();
        }
    }
}
