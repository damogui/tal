package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IBannerService;
import com.gongsibao.entity.cms.Banner;

@Service
public class BannerService extends PersistableService<Banner> implements IBannerService {

    public BannerService(){
        super();
        this.type=Banner.class;
    }
}