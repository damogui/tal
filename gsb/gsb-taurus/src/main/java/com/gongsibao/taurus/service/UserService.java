package com.gongsibao.taurus.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.taurus.User;
import com.gongsibao.entity.taurus.UserDingtalkKeyword;
import com.gongsibao.taurus.base.IUserDingtalkKeywordService;
import com.gongsibao.taurus.base.IUserService;

@Service
public class UserService extends PersistableService< User> implements IUserService {

    public UserService(){
        super();
        this.type= User.class;
    }
    
    @Override
    public User save(User entity) {
    	
    	if(entity.getEntityState() == EntityState.New){
    		
    		User oldUser  = byMobile(entity.getMobile());
    		if(oldUser != null){
    			
    			new BusinessException("手机号已存在！");
    		}
    	}
    	
    	entity = super.save(entity);
    	return entity;
    }
    
    @Override
    public User byId(Object id) {
    	
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("User.*,User.walletLogs.*,User.collectCompanys.*");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		User user = this.queryFirst(oql);
		
		//查询舆情关键字
		IUserDingtalkKeywordService keywordService = ServiceFactory.create(IUserDingtalkKeywordService.class);
		List<UserDingtalkKeyword> keywordList = keywordService.queryList(user.getId());
		user.setDingtalkKeywords(keywordList);
		return user;
    }

	@Override
	public User byMobile(String mobile) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("mobile=?");
			oql.getParameters().add("mobile", mobile, Types.VARCHAR);
		}
		return this.queryFirst(oql);
	}
}
