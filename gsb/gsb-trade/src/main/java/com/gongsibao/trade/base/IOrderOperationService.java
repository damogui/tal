package com.gongsibao.trade.base;

import java.util.List;

import org.netsharp.core.annotations.Transaction;

public interface IOrderOperationService extends ISoOrderDTOService {

	//批量转移业务员
	@Transaction
	Boolean BatchTransferSalesman(int ywyUserId,List<Integer> orderIdList);
}
