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
	

	/**
	 * @Title: queryOrderProdTraceFile
	 * @Description: TODO(根据订单明细Id查询跟进文件，根据isTop排序)
	 * @param: @param orderProdId
	 * @param: @return
	 * @return: List<OrderProdTraceFile>
	 * @throws
	 */
	List<OrderProdTraceFile> queryOrderProdTraceFiles(Integer orderProdId);
	
	
	/**   
	 * @Title: queryList   
	 * @Description: TODO(查询)   
	 * @param: @param orderProdId
	 * @param: @return      
	 * @return: List<OrderProdTraceFile>      
	 * @throws   
	 */
	List<OrderProdTraceFile> queryList(Integer orderProdId);
	
	/**   
	 * @Title: topTraceFile   
	 * @Description: TODO(跟进文件置顶)   
	 * @param: @param orderProdId
	 * @param: @param traceFileId
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean topTraceFile(Integer orderProdId,Integer traceFileId);
	
}