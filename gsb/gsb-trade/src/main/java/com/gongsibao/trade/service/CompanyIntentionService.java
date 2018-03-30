package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.Oql;
import org.netsharp.core.Row;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.trade.base.ICompanyIntentionService;

@Service
public class CompanyIntentionService extends PersistableService<CompanyIntention> implements ICompanyIntentionService {

    public CompanyIntentionService() {
        super();
        this.type = CompanyIntention.class;
    }

    @Override
    public Map<Integer, String> getCompanyByOrderIdList(List<Integer> orderIdList) {

        StringBuffer sql = new StringBuffer();
        Map<Integer, String> resMap = new HashMap<Integer, String>();

        if (CollectionUtils.isEmpty(orderIdList)) {
            return resMap;
        }

        String orderIds = StringManager.join(",", orderIdList);

        sql.append("SELECT oi.`pkid` 'orderId', (CASE WHEN cri.company_name!='' THEN cri.company_name WHEN cri.`full_name`!='' THEN cri.`full_name` WHEN cri.`name`!='' THEN cri.`name`   ");
        sql.append("ELSE GROUP_CONCAT(DISTINCT(CASE WHEN cri1.company_name!='' THEN cri1.company_name WHEN cri1.`full_name`!='' THEN cri1.`full_name` ELSE cri.`name` END) SEPARATOR ',') END) 'companyName' ");
        sql.append("FROM so_order oi ");
        sql.append("JOIN so_order_prod od ON oi.`pkid` = od.`order_id` ");
        sql.append("LEFT JOIN crm_company_intention cri ON cri.pkid = oi.`company_id`  ");
        sql.append("LEFT JOIN crm_company_intention cri1 ON cri1.pkid = od.`company_id`   ");
        sql.append("WHERE oi.`pkid` IN(" + orderIds + ")  ");
        sql.append("GROUP BY oi.`pkid`   ");

        DataTable executeTable = this.pm.executeTable(sql.toString(), null);
        for (Row row : executeTable) {
            resMap.put(row.getInteger("orderId"), row.getString("companyName"));
        }

        return resMap;
    }


    @Override
    public CompanyIntention getByCompanyName(String companyName) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("companyName = ? and is_delete =0 ");
            oql.getParameters().add("companyName", companyName, Types.VARCHAR);
        }
        CompanyIntention companyIntention = this.pm.queryFirst(oql);
        return companyIntention;
    }
}