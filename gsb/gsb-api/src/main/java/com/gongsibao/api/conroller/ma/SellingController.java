package com.gongsibao.api.conroller.ma;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.gongsibao.api.dto.ma.FilterTypeDTO;
import com.gongsibao.api.dto.ma.ProvinceCityCountyDTO;
import com.gongsibao.api.dto.ma.QuerySellingDemandDTO;
import com.gongsibao.api.dto.ma.SellingDemandDTO;
import com.gongsibao.api.dto.ma.ServiceTypeDTO;
import com.gongsibao.api.service.ma.SellingDemandDTOService;
import com.gongsibao.taurus.util.StringManager;


/**
 * Created by win on 2018/2/1.
 */
/*公司出售需求控制器只提供查询接口*/
@Path("/ma/selling")
//@AuthAnnotation
public class SellingController {
    SellingDemandDTOService sellingDemandDTOService = new SellingDemandDTOService();
    /*列表接口*/
    @GET
    @Path("/getsellinglist")
    @Produces(MediaType.APPLICATION_JSON)
    public List<SellingDemandDTO> getSellingList(@Context HttpServletRequest request) throws IOException {
        String para = request.getParameter("para");
        QuerySellingDemandDTO paraM=new QuerySellingDemandDTO();
        List<SellingDemandDTO> listResult=new ArrayList<>();
        if (!StringManager.isNullOrEmpty(para)){
             paraM = (QuerySellingDemandDTO) org.netsharp.util.JsonManage.deSerialize(QuerySellingDemandDTO.class, para);

            listResult= sellingDemandDTOService.queryList(paraM);
        }else{
            listResult= sellingDemandDTOService.queryList(null);

        }
        return listResult;
    }

    /*服务类型*/
    @GET
    @Path("/getservicetype")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ServiceTypeDTO> getServiceType(@Context HttpServletRequest request) throws IOException {
        List<ServiceTypeDTO> list = sellingDemandDTOService.getServiceTypeLsit();
        return list;
    }

    /*全国地区*/
    @GET
    @Path("/getprovincecitycounty")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProvinceCityCountyDTO> getProvinceCityCounty(@Context HttpServletRequest request) {

        Integer parentId = 0;
        String para = request.getParameter("para");
        if (!StringManager.isNullOrEmpty(para)) {
            parentId = Integer.parseInt(para);
        }
        List<ProvinceCityCountyDTO> list = sellingDemandDTOService.getPcc(parentId);
        return list;
    }

    /*筛选*/
    @GET
    @Path("/getfilters")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FilterTypeDTO> getFilters(@Context HttpServletRequest request) throws IOException {
        List<FilterTypeDTO> list = sellingDemandDTOService.getFilters();
        return list;
    }


    /*详情*/
    @GET
    @Path("/getdetails")
    @Produces(MediaType.APPLICATION_JSON)
    public SellingDemandDTO getDetails(@Context HttpServletRequest request) {
        String para = request.getParameter("para");
        Integer sellingId = 0;
        if (!StringManager.isNullOrEmpty(para)) {

            sellingId=Integer.parseInt(para);
        }
        SellingDemandDTO sellingDemandDTO = sellingDemandDTOService.getSellingDemandDTOById(sellingId);
        return sellingDemandDTO;
    }


}
