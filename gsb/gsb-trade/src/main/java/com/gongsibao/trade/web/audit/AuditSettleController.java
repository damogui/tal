package com.gongsibao.trade.web.audit;

import com.gongsibao.bd.service.auditLog.*;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.settle.dict.SettleHandleStatus;
import com.gongsibao.trade.base.IAuditService;
import com.gongsibao.trade.base.settle.ISettleService;
import com.gongsibao.trade.web.dto.AuditLogDTO;
import org.netsharp.communication.ServiceFactory;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.List;

public class AuditSettleController extends AuditBaseController {


    // 结算审核
    AbstractAuditLogService auditLogService = AuditFactory.getAudit(SettleAudit.class);


    @Override
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

    /*回款审核流程*/
    public List<AuditLogDTO> getAuditLogList(Integer id) {
        List<AuditLog> logList = new ArrayList<AuditLog>();
        List<AuditLogDTO> logDtos = new ArrayList<AuditLogDTO>();

        logList = super.getAuditLogList(id, AuditLogType.Sksq.getValue());
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
