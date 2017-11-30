package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.PackagePriceMap;
import com.gongsibao.product.base.IPackagePriceMapService;

@Service
public class PackagePriceMapService extends PersistableService<PackagePriceMap> implements IPackagePriceMapService {

    public PackagePriceMapService(){
        super();
        this.type=PackagePriceMap.class;
    }
}