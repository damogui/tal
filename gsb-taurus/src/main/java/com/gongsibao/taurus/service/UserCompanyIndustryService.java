package com.gongsibao.taurus.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.taurus.UserCompanyIndustry;
import com.gongsibao.taurus.base.IUserCompanyIndustryService;

@Service
public class UserCompanyIndustryService extends PersistableService<UserCompanyIndustry> implements IUserCompanyIndustryService {

	public UserCompanyIndustryService() {
		super();
		this.type = UserCompanyIndustry.class;
	}
}
