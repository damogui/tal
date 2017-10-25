package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.IGuestVoiceService;
import com.gongsibao.entity.cms.GuestVoice;

@Service
public class GuestVoiceService extends PersistableService<GuestVoice> implements IGuestVoiceService {

    public GuestVoiceService(){
        super();
        this.type=GuestVoice.class;
    }
}