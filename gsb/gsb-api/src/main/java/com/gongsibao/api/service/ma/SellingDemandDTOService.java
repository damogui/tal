package com.gongsibao.api.service.ma;

import com.gongsibao.api.dto.ma.QuerySellingDemandDTO;
import com.gongsibao.api.dto.ma.SellingDemandDTO;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.service.SellingDemandService;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * 出售需求对外接口逻辑层
 */
public class SellingDemandDTOService {
    SellingDemandService sellingDemandService = new SellingDemandService();

    /*根据传递条件进行查询数据返回*/
    public List<SellingDemandDTO> queryList(QuerySellingDemandDTO queryModel) {
        Oql oql = new Oql();
        oql.setType(SellingDemand.class);
        oql.setSelects("SellingDemand.*");
        if (queryModel != null) {

            if (!queryModel.getCompanyName().equals("") && queryModel.getCompanyName() != null) {


                // String sqlFiter=String.format("company_name  like '%%s%' or ");
                oql.setFilter("company_name  like '%" + queryModel.getCompanyName() + "%'");


            }


        } else {
            Paging paging = new Paging();
            {
                paging.setPageNo(1);
                paging.setPageSize(10);
            }
            oql.setOrderby("update_time  desc");
            oql.setPaging(paging);


        }


        List<SellingDemand> sellingList = sellingDemandService.queryList(oql);


        List<SellingDemandDTO> sellingDtoList = new ArrayList<SellingDemandDTO>();

        for (SellingDemand item : sellingList
                ) {

            SellingDemandDTO sellDto = new SellingDemandDTO();
            sellDto.setId(item.getId());
//            sellDto.setCode(item.getCode());
            sellDto.setCompanyName(item.getCompanyName());//
//            sellDto.setCompanyType(item.getCompanyType());
            sellDto.setCompanyNature(item.getCompanyNature());//公司性质
            sellDto.setCompanyFeature(item.getCompanyFeature());//公司特点
//            sellDto.setTaxMode(item.getTaxMode());
//            sellDto.setAddressMode(item.getAddressMode());
            sellDto.setRegistDate(item.getRegistDate());
//            sellDto.setRegistDateEnd(item.getRegistDateEnd());
            sellDto.setProvince(item.getProvince());
            sellDto.setCity(item.getCity());
            sellDto.setCounty(item.getCounty());
//            sellDto.setHasBankAccount(item.getHasBankAccount());
//            sellDto.setTaxRegister(item.getTaxRegister());
//            sellDto.setQualificationDetails(item.getQualificationDetails());
//            sellDto.setIntangibleAssetss(item.getIntangibleAssetss());
//            sellDto.setFixedAssetss(item.getFixedAssetss());
//            sellDto.setLicenseAdvantage(item.getLicenseAdvantage());
            sellDto.setSelingStatus(item.getSelingStatus());//是否出售
            sellingDtoList.add(sellDto);


        }

        return sellingDtoList;


    }

    /*根据主键获取详情*/
    public SellingDemandDTO getSellingDemandDTOById(int sellingId) {

        SellingDemand selling = sellingDemandService.byId(sellingId);//详情信息转换DTO
        SellingDemandDTO sellingDemandDTO = new SellingDemandDTO();
        sellingDemandDTO.setId(selling.getId());
        sellingDemandDTO.setCompanyName(selling.getCompanyName());
        sellingDemandDTO.setCompanyType(selling.getCompanyType());
        sellingDemandDTO.setCompanyNature(selling.getCompanyNature());
        sellingDemandDTO.setCompanyFeature(selling.getCompanyFeature());
        sellingDemandDTO.setTaxMode(selling.getTaxMode());
        sellingDemandDTO.setRegistDate(selling.getRegistDate());
        sellingDemandDTO.setProvince(selling.getProvince());
        sellingDemandDTO.setHasBankAccount(selling.getHasBankAccount());
        sellingDemandDTO.setTaxRegister(selling.getTaxRegister());
        sellingDemandDTO.setIntangibleAssetss(selling.getIntangibleAssetss());
        sellingDemandDTO.setFixedAssetss(selling.getFixedAssetss());
        sellingDemandDTO.setQualificationDetails(selling.getQualificationDetails());
        sellingDemandDTO.setLicenseAdvantage(selling.getLicenseAdvantage());
        sellingDemandDTO.setSelingStatus(selling.getSelingStatus());
        return sellingDemandDTO;
    }
}
