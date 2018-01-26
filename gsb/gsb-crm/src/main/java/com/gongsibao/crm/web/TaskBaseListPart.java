package com.gongsibao.crm.web;

import java.util.ArrayList;

import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.supplier.web.panda.BaseSupplierListPart;

public class TaskBaseListPart extends BaseSupplierListPart{

	@Override
	public String getFilters(){

		ArrayList<String> filters = new ArrayList<String>();
		
		FilterParameter fp = filterMap.get("keyword");
		if(fp != null){

			//这里全匹配
			String keyword = fp.getValue1().toString();
			filters.add("id='"+keyword+"'");
			filters.add("name='"+keyword+"'");
			filters.add("customer.id='"+keyword+"'");
			filters.add("customer.realName='"+keyword+"'");
			filters.add("customer.mobile='"+keyword+"'");
			filters.add("customer.telephone='"+keyword+"'");
			filters.add("customer.qq='"+keyword+"'");
			filters.add("customer.weixin='"+keyword+"'");
		}

		filter = StringManager.join(" or ", filters);
		System.out.println("query filter:" + filter);
		return filter;
	}
}
