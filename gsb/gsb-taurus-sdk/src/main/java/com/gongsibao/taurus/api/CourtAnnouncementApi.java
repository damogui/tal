package com.gongsibao.taurus.api;

import com.gongsibao.taurus.message.response.CourtAnnouncementResponseMessage;

public class CourtAnnouncementApi  extends AbstractApi<CourtAnnouncementResponseMessage>{

	@Override
	public String getUrl() {

		return "/tele/courtannouncement";
	}

	@Override
	public Class<?> getResponseType() {

		return CourtAnnouncementResponseMessage.class;
	}

}
