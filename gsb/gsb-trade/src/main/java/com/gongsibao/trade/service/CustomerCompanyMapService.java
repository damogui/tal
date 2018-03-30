package com.gongsibao.trade.service;

import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.entity.crm.CustomerCompanyMap;
import com.gongsibao.trade.base.ICompanyIntentionService;
import com.gongsibao.trade.base.ICustomerCompanyMapService;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

/**
 * Created by zhangchao on 2018/3/30.
 */
@Service
public class CustomerCompanyMapService extends PersistableService<CustomerCompanyMap> implements ICustomerCompanyMapService {
}
