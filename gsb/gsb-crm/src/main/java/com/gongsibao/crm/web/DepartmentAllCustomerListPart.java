package com.gongsibao.crm.web;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.util.StringManager;

import com.gongsibao.utils.SupplierSessionManager;

public class DepartmentAllCustomerListPart extends NCustomerAllListPart{

	@Override
	protected String getExtraFilter() {

		List<String> ss = new ArrayList<String>();
		
		//父类过滤条件
		String filter = super.getExtraFilter();
		if(!StringManager.isNullOrEmpty(filter)){

			ss.add(filter);
		}
		
		//过滤部门Id
		String departmentIds = SupplierSessionManager.getSubDepartmentIdsStr();
		if (!StringManager.isNullOrEmpty(departmentIds)) {

			ss.add(" id in (select customer_id from n_crm_customer_task where department_id in (" + departmentIds+"))");
		}else {
			
			//非服务商内部人员看不到数据
			ss.add("1=2");
		}

		return StringManager.join(" and ", ss);
	}
}
