package com.gongsibao.igirl.tm.web;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.annotation.Authorization;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.igirl.res.ConvertToOrderResult;
import com.gongsibao.entity.igirl.tm.DownloadAttachment;
import com.gongsibao.entity.igirl.tm.TradeMark;
import com.gongsibao.entity.igirl.tm.TradeMarkCase;
import com.gongsibao.entity.igirl.tm.UploadAttachment;
import com.gongsibao.igirl.tm.base.IDownloadAttachmentService;
import com.gongsibao.igirl.tm.base.ITradeMarkCaseService;
import com.gongsibao.igirl.tm.base.ITradeMarkService;
import com.gongsibao.igirl.tm.base.IUploadAttachmentService;
import com.gongsibao.igirl.tm.dto.AbnormalNoticeDto;
import com.gongsibao.igirl.tm.dto.CompanyDto;
import com.gongsibao.igirl.tm.dto.ResultDto;
import com.gongsibao.igirl.tm.dto.SysAttachmentDto;
import com.gongsibao.igirl.tm.dto.TradeMarkDto;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.service.TaurusApiService;
import com.gongsibao.utils.SupplierSessionManager;

public class TradeMarkCasePart extends FormPart {
	ITradeMarkCaseService tradeMarkCaseService = ServiceFactory.create(ITradeMarkCaseService.class);
	ITradeMarkService tradeMarkService = ServiceFactory.create(ITradeMarkService.class);
	IUploadAttachmentService uploadAttachmentService = ServiceFactory.create(IUploadAttachmentService.class);

	@Override
	public IPersistable save(IPersistable entity) {
		// TODO Auto-generated method stub
		//this.getContext().getWorkspace().ge
		//鑾峰彇褰撳墠鐨勫煙鍚�
		TradeMarkCase entity1 = (TradeMarkCase) entity;
		Integer departmentId = SupplierSessionManager.getDepartmentId();
		Integer sid = SupplierSessionManager.getSupplierId();
		//判断服务商id是否相同
		if(sid!=null && sid!=-1){
			if(SessionManager.getUserId().equals(entity1.getOwnerId())) {
				entity1.setSupplierId(sid);
			}else {
				entity1.setSupplierId(entity1.getSupplierId());
			}
		}
		//判断部门Id是否相同
		if(departmentId!=null && departmentId!=-1) {
			if(departmentId.equals(entity1.getDepartmentId())) {
				entity1.setDepartmentId(departmentId);
			}else {
				entity1.setDepartmentId(entity1.getDepartmentId());
			}
		}
		if (entity1.getEntityState() == EntityState.New) {
			entity1.setCode(DateManage.toString(new Date(),"yyyyMMddHHmmss"));
			String urlstr = this.fetchQrCodeUrl(entity1.getCode());
			entity1.setTokenImgUrl(urlstr);
		}
		return super.save(entity1);
	}

	public CompanyDto fetchCompanyByName(String name) {
		try {
			ResponseMessage<com.gongsibao.taurus.entity.CompanyInfo> cms = TaurusApiService.getCompanyListByKey(name, 0, 10);
			if (cms != null) {
				if (cms.getResult() == 0) {
					return null;
				} else {
					com.gongsibao.taurus.entity.CompanyInfo cm = cms.getList().get(0);
					String cmname = cm.getName();
					if (!StringManager.isNullOrEmpty(cmname)) {
						//EntRegistry er=TaurusApiService.getEntRegistry(cmname);
						CompanyDto cp = new CompanyDto();
						cp.setAppCnName(cm.getName());
						cp.setAppCnAddr(cm.getRegLocation());
						cp.setCertCode(cm.getProperty1());
						cp.setApplyer(cm.getLegalPersonName());
						cp.setPostcode("");
						cp.setFax("");
						return cp;
					} else {
						return null;
					}
				}
			} else {
				return new CompanyDto();
			}

		} catch (Exception e) {
			return new CompanyDto();
		}
	}

	public String fetchQrCodeUrl(String casecode) {
		String url = HttpContext.getCurrent().getRequest().getRequestURL().replace("panda/rest/service", "");
		return tradeMarkCaseService.fetchQrCodeUrl(url, casecode);

	}

	@Authorization(is = false)
	public TradeMarkCase fetchUnconfirmedCaseInfoByCode(String code) {
		Oql oql = new Oql();
		{
			oql.setType(TradeMarkCase.class);
			oql.setSelects("TradeMarkCase.*,TradeMarkCase.tradeMarks.*,TradeMarkCase.uploadAttachments.*,TradeMarkCase.downLoadAttaments.*");
			oql.setFilter("code=?");
			oql.getParameters().add("code", code, Types.VARCHAR);
		}
		TradeMarkCase tmc = tradeMarkCaseService.queryFirst(oql);
		return tmc;
	}

	@SuppressWarnings("rawtypes")
	@Authorization(is = false)
	public ResultDto denyAdvice(String caseid, String advice) {
		int rtn = tradeMarkCaseService.denyAdvice(caseid, advice);
		return ResultDto.getSimpleResultDto(rtn);
	}

