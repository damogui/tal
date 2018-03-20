package com.gongsibao.trade.web;

import java.sql.Types;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.trade.base.IContractService;


/**
 * Created by zhangchao on 2018/3/9.
 */
public class OrderAllListPart extends SalesmanAllOrderListPart {
	
	

	/**
	 * 根据订单查询合同
	 * @param orderId
	 * @return
	 */
	public boolean checkContract(Integer orderId){
		StringBuilder builder = new StringBuilder();
		builder.append("Contract.*,");
		Oql oql = new Oql();
		{
			oql.setType(Contract.class);
			oql.setSelects(builder.toString());
			oql.setFilter("orderId=?");
			oql.getParameters().add("orderId", orderId, Types.INTEGER);
		}

		IContractService contractService = ServiceFactory.create(IContractService.class);
		Contract entity = contractService.queryFirst(oql);
		if(entity != null){
			return true;
		}else{
			return false;
		}
	}
}
