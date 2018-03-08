package com.gongsibao.u8.base;

import java.util.List;
import java.util.Map;

import com.gongsibao.entity.supplier.Salesman;
import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.OrderManualVoucherStatus;
import org.netsharp.core.annotations.Transaction;
import org.netsharp.organization.entity.Employee;

public interface ISoOrderService extends IPersistableService<SoOrder> {

    Boolean updateManuaVoucherStatus(Integer orderId, OrderManualVoucherStatus status);

    //根据订单id集合获取订单客户名称
    Map<Integer, String> getCustNameByOrderIdList(List<Integer> orderIdList);

    //转移/分配（包括批量转移/分配）
    @Transaction
    void orderTran(List<Integer> orderIdList, Integer toUserId);

    /*
    *根据订单id集合获取，对应的业务员信息
    * */
    public Map<Integer, Salesman> getSalesmanMapByOrderIdList(List<Integer> orderIdList);
}
