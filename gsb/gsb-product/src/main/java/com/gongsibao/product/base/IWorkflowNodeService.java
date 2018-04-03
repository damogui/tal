package com.gongsibao.product.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.product.WorkflowNode;

/**   
 * @ClassName:  IWorkflowNodeService   
 * @Description:TODO
 * @author: 韩伟
 * @date:   2018年4月3日 下午6:46:11   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public interface IWorkflowNodeService extends IPersistableService<WorkflowNode> {
	
	/**   
	 * @Title: getWorkflowNodeMaxVersion   
	 * @Description: TODO(根据产品Id、地区Id获取节点最大版本号)   
	 * @param: @param prodId
	 * @param: @param cityId
	 * @param: @return      
	 * @return: Integer      
	 * @throws   
	 */
	Integer getWorkflowNodeMaxVersion(Integer prodId, Integer cityId);
	
	/**   
	 * @Title: queryWorkflowNodeList   
	 * @Description: TODO(查询订单明细工作流节点)   
	 * @param: @param prodId
	 * @param: @param cityId
	 * @param: @param version
	 * @param: @return      
	 * @return: List<WorkflowNode>      
	 * @throws   
	 */
	List<WorkflowNode> queryWorkflowNodeList(Integer prodId, Integer cityId, Integer version);
}