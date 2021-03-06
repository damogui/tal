//package com.gongsibao.trade.service;
//
//import java.sql.Types;
//import java.util.Date;
//import java.util.List;
//
//import org.apache.commons.lang.StringUtils;
//import org.netsharp.communication.Service;
//import org.netsharp.core.Oql;
//import org.netsharp.core.QueryParameters;
//import org.netsharp.service.PersistableService;
//import org.netsharp.util.sqlbuilder.UpdateBuilder;
//
//import com.gongsibao.entity.bd.AuditLog;
//import com.gongsibao.entity.bd.dic.AuditLogStatusType;
//import com.gongsibao.entity.bd.dic.AuditLogType;
//
//@Service
//public class AuditService extends PersistableService<AuditLog> implements IAuditService {
//
//    public AuditService() {
//        super();
//        this.type = AuditLog.class;
//    }
//
//    //审核通过
//    @Override
//    public Boolean auditApproved(Integer auditId, String remark) {
//        AuditLog auditLog = byId(auditId);
//        if (auditLog == null) {
//            return false;
//        }
//
//        //将自己的状态改为【审核通过】
//        updateStatus(auditLog.getId(), AuditLogStatusType.AUDITPASS, remark);
//        //将等于自己同级别的审核记录修改成【关闭】,且不包括自己
//        updateStatusToClose(auditLog, "=");
//        //将下一级别的审核记录修改成【待审核】
//        updateStatusTOAUDITByNextLevel(auditLog);
//
//        return true;
//    }
//
//    //审核驳回
//    @Override
//    public Boolean auditRejected(Integer auditId, String remark) {
//
//        AuditLog auditLog = byId(auditId);
//        //将自己的状态改为【审核驳回】
//        updateStatus(auditLog.getId(), AuditLogStatusType.AUDITREJECT, remark);
//        //将大于自己同级别的审核记录修改成【关闭】,且不包括自己
//        updateStatusToClose(auditLog, ">=");
//        return true;
//    }
//
//    @Override
//    public List<AuditLog> getByTypeIdFormId(AuditLogType auditLogType, Integer formId) {
//
//        Oql oql = new Oql();
//        {
//            oql.setType(this.type);
//            oql.setSelects("auditLog.*,employee.{id,name}");
//            oql.setFilter("type_id = ? and form_id = ?");
//            oql.getParameters().add("typeId", auditLogType.getValue(), Types.INTEGER);
//            oql.getParameters().add("formId", formId, Types.INTEGER);
//            oql.setOrderby("level asc");//级别升序排列
//        }
//
//        List<AuditLog> auditLogs = this.pm.queryList(oql);
//
//        return auditLogs;
//    }
//
//    //region 私有方法
//    //修改审核状态,和审批记录
//    private void updateStatus(Integer id, AuditLogStatusType auditLogStatusType, String remark) {
//        remark = StringUtils.trimToEmpty(remark);
//        UpdateBuilder updateBuilder = new UpdateBuilder();
//        {
//            updateBuilder.update("bd_audit_log");
//            updateBuilder.set("status_id", auditLogStatusType.getValue());
//            updateBuilder.set("audit_time", new Date());
//            updateBuilder.set("remark", remark);
//            updateBuilder.where("pkid=?");
//        }
//
//        String sql = updateBuilder.toSQL();
//        QueryParameters qps = new QueryParameters();
//        qps.add("id", id, Types.INTEGER);
//        this.pm.executeNonQuery(sql, qps);
//    }
//
//    //将等于（或大于）自己同级别的审核记录修改成【关闭】,且不包括自己
//    private void updateStatusToClose(AuditLog auditLog, String operationStr) {
//        UpdateBuilder updateBuilder = new UpdateBuilder();
//        {
//            updateBuilder.update("bd_audit_log");
//            updateBuilder.set("status_id", AuditLogStatusType.Close.getValue());
//            updateBuilder.where("level " + operationStr + " " + auditLog.getLevel() + " " +
//                    "and type_id=" + auditLog.getType().getValue() + " " +
//                    "and form_id = " + auditLog.getFormId() + " " +
//                    "and pkid !=" + auditLog.getId() + " ");
//        }
//        String sql = updateBuilder.toSQL();
//        this.pm.executeNonQuery(sql, null);
//    }
//
//    //将下一级别的审核记录修改成【待审核】
//    private void updateStatusTOAUDITByNextLevel(AuditLog auditLog) {
//        if (!auditLog.getLevel().equals(auditLog.getMaxLevel())) {
//            Integer nextLevel = auditLog.getLevel() + 1;
//            UpdateBuilder updateBuilder = new UpdateBuilder();
//            {
//                updateBuilder.update("bd_audit_log");
//                updateBuilder.set("status_id", AuditLogStatusType.TOAUDIT.getValue());
//                updateBuilder.where("level = " + nextLevel + " and form_id = " + auditLog.getFormId() + " and type_id=" + auditLog.getType().getValue() + " ");
//            }
//
//            String sql = updateBuilder.toSQL();
//            this.pm.executeNonQuery(sql, null);
//        }
//    }
//
//	@Override
//	public AuditLog getById(Integer id) {
//		Oql oql = new Oql();
//        {
//            oql.setType(this.type);
//            oql.setSelects("*");
//            oql.setFilter("pkid = ?");
//            oql.getParameters().add("pkid", id, Types.INTEGER);
//        }
//
//       AuditLog auditLogs = this.pm.queryFirst(oql);
//        return auditLogs;
//	}
//
//    // endregion
//
//
//}