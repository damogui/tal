package com.gongsibao.dingtalkrobot.service;

import com.gongsibao.dingtalkrobot.base.ICustomerService;
import com.gongsibao.entity.crm.NCustomer;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import java.sql.Types;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService extends PersistableService<NCustomer> implements ICustomerService {
    @Override
    public Map<Integer, NCustomer> findMapByAccountIds(List<Integer> accountIdList) {
        return null;
    }

    @Override
    public NCustomer getByAccount(Integer accountId) {
        Oql oql = new Oql();
        {
            oql.setType(NCustomer.class);
            oql.setSelects("*");
            oql.setFilter("account_id = ?");
            oql.getParameters().add("accountId", accountId, Types.INTEGER);
        }
        return this.pm.queryFirst(oql);
    }
}
