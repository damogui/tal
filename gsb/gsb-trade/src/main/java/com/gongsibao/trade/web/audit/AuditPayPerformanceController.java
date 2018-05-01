package com.gongsibao.trade.web.audit;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.bd.service.auditLog.AbstractAuditService;
import com.gongsibao.bd.service.auditLog.AuditServiceFactory;
import com.gongsibao.bd.service.auditLog.AuditPayPerformanceService;
import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.INDepPayService;
import com.gongsibao.trade.web.dto.NDepReceivableDTO;
import com.gongsibao.trade.web.dto.OrderInfoDTO;
import com.gongsibao.u8.base.ISoOrderService;

/**
 * Created by win on 2018/3/23.
 */
public class AuditPayPerformanceController extends AuditBaseController {

	@Override
	protected AbstractAuditService getAuditService() {

		return AuditServiceFactory.create(AuditPayPerformanceService.class);
	}


    /*获取订单业绩划分展示根据订单id*/
    public List<NDepReceivableDTO> getPayCutPerformance(Integer orderId) {

        INDepPayService nDepPayService = ServiceFactory.create(INDepPayService.class);
        List<NDepPay> nDepPas = new ArrayList<NDepPay>();

        List<NDepReceivableDTO> depReceivableDTOs = new ArrayList<NDepReceivableDTO>();
        Oql oql = new Oql();
        {
            oql.setType(NDepPay.class);
            oql.setSelects("amount,department.{name},supplier.{name},salesman.{name}");
            oql.setFilter("order_id=?");
            oql.getParameters().add("order_id", orderId, Types.INTEGER);

        }
        nDepPas = nDepPayService.queryList(oql);
        for (NDepPay item : nDepPas
                ) {

            NDepReceivableDTO nDepReceivableDTO = new NDepReceivableDTO();

            nDepReceivableDTO.setId(item.getId());
            nDepReceivableDTO.setSuppliername(item.getSupplier() == null ? "" : item.getSupplier().getName());
            nDepReceivableDTO.setDepartmentname(item.getDepartment() == null ? "" : item.getDepartment().getName());
            nDepReceivableDTO.setSalesmanname(item.getSalesman() == null ? "" : item.getSalesman().getName());
            nDepReceivableDTO.setAmount(item.getAmount());
            depReceivableDTOs.add(nDepReceivableDTO);

        }


        return depReceivableDTOs;

    }


//    /*回款业绩审核流程*/
//    public List<AuditLogDTO> getAuditLogList(Integer id) {
//        List<AuditLog> logList = new ArrayList<AuditLog>();
//        List<AuditLogDTO> logDtos = new ArrayList<AuditLogDTO>();
//
//        logList = super.getAuditLogList(id, AuditLogType.Skyjsh.getValue());
//        for (AuditLog item : logList
//                ) {
//            AuditLogDTO auditLogDTO = new AuditLogDTO();
//            auditLogDTO.setId(item.getId());
//            auditLogDTO.setCreator(item.getEmployee() == null ? "" : item.getEmployee().getName());
//            auditLogDTO.setOption(item.getStatus().getText());
//            auditLogDTO.setContent(item.getContent());
//            auditLogDTO.setRemark(item.getRemark());
//            auditLogDTO.setCreateTime(item.getCreateTime());
//            logDtos.add(auditLogDTO);
//        }
//        return logDtos;
//    }


    /*获取订单信息*/
    public OrderInfoDTO getOrderInfo(Integer orderId) {

        ISoOrderService soOrderService = ServiceFactory.create(ISoOrderService.class);
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        Oql oql = new Oql();
        {
            oql.setType(SoOrder.class);
            oql.setSelects("*");
            oql.setFilter("id=?");
            oql.getParameters().add("id", orderId, Types.INTEGER);

        }
        SoOrder soOrder = soOrderService.queryFirst(oql);
        if (soOrder == null) {
            return orderInfoDTO;
        }
        orderInfoDTO.setOrderNo(soOrder.getNo());
        orderInfoDTO.setPayablePrice(soOrder.getPayablePrice());
        orderInfoDTO.setPaidPrice(soOrder.getPaidPrice());
        orderInfoDTO.setAccountName(soOrder.getAccountName());
        orderInfoDTO.setAccountMobile(soOrder.getAccountMobile());
        orderInfoDTO.setAddTime(soOrder.getCreateTime() == null ? "" : soOrder.getCreateTime().toString());
        orderInfoDTO.setPlatformSource(soOrder.getPlatformSource() == null ? "" : soOrder.getPlatformSource().getText());
        orderInfoDTO.setPayStatus(soOrder.getPayStatus() == null ? "" : soOrder.getPayStatus().getText());
        orderInfoDTO.setInstallmentCount(soOrder.getStageNum().getValue());
        orderInfoDTO.setChannelOrderNo(soOrder.getChannelOrderNo());
        orderInfoDTO.setUnAllotPayPrice(soOrder.getUnAllotPayPrice().toString());
        orderInfoDTO.setRemark(soOrder.getRemark());

        return orderInfoDTO;

    }
}
