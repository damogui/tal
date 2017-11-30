package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.DishonestInfoResponseMessage;

public class DishonestInfoApi extends AbstractApi<DishonestInfoResponseMessage>{

	@Override
	public String getUrl() {

		return "/data/api/companyInfo/pageDishonestInfo";
	}

	@Override
	public Class<?> getResponseType() {

		return DishonestInfoResponseMessage.class;
	}

}
