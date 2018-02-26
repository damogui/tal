package com.gongsibao.api.conroller.igirl;

import com.gongsibao.igirl.base.IChangeTradeMarkService;
import com.gongsibao.igirl.dto.ChangeTradeMark.ChangeTradeMarkToRoBotDto;
import org.netsharp.communication.ServiceFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/igirl")
public class CtmAutoSubmitController {
    IChangeTradeMarkService service = ServiceFactory.create(IChangeTradeMarkService.class);

    @GET
    @Path("/ctm/autosub")
    @Produces(MediaType.APPLICATION_JSON)
    public ChangeTradeMarkToRoBotDto ctmToRobot(){
        ChangeTradeMarkToRoBotDto dto = service.ctmToRobot();
        return dto;
    }
}
