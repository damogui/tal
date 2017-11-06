package com.gongsibao.taurus.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gongsibao.taurus.message.response.TmdescResponseMessage;
import com.gongsibao.taurus.util.ConfigHelper;
import com.gongsibao.taurus.util.MD5Util;

public class TmdescApi extends AbstractApi<TmdescResponseMessage>{

	private int tmId;
	
	@Override
	protected List<String> getParameters() {

		String nowTime = shortFormate.format(new Date());
		List<String> parameters = new ArrayList<String>();
		parameters.add("tmId="+tmId);
		parameters.add("currentTime=" + nowTime);
		parameters.add("appKey=" + ConfigHelper.APP_KEY );
		String origin = nowTime + ConfigHelper.APP_KEY  + ConfigHelper.APP_SIGN  + tmId;
		String token = MD5Util.MD5Encode(origin, "UTF-8");
		parameters.add("token=" + token);
		return parameters;
	}
	
	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pageTmdesc";
	}

	@Override
	public Class<?> getResponseType() {

		return TmdescResponseMessage.class;
	}

	public int getTmId() {
		return tmId;
	}

	public void setTmId(int tmId) {
		this.tmId = tmId;
	}
}
