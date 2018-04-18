package com.gongsibao.api.conroller.igirl.ic;

import com.gongsibao.api.util.ResponseResult;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class IcExRegisterController {
    @GET
    @Path("/ic/updateState/{id}/{state}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult updateState(@PathParam("id")String id,
                                      @PathParam("state")Integer state){
        //TODO 工商注册自动更新公司状态，待完成
        ResponseResult result = new ResponseResult();
        result.setCode("200");
        result.setData(null);
        return result;
    }
}
