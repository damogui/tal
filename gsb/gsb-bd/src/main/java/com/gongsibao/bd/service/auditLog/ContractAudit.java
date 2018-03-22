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

    @Override
    protected List<AuditLog> getExtenAuditLogList(Integer formId, Integer addUserId) {
        List<AuditLog> auditLogList = new ArrayList<AuditLog>();
        //Platform_Finance_STKZY 财务_收退款专员、Platform_Law_FWZY 法务_法务专员
        Integer level = 0;
        //财务_收退款专员
        List<Integer> stkzyIds = salesmanService.getEmployeeIdListByRoleCodes(Arrays.asList("Platform_Finance_STKZY"));
        //法务_法务专员
        level++;
        List<Integer> fWZYIds = salesmanService.getEmployeeIdListByRoleCodes(Arrays.asList("Platform_Law_FWZY"));
        return auditLogList;
    }


	@Override
	protected AuditLogType setAuditLogType() {
		// TODO Auto-generated method stub
		return AuditLogType.Htsq;
	}
    
}
