package com.gongsibao.trade.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.crm.CustomerCompanyMap;
import com.gongsibao.trade.base.ICustomerCompanyMapService;

/**
 * Created by zhangchao on 2018/3/30.
 */
@Service
public class CustomerCompanyMapService extends PersistableService<CustomerCompanyMap> implements ICustomerCompanyMapService {
}
