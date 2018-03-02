package com.gongsibao.api.conroller.igirl;

import com.gongsibao.api.util.ResponseResult;
import com.gongsibao.igirl.base.ITransferTradeMarkService;
import com.gongsibao.igirl.dto.TransferTradeMarkDto;
import org.netsharp.communication.ServiceFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/igirl")
public class TtmAutoSubmitController {
    ITransferTradeMarkService service = ServiceFactory.create(ITransferTradeMarkService.class);
    @GET
    @Path("/ttm/autosub")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult ctmToRobot(){
        List<TransferTradeMarkDto> dtos = service.ctmToRobot();
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
    @Path("/ttm/updateTtmState/{agentFileNum}/{state}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseResult updateTtmState(@PathParam("agentFileNum")String agentFileNum,
                                         @PathParam("state")Integer state){
        ResponseResult result = new ResponseResult();
        service.updateTtmState(agentFileNum,state);

        return result;
    }
}
