package com.gongsibao.trade.service;

import com.gongsibao.entity.bd.dic.AuditLogStatusType;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.trade.base.IAuditService;
import com.gongsibao.trade.service.action.audit.AuditContext;
import com.gongsibao.trade.service.action.audit.AuditState;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import java.sql.Types;

@Service
public class AuditService extends PersistableService<AuditLog> implements IAuditService {

    public AuditService() {
        super();
        this.type = AuditLog.class;
    }

    @Override
    public Boolean auditCarryover(AuditState state, Integer auditLogId, String remark) {

        return this.audit(state, auditLogId, remark, "gsb/crm/audit/carryover");
    }

    @Override
    public Boolean auditContract(AuditState state, Integer auditLogId, String remark) {

        return this.audit(state, auditLogId, remark, "gsb/crm/audit/contract");
    }

    @Override
    public Boolean auditCost(AuditState state, Integer auditLogId, String remark) {

        return this.audit(state, auditLogId, remark, "gsb/crm/audit/cost");
    }

    @Override
    public Boolean auditInvoice(AuditState state, Integer auditLogId, String remark) {

        return this.audit(state, auditLogId, remark, "gsb/crm/audit/invoice");
    }

    @Override
    public Boolean auditOrder(AuditState state, Integer auditLogId, String remark) {

        return this.audit(state, auditLogId, remark, "gsb/crm/audit/order");
    }

    @Override
    public Boolean auditPay(AuditState state, Integer auditLogId, String remark) {

        return this.audit(state, auditLogId, remark, "gsb/crm/audit/pay");
    }

    @Override
    public Boolean auditPerformance(AuditState state, Integer auditLogId, String remark) {

        return this.audit(state, auditLogId, remark, "gsb/crm/audit/performance");
    }

    @Override
    public Boolean auditRefund(AuditState state, Integer auditLogId, String remark) {

        return this.audit(state, auditLogId, remark, "gsb/crm/audit/refund");
    }

    @Override
    public Boolean auditStage(AuditState state, Integer auditLogId, String remark) {

        return this.audit(state, auditLogId, remark, "gsb/crm/audit/stage");
    }

    private Boolean audit(AuditState state, Integer auditLogId, String remark, String actionPath) {

        AuditContext auditContext = new AuditContext();
        {
            // 这里根据传入的参数构造;
            auditContext.setState(state);
            auditContext.setAuditLogId(auditLogId);
            auditContext.setremark(remark);
        }
        ActionContext ctx = new ActionContext();
        {
            ctx.setPath(actionPath);
            ctx.setItem(auditContext);
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
    }

    //审核通过
    @Override
    public Boolean auditApproved(Integer auditId) {
        AuditLog auditLog = byId(auditId);
        if (auditLog == null) {
            return false;
        }

        //将自己的状态改为【审核通过】
        updateStatus(auditLog.getId(), AuditLogStatusType.AUDITPASS, "");
        //将等于自己同级别的审核记录修改成【关闭】,且不包括自己
        updateStatusToClose(auditLog, "=");
        //将下一级别的审核记录修改成【待审核】
        updateStatusTOAUDITByNextLevel(auditLog);

        return true;
    }

    //审核驳回
    @Override
    public Boolean auditRejected(Integer auditId, String remark) {

        AuditLog auditLog = byId(auditId);
        updateStatus(auditLog.getId(), AuditLogStatusType.AUDITREJECT, remark);
        //将大于自己同级别的审核记录修改成【关闭】,且不包括自己
        updateStatusToClose(auditLog, ">=");

        return true;
    }


    //region 私有方法
    //修改审核状态,和审批记录
    private void updateStatus(Integer id, AuditLogStatusType auditLogStatusType, String remark) {
        UpdateBuilder updateBuilder = new UpdateBuilder();
        {
            updateBuilder.update("bd_audit_log");
            updateBuilder.set("status_id", auditLogStatusType.getValue());
            updateBuilder.set("remark", remark);
            updateBuilder.where("pkid=?");
        }

        String sql = updateBuilder.toSQL();
        QueryParameters qps = new QueryParameters();
        qps.add("id", id, Types.INTEGER);
        this.pm.executeNonQuery(sql, qps);
    }

    //将等于（或大于）自己同级别的审核记录修改成【关闭】,且不包括自己
    private void updateStatusToClose(AuditLog auditLog, String operationStr) {
        UpdateBuilder updateBuilder = new UpdateBuilder();
        {
            updateBuilder.update("bd_audit_log");
            updateBuilder.set("status_id", AuditLogStatusType.Close.getValue());
            updateBuilder.where("level " + operationStr + " " + auditLog.getLevel() + " " +
                    "and type_id=" + auditLog.getType().getValue() + " " +
                    "and form_id = " + auditLog.getFormId() + " " +
                    "and pkid !=" + auditLog.getId() + " ");
        }
        this.pm.executeNonQuery(updateBuilder.toString(), null);
    }

    //将下一级别的审核记录修改成【待审核】
    private void updateStatusTOAUDITByNextLevel(AuditLog auditLog) {
        if (!auditLog.getLevel().equals(auditLog.getMaxLevel())) {
            Integer nextLevel = auditLog.getLevel() + 1;
            UpdateBuilder updateBuilder = new UpdateBuilder();
            {
                updateBuilder.update("bd_audit_log");
                updateBuilder.set("status_id", AuditLogStatusType.TOAUDIT.getValue());
                updateBuilder.where("level = " + nextLevel + " and form_id = " + auditLog.getFormId() + " and type_id=" + auditLog.getType().getValue() + " ");
            }

            String sql = updateBuilder.toSQL();
            this.pm.executeNonQuery(sql, null);
        }
    }

    // endregion


}