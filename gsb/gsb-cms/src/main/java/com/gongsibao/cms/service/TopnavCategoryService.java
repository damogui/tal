package com.gongsibao.cms.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cms.base.ITopnavCategoryService;
import com.gongsibao.entity.cms.TopnavCategory;

@Service
public class TopnavCategoryService extends PersistableService<TopnavCategory> implements ITopnavCategoryService {

    public TopnavCategoryService(){
        super();
        this.type=TopnavCategory.class;
    }
}