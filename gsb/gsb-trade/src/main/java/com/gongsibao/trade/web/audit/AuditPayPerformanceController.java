package com.gongsibao.trade.web.audit;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.trade.base.INDepPayService;
import com.gongsibao.trade.base.INDepReceivableService;
import com.gongsibao.trade.service.action.audit.AuditState;
import com.gongsibao.trade.web.dto.AuditLogDTO;
import com.gongsibao.trade.web.dto.NDepReceivableDTO;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by win on 2018/3/23.
 */
public class AuditPayPerformanceController  extends AuditBaseController {

    /**
     * 审核通过 注：参数未定
     *
     * @return
     */
    public Boolean approved(Integer auditLogId) {

        return auditService.auditPerformance (AuditState.PASS, auditLogId, null);
    }

    /**
     * 驳回 注：参数未定
     *
     * @return
     */
    public Boolean rejected(Integer auditLogId, String remark) {

        return auditService.auditPerformance (AuditState.NOTPASS, auditLogId, remark);
    }

    /*获取订单业绩划分展示根据订单id*/
    public List<NDepReceivableDTO> getPayCutPerformance(Integer orderId) {

        INDepPayService   nDepPayService = ServiceFactory.create (INDepPayService.class);
        List<NDepPay>  nDepPas= new ArrayList<NDepPay> ();

        List<NDepReceivableDTO> depReceivableDTOs = new ArrayList<NDepReceivableDTO> ();
        Oql oql = new Oql ();
        {
            oql.setType (NDepPay.class);
            oql.setSelects ("amount,department.{name},supplier.{name},salesman.{name}");
            oql.setFilter ("order_id=?");
            oql.getParameters ().add ("order_id", orderId, Types.INTEGER);

        }
        nDepPas = nDepPayService.queryList (oql);
        for (NDepPay item : nDepPas
                ) {

            NDepReceivableDTO nDepReceivableDTO = new NDepReceivableDTO ();

            nDepReceivableDTO.setId (item.getId ());
            nDepReceivableDTO.setSuppliername (item.getSupplier ()==null?"":item.getSupplier ().getName ());
            nDepReceivableDTO.setDepartmentname (item.getDepartment ()==null?"":item.getDepartment ().getName ());
            nDepReceivableDTO.setSalesmanname (item.getSalesman ()==null?"":item.getSalesman ().getName ());
            nDepReceivableDTO.setAmount (item.getAmount ());
            depReceivableDTOs.add (nDepReceivableDTO);

        }


        return depReceivableDTOs;

    }

    /*回款业绩审核流程*/
    public List<AuditLogDTO> getAuditLogList(Integer id) {
        List<AuditLog> logList = new ArrayList<AuditLog> ();
        List<AuditLogDTO> logDtos = new ArrayList<AuditLogDTO> ();

        logList = super.getAuditLogList (id, AuditLogType.Skyjsh.getValue ());
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
