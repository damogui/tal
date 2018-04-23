package com.gongsibao.crm.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.crm.CustomerCompanyMap;

import java.util.List;
import java.util.Map;

public interface ICustomerCompanyMapService extends IPersistableService<CustomerCompanyMap> {

    Map<Integer,String> getCompanyNameByCustomerIdList(List<Integer> customerIdList);
}