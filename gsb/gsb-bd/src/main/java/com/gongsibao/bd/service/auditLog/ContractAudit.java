package com.gongsibao.bd.service.auditLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.supplier.base.ISalesmanService;
import org.netsharp.communication.ServiceFactory;

/**
 * 合同申请审核
 *
 * @author Administrator
 */
public class ContractAudit extends AbstractAuditLogService {

    ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);

    //合同申请审批流：提交人（级别:0,状态:审核通过）-》分公司总经理（级别:1,状态:待审核）->事业部总经理(合同额大于5w时有或有违约责任事项时)(级别:2,状态:等待)->合同采购专员(级别:3,状态:等待)->法务专员(级别:4,状态:等待)
    @Override
    protected List<AuditLog> getExtenAuditLogList(Integer formId, Integer addUserId) {
        List<AuditLog> auditLogList = new ArrayList<AuditLog>();
        //Platform_Finance_STKZY 财务_收退款专员、Platform_Law_FWZY 法务_法务专员
        Integer level = getCurrentLevel();
        level++;
        //财务_收退款专员
        List<Integer> stkzyIds = salesmanService.getEmployeeIdListByRoleCodes(Arrays.asList("Platform_Finance_HTCGZY"));
        for (Integer stkzyId : stkzyIds) {
            addAuditLog(formId, "合同采购专员审核", stkzyId, level);
        }

        //法务_法务专员
        level++;
        List<Integer> fWZYIds = salesmanService.getEmployeeIdListByRoleCodes(Arrays.asList("Platform_Law_FWZY"));
        for (Integer fWZYId : fWZYIds) {
            addAuditLog(formId, "法务专员审核", fWZYId, level);
        }

        return auditLogList;
    }


    @Override
    protected AuditLogType setAuditLogType() {
        return AuditLogType.Htsq;
    }


}
