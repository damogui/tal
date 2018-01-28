package com.gongsibao.crm.web;

import java.util.ArrayList;

import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

public class TaskBaseListPart extends AdvancedListPart {

	public String getFilterByParameter(FilterParameter parameter) {

		ArrayList<String> filters = new ArrayList<String>();
		if(parameter.getKey().equals("keyword")){

			// 这里全匹配
			String keyword = parameter.getValue1().toString();
			filters.add("id='" + keyword + "'");
			filters.add("name='" + keyword + "'");
			filters.add("customer.id='" + keyword + "'");
			filters.add("customer.real_name='" + keyword + "'");
			filters.add("customer.mobile='" + keyword + "'");
			filters.add("customer.telephone='" + keyword + "'");
			filters.add("customer.qq='" + keyword + "'");
			filters.add("customer.weixin='" + keyword + "'");
			return "(" + StringManager.join(" or ", filters) + ")";
		}
		return parameter.getFilter();
	}

}
