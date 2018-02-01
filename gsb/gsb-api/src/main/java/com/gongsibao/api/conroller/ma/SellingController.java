package com.gongsibao.api.conroller.ma;

import com.alibaba.fastjson.JSON;

import com.gongsibao.api.dto.ma.QuerySellingDemandDTO;
import com.gongsibao.api.dto.ma.SellingDemandDTO;
import com.gongsibao.api.service.ma.SellingDemandDTOService;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

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
    public List<SellingDemandDTO> getSellingList(@Context HttpServletRequest request) {
        String para = request.getParameter("para");
        QuerySellingDemandDTO paraM = (QuerySellingDemandDTO) JSON.parseObject(para, QuerySellingDemandDTO.class);
        List<SellingDemandDTO> list = sellingDemandDTOService.queryList(paraM);
        return list;
    }

    /*服务类型*/
    @GET
    @Path("/servicetype")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SellingDemandDTO>  getServiceType(@Context HttpServletRequest request) {
        String para = request.getParameter("para");
        QuerySellingDemandDTO paraM = (QuerySellingDemandDTO) JSON.parseObject(para, QuerySellingDemandDTO.class);
        List<SellingDemandDTO> list = sellingDemandDTOService.queryList(paraM);
        return list;
    }

    /*全国地区*/
    @GET
    @Path("/pcc")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SellingDemandDTO> getPcc(@Context HttpServletRequest request) {
        String para = request.getParameter("para");
        QuerySellingDemandDTO paraM = (QuerySellingDemandDTO) JSON.parseObject(para, QuerySellingDemandDTO.class);
        List<SellingDemandDTO> list = sellingDemandDTOService.queryList(paraM);
        return list;
    }

    /*筛选*/
    @GET
    @Path("/filters")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SellingDemandDTO> getFilters(@Context HttpServletRequest request) {
        String para = request.getParameter("para");
        QuerySellingDemandDTO paraM = (QuerySellingDemandDTO) JSON.parseObject(para, QuerySellingDemandDTO.class);
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
