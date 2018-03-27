package com.gongsibao.trade.web.department;

import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.utils.SupplierSessionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by win on 2018/3/27.
 */
/*部门回款*/
public class DepartmentOrderPayListPart extends AdvancedListPart {

    @Override
    public String getFilterByParameter(FilterParameter parameter) {

        ArrayList<String> filters = new ArrayList<String>();
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1().toString();
        if (parameter.getKey().equals("keyword")) {

            filters.add("order.no ='" + keyword + "'");
            filters.add("order.channel_order_no = '" + keyword + "'");
            filters.add("order.account_mobile = '" + keyword + "'");
            filters.add("order.account_name = '" + keyword + "'");
            filters.add("order.company_id in( select pkid from crm_company_intention where (name like '%" + keyword + "%' or full_name like '%" + keyword + "%' or company_name like '%" + keyword + "%' )  )");
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
            ss.add (String.format ("pkid IN (SELECT pay_id FROM so_order_pay_map WHERE order_id IN (SELECT pkid FROM so_order WHERE owner_id   IN ( SELECT employee_id FROM   `sp_salesman` WHERE  department_id in (%s))    ORDER BY pkid DESC))",departmentIds));//进行过滤
            // ss.add("departmentId in (" + departmentIds + ")");
        } else {

            // 非服务商内部人员看不到数据
            ss.add("1=2");
        }

        return StringManager.join(" and ", ss);
    }




    @Override
    public List<?> doQuery(Oql oql) {
        oql.setSelects ("id,payForOrderCount,payWayType,amount,offlineAuditStatus,createTime,creator,orderPayMaps.*");
        List<Pay> resList = (List<Pay>) super.doQuery (oql);

        return resList;
    }

    @Override
    protected Object serialize(List<?> list, Oql oql) {

        HashMap<String, Object> json = (HashMap<String, Object>) super.serialize (list, oql);
        ArrayList<HashMap<String, Object>> ob2 = (ArrayList<HashMap<String, Object>>) json.get ("rows");

        for (int i = 0; i < ob2.size (); i++) {
            ob2.get (i).put ("orderIds", getOrderIds (list.get (i)));


        }


        return json;
    }

    /*获取订单号*/
    private Object getOrderIds(Object o) {
        Pay pay = (Pay) o;
        IOrderService orderService = ServiceFactory.create (IOrderService.class);//订
        /*根据订单id获取订单编号*/
        StringBuilder sb = new StringBuilder ();
        if (pay.getOrderPayMaps ().size () > 0) {


            for (OrderPayMap item : pay.getOrderPayMaps ()
                    ) {


                if (!item.equals (pay.getOrderPayMaps ().get (pay.getOrderPayMaps ().size () - 1))) {
                    sb.append ("<p>");

                }
                SoOrder order = orderService.getByOrderId (item.getOrderId ());
                if (order != null) {

                    sb.append (order.getNo ());
                }


                if (!item.equals (pay.getOrderPayMaps ().get (pay.getOrderPayMaps ().size () - 1))) {
                    sb.append ("</p>");

                }


            }


        }
        return sb.toString ();
    }

}
