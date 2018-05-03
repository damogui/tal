package com.gongsibao.igirl.ic.base;

import com.gongsibao.entity.igirl.ic.ex.baseinfo.CompanyName;
import org.netsharp.base.IPersistableService;

public interface IcCompanyNameService extends IPersistableService<CompanyName> {
    String getName(CompanyName companyName);

    CompanyName byEntTra(String entTra);

    CompanyName updateState(Integer id);
}
