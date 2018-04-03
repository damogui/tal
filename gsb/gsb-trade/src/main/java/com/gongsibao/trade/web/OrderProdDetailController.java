package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;

import com.gongsibao.entity.product.WorkflowNode;
import com.gongsibao.entity.trade.OrderProdTrace;
import com.gongsibao.product.base.IWorkflowNodeService;
import com.gongsibao.trade.base.IOrderProdTraceService;

public class OrderProdDetailController {

	IOrderProdTraceService traceService = ServiceFactory.create(IOrderProdTraceService.class);

	/**
	 * @Title: queryProdTraceList
	 * @Description: TODO(根据订单明细Id查询订单明细跟进)
	 * @param: @param orderProdId
	 * @param: @return
	 * @return: List<OrderProdTrace>
	 * @throws
	 */
	public List<OrderProdTrace> queryProdTraceList(Integer orderProdId) {

		Oql oql = new Oql();
		{
			oql.setType(OrderProdTrace.class);
			oql.setSelects("OrderProdTrace.*,operator.{id,name},orderProdStatus.{id,name}");
			oql.setFilter("orderProdId=?");
			oql.setOrderby("createTime DESC");
			oql.getParameters().add("orderProdId", orderProdId, Types.INTEGER);
		}
		return traceService.queryList(oql);
	}

	/**
	 * @Title: updateTraceTipColor
	 * @Description: TODO(更新订单明细跟进文字颜色)
	 * @param: @param traceId
	 * @param: @param tipColor
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	public Boolean updateTraceTipColor(Integer traceId, String tipColor) {

		return traceService.updateTraceTipColor(traceId, tipColor);
	}

	/**
	 * @Title: queryWorkflowNodeList
	 * @Description: TODO(查询订单明细工作流节点)
	 * @param: @param orderProdId
	 * @param: @return
	 * @return: List<WorkflowNode>
	 * @throws
	 */
	public List<WorkflowNode> queryWorkflowNodeList(Integer prodId, Integer cityId, Integer version) {

		IWorkflowNodeService workflowNodeService = ServiceFactory.create(IWorkflowNodeService.class);
		if (version == null) {

			version = workflowNodeService.getWorkflowNodeMaxVersion(prodId, cityId);
		}

		if (version == null) {

			throw new BusinessException("交付流程模版未设置，请联系管理！");
		}

		return workflowNodeService.queryWorkflowNodeList(prodId, cityId, version);
	}

	/**   
	 * @Title: updateProcessStatus   
	 * @Description: TODO(更新订单、订单明细进度状态,并创建跟进记录)   
	 * @param: @param orderProdId
	 * @param: @param processStatusId
	 * @param: @param processStatusText
	 * @param: @return      
	 * @return: Boolean      
	 * @throws   
	 */
	public Boolean updateProcessStatus(Integer orderProdId, Boolean isSendMessage,Integer processStatusId, String processStatusText) {

		
		return true;
	}
}
