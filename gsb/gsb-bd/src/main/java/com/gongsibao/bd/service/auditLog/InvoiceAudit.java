package com.gongsibao.bd.service.auditLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.supplier.base.ISalesmanService;
import org.netsharp.communication.ServiceFactory;

/**
 * 发票申请审核
 *
 * @author Administrator
 */
public class InvoiceAudit extends AbstractAuditLogService {

    ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);

    //发票申请审批流：提交人（级别:0,状态:审核通过）-》发票专员（级别:1,状态:待审核）
    @Override
    protected List<AuditLog> getExtenAuditLogList(Integer formId, Integer addUserId) {
        List<AuditLog> auditLogList = new ArrayList();
        //Platform_Finance_FPZY 财务_发票专员
        Integer level = getCurrentLevel();
        level++;
        //财务_发票专员
        List<Integer> fbzyIds = salesmanService.getEmployeeIdListByRoleCodes(Arrays.asList("Platform_Finance_FPZY"));
        for (Integer fbzyId : fbzyIds) {
            auditLogList.add(addAuditLog(formId, "发票专员审核", fbzyId, level));
        }
        return auditLogList;
    }

    @Override
    protected AuditLogType setAuditLogType() {
        return AuditLogType.Fbsq;
    }

    @Override
    protected AuditLog getDirectLeaderAudit(Integer formId, Integer addUserId) {
        //发票申请不需要，部门领导审核
        return null;
    }

    @Override
    protected AuditLog getSuperiorLeaderAudit(Integer formId, Integer addUserId) {
        //发票申请不需要，服务商领导审核
        return null;
    }

    @Override
    protected String setActionPath() {
        return "gsb/crm/audit/invoice";
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
