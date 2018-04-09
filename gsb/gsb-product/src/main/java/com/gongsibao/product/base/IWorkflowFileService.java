package com.gongsibao.product.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.product.WorkflowFile;

public interface IWorkflowFileService extends IPersistableService<WorkflowFile> {
	
	/**   
	 * @Title: getWorkflowNodeMaxVersion   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param prodId
	 * @param: @param cityId
	 * @param: @return      
	 * @return: Integer      
	 * @throws   
	 */
	Integer getWorkflowFileMaxVersion(Integer prodId, Integer cityId);
	
	
	/**   
	 * @Title: queryWorkflowFileList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param prodId
	 * @param: @param cityId
	 * @param: @return      
	 * @return: List<WorkflowFile>      
	 * @throws   
	 */
	List<WorkflowFile> queryWorkflowMustFileList(Integer prodId, Integer cityId);
	
	/**   
	 * @Title: queryWorkflowFileList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param prodId
	 * @param: @param cityId
	 * @param: @param version
	 * @param: @return      
	 * @return: List<WorkflowNode>      
	 * @throws   
	 */
	List<WorkflowFile> queryWorkflowMustFileList(Integer prodId, Integer cityId, Integer version);
	
	/**   
	 * @Title: queryWorkflowFileList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param prodId
	 * @param: @param cityId
	 * @param: @return      
	 * @return: List<WorkflowFile>      
	 * @throws   
	 */
	List<WorkflowFile> queryWorkflowFileList(Integer prodId, Integer cityId);
	
	/**   
	 * @Title: queryWorkflowFileList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param prodId
	 * @param: @param cityId
	 * @param: @param version
	 * @param: @return      
	 * @return: List<WorkflowNode>      
	 * @throws   
	 */
	List<WorkflowFile> queryWorkflowFileList(Integer prodId, Integer cityId, Integer version);
}