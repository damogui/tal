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
    @Override
    protected List<AuditLog> getExtenAuditLogList(Integer formId, Integer addUserId) {
        List<AuditLog> auditLogList = new ArrayList();
        //Platform_Finance_FPZY 财务_发票专员
        Integer level = getCurrentLevel();
        level++;
        //财务_发票专员
        List<Integer> fbzyIds = salesmanService.getEmployeeIdListByRoleCodes(Arrays.asList("Platform_Finance_FPZY"));
        for (Integer fbzyId : fbzyIds) {
            addAuditLog(formId, "发票专员审核", fbzyId, level);
        }
        return auditLogList;
    }

    @Override
    protected AuditLogType setAuditLogType() {
        return AuditLogType.Fbsq;
    }

}
