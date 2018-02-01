package com.gongsibao.api.service.ma;

import com.gongsibao.api.dto.ma.QuerySellingDemandDTO;
import com.gongsibao.api.dto.ma.SellingDemandDTO;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.ma.service.SellingDemandService;
import org.netsharp.core.Oql;

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
        Oql oql=new Oql();
        oql.setType(SellingDemand.class);
        oql.setSelects("SellingDemand.*");
        if (queryModel!=null){

            if (queryModel.getCompanyName()!=""&&queryModel.getCompanyName()!=null){

                oql.setFilter("company_name  like '%"+queryModel.getCompanyName()+"%'");


            }



        }else {

            oql = new Oql();
            {

                oql.setFilter("id<?");
                oql.getParameters().add("@id", 9, Types.INTEGER);
            }


        }



        List<SellingDemand> sellingList = sellingDemandService.queryList(oql);


        List<SellingDemandDTO> sellingDtoList = new ArrayList<SellingDemandDTO>();

        for (SellingDemand item : sellingList
                ) {

            SellingDemandDTO sellDto = new SellingDemandDTO();
            sellDto.setCode(item.getCode());
            sellDto.setCompanyName(item.getCompanyName());
            sellDto.setCompanyType(item.getCompanyType());
            sellDto.setCompanyNature(item.getCompanyNature());
            sellDto.setCompanyFeature(item.getCompanyFeature());
            sellDto.setTaxMode(item.getTaxMode());
            sellDto.setAddressMode(item.getAddressMode());
            sellDto.setRegistDate(item.getRegistDate());
            sellDto.setRegistDateEnd(item.getRegistDateEnd());
            sellDto.setProvince(item.getProvince());
            sellDto.setCity(item.getCity());
            sellDto.setCounty(item.getCounty());
            sellDto.setHasBankAccount(item.getHasBankAccount());
            sellDto.setTaxRegister(item.getTaxRegister());
            sellDto.setQualificationDetails(item.getQualificationDetails());
            sellDto.setIntangibleAssetss(item.getIntangibleAssetss());
            sellDto.setFixedAssetss(item.getFixedAssetss());
            sellDto.setLicenseAdvantage(item.getLicenseAdvantage());
            sellDto.setSelingStatus(item.getSelingStatus());
            sellingDtoList.add(sellDto);


        }

        return sellingDtoList;


    }


}
