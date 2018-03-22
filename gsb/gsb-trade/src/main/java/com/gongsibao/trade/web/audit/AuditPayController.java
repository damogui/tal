package com.gongsibao.trade.web.audit;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.trade.service.action.audit.AuditState;
import com.gongsibao.trade.web.dto.AuditLogDTO;

import java.util.ArrayList;
import java.util.List;

public class AuditPayController extends AuditBaseController{

	/**
	 * 审核通过 注：参数未定
	 * 
	 * @return
	 */
	public Boolean approved(Integer auditLogId) {

		return auditService.auditPay(AuditState.PASS, auditLogId, null);
	}

	/**
	 * 驳回 注：参数未定
	 * 
	 * @return
	 */
	public Boolean rejected(Integer auditLogId, String remark) {

		return auditService.auditPay(AuditState.NOTPASS, auditLogId, remark);
	}

    /*订单业绩审核流程*/
    public List<AuditLogDTO> getAuditLogList(Integer id) {
        List<AuditLog> logList = new ArrayList<AuditLog> ();
        List<AuditLogDTO> logDtos = new ArrayList<AuditLogDTO> ();

        logList = super.getAuditLogList (id, AuditLogType.Sksq.getValue ());
        for (AuditLog item : logList
                ) {
            AuditLogDTO auditLogDTO = new AuditLogDTO ();
            auditLogDTO.setId (item.getId ());
            auditLogDTO.setCreator (item.getCreator ());
            auditLogDTO.setOption (item.getStatus ().getText ());
            auditLogDTO.setRemark (item.getContent ());
            auditLogDTO.setCreateTime (item.getCreateTime ().toString ());
            logDtos.add (auditLogDTO);
        }
        return logDtos;
    }

}
