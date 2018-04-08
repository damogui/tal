package com.gongsibao.trade.web;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.panda.commerce.EasyuiDatagridResult;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.product.WorkflowNode;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.OrderProdTrace;
import com.gongsibao.entity.trade.OrderProdUserMap;
import com.gongsibao.entity.trade.dic.OrderProdUserMapStatus;
import com.gongsibao.product.base.IWorkflowNodeService;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IOrderProdTraceService;
import com.gongsibao.trade.base.IOrderProdUserMapService;

public class OrderProdDetailController {

	IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);
	IOrderProdTraceService traceService = ServiceFactory.create(IOrderProdTraceService.class);
	IOrderProdUserMapService prodUserMapService = ServiceFactory.create(IOrderProdUserMapService.class);

	/**
	 * @Title: getOrderProdById
	 * @Description: TODO(根据Id查询订单明细)
	 * @param: @param id
	 * @param: @return
	 * @return: OrderProd
	 * @throws
	 */
	public OrderProd getOrderProdById(Integer id) {

		return orderProdService.byId(id);
	}

	/**
	 * @Title: editApplyNo
	 * @Description: TODO(编辑申请号)
	 * @param: @param orderProdId
	 * @param: @param applyNo
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	public Boolean editApplyNo(Integer orderProdId, String applyNo) {

		return orderProdService.editApplyNo(orderProdId, applyNo);
	}

	/**
	 * @Title: editHandleName
	 * @Description: TODO(编辑办理名称)
	 * @param: @param orderProdId
	 * @param: @param handleName
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	public Boolean editHandleName(Integer orderProdId, String handleName) {

		return orderProdService.editHandleName(orderProdId, handleName);
	}

	/**
	 * @Title: getLoginUserId
	 * @Description: TODO(获取当前登录人Id)
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	public Integer getLoginUserId() {

		return SessionManager.getUserId();
	}

	/**
	 * @Title: queryProdTraceList
	 * @Description: TODO(根据订单明细Id查询订单明细跟进)
	 * @param: @param orderProdId
	 * @param: @return
	 * @return: List<OrderProdTrace>
	 * @throws
	 */
	public EasyuiDatagridResult queryProdTraceList(Integer orderProdId, Integer pageIndex, Integer pageSize) {

		Paging paging = new Paging();
		{
			paging.setPageNo(pageIndex);
			paging.setPageSize(pageSize);
		}

		Oql oql = new Oql();
		{
			oql.setType(OrderProdTrace.class);
			oql.setSelects("OrderProdTrace.*,operator.{id,name},orderProdStatus.{id,name}");
			oql.setFilter("orderProdId=?");
			oql.setOrderby("createTime DESC");
			oql.setPaging(paging);
			oql.getParameters().add("orderProdId", orderProdId, Types.INTEGER);
		}
		List<OrderProdTrace> list = traceService.queryList(oql);
		EasyuiDatagridResult result = new EasyuiDatagridResult();
		{
			result.setRows(list);
			if (oql.getPaging() != null) {

				result.setTotal(oql.getPaging().getTotalCount());
			}
		}
		return result;
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
	public Boolean updateProcessStatus(OrderProdTrace trace) {

		return traceService.updateProcessStatus(trace);
	}

	public Boolean remark(OrderProdTrace trace) {

		traceService.create(trace);
		return true;
	}

	/**
	 * @Title: markComplaint
	 * @Description: TODO(标记投诉)
	 * @param: @param trace
	 * @param: @param isFocus
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	public Boolean markComplaint(OrderProdTrace trace, Boolean isFocus) {

		return traceService.markComplaint(trace, isFocus);
	}

	/**
	 * @Title: markAbnormal
	 * @Description: TODO(标记异常)
	 * @param: @param trace
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	public Boolean markAbnormal(OrderProdTrace trace) {

		return traceService.markAbnormal(trace);
	}

	/**
	 * @Title: remindPrincipal
	 * @Description: TODO(提醒客户)
	 * @param: @param trace
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	public Boolean remindPrincipal(OrderProdTrace trace) {

		return traceService.remindCustomer(trace);
	}

	/**
	 * @Title: sendExpress
	 * @Description: TODO(发快递)
	 * @param: @param trace
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	public Boolean sendExpress(OrderProdTrace trace) {

		return traceService.sendExpress(trace);
	}

	/**
	 * @Title: queryProdPrincipalList
	 * @Description: TODO(查询负责人)
	 * @param: @param orderProdId
	 * @param: @return
	 * @return: List<OrderProdUserMap>
	 * @throws
	 */
	public List<OrderProdUserMap> queryProdPrincipalList(Integer orderProdId) {

		return prodUserMapService.queryProdPrincipalList(orderProdId);
	}

	/**
	 * @Title: remindPrincipal
	 * @Description: TODO(提醒负责人)
	 * @param: @param soOrderProdId
	 * @param: @param orderProdStatusId
	 * @param: @param principalName 负责人姓名
	 * @param: @param principalMobile 负责人手机号：冗余的，查询时直接使用
	 * @param: @param orderNo 订单号
	 * @param: @param info 提醒内容
	 * @param: @param isSendSms
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	public Boolean remindPrincipal(Integer soOrderProdId, Integer orderProdStatusId, String principalName, String principalMobile, String orderNo, String info, Boolean isSendSms) {

		return traceService.remindPrincipal(soOrderProdId, orderProdStatusId, principalName, principalMobile, orderNo, info, isSendSms);
	}

	/**
	 * @Title: finishPrincipal
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param orderProdStatusId
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	public Boolean finishPrincipal(Integer orderProdStatusId) {

		return prodUserMapService.updateStatus(orderProdStatusId, OrderProdUserMapStatus.Cjfz, OrderProdUserMapStatus.Zzfz);
	}

	/**
	 * @Title: querySalesmans
	 * @Description: TODO(查询业务员：要改成使用DTO)
	 * @param: @param keyWord 关键字（服务商名称、部门名称、业务员姓名）
	 * @param: @return
	 * @return: List<Salesman>
	 * @throws
	 */
	public EasyuiDatagridResult querySalesmans(String keyWord, Integer pageIndex, Integer pageSize) {

		Paging paging = new Paging();
		{
			paging.setPageNo(pageIndex);
			paging.setPageSize(pageSize);
		}

		List<String> ss = new ArrayList<String>();
		ss.add("salesman.name like '%" + keyWord + "%'");
		ss.add("supplier.name like '%" + keyWord + "%'");
		ss.add("department.name like '%" + keyWord + "%'");
		String filter = StringManager.join(" or ", ss);
		Oql oql = new Oql();
		{
			oql.setType(Salesman.class);
			oql.setSelects("salesman.{id,employeeId,name,receiving,disabled},supplier.{id,name},department.{id,name}");
			oql.setFilter(filter);
			oql.setPaging(paging);
		}
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		List<Salesman> list = salesmanService.queryList(oql);
		EasyuiDatagridResult result = new EasyuiDatagridResult();
		{
			result.setRows(list);
			if (oql.getPaging() != null) {

				result.setTotal(oql.getPaging().getTotalCount());
			}
		}
		return result;
	}

	/**
	 * @Title: addPrincipal
	 * @Description: TODO(添加负责人)
	 * @param: @param principalIds
	 * @param: @param principalNames
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	public Boolean addPrincipal(Integer orderProdId, String principalIds, String principalNames) {

		return prodUserMapService.addPrincipal(orderProdId, principalIds, principalNames);
	}
}
