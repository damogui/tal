package com.gongsibao.u8.base;

import java.util.Map;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.dto.ReceivablesAuditDTO;

public interface IReceivablesAuditDTOService extends IPersistableService<ReceivablesAuditDTO> {

	Map<String, Object> getOrderInfoListByPayId(Integer payId);
	
	Integer payAudit(Integer payId, Integer auditId,Integer auditStatusId,String remark,String confirmTime);
}
