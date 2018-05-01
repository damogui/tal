package com.gongsibao.trade.web.audit;

import java.util.ArrayList;
import java.util.List;

import com.gongsibao.bd.service.auditLog.AbstractAuditService;
import com.gongsibao.bd.service.auditLog.AuditServiceFactory;
import com.gongsibao.bd.service.auditLog.AuditSettleService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.trade.web.dto.AuditLogDTO;

public class AuditSettleController extends AuditBaseController {

	@Override
	protected AbstractAuditService getAuditService() {

		return AuditServiceFactory.create(AuditSettleService.class);
	}


    /*回款审核流程*/
    public List<AuditLogDTO> getAuditLogList(Integer id) {
        List<AuditLog> logList = new ArrayList<AuditLog>();
        List<AuditLogDTO> logDtos = new ArrayList<AuditLogDTO>();

        logList = super.getAuditLogList(id, AuditLogType.Sksq);
        for (AuditLog item : logList
                ) {
            AuditLogDTO auditLogDTO = new AuditLogDTO();
            auditLogDTO.setId(item.getId());
            auditLogDTO.setCreator(item.getEmployee() == null ? "" : item.getEmployee().getName());
            auditLogDTO.setOption(item.getStatus().getText());
            auditLogDTO.setRemark(item.getContent());
            auditLogDTO.setCreateTime(item.getCreateTime());
            logDtos.add(auditLogDTO);
        }
        return logDtos;
    }
}
