package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.gongsibao.entity.crm.CompanyIntention;
import com.gongsibao.trade.base.ICompanyIntentionService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.AdvancedListPart;
import org.netsharp.panda.commerce.FilterParameter;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.trade.base.ISoOrderService;
import com.gongsibao.utils.NumberUtils;

/**
 * Created by win on 2018/3/2.
 */
public class SalesmanAllOrderListPart extends AdvancedListPart {
    ISoOrderService orderService = ServiceFactory.create(ISoOrderService.class);
    IOrderService noService = ServiceFactory.create(IOrderService.class);
    ICompanyIntentionService companyIntentionService = ServiceFactory.create(ICompanyIntentionService.class);

    @Override
    public List<?> doQuery(Oql oql) {

        StringBuilder sb = new StringBuilder();
        sb.append("SoOrder.*,");
        sb.append("SoOrder.companyIntention.{pkid,name,full_name,company_name},");
        sb.append("SoOrder.customer.realName,");
        sb.append("SoOrder.owner.{id,name}");
        oql.setSelects(sb.toString());
        oql.setOrderby("add_time DESC");
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
            filters.add("account_mobile like '%" + keyword + "%'");
            filters.add("channel_order_no like '%" + keyword + "%'");
            filters.add("account_mobile like '%" + keyword + "%'");
            filters.add("customer_name like '%" + keyword + "%'");
            filters.add("company_id in( select pkid from crm_company_intention where (name like '%" + keyword + "%' or full_name like '%" + keyword + "%' or company_name like '%" + keyword + "%' )  )");
            return "(" + StringManager.join(" or ", filters) + ")";
        }
        //业务员
        if (parameter.getKey().equals("ywyName")) {
            return "owner_id in (select id from sys_permission_employee where name like '%" + keyword + "%')";
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
     * 验证分期付款的订单
     * @param id
     * @return 0-已经分期；1-审核中；2-可以审核
     */
    public Integer ValidateStaged(Integer id) {
        Integer resultValueInteger = 2;
        Oql oql = new Oql();
        {
            oql.setType(SoOrder.class);
            oql.setSelects("*");
            oql.setFilter("id=?");
            oql.getParameters().add("id", id, Types.INTEGER);
        }
        SoOrder entity = orderService.queryFirst(oql);
        if (entity.getIsInstallment()) {
            return 0;
        } else {
            if (entity.getInstallmentAuditStatusId().getValue().equals(AuditStatusType.Shz.getValue())) {
                return 1;
            }
        }
        return resultValueInteger;
    }

    /*
     * 是否是改价的订单
     * @param id
     * @return 0-审核通过、未审核；1-驳回；2-审核中
     */
    public Integer isChangePriceOrde(Integer id) {
        Integer resultValueInteger = 0;
        Oql oql = new Oql();
        {
            oql.setType(SoOrder.class);
            oql.setSelects("*");
            oql.setFilter("id=?");
            oql.getParameters().add("id", id, Types.INTEGER);
        }
        SoOrder entity = orderService.queryFirst(oql);
        if (entity.getIsChangePrice()) {
            if (entity.getChangePriceAuditStatus().getValue().equals(AuditStatusType.Shtg.getValue())) {
                resultValueInteger = 0;
            } else if (entity.getChangePriceAuditStatus().getValue().equals(AuditStatusType.Bhsh.getValue())) {
                resultValueInteger = 1;
            } else if (entity.getChangePriceAuditStatus().getValue().equals(AuditStatusType.Shz.getValue())) {
                resultValueInteger = 2;
            }
        }
        return resultValueInteger;
    }

    /**
     * 获取订单的结转状态、结转金额判断
     *
     * @param id 订单Id
     * @return -1 订单余额不足(订单余额 = paidPrice+carryIntoAmount-refundPrice-carryAmount yyk提供公式)
     */
    public Integer carryValidate(Integer id) {
        Integer reusltValue = 0;
        Oql oql = new Oql();
        {
            oql.setType(SoOrder.class);
            oql.setSelects("*");
            oql.setFilter("id=?");
            oql.getParameters().add("id", id, Types.INTEGER);
        }
        SoOrder entity = orderService.queryFirst(oql);

        Integer paidPrice = entity.getPaidPrice() == null ? 0 : entity.getPaidPrice().intValue();
        Integer carryIntoAmount = entity.getCarryIntoAmount() == null ? 0 : entity.getCarryIntoAmount().intValue();
        Integer refundPrice = entity.getRefundPrice() == null ? 0 : entity.getRefundPrice().intValue();
        Integer carryAmount = entity.getCarryAmount() == null ? 0 : entity.getCarryAmount().intValue();
        //订单余额
        Integer balance = paidPrice + carryIntoAmount - refundPrice - carryAmount;
        if (balance > 0) {
            reusltValue = entity.getCarryStatus() == null ? 0 : entity.getCarryStatus().getValue();
        } else {
            reusltValue = -1;
        }
        return reusltValue;
    }

    /**
     * 获取订单的退款状态或退款金额验证
     *
     * @param id 订单Id
     * @return -1 可退款金额不足(可退款金额=已付金额-已退金额 yyk提供公式)
     */
    public Integer refundValidate(Integer id) {
        Integer reusltValue = 0;
        Oql oql = new Oql();
        {
            oql.setType(SoOrder.class);
            oql.setSelects("*");
            oql.setFilter("id=?");
            oql.getParameters().add("id", id, Types.INTEGER);
        }
        SoOrder entity = orderService.queryFirst(oql);
        //已付金额
        Integer paidPrice = entity.getPaidPrice() == null ? 0 : entity.getPaidPrice().intValue();
        //已退金额
        Integer refundPrice = entity.getRefundPrice() == null ? 0 : entity.getRefundPrice().intValue();
        //可退金额
        Integer canRefundAmount = paidPrice - refundPrice;
        if (canRefundAmount > 0) {
            reusltValue = entity.getRefundStatus() == null ? 0 : entity.getRefundStatus().getValue();
        } else {
            reusltValue = -1;
        }
        return reusltValue;
    }

    /*校验是不是存在订单的改价审核和回款审核，存在不弹窗*/
    public Integer checkCanPay(Integer orderId) {
        return noService.checkCanPay(orderId);


    }

    /*创建订单业绩是不是已经存在存在的话不能创建*/
    public Integer checkCanOrderPer(Integer orderId) {
        return noService.checkCanOrderPer(orderId, 0);
    }


    /*创建回款业绩是不是已经存待审核在存在的话不能创建*/
    public Integer checkCanOrderPayPer(Integer orderId) {
        return noService.checkCanOrderPer(orderId, 1);
    }

    /*根据订单id获取订单信息*/
    public SoOrder getOrderByOrderNo(String orderno) {
        SoOrder order = orderService.getByOrderNo(orderno);
        if (NumberUtils.toInt(order.getCompanyId()) > 0) {
            CompanyIntention companyIntention = companyIntentionService.getById(order.getCompanyId());
            order.setCompanyIntention(companyIntention);
        }
        return order;
    }




    /*进行删除操作*/
    public   String  orderDel(Integer orderId) {
        return noService.orderDel(orderId);


    }

}
