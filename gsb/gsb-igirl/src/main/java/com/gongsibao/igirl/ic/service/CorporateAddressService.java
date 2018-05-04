package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.CorporateAddress;
import com.gongsibao.igirl.ic.base.IcCorporateAddressService;
import org.netsharp.communication.Service;

@Service
public class CorporateAddressService extends GsbPersistableService<CorporateAddress> implements IcCorporateAddressService {
    public CorporateAddressService() {
        super();
        this.type = CorporateAddress.class;
    }
}
