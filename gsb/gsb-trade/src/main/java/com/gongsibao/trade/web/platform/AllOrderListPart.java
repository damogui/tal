package com.gongsibao.trade.web.platform;

import java.sql.Types;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.supplier.base.ISupplierService;
import com.gongsibao.trade.base.IContractService;
import com.gongsibao.trade.web.SalesmanAllOrderListPart;
import com.gongsibao.u8.base.ISoOrderService;

/**
 * @ClassName: AllOrderListPart
 * @Description:TODO 全部订单后台控制控制器
 * @author: 韩伟
 * @date: 2018年4月22日 下午7:14:05
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class AllOrderListPart extends SalesmanAllOrderListPart {

	@Override
	public String getDefaultFilter() {

		// 当前登录人为运营专员时，过滤服务商Id
		String defaultFilter = null;
		ISupplierService supplierService = ServiceFactory.create(ISupplierService.class);
		List<Integer> supplierIdList = supplierService.getSupplierIdListByOwnerId(SessionManager.getUserId());
		if (supplierIdList != null && supplierIdList.size() > 0) {

			defaultFilter =  " supplier_id in (" + StringManager.join(",", supplierIdList) + ")";
		}
		System.out.println("全部订单：" +defaultFilter);
		return defaultFilter;
	}

	ISoOrderService soOrderService = ServiceFactory.create(ISoOrderService.class);

	/**
	 * ���ݶ�����ѯ��ͬ
	 *
	 * @param orderId
	 * @return
	 */
	public int checkContract(Integer orderId) {
		Integer res = 0;
		StringBuilder builder = new StringBuilder();
		builder.append("Contract.*");
		Oql oql = new Oql();
		{
			oql.setType(Contract.class);
			oql.setSelects(builder.toString());
			oql.setFilter("orderId=? and audit_status_id in(" + AuditStatusType.Dsh.getValue() + "," + AuditStatusType.Shtg.getValue() + "," + AuditStatusType.Shz.getValue() + ")");
			oql.getParameters().add("orderId", orderId, Types.INTEGER);
		}

		IContractService contractService = ServiceFactory.create(IContractService.class);
		List<Contract> contracts = contractService.queryList(oql);
		if (CollectionUtils.isNotEmpty(contracts)) {
			res = -1;// 该订单已经创建合同
		} else {
			SoOrder order = soOrderService.getByOrderId(orderId);
			// 当该订单是改价订单时，并且改价状态不是【审核通过】
			if (order.getIsChangePrice() && !order.getChangePriceAuditStatus().equals(AuditStatusType.Shtg)) {
				res = -2;// 当该订单的改价状态不是审核通过
			}
		}
		return res;
	}
}
