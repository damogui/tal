package com.gongsibao.crm.service;

import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.crm.base.ICustomerCompanyMapService;
import com.gongsibao.entity.crm.CustomerCompanyMap;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.util.StringManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerCompanyMapService extends GsbPersistableService<CustomerCompanyMap> implements ICustomerCompanyMapService {

    public CustomerCompanyMapService() {
        super();
        this.type = CustomerCompanyMap.class;
    }

    @Override
    public Map<Integer, String> getCompanyNameByCustomerIdList(List<Integer> customerIdList) {
        Map<Integer, String> resMap = new HashMap<>();
        StringBuffer sql = new StringBuffer();
        if (CollectionUtils.isEmpty(customerIdList)) {
            return resMap;
        }
        String customerIds = StringManager.join(",", customerIdList);
        sql.append("SELECT ccm.customer_id 'customerId', GROUP_CONCAT( DISTINCT cri.company_name ORDER BY ccm.company_id DESC) 'companyName' FROM crm_customer_company_map ccm ");
        sql.append("JOIN crm_company_intention cri ON ccm.company_id = cri.pkid ");
        sql.append("WHERE ccm.customer_id IN (" + customerIds + ") ");
        sql.append("GROUP BY ccm.customer_id ");
        DataTable rows = this.pm.executeTable(sql.toString(), null);

        for (IRow row : rows) {
            Integer customerId = NumberUtils.toInt(row.getString("customerId"));
            String companyName = row.getString("companyName");
            resMap.put(customerId, companyName);
        }

        return resMap;
    }
}