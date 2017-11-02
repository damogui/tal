package com.gongsibao.taurus.service;

import java.util.List;

import com.gongsibao.taurus.api.AnnualReportApi;
import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.CompanyAbnormalApi;
import com.gongsibao.taurus.api.CompanyEquityApi;
import com.gongsibao.taurus.api.CompanyMortgageApi;
import com.gongsibao.taurus.api.CopyrightApi;
import com.gongsibao.taurus.api.CourtAnnouncementApi;
import com.gongsibao.taurus.api.CourtExecutiveApi;
import com.gongsibao.taurus.api.DishonestInfoApi;
import com.gongsibao.taurus.api.EntBranchApi;
import com.gongsibao.taurus.api.EntChangeRecordApi;
import com.gongsibao.taurus.api.EntInvestApi;
import com.gongsibao.taurus.api.EntMemberApi;
import com.gongsibao.taurus.api.EntRegistryApi;
import com.gongsibao.taurus.api.EntSearchApi;
import com.gongsibao.taurus.api.EntShareholderApi;
import com.gongsibao.taurus.api.IcpInfoApi;
import com.gongsibao.taurus.api.JudgmentApi;
import com.gongsibao.taurus.api.PatentDescApi;
import com.gongsibao.taurus.api.PatentsApi;
import com.gongsibao.taurus.api.ReportOutboundInvestmentApi;
import com.gongsibao.taurus.api.ReportShareholderApi;
import com.gongsibao.taurus.api.ReportWebInfoApi;
import com.gongsibao.taurus.api.TmApi;
import com.gongsibao.taurus.api.TmdescApi;
import com.gongsibao.taurus.api.TmflowApi;
import com.gongsibao.taurus.api.WorksCopyrightApi;
import com.gongsibao.taurus.entity.AnnualReport;
import com.gongsibao.taurus.entity.Company;
import com.gongsibao.taurus.entity.CompanyAbnormal;
import com.gongsibao.taurus.entity.CompanyEquity;
import com.gongsibao.taurus.entity.CompanyMortgage;
import com.gongsibao.taurus.entity.Copyright;
import com.gongsibao.taurus.entity.CourtAnnouncement;
import com.gongsibao.taurus.entity.CourtExecutive;
import com.gongsibao.taurus.entity.DishonestInfo;
import com.gongsibao.taurus.entity.EntBranch;
import com.gongsibao.taurus.entity.EntChangeRecord;
import com.gongsibao.taurus.entity.EntInvest;
import com.gongsibao.taurus.entity.EntMember;
import com.gongsibao.taurus.entity.EntRegistry;
import com.gongsibao.taurus.entity.EntShareholder;
import com.gongsibao.taurus.entity.IcpInfo;
import com.gongsibao.taurus.entity.Judgment;
import com.gongsibao.taurus.entity.PatentDesc;
import com.gongsibao.taurus.entity.Patents;
import com.gongsibao.taurus.entity.ReportOutboundInvestment;
import com.gongsibao.taurus.entity.ReportShareholder;
import com.gongsibao.taurus.entity.ReportWebInfo;
import com.gongsibao.taurus.entity.Tm;
import com.gongsibao.taurus.entity.Tmdesc;
import com.gongsibao.taurus.entity.Tmflow;
import com.gongsibao.taurus.entity.WorksCopyright;
import com.gongsibao.taurus.message.ResponseMessage;

public class TaurusApiService {

