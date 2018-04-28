package com.gongsibao.trade.web.department;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.utils.SupplierSessionManager;

/*部门回款业绩*/
public class DepartmentOrderReceivedListPart extends AdvancedListPart {
    @Override
    public String getFilterByParameter(FilterParameter parameter) {

        ArrayList<String> filters = new ArrayList<String>();
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1().toString();
        if (parameter.getKey().equals("keyword")) {
            return "soOrder.pkid>0 and (soOrder.no like '%" + keyword + "%' or soOrder.channel_order_no like '%" + keyword + "%')";

        }
        if (parameter.getKey().equals("isOnlinePay")) {

            //filters.add ("soOrder.is_online_pay ='" + keyword + "'");
            return "soOrder.is_online_pay ='" + keyword + "'";

        }


        return parameter.getFilter();
    }


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

            ss.add("departmentId in (" + departmentIds + ")");
        } else {

            // 非服务商内部人员看不到数据
            ss.add("1=2");
        }

        return StringManager.join(" and ", ss);
    }
}
