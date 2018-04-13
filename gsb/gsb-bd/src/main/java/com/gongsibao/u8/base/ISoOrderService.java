package com.gongsibao.u8.base;

import java.util.List;
import java.util.Map;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.OrderManualVoucherStatus;

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

    /*
     *根据订单id获取订单实体
     * */
    public SoOrder getByOrderId(Integer orderId);

    /*
     *根据订单id获取订单实体，包含该订单下所有的产品订单
     * */
    SoOrder getOrderWithOrderProdsByOrderId(Integer orderId);
    /*
     *根据订单id获取订单实体，包含该订单下所有分期信息
     * */
    SoOrder getOrderStageByOrderId(Integer orderId);
    /*
     *根据订单no获取订单实体
     * */
    public SoOrder getByOrderNo(String orderNo);

//    /*是否可以创建回款*/
//    Integer checkCanPay(Integer orderId);
//    /*是否可以订单业绩type=0   是否可以创建回款业绩  type=1*/
//    Integer checkCanOrderPer(Integer orderId,Integer type);

}
