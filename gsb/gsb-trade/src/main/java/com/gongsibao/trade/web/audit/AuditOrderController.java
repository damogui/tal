package com.gongsibao.trade.web.audit;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.bd.service.auditLog.ChangeOrderPriceAudit;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.trade.web.dto.AuditLogDTO;

import java.util.ArrayList;
import java.util.List;

public class AuditOrderController extends AuditBaseController{

    // 订单（改价）审核
    AbstractAuditLogService auditLogService = AuditFactory.getAudit(ChangeOrderPriceAudit.class);

	/**
	 * 审核通过 注：参数未定
	 * 
	 * @return
	 */
	public Boolean approved(Integer auditLogId, String remark) {
        return auditLogService.audit(AuditState.PASS, auditLogId, remark);
	}

	/**
	 * 驳回 注：参数未定
	 * 
	 * @return
	 */
	public Boolean rejected(Integer auditLogId, String remark) {
        return auditLogService.audit(AuditState.NOTPASS, auditLogId, remark);
	}

    /*订单业绩审核流程*/
    public List<AuditLogDTO> getAuditLogList(Integer id) {
        List<AuditLog> logList = new ArrayList<AuditLog> ();
        List<AuditLogDTO> logDtos = new ArrayList<AuditLogDTO> ();

        logList = super.getAuditLogList (id, AuditLogType.DdSq.getValue ());
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
