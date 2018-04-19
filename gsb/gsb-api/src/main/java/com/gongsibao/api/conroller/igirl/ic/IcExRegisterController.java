package com.gongsibao.api.conroller.igirl.ic;

import com.gongsibao.api.util.ResponseResult;
import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.ic.ex.dict.ApprovalType;
import com.gongsibao.igirl.ic.base.IcExRegisterService;
import org.netsharp.communication.ServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class IcExRegisterController {
    IcExRegisterService service = ServiceFactory.create(IcExRegisterService.class);

    @GET
    @Path("/ic/updateState/{id}/{state}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult updateState(@PathParam("id")String id,
                                      @PathParam("state")Integer state){
        service.updateState(id,state);
        ResponseResult result = new ResponseResult();
        result.setCode("200");
        result.setData(null);
        return result;
    }

    @GET
    @Path("/ic/getCaseToUpdate")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult getCaseToUpdate(){
        List<IcExRegisterCase> list = service.getIcCaseByType(ApprovalType.WAIT);
        ResponseResult result = new ResponseResult();
        if (list.isEmpty()){
            result.setCode("-1");
            result.setData("");
            result.setMessage("当前没有需要查询的数据");
        }else{
            result.setCode("200");
            result.setData(list);
            result.setMessage("获取成功");
        }
        return result;
    }

    @GET
    @Path("/ic/updateIcCase/{name}/{state}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult updateIcCase(@PathParam("name")String name,
                                      @PathParam("state")Integer state){
        ResponseResult result = new ResponseResult();
        IcExRegisterCase icCase = service.updateIcCase(name,state);
        if (icCase!=null){
            result.setCode("200");
            result.setData("");
            result.setMessage("修改成功");
        }else{
            result.setCode("-1");
            result.setData("");
            result.setMessage("当前公司数据不存在");
        }
        return result;
    }
}
