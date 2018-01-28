package com.gongsibao.api.conroller.igirl;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.netsharp.communication.ServiceFactory;
import com.gongsibao.igirl.base.ITradeMarkService;
import com.gongsibao.igirl.dto.TradeMark.TradeMarkApplyInfo;
@Path("/igirl")
public class TmAutoSubmitController {
	ITradeMarkService tmService=ServiceFactory.create(ITradeMarkService.class);
	@GET
	@Path("/tm/autosub/{innerHour}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<TradeMarkApplyInfo> tmsForRobot(@PathParam("innerHour") Integer innerHour) {
		List<TradeMarkApplyInfo> applyInfos =tmService.tmsForRobot(innerHour);
		return applyInfos;
	}
}
