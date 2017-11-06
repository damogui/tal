package com.gongsibao.taurus.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.taurus.UserDingtalkKeyword;

public interface IUserDingtalkKeywordService  extends IPersistableService<UserDingtalkKeyword>{

	List<UserDingtalkKeyword> queryList(int userId);
}
