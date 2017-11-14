package com.gongsibao.taurus.service;

import org.netsharp.communication.Service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.taurus.UserCompanyIndustry;
import com.gongsibao.taurus.base.IUserCompanyIndustryService;

@Service
public class UserCompanyIndustryService extends GsbPersistableService<UserCompanyIndustry> implements IUserCompanyIndustryService {

	public UserCompanyIndustryService() {
		super();
		this.type = UserCompanyIndustry.class;
	}
}
