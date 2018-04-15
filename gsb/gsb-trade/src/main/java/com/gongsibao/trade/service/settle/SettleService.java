package com.gongsibao.trade.service.settle;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.SettleAudit;
import com.gongsibao.entity.Result;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.settle.Settle;
import com.gongsibao.entity.trade.settle.dict.SettleHandleStatus;
import com.gongsibao.trade.base.settle.ISettleService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.EntityState;
import org.netsharp.service.PersistableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import java.util.List;

@Service
public class SettleService extends PersistableService<Settle> implements ISettleService {

    public SettleService() {
        super();
        this.type = Settle.class;
    }

    @Override
    public Result<Settle> saveSettle(List<Integer> orderProdCaseIds) {
        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/supplier/settle/apply");
            ctx.setItem(null);
            ctx.setState(EntityState.New);
            ctx.getStatus().put("orderProdCaseIds", orderProdCaseIds);
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);

        Settle settle = (Settle) ctx.getItem();
        Result<Settle> result = new Result<>();
        result.setObj(settle);
        return result;
//        if (null == orderProdCaseIds || orderProdCaseIds.isEmpty()) {
//            return new Result<>(ResponseStatus.FAILED, "明细订单不存在");
//        }
//
//        // 查询order_prod_case对象列表
//        List<OrderProdSettle> orderProdCaseList = orderProdSettleService.byIds(orderProdCaseIds);
//        if (null == orderProdCaseList || orderProdCaseList.isEmpty()) {
//            return new Result<>(ResponseStatus.FAILED, "明细订单不存在");
//        }
//
//        // 检查各项产品是否处于待结算状态
//        List<Integer> orderProdIds = new ArrayList<>();
//
//        // 总金额
//        double totalAmount = 0d;
//        // 税额
//        double tax = 0d;
//        // 佣金
//        double commission = 0d;
//        // 总成本
//        double totalCost = 0d;
//        // 总服务费
//        double totalCharge = 0d;
//
//        Supplier supplier = SupplierSessionManager.getSupplier();
//        if (null == supplier) {
//            return new Result<>(ResponseStatus.FAILED, "请重新登录");
//        }
//
//        Integer supplierId = supplier.getId();
//
//        if (null == supplierId || supplierId == 0) {
//            return new Result<>(ResponseStatus.FAILED, "请重新登录");
//        }
//
//        for (OrderProdSettle orderProdCase : orderProdCaseList) {
//            SoOrder soOrder = orderProdCase.getSoOrder();
//            // 验证是否已付款
//            if (soOrder.getPayablePrice() > 0 && soOrder.getPaidPrice().compareTo(soOrder.getPayablePrice()) < 0) {
//                return new Result<>(ResponseStatus.FAILED, "订单[" + orderProdCase.getSoOrder().getNo() + "]未支付完成");
//            }
//
//            // 验证结算状态
//            OrderProd orderProd = orderProdCase.getOrderProd();
//            if (orderProd.getSettleStatus().getValue() != SettleStatus.NO_SETTLEMENT.getValue()) {
//                return new Result<>(ResponseStatus.FAILED, "明细订单[" + orderProdCase.getOrderProdId() + "]目前未[" + orderProd.getSettleStatus().getText() + "]状态");
//            }
//
//            // 验证订单归属服务商
//            if (supplierId.compareTo(orderProdCase.getSupplierId()) != 0) {
//                return new Result<>(ResponseStatus.FAILED, "明细订单[" + orderProdCase.getOrderProdId() + "]不属于您");
//            }
//
//            orderProdIds.add(orderProdCase.getOrderProdId());
//            totalCost = AmountUtils.add(totalCost, orderProdCase.getCost().doubleValue());
//            totalCharge = AmountUtils.add(totalCharge, orderProdCase.getCharge().doubleValue());
//        }
//
//        // 税率
//        double taxRate = AmountUtils.div(Settle.TAX_RATE.doubleValue(), 100, 4);
//
//        // 总金额
//        totalAmount = AmountUtils.add(totalCost, totalCharge);
//
//        // 佣金
//        commission = AmountUtils.div(AmountUtils.mul((1 - taxRate), totalCharge), 1, 2);
//
//        // 税额
//        tax = AmountUtils.sub(totalCharge, commission);
//
//        Date today = new Date();
//        String dateStr = DateUtils.getDateStr(today);
//
//        // 构建结算实体
//        Settle settle = new Settle();
//        {
//            settle.toNew();
//            settle.setTotalAmount(BigDecimal.valueOf(totalAmount));
//            settle.setTax(BigDecimal.valueOf(tax));
//            settle.setCommission(BigDecimal.valueOf(commission));
//            settle.setTotalCost(BigDecimal.valueOf(totalCost));
//            settle.setTotalCharge(BigDecimal.valueOf(totalCharge));
//            settle.setTax(BigDecimal.valueOf(tax));
//            settle.setTaxRate(Settle.TAX_RATE);
//
//            settle.setSupplierId(supplierId);
//            settle.setMemo("结算单" + dateStr);
//            settle.setCreateTime(today);
//            settle.setUpdateTime(today);
//            settle.setCreator(supplier.getName());
//            settle.setHandleStatus(SettleHandleStatus.PLATFORM_AUDITING);
//        }
//
//        // 构建明细订单关联id实体
//        List<SettleOrder> settleOrderList = new ArrayList<>();
//        double tmpCommission = 0d;
//        for (int i = 0; i < orderProdCaseList.size(); i++) {
//            OrderProdSettle orderProdCase = orderProdCaseList.get(i);
//            double orderProdCommission = 0d;
//            if (i == orderProdCaseList.size() - 1) {
//                // 前面损失的精度，在最后一个产品处补全
//                orderProdCommission = AmountUtils.sub(commission, tmpCommission);
//            } else {
//                orderProdCommission = AmountUtils.div(AmountUtils.mul((1 - taxRate), orderProdCase.getCharge().doubleValue()), 1, 2);
//            }
//
//            SettleOrder settleOrder = new SettleOrder();
//            {
//                settleOrder.toNew();
//                settleOrder.setOrderId(orderProdCase.getOrderId());
//                settleOrder.setOrderProdId(orderProdCase.getOrderProdId());
//                settleOrder.setCost(orderProdCase.getCost());
//                settleOrder.setCharge(orderProdCase.getCharge());
//                settleOrder.setCommission(BigDecimal.valueOf(orderProdCommission));
//
//                settleOrder.setCreatorId(supplierId);
//                settleOrder.setCreateTime(today);
//                settleOrder.setUpdateTime(today);
//                settleOrder.setCreator(supplier.getName());
//            }
//
//            settleOrderList.add(settleOrder);
//
//
//        }
//        settle.setSettleOrderList(settleOrderList);
//
//        // 保存
//        settle = save(settle);
//
//        // 创建审核日志
//        List<AuditLog> logList = getExtenAuditLogList(settle.getId(), SessionManager.getUserId());
//        settle.setAuditLogList(logList);
//
//        auditLogService.saves(logList);
//
//        // 更新产品订单，结算状态改为结算中
//        orderProdService.updateSettleStatus(orderProdIds, SettleStatus.DO_SETTLEMENT);
//        Result<Settle> result = new Result<>();
//        result.setObj(settle);
//        return result;
    }

    @Override
    public Boolean updateStatusHandleStatus(int settleId, SettleHandleStatus handleStatus) {
        UpdateBuilder builder = UpdateBuilder.getInstance();
        {
            builder.update("so_settle");
            builder.set("so_settle.`handle_status`", handleStatus.getValue());
            builder.where("id=" + settleId + "");
        }

        return this.pm.executeNonQuery(builder.toSQL(), null) > 0;
    }

    //合同申请审批流：提交人（级别:0,状态:审核通过）-》部门领导（级别:1,状态:待审核）->服务商管理员(级别:2,状态:等待)->合同采购专员(级别:3,状态:等待)->法务专员(级别:4,状态:等待)
    protected List<AuditLog> getExtenAuditLogList(Integer formId, Integer addUserId) {
        AbstractAuditLogService auditLogService = AuditFactory.getAudit(SettleAudit.class);
        return auditLogService.execute(formId, addUserId);
    }
}
