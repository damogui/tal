package com.gongsibao.u8.web;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.ListPart;

import com.gongsibao.u8.base.IReceivablesAuditDTOService;

public class ReceivablesAuditDTOController extends ListPart {

	// 根据支付id获取关联的订单和支付信息
	public Map<String, Object> getOrderInfoListByPayId(Integer payId) {
		Map<String, Object> resMap = new HashMap<>();
		IReceivablesAuditDTOService receivablesAuditDTOService = ServiceFactory.create(IReceivablesAuditDTOService.class);
		resMap = receivablesAuditDTOService.getOrderInfoListByPayId(payId);
		return resMap;
	}

	public Map<String, Object> payAudit(Integer payId, Integer auditId,Integer auditStatusId,String remark,String confirmTime) {
		Map<String, Object> resMap = new HashMap();
		IReceivablesAuditDTOService receivablesAuditDTOService = ServiceFactory.create(IReceivablesAuditDTOService.class);
		receivablesAuditDTOService.payAudit(payId, auditId, auditStatusId, remark, confirmTime);
		return resMap;
	}

}
