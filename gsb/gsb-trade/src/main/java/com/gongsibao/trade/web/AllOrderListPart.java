package com.gongsibao.trade.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

/**
 * Created by zhangchao on 2018/2/8.
 */
public class AllOrderListPart extends AdvancedListPart {

    public String getFilterByParameter(FilterParameter parameter) {

        ArrayList<String> filters = new ArrayList<String>();

        if (CollectionUtils.isNotEmpty(filters)) {
            return StringManager.join(" and ", filters);
        }

        return parameter.getFilter();
    }

    @Override
    protected String getExtraFilter() {

        List<String> filterList = new ArrayList<String>();
        //会员id
        String accountId = this.getRequest("accountId");
        //订单开始时间
        String startAddOrderDate = this.getRequest("startAddOrderDate");
        //订单结束时间
        String endAddOrderDate = this.getRequest("endAddOrderDate");

        if (!StringManager.isNullOrEmpty(accountId)) {
            filterList.add(" account_id = " + accountId);
        }

        if (!StringManager.isNullOrEmpty(startAddOrderDate)) {
            filterList.add(" add_time >='" + startAddOrderDate + "' ");
        }

        if (!StringManager.isNullOrEmpty(endAddOrderDate)) {
            filterList.add(" add_time <='" + endAddOrderDate + "' ");
        }


        return StringManager.join(" and ", filterList);
    }

}