	@SuppressWarnings("rawtypes")
	@Authorization(is = false)
	public ResultDto confirmCase(String caseid) {
		int rtn = tradeMarkCaseService.confirmCase(caseid);
		return ResultDto.getSimpleResultDto(rtn);

	}

	IUploadAttachmentService up = ServiceFactory.create(IUploadAttachmentService.class);
	IDownloadAttachmentService down = ServiceFactory.create(IDownloadAttachmentService.class);

	@SuppressWarnings("rawtypes")
	@Authorization(is = false)
	public ResultDto updateAttachment(String id, String filepath) {
		int rtn = up.uploadAttachmentFileurl(id, filepath);
		return ResultDto.getSimpleResultDto(rtn);
	}


	@SuppressWarnings("rawtypes")
	@Authorization(is = false)
	public ResultDto updateDownloadAttachment(String upid, String filepath) {
		//鍙傛暟鏄笂浼犲浘鏍烽檮浠剁殑id,鑾峰彇涓婁紶闄勪欢锛岃幏鍙朿aseid鍜宯ame,鑾峰彇memo,鎸夌収caseid,memo+"_xxxxx"鐨勬柟寮忓幓鏇存柊寰呬笅杞介檮浠�
		int rtn = down.updateDownloadDeleProofAttachmentFileurl(upid, filepath);
		return ResultDto.getSimpleResultDto(rtn);
	}

	@SuppressWarnings("rawtypes")
	@Authorization(is = false)
	public ResultDto findAllAttachmentsByCaseId(String caseid) {
		List<UploadAttachment> ups = up.findAllAttachmentsByCaseId(caseid);
		return ResultDto.getEntityListResultDto(ups);
	}

	@SuppressWarnings("rawtypes")
	@Authorization(is = false)
	public ResultDto findDownAttachmentsByCaseId(String caseid) {
		List<DownloadAttachment> ups = down.findDownAttachmentsByCaseId(caseid);
		return ResultDto.getEntityListResultDto(ups);
	}


	@SuppressWarnings("rawtypes")
	@Authorization(is = false)
	public ResultDto fetchCaseState(String casecode) {
		int st = tradeMarkCaseService.fetchCaseState(casecode);
		System.out.println(st);
		return ResultDto.getSimpleResultDto(st);
	}

	@SuppressWarnings("rawtypes")
	@Authorization(is = false)
	public ResultDto updateCaseState(String casecode, int state) {
		int st = tradeMarkCaseService.updateCaseState(casecode, state);
		return ResultDto.getSimpleResultDto(st);
	}

	@SuppressWarnings("rawtypes")
	@Authorization(is = false)
	public ResultDto isAllUpload(int caseId) {
		int ua = uploadAttachmentService.isAllUpload(caseId);
		return ResultDto.getSimpleResultDto(ua);//
	}

	public int attachmentMake(String caseid) {
		//
		return tradeMarkCaseService.attachmentMake(caseid);
	}

	@SuppressWarnings("rawtypes")
	@Authorization(is=false)
	public ResultDto findTradeMarksByCode(String  caseCode) {
		List<TradeMark> tradeMarks=tradeMarkCaseService.findTradeMarksByCode(caseCode);
		List<TradeMarkDto> tmds = new ArrayList<>();
		for(TradeMark tm :tradeMarks) {
			TradeMarkDto tmd =new TradeMarkDto();
			tmd.setTmId(tm.getId());
			tmd.setTmName(tm.getMemo()+":"+tm.getNclOne().getCode()+"类");
			tmd.setTmState(tm.getMarkState().getText());
			tmds.add(tmd);
		}
		return ResultDto.getEntityListResultDto(tmds);
	}
	@SuppressWarnings("rawtypes")
	@Authorization(is=false)
	public ResultDto findUrlByCaseId(int  caseId) {
		List<SysAttachmentDto> urlList=tradeMarkService.findUrlById(caseId);
		return ResultDto.getEntityListResultDto(urlList);//
	}

	@Authorization(is = false)
	public ResultDto convertToOrder(String caseid) {
		ConvertToOrderResult result = tradeMarkCaseService.convertToOrder(caseid);
		return ResultDto.getConvertToOrderResultDto(result);
	}

	@Authorization(is = false)
	public ResultDto convertToOrderConfirm(String caseid, String orderNo) {
		ConvertToOrderResult result = tradeMarkCaseService.convertToOrder(caseid, orderNo);
		return ResultDto.getConvertToOrderResultDto(result);
	}
	
//	@SuppressWarnings("rawtypes")
//	@Authorization(is=false)
//	public ResultDto getAbnormalNotice() {
//		String a ="'{userId}'";
//		System.out.println(a);
//		return null;//
//	}

	@SuppressWarnings("rawtypes")
	@Authorization(is=false)
	public ResultDto getAbnormalNotice() {
		List<AbnormalNoticeDto> noticeList=tradeMarkService.getAbnormalNotice();
		return ResultDto.getEntityListResultDto(noticeList);
	}
	
}
