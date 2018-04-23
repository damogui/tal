package com.gongsibao.trade.service;

import com.gongsibao.bd.base.IPreferentialCodeService;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.trade.*;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.dic.OrderProcessStatusType;
import com.gongsibao.trade.base.*;
import com.gongsibao.trade.service.action.order.utils.AuditHelper;
import com.gongsibao.trade.web.dto.OrderPayDTO;
import org.apache.commons.lang3.ObjectUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService extends PersistableService<SoOrder> implements IOrderService {

    IPreferentialCodeService preferentialCodeService = ServiceFactory.create(IPreferentialCodeService.class);
    IInvoiceService invoiceService = ServiceFactory.create(IInvoiceService.class);
    IOrderInvoiceMapService orderInvoiceMapService = ServiceFactory.create(IOrderInvoiceMapService.class);
    IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);
    IOrderDiscountService orderDiscountService = ServiceFactory.create(IOrderDiscountService.class);
    IOrderService tradeOrderService = ServiceFactory.create(IOrderService.class);

    public OrderService() {
        super();
        this.type = SoOrder.class;
    }

    @Override
    public SoOrder save(SoOrder entity) {

        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/crm/order/save");
            ctx.setItem(entity);
            ctx.setState(entity.getEntityState());
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);

        entity = (SoOrder) ctx.getItem();
        return entity;
    }


    @Override
    public Boolean applyStage(SoOrder entity) {

        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/crm/order/stage");
            ctx.setItem(entity);
            ctx.setState(entity.getEntityState());
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
    }

    @Override
    public Boolean applyRefund(Refund refund) {

        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/crm/order/refund");
            ctx.setItem(refund);
            ctx.setState(refund.getEntityState());
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
    }

    @Override
    public Boolean applyCarryover(NOrderCarryover orderCarryover) {

        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/crm/order/carryover");
            ctx.setItem(orderCarryover);
            ctx.setState(orderCarryover.getEntityState());
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
    }

    @Override
    public SoOrder getByOrderId(Integer orderId) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("pkid =?");
            oql.getParameters().add("@pkid", orderId, Types.INTEGER);
        }
        SoOrder entity = super.queryFirst(oql);
        return entity;
    }

    @Override
    public SoOrder getByOrderNo(String orderNo) {
        if (StringManager.isNullOrEmpty(orderNo)) {
            return null;
        }

        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("no =?");
            oql.getParameters().add("@no", orderNo, Types.VARCHAR);
        }
        SoOrder entity = super.queryFirst(oql);
        return entity;
    }


    /*获取订单id根据no*/
    @Override
    public Integer getOrderIdByNo(Integer orderNo) {
        IPersister<SoOrder> orderService = PersisterFactory.create();
        String sql = "SELECT  IFNULL(MAX(pkid),0) FROM so_order  WHERE  no=? ;";//根据订单编号获取订单id
        QueryParameters qps = new QueryParameters();
        qps.add("@no", orderNo, Types.INTEGER);//订单编号
        Integer orderId = orderService.executeInt(sql, qps);
        return orderId;
    }

    /*根据参数值更细状态值*/
    @Override
    public void updateStatus(String status_id, Integer id, AuditStatusType shzt) {
        String sql = "";
        UpdateBuilder updateBuilder = new UpdateBuilder();
        {

            if (shzt.equals(AuditStatusType.Shtg) && status_id.equals("dep_receivable_audit_status_id")) {
                sql = String.format("update  so_order  set performance_price=payable_price,%s=%s  where  pkid=? ", status_id, shzt.getValue());
            }
            if (shzt.equals(AuditStatusType.Shtg) && status_id.equals("dep_payper_audit_status_id")) {
                //updateBuilder.set ("returned_price","paid_price-refund_price-returned_price-carry_amount");//回款业绩待划分金额,计算公式
                sql = String.format("update  so_order  set returned_price=paid_price-refund_price-carry_amount,%s=%s  where  pkid=? ", status_id, shzt.getValue());
            }
        }
        if (sql.length() > 0) {
            QueryParameters qps = new QueryParameters();
            qps.add("@pkid", id, Types.INTEGER);
            this.pm.executeNonQuery(sql, qps);

        }


    }

    /*是否可以创建回款*/
    @Override
    public Integer checkCanPay(Integer orderId) {
        //校验余额是否小于应付金额 0是1不是
        int num = 0;
        num = checkIsBancleLessOrder(orderId);
        if (num > 0) {
            return num;//回款已经创建完毕
        }


        String sql = "SELECT COUNT(change_price_audit_status_id)  FROM so_order  WHERE  change_price_audit_status_id<>1054  AND  is_change_price=1 AND  pkid=?";//有没有待审核、审核中

        QueryParameters qps = new QueryParameters();
        qps.add("@pkid", orderId, Types.INTEGER);
        num = this.pm.executeInt(sql, qps);
        if (num > 0) {

            return 1;

        } else {
//            SoOrder order = byId (orderId);
            num = checkCanPayByOrderId(orderId);//订单是否存在已经支付的待审核
            return num;

        }
    }

    /*校验余额是否小于应付金额 0是1不是  等于也不行*/
    private int checkIsBancleLessOrder(Integer orderId) {

        SoOrder order = getSoOrderById(orderId, null);
        if (order.getBalance() < order.getPayablePrice()) {
            return 0;

        } else {
            return 1;

        }


    }

    /*订单是否存在已经支付的待审核*/
    private int checkCanPayByOrderId(Integer orderId) {
        SoOrder sorder = getSoOrderById(orderId, "SoOrder.*,SoOrder.pays.*");
        List<Integer> listPayId = new ArrayList<>();
        for (OrderPayMap item : sorder.getPays()
                ) {
            listPayId.add(item.getPayId());
        }


        if (listPayId.size() > 0) {
            String payIds = StringManager.join(",", listPayId);
            String sql = String.format("SELECT  COUNT(1)  FROM bd_audit_log        WHERE    type_id=%s   and  status_id   IN (1051,1052) and form_id in (%s)", AuditLogType.Sksq.getValue(), payIds);//待审核和审核中的

            int num = this.pm.executeInt(sql, null);
            if (num > 0) {
                return 1;

            } else {
                return 0;

            }
        }

        return 0;


    }

    /*是否可以创建   type=0（订单业绩），=1(回款业绩)*/
    @Override
    public Integer checkCanOrderPer(Integer orderId, Integer type) {
        String sql = String.format("SELECT  COUNT(1)  FROM bd_audit_log        WHERE    type_id=%s   and  status_id  NOT IN (0,1053) and form_id=?", AuditLogType.DdYjSq.getValue());//无审核状态和驳回审核的不在查询列
        if (type == 1) {

            sql = String.format("SELECT  COUNT(1)  FROM bd_audit_log        WHERE    type_id=%s   and  status_id   IN (1051,1052) and form_id=?", AuditLogType.Skyjsh.getValue());//待审核和审核中的
        }

        QueryParameters qps = new QueryParameters();
        qps.add("@form_id", orderId, Types.INTEGER);
        int num = this.pm.executeInt(sql, qps);
        if (num > 0) {
            return 1;

        } else {
            if (type == 0) {//订单业绩金额
                //校验
                Integer execNum2 = AuditHelper.getCarryRecode(orderId, AuditStatusType.Dsh);
                if (execNum2 > 0) {
                    return 2;//有结转
                } else {
                    SoOrder order = getByOrderId(orderId);
                    if ((order.getPayablePrice() - order.getCarryIntoAmount()) <= 0) {//
                        return 3;//订单业绩已经分配完毕
                    }
                }

            }
            return 0;

        }
    }

    @Override
    public String getCustomerMobile(Integer orderId) {

        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("id,accountMobile");
            oql.setFilter("pkid =" + orderId);
        }
        SoOrder entity = super.queryFirst(oql);

        return entity.getAccountMobile();
    }

    @Override
    public NCustomer getCustomerByOrderId(Integer orderId) {

        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("id,customerId,customer.{id,realName,mobile,telephone,email,qq,weixin,addr}");
            oql.setFilter("id =?");
            oql.getParameters().add("@orderId", orderId, Types.INTEGER);
        }
        SoOrder entity = super.queryFirst(oql);
        if (entity != null) {

            return entity.getCustomer();
        }
        return null;
    }

    /*只是查询soorder的基础字段，不多查，效率高*/
    @Override
    public SoOrder getSoOrderById(Object id, String selects) {

        if (id == null) {

            id = "0";
        }
        Oql oql = new Oql();

        StringBuilder sb = new StringBuilder();
        sb.append("SoOrder.*");
        if (!StringManager.isNullOrEmpty(selects)) {
            oql.setSelects(selects);
        } else {
            oql.setSelects(sb.toString());
        }

        oql.setType(SoOrder.class);
        oql.setFilter("pkid=?");
        oql.getParameters().add("@pkid", id, Types.INTEGER);
        SoOrder obj = queryFirst(oql);
        return obj;

    }

    @Override
    public Integer countByAccountId(Integer accountId, boolean isPaid) {
        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("COUNT(1) ");

            StringBuilder filter = new StringBuilder();
            filter.append("account_id = " + accountId);
            if (isPaid) {
                filter.append(" AND paid_price > 0 ");
            }
            oql.setFilter(filter.toString());
        }
        return queryCount(oql);
    }

    @Override
    public SoOrder saveWebOrder(SoOrder order, Invoice invoice, List<String> couponNoList) {
        // 保存订单
        order = super.save(order);

        // 更新订单号
        updateNo(order);

        if (null != invoice) {
            for (OrderInvoiceMap orderInvoiceMap : invoice.getOrderInvoiceMaps()) {
                orderInvoiceMap.setOrderId(order.getId());
            }
        }

        // 回写优惠券状态
        if (null != couponNoList && couponNoList.size() > 0) {
            preferentialCodeService.updateCodeStatus(couponNoList, 2, order.getId());
        }

        // 保存发票
        if (null != invoice) {
            // 删除发票关联记录
            orderInvoiceMapService.getByInvoiceId(invoice.getId());
            // 保存发票
            invoiceService.save(invoice);
        }
        return order;
    }

    @Override
    public void updateNo(SoOrder soOrder) {
        Integer pkid = soOrder.getId();
        String no = String.valueOf((100000000 + pkid));
        String cmdText = "UPDATE so_order SET no = ? WHERE pkid = ? ";
        QueryParameters qps = new QueryParameters();
        {
            qps.add("no", no, Types.VARCHAR);
            qps.add("pkid", pkid, Types.INTEGER);
        }
        IPersister<SoOrder> pm = PersisterFactory.create();
        pm.executeNonQuery(cmdText, qps);
        soOrder.setNo(no);
    }

    @Override
    public void updateOnlinePay(OrderPayDTO orderPayDTO) {
        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/rest/order/onlinePay");
            ctx.setItem(orderPayDTO);
            ctx.setState(EntityState.Persist);
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);
    }

    @Override
    public Boolean addPaidPrice(Integer id, Integer paidPrice) {
        String sql = "UPDATE `so_order` SET paid_price = paid_price + " + paidPrice + " WHERE pkid = " + id;
        return this.pm.executeNonQuery(sql, null) > 0;
    }

    @Override
    public Boolean updateProcessStatusId(Integer id, Integer processStatusId) {
        String sql = "UPDATE `so_order` SET process_status_id = " + processStatusId + " WHERE pkid = " + id;
        return this.pm.executeNonQuery(sql, null) > 0;
    }

    @Override
    public Boolean updatePayStatus(int id, Integer payStatusId) {
        String sql = "UPDATE `so_order` SET pay_status_id = " + payStatusId + " WHERE `pkid` =  " + id;
        if (payStatusId == 3013) {
            sql = sql + " AND paid_price >= payable_price ";
        }
        return this.pm.executeNonQuery(sql, null) > 0;
    }

    @Override
    public Boolean updateFistPayTime(Integer id, Date fistPayTime) {
        UpdateBuilder builder = UpdateBuilder.getInstance();
        {
            builder.update("so_order");
            builder.set("fist_pay_time", fistPayTime);
            builder.set("pkid", id);
            builder.where("pkid = " + id);
        }
        return this.pm.executeNonQuery(builder.toSQL(), null) > 0;
    }

    @Override
    public Boolean updatePayTime(Integer id, Date payTime) {
        UpdateBuilder builder = UpdateBuilder.getInstance();
        {
            builder.update("so_order");
            builder.set("pay_time", payTime);
            builder.where("pkid = " + id);
        }
        return this.pm.executeNonQuery(builder.toSQL(), null) > 0;
    }

    @Override
    public int countOrderAccountIdStatus(Integer accountId, Integer status) {
        StringBuffer sql = new StringBuffer("");
        Oql oql = new Oql();
        oql.setType(this.type);
        oql.setSelects("*");
        sql.append(" SoOrder.accountId = ? and SoOrder.changePriceAuditStatus not in (1051,1052,1053) ");
        if (status == 1) {//未付款
            sql.append(" and SoOrder.payStatus in (3011, 3012) AND SoOrder.processStatus <> 3023 ");
        } else if (status == 2) {//办理中3022
            sql.append(" and SoOrder.processStatus = 3022 AND SoOrder.payStatus = 3013 ");
        } else if (status == 3) {//办理完成
            sql.append(" and SoOrder.processStatus = 3024 and SoOrder.payStatus = 3013 ");
        }
        oql.setFilter(sql.toString());
        oql.getParameters().add("accountId", accountId, Types.INTEGER);
        return this.pm.queryCount(oql);
    }

    @Override
    public List<SoOrder> pageOrderListByAccountIdStatus(Integer accountId, Integer status, int currentPage, int
            pageSize) {
        StringBuffer sql = new StringBuffer("");
        Oql oql = new Oql();
        oql.setType(this.type);
        oql.setSelects("SoOrder.*,SoOrder.products.*");
        sql.append(" SoOrder.accountId = ? and SoOrder.changePriceAuditStatus not in (1051,1052,1053) ");
        if (status == 1) {//未付款
            sql.append(" and SoOrder.payStatus in (3011, 3012) and SoOrder.processStatus <> 3023 ");
        } else if (status == 2) {//办理中3022
            sql.append(" and SoOrder.processStatus = 3022 and SoOrder.payStatus = 3013 ");
        } else if (status == 3) {//办理完成
            sql.append(" and SoOrder.processStatus = 3024 and SoOrder.payStatus = 3013 ");
        }
        oql.setFilter(sql.toString());
        //oql.setOrderby(" createTime DESC ");
        oql.setPaging(new Paging(currentPage, pageSize));
        oql.getParameters().add("accountId", accountId, Types.INTEGER);
        return this.pm.queryList(oql);
    }

    @Override
    public int updateOrderStatus(Integer accountId, Integer orderId, Integer status) {
        String sql = "update so_order set so_order.process_status_id = ? where pkid = ? and account_id = ? ";
        QueryParameters queryParameters = new QueryParameters();
        queryParameters.add("process_status_id",status,Types.INTEGER);
        queryParameters.add("pkid",orderId,Types.INTEGER);
        queryParameters.add("account_id",accountId,Types.INTEGER);
        return this.pm.executeNonQuery(sql, queryParameters);
    }

    @Override
    public int updateCancelOrder(Integer accountId, Integer orderId) {
        int effectNum = updateOrderStatus(accountId,orderId, OrderProcessStatusType.Yqx.getValue());
        if (effectNum > 0) {
            List<OrderProd> orderProds = orderProdService.byOrderId(orderId);
            if (orderProds != null) {
                List<Integer> orderProdIds = orderProds.stream().map(OrderProd::getId).collect(Collectors.toList());
                orderProdService.removeCompanyQualifyByOrderProdIds(orderProdIds);
                List<OrderDiscount> orderDiscounts = orderDiscountService.queryByOrderId(orderId);
                if (orderDiscounts != null) {
                    final int[] amount = {0};
                    orderDiscounts.stream().forEach(orderDiscount -> {
                        amount[0] += ObjectUtils.defaultIfNull(orderDiscount.getAmount(), 0);
                        // 优惠券使用记录，更新为用户取消
                        orderDiscountService.updateNo(orderDiscount.getId(), orderDiscount.getNo().concat("-用户取消"));
                        // 优惠券使用状态还原
                        preferentialCodeService.updateUseRevert(orderDiscount.getPreferentialId(), orderDiscount
                                .getNo());
                    });
                    // 复原订单价格
                    tradeOrderService.updatePayablePriceRevert(orderId, amount[0]);
                }
            }
        }
        return effectNum;
    }

    @Override
    public int updatePayablePriceRevert(int pkid, int price) {
        String sql = String.format("UPDATE so_order SET payable_price = payable_price + %s WHERE pkid = %s ",price,pkid);
        return this.pm.executeNonQuery(sql, null);
    }
}