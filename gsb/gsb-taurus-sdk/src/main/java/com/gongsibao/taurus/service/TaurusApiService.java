package com.gongsibao.taurus.service;

import com.gongsibao.taurus.api.*;
import com.gongsibao.taurus.entity.*;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.message.response.CompanyInfoResponseMessage;
import com.gongsibao.taurus.message.response.TmNewResponseMessage;
import com.gongsibao.taurus.util.StringManager;

import java.util.List;

public class TaurusApiService {

    /**
     * @throws
     * @Title: getEntRegistry
     * @Description: 获取企业注册信息
     * @param: @param companyName
     * @param: @return
     * @return: List<DishonestInfo>
     */
    public static EntRegistry getEntRegistry(String companyName) {

//        EntRegistryApi api = ApiFactory.create(EntRegistryApi.class);
//        api.setCompanyName(companyName);
//        ResponseMessage<EntRegistry> response = api.getResponse();
//        if (response == null) {
//
//            return null;
//        }
//
//        if (response.getList().size() == 0) {
//
//            return null;
//        }
//
//        return response.getList().get(0);
        CompanyInfoApi api = ApiFactory.create(CompanyInfoApi.class);
        api.setCompanyName(companyName);
        CompanyInfoResponseMessage response = api.getResponse();
        if (null == response || null == response.getData()) {
            EntRegistryApi entApi = ApiFactory.create(EntRegistryApi.class);
            api.setCompanyName(companyName);
            ResponseMessage<EntRegistry> entResponse = entApi.getResponse();
            if (response == null) {

                return null;
            }

            if (response.getList().size() == 0) {

                return null;
            }

            return entResponse.getList().get(0);
        }

        CompanyInfo company = response.getData();
        EntRegistry entRegistry = new EntRegistry();

        // 营业时间
        String fromTime = StringManager.isNullOrEmpty(company.getFromTime()) ? "-" : company.getFromTime().replace("00:00:00", "").trim();
        String toTime = StringManager.isNullOrEmpty(company.getToTime()) ? "-" : company.getToTime().replace("00:00:00", "").trim();

        entRegistry.setName(company.getName());
        entRegistry.setCreditCode(company.getProperty1());
        entRegistry.setRegistrID(company.getRegNumber());
        entRegistry.setCompanyType(company.getCompanyOrgType());
        entRegistry.setLegalRepresentative(company.getLegalPersonName());
        entRegistry.setRegisteredCapital(company.getRegCapital());
        entRegistry.setRegisterOffice(company.getRegInstitute());
        entRegistry.setBusinessAddress(company.getRegLocation());
        entRegistry.setScope(company.getBusinessScope());
        entRegistry.setOrganizationCode(company.getOrgNumber());
        entRegistry.setManagementState(company.getRegStatus());
        entRegistry.setFoundation(company.getEstiblishTime());

        entRegistry.setDateIssue(fromTime);
        entRegistry.setBusinessTerm(fromTime + "至" + toTime);
        return entRegistry;
    }

