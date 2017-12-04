package com.gongsibao.franchisee.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.franchisee.FranchiseeReport;
import com.gongsibao.franchisee.base.IFranchiseeReportService;

@Service
public class FranchiseeReportService extends PersistableService<FranchiseeReport> implements IFranchiseeReportService {

    public FranchiseeReportService(){
        super();
        this.type=FranchiseeReport.class;
    }
}