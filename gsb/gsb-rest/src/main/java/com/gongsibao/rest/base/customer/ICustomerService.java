package com.gongsibao.rest.base.customer;


import com.gongsibao.entity.crm.Customer;

public interface ICustomerService {

    Customer byAccountId(int accountId);
}
