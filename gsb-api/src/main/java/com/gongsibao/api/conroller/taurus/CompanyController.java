package com.gongsibao.api.conroller.taurus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import com.gongsibao.api.conroller.taurus.analysis.AnalysisContext;
import com.gongsibao.api.conroller.taurus.analysis.AnalysisManager;
import com.gongsibao.api.conroller.taurus.dic.TrademarkAttentionDegree;
import com.gongsibao.api.conroller.taurus.dto.AnalysisDTO;
import com.gongsibao.api.conroller.taurus.dto.CompanyRegistryInfoDTO;
import com.gongsibao.api.conroller.taurus.dto.HaveProtectedTmCategoryDTO;
import com.gongsibao.api.conroller.taurus.dto.SuggestProtectedTmCategoryDTO;
import com.gongsibao.api.conroller.taurus.dto.TmInfoDTO;
import com.gongsibao.api.conroller.taurus.dto.TmOrCopyrightDTO;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;

import com.gongsibao.api.auth.AuthAnnotation;
import com.gongsibao.api.util.ApiException;
import com.gongsibao.entity.yj.YjTrademarkCategory;
import com.gongsibao.taurus.entity.AnnualReport;
import com.gongsibao.taurus.entity.Company;
import com.gongsibao.taurus.entity.Copyright;
import com.gongsibao.taurus.entity.EntChangeRecord;
import com.gongsibao.taurus.entity.EntInvest;
import com.gongsibao.taurus.entity.EntMember;
import com.gongsibao.taurus.entity.EntRegistry;
import com.gongsibao.taurus.entity.EntShareholder;
import com.gongsibao.taurus.entity.IcpInfo;
import com.gongsibao.taurus.entity.Judgment;
import com.gongsibao.taurus.entity.ReportOutboundInvestment;
import com.gongsibao.taurus.entity.ReportShareholder;
import com.gongsibao.taurus.entity.ReportWebInfo;
import com.gongsibao.taurus.entity.Tm;
import com.gongsibao.taurus.entity.WorksCopyright;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.service.TaurusApiService;

