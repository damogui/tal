package com.gongsibao.trade.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.trade.service.action.audit.AuditState;

public interface IAuditService extends IPersistableService<AuditLog> {


    /**
     * 审核结转
     *
     * @return
     */
    @Transaction
    Boolean auditCarryover(AuditState state, Integer auditLogId, String remark);

    /**
     * 审核合同
     *
     * @return
     */
    @Transaction
    Boolean auditContract(AuditState state, Integer auditLogId, String remark);

    /**
     * 审核成本
     *
     * @return
     */
    @Transaction
    Boolean auditCost(AuditState state, Integer auditLogId, String remark);

    /**
     * 审核发票
     *
     * @return
     */
    @Transaction
    Boolean auditInvoice(AuditState state, Integer auditLogId, String remark);

    /**
     * 审核订单（改价审核）
     *
     * @return
     */
    @Transaction
    Boolean auditOrder(AuditState state, Integer auditLogId, String remark);

    /**
     * 审核回款
     *
     * @return
     */
    @Transaction
    Boolean auditPay(AuditState state, Integer auditLogId, String remark);

    /**
     * 订单业绩审核
     *
     * @return
     */
    @Transaction
    Boolean auditPerformance(AuditState state, Integer auditLogId, String remark);

    /**
     * 退款审核
     *
     * @return
     */
    @Transaction
    Boolean auditRefund(AuditState state, Integer auditLogId, String remark);

    /**
     * 分期审核
     *
     * @return
     */
    @Transaction
    Boolean auditStage(AuditState state, Integer auditLogId, String remark);

    //审核通过
    @Transaction
    Boolean auditApproved(Integer auditId);

    //审核通过
    @Transaction
    Boolean auditRejected(Integer auditId, String remark);

}
