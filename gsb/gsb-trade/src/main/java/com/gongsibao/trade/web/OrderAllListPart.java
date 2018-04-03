package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.List;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.u8.base.ISoOrderService;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.trade.base.IContractService;


/**
 * Created by zhangchao on 2018/3/9.
 */
public class OrderAllListPart extends SalesmanAllOrderListPart {

    ISoOrderService soOrderService = ServiceFactory.create(ISoOrderService.class);

    /**
     * ���ݶ�����ѯ��ͬ
     *
     * @param orderId
     * @return
     */
    public int checkContract(Integer orderId) {
        Integer res = 0;
        StringBuilder builder = new StringBuilder();
        builder.append("Contract.*");
        Oql oql = new Oql();
        {
            oql.setType(Contract.class);
            oql.setSelects(builder.toString());
            oql.setFilter("orderId=? and audit_status_id in(" + AuditStatusType.Dsh.getValue() + "," + AuditStatusType.Shtg.getValue() + "," + AuditStatusType.Shz.getValue() + ")");
            oql.getParameters().add("orderId", orderId, Types.INTEGER);
        }

        IContractService contractService = ServiceFactory.create(IContractService.class);
        List<Contract> contracts = contractService.queryList(oql);
        if (CollectionUtils.isNotEmpty(contracts)) {
            res = -1;//该订单已经创建合同
        } else {
            SoOrder order = soOrderService.getByOrderId(orderId);
            //当该订单是改价订单时，并且改价状态不是【审核通过】
            if (order.getIsChangePrice() && !order.getChangePriceAuditStatus().equals(AuditStatusType.Shtg)) {
                res = -2;//当该订单的改价状态不是审核通过
            }
        }
        return res;


    }
}
