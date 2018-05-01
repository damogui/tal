package com.gongsibao.bd.service.auditLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.netsharp.communication.ServiceFactory;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.supplier.base.ISalesmanService;

/**
 * 退单申请审核：提交人（级别:0,状态:审核通过）-》部门领导（级别:1,状态:待审核）-》服务商管理员（级别:2,状态:等待）-》收退款专员（级别:3,状态:等待）-》法务专员（级别:4,状态:等待）
 * @author Administrator
 *
 */
public class AuditRefundService extends AbstractAuditService{
	ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
	@Override
	protected List<AuditLog> getExtenAuditLogList(Integer formId,Integer addUserId) {
		List<AuditLog> auditLogList = new ArrayList<AuditLog>();
        //Platform_Finance_STKZY 财务_收退款专员、Platform_Law_FWZY 法务_法务专员
        Integer level = getCurrentLevel();
        level++;
        //财务_收退款专员
        List<Integer> stkzyIds = salesmanService.getEmployeeIdListByRoleCodes(Arrays.asList("Platform_Finance_STKZY"));
        for (Integer stkzyId : stkzyIds) {
        	auditLogList.add(addAuditLog(formId, "收退款专员审核", stkzyId, level));
        }

        //法务_法务专员
        level++;
        List<Integer> fWZYIds = salesmanService.getEmployeeIdListByRoleCodes(Arrays.asList("Platform_Law_FWZY"));
        for (Integer fWZYId : fWZYIds) {
        	auditLogList.add(addAuditLog(formId, "法务专员审核", fWZYId, level));
        }

        return auditLogList;
	}

	@Override
	protected AuditLogType setAuditLogType() {
		// TODO Auto-generated method stub
		return AuditLogType.Tdsq;
	}

    @Override
    protected String setActionPath() {
        return "gsb/crm/audit/refund";
    }


}
