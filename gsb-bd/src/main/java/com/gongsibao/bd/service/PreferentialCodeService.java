package com.gongsibao.bd.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.bd.base.IPreferentialCodeService;
import com.gongsibao.entity.bd.PreferentialCode;

@Service
public class PreferentialCodeService extends PersistableService<PreferentialCode> implements IPreferentialCodeService {

    public PreferentialCodeService(){
        super();
        this.type=PreferentialCode.class;
    }
}