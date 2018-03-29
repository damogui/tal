package com.gongsibao.api.conroller.igirl;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.netsharp.communication.ServiceFactory;

import com.gongsibao.api.util.ResponseResult;
import com.gongsibao.entity.igirl.tm.TradeMark;
import com.gongsibao.igirl.tm.base.ITradeMarkService;
import com.gongsibao.igirl.tm.dto.TradeMark.TradeMarkApplyInfo;

@Path("/igirl")
public class TmAutoSubmitController {
	ITradeMarkService tmService=ServiceFactory.create(ITradeMarkService.class);
	@GET
	@Path("/tm/autosub/{innerHour}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseResult tmsForRobot(@PathParam("innerHour") Integer innerHour) {
		List<TradeMarkApplyInfo> tminfos =tmService.tmsForRobot(innerHour);
		ResponseResult result = new ResponseResult();
		result.setCode("200");
		if (tminfos.size()>0){
			result.setMessage("获取数据成功！");
		}else {
			result.setMessage("未获取到申报数据！");
		}
		result.setData(tminfos);
		return result;
	}
	@GET
	@Path("/tm/updatestate/{proxyCode}/{stateCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateState(@PathParam("proxyCode") String proxyCode,@PathParam("stateCode") Integer stateCode){
		TradeMark tm = tmService.tmRobotUpdateMarkState(proxyCode,stateCode);
		if (tm!=null){
			return "success";
		}
		return "error";
	}

    @GET
    @Path("/tm/updateTradeMarkCode/{proxyCode}/{code}/{stateCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateTradeMarkCode(@PathParam("proxyCode") String proxyCode,
                                      @PathParam("code") String code,
                                      @PathParam("stateCode") Integer stateCode){
	    TradeMark tm = tmService.tmRobotUpdateMarkCode(proxyCode,code,stateCode);
	    if (tm!=null){
	        return "success";
        }
	    return "error";
    }

}
