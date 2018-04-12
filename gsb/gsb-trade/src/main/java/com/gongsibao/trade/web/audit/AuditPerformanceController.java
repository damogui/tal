package com.gongsibao.trade.web.audit;

import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.bd.service.auditLog.OrderPerformanceAudit;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.trade.base.INDepReceivableService;
import com.gongsibao.trade.web.dto.AuditLogDTO;
import com.gongsibao.trade.web.dto.NDepReceivableDTO;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class AuditPerformanceController extends AuditBaseController {

    // 订单业绩审核
    AbstractAuditLogService auditLogService = AuditFactory.getAudit (OrderPerformanceAudit.class);

    /**
     * 审核通过 注：参数未定
     *
     * @return
     */
    public Boolean approved(Integer auditLogId, String remark) {
        boolean auditResult = auditLogService.audit (AuditState.PASS, auditLogId, remark);

        if (auditResult) {

//回写数据
        } else {

            //
        }

        return  auditResult;


    }

    /**
     * 驳回 注：参数未定
     *
     * @return
     */
    public Boolean rejected(Integer auditLogId, String remark) {
        return auditLogService.audit (AuditState.NOTPASS, auditLogId, remark);
    }

    /*获取订单业绩划分展示根据订单id*/
    public List<NDepReceivableDTO> getOrderCutPerformance(Integer orderId) {

        INDepReceivableService nDepReceivableService = ServiceFactory.create (INDepReceivableService.class);
        List<NDepReceivable> depReceivables = new ArrayList<NDepReceivable> ();

        List<NDepReceivableDTO> depReceivableDTOs = new ArrayList<NDepReceivableDTO> ();
        Oql oql = new Oql ();
        {
            oql.setType (NDepReceivable.class);
            oql.setSelects ("amount,department.{name},supplier.{name},salesman.{name}");
            oql.setFilter ("order_id=?");
            oql.getParameters ().add ("order_id", orderId, Types.INTEGER);

        }
        depReceivables = nDepReceivableService.queryList (oql);
        for (NDepReceivable item : depReceivables
                ) {

            NDepReceivableDTO nDepReceivableDTO = new NDepReceivableDTO ();

            nDepReceivableDTO.setId (item.getId ());
            nDepReceivableDTO.setSuppliername (item.getSupplier ().getName ());
            nDepReceivableDTO.setDepartmentname (item.getDepartment ().getName ());
            nDepReceivableDTO.setSalesmanname (item.getSalesman ().getName ());
            nDepReceivableDTO.setAmount (item.getAmount ());
            depReceivableDTOs.add (nDepReceivableDTO);

        }


        return depReceivableDTOs;

    }

    /*订单业绩审核流程*/
    public List<AuditLogDTO> getAuditLogList(Integer id) {
        List<AuditLog> logList = new ArrayList<AuditLog> ();
        List<AuditLogDTO> logDtos = new ArrayList<AuditLogDTO> ();

        logList = super.getAuditLogList (id, AuditLogType.DdYjSq.getValue ());
        for (AuditLog item : logList
                ) {
            AuditLogDTO auditLogDTO = new AuditLogDTO ();
            auditLogDTO.setId (item.getId ());
            auditLogDTO.setCreator (item.getEmployee ()==null?"":item.getEmployee ().getName ());
            auditLogDTO.setOption (item.getStatus ().getText ());
            auditLogDTO.setRemark (item.getContent ());
            //auditLogDTO.setCreateTime (item.getCreateTime ().toString ());
            logDtos.add (auditLogDTO);
        }
        return logDtos;
    }


}
