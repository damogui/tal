package com.gongsibao.product.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.product.WorkflowFile;
import org.omg.PortableInterceptor.INACTIVE;

public interface IWorkflowFileService extends IPersistableService<WorkflowFile> {

    /**
     * @throws
     * @Title: getWorkflowNodeMaxVersion
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: @param prodId
     * @param: @param cityId
     * @param: @return
     * @return: Integer
     */
    Integer getWorkflowFileMaxVersion(Integer prodId, Integer cityId);


    /**
     * @throws
     * @Title: queryWorkflowFileList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: @param prodId
     * @param: @param cityId
     * @param: @return
     * @return: List<WorkflowFile>
     */
    List<WorkflowFile> queryWorkflowMustFileList(Integer prodId, Integer cityId);

    /**
     * @throws
     * @Title: queryWorkflowFileList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: @param prodId
     * @param: @param cityId
     * @param: @param version
     * @param: @return
     * @return: List<WorkflowNode>
     */
    List<WorkflowFile> queryWorkflowMustFileList(Integer prodId, Integer cityId, Integer version);

    /**
     * @throws
     * @Title: queryWorkflowFileList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: @param prodId
     * @param: @param cityId
     * @param: @return
     * @return: List<WorkflowFile>
     */
    List<WorkflowFile> queryWorkflowFileList(Integer prodId, Integer cityId);

    /**
     * @throws
     * @Title: queryWorkflowFileList
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @param: @param prodId
     * @param: @param cityId
     * @param: @param version
     * @param: @return
     * @return: List<WorkflowNode>
     */
    List<WorkflowFile> queryWorkflowFileList(Integer prodId, Integer cityId, Integer version);

    List<WorkflowFile> getListByOrderProdId(Integer orderProdId);

    List<WorkflowFile> getListByFlowIdsAndVersion(List<Integer> workFlowIdList, Integer version);
}