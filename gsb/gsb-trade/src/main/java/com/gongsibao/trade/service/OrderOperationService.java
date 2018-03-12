package com.gongsibao.trade.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.authorization.UserPermission;
import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.entity.Employee;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.OrderProdTrace;
import com.gongsibao.entity.trade.OrderProdUserMap;
import com.gongsibao.entity.trade.dic.OrderProdTraceType;
import com.gongsibao.entity.trade.dic.OrderProdUserMapStatusType;
import com.gongsibao.entity.trade.dic.OrderProdUserMapType;
import com.gongsibao.entity.trade.dto.SoOrderDTO;
import com.gongsibao.entity.uc.User;
import com.gongsibao.trade.base.IOrderOperationService;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IOrderProdTraceService;
import com.gongsibao.trade.base.IOrderProdUserMapService;
import com.gongsibao.trade.base.IUcUserService;

@Service
public class OrderOperationService extends SoOrderDTOService implements IOrderOperationService {

	public OrderOperationService() {
		super();
		this.type = SoOrderDTO.class;
	}

	// 产品订单服务
	IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);
	// 订单分配服务
	IOrderProdUserMapService orderProdUserMapService = ServiceFactory.create(IOrderProdUserMapService.class);
	// 跟进记录服务
	IOrderProdTraceService orderProdTraceService = ServiceFactory.create(IOrderProdTraceService.class);
	// 系统用户服务
	IUcUserService ucUserService = ServiceFactory.create(IUcUserService.class);

	@Override
	public Boolean BatchTransferSalesman(int ywyUserId, List<Integer> orderIdList) {

		User user = ucUserService.byId(ywyUserId);

		if (user == null)// 该业务员不存在
			return false;

		// 所选订单的所有的产品订单id
		List<Integer> orderProdIdList = orderProdService.getIdsByOrderIds(orderIdList);

		// 获取该产品订单对应的业务员
		Map<Integer, String> orderUserByIdsMap = orderProdUserMapService.getOrderUserByIds(orderProdIdList, OrderProdUserMapType.Ywy.getValue(), OrderProdUserMapStatusType.Zzfz.getValue());

		// 将该产品订单之前的业务员的状态改为【曾经负责】
		orderProdUserMapService.updateStatusByOrderProdId(orderProdIdList, OrderProdUserMapType.Ywy.getValue(), OrderProdUserMapStatusType.Cjfz.getValue(), OrderProdUserMapStatusType.Zzfz.getValue());

		List<OrderProdUserMap> orderProdUserMapList = new ArrayList<OrderProdUserMap>();
		// 插入新的【正在负责】的中间表
		for (Integer orderProdId : orderProdIdList) {
			OrderProdUserMap orderProdUserMap = new OrderProdUserMap();
			orderProdUserMap.toNew();
			orderProdUserMap.setOrderProdId(orderProdId);
			orderProdUserMap.setStatusId(OrderProdUserMapStatusType.Zzfz.getValue());
			orderProdUserMap.setUserId(ywyUserId);
			orderProdUserMap.setTypeId(OrderProdUserMapType.Ywy.getValue());
			orderProdUserMapList.add(orderProdUserMap);
		}
		// 添加订单和业务员的中间表
		if (CollectionUtils.isNotEmpty(orderProdUserMapList)) {
			orderProdUserMapService.saves(orderProdUserMapList);
		}

		// 将该产品订单的【是否分配 0 未分配 1已分配】，改为1已分配
		int updateAssignByIds = orderProdService.updateAssignByIds(1, orderProdIdList);

		List<OrderProdTrace> orderProdTraceList = new ArrayList<OrderProdTrace>();

		// 获取当前登录人的名称
		UserPermission permission = UserPermissionManager.getUserPermission();
		Employee employee = permission.getEmployee();// 登陆人信息的实体

		for (Integer orderProdId : orderProdIdList) {
			String orginName = StringManager.isNullOrEmpty(orderUserByIdsMap.get(orderProdId)) ? "" : orderUserByIdsMap.get(orderProdId);
			String info = "【" + employee.getName() + "】批量转移给【" + user.getName() + "】，原跟进人为【" + orginName + "】";
			OrderProdTrace orderProdTrace = new OrderProdTrace();
			orderProdTrace.toNew();
			orderProdTrace.setOrderProdId(orderProdId);
			orderProdTrace.setOrderProdStatusId(0);
			orderProdTrace.setTypeId(OrderProdTraceType.Ghywy);
			orderProdTrace.setInfo(info);
			orderProdTrace.setRemark("");
			orderProdTrace.setIsSendSms(0);
			orderProdTrace.setOperatorId(employee.getId());
			orderProdTraceList.add(orderProdTrace);
		}
		// 插入跟进记录
		orderProdTraceService.saves(orderProdTraceList);

		return updateAssignByIds > 1;
	}

}
