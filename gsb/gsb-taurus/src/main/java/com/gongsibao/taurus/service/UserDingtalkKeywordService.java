package com.gongsibao.taurus.service;

import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.taurus.UserDingtalkKeyword;
import com.gongsibao.taurus.base.IUserDingtalkKeywordService;

@Service
public class UserDingtalkKeywordService extends PersistableService< UserDingtalkKeyword> implements IUserDingtalkKeywordService {

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
			oql.setFilter("type=1");
		}
		return this.queryList(oql);
	}
}
