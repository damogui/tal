package com.gongsibao.trade.service.action.order.performance.pay;

import java.sql.Types;
import java.util.List;

import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.trade.service.action.order.utils.AuditHelper;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Oql;

import com.gongsibao.entity.trade.NDepPay;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderService;

/**
 * @ClassName: ActionApplyPayVerify
 * @Description:TODO 回款业绩验证
 * @author: 韩伟
 * @date: 2018年3月22日 下午5:44:49
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class ActionApplyPayPerformanceVerify implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		@SuppressWarnings("unchecked")
		List<NDepPay> depPayList = (List<NDepPay>) ctx.getItem();







        if (depPayList == null || depPayList.size() == 0) {

			throw new BusinessException("分配记录不能为空！");
		}

		Integer orderId = depPayList.get(0).getOrderId();
		if (orderId == null || orderId.equals(0)) {

			throw new BusinessException("订单信息错误！");
		}



		SoOrder soOrder = this.getSoOrder(orderId);
		if (soOrder == null) {

			throw new BusinessException("订单信息不存在！");
		}

        Integer execNum = AuditHelper.getRecode (soOrder.getId (), AuditLogType.Skyjsh.getValue ());
        if (execNum > 0) {

            throw new BusinessException ("订单正处于回款业绩审核状态");//execNum

        }


        Integer unAllotPayPrice = soOrder.getUnAllotPayPrice();
		if (unAllotPayPrice == null || unAllotPayPrice.compareTo(0) != 1) {

			throw new BusinessException("【未划分回款业绩额】为0！");
		}

		Integer allotTotalAmount = 0;
		for (NDepPay depPay : depPayList) {

			depPay.toNew();
			allotTotalAmount += depPay.getAmount();
		}

		if (allotTotalAmount.compareTo(unAllotPayPrice) != 0) {

			throw new BusinessException("本次需把未划分回款业绩额全部分配！");
		}

		/**
		 * 校验订单是否已经处于审核状态是的话不能进行创建回款审核 1.结转、退款、分期 (抽取公共处理，这里只需调用)
		 *
		 */

	}

	private SoOrder getSoOrder(Integer id) {

		Oql oql = new Oql();
		{
			oql.setType(SoOrder.class);
			oql.setSelects("id,totalPrice,payablePrice,paidPrice,refundPrice,returnedPrice,carryAmount");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		IOrderService orderService = ServiceFactory.create(IOrderService.class);
		SoOrder entity = orderService.queryFirst(oql);
		return entity;
	}
}
