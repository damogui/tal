package com.gongsibao.api.conroller.ma;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gongsibao.api.base.ICarService;
import com.gongsibao.api.config.MyJacksonJsonProvider;
import com.gongsibao.api.dto.OrderDTO;

import com.gongsibao.api.dto.ma.QuerySellingDemandDTO;
import com.gongsibao.api.dto.ma.SellingDemandDTO;
import com.gongsibao.api.service.ma.SellingDemandDTOService;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.base.ISellingDemandService;
import com.gongsibao.ma.service.SellingDemandService;
import org.junit.runners.Parameterized;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.annotations.Param;
import org.netsharp.util.ResultCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import static sun.plugin.javascript.navig.JSType.Form;

/**
 * Created by win on 2018/2/1.
 */

@Path("/selling")
public class SellingController {

    //ISellingDemandService sellingDemandServiceI = ServiceFactory.create(ISellingDemandService.class);
    SellingDemandDTOService sellingDemandDTOService = new SellingDemandDTOService();
    private HttpServletRequest request;
    private QuerySellingDemandDTO para;

    @GET
    @Path("/sellinglist")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SellingDemandDTO> list(@Context HttpServletRequest request) {
        String para = request.getParameter("para");
        QuerySellingDemandDTO paraM = (QuerySellingDemandDTO) JSON.parseObject(para, QuerySellingDemandDTO.class);
        List<SellingDemandDTO> list = sellingDemandDTOService.queryList(paraM);
        return list;
    }

}
