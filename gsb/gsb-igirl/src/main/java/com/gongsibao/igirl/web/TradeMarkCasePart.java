package com.gongsibao.igirl.web;
import java.sql.Types;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.panda.core.HttpContext;

import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.baseinfo.IGirlConfig;
import com.gongsibao.entity.igirl.dict.ConfigType;
import com.gongsibao.igirl.base.IGirlConfigService;
import com.gongsibao.igirl.base.ITradeMarkCaseService;
import com.gongsibao.igirl.base.ITradeMarkService;
import com.gongsibao.igirl.dto.Company;
import com.gongsibao.taurus.api.ApiFactory;
import com.gongsibao.taurus.api.EntRegistryApi;
import com.gongsibao.taurus.entity.EntRegistry;
import com.gongsibao.taurus.message.ResponseMessage;
public class TradeMarkCasePart extends FormPart {
     ITradeMarkCaseService tradeMarkCaseService = ServiceFactory.create(ITradeMarkCaseService.class);
	ITradeMarkService tradeMarkService = ServiceFactory.create(ITradeMarkService.class);

	@Override
	public IPersistable save(IPersistable entity) {
		// TODO Auto-generated method stub
		//this.getContext().getWorkspace().ge
		//获取当前的域名
		TradeMarkCase entity1=(TradeMarkCase)entity;
		return super.save(entity1);
	}
	
	public Company fetchCompanyByName(String name) {
		EntRegistryApi api = ApiFactory.create(EntRegistryApi.class);
		api.setCompanyName(name);
		ResponseMessage<EntRegistry> response = api.getResponse();
		if(response == null){	
			return null;
		}else {
			List<EntRegistry> ens=  response.getList();
			if(ens.size()>0) {
				EntRegistry er=ens.get(0);
				Company cp=new Company();
				cp.setAppCnName(er.getName());
				cp.setAppCnAddr(er.getBusinessAddress());
				cp.setCertCode(er.getOrganizationCode());
				cp.setApplyer(er.getLegalRepresentative());
				cp.setPostcode("");
				cp.setFax("");
				return cp;
			}else {
				return null;
			}
		}
	}
	
	public String fetchQrCodeUrl(String mobile) {
		IGirlConfigService girlConf=ServiceFactory.create(IGirlConfigService.class);
		Oql oql=new Oql();{
			oql.setType(IGirlConfig.class);
			oql.setSelects("IGirlConfig.*");
			oql.setFilter("configType=? or configType=?");
			oql.getParameters().add("configType",ConfigType.IGIRL_QR_URL.getValue(),Types.INTEGER);
			oql.getParameters().add("configType",ConfigType.IGIRL_MOBILE_TESTURL.getValue(),Types.INTEGER);
		}
		List<IGirlConfig> configs=girlConf.queryList(oql);
		String qcurl="";
		if(configs.size()==1) {
			String url=HttpContext.getCurrent().getRequest().getRequestURL().replace("panda/rest/service", "");
			qcurl="{qrServiceUrl}/qc?detailLink= {currentDomain}/gsb/igirl/tmcase.html?mobile="+mobile;
			qcurl=qcurl.replace("{qrServiceUrl}", configs.get(0).getConfigValue()).replace("{currentDomain}", url);
		}
		if(configs.size()==2) {
			qcurl="{qrServiceUrl}/qc?detailLink= {currentDomain}/gsb/igirl/tmcase.html?mobile="+mobile;
			qcurl=qcurl.replace("{qrServiceUrl}", configs.get(0).getConfigValue()).replace("{currentDomain}", configs.get(1).getConfigValue());
		}
		return qcurl;
	}
}
