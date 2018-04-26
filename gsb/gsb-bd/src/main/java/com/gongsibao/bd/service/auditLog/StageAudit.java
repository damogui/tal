package com.gongsibao.bd.service.auditLog;

import java.util.List;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;

/**
 * 分期申请审核：提交人（级别:0,状态:审核通过）-》部门领导（级别:1,状态:待审核）-》服务商管理员（级别:2,状态:等待）
 *
 * @author Administrator
 */
public class StageAudit extends AbstractAuditLogService {

    @Override
    protected List<AuditLog> getExtenAuditLogList(Integer formId,
                                                  Integer addUserId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected AuditLogType setAuditLogType() {
        // TODO Auto-generated method stub
        return AuditLogType.Fqsq;
    }

    @Override
    protected String setActionPath() {
        return "gsb/crm/audit/stage";
    }

    @Override
    public List<String> getAuditPassTel() {
        return null;
    }

    @Override
    public List<String> getAuditFailTel() {
        return null;
    }

    @Override
    public List<String> getAuditWaitTel(int level) {
        return null;
    }
}
