package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IFriendLinkService;
import com.gongsibao.entity.cms.FriendLink;

@Service
public class FriendLinkService extends PersistableService<FriendLink> implements IFriendLinkService {

    public FriendLinkService(){
        super();
        this.type=FriendLink.class;
    }
}