package com.gongsibao.trade.service.action.order.pay;

import com.gongsibao.entity.trade.OrderProdTrace;
import com.gongsibao.entity.trade.dic.OrderProdTraceOperatorType;
import com.gongsibao.entity.trade.dic.OrderProdTraceType;
import com.gongsibao.trade.base.IOrderProdService;
import com.gongsibao.trade.base.IOrderProdTraceService;
import com.gongsibao.trade.web.dto.OrderPayDTO;
import com.gongsibao.utils.AmountUtils;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/*创建回款通知*/
public class ActionOnlinePayTrace implements IAction{

	IOrderProdService orderProdService = ServiceFactory.create(IOrderProdService.class);

	IOrderProdTraceService orderProdTraceService = ServiceFactory.create(IOrderProdTraceService.class);

	@Override
	public void execute(ActionContext ctx) {
		OrderPayDTO dto = (OrderPayDTO) ctx.getItem();

		List<Integer> orderProdIds = orderProdService.getIdsByOrderIds(Arrays.asList(dto.getOrderId()));

		if (null != orderProdIds) {

			List<OrderProdTrace> itemList = new ArrayList<>();
			for (Integer orderProdId : orderProdIds) {
				OrderProdTrace orderProdTrace = new OrderProdTrace();
				orderProdTrace.setOrderProdId(orderProdId);
				orderProdTrace.setOrderProdStatusId(0);
				orderProdTrace.setTypeId(OrderProdTraceType.Ggzt); // 支付成功
				orderProdTrace.setOperatorType(OrderProdTraceOperatorType.wu);
				orderProdTrace.setInfo("线上支付" + AmountUtils.getRealAmount(dto.getTotalFee()) + "元");
				orderProdTrace.setOperatorId(0);
				orderProdTrace.setCreateTime(new Date());
				orderProdTrace.setRemark(String.valueOf(dto.getPayId()));
				orderProdTrace.setIsSendSms(false);
				orderProdTrace.setExpressCompanyName("");

				orderProdTrace.setExpressContent("");
				orderProdTrace.setExpressNo("");
				orderProdTrace.setExpressTo("");
				orderProdTrace.setProcessdDays(0);
				orderProdTrace.setTimeoutDays(0);
				itemList.add(orderProdTrace);
			}
			// 批量处理trace记录
			orderProdTraceService.saves(itemList);
		}

	}

}
