package com.gongsibao.igirl.web;
import java.sql.Types;
import java.util.List;

import org.joda.time.DateTime;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.annotation.Authorization;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.igirl.DownloadAttachment;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.igirl.base.IDownloadAttachmentService;
import com.gongsibao.igirl.base.ITradeMarkCaseService;
import com.gongsibao.igirl.base.ITradeMarkService;
import com.gongsibao.igirl.base.IUploadAttachmentService;
import com.gongsibao.igirl.dto.CompanyDto;
import com.gongsibao.igirl.dto.ResultDto;
import com.gongsibao.taurus.entity.EntRegistry;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.service.TaurusApiService;
import com.gongsibao.utils.SupplierSessionManager;
public class TradeMarkCasePart extends FormPart {
     ITradeMarkCaseService tradeMarkCaseService = ServiceFactory.create(ITradeMarkCaseService.class);
	ITradeMarkService tradeMarkService = ServiceFactory.create(ITradeMarkService.class);

	@Override
	public IPersistable save(IPersistable entity) {
		// TODO Auto-generated method stub
		//this.getContext().getWorkspace().ge
		//获取当前的域名
		TradeMarkCase entity1=(TradeMarkCase)entity;
		Integer departmentId = SupplierSessionManager.getDepartmentId();
		entity1.setDepartmentId(departmentId);	
		if(entity1.getEntityState()==EntityState.New) {
			entity1.setCode(DateTime.now().toString("yyyyMMddHHmmss"));
			String urlstr=this.fetchQrCodeUrl(entity1.getCode());
			entity1.setTokenImgUrl(urlstr);
		}
		return super.save(entity1);
	}
	public CompanyDto fetchCompanyByName(String name) {
		try {
			ResponseMessage<com.gongsibao.taurus.entity.CompanyInfo> cms=TaurusApiService.getCompanyListByKey(name, 0, 10);
			if(cms!=null) {
				if(cms.getResult()==0) {
					return null;
				}else {
					com.gongsibao.taurus.entity.CompanyInfo cm=cms.getList().get(0);
					String cmname=cm.getName();
					if(!StringManager.isNullOrEmpty(cmname)) {
						//EntRegistry er=TaurusApiService.getEntRegistry(cmname);
						CompanyDto cp=new CompanyDto();
						cp.setAppCnName(cm.getName());
						cp.setAppCnAddr(cm.getRegLocation());
						cp.setCertCode(cm.getRegNumber());
						cp.setApplyer(cm.getLegalPersonName());
						cp.setPostcode("");
						cp.setFax("");
						return cp;
					}else {
						return null;
					}
				}
			}else {
				return new CompanyDto();
			}
			
		}catch(Exception e) {
			return new CompanyDto();
		}
	}
	public String fetchQrCodeUrl(String casecode) {
		String url=HttpContext.getCurrent().getRequest().getRequestURL().replace("panda/rest/service", "");
		return tradeMarkCaseService.fetchQrCodeUrl(url,casecode);
		
	}
	@Authorization(is=false)
	public TradeMarkCase fetchUnconfirmedCaseInfoByCode(String code) {
		Oql oql=new Oql();
		{
			oql.setType(TradeMarkCase.class);
			oql.setSelects("TradeMarkCase.*,TradeMarkCase.tradeMarks.*,TradeMarkCase.uploadAttachments.*,TradeMarkCase.downLoadAttaments.*");
			oql.setFilter("code=?");
			oql.getParameters().add("code",code,Types.VARCHAR);
		}
		TradeMarkCase tmc=tradeMarkCaseService.queryFirst(oql);
		return tmc;
	}
	@SuppressWarnings("rawtypes")
	@Authorization(is=false)
	public ResultDto denyAdvice(String caseid,String advice) {
		int rtn=tradeMarkCaseService.denyAdvice(caseid, advice);
		return ResultDto.getSimpleResultDto(rtn);
	}
	@SuppressWarnings("rawtypes")
	@Authorization(is=false)
	public ResultDto confirmCase(String caseid) {
		int rtn=tradeMarkCaseService.confirmCase(caseid);
		return ResultDto.getSimpleResultDto(rtn);
 
	}
	IUploadAttachmentService up=ServiceFactory.create(IUploadAttachmentService.class);
	IDownloadAttachmentService down=ServiceFactory.create(IDownloadAttachmentService.class);
	@SuppressWarnings("rawtypes")
	@Authorization(is=false)
	public ResultDto updateAttachment(String id,String filepath) {
		int rtn=up.uploadAttachmentFileurl(id, filepath);
		return ResultDto.getSimpleResultDto(rtn);
	}
	
	
	@SuppressWarnings("rawtypes")
	@Authorization(is=false)
	public ResultDto updateDownloadAttachment(String upid,String filepath) {
		//参数是上传图样附件的id,获取上传附件，获取caseid和name,获取memo,按照caseid,memo+"_xxxxx"的方式去更新待下载附件
		int rtn=down.updateDownloadDeleProofAttachmentFileurl(upid, filepath);
		return ResultDto.getSimpleResultDto(rtn);
	}
	
	@SuppressWarnings("rawtypes")
	@Authorization(is=false)
	public ResultDto findAllAttachmentsByCaseId(String caseid) {
		List<UploadAttachment> ups=up.findAllAttachmentsByCaseId(caseid);
		return ResultDto.getEntityListResultDto(ups);
	}
	
	@SuppressWarnings("rawtypes")
	@Authorization(is=false)
	public ResultDto findDownAttachmentsByCaseId(String caseid) {
		List<DownloadAttachment> ups=down.findDownAttachmentsByCaseId(caseid);
		return ResultDto.getEntityListResultDto(ups);
	}
	
	
	@SuppressWarnings("rawtypes")
	@Authorization(is=false)
	public ResultDto fetchCaseState(String casecode) {
		int st=tradeMarkCaseService.fetchCaseState(casecode);
		System.out.println(st);
		return ResultDto.getSimpleResultDto(st);
	}
	
	@SuppressWarnings("rawtypes")
	@Authorization(is=false)
	public ResultDto updateCaseState(String casecode,int state) {
		int st=tradeMarkCaseService.updateCaseState(casecode,state);
		return ResultDto.getSimpleResultDto(st);
	}
	
	public int attachmentMake(String caseid) {
		return tradeMarkCaseService.attachmentMake(caseid);
	}
}
