package com.gongsibao.rest.service.customer;


import com.gongsibao.entity.crm.Customer;

public interface ICustomerService {

    Customer byAccountId(int accountId);
}
