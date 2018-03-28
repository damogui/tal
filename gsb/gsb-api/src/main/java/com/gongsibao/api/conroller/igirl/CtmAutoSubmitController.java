package com.gongsibao.api.conroller.igirl;

import com.gongsibao.api.util.ResponseResult;
import com.gongsibao.entity.igirl.tm.ChangeTradeMark;
import com.gongsibao.igirl.tm.base.IChangeTradeMarkService;
import com.gongsibao.igirl.tm.dto.ChangeTradeMarkDto;
import org.netsharp.communication.ServiceFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/igirl")
public class CtmAutoSubmitController {
    IChangeTradeMarkService service = ServiceFactory.create(IChangeTradeMarkService.class);

    @GET
    @Path("/ctm/autosub")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult ctmToRobot(){
        List<ChangeTradeMarkDto> dtos = service.ctmToRobot();
        ResponseResult result = new ResponseResult();
        result.setCode("200");
        result.setData(dtos);
        if (dtos.size()>0){
            result.setMessage("获取变更数据成功");
        }else{
            result.setMessage("未获取到当前更新数据");
        }
        return result;
    }

    @GET
    @Path("/ctm/updateCtmState/{agentFileNum}/{state}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult updateCtmState(@PathParam("agentFileNum")String agentFileNum,
                                 @PathParam("state")Integer state){
        ResponseResult result = new ResponseResult();
        ChangeTradeMark ctm = service.updateCtmState(agentFileNum,state);
        if (ctm!=null){
            result.setCode("200");
            result.setMessage("更新完成");
        }else {
            result.setCode("-1");
            result.setMessage("更新失败");
        }
        return result;
    }
}
