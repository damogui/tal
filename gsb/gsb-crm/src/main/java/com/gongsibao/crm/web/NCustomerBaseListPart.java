package com.gongsibao.crm.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.crm.NCustomer;

public class NCustomerBaseListPart extends AdvancedListPart {

	public String getFilterByParameter(FilterParameter parameter) {

		ArrayList<String> filters = new ArrayList<String>();
		if (parameter.getKey().equals("keyword")) {

			// 这里全匹配
			String keyword = parameter.getValue1().toString();
			filters.add("pkid='" + keyword + "'");
			filters.add("real_name='" + keyword + "'");
			filters.add("mobile='" + keyword + "'");
			filters.add("telephone='" + keyword + "'");
			filters.add("qq='" + keyword + "'");
			filters.add("weixin='" + keyword + "'");
			//过滤客户来源
			filters.add("last_customer_source in( select pkid from bd_dict where name like '%" + keyword + "%' )");
			filters.add("customer_source in( select pkid from bd_dict where name like '%" + keyword + "%' )");
			return "(" + StringManager.join(" or ", filters) + ")";
		}
		return parameter.getFilter();
	}

	@Override
	public List<?> doQuery(Oql oql) {
		oql.setSelects("NCustomer.*,NCustomer.company.companyName,NCustomer.lastCustomerSource.name,NCustomer.customerSource.name");
		List<NCustomer> resList = (List<NCustomer>) super.doQuery(oql);
		return resList;
	}

	@Override
	protected Object serialize(List<?> list, Oql oql) {
		HashMap<String, Object> json = (HashMap<String, Object>) super.serialize(list, oql);
		ArrayList<HashMap<String, Object>> ob2 = (ArrayList<HashMap<String, Object>>) json.get("rows");
		for (int i = 0; i < ob2.size(); i++) {
			NCustomer customer = ((NCustomer) list.get(i));
			//客户来源取不到（无商机客户），去客户最原始的来源
			if(customer.getLastCustomerSource() !=null){ 
				ob2.get(i).put("lastCustomerSource_name",customer.getLastCustomerSource().getName());
			}else{
				if(customer.getCustomerSource() != null){
					ob2.get(i).put("lastCustomerSource_name",customer.getCustomerSource().getName());
				}
			}			
		}
		return json;
	}
}
