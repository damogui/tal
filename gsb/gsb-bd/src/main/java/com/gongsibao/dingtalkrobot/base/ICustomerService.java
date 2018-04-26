package com.gongsibao.dingtalkrobot.base;

import com.gongsibao.entity.crm.NCustomer;
import org.netsharp.base.IPersistableService;

import java.util.List;
import java.util.Map;

public interface ICustomerService extends IPersistableService<NCustomer> {

    Map<Integer, NCustomer> findMapByAccountIds(List<Integer> accountIdList);
}
