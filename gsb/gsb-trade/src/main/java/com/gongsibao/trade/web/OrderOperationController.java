package com.gongsibao.trade.web;

import java.util.List;

import org.netsharp.communication.ServiceFactory;

import com.gongsibao.trade.base.IOrderOperationService;

public class OrderOperationController extends SoOrderDTOController {

	IOrderOperationService orderOperationService = ServiceFactory.create(IOrderOperationService.class);

	// 批量转移业务员
	public Boolean BatchTransferSalesman(int ywyUserId, List<Integer> orderIdList) {
		return orderOperationService.BatchTransferSalesman(ywyUserId, orderIdList);
	}
}
