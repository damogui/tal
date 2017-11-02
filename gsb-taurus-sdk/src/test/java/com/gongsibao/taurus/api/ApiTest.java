package com.gongsibao.taurus.api;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

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
import com.gongsibao.taurus.service.TaurusApiService;
import com.gongsibao.taurus.util.json.JacksonObjectMapper;

public class ApiTest {

	String companyName = "北京百度网讯科技有限公司";

	ObjectMapper mapper = new JacksonObjectMapper();

	String json = "";

	@Test
	public void run() throws JsonGenerationException, JsonMappingException, IOException {
//
//		// 2.1 查询 企业注册信息
//		EntRegistry entRegistry = TaurusApiService.getEntRegistry(companyName);
//		json = mapper.writeValueAsString(entRegistry);
//		System.out.println(json);
//		System.err.println("注册信息--------------------------------------------------------------------");
//
//		// 2.15 年报汇总
//		ResponseMessage<AnnualReport> annualReportList = TaurusApiService.getAnnualReportList(companyName,0,10);
//		json = mapper.writeValueAsString(annualReportList);
//		System.out.println(json);
//		System.err.println(annualReportList.getTotalSize() +"，年报汇总--------------------------------------------------------------------");
//
//		// 2.12  软件著作权
//		ResponseMessage<Copyright> copyrightList = TaurusApiService.getCopyrightList(companyName,0,10);
//		json = mapper.writeValueAsString(copyrightList);
//		System.out.println(json);
//		System.err.println(copyrightList.getTotalSize()+"：软件著作权--------------------------------------------------------------------");
//
//		// 2.8 被执行人信息
//		ResponseMessage<CourtExecutive> courtExecutiveList = TaurusApiService.getCourtExecutiveList(companyName,0,10);
//		json = mapper.writeValueAsString(courtExecutiveList);
//		System.out.println(json);
//		System.err.println(courtExecutiveList.getTotalSize()+"：被执行人信息--------------------------------------------------------------------");
//
//		// 2.9 失信人信息
//		ResponseMessage<DishonestInfo> dishonestInfoList = TaurusApiService.getDishonestInfoList(companyName,0,10);
//		json = mapper.writeValueAsString(dishonestInfoList);
//		System.out.println(json);
//		System.err.println(dishonestInfoList.getTotalSize()+"：失信人信息--------------------------------------------------------------------");
//
//		// 2.6 分支机构
//		
		ResponseMessage<EntBranch> entBranchList = TaurusApiService.getEntBranchList(companyName,0,10);
		json = mapper.writeValueAsString(entBranchList);
		System.out.println(json);
		System.err.println(entBranchList.getTotalSize()+"：分支机构--------------------------------------------------------------------");
//
//		// 2.5 企业变更记录
//		ResponseMessage<EntChangeRecord> entChangeRecordList = TaurusApiService.getEntChangeRecordList(companyName,0,10);
//		json = mapper.writeValueAsString(entChangeRecordList);
//		System.out.println(json);
//		System.err.println(entChangeRecordList.getTotalSize()+"： 企业变更记录--------------------------------------------------------------------");
//
//		// 2.7 对外投资
//		ResponseMessage<EntInvest> entInvestList = TaurusApiService.getEntInvestList(companyName,0,10);
//		json = mapper.writeValueAsString(entInvestList);
//		System.out.println(json);
//		System.err.println(entInvestList.getTotalSize()+"： 对外投资--------------------------------------------------------------------");
//
//		// 2.4 主要人员信息
//		ResponseMessage<EntMember> entMemberList = TaurusApiService.getEntMemberList(companyName,0,10);
//		json = mapper.writeValueAsString(entMemberList);
//		System.out.println(json);
//		System.err.println(entMemberList.getTotalSize()+"： 主要人员信息--------------------------------------------------------------------");
//
//		// 2.2 股东信息
//		ResponseMessage<EntShareholder> entShareholderList = TaurusApiService.getEntShareholderList(companyName,0,10);
//		json = mapper.writeValueAsString(entShareholderList);
//		System.out.println(json);
//		System.err.println(entShareholderList.getTotalSize()+"： 股东信息--------------------------------------------------------------------");
//
//		// 2.10 ICP信息
//		ResponseMessage<IcpInfo> icpInfoList = TaurusApiService.getIcpInfoList(companyName,0,10);
//		json = mapper.writeValueAsString(icpInfoList);
//		System.out.println(json);
//		System.err.println(icpInfoList.getTotalSize()+"： ICP信息--------------------------------------------------------------------");
//
//		// 2.3 法院判决
//		ResponseMessage<Judgment> judgmentList = TaurusApiService.getJudgmentList(companyName,0,10);
//		json = mapper.writeValueAsString(judgmentList);
//		System.out.println(json);
//		System.err.println(judgmentList.getTotalSize()+"： 法院判决--------------------------------------------------------------------");
//
//		// 2.11 专利信息
//		ResponseMessage<Patents> patentsInfoList = TaurusApiService.getPatentsList(companyName,0,10);
//		json = mapper.writeValueAsString(patentsInfoList);
//		System.out.println(json);
//		System.err.println(patentsInfoList.getTotalSize()+"： 专利信息--------------------------------------------------------------------");
//
//		// 2.13 商标
//		ResponseMessage<Tm> tmList = TaurusApiService.getTmList(companyName,0,10);
//		json = mapper.writeValueAsString(tmList);
//		System.out.println(json);
//		System.err.println(tmList.getTotalSize()+"：商标--------------------------------------------------------------------");
//
//		// 2.14 作品著作权
//		ResponseMessage<WorksCopyright> worksCopyrightList = TaurusApiService.getWorksCopyrightList(companyName,0,10);
//		json = mapper.writeValueAsString(worksCopyrightList);
//		System.out.println(json);
//		System.err.println(worksCopyrightList.getTotalSize()+"：作品著作权--------------------------------------------------------------------");
//
//		// 2.17 商标详情
//		Tmdesc  tmdesc= TaurusApiService.getTmdesc(18088745);
//		json = mapper.writeValueAsString(tmdesc);
//		System.out.println(json);
//		System.err.println("商标详情--------------------------------------------------------------------");
//
//		// 2.18 专利详情
//		PatentDesc patentDesc = TaurusApiService.getPatentDesc("02124d88087b456e953a595d6512229c");
//		json = mapper.writeValueAsString(patentDesc);
//		System.out.println(json);
//		System.err.println("专利详情--------------------------------------------------------------------");
//		
//		// 2.19 商标流程
//		ResponseMessage<Tmflow> tmflowList = TaurusApiService.getTmflowList(18088745,0,10);
//		json = mapper.writeValueAsString(tmflowList);
//		System.out.println(json);
//		System.err.println(tmflowList.getTotalSize()+"：商标流程--------------------------------------------------------------------");
////		
//		// 2.20 年报股东出资
//		ResponseMessage<ReportShareholder> reportShareholderList = TaurusApiService.getReportShareholderList(1622353975,0,10);
//		json = mapper.writeValueAsString(reportShareholderList);
//		System.out.println(json);
//		System.err.println(reportShareholderList.getTotalSize()+"：年报股东出资--------------------------------------------------------------------");
//		
//		// 2.21 年报对外投资
//		ResponseMessage<ReportOutboundInvestment> reportOutboundInvestmentList = TaurusApiService.getReportOutboundInvestmentList(1622353975,0,10);
//		json = mapper.writeValueAsString(reportOutboundInvestmentList);
//		System.out.println(json);
//		System.err.println(reportOutboundInvestmentList.getTotalSize()+"：年报对外投资--------------------------------------------------------------------");
//		
//		// 2.22 年报网站信息
//		ResponseMessage<ReportWebInfo> reportWebInfoList = TaurusApiService.getReportWebInfoList(1622353975,0,10);
//		json = mapper.writeValueAsString(TaurusApiService.getReportWebInfoList(1622353975,1,10));
//		System.out.println(json);
//		System.err.println(reportWebInfoList.getTotalSize()+"：年报网站信息--------------------------------------------------------------------");
//		
//		// 2.23 模糊查询公司信息
//		ResponseMessage<Company> entList = TaurusApiService.getEntList("百度",0,10);
//		json = mapper.writeValueAsString(entList);
//		System.out.println(json);
//		System.err.println(entList.getTotalSize()+"：模糊查询公司信息--------------------------------------------------------------------");
		
		//经营异常
		ResponseMessage<CompanyAbnormal> abnormalList = TaurusApiService.getAbnormalList("吉林省筑森林业有限公司",0,10);
		json = mapper.writeValueAsString(abnormalList);
		System.out.println(json);
		System.err.println(abnormalList.getTotalSize()+"：经营异常--------------------------------------------------------------------");
		
		//股权出质
		ResponseMessage<CompanyEquity> equityList = TaurusApiService.getEquityList("北京百度网讯科技有限公司",0,10);
		json = mapper.writeValueAsString(equityList);
		System.out.println(json);
		System.err.println(equityList.getTotalSize()+"：股权出质--------------------------------------------------------------------");
		
		
		//动产抵押
		ResponseMessage<CompanyMortgage> mortgageList = TaurusApiService.getMortgageList("克山县裕民玉米种植专业合作社",0,10);
		json = mapper.writeValueAsString(mortgageList);
		System.out.println(json);
		System.err.println(mortgageList.getTotalSize()+"：动产抵押--------------------------------------------------------------------");
		
		
		//法院公告
		ResponseMessage<CourtAnnouncement> courtAnnouncementList = TaurusApiService.getCourtAnnouncementList("北京臻香思餐饮管理有限公司",0,10);
		json = mapper.writeValueAsString(courtAnnouncementList);
		System.out.println(json);
		System.err.println(courtAnnouncementList.getTotalSize()+"：法院公告--------------------------------------------------------------------");
	}

}