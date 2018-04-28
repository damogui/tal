package com.gongsibao.trade.base;

import com.gongsibao.entity.crm.NCustomer;
import com.gongsibao.entity.trade.Invoice;
import com.gongsibao.entity.trade.dic.AuditStatusType;

import com.gongsibao.trade.web.dto.OrderPayDTO;
import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.trade.NOrderCarryover;
import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;

import java.util.Date;
import java.util.List;

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
    Integer checkCanOrderPer(Integer orderId, Integer type);

    /**
     * @throws
     * @Title: getCustomerMobile
     * @Description: TODO(根据订单Id获取客户手机号)
     * @param: @param orderId
     * @param: @return
     * @return: String
     */
    String getCustomerMobile(Integer orderId);

    /**
     * @throws
     * @Title: getCustomerByOrderId
     * @Description: TODO(根据订单Id获取NCustomer)
     * @param: @param orderId
     * @param: @return
     * @return: NCustomer
     */
    NCustomer getCustomerByOrderId(Integer orderId);

    /*orderId 订单id selects设置查询项*/
    SoOrder getSoOrderById(Object orderId, String selects);

    /**
     * @Description: 查询会员订单数量
     * @param   accountId 会员id; isPaid 是否已支付
     * @return
     * @author wangkun <wangkun@gongsibao.com>
     * @date 2018/4/18
     */
    Integer countByAccountId(Integer accountId, boolean isPaid);

    @Transaction
    SoOrder saveWebOrder(SoOrder order, Invoice invoice, List<String> couponNo);

    void updateNo(SoOrder soOrder);

    @Transaction
    void updateOnlinePay(OrderPayDTO orderPayDTO);

    Boolean addPaidPrice(Integer id, Integer paidPrice);

    Boolean updateProcessStatusId(Integer id, Integer processStatusId);

    Boolean updatePayStatus(int id, Integer payStatusId);

    Boolean updateFistPayTime(Integer id, Date fistPayTime);

    Boolean updatePayTime(Integer orderId, Date payTime);

    /**
     * 根据账号ID查询特定状态下的订单数量
     *
     * @param accountId 账号ID
     * @param status  订单状态
     * @return
     */
    int countOrderAccountIdStatus(Integer accountId, Integer status);

    /**
     * 根据账号ID查询特定状态下的订单数量（分页）
     *
     * @param accountId   账号ID
     * @param status      订单状态
     * @param currentPage 起始页
     * @param pageSize    每页显示条数
     * @return
     */
    List<SoOrder> pageOrderListByAccountIdStatus(Integer accountId, Integer status,
                                                 int currentPage, int pageSize);

    @Transaction
    int updateOrderStatus(Integer accountId,Integer orderId,Integer status);

    /**
     * 取消订单 - 含有业务,外面事物不起作用
     * @param accountId
     * @param orderId
     * @return
     */
    @Transaction
    int updateCancelOrder(Integer accountId,Integer orderId);

    /**
     * 复原订单价格
     * @param pkid
     * @param price
     * @return
     */
    @Transaction
    int updatePayablePriceRevert(int pkid, int price);
    @Transaction
    String orderDel(Integer orderId);

    String getOrderNoById(Integer id);
    /**
     * 订单恢复
     * @param orderId
     * @return
     */
    Integer orderRecover(Integer orderId);
}