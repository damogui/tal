package com.gongsibao.crm.web;

import java.util.ArrayList;

import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

public class CustomerVerifyListPart extends AdvancedListPart{


	@Override
	public String getFilters(){

		ArrayList<String> filters = new ArrayList<String>();
		
		FilterParameter fp = filterMap.get("realName");
		if(fp != null){

			//这里全匹配
			String realName = fp.getValue1().toString();
			filters.add("id='"+realName+"'");
			filters.add("realName='"+realName+"'");
			filters.add("mobile='"+realName+"'");
			filters.add("telephone='"+realName+"'");
			filters.add("qq='"+realName+"'");
			filters.add("weixin='"+realName+"'");
		}

		filter = StringManager.join(" or ", filters);
		System.out.println("query filter:" + filter);
		return filter;
	}
}
