package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.Shareholder;
import com.gongsibao.igirl.ic.base.IcShareholderService;
import org.netsharp.communication.Service;

@Service
public class ShareholderService extends GsbPersistableService<Shareholder> implements IcShareholderService {
    public ShareholderService() {
        super();
        this.type = Shareholder.class;
    }
}
