package com.gongsibao.crm.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

import com.gongsibao.crm.base.ICustomerCompanyMapService;
import com.gongsibao.utils.NumberUtils;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.crm.NCustomer;

public class NCustomerBaseListPart extends AdvancedListPart {

    ICustomerCompanyMapService customerCompanyMapService = ServiceFactory.create(ICustomerCompanyMapService.class);

    public String getFilterByParameter(FilterParameter parameter) {

        ArrayList<String> filters = new ArrayList<String>();
        if (parameter.getKey().equals("keyword")) {
            // 这里全匹配
            String keyword = parameter.getValue1().toString();
            String customerIds = "";
            List<Integer> customerIdList = getIdListByComanyName(keyword);
            if (CollectionUtils.isNotEmpty(customerIdList)) {
                customerIds = StringManager.join(",", customerIdList);
            }
            filters.add("pkid='" + keyword + "'");
            filters.add("real_name='" + keyword + "'");
            filters.add("mobile='" + keyword + "'");
            filters.add("telephone='" + keyword + "'");
            filters.add("qq='" + keyword + "'");
            filters.add("weixin='" + keyword + "'");
            //过滤客户来源
            filters.add("last_customer_source in( select pkid from bd_dict where name like '%" + keyword + "%' )");
            filters.add("customer_source in( select pkid from bd_dict where name like '%" + keyword + "%' )");
            if (!StringManager.isNullOrEmpty(customerIds)) {
                filters.add("pkid in(" + customerIds + ")");
            }
            return "(" + StringManager.join(" or ", filters) + ")";
        }
        return parameter.getFilter();
    }

    private List<Integer> getIdListByComanyName(String companyName) {
        List<Integer> resLIst = new ArrayList<>();
        StringBuffer sql = new StringBuffer();

        sql.append("SELECT distinct customer_id FROM crm_customer_company_map ");
        sql.append("WHERE company_id IN(SELECT pkid FROM crm_company_intention ");
        sql.append("WHERE (NAME LIKE '%" + companyName + "%'  ");
        sql.append("OR full_name LIKE '%" + companyName + "%'  ");
        sql.append("OR company_name LIKE '%" + companyName + "%' )) ");
        DataTable rows = customerCompanyMapService.executeTable(sql.toString(), null);
        for (IRow row : rows) {
            int customerId = NumberUtils.toInt(row.getString("customer_id"));
            if (customerId > 0) {
                resLIst.add(customerId);
            }
        }
        return resLIst;
    }


    @Override
    public List<?> doQuery(Oql oql) {
        oql.setSelects("nCustomer.*,lastCustomerSource.{pkid,name},customerSource.{pkid,name}");
        oql.setOrderby("add_time DESC");
        List<NCustomer> customerList = (List<NCustomer>) super.doQuery(oql);
        List<Integer> customerIdList = getCustomerIdList(customerList);
        setCompanyName(customerList, customerIdList);
        return customerList;
    }

    @Override
    protected Object serialize(List<?> list, Oql oql) {
        HashMap<String, Object> json = (HashMap<String, Object>) super.serialize(list, oql);
        ArrayList<HashMap<String, Object>> ob2 = (ArrayList<HashMap<String, Object>>) json.get("rows");
        for (int i = 0; i < ob2.size(); i++) {
            NCustomer customer = ((NCustomer) list.get(i));
            ob2.get(i).put("companyName", customer.getCompanyName());
            //客户来源取不到（无商机客户），去客户最原始的来源
            if (customer.getLastCustomerSource() != null) {
                ob2.get(i).put("lastCustomerSource_name", customer.getLastCustomerSource().getName());
            } else {
                if (customer.getCustomerSource() != null) {
                    ob2.get(i).put("lastCustomerSource_name", customer.getCustomerSource().getName());
                }
            }
        }
        return json;
    }

    //region 私有方法
    private List<Integer> getCustomerIdList(List<NCustomer> customerList) {
        List<Integer> customerIdList = new ArrayList<>();
        for (NCustomer customer : customerList) {
            customerIdList.add(customer.getId());
        }
        return customerIdList;
    }

    private void setCompanyName(List<NCustomer> customerList, List<Integer> customerIdList) {
        Map<Integer, String> companyNameMap = customerCompanyMapService.getCompanyNameByCustomerIdList(customerIdList);
        for (NCustomer customer : customerList) {
            customer.setCompanyName(companyNameMap.get(customer.getId()));
        }
    }
    //endregion


}
