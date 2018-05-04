package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.baseinfo.IcBaseInfo;
import com.gongsibao.igirl.ic.base.IBaseInfoService;
import org.netsharp.communication.Service;

@Service
public class BaseInfoService extends GsbPersistableService<IcBaseInfo> implements IBaseInfoService {
    public BaseInfoService() {
        super();
        this.type = IcBaseInfo.class;
    }
}
