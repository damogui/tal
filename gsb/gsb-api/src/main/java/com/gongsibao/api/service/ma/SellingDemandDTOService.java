package com.gongsibao.api.service.ma;

import com.gongsibao.api.dto.ma.*;
import com.gongsibao.entity.ma.DemandFixedAssets;
import com.gongsibao.entity.ma.DemandIntangibleAssets;
import com.gongsibao.entity.ma.DemandQualificationDetail;
import com.gongsibao.entity.ma.SellingDemand;
import com.gongsibao.entity.ma.dic.CompanyNature;
import com.gongsibao.entity.ma.dic.CompanyType;
import com.gongsibao.entity.ma.dic.EnterpriseQualification;
import com.gongsibao.entity.ma.dic.IndustryFeature;
import com.gongsibao.ma.service.SellingDemandService;
import com.gongsibao.taurus.util.StringManager;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.core.QueryParameters;
import org.netsharp.pcc.entity.ProvinceCityCounty;
import org.netsharp.pcc.service.ProvinceCityCountyService;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 出售需求对外接口逻辑层
 */
public class SellingDemandDTOService {
    SellingDemandService sellingDemandService = new SellingDemandService();
    ProvinceCityCountyService provin = new ProvinceCityCountyService();//省市县

    /*根据传递条件进行查询数据返回*/
    public List<SellingDemandDTO> queryList(QuerySellingDemandDTO queryModel) {
        Oql oql = new Oql();
        StringBuilder filterBuilder = new StringBuilder();
        oql.setType(SellingDemand.class);


        StringBuilder selectStr = new StringBuilder();
        selectStr.append("SellingDemand.*,");
        selectStr.append("SellingDemand.province.{code,name},");
        selectStr.append("SellingDemand.city.{code,name},");
        selectStr.append("SellingDemand.county.{code,name}");
//        selectStr.append("SellingDemand.*,");
//        selectStr.append("SellingDemand.*,");


        oql.setSelects(selectStr.toString());

        QueryParameters qps = new QueryParameters();
        if (queryModel != null) {//进行组装筛选条件数据

            if (!StringManager.isNullOrEmpty(queryModel.getCompanyName())) {
                // String sqlFiter=String.format("company_name  like '%%s%' or ");
                filterBuilder.append("company_name  like '%" + queryModel.getCompanyName() + "%'");
            }
            if (queryModel.getComQualType() != 0) {//资质类型  qualificationDetails
                filterBuilder.append("qualificationDetails.enterpriseQualification=?");
                qps.add("@enterpriseQualification", queryModel.getComQualType(), Types.INTEGER);


            }
            if (queryModel.getProvinceId() != 0) {//地区省市县

                filterBuilder.append("province_id`=?");
                qps.add("@province_id`", queryModel.getProvinceId(), Types.INTEGER);
            }
            if (queryModel.getCityId() != 0) {

                filterBuilder.append("city_id=?");
                qps.add("@city_id", queryModel.getCityId(), Types.INTEGER);
            }
            if (queryModel.getCountyId() != 0) {

                filterBuilder.append("county_id=?");
                qps.add("@county_id", queryModel.getCountyId(), Types.INTEGER);
            }

            if (queryModel.getCompanyType() != 0) {//公司类型

                filterBuilder.append("company_type=?");
                qps.add("@company_type", queryModel.getCompanyType(), Types.INTEGER);
            }
            if (queryModel.getCompanyNature() != 0) {//公司性质

                filterBuilder.append("company_nature=?");
                qps.add("@company_nature", queryModel.getCompanyNature(), Types.INTEGER);
            }
            if (queryModel.getYearType() != 0) {//公司年限   需要考虑截止时间吗

                switch (queryModel.getYearType()) {
                    case 1:
                        filterBuilder.append("TIMESTAMPDIFF(YEAR,regist_date,NOW())<=1 ");
                        break;
                    case 2:
                        filterBuilder.append("1<TIMESTAMPDIFF(YEAR,regist_date,NOW())   AND  TIMESTAMPDIFF(YEAR,regist_date,NOW())<=2 ");
                        break;
                    case 3:
                        filterBuilder.append("2<TIMESTAMPDIFF(YEAR,regist_date,NOW())   AND  TIMESTAMPDIFF(YEAR,regist_date,NOW())<=3 ");
                        break;
                    case 4:
                        filterBuilder.append("3<TIMESTAMPDIFF(YEAR,regist_date,NOW())");
                        break;
                    default:
                        break;


                }


            }

            //默认页码和大小
            if (queryModel.getPageIndex() == 0) queryModel.setPageIndex(1);
            if (queryModel.getPageSize() == 0) queryModel.setPageSize(10);
            oql.setOrderby("update_time  desc");
            oql.setPaging(new Paging(queryModel.getPageIndex(), queryModel.getPageSize()));


        } else {
            oql.setOrderby("update_time  desc");
            oql.setPaging(new Paging(1, 10));
        }
        String fiterStr = filterBuilder.toString();
        if (!StringManager.isNullOrEmpty(fiterStr)) {
            oql.setFilter(fiterStr);
            oql.setParameters(qps);
        }

        List<SellingDemand> sellingList = sellingDemandService.queryList(oql);
        List<SellingDemandDTO> sellingDtoList = new ArrayList<SellingDemandDTO>();

        for (SellingDemand item : sellingList
                ) {

            SellingDemandDTO sellDto = new SellingDemandDTO();
            sellDto.setId(item.getId());
            sellDto.setCompanyName(item.getCompanyName());//
            sellDto.setCompanyNature(item.getCompanyNature());//公司性质
            if (item.getCompanyNature() != null) {
                sellDto.setCompanyNatureStr(item.getCompanyNature().getText());//公司性质
            }

            sellDto.setCompanyFeature(item.getCompanyFeature());//公司特点
            if (item.getCompanyFeature() != null) {
                sellDto.setCompanyFeatureStr(item.getCompanyFeature().getText());//公司特点
            }

            sellDto.setProvince(item.getProvince());
            if (item.getProvince() != null) {
                sellDto.setProvinceStr(item.getProvince().getName());//省份名称

            }
            sellDto.setCity(item.getCity());
            if (item.getCity() != null) {
                sellDto.setCityStr(item.getCity().getName());

            }

            sellDto.setCounty(item.getCounty());
            sellDto.setRegistDate(item.getRegistDate());

            if (item.getRegistDate()!=null){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                sellDto.setRegistDateStr(sdf.format(item.getRegistDate()));
            }
            sellDto.setSelingStatus(item.getSelingStatus());//是否出售
//            sellDto.setCode(item.getCode());
//            sellDto.setCompanyType(item.getCompanyType());
//            sellDto.setTaxMode(item.getTaxMode());
//            sellDto.setAddressMode(item.getAddressMode());
//            sellDto.setRegistDateEnd(item.getRegistDateEnd());
//            sellDto.setHasBankAccount(item.getHasBankAccount());
//            sellDto.setTaxRegister(item.getTaxRegister());
//            sellDto.setQualificationDetails(item.getQualificationDetails());
//            sellDto.setIntangibleAssetss(item.getIntangibleAssetss());
//            sellDto.setFixedAssetss(item.getFixedAssetss());
//            sellDto.setLicenseAdvantage(item.getLicenseAdvantage());

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
        sellingDemandDTO.setSelingStatus(selling.getSelingStatus());
        if (selling.getSelingStatus()!= null) {//是否出售
            sellingDemandDTO.setSelingStatusStr(selling.getSelingStatus().getText());

        }

        sellingDemandDTO.setCompanyType(selling.getCompanyType());
        if (selling.getCompanyType()!= null) {//公司类型
            sellingDemandDTO.setCompanyTypeStr(selling.getCompanyType().getText());

        }


        sellingDemandDTO.setCompanyNature(selling.getCompanyNature());

        if (selling.getCompanyNature()!= null) {//公司性质
            sellingDemandDTO.setCompanyNatureStr(selling.getCompanyNature().getText());

        }
        sellingDemandDTO.setCompanyFeature(selling.getCompanyFeature());
        if (selling.getCompanyFeature()!= null) {//行业特点
            sellingDemandDTO.setCompanyFeatureStr(selling.getCompanyFeature().getText());

        }
        sellingDemandDTO.setTaxMode(selling.getTaxMode());
        if (selling.getTaxMode()!= null) {//纳税人
            sellingDemandDTO.setTaxModeStr(selling.getTaxMode().getText());

        }
        sellingDemandDTO.setRegistDate(selling.getRegistDate());
        if (selling.getRegistDate()!=null){//成立时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            sellingDemandDTO.setRegistDateStr(sdf.format(selling.getRegistDate()));
        }
        sellingDemandDTO.setProvince(selling.getProvince());
        if (selling.getProvince()!= null) {//省市
            sellingDemandDTO.setProvinceStr(selling.getProvince().getName());

        }
        sellingDemandDTO.setCity(selling.getCity());
        if (selling.getCity()!= null) {//省市
            sellingDemandDTO.setCityStr(selling.getCity().getName());

        }
        sellingDemandDTO.setAddressStr(sellingDemandDTO.getProvinceStr()+sellingDemandDTO.getCityStr());//地址
        sellingDemandDTO.setHasBankAccount(selling.getHasBankAccount());//是否存在银行账户
        sellingDemandDTO.setTaxRegister(selling.getTaxRegister());//国地税
       //sellingDemandDTO.setIntangibleAssetss(selling.getIntangibleAssetss());//无形资产
        List<DemandIntangibleAssets> listDemandIntangibleAssets=selling.getIntangibleAssetss();
        List<String>listDemandIntangibleAssetsStr=new ArrayList<>();
        for (DemandIntangibleAssets item:
        listDemandIntangibleAssets) {
            listDemandIntangibleAssetsStr.add(item.getIntangibleAssets().getText());
        }
        sellingDemandDTO.setIntangibleAssetssList(listDemandIntangibleAssetsStr);//无形资产
        //sellingDemandDTO.setFixedAssetss(selling.getFixedAssetss());//固定资产
        List<DemandFixedAssets> listDemandFixedAssets=selling.getFixedAssetss();
        List<String>listDemandFixedAssetsStr=new ArrayList<>();
        for (DemandFixedAssets item:
                listDemandFixedAssets) {
            listDemandFixedAssetsStr.add(item.getFixedAssets().getText());
        }
        sellingDemandDTO.setFixedAssetssList(listDemandFixedAssetsStr);//固定资产
        //sellingDemandDTO.setQualificationDetails(selling.getQualificationDetails());//企业资质

        List<DemandQualificationDetail> listDemandQualificationDetail=selling.getQualificationDetails();
        List<String>listDemandQualificationDetailStr=new ArrayList<>();
        for (DemandQualificationDetail item:
                listDemandQualificationDetail) {
            listDemandQualificationDetailStr.add(item.getEnterpriseQualification().getText());
        }
        sellingDemandDTO.setQualificationDetailsList(listDemandQualificationDetailStr);//企业资质

        sellingDemandDTO.setLicenseAdvantage(selling.getLicenseAdvantage());//执照优势

        return sellingDemandDTO;
    }

    /*获取省市县代码和名称*/
    public List<ProvinceCityCountyDTO> getPcc(int parentId) {
        List<ProvinceCityCounty> listP = new ArrayList<>();
        List<ProvinceCityCountyDTO> listPDTO = new ArrayList<ProvinceCityCountyDTO>();

        if (parentId == 0) {
            listP = provin.queryProvince();
        } else {
            listP = provin.queryPcc(parentId);
        }

        for (ProvinceCityCounty item : listP
                ) {
            ProvinceCityCountyDTO proDTO = new ProvinceCityCountyDTO();
            proDTO.setName(item.getName());
            proDTO.setId(item.getId());
            listPDTO.add(proDTO);
        }

        return listPDTO;

    }

    /*获取服务类型的枚举类型转换集合*/
    public List<ServiceTypeDTO> getServiceTypeLsit() {
        List<ServiceTypeDTO> listServiceType = new ArrayList<>();

        ServiceTypeDTO serviceTypeDTO = new ServiceTypeDTO();
        serviceTypeDTO.setServiceName(ServiceTypeEnum.p5.getText());
        serviceTypeDTO.setServiceType(ServiceTypeEnum.p5.getValue());
        ArrayList<ServiceDic> dicList = new ArrayList();
          /*循环公司性质枚举返回*/
        for (EnterpriseQualification item : EnterpriseQualification.values()
                ) {
            ServiceDic serviceDic = new ServiceDic();
            serviceDic.setId(item.getValue());
            serviceDic.setName(item.getText());
            dicList.add(serviceDic);

        }
        serviceTypeDTO.setServiceList(dicList);
        listServiceType.add(serviceTypeDTO);
        return listServiceType;


    }

    /*筛选条件集合*/
    public List<FilterTypeDTO> getFilters() {
        List<FilterTypeDTO> listFilter = new ArrayList<>();

        FilterTypeDTO filterType = new FilterTypeDTO();
        filterType.setFilterType(ServiceTypeEnum.p7.getValue());//公司类型
        filterType.setFilterName(ServiceTypeEnum.p7.getText());
        ArrayList<ServiceDic> typeList = new ArrayList<>();
        for (CompanyType item : CompanyType.values()
                ) {
            ServiceDic servDic = new ServiceDic(item.getValue(), item.getText());
            typeList.add(servDic);
        }
        filterType.setFilterList(typeList);
        listFilter.add(filterType);

        FilterTypeDTO filterNature = new FilterTypeDTO();
        filterNature.setFilterType(ServiceTypeEnum.p8.getValue());//公司性质
        filterNature.setFilterName(ServiceTypeEnum.p8.getText());
        ArrayList<ServiceDic> natureList = new ArrayList<>();
        for (CompanyNature item : CompanyNature.values()
                ) {
            ServiceDic servDic = new ServiceDic(item.getValue(), item.getText());
            natureList.add(servDic);
        }
        filterNature.setFilterList(natureList);
        listFilter.add(filterNature);


        FilterTypeDTO filterDate = new FilterTypeDTO();
        filterDate.setFilterType(ServiceTypeEnum.p9.getValue());//成立年限
        filterDate.setFilterName(ServiceTypeEnum.p9.getText());
        ArrayList<ServiceDic> yearList = new ArrayList<>();
        yearList.add(new ServiceDic(1, "1年以内"));
        yearList.add(new ServiceDic(2, "1-2年"));
        yearList.add(new ServiceDic(3, "2-3年"));
        yearList.add(new ServiceDic(4, "3年以上"));
        filterDate.setFilterList(yearList);
        listFilter.add(filterDate);
        return listFilter;
    }
}
