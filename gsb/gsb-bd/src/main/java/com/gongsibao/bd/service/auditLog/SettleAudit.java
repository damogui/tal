package com.gongsibao.bd.service.auditLog;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.supplier.base.ISalesmanService;
import org.netsharp.communication.ServiceFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 结算申请审核
 *
 * @author Administrator
 */
public class SettleAudit extends AbstractAuditLogService {

    ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);

    // 结算申请审批流：提交人（级别:0,状态:审核通过）-》平台审核（级别:1,状态:待审核）->财务审核(级别:2,状态:等待)
    @Override
    protected List<AuditLog> getExtenAuditLogList(Integer formId, Integer addUserId) {
        List<AuditLog> auditLogList = new ArrayList<AuditLog>();
        //Platform_Operation_Leader 运营_经理、Platform_Finance_STKZY 财务_收退款专员
        Integer level = getCurrentLevel();
        level++;
        // 平台运营经理审核
        List<Integer> yyJlIds = salesmanService.getEmployeeIdListByRoleCodes(Arrays.asList("Platform_Operation_Leader"));
        for (Integer yyJlId : yyJlIds) {
            auditLogList.add(addAuditLog(formId, "平台运营经理审核", yyJlId, level));
        }

        // 财务审核
        level++;
        List<Integer> stkZyIds = salesmanService.getEmployeeIdListByRoleCodes(Arrays.asList("Platform_Finance_STKZY"));
        for (Integer stkZyId : stkZyIds) {
            auditLogList.add(addAuditLog(formId, "财务专员审核", stkZyId, level));
        }
        return auditLogList;
    }

    @Override
    protected AuditLogType setAuditLogType() {
        return AuditLogType.Jssh;
    }

    @Override
    protected AuditLog getDirectLeaderAudit(Integer formId, Integer addUserId) {
        // 结算申请不需要，部门领导审核
        return null;
    }

    @Override
    protected AuditLog getSuperiorLeaderAudit(Integer formId, Integer addUserId) {
        // 结算申请不需要，服务商领导审核
        return null;
    }


    @Override
    protected String setActionPath() {
        return "gsb/platform/audit/settle";
    }
//    gsb/supplier/audit/settle
}
