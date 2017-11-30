package com.gongsibao.bd.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.bd.base.IConfigService;
import com.gongsibao.entity.bd.Config;

@Service
public class ConfigService extends PersistableService<Config> implements IConfigService {

    public ConfigService(){
        super();
        this.type=Config.class;
    }
}