    /**
     * @throws
     * @Title: getAnnualReport
     * @Description: 获取年报汇总
     * @param: @param companyName
     * @param: @return
     * @return: List<AnnualReport>
     */
    public static ResponseMessage<AnnualReport> getAnnualReportList(String companyName, int currentPage, int pageSize) {

        AnnualReportApi api = ApiFactory.create(AnnualReportApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<AnnualReport> response = api.getResponse();
        return response;
    }

    /**
     * @throws
     * @Title: getCopyright
     * @Description: 获取软件著作权
     * @param: @param companyName
     * @param: @return
     * @return: List<Copyright>
     */
    public static ResponseMessage<Copyright> getCopyrightList(String companyName, int currentPage, int pageSize) {

        CopyrightApi api = ApiFactory.create(CopyrightApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<Copyright> response = api.getResponse();
        return response;
    }

    /**
     * @throws
     * @Title: getCourtExecutive
     * @Description: 获取被执行人信息
     * @param: @param companyName
     * @param: @return
     * @return: List<CourtExecutive>
     */
    public static ResponseMessage<CourtExecutive> getCourtExecutiveList(String companyName, int currentPage, int pageSize) {

        CourtExecutiveApi api = ApiFactory.create(CourtExecutiveApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<CourtExecutive> response = api.getResponse();
        return response;
    }

    /**
     * @throws
     * @Title: getCourtExecutive
     * @Description: 获取失信人信息
     * @param: @param companyName
     * @param: @return
     * @return: List<DishonestInfo>
     */
    public static ResponseMessage<DishonestInfo> getDishonestInfoList(String companyName, int currentPage, int pageSize) {

        DishonestInfoApi api = ApiFactory.create(DishonestInfoApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<DishonestInfo> response = api.getResponse();
        return response;
    }

    /**
     * @throws
     * @Title: getEntBranch
     * @Description: 获取分支机构
     * @param: @param companyName
     * @param: @return
     * @return: List<DishonestInfo>
     */
    public static ResponseMessage<EntBranch> getEntBranchList(String companyName, int currentPage, int pageSize) {

        EntBranchApi api = ApiFactory.create(EntBranchApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<EntBranch> response = api.getResponse();
        return response;
    }

    /**
     * @throws
     * @Title: getEntChangeRecord
     * @Description: 获取企业变更记录
     * @param: @param companyName
     * @param: @return
     * @return: List<DishonestInfo>
     */
    public static ResponseMessage<EntChangeRecord> getEntChangeRecordList(String companyName, int currentPage, int pageSize) {

        EntChangeRecordApi api = ApiFactory.create(EntChangeRecordApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<EntChangeRecord> response = api.getResponse();
        return response;
    }

    /**
     * @throws
     * @Title: getEntInvest
     * @Description: 获取对外投资
     * @param: @param companyName
     * @param: @return
     * @return: List<DishonestInfo>
     */
    public static ResponseMessage<EntInvest> getEntInvestList(String companyName, int currentPage, int pageSize) {

        EntInvestApi api = ApiFactory.create(EntInvestApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<EntInvest> response = api.getResponse();
        return response;
    }

    /**
     * @throws
     * @Title: getEntMember
     * @Description: 获取主要人员信息
     * @param: @param companyName
     * @param: @return
     * @return: List<DishonestInfo>
     */
    public static ResponseMessage<EntMember> getEntMemberList(String companyName, int currentPage, int pageSize) {

        EntMemberApi api = ApiFactory.create(EntMemberApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<EntMember> response = api.getResponse();
        return response;
    }

    /**
     * @throws
     * @Title: getEntShareholder
     * @Description: 获取股东信息信息
     * @param: @param companyName
     * @param: @return
     * @return: List<DishonestInfo>
     */
    public static ResponseMessage<EntShareholder> getEntShareholderList(String companyName, int currentPage, int pageSize) {

        EntShareholderApi api = ApiFactory.create(EntShareholderApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<EntShareholder> response = api.getResponse();
        return response;
    }

    /**
     * @throws
     * @Title: getEntRegistry
     * @Description: 获取ICP 信息
     * @param: @param companyName
     * @param: @return
     * @return: List<DishonestInfo>
     */
    public static ResponseMessage<IcpInfo> getIcpInfoList(String companyName, int currentPage, int pageSize) {

        IcpInfoApi api = ApiFactory.create(IcpInfoApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<IcpInfo> response = api.getResponse();
        return response;
    }

    /**
     * @throws
     * @Title: getJudgment
     * @Description: 获取法院判决信息
     * @param: @param companyName
     * @param: @return
     * @return: List<DishonestInfo>
     */
    public static ResponseMessage<Judgment> getJudgmentList(String companyName, int currentPage, int pageSize) {

        JudgmentApi api = ApiFactory.create(JudgmentApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<Judgment> response = api.getResponse();
        return response;
    }

    /**
     * @throws
     * @Title: getPatents
     * @Description: 获取专利信息
     * @param: @param companyName
     * @param: @return
     * @return: List<DishonestInfo>
     */
    public static ResponseMessage<Patents> getPatentsList(String companyName, int currentPage, int pageSize) {

        PatentsApi api = ApiFactory.create(PatentsApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<Patents> response = api.getResponse();
        return response;
    }

    /**
     * @throws
     * @Title: getTm
     * @Description: 获取商标
     * @param: @param companyName
     * @param: @return
     * @return: List<DishonestInfo>
     */
    public static ResponseMessage<Tm> getTmList(String companyName, int currentPage, int pageSize) {

        TmApi api = ApiFactory.create(TmApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<Tm> response = api.getResponse();
        return response;
    }


    /**
     * @throws
     * @Title: getTm
     * @Description: 通过名称获取商标列表
     * @param: @param tmName
     * @param: @return
     * @return: List<DishonestInfo>
     */
    public static ResponseMessage<Tm> getTmByName(String tmName, int currentPage, int pageSize) {
        TmByNameApi api = ApiFactory.create(TmByNameApi.class);
        api.setTmName(tmName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<Tm> response = api.getResponse();
        return response;
    }

    /**
     * @throws
     * @Title: getWorksCopyright
     * @Description: 获取作品著作权
     * @param: @param companyName
     * @param: @return
     * @return: List<DishonestInfo>
     */
    public static ResponseMessage<WorksCopyright> getWorksCopyrightList(String companyName, int currentPage, int pageSize) {

        WorksCopyrightApi api = ApiFactory.create(WorksCopyrightApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<WorksCopyright> response = api.getResponse();
        return response;
    }

    /**
     * @throws
     * @Title: getPatentDesc
     * @Description: 获取专利详情
     * @param: @param companyName
     * @param: @return
     * @return: List<DishonestInfo>
     */
    public static PatentDesc getPatentDesc(String patentId) {

        PatentDescApi api = ApiFactory.create(PatentDescApi.class);
        api.setPatentId(patentId);
        ResponseMessage<PatentDesc> response = api.getResponse();
        if (response == null) {

            return null;
        }

        List<PatentDesc> list = response.getList();
        if (list.size() == 0) {

            return null;
        }
        return list.get(0);
    }

    /**
     * @throws
     * @Title: getTmdesc
     * @Description: 获取商标详情
     * @param: @param companyName
     * @param: @return
     * @return: List<DishonestInfo>
     */
    public static Tmdesc getTmdesc(int tmId) {

        TmdescApi api = ApiFactory.create(TmdescApi.class);
        api.setTmId(tmId);
        ResponseMessage<Tmdesc> response = api.getResponse();
        if (response == null) {

            return null;
        }

        List<Tmdesc> list = response.getList();
        if (list.size() == 0) {

            return null;
        }
        return list.get(0);
    }

    /**
     * @throws
     * @Title: getReportWebInfo
     * @Description: 获取商标流程
     * @param: @param companyName
     * @param: @return
     * @return: List<DishonestInfo>
     */
    public static ResponseMessage<Tmflow> getTmflowList(int tmId, int currentPage, int pageSize) {

        TmflowApi api = ApiFactory.create(TmflowApi.class);
        api.setTmId(tmId);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<Tmflow> response = api.getResponse();
        if (response == null) {

            return null;
        }
        return response;
    }

    /**
     * @throws
     * @Title: getReportShareholderList
     * @Description: TODO(获取年报股东出资信息)
     * @param: @param annualReportId
     * @param: @param currentPage
     * @param: @param pageSize
     * @param: @return
     * @return: ResponseMessage<ReportShareholder>
     */
    public static ResponseMessage<ReportShareholder> getReportShareholderList(int annualReportId, int currentPage, int pageSize) {

        ReportShareholderApi api = ApiFactory.create(ReportShareholderApi.class);
        api.setAnnualReportId(annualReportId);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<ReportShareholder> response = api.getResponse();
        if (response == null) {

            return null;
        }
        return response;
    }

    /**
     * @throws
     * @Title: getReportOutboundInvestmentList
     * @Description: TODO(获取年报对外投资)
     * @param: @param annualReportId
     * @param: @param currentPage
     * @param: @param pageSize
     * @param: @return
     * @return: ResponseMessage<ReportOutboundInvestment>
     */
    public static ResponseMessage<ReportOutboundInvestment> getReportOutboundInvestmentList(int annualReportId, int currentPage, int pageSize) {

        ReportOutboundInvestmentApi api = ApiFactory.create(ReportOutboundInvestmentApi.class);
        api.setAnnualReportId(annualReportId);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<ReportOutboundInvestment> response = api.getResponse();
        if (response == null) {

            return null;
        }
        return response;
    }

    /**
     * @throws
     * @Title: getReportWebInfoList
     * @Description: TODO(获取年报网站信息)
     * @param: @param annualReportId
     * @param: @param currentPage
     * @param: @param pageSize
     * @param: @return
     * @return: ResponseMessage<ReportWebInfo>
     */
    public static ResponseMessage<ReportWebInfo> getReportWebInfoList(int annualReportId, int currentPage, int pageSize) {

        ReportWebInfoApi api = ApiFactory.create(ReportWebInfoApi.class);
        api.setAnnualReportId(annualReportId);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<ReportWebInfo> response = api.getResponse();
        if (response == null) {

            return null;
        }
        return response;
    }

    /**
     * @throws
     * @Title: getEntList
     * @Description: TODO(模糊查询公司信息)
     * @param: @param companyName
     * @param: @param currentPage
     * @param: @param pageSize
     * @param: @return
     * @return: ResponseMessage<Company>
     */
    public static ResponseMessage<Company> getEntList(String companyName, int currentPage, int pageSize) {

        EntSearchApi api = ApiFactory.create(EntSearchApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<Company> response = api.getResponse();
        if (response == null) {

            return null;
        }
        return response;
    }

    /**
     * 获取公司详情
     *
     * @param companyName
     * @return
     */
    public static ResponseMessage<CompanyInfo> getCompanyInfo(String companyName) {
        CompanyInfoApi api = ApiFactory.create(CompanyInfoApi.class);
        api.setCompanyName(companyName);
        return api.getResponse();
    }

    /**
     * 根据关键字模糊查询公司列表
     *
     * @param key
     * @param currentPage
     * @param pageSize
     * @return
     */
    public static ResponseMessage<CompanyInfo> getCompanyListByKey(String key, int currentPage, int pageSize) {
        CompanyInfoListByKeyApi api = ApiFactory.create(CompanyInfoListByKeyApi.class);
        api.setKey(key);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        return api.getResponse();
    }

    /**
     * 根据关键字模糊查询公司名字列表
     *
     * @param key
     * @param currentPage
     * @param pageSize
     * @return
     */
    public static ResponseMessage<CompanyNameByKey> getCompanyNameByKey(String key, int currentPage, int pageSize) {
        CompanyNameByKeyApi api = ApiFactory.create(CompanyNameByKeyApi.class);
        api.setKey(key);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        return api.getResponse();
    }

    /**
     * 根据公司名称查询商标列表
     *
     * @param companyName
     * @param currentPage
     * @param pageSize
     * @return
     */
    public static ResponseMessage<TmInfo> getCompanyTmList(String companyName, int currentPage, int pageSize) {
        TmInfoListApi api = ApiFactory.create(TmInfoListApi.class);
        api.setName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        return api.getResponse();
    }

    /**
     * 根据公司名称查询商标变更列表
     *
     * @param companyName
     * @param currentPage
     * @param pageSize
     * @return
     */
    public static ResponseMessage<TmInfo> getTmChangeList(String companyName, int currentPage, int pageSize) {
        TmChangeListApi api = ApiFactory.create(TmChangeListApi.class);
        api.setName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        return api.getResponse();
    }

    /**
     * 根据公司名称查询商标续展列表
     *
     * @param companyName
     * @param currentPage
     * @param pageSize
     * @return
     */
    public static ResponseMessage<TmInfo> getTmRenewalList(String companyName, String endDate, int currentPage, int pageSize) {
        TmRenewalListApi api = ApiFactory.create(TmRenewalListApi.class);
        api.setName(companyName);
        api.setEndDate(endDate);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        return api.getResponse();
    }

    /**
     * 查询公司已有商标信息
     *
     * @param companyName
     * @param currentPage
     * @param pageSize
     * @return
     */
    public static ResponseMessage<TmAssemble> getRegTmAssembleList(String companyName, int currentPage, int pageSize) {
        TmRegAssembleListApi api = ApiFactory.create(TmRegAssembleListApi.class);
        api.setName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        return api.getResponse();
    }

    /**
     * 查询公司商标信息
     *
     * @param companyName
     * @param currentPage
     * @param pageSize
     * @return
     */
    public static ResponseMessage<TmAssemble> getTmAssembleList(String companyName, int currentPage, int pageSize) {
        TmAssembleApi api = ApiFactory.create(TmAssembleApi.class);
        api.setName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        return api.getResponse();
    }

    /**
     * 查询单个公司业务项
     *
     * @param companyName
     * @return
     */
    public static ResponseMessage<CompanyBusinessCount> getCompanyBusinessCount(String companyName) {
        CompanyBusinessCountApi api = ApiFactory.create(CompanyBusinessCountApi.class);
        api.setName(companyName);
        return api.getResponse();
    }

    public static ResponseMessage<TmInfo> getTmExceptionList(String companyName, int currentPage, int pageSize) {
        TmExceptionListApi api = ApiFactory.create(TmExceptionListApi.class);
        api.setName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        return api.getResponse();
    }


    /**
     * 查询公司潜在机会数量查询
     *
     * @param companyName
     * @return
     */
    public static ResponseMessage<CompanyPotentialCount> getCompanyPotentialCount(String companyName) {
        CompanyPotentialCountApi api = ApiFactory.create(CompanyPotentialCountApi.class);
        api.setName(companyName);
        return api.getResponse();
    }

    /**
     * 查询单个公司商标覆盖分类数量
     *
     * @param companyName
     * @return
     */
    public static ResponseMessage<Count> getTmCategoryCount(String companyName) {
        TmCategoryCountApi api = ApiFactory.create(TmCategoryCountApi.class);
        api.setName(companyName);
        return api.getResponse();
    }

    /**
     * 查询单个公司商标机会数量查询
     *
     * @param companyName
     * @return
     */
    public static ResponseMessage<PatentCount> getPatentCount(String companyName) {
        PatentCountApi api = ApiFactory.create(PatentCountApi.class);
        api.setName(companyName);
        return api.getResponse();
    }

    /**
     * 查询单个公司的著作权机会数量
     *
     * @param companyName
     * @return
     */
    public static ResponseMessage<CopyrightCount> getCopyrightCount(String companyName) {
        CopyrightCountApi api = ApiFactory.create(CopyrightCountApi.class);
        api.setName(companyName);
        return api.getResponse();
    }


    /**
     * 查询单个公司年报数量
     *
     * @param companyName
     * @return
     */
    public static ResponseMessage<Count> getYearReportCount(String companyName) {
        YearReportCountApi api = ApiFactory.create(YearReportCountApi.class);
        api.setName(companyName);
        return api.getResponse();
    }

    /**
     * 查询单个公司增值电信机会
     *
     * @param companyName
     * @return
     */
    public static ResponseMessage<HasCount> getDianXinCount(String companyName) {
        DianXinCountApi api = ApiFactory.create(DianXinCountApi.class);
        api.setName(companyName);
        return api.getResponse();
    }

    /**
     * 查询单个公司娱乐牌照机会
     *
     * @param companyName
     * @return
     */
    public static ResponseMessage<HasCount> getYuLeCount(String companyName) {
        YuLeCountApi api = ApiFactory.create(YuLeCountApi.class);
        api.setName(companyName);
        return api.getResponse();
    }


    /**
     * 查询单个公司高新企业机会
     *
     * @param companyName
     * @return
     */
    public static ResponseMessage<Count> getGaoXinCount(String companyName) {
        GaoXinApi api = ApiFactory.create(GaoXinApi.class);
        api.setName(companyName);
        return api.getResponse();
    }


    /**
     * 查询单个公司税收筹划机会
     *
     * @param companyName
     * @return
     */
    public static ResponseMessage<Count> getShuiShouCount(String companyName) {
        ShuiShouApi api = ApiFactory.create(ShuiShouApi.class);
        api.setName(companyName);
        return api.getResponse();
    }

    /**
     * 查询单个公司影视审批机会
     *
     * @param companyName
     * @return
     */
    public static ResponseMessage<Count> getYingShiCount(String companyName) {
        YingShiCountApi api = ApiFactory.create(YingShiCountApi.class);
        api.setName(companyName);
        return api.getResponse();
    }

    /**
     * 查询单个公司食品流通机会
     *
     * @param companyName
     * @return
     */
    public static ResponseMessage<Count> getShiPinCount(String companyName) {
        ShiPinCountApi api = ApiFactory.create(ShiPinCountApi.class);
        api.setName(companyName);
        return api.getResponse();
    }

    /**
     * 根据公司名称查询新商标数据
     *
     * @param companyName 查询条件
     * @param currentPage
     * @param pageSize
     * @return
     */
    public static ResponseMessage<TmNew> getTmNewByCompany(String companyName, int currentPage, int pageSize) {
        TmNewByCompanyApi api = ApiFactory.create(TmNewByCompanyApi.class);
        api.setQ(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        TmNewResponseMessage response = api.getResponse();
        if (null != response) {
            response.setCurrentPage(currentPage);
            response.setPageSize(pageSize);
        }
        return response;
    }

    /**
     * 根据商标名称查询新商标数据
     *
     * @param tmName      查询条件
     * @param currentPage
     * @param pageSize
     * @return
     */
    public static ResponseMessage<TmNew> getTmNewByName(String tmName, int currentPage, int pageSize) {
        TmNewByNameApi api = ApiFactory.create(TmNewByNameApi.class);
        api.setQ(tmName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        TmNewResponseMessage response = api.getResponse();
        if (null != response) {
            response.setCurrentPage(currentPage);
            response.setPageSize(pageSize);
        }
        return response;
    }

    /**
     * 根据公司名称查询新商标数据
     *
     * @param regNo       查询条件
     * @param currentPage
     * @param pageSize
     * @return
     */
    public static ResponseMessage<TmNew> getTmNewByRegNo(String regNo, int currentPage, int pageSize) {
        TmNewByRegNoApi api = ApiFactory.create(TmNewByRegNoApi.class);
        api.setQ(regNo);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        TmNewResponseMessage response = api.getResponse();
        if (null != response) {
            response.setCurrentPage(currentPage);
            response.setPageSize(pageSize);
        }
        return response;
    }


    public static ResponseMessage<CompanyAbnormal> getAbnormalList(String companyName, int currentPage, int pageSize) {

        CompanyAbnormalApi api = ApiFactory.create(CompanyAbnormalApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<CompanyAbnormal> response = api.getResponse();
        if (response == null) {

            return null;
        }

        // ResponseMessage<CompanyAbnormal> response = new
        // ResponseMessage<CompanyAbnormal>();
        // List<CompanyAbnormal> list = new ArrayList<CompanyAbnormal>();
        // {
        // CompanyAbnormal entity = new CompanyAbnormal();
        // {
        //
        // entity.setId(222078129L);
        // entity.setPutDate("2015-08-10 00:00:00");
        // entity.setRemoveDate("2016-07-29 00:00:00");
        // entity.setPutDepartment("梅河口市市场监督管理局");
        // entity.setRemoveDepartment("梅河口市市场监督管理局");
        // entity.setRemoveReason("列入经营异常名录3年内且依照《经营异常名录管理办法》第六条规定被列入经营异常名录的企业，可以在补报未报年份的年度报告并公示后，申请移出");
        // }
        // list.add(entity);
        //
        // entity = new CompanyAbnormal();
        // {
        //
        // entity.setId(222078130L);
        // entity.setPutDate("2015-08-10 00:00:00");
        // entity.setRemoveDate("2016-08-02 00:00:00");
        // entity.setPutDepartment("梅河口市市场监督管理局");
        // entity.setRemoveDepartment("梅河口市市场监督管理局");
        // entity.setRemoveReason("列入经营异常名录3年内且依照《经营异常名录管理办法》第六条规定被列入经营异常名录的企业，可以在补报未报年份的年度报告并公示后，申请移出");
        // }
        // list.add(entity);
        // }
        // response.setList(list);
        return response;
    }

    public static ResponseMessage<CompanyEquity> getEquityList(String companyName, int currentPage, int pageSize) {

        CompanyEquityApi api = ApiFactory.create(CompanyEquityApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<CompanyEquity> response = api.getResponse();
        if (response == null) {

            return null;
        }

        // ResponseMessage<CompanyEquity> response = new
        // ResponseMessage<CompanyEquity>();
        // List<CompanyEquity> list = new ArrayList<CompanyEquity>();
        // {
        // CompanyEquity entity = new CompanyEquity();
        // {
        //
        // entity.setId(13890154L);
        // entity.setRegNumber("110108002734659_0002");
        // entity.setPledgor("李彦宏");
        // entity.setPledgee("百度在线网络技术（北京）有限公司");
        // entity.setEquityAmount("9950 万元");
        // entity.setRegDate("2011-12-22 00:00:00");
        // entity.setState("有效");
        // }
        // list.add(entity);
        //
        // entity = new CompanyEquity();
        // {
        //
        // entity.setId(13890155L);
        // entity.setRegNumber("110108002734659_0003");
        // entity.setPledgor("王湛");
        // entity.setPledgee("百度在线网络技术（北京）有限公司");
        // entity.setEquityAmount("50 万元");
        // entity.setRegDate("2017-05-09 00:00:00");
        // entity.setState("无效");
        // }
        // list.add(entity);
        // }
        // response.setList(list);
        return response;
    }

    public static ResponseMessage<CompanyMortgage> getMortgageList(String companyName, int currentPage, int pageSize) {

        CompanyMortgageApi api = ApiFactory.create(CompanyMortgageApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<CompanyMortgage> response = api.getResponse();
        if (response == null) {

            return null;
        }

        // ResponseMessage<CompanyMortgage> response = new
        // ResponseMessage<CompanyMortgage>();
        // List<CompanyMortgage> list = new ArrayList<CompanyMortgage>();
        // {
        // CompanyMortgage entity = new CompanyMortgage();
        // {
        //
        // entity.setId(5662L);
        // entity.setRegNum("黑克山工商动抵登字（2015）第35号");
        // entity.setRegDate("2015-04-29 00:00:00");
        // entity.setRegDepartment("黑龙江省克山县工商行政管理局");
        // entity.setStatus("有效");
        // entity.setAmount("150万元");
        // }
        // list.add(entity);
        //
        // entity = new CompanyMortgage();
        // {
        // entity.setId(8640010L);
        // entity.setRegNum("黑克山工商动抵登字（2015）第35号");
        // entity.setRegDate("2015-04-29 00:00:00");
        // entity.setRegDepartment("克山县市场监督管理局");
        // entity.setStatus("有效");
        // entity.setAmount("150万元");
        // }
        // list.add(entity);
        // }
        // response.setList(list);
        return response;
    }

    public static ResponseMessage<CourtAnnouncement> getCourtAnnouncementList(String companyName, int currentPage, int pageSize) {

        CourtAnnouncementApi api =
                ApiFactory.create(CourtAnnouncementApi.class);
        api.setCompanyName(companyName);
        api.setCurrentPage(currentPage);
        api.setPageSize(pageSize);
        ResponseMessage<CourtAnnouncement> response = api.getResponse();
        if (response == null) {

            return null;
        }

        // ResponseMessage<CourtAnnouncement> response = new
        // ResponseMessage<CourtAnnouncement>();
        // List<CourtAnnouncement> list = new ArrayList<CourtAnnouncement>();
        // {
        // CourtAnnouncement entity = new CourtAnnouncement();
        // {
        //
        // entity.setId(80L);
        // entity.setPublishdate("2016-01-06");
        // entity.setBltntype("62");
        // entity.setParty1("安得利（北京）食品贸易有限公司");
        // entity.setContent("北京臻香思餐饮管理有限公司：\n本院对原告安得利（北京）食品贸易有限公司诉你方买卖合同纠纷一案已审理终结。现依法向你方公告送达（2015）朝民（商）初字第29066号民事判决书。被告自公告之日起60日内来本院领取民事判决书，逾期则视为送达。如不服本判决，可在公告期满后15日内，向本院递交上诉状及副本，上诉于北京市第三中级人民法院。逾期未上诉的，本判决即发生法律效力。 \n");
        //
        // }
        // list.add(entity);
        //
        // entity = new CourtAnnouncement();
        // {
        // entity.setId(136753L);
        // entity.setPublishdate("2015-10-31");
        // entity.setBltntype("132");
        // entity.setParty1("北京麦冬仓储有限公司");
        // entity.setContent("北京臻香思餐饮管理有限公司：\n本院受理原告北京麦冬仓储有限公司诉被告北京臻香思餐饮管理有限公司仓储合同纠纷一案，现依法向你公告送达起诉状副本、应诉通知书、开庭传票。自本公告发出之日起经过60日即视为送达。提出答辩状的期限为公告送达期满后次日起15日内。并定于答辩期满后第1个工作日上午9时（遇法定节假日顺延）在本院第44法庭公开开庭审理。 \n");
        //
        // }
        // list.add(entity);
        //
        // entity = new CourtAnnouncement();
        // {
        // entity.setId(147330L);
        // entity.setPublishdate("2015-10-24");
        // entity.setBltntype("132");
        // entity.setParty1("于爱匣");
        // entity.setContent("北京臻香思餐饮管理有限公司：                   \n本院受理的（2015）朝民（商）初字第33814号原告于爱匣诉被告北京臻香思餐饮管理有限公司合伙协议纠纷一案，现依法向你公告送达民事起诉状副本、开庭传票、应诉通知书、举证通知书。原告的诉讼请求为：1、确认原被告于2014年5月16日签订的《合伙协议》已解除；2、被告返还原告出资款20万元；3、诉讼费被告承担。自公告发出之日起60日视为送达。提出答辩状和举证的时限为送达期满后的15日内。并定于举证期满后第1个工作日上午9时（如遇法定节假日顺延）在北京市朝阳区华威北里甲14号民四庭开庭审理，逾期将依法缺席判决。\n");
        //
        // }
        // list.add(entity);
        // }
        // response.setList(list);
        return response;
    }
}