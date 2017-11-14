package com.gongsibao.taurus.service;

import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.taurus.UserDingtalkKeyword;
import com.gongsibao.taurus.base.IUserDingtalkKeywordService;

@Service
public class UserDingtalkKeywordService extends GsbPersistableService< UserDingtalkKeyword> implements IUserDingtalkKeywordService {

    public UserDingtalkKeywordService(){
        super();
        this.type= UserDingtalkKeyword.class;
    }

	@Override
	public List<UserDingtalkKeyword> queryList(int userId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("type=1 and accountId="+userId);
		}
		return this.queryList(oql);
	}
}
