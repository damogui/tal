package com.gongsibao.uc.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.SupplerProd;
import com.gongsibao.uc.base.ISupplerProdService;

@Service
public class SupplerProdService extends PersistableService<SupplerProd> implements ISupplerProdService {

    public SupplerProdService(){
        super();
        this.type=SupplerProd.class;
    }
}