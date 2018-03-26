package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.List;

import com.gongsibao.entity.trade.dic.AuditStatusType;
import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.trade.base.IContractService;


/**
 * Created by zhangchao on 2018/3/9.
 */
public class OrderAllListPart extends SalesmanAllOrderListPart {


    /**
     * ���ݶ�����ѯ��ͬ
     *
     * @param orderId
     * @return
     */
    public boolean checkContract(Integer orderId) {
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
            return true;
        } else {
            return false;
        }
    }
}
