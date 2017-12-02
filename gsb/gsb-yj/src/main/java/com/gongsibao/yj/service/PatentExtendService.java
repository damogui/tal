package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.PatentExtend;
import com.gongsibao.yj.base.IPatentExtendService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class PatentExtendService extends PersistableService<PatentExtend> implements IPatentExtendService {

    public PatentExtendService(){
        super();
        this.type=PatentExtend.class;
    }
}