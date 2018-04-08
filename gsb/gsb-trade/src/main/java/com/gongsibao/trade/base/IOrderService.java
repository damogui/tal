package com.gongsibao.trade.base;

import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.trade.dic.AuditStatusType;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;

public interface IOrderService extends IPersistableService<SoOrder> {

    /**
     * @throws
     * @Title: applyStage
     * @Description: TODO(申请分期)
     * @param: @param soOrder
     * @param: @return
     * @return: Boolean
     */
    @Transaction
    Boolean applyStage(SoOrder soOrder);


    /**
     * @throws
     * @Title: applyRefund
     * @Description: TODO(申请退款)
     * @param: @param refund
     * @param: @return
     * @return: Boolean
     */
    @Transaction
    Boolean applyRefund(Refund refund);

    /**
     * @throws
     * @Title: applyCarryover
     * @Description: TODO(申请结转)
     * @param: @return
     * @return: Boolean
     */
    @Transaction
    Boolean applyCarryover(NOrderCarryover orderCarryover);

    /*
    *根据订单id获取订单实体
    * */
    SoOrder getByOrderId(Integer orderId);

    /*
     *根据订单no获取订单实体
     * */
    SoOrder getByOrderNo(String orderNo);

    /*
     *根据订单no获取订单id
     * */
    Integer getOrderIdByNo(Integer orderNo);

    /*更新状态值根据字段名、订单id、和状态值*/
    @Transaction
    void updateStatus(String status_id, Integer id, AuditStatusType shzt);
    
    /*是否可以创建回款*/
    Integer checkCanPay(Integer orderId);
    
    /*是否可以订单业绩type=0   是否可以创建回款业绩  type=1*/
    Integer checkCanOrderPer(Integer orderId,Integer type);
    
    /**   
     * @Title: getCustomerMobile   
     * @Description: TODO(根据订单Id获取客户手机号)   
     * @param: @param orderId
     * @param: @return      
     * @return: String      
     * @throws   
     */
    String getCustomerMobile(Integer orderId);
    
    /**   
     * @Title: getCustomerByOrderId   
     * @Description: TODO(根据订单Id获取NCustomer)   
     * @param: @param orderId
     * @param: @return      
     * @return: NCustomer      
     * @throws   
     */
    NCustomer getCustomerByOrderId(Integer orderId);
}