	/**
	 * @Title: getEntRegistry
	 * @Description: 获取企业注册信息
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static EntRegistry getEntRegistry(String companyName) {

		EntRegistryApi api = ApiFactory.create(EntRegistryApi.class);
		api.setCompanyName(companyName);
		ResponseMessage<EntRegistry> response = api.getResponse();
		if (response == null) {

			return null;
		}
		
		if (response.getList().size() == 0) {

			return null;
		}
		
		return response.getList().get(0);
	}

	/**
	 * @Title: getAnnualReport
	 * @Description: 获取年报汇总
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<AnnualReport>
	 * @throws
	 */
	public static ResponseMessage<AnnualReport> getAnnualReportList(String companyName,int currentPage,int pageSize) {

		AnnualReportApi api = ApiFactory.create(AnnualReportApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<AnnualReport> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getCopyright
	 * @Description: 获取软件著作权
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<Copyright>
	 * @throws
	 */
	public static ResponseMessage<Copyright> getCopyrightList(String companyName,int currentPage,int pageSize) {

		CopyrightApi api = ApiFactory.create(CopyrightApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<Copyright> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getCourtExecutive
	 * @Description: 获取被执行人信息
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<CourtExecutive>
	 * @throws
	 */
	public static ResponseMessage<CourtExecutive> getCourtExecutiveList(String companyName,int currentPage,int pageSize) {

		CourtExecutiveApi api = ApiFactory.create(CourtExecutiveApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<CourtExecutive> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getCourtExecutive
	 * @Description: 获取失信人信息
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<DishonestInfo> getDishonestInfoList(String companyName,int currentPage,int pageSize) {

		DishonestInfoApi api = ApiFactory.create(DishonestInfoApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<DishonestInfo> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getEntBranch
	 * @Description: 获取分支机构
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<EntBranch> getEntBranchList(String companyName,int currentPage,int pageSize) {

		EntBranchApi api = ApiFactory.create(EntBranchApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<EntBranch> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getEntChangeRecord
	 * @Description: 获取企业变更记录
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<EntChangeRecord> getEntChangeRecordList(String companyName,int currentPage,int pageSize) {

		EntChangeRecordApi api = ApiFactory.create(EntChangeRecordApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<EntChangeRecord> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getEntInvest
	 * @Description: 获取对外投资
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<EntInvest> getEntInvestList(String companyName,int currentPage,int pageSize) {

		EntInvestApi api = ApiFactory.create(EntInvestApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<EntInvest> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getEntMember
	 * @Description: 获取主要人员信息
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<EntMember> getEntMemberList(String companyName,int currentPage,int pageSize) {

		EntMemberApi api = ApiFactory.create(EntMemberApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<EntMember> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getEntShareholder
	 * @Description: 获取股东信息信息
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<EntShareholder> getEntShareholderList(String companyName,int currentPage,int pageSize) {

		EntShareholderApi api = ApiFactory.create(EntShareholderApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<EntShareholder> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getEntRegistry
	 * @Description: 获取ICP 信息
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<IcpInfo> getIcpInfoList(String companyName,int currentPage,int pageSize) {

		IcpInfoApi api = ApiFactory.create(IcpInfoApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<IcpInfo> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getJudgment
	 * @Description: 获取法院判决信息
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<Judgment> getJudgmentList(String companyName,int currentPage,int pageSize) {

		JudgmentApi api = ApiFactory.create(JudgmentApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<Judgment> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getPatents
	 * @Description: 获取专利信息
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<Patents> getPatentsList(String companyName,int currentPage,int pageSize) {

		PatentsApi api = ApiFactory.create(PatentsApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<Patents> response = api.getResponse();
		return response;
	}


	/**
	 * @Title: getTm
	 * @Description: 获取商标
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<Tm> getTmList(String companyName,int currentPage,int pageSize) {

		TmApi api = ApiFactory.create(TmApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<Tm> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getWorksCopyright
	 * @Description: 获取作品著作权
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<WorksCopyright> getWorksCopyrightList(String companyName,int currentPage,int pageSize) {

		WorksCopyrightApi api = ApiFactory.create(WorksCopyrightApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<WorksCopyright> response = api.getResponse();
		return response;
	}

	/**
	 * @Title: getPatentDesc
	 * @Description: 获取专利详情
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
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
	 * @Title: getTmdesc
	 * @Description: 获取商标详情
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
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
	 * @Title: getReportWebInfo
	 * @Description: 获取商标流程
	 * @param: @param companyName
	 * @param: @return
	 * @return: List<DishonestInfo>
	 * @throws
	 */
	public static ResponseMessage<Tmflow> getTmflowList(int tmId,int currentPage,int pageSize) {

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
	 * @Title: getReportShareholderList   
	 * @Description: TODO(获取年报股东出资信息)   
	 * @param: @param annualReportId
	 * @param: @param currentPage
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: ResponseMessage<ReportShareholder>      
	 * @throws   
	 */
	public static ResponseMessage<ReportShareholder> getReportShareholderList(int annualReportId,int currentPage,int pageSize) {

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
	 * @Title: getReportOutboundInvestmentList   
	 * @Description: TODO(获取年报对外投资)
	 * @param: @param annualReportId
	 * @param: @param currentPage
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: ResponseMessage<ReportOutboundInvestment>      
	 * @throws   
	 */
	public static ResponseMessage<ReportOutboundInvestment> getReportOutboundInvestmentList(int annualReportId,int currentPage,int pageSize) {

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
	 * @Title: getReportWebInfoList   
	 * @Description: TODO(获取年报网站信息)   
	 * @param: @param annualReportId
	 * @param: @param currentPage
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: ResponseMessage<ReportWebInfo>      
	 * @throws   
	 */
	public static ResponseMessage<ReportWebInfo> getReportWebInfoList(int annualReportId,int currentPage,int pageSize) {

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
	 * @Title: getEntList   
	 * @Description: TODO(模糊查询公司信息)   
	 * @param: @param companyName
	 * @param: @param currentPage
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: ResponseMessage<Company>      
	 * @throws   
	 */
	public static ResponseMessage<Company> getEntList(String companyName,int currentPage,int pageSize) {

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
	
	public static ResponseMessage<CompanyAbnormal> getAbnormalList(String companyName,int currentPage,int pageSize) {

		CompanyAbnormalApi api = ApiFactory.create(CompanyAbnormalApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<CompanyAbnormal> response = api.getResponse();
		if (response == null) {

			return null;
		}
		return response;
	}
	
	public static ResponseMessage<CompanyEquity> getEquityList(String companyName,int currentPage,int pageSize) {

		CompanyEquityApi api = ApiFactory.create(CompanyEquityApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<CompanyEquity> response = api.getResponse();
		if (response == null) {

			return null;
		}
		return response;
	}
	
	public static ResponseMessage<CompanyMortgage> getMortgageList(String companyName,int currentPage,int pageSize) {

		CompanyMortgageApi api = ApiFactory.create(CompanyMortgageApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<CompanyMortgage> response = api.getResponse();
		if (response == null) {

			return null;
		}
		return response;
	}
	
	public static ResponseMessage<CourtAnnouncement> getCourtAnnouncementList(String companyName,int currentPage,int pageSize) {

		CourtAnnouncementApi api = ApiFactory.create(CourtAnnouncementApi.class);
		api.setCompanyName(companyName);
		api.setCurrentPage(currentPage);
		api.setPageSize(pageSize);
		ResponseMessage<CourtAnnouncement> response = api.getResponse();
		if (response == null) {

			return null;
		}
		return response;
	}
	
}
