package com.gongsibao.trade.web;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;

import com.gongsibao.entity.trade.Pay;

/**
 * Created by win on 2018/3/23.
 */
//我的回款
public class SalesmanOrderPayListPart extends AdvancedListPart {
    @Override
    public String getFilterByParameter(FilterParameter parameter) {

        ArrayList<String> filters = new ArrayList<String>();
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1().toString();
        if (parameter.getKey().equals("keyword")) {
//filters.add (String.format (" FIND_IN_SET(%s,orderNo) ",keyword));
//            return "(" + StringManager.join (" or ", filters) + ")";

            return "orderNo  like '%" + keyword + "%'";
        }

        return parameter.getFilter();
    }


    @Override
    public List<?> doQuery(Oql oql) {

        oql.setSelects("id,orderNo,payForOrderCount,payWayType,amount,offlineAuditStatus,createTime,creator,orderPayMaps.*");
        oql.setOrderby("add_time DESC");
        List<Pay> resList = (List<Pay>) super.doQuery(oql);

        return resList;
    }

//    @Override
//    protected Object serialize(List<?> list, Oql oql) {
//
//        HashMap<String, Object> json = (HashMap<String, Object>) super.serialize (list, oql);
//        ArrayList<HashMap<String, Object>> ob2 = (ArrayList<HashMap<String, Object>>) json.get ("rows");
//
//        for (int i = 0; i < ob2.size (); i++) {
//            ob2.get (i).put ("orderIds", getOrderIds (list.get (i)));
//        }
//
//
//        return json;
//    }

    /*获取订单号 此方法需优化,可在创建时进行冗余：hw 2018-04-22*/
//    private Object getOrderIds(Object o) {
//        Pay pay = (Pay) o;
//        IOrderService orderService = ServiceFactory.create (IOrderService.class);//订
//        /*根据订单id获取订单编号*/
//        StringBuilder sb = new StringBuilder ();
//        if (pay.getOrderPayMaps ().size () > 0) {
//
//        	
//            for (OrderPayMap item : pay.getOrderPayMaps ()
//                    ) {
//
//
//                if (!item.equals (pay.getOrderPayMaps ().get (pay.getOrderPayMaps ().size () - 1))) {
//                    sb.append ("<p>");
//
//                }
//                SoOrder order = orderService.getByOrderId (item.getOrderId ());
//                if (order != null) {
//
//                    sb.append (order.getNo ());
//                }
//
//
//                if (!item.equals (pay.getOrderPayMaps ().get (pay.getOrderPayMaps ().size () - 1))) {
//                    sb.append ("</p>");
//
//                }
//
//
//            }
//
//
//        }
//        return sb.toString ();
//    }
}
