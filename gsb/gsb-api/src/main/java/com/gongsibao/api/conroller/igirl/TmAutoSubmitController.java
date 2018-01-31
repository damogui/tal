package com.gongsibao.api.conroller.igirl;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gongsibao.entity.igirl.TradeMark;
import org.netsharp.communication.ServiceFactory;
import com.gongsibao.igirl.base.ITradeMarkService;
import com.gongsibao.igirl.dto.TradeMark.TmForRobotDto;
@Path("/igirl")
public class TmAutoSubmitController {
	ITradeMarkService tmService=ServiceFactory.create(ITradeMarkService.class);
	@GET
	@Path("/tm/autosub/{innerHour}")
	@Produces(MediaType.APPLICATION_JSON)
	public TmForRobotDto tmsForRobot(@PathParam("innerHour") Integer innerHour) {
		TmForRobotDto trf =tmService.tmsForRobot(innerHour);
		return trf;
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
