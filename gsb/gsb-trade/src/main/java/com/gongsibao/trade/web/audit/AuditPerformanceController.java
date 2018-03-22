package com.gongsibao.trade.web.audit;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.trade.base.INDepReceivableService;
import com.gongsibao.trade.service.action.audit.AuditState;
import com.gongsibao.trade.web.dto.NDepReceivableDTO;
import com.gongsibao.u8.base.ISoOrderService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class AuditPerformanceController extends AuditBaseController {

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
        for (NDepReceivable  item:depReceivables
             ) {

            NDepReceivableDTO  nDepReceivableDTO=new NDepReceivableDTO();

            nDepReceivableDTO.setId (item.getId ());
            nDepReceivableDTO.setSuppliername (item.getSupplier ().getName ());
            nDepReceivableDTO.setDepartmentname (item.getDepartment ().getName ());
            nDepReceivableDTO.setSalesmanname (item.getSalesman ().getName ());
            nDepReceivableDTO.setAmount (item.getAmount ());
            depReceivableDTOs.add (nDepReceivableDTO);

        }


        return depReceivableDTOs;

    }


}
