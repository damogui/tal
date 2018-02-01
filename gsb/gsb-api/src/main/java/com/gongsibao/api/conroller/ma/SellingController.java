package com.gongsibao.api.conroller.ma;


import com.gongsibao.api.dto.ma.ProvinceCityCountyDTO;
import com.gongsibao.api.dto.ma.QuerySellingDemandDTO;
import com.gongsibao.api.dto.ma.SellingDemandDTO;
import com.gongsibao.api.service.ma.SellingDemandDTOService;
import com.gongsibao.taurus.util.StringManager;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.util.List;


/**
 * Created by win on 2018/2/1.
 */

@Path("/selling")
public class SellingController {

    //ISellingDemandService sellingDemandServiceI = ServiceFactory.create(ISellingDemandService.class);
    SellingDemandDTOService sellingDemandDTOService = new SellingDemandDTOService();
    private HttpServletRequest request;
    private QuerySellingDemandDTO para;


  /*列表接口*/
    @GET
    @Path("/sellinglist")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SellingDemandDTO> getSellingList(@Context HttpServletRequest request) throws IOException {
        String para = request.getParameter("para");
        QuerySellingDemandDTO paraM= (QuerySellingDemandDTO) org.netsharp.util.JsonManage.deSerialize(QuerySellingDemandDTO.class,para);
        //QuerySellingDemandDTO paraM = (QuerySellingDemandDTO) JSON.parseObject(para, QuerySellingDemandDTO.class);

        List<SellingDemandDTO> list = sellingDemandDTOService.queryList(paraM);
        return list;
    }

    /*服务类型*/
    @GET
    @Path("/servicetype")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SellingDemandDTO>  getServiceType(@Context HttpServletRequest request) throws IOException {
        String para = request.getParameter("para");
        QuerySellingDemandDTO paraM= (QuerySellingDemandDTO) org.netsharp.util.JsonManage.deSerialize(QuerySellingDemandDTO.class,para);
        List<SellingDemandDTO> list = sellingDemandDTOService.queryList(paraM);
        return list;
    }

    /*全国地区*/
    @GET
    @Path("/pcc")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProvinceCityCountyDTO> getPcc(@Context HttpServletRequest request){

        Integer parentId = 0;
        String para = request.getParameter("para");
        if (!StringManager.isNullOrEmpty(para)){

            parentId=Integer.parseInt(para);
        }
        List<ProvinceCityCountyDTO> list = sellingDemandDTOService.getPcc(parentId);
        return list;
    }

    /*筛选*/
    @GET
    @Path("/filters")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SellingDemandDTO> getFilters(@Context HttpServletRequest request) throws IOException {
        String para = request.getParameter("para");
        QuerySellingDemandDTO paraM= (QuerySellingDemandDTO) org.netsharp.util.JsonManage.deSerialize(QuerySellingDemandDTO.class,para);
        List<SellingDemandDTO> list = sellingDemandDTOService.queryList(paraM);
        return list;
    }


    /*详情*/
    @GET
    @Path("/details/{sellingId}")
    @Produces(MediaType.APPLICATION_JSON)
    public SellingDemandDTO getDetails(@PathParam("sellingId") int sellingId) {
        SellingDemandDTO sellingDemandDTO = sellingDemandDTOService.getSellingDemandDTOById(sellingId);
        return sellingDemandDTO;
    }



}
