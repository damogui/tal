package com.gongsibao.trade.web.audit;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.bd.service.auditLog.AbstractAuditLogService;
import com.gongsibao.bd.service.auditLog.AuditFactory;
import com.gongsibao.bd.service.auditLog.AuditState;
import com.gongsibao.bd.service.auditLog.PayAudit;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Pay;
import com.gongsibao.trade.base.IOrderPayMapService;
import com.gongsibao.trade.base.IPayService;
import com.gongsibao.trade.web.dto.AuditLogDTO;
import com.gongsibao.trade.web.dto.OrderInfoDTO;
import com.gongsibao.trade.web.dto.OrderPayInfoDTO;
import com.gongsibao.utils.NumberUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.NumUtil;
import org.netsharp.util.StringManager;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuditPayController extends AuditBaseController {


    // 收款（回款）审核
    AbstractAuditLogService auditLogService = AuditFactory.getAudit (PayAudit.class);

    /**
     * 审核通过 注：参数未定
     *
     * @return
     */
    public Boolean approvedPay(Integer auditLogId, String remark, String payTime) {
        boolean auditResult = auditLogService.audit (AuditState.PASS, auditLogId, remark);

        if (auditResult) {
            IPersister<Pay> payIPersister = PersisterFactory.create ();
            //IPersister<AuditLog> auditLogIPersister = PersisterFactory.create ();
            IAuditLogService auditLogService = ServiceFactory.create (IAuditLogService.class);

            AuditLog auditLog = auditLogService.byId (auditLogId);
            String sql = "  UPDATE  so_pay  SET  confirm_time=? WHERE  pkid=?  ";

            QueryParameters qps = new QueryParameters ();
            qps.add ("@confirm_time", payTime, Types.DATE);
            qps.add ("@pkid", auditLog.getFormId (), Types.DATE);
            payIPersister.executeNonQuery (sql, qps);
            //更改确认时间
        } else {


        }

        return auditResult;
    }

    @Override
    public Boolean approved(Integer auditLogId, String remark) {
        return null;
    }

    /**
     * 驳回 注：参数未定
     *
     * @return
     */
    public Boolean rejected(Integer auditLogId, String remark) {
        return auditLogService.audit (AuditState.NOTPASS, auditLogId, remark);
    }

    /*回款审核流程*/
    public List<AuditLogDTO> getAuditLogList(Integer id) {
        List<AuditLog> logList = new ArrayList<AuditLog> ();
        List<AuditLogDTO> logDtos = new ArrayList<AuditLogDTO> ();

        logList = super.getAuditLogList (id, AuditLogType.Sksq.getValue ());
        for (AuditLog item : logList
                ) {
            AuditLogDTO auditLogDTO = new AuditLogDTO ();
            auditLogDTO.setId (item.getId ());
            auditLogDTO.setCreator (item.getEmployee ()==null?"":item.getEmployee ().getName ());
            auditLogDTO.setOption (item.getStatus ().getText ());
            auditLogDTO.setRemark (item.getContent ());
            auditLogDTO.setCreateTime (item.getCreateTime ().toString ());
            logDtos.add (auditLogDTO);
        }
        return logDtos;
    }

    /*获取订单信息、付款凭证、关联订单*/
    public OrderPayInfoDTO getOrderCutPerformanceByPayId(Integer id) {

        IPayService payService = ServiceFactory.create (IPayService.class);
        OrderPayInfoDTO orderPayInfoDTO = new OrderPayInfoDTO ();
        Oql oql = new Oql ();
        {
            oql.setType (Pay.class);
            StringBuilder sb = new StringBuilder ();

            sb.append ("pay.*,");
            sb.append ("pay.u8Bank.name,");
            sb.append ("pay.setOfBooks.name,");
//            oql.setSelects("setOfBooksId,setOfBooks.name,u8Bank.name,offlinePayerName,offlineBankNo,payForOrderCount,amount,offlineRemark,files,");//,orderPayMaps.{soOrder,orderPrice,offlineInstallmentType}
            sb.append ("pay.files.*");
            oql.setSelects (sb.toString ());

            oql.setFilter ("id=?");
            oql.getParameters ().add ("@id", id, Types.INTEGER);

        }
        Pay pay = payService.queryFirst (oql);

        orderPayInfoDTO.setAccountName (pay.getSetOfBooks () == null ? "" : pay.getSetOfBooks ().getName ());
        orderPayInfoDTO.setPayWay (pay.getPayWayType () == null ? "" : pay.getPayWayType ().getText ());
        orderPayInfoDTO.setBankName (pay.getOfflinePayerName ());
        orderPayInfoDTO.setBankNo (pay.getOfflineBankNo ());
        orderPayInfoDTO.setIsMoreOrder (pay.getPayForOrderCount ().getText ());

        orderPayInfoDTO.setAmount (NumberUtils.getRealMoney (pay.getAmount ()).toString ());//转换为元
        orderPayInfoDTO.setMark (pay.getOfflineRemark ());
        orderPayInfoDTO.setFiles (pay.getFiles ());
        // orderPayInfoDTO.setOrderInfos (getOrderInfosByMap (pay.getOrderPayMaps ()));


        return orderPayInfoDTO;
    }

    /*获取关联订单的信息*/
    public List<OrderInfoDTO> getOrderInfosById(Integer id) {

        IOrderPayMapService orderPayMapService = ServiceFactory.create (IOrderPayMapService.class);

        Oql oql = new Oql ();
        {
            oql.setType (OrderPayMap.class);
            oql.setSelects ("soOrder.no,orderPrice,offlineInstallmentType");//,orderPayMaps.{soOrder,orderPrice,offlineInstallmentType}
            oql.setFilter ("payId=?");
            oql.getParameters ().add ("@payId", id, Types.INTEGER);

        }

        List<OrderPayMap> orderPayMaps = new ArrayList<> ();
        orderPayMaps = orderPayMapService.queryList (oql);
        List<OrderInfoDTO> orderInfoDTOs = new ArrayList<> ();
        for (OrderPayMap item : orderPayMaps
                ) {
            OrderInfoDTO orderInfoDTO = new OrderInfoDTO ();
            orderInfoDTO.setOrderNo (item.getSoOrder ().getNo ());
            orderInfoDTO.setOrderCut (NumberUtils.getRealMoney (item.getOrderPrice ()).toString ());
            orderInfoDTO.setPayType (item.getOfflineInstallmentType ().getText ());
            orderInfoDTOs.add (orderInfoDTO);
        }
        return orderInfoDTOs;

    }


}