@Path("/jnz/company")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CompanyController {

	@GET
	@AuthAnnotation
	@Path("/{keyword}")
	public ResponseMessage<Company> search(@PathParam("keyword") String keyword) {

		return null;
	}

	/**
	 * @Title: entregistry
	 * @Description: TODO(企业注册信息)
	 * @param: @param companyName
	 * @param: @return
	 * @return: CompanyRegistryInfo
	 * @throws
	 */
	@GET
	@AuthAnnotation
	@Path("/entregistry/{companyName}")
	public CompanyRegistryInfoDTO entregistry(
			@PathParam("companyName") String companyName) {

		this.verifyCompanyName(companyName);

		CompanyRegistryInfoDTO dto = new CompanyRegistryInfoDTO();

		EntRegistry entRegistry = TaurusApiService.getEntRegistry(companyName);
		if (entRegistry == null) {

			throw new ApiException(-1, "企业信息不存在");
		}

		dto.setEntRegistry(entRegistry);

		// 存续年数
		Date foundation = DateManage.parse(entRegistry.getFoundation());
		dto.setYears(DateManage.getDistinceYear(foundation, new Date()));

		// 经营项目
		if (!StringManager.isNullOrEmpty(entRegistry.getScope())) {

			dto.setProjectCount(StringUtils.split(entRegistry.getScope(), ";|；").length);
		}
		// 商标
		int tmCount = TaurusApiService.getTmList(companyName, 0, 1)
				.getTotalSize();
		dto.setTmCount(tmCount);

		// 专利
		int patentCount = TaurusApiService.getPatentsList(companyName, 0, 1)
				.getTotalSize();
		dto.setPatentCount(patentCount);

		// 对外投资
		int entInvestCount = TaurusApiService.getEntInvestList(companyName, 0,
				1).getTotalSize();
		dto.setEntInvestCount(entInvestCount);

		// 作品著作权
		int worksCopyrightCount = TaurusApiService.getWorksCopyrightList(
				companyName, 0, 1).getTotalSize();
		dto.setWorksCopyrightCount(worksCopyrightCount);

		// 软件著作权
		int copyrightCount = TaurusApiService.getCopyrightList(companyName, 0,
				1).getTotalSize();
		dto.setCopyrightCount(copyrightCount);

		return dto;
	}

	@GET
	@AuthAnnotation
	@Path("/lstEntShareholder/{currentPage}/{pageSize}/{companyName}")
	public ResponseMessage<EntShareholder> lstEntShareholder(
			@PathParam("companyName") String companyName,
			@DefaultValue("0") @PathParam("currentPage") Integer currentPage,
			@DefaultValue("10") @PathParam("pageSize") Integer pageSize) {

		this.verifyCompanyName(companyName);
		ResponseMessage<EntShareholder> list = TaurusApiService
				.getEntShareholderList(companyName, currentPage, pageSize);
		return list;
	}

	@GET
	@AuthAnnotation
	@Path("/lstJudgment/{currentPage}/{pageSize}/{companyName}")
	public ResponseMessage<Judgment> lstJudgment(
			@PathParam("companyName") String companyName,
			@DefaultValue("0") @PathParam("currentPage") Integer currentPage,
			@DefaultValue("10") @PathParam("pageSize") Integer pageSize) {

		this.verifyCompanyName(companyName);
		ResponseMessage<Judgment> list = TaurusApiService.getJudgmentList(
				companyName, currentPage, pageSize);
		return list;
	}

	@GET
	@AuthAnnotation
	@Path("/lstEntMember/{currentPage}/{pageSize}/{companyName}")
	public ResponseMessage<EntMember> lstEntMember(
			@PathParam("companyName") String companyName,
			@DefaultValue("0") @PathParam("currentPage") Integer currentPage,
			@DefaultValue("10") @PathParam("pageSize") Integer pageSize) {

		this.verifyCompanyName(companyName);
		ResponseMessage<EntMember> list = TaurusApiService.getEntMemberList(
				companyName, currentPage, pageSize);
		return list;
	}

	@GET
	@AuthAnnotation
	@Path("/lstEntChangeRecord/{currentPage}/{pageSize}/{companyName}")
	public ResponseMessage<EntChangeRecord> lstEntChangeRecord(
			@PathParam("companyName") String companyName,
			@DefaultValue("0") @PathParam("currentPage") Integer currentPage,
			@DefaultValue("10") @PathParam("pageSize") Integer pageSize) {

		this.verifyCompanyName(companyName);

		ResponseMessage<EntChangeRecord> list = TaurusApiService
				.getEntChangeRecordList(companyName, currentPage, pageSize);
		return list;
	}

	@GET
	@AuthAnnotation
	@Path("/lstEntInvest/{currentPage}/{pageSize}/{companyName}")
	public ResponseMessage<EntInvest> lstEntInvest(
			@PathParam("companyName") String companyName,
			@DefaultValue("0") @PathParam("currentPage") Integer currentPage,
			@DefaultValue("10") @PathParam("pageSize") Integer pageSize) {

		this.verifyCompanyName(companyName);
		ResponseMessage<EntInvest> list = TaurusApiService.getEntInvestList(
				companyName, currentPage, pageSize);
		return list;
	}

	@GET
	@AuthAnnotation
	@Path("/lstAnnualReport/{currentPage}/{pageSize}/{companyName}")
	public ResponseMessage<AnnualReport> lstAnnualReport(
			@PathParam("companyName") String companyName,
			@DefaultValue("0") @PathParam("currentPage") Integer currentPage,
			@DefaultValue("10") @PathParam("pageSize") Integer pageSize) {

		this.verifyCompanyName(companyName);
		ResponseMessage<AnnualReport> list = TaurusApiService
				.getAnnualReportList(companyName, currentPage, pageSize);
		return list;
	}

	@GET
	@AuthAnnotation
	@Path("/lstReportShareholder/{currentPage}/{pageSize}/{reportId}")
	public ResponseMessage<ReportShareholder> lstReportShareholder(
			@PathParam("reportId") Integer reportId,
			@DefaultValue("0") @PathParam("currentPage") Integer currentPage,
			@DefaultValue("10") @PathParam("pageSize") Integer pageSize) {

		ResponseMessage<ReportShareholder> list = TaurusApiService
				.getReportShareholderList(reportId, currentPage, pageSize);
		return list;
	}

	@GET
	@AuthAnnotation
	@Path("/lstReportOutboundInvestment/{currentPage}/{pageSize}/{reportId}")
	public ResponseMessage<ReportOutboundInvestment> lstReportOutboundInvestment(
			@PathParam("reportId") Integer reportId,
			@DefaultValue("0") @PathParam("currentPage") Integer currentPage,
			@DefaultValue("10") @PathParam("pageSize") Integer pageSize) {

		ResponseMessage<ReportOutboundInvestment> list = TaurusApiService
				.getReportOutboundInvestmentList(reportId, currentPage,
						pageSize);
		return list;
	}

	@GET
	@AuthAnnotation
	@Path("/lstReportWebInfo/{currentPage}/{pageSize}/{reportId}")
	public ResponseMessage<ReportWebInfo> lstReportWebInfo(
			@PathParam("reportId") Integer reportId,
			@DefaultValue("0") @PathParam("currentPage") Integer currentPage,
			@DefaultValue("10") @PathParam("pageSize") Integer pageSize) {

		ResponseMessage<ReportWebInfo> list = TaurusApiService
				.getReportWebInfoList(reportId, currentPage, pageSize);
		return list;
	}

	@GET
	@AuthAnnotation
	@Path("/lstEnt/{currentPage}/{pageSize}/{companyName}")
	public ResponseMessage<com.gongsibao.taurus.entity.Company> lstEnt(
			@PathParam("companyName") String companyName,
			@DefaultValue("0") @PathParam("currentPage") Integer currentPage,
			@DefaultValue("10") @PathParam("pageSize") Integer pageSize) {

		this.verifyCompanyName(companyName);
		ResponseMessage<com.gongsibao.taurus.entity.Company> list = TaurusApiService
				.getEntList(companyName, currentPage, pageSize);
		return list;
	}

	@GET
	@AuthAnnotation
	@Path("/lstIcpInfo/{currentPage}/{pageSize}/{companyName}")
	public ResponseMessage<IcpInfo> lstIcpInfo(
			@PathParam("companyName") String companyName,
			@DefaultValue("0") @PathParam("currentPage") Integer currentPage,
			@DefaultValue("10") @PathParam("pageSize") Integer pageSize) {

		this.verifyCompanyName(companyName);
		ResponseMessage<IcpInfo> list = TaurusApiService.getIcpInfoList(
				companyName, currentPage, pageSize);
		return list;
	}

	@GET
	@AuthAnnotation
	@Path("/lstTmOrCopyright/{companyName}")
	public TmOrCopyrightDTO lstTmOrCopyright(
			@PathParam("companyName") String companyName) {

		this.verifyCompanyName(companyName);

		int page = 0;
		int currentPage = 0;
		// 2.13 商标
		List<Tm> tmList = new ArrayList<>();
		ResponseMessage<Tm> tmResponseMessage = TaurusApiService.getTmList(
				companyName, 0, Integer.MAX_VALUE);
		if (tmResponseMessage != null && tmResponseMessage.getTotalSize() > 0) {
			for (Tm tm : tmResponseMessage.getList()) {
				if (StringUtils.trimToEmpty(tm.getTmName()).contains("游戏")
						|| StringUtils.trimToEmpty(tm.getTmName()).contains(
								"游戏机")) {
					tmList.add(tm);
				}
			}

			page = (tmResponseMessage.getTotalSize() - 1) / 99 + 1;
			currentPage = tmResponseMessage.getCurrentPage();
			for (int i = 0; i < page - 1; i++) {
				tmResponseMessage = TaurusApiService.getTmList(companyName,
						currentPage + 2, Integer.MAX_VALUE);
				currentPage = tmResponseMessage.getCurrentPage();
				if (tmResponseMessage != null
						&& tmResponseMessage.getTotalSize() > 0) {
					for (Tm tm : tmResponseMessage.getList()) {
						if (StringUtils.trimToEmpty(tm.getTmName()).contains(
								"游戏")
								|| StringUtils.trimToEmpty(tm.getTmName())
										.contains("游戏机")) {
							tmList.add(tm);
						}
					}
				}
			}
		}

		// 2.12 软件著作权
		List<Copyright> copyrightList = new ArrayList<>();
		ResponseMessage<Copyright> copyrightResponseMessage = TaurusApiService
				.getCopyrightList(companyName, 0, Integer.MAX_VALUE);
		if (copyrightResponseMessage != null
				&& copyrightResponseMessage.getTotalSize() > 0) {
			for (Copyright copyright : copyrightResponseMessage.getList()) {
				if (StringUtils.trimToEmpty(copyright.getFullName()).contains(
						"游戏")
						|| StringUtils.trimToEmpty(copyright.getFullName())
								.contains("游戏机")) {
					copyrightList.add(copyright);
				}
			}

			page = (copyrightResponseMessage.getTotalSize() - 1) / 99 + 1;
			currentPage = copyrightResponseMessage.getCurrentPage();
			for (int i = 0; i < page - 1; i++) {
				copyrightResponseMessage = TaurusApiService.getCopyrightList(
						companyName, currentPage + 2, Integer.MAX_VALUE);
				currentPage = copyrightResponseMessage.getCurrentPage();
				if (copyrightResponseMessage != null
						&& copyrightResponseMessage.getTotalSize() > 0) {
					for (Copyright copyright : copyrightResponseMessage
							.getList()) {
						if (StringUtils.trimToEmpty(copyright.getFullName())
								.contains("游戏")
								|| StringUtils.trimToEmpty(
										copyright.getFullName())
										.contains("游戏机")) {
							copyrightList.add(copyright);
						}
					}
				}
			}
		}

		// 2.14 作品著作权
		List<WorksCopyright> worksCopyrightList = new ArrayList<>();
		ResponseMessage<WorksCopyright> worksCopyrightResponseMessage = TaurusApiService
				.getWorksCopyrightList(companyName, 0, Integer.MAX_VALUE);
		if (worksCopyrightResponseMessage != null
				&& worksCopyrightResponseMessage.getTotalSize() > 0) {
			for (WorksCopyright worksCopyright : worksCopyrightResponseMessage
					.getList()) {
				if (StringUtils.trimToEmpty(worksCopyright.getName()).contains(
						"游戏")
						|| StringUtils.trimToEmpty(worksCopyright.getName())
								.contains("游戏机")) {
					worksCopyrightList.add(worksCopyright);
				}
			}

			page = (worksCopyrightResponseMessage.getTotalSize() - 1) / 99 + 1;
			currentPage = worksCopyrightResponseMessage.getCurrentPage();
			for (int i = 0; i < page - 1; i++) {
				worksCopyrightResponseMessage = TaurusApiService
						.getWorksCopyrightList(companyName, currentPage + 2,
								Integer.MAX_VALUE);
				currentPage = worksCopyrightResponseMessage.getCurrentPage();
				if (worksCopyrightResponseMessage != null
						&& worksCopyrightResponseMessage.getTotalSize() > 0) {
					for (WorksCopyright worksCopyright : worksCopyrightResponseMessage
							.getList()) {
						if (StringUtils.trimToEmpty(worksCopyright.getName())
								.contains("游戏")
								|| StringUtils.trimToEmpty(
										worksCopyright.getName()).contains(
										"游戏机")) {
							worksCopyrightList.add(worksCopyright);
						}
					}
				}
			}
		}

		TmOrCopyrightDTO dto = new TmOrCopyrightDTO();
		{
			dto.setTmList(tmList);
			dto.setCopyrightList(copyrightList);
			dto.setWorksCopyrightList(worksCopyrightList);
		}
		return dto;
	}

	@GET
	@AuthAnnotation
	@Path("/analysis/{companyName}")
	public AnalysisDTO analysis(@PathParam("companyName") String companyName) {

		this.verifyCompanyName(companyName);

		// 2.1 查询 企业注册信息
		EntRegistry entRegistry = TaurusApiService.getEntRegistry(companyName);
		if (entRegistry == null) {

			throw new ApiException(-1, "该公司不存在");
		}

		AnalysisContext context = new AnalysisContext();{
			context.setCompanyName(companyName);
			context.setAnalysisDTO(new AnalysisDTO());
			context.setEntRegistry(entRegistry);
		}
		AnalysisDTO dto = AnalysisManager.getAnalysisDTO(context);
		return dto;
	}

	private void verifyCompanyName(String companyName) {

		if (StringManager.isNullOrEmpty(companyName)) {

			throw new ApiException(-1, "名称非空");
		}
	}

	private TmInfoDTO getTrademarkInfo(EntRegistry entRegistry) {

		TmInfoDTO dto = new TmInfoDTO();

		ResponseMessage<Tm> tmResponseMessage = TaurusApiService.getTmList(
				entRegistry.getName(), 0, Integer.MAX_VALUE);
		int tmCount = tmResponseMessage.getTotalSize();

		List<HaveProtectedTmCategoryDTO> haveList = new ArrayList<HaveProtectedTmCategoryDTO>();
		// List<YjTrademarkCategory> trademarkCategoryList =
		// yjTrademarkCategoryService.getByLevel(1);
//		List<YjTrademarkCategory> trademarkCategoryList = new ArrayList<YjTrademarkCategory>();
//		for (Tm tm : tmResponseMessage.getList()) {
//			List<YjTrademarkCategory> temptmcList = trademarkCategoryList
//					.stream()
//					.filter(x -> tm.getClassify().replace("、", "")
//							.indexOf(x.getName()) > -1)
//					.collect(Collectors.toList());
//			for (YjTrademarkCategory tmc : temptmcList) {
//
//				HaveProtectedTmCategoryDTO protectedDTO = new HaveProtectedTmCategoryDTO();
//				{
//
//					protectedDTO.setIntclsName(tmc.getAlias());
//					protectedDTO.setName(tmc.getName());
//					protectedDTO.setCategoryId(tmc.getCode());
//				}
//			}
//		}
		// 去重
//		haveList = haveList
//				.stream()
//				.sorted((t1, t2) -> {
//					return Integer.parseInt(t1.getCategoryId())
//							- Integer.parseInt(t2.getCategoryId());
//				}).distinct().collect(Collectors.toList());
//
//		List<SuggestProtectedTmCategoryDTO> suggestList = new ArrayList<SuggestProtectedTmCategoryDTO>();
//		dto.setPotentialDemandAmount(100L);
//		dto.setAttentionDegree(TrademarkAttentionDegree.getItem(tmCount)
//				.getText());// 重视度
//		dto.setChanceCount(suggestList.size());
//		dto.setHaveCount(tmCount);
//		dto.setHaveList(haveList);
//		dto.setSuggestList(suggestList);

		// resMap.put("potentialDemandAmount",
		// AmountUtils.ceil(prodApiService.getPrice(1177) *
		// suggestList.size()));//潜在金额(默认【商标注册专业版】)

		return dto;
	}
}
