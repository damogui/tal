package com.gongsibao.trade.base;

import java.util.List;
import java.util.Map;

import com.gongsibao.entity.product.WorkflowFile;
import com.gongsibao.entity.product.WorkflowNode;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.dic.SettleStatus;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.trade.OrderProd;

public interface IOrderProdService extends IPersistableService<OrderProd> {

	//根据订单id获取产品订单的产品名称
	Map<Integer, String> getProductCityNameByOrderIds(List<Integer> orderIdList);
	//根据订单id获取产品订单id集合
	List<Integer> getIdsByOrderIds(List<Integer> orderIdList);
	//更新是否分配 0 未分配 1已分配
	int updateAssignByIds(Integer isAssign, List<Integer>  pkidList);
	
	List<OrderProd> queryByOrderId(Integer orderId);

    boolean updateSettleStatus(List<Integer> orderProdIds, SettleStatus settleStatus);
    
    /**   
     * @Title: updateIsComplaint   
     * @Description: TODO(标记为投诉)   
     * @param: @param orderProdId
     * @param: @return      
     * @return: boolean      
     * @throws   
     */
    boolean updateIsComplaint(Integer orderProdId);
    
    
    /**   
     * @Title: editApplyNo   
     * @Description: TODO(编辑申请号)   
     * @param: @param orderProdId
     * @param: @param applyNo
     * @param: @return      
     * @return: Boolean      
     * @throws   
     */
    Boolean editApplyNo(Integer orderProdId, String applyNo);
    
    /**   
     * @Title: editHandleName   
     * @Description: TODO(编辑办理名称)   
     * @param: @param orderProdId
     * @param: @param handleName
     * @param: @return      
     * @return: Boolean      
     * @throws   
     */
    Boolean editHandleName(Integer orderProdId, String handleName);
    
    /**   
     * @Title: getProcessStatusId   
     * @Description: TODO(获取订单操作进度Id)   
     * @param: @param orderProdId
     * @param: @return      
     * @return: Integer      
     * @throws   
     */
    Integer getProcessStatusId(Integer orderProdId);
    
    
    /**   
     * @Title: updateStatus   
     * @Description: TODO(更新明细订单状态)   
     * @param: @param orderProdId
     * @param: @param processStatusId
     * @param: @param auditStatus
     * @param: @return      
     * @return: Boolean      
     * @throws   
     */
    Boolean updateStatus(Integer orderProdId, Integer processStatusId, AuditStatusType auditStatus);
    
    /**   
     * @Title: getWorkflowNodeList   
     * @Description: TODO(根据明细订单Id获取流程节点)   
     * @param: @param orderProdId
     * @param: @return      
     * @return: List<WorkflowNode>      
     * @throws   
     */
    List<WorkflowNode> getWorkflowNodeList(Integer orderProdId); 
    
    
    /**   
     * @Title: queryWorkflowFileList   
     * @Description: TODO(根据明细订单Id获取流程节点对应的文件节点)   
     * @param: @param orderProdId
     * @param: @return      
     * @return: List<WorkflowFile>      
     * @throws   
     */
    List<WorkflowFile> queryWorkflowFileList(Integer orderProdId);
    
	/**   
	 * @Title: cancelRelevanceCompany   
	 * @Description: TODO(取消订单明细关联企业信息)   
	 * @param: @param orderProdId
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean cancelRelevanceCompany(Integer orderProdId);
	
	/**   
	 * @Title: cancelRelevanceCompany   
	 * @Description: TODO(添加订单明细关联企业信息)   
	 * @param: @param orderProdId
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	Boolean addRelevanceCompany(Integer orderProdId,Integer companyId);
}