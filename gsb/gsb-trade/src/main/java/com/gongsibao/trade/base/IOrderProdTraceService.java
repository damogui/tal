package com.gongsibao.trade.base;

import java.util.List;
import java.util.Map;

import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import com.gongsibao.entity.trade.OrderProdTrace;

public interface IOrderProdTraceService extends IPersistableService<OrderProdTrace> {
	// 查看办理进度
	List<OrderProdTrace> querySoOrderTraceList(Integer soOrderId);

	// 根据订单id和类别查询（type:-1全部
	// 3151更改状态、3152备注、3153上传材料、3154提示客户、3155快递、3156帐号密码、3157标记投诉、
	// 3158提醒负责人、3159 办理名称、3160 申请号、31502 更换业务员）
	List<OrderProdTrace> getByOrderIdAndType(List<Integer> orderIdList, Integer type);
	
	Map<Integer, String> getLastInfoByOrderIdType(List<Integer> orderIdList, Integer type);

	/**   
	 * @Title: updateTraceTipColor   
	 * @Description: TODO(根据订单明细Id查询订单明细跟进)   
	 * @param: @param traceId
	 * @param: @param tipColor
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean updateTraceTipColor(Integer traceId,String tipColor);
	
	/**   
	 * @Title: create   
	 * @Description: TODO(创建跟进)   
	 * @param: @param entity
	 * @param: @return      
	 * @return: OrderProdTrace      
	 * @throws   
	 */
	@Transaction
	OrderProdTrace create(OrderProdTrace entity);
	
	
	/**   
	 * @Title: markComplaint   
	 * @Description: TODO(标记投诉)   
	 * @param: @param trace
	 * @param: @param isFocus
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	@Transaction
	Boolean markComplaint(OrderProdTrace trace, Boolean isFocus);
}