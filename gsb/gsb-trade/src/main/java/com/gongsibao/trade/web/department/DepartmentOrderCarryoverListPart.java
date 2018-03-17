package com.gongsibao.trade.web.department;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.utils.SupplierSessionManager;

public class DepartmentOrderCarryoverListPart extends BaseDepartmentListPart{

    @Override
    public String getFilterByParameter(FilterParameter parameter) {

        ArrayList<String> filters = new ArrayList<String>();
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1().toString();
        if (parameter.getKey().equals("keyword")) {

            filters.add("form_order_no = '" + keyword + "'");
            filters.add("to_order_no = '" + keyword + "'");
            return "(" + StringManager.join(" or ", filters) + ")";
        }
        
        return parameter.getFilter();
    }
    @Override
	protected String getExtraFilter() {

		List<String> ss = new ArrayList<String>();

		// 父类过滤条件
		String filter = super.getExtraFilter();
		if (!StringManager.isNullOrEmpty(filter)) {
			ss.add(filter);
		}

		// 过滤部门Id
		String departmentIds = SupplierSessionManager.getSubDepartmentIdsStr();
		if (!StringManager.isNullOrEmpty(departmentIds)) {

			ss.add("formOrder.department_id in (" + departmentIds + ")");
		} else {

			// 非服务商内部人员看不到数据
			ss.add("1=2");
		}

		return StringManager.join(" and ", ss);
	}
}
