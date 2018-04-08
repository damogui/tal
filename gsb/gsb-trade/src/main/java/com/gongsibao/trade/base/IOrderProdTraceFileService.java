package com.gongsibao.trade.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.OrderProdTraceFile;

public interface IOrderProdTraceFileService extends IPersistableService<OrderProdTraceFile> {
	
	/**   
	 * @Title: queryWorkflowFileId   
	 * @Description: TODO(查询订单跟进对应的文件Id)   
	 * @param: @param orderProdTraceIds
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws   
	 */
	List<Integer> queryWorkflowFileId(List<Integer> orderProdTraceIds);
}