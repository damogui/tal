package com.gongsibao.crm.web;

import java.util.ArrayList;

import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;

public class NCustomerVerifyListPart extends AdvancedListPart{

	public String getFilterByParameter(FilterParameter parameter){
		
		ArrayList<String> filters = new ArrayList<String>();
		if(parameter.getKey() == "keyword"){
			
			//这里全匹配
			String keyword = parameter.getValue1().toString();
			filters.add("id='"+keyword+"'");
			filters.add("realName='"+keyword+"'");
			filters.add("mobile='"+keyword+"'");
			filters.add("telephone='"+keyword+"'");
			filters.add("qq='"+keyword+"'");
			filters.add("weixin='"+keyword+"'");
		}
		return parameter.getFilter();
	}
}
