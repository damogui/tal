package com.gongsibao.taurus.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.taurus.UserDingtalkKeyword;
import com.gongsibao.taurus.base.IUserDingtalkKeywordService;

@Service
public class UserDingtalkKeywordService extends PersistableService< UserDingtalkKeyword> implements IUserDingtalkKeywordService {

    public UserDingtalkKeywordService(){
        super();
        this.type= UserDingtalkKeyword.class;
    }
}
