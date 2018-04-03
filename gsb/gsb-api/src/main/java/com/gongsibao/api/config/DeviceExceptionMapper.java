package com.gongsibao.api.config;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.gongsibao.api.dto.ma.MaResponseCodeEnum;
import com.gongsibao.api.util.ResponseResult;

/**
 * 股转接口自定义异常，处理股转的code返回和message返回，（需要手动注册）
 */
public class DeviceExceptionMapper implements ExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception e) {
        Response.ResponseBuilder ResponseBuilder = null;
        if (e instanceof DeviceException) {

            ResponseResult result = new ResponseResult();
            DeviceException de = (DeviceException) e;//根据类型处理不同的异常
            result.setCode(MaResponseCodeEnum.paraError.getText());
            result.setMessage(de.getMessage());
            ResponseBuilder = Response.ok(result).status(200).type(MediaType.APPLICATION_JSON);
            //throw new WebApplicationException(response);//处理在response

        } else {
            ResponseBuilder = Response.ok("false").status(500).type(MediaType.APPLICATION_JSON);
            return ResponseBuilder.build ();
        }
        return ResponseBuilder.build();
    }
}
