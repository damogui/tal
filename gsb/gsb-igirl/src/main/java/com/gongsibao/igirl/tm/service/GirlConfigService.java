package com.gongsibao.igirl.tm.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.tm.baseinfo.IGirlConfig;
import com.gongsibao.igirl.tm.base.IGirlConfigService;
import org.netsharp.communication.Service;

@Service
public class GirlConfigService extends GsbPersistableService<IGirlConfig> implements IGirlConfigService {
    public GirlConfigService() {
        super();
        this.type = IGirlConfig.class;
    }
}
