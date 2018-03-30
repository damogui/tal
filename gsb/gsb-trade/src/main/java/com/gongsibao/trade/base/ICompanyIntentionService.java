package com.gongsibao.trade.base;

import java.util.List;
import java.util.Map;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.crm.CompanyIntention;

public interface ICompanyIntentionService extends IPersistableService<CompanyIntention> {

    Map<Integer, String> getCompanyByOrderIdList(List<Integer> orderIdList);

    CompanyIntention getByCompanyName(String companyName);

}
