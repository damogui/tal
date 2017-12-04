package com.gongsibao.yj.service;

import com.gongsibao.entity.yj.CompanyChangeRecord;
import com.gongsibao.yj.base.ICompanyChangeRecordService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

@Service
public class CompanyChangeRecordService extends PersistableService<CompanyChangeRecord> implements ICompanyChangeRecordService {

    public CompanyChangeRecordService(){
        super();
        this.type=CompanyChangeRecord.class;
    }
}