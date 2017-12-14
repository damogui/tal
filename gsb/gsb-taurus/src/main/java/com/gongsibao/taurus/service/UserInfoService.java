package com.gongsibao.taurus.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.taurus.UserInfo;
import com.gongsibao.taurus.base.IUserInfoService;

@Service
public class UserInfoService extends PersistableService<UserInfo> implements IUserInfoService {

	public UserInfoService() {
		super();
		this.type = UserInfo.class;
	}
}