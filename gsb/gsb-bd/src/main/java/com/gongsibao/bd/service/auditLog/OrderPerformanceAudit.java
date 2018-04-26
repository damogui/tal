package com.gongsibao.bd.service.auditLog;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.supplier.base.ISalesmanService;
import org.netsharp.communication.ServiceFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by win on 2018/3/24.
 */
/*订单业绩审核*/
public class OrderPerformanceAudit  extends AbstractAuditLogService{

    @Override
    protected List<AuditLog> getExtenAuditLogList(Integer formId, Integer addUserId) {
        ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
        List<AuditLog> auditLogList = new ArrayList<AuditLog> ();
        //Platform_Finance_STKZY 财务_收退款专员
        Integer level = getCurrentLevel();
        level++;
        //财务_收退款专员
        List<Integer> stkzyIds = salesmanService.getEmployeeIdListByRoleCodes(Arrays.asList("Platform_Finance_STKZY"));
        for (Integer stkzyId : stkzyIds) {
            auditLogList.add(addAuditLog(formId, "收退款专员审核", stkzyId, level));
        }
        return auditLogList;
    }

//提交人（级别:0,状态:审核通过）-》收退款专员（级别:1,状态:待审核）
    @Override
    protected AuditLog getDirectLeaderAudit(Integer formId, Integer addUserId) {
        return null;
    }

    @Override
    protected AuditLog getSuperiorLeaderAudit(Integer formId, Integer addUserId) {
        return null;
    }
    @Override
    protected AuditLogType setAuditLogType() {
        return AuditLogType.DdYjSq;
    }

    @Override
    protected String setActionPath() {
        return "gsb/crm/audit/performance";
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
