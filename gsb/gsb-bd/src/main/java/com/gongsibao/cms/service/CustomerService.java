package com.gongsibao.cms.service;

import com.gongsibao.cms.base.ICustomerService;
import com.gongsibao.entity.crm.NCustomer;
import org.netsharp.service.PersistableService;

import java.util.List;
import java.util.Map;

public class CustomerService extends PersistableService<NCustomer> implements ICustomerService {
    @Override
    public Map<Integer, NCustomer> findMapByAccountIds(List<Integer> accountIdList) {
        return null;
    }
}
