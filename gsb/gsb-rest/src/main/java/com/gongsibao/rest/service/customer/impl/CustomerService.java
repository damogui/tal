package com.gongsibao.rest.service.customer.impl;

import com.gongsibao.entity.crm.Customer;
import com.gongsibao.rest.service.customer.ICustomerService;
import org.netsharp.communication.ServiceFactory;
import org.springframework.stereotype.Service;

/**
 * ClassName: $
 *
 * @author wangkun <wangkun@gongsibao.com>
 * @Description: TODO
 * @date $ $
 */
@Service
public class CustomerService implements ICustomerService {
    // 客户服务
    com.gongsibao.trade.base.ICustomerService baseCustomerService = ServiceFactory.create(com.gongsibao.trade.base.ICustomerService.class);


    @Override
    public Customer byAccountId(int accountId) {
        return baseCustomerService.byAccountId(accountId);
    }
}
