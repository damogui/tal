package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gongsibao.entity.trade.dto.DepPayMapDTO;
import com.gongsibao.utils.NumberUtils;
import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.u8.base.ISoOrderService;

/**
 * Created by win on 2018/3/2.
 */
public class SalesmanAllOrderListPart extends AdvancedListPart {
    ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);
    IPersister<SoOrder> oService = PersisterFactory.create();//执行sql

    @Override
    public List<?> doQuery(Oql oql) {

        StringBuilder sb = new StringBuilder();
        sb.append("SoOrder.*,");
        sb.append("SoOrder.companyIntention.name,");
        sb.append("SoOrder.customer.realName");
        oql.setSelects(sb.toString());
        List<?> rows = orderService.queryList(oql);
        return rows;
    }


    protected Object serialize(List<?> list, Oql oql) {
        HashMap<String, Object> json = (HashMap<String, Object>) super.serialize(list, oql);
        ArrayList<HashMap<String, Object>> ob2 = (ArrayList<HashMap<String, Object>>) json.get("rows");
        for (int i = 0; i < ob2.size(); i++) {
            SoOrder soOrder = ((SoOrder) list.get(i));
            //this.balance = paidPrice + carryIntoAmount - refundPrice - carryAmount;
            Integer balance = NumberUtils.toInt(soOrder.getPaidPrice()) + NumberUtils.toInt(soOrder.getCarryIntoAmount()) - NumberUtils.toInt(soOrder.getRefundPrice()) - NumberUtils.toInt(soOrder.getCarryAmount());
            ob2.get(i).put("balance", balance);
        }
        return json;
    }


    @Override
    public String getFilterByParameter(FilterParameter parameter) {

        ArrayList<String> filters = new ArrayList<String>();
        //当是关键字时(订单编号、渠道订单编号、下单人、下单人电话、关联公司)
        String keyword = parameter.getValue1().toString();
        if (parameter.getKey().equals("keyword")) {

            filters.add("no like '%" + keyword + "%'");
            filters.add("channel_order_no like '%" + keyword + "%'");
            filters.add("account_mobile like '%" + keyword + "%'");
            filters.add("customer_name like '%" + keyword + "%'");
            filters.add("company_id in( select pkid from crm_company_intention where (name like '%" + keyword + "%' or full_name like '%" + keyword + "%' or company_name like '%" + keyword + "%' )  )");
            return "(" + StringManager.join(" or ", filters) + ")";
        }
        //业务员
        if (parameter.getKey().equals("ywyName")) {
            return "owner_id in (select id from sys_permission_employee where name = '" + keyword + "')";
        }

        //分期申请时间
        if (parameter.getKey().equals("stageCreateTime")) {

            List<String> stageCreateTime = new ArrayList<>();
            if (parameter.getValue1() != null) {
                stageCreateTime.add(" create_time >= '" + parameter.getValue1().toString() + "'");
            }
            if (parameter.getValue2() != null) {
                stageCreateTime.add(" create_time <= '" + parameter.getValue2().toString() + "'");
            }

            return "pkid in (select order_id from so_order_stage where " + StringManager.join(" and ", stageCreateTime) + " )";
        }

        //分期申请人
        if (parameter.getKey().equals("stageCreator")) {
            return "pkid in (select order_id from so_order_stage where creator like '%" + keyword + "%')";
        }

        return parameter.getFilter();
    }

    //转移/分配（包括批量转移/分配）
    public void orderTran(List<Integer> orderList, Integer toUserId) {
        orderService.orderTran(orderList, toUserId);
    }


    /*
    * 是否是分期付款的订单
    * @param id
    * @return
    */
    public Boolean isStaged(Integer id) {
        Oql oql = new Oql();
        {
            oql.setType(SoOrder.class);
            oql.setSelects("*");
            oql.setFilter("id=?");
            oql.getParameters().add("id", id, Types.INTEGER);
        }
        SoOrder entity = orderService.queryFirst(oql);
        return entity.getIsInstallment() == null ? false : entity.getIsInstallment();
    }

    /**
     * 获取订单的退款(结转)状态、退款(结转)金额判断
     *
     * @param id   订单Id
     * @param type 0-退款、1-结转
     * @return -1 金额不足
     */
    public Integer refundCarryValidate(Integer id, Integer type) {
        Integer reusltValue = 0;
        Oql oql = new Oql();
        {
            oql.setType(SoOrder.class);
            oql.setSelects("*");
            oql.setFilter("id=?");
            oql.getParameters().add("id", id, Types.INTEGER);
        }
        SoOrder entity = orderService.queryFirst(oql);
        //退款金额
        Integer refundPrice = entity.getRefundPrice() == null ? 0 : entity.getRefundPrice().intValue();
        //结转金额
        Integer carryAmount = entity.getCarryAmount() == null ? 0 : entity.getCarryAmount().intValue();
        Integer allAmount = refundPrice.intValue() + carryAmount.intValue();
        if ((entity.getPaidPrice().intValue() - allAmount.intValue()) > 0) {
            if (type.equals(0)) {
                reusltValue = entity.getRefundStatus() == null ? 0 : entity.getRefundStatus().getValue();
            } else {
                reusltValue = entity.getCarryStatus() == null ? 0 : entity.getCarryStatus().getValue();
            }
        } else {
            reusltValue = -1;
        }
        return reusltValue;
    }

    /*校验是不是存在订单的改价审核和回款审核，存在不弹窗*/
    public Integer checkCanPay(Integer orderId) {
        String sql = "SELECT IFNULL(COUNT(change_price_audit_status_id),0)   FROM so_order  WHERE  change_price_audit_status_id<>1054  AND  is_change_price=1 AND  pkid=?";//有没有待审核、审核中

        QueryParameters qps = new QueryParameters();
        qps.add("@pkid", orderId, Types.INTEGER);
        int num = oService.executeInt(sql, qps);
        if (num > 0) {
            return 1;

        } else {
            return 0;

        }


    }

}
