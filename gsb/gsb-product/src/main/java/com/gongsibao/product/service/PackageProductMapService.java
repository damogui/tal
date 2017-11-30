package com.gongsibao.product.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.product.PackageProductMap;
import com.gongsibao.product.base.IPackageProductMapService;

@Service
public class PackageProductMapService extends PersistableService<PackageProductMap> implements IPackageProductMapService {

    public PackageProductMapService(){
        super();
        this.type=PackageProductMap.class;
    }
}