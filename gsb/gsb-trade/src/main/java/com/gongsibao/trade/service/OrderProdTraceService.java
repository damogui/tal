package com.gongsibao.trade.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.core.Row;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.product.WorkflowFile;
import com.gongsibao.entity.product.WorkflowNode;
import com.gongsibao.entity.trade.OrderProdTrace;
import com.gongsibao.entity.trade.OrderProdUserMap;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.entity.trade.dic.OrderProdTraceOperatorType;
import com.gongsibao.entity.trade.dic.OrderProdTraceType;
import com.gongsibao.entity.trade.dic.OrderProdUserMapStatus;
import com.gongsibao.entity.trade.dic.OrderProdUserMapType;
import com.gongsibao.product.base.IWorkflowNodeService;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IOrderProdTraceFileService;
import com.gongsibao.trade.base.IOrderProdTraceService;
import com.gongsibao.trade.base.IOrderProdUserMapService;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.utils.sms.SmsHelper;

@Service
public class OrderProdTraceService extends PersistableService<OrderProdTrace> implements IOrderProdTraceService {

	public OrderProdTraceService() {
		super();
		this.type = OrderProdTrace.class;
	}

	IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);

	@Override
	public List<OrderProdTrace> querySoOrderTraceList(Integer soOrderId) {
		List<OrderProdTrace> getList = new ArrayList<OrderProdTrace>();

		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("SELECT * from so_order_prod_trace where type_id in (3151, 3153) and order_prod_id in(");
		// sqlBuilder.append("SELECT pkid from so_order_prod where order_id="+soOrderId);
		sqlBuilder.append("SELECT pkid from so_order_prod where order_id=263853");
		sqlBuilder.append(") ORDER BY add_time desc");

		DataTable dataTable = this.pm.executeTable(sqlBuilder.toString(), null);
		try {
			for (IRow row : dataTable) {
				String info = row.getString("info");
				OrderProdTrace getEntity = new OrderProdTrace();
				getEntity.setInfo(info);
				getEntity.setCreateTime(row.getDate("add_time"));
				getList.add(getEntity);
			}
		} catch (Exception e) {

		}
		return getList;
	}

	@Override
	public List<OrderProdTrace> getByOrderIdAndType(List<Integer> orderIdList, Integer type) {
		List<OrderProdTrace> resList = new ArrayList<OrderProdTrace>();
		List<Integer> orderProdIdList = orderProdService.getIdsByOrderIds(orderIdList);

		if (CollectionUtils.isEmpty(orderProdIdList)) {
			return resList;
		}

		String orderProdIds = StringManager.join(",", orderProdIdList);

		String typeWhere = type.equals(-1) ? "" : " AND type = " + type + " ";

		StringBuilder builder = new StringBuilder();
		builder.append("OrderProdTrace.*,");
		builder.append("OrderProdTrace.allocationOrg.*,");
		builder.append("Customer.prodDetails.*,");

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("orderProdId in (" + orderProdIds + ") " + typeWhere + "");
			oql.setOrderby("createTime DESC");
		}
		resList = this.pm.queryList(oql);

		return resList;
	}

	@Override
	public Map<Integer, String> getLastInfoByOrderIdType(List<Integer> orderIdList, Integer type) {

		Map<Integer, String> resMap = new HashMap<Integer, String>();

		if (CollectionUtils.isEmpty(orderIdList)) {
			return resMap;
		}

		String orderIds = StringManager.join(",", orderIdList);

		String typeWhere = type.equals(-1) ? "" : " AND odt.type_id = " + type + " ";

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT od.`order_id`,odt.info,odt.add_time FROM so_order_prod od ");
		sql.append("JOIN (SELECT * FROM so_order_prod_trace WHERE pkid IN(SELECT MAX(pkid) FROM so_order_prod_trace GROUP BY order_prod_id)) odt ON odt.order_prod_id = od.`pkid` ");
		sql.append("WHERE od.order_id IN(" + orderIds + ") " + typeWhere + " ");
		sql.append("GROUP BY od.order_id ");

		DataTable executeTable = this.pm.executeTable(sql.toString(), null);

		for (Row row : executeTable) {
			resMap.put(row.getInteger("order_id"), row.getString("info") + "-跟进时间：" + row.getDate("add_time"));
		}

		return resMap;
	}

	@Override
	public Boolean updateTraceTipColor(Integer traceId, String tipColor) {

		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order_prod_trace");
			updateSql.set("tip_color", tipColor);
			updateSql.where("pkid=" + traceId);
		}
		String cmdText = updateSql.toSQL();
		return this.pm.executeNonQuery(cmdText, null) > 0;
	}

	@Override
	public OrderProdTrace create(OrderProdTrace entity) {

		entity.toNew();
		entity.setOperatorId(SessionManager.getUserId());
		entity = this.save(entity);
//
//		if (entity.getOrderProdStatusId() != null && entity.getOrderProdStatusId().compareTo(0) == 1) {
//
//			// 更新订单和订单明细的状态
//			updateOrderProdProcessStatus(entity);
//
//			// 更新订单状态
//			updateOrderProcessStatus(entity);
//		}
		return entity;
	}

	@Override
	public Boolean remindCustomer(OrderProdTrace trace) {

		trace.toNew();
		trace.setTypeId(OrderProdTraceType.Tskf);
		trace.setOperatorId(SessionManager.getUserId());
		trace = this.save(trace);

		if (trace.getIsSendSms()) {
			// 发短信
			IOrderService orderService = ServiceFactory.create(IOrderService.class);
			String mobile = orderService.getCustomerMobile(trace.getOrderId());
			String smsString = "【公司宝】尊敬的公司宝用户您好：" + trace.getInfo() + "。如需帮助，请拨打服务热线：4006-798-999。";
			// 发送短信
			new Thread() {
				@Override
				public void run() {

					SmsHelper.send(mobile, smsString);
				}
			}.start();
		}

		return true;
	}

	/**
	 * @Title: updateOrderProdProcessStatus
	 * @Description: TODO(更新订单明细进度状态)
	 * @param: @param orderProdStatusId
	 * @param: @param orderProdId
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	private Boolean updateOrderProdProcessStatus(OrderProdTrace entity) {

		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order_prod");
			updateSql.set("process_status_id", entity.getOrderProdStatusId());
			updateSql.set("audit_status_id", AuditStatusType.Shz.getValue());
			updateSql.set("version", entity.getVersion());
			updateSql.where("pkid=" + entity.getOrderProdId());
		}
		return this.pm.executeNonQuery(updateSql.toSQL(), null) > 0;
	}

	/**
	 * @Title: updateOrderProcessStatus
	 * @Description: TODO(更新订单进度状态)
	 * @param: @param orderProdStatusId
	 * @param: @param orderProdId
	 * @param: @return
	 * @return: Boolean
	 * @throws
	 */
	private Boolean updateOrderProcessStatus(OrderProdTrace entity) {

		// 这里好像是取的type，hw。
		// 这里有个系统的帐号
		UpdateBuilder updateSql = UpdateBuilder.getInstance();
		{
			updateSql.update("so_order");
			updateSql.set("process_status_id", entity.getOrderProdStatusId());
			updateSql.where("pkid in (SELECT order_id from so_order_prod where pkid=" + entity.getOrderProdId() + ")");
		}
		return this.pm.executeNonQuery(updateSql.toSQL(), null) > 0;
	}

	@Override
	public Boolean markComplaint(OrderProdTrace trace, Boolean isFocus) {

		trace = this.create(trace);
		IOrderProdUserMapService orderProdUserMapService = ServiceFactory.create(IOrderProdUserMapService.class);
		if (trace.getIsSendSms()) {

			// 给所有业务员发送短信

			List<OrderProdUserMap> orderProdUserMapList = orderProdUserMapService.queryList(trace.getOrderProdId(), OrderProdUserMapType.Ywy);
			if (orderProdUserMapList.size() > 0) {

				List<Integer> ss = new ArrayList<Integer>();
				for (OrderProdUserMap item : orderProdUserMapList) {

					ss.add(item.getPrincipalId());
				}

				IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
				Oql oql = new Oql();
				{
					oql.setType(Employee.class);
					oql.setSelects("id,name,mobile");
					oql.setFilter("id in (" + StringManager.join(",", ss) + ")");
				}

				List<Employee> employees = employeeService.queryList(oql);

				// 给这些业务员们发短信
				for (Employee employee : employees) {

					String smsString = "【公司宝】" + employee.getName() + "，您好！" + SessionManager.getUserName() + "给您发了一条订单（订单号：" + trace.getOrderNo() + "）提醒：" + trace.getInfo()
							+ "。如需帮助，请拨打服务热线：4006-798-999。";
					// 发送短信
					new Thread() {

						@Override
						public void run() {

							SmsHelper.send(employee.getMobile(), smsString);
						}
					}.start();
				}
			}
		}

		// 重点关注(没看懂有啥用)
		if (isFocus) {

			OrderProdUserMap orderProdUserMap = new OrderProdUserMap();
			{
				orderProdUserMap.toNew();
				orderProdUserMap.setPrincipalId(0);
				orderProdUserMap.setOrderProdId(trace.getOrderProdId());
				orderProdUserMap.setType(OrderProdUserMapType.Kfy);
				orderProdUserMap.setStatus(OrderProdUserMapStatus.Zzfz);
			}
			orderProdUserMapService.save(orderProdUserMap);
		}

		// 更新明细订单为【客户投诉】
		IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);
		orderProdService.updateIsComplaint(trace.getOrderProdId());
		return true;
	}

	@Override
	public Boolean markAbnormal(OrderProdTrace trace) {

		// 1.创建跟进记录
		trace = this.create(trace);

		// 2.更新跟进记录的操作类型(操作员情况下)
		// Map<String, Object> userMap = new HashMap<>();
		// userMap.put("orderProdId", soOrderProd.getPkid());
		// userMap.put("userId", user.getUcUser().getPkid());
		// List<SoOrderProdUserMap> soOrderProdUserMapList =
		// soOrderProdUserMapService.findByProperties(new HashMap<>(), 0,
		// 1);---老代码，看着好像没处理啥
		// SoOrderProdUserMap soOrderProdUserMap =
		// soOrderProdUserMapList.get(0);
		// if (soOrderProdUserMap.getTypeId() == 3063) {
		// soOrderProdTrace.setOperatorType(315201);
		// } else if (soOrderProdUserMap.getTypeId() == 3061 ||
		// soOrderProdUserMap.getTypeId() == 3062) {
		// soOrderProdTrace.setOperatorType(315202);
		// } else {
		// soOrderProdTrace.setOperatorType(0);
		return true;
	}

	@Override
	public Boolean sendExpress(OrderProdTrace trace) {

		trace = this.create(trace);
		if (trace.getIsSendSms()) {

			IOrderService orderService = ServiceFactory.create(IOrderService.class);
			String mobile = orderService.getCustomerMobile(trace.getOrderId());
			String smsString = "【公司宝】您的订单：" + trace.getOrderNo() + "有一条新快递信息。快递清单：" + trace.getExpressContent() + "。快递公司：" + trace.getExpressCompanyName() + "。" + "快递单号：" + trace.getExpressNo()
					+ "。发件人留言：" + trace.getInfo() + "。如需帮助，请拨打服务热线：4006-798-999。";
			// 发送短信
			new Thread() {
				@Override
				public void run() {

					SmsHelper.send(mobile, smsString);
				}
			}.start();

		}
		return true;
	}

	@Override
	public Boolean remindPrincipal(Integer soOrderProdId, Integer orderProdStatusId, String principalName, String principalMobile, String orderNo, String info, Boolean isSendSms) {

		// 1.添加跟进
		OrderProdTrace trace = new OrderProdTrace();
		trace.toNew();
		trace.setOrderProdId(soOrderProdId);
		trace.setOrderProdStatusId(orderProdStatusId);
		trace.setOperatorType(OrderProdTraceOperatorType.Txfzr);
		trace.setInfo(info);
		trace.setIsSendSms(isSendSms);
		trace.setTypeId(OrderProdTraceType.wu);
		trace.setOperatorId(SessionManager.getUserId());
		this.create(trace);

		// 2.发送信息
		if (isSendSms) {

			String content = "【公司宝】" + principalName + "，您好！" + SessionManager.getUserName() + "给您发了一条订单（订单号：" + orderNo + "）提醒：" + info;
			SmsHelper.send(principalMobile, content);
		}
		return true;
	}

	@Override
	public Boolean updateProcessStatus(OrderProdTrace trace) {

		// 这里有很多校验
		Integer orderProdId = trace.getOrderProdId();
		Integer oldProcessStatusId = orderProdService.getProcessStatusId(orderProdId);
		Integer newProcessStatusId = trace.getOrderProdStatusId();

		trace.setTypeId(OrderProdTraceType.Ggzt);
		trace.setOperatorId(SessionManager.getUserId());

		IWorkflowNodeService workflowNodeService = ServiceFactory.create(IWorkflowNodeService.class);
		WorkflowNode oldWorkflowNode = workflowNodeService.byId(oldProcessStatusId);
		WorkflowNode newWorkflowNode = workflowNodeService.byId(newProcessStatusId);

		trace.setInfo("更新状态:" + newWorkflowNode.getName());
		if (null != oldWorkflowNode && null != newWorkflowNode && oldWorkflowNode.getSort() > newWorkflowNode.getSort()) {
			trace.setOperatorType(OrderProdTraceOperatorType.Ztth);
			trace.setInfo("更新状态(回退): " + newWorkflowNode.getName());
		}

		List<WorkflowNode> workflowNodeList = orderProdService.getWorkflowNodeList(orderProdId);

		//最后一个节点
		WorkflowNode lastWorkflowNode = workflowNodeList.get(workflowNodeList.size() - 1);
		if (newProcessStatusId.compareTo(lastWorkflowNode.getId()) == 0) {

			List<WorkflowFile> WorkflowFileList = orderProdService.queryWorkflowFileList(trace.getOrderProdId());
			List<Integer> isMustWorkflowFilelist = new ArrayList<Integer>();
			if (CollectionUtils.isNotEmpty(WorkflowFileList)) {
				for (WorkflowFile workflowFile : WorkflowFileList) {
					if (workflowFile.getMust()) {
						isMustWorkflowFilelist.add(workflowFile.getId());
					}
				}
			}

			if (!CollectionUtils.isNotEmpty(isMustWorkflowFilelist)) {

				List<OrderProdTrace> orderProdTraceList = this.queryList(orderProdId, OrderProdTraceType.Sccl);
				if (CollectionUtils.isEmpty(orderProdTraceList)) {

					throw new BusinessException("必须材料没有上传完！");
				}

				List<Integer> orderProdTraceIds = new ArrayList<Integer>();
				for (OrderProdTrace item : orderProdTraceList) {
					orderProdTraceIds.add(item.getId());
				}

				IOrderProdTraceFileService orderProdTraceFileService = ServiceFactory.create(IOrderProdTraceFileService.class);
				List<Integer> orderProdTraceFileList = orderProdTraceFileService.queryWorkflowFileId(orderProdTraceIds);
				isMustWorkflowFilelist.removeAll(orderProdTraceFileList);
				if (CollectionUtils.isNotEmpty(isMustWorkflowFilelist)) {

					throw new BusinessException("必须材料没有上传完！");
				}
			}

			//哪些需要完善资质信息？没看懂，参数5是啥意思？
//			List<BdServiceProduct> productList = bdServiceService.findProductList(5);
//			boolean isQualify = false;
//			for (BdServiceProduct bdServiceProduct : productList) {
//				if (soOrderProd.getProductId().compareTo(bdServiceProduct.getProductId()) == 0) {
//					isQualify = true;
//					break;
//				}
//			}
//
//			if (isQualify) {
//				
//				UcAccountCompanyQualify qualify = ucAccountCompanyQualifyService.findByOrderProdId(NumberUtils.toInt(soOrderProd.getPkid()));
//				if (null == qualify || StringUtils.isBlank(qualify.getCode()) || null == qualify.getBeginDate() || null == qualify.getEndDate()) {
//					data.setMsg("请先完善资质信息");
//					data.setCode(402);
//					return data;
//				}
//			}

			//这里先不处理内外部，全部要审核
			// 内部人员并且设置了不审核属性 则直接审核通过
//			if (!internal_check && NumberUtils.toInt(user.getUcUser().getIsInner()) == 1) {
//				
//				orderProdService.updateStatus(orderProdId, newProcessStatusId, AuditStatusType.Shtg);
//			}else{
//				
//				orderProdService.updateStatus(orderProdId, newProcessStatusId, AuditStatusType.Shz);
//			}
		}
		
		// 更新订单和订单明细的状态
		updateOrderProdProcessStatus(trace);

		// 更新订单状态
		updateOrderProcessStatus(trace);
		
		this.create(trace);

		if (trace.getIsSendSms()) {

			Calendar now = Calendar.getInstance();
			IOrderService orderService = ServiceFactory.create(IOrderService.class);
			String mobile = orderService.getCustomerMobile(trace.getOrderId());
			String smsString = "【公司宝】您的订单：" + trace.getOrderNo() + ",已于" + now.get(Calendar.YEAR) + "年" + (now.get(Calendar.MONTH) + 1) + "月" + now.get(Calendar.DAY_OF_MONTH) + "日变更为“"
					+ newWorkflowNode.getName() + "”状态。如需帮助，请拨打服务热线：4006-798-999。";
			new Thread() {
				@Override
				public void run() {
					SmsHelper.send(mobile, smsString);
				}
			}.start();
		}
		return true;
	}
	
	
	public List<OrderProdTrace> queryList(Integer orderProdId,OrderProdTraceType type) {
		
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("orderProdId =? and typeId = ?");
			oql.getParameters().add("orderProdId", orderProdId, Types.INTEGER);
			oql.getParameters().add("typeId", type.getValue(), Types.INTEGER);
		}
		return this.pm.queryList(oql);
	}

	@Override
	public OrderProdTrace getLastUpdateProcessTrace(Integer orderProdId, Integer orderProdStatusId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("orderProdId =? and orderProdStatusId=? and typeId = ?");
			oql.getParameters().add("orderProdId", orderProdId, Types.INTEGER);
			oql.getParameters().add("orderProdStatusId", orderProdStatusId, Types.INTEGER);
			oql.getParameters().add("typeId", OrderProdTraceType.Ggzt.getValue(), Types.INTEGER);
		}
		return this.pm.queryFirst(oql);
	}
}

// 【admin】添加【admin】为负责人 admin 内容服务 2018-01-05 16:02:26
// 更新状态:内容服务 admin 内容服务 2018-01-05 16:02:14
// 办理名称修改（订单开始操作【汉唐】） admin 咨询下单 2018-01-05 16:01:44
// 设置办理名称为【234324234】 admin 咨询下单 2018-01-05 16:01:44
// 公司名称修改（订单开始操作【汉唐】） admin 咨询下单 2018-01-05 16:01:44
// 线上支付0.01元 2018-01-05 15:56:19