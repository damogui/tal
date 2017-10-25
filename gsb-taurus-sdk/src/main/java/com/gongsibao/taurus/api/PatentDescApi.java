package com.gongsibao.taurus.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gongsibao.taurus.message.response.PatentDescResponseMessage;
import com.gongsibao.taurus.util.ConfigHelper;
import com.gongsibao.taurus.util.MD5Util;

public class PatentDescApi extends AbstractApi<PatentDescResponseMessage>{

	private String patentId;
 
	public String getPatentId() {
		return patentId;
	}

	public void setPatentId(String patentId) {
		this.patentId = patentId;
	}

	@Override
	protected List<String> getParameters() {
		
		String nowTime = shortFormate.format(new Date());
		List<String> parameters = new ArrayList<String>();
		parameters.add("patentId="+patentId);
		parameters.add("currentTime=" + nowTime);
		parameters.add("appKey=" + ConfigHelper.APP_KEY);
		
		String origin = nowTime + ConfigHelper.APP_KEY + ConfigHelper.APP_SIGN + patentId;
		String token = MD5Util.MD5Encode(origin, "UTF-8");
		parameters.add("token=" + token);
		return parameters;
	}
	
	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pagePatentdesc";
	}

	@Override
	public Class<?> getResponseType() {

		return PatentDescResponseMessage.class;
	}

}
