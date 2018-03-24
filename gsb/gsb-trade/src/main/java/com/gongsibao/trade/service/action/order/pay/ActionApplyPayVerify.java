package com.gongsibao.trade.service.action.order.pay;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.core.BusinessException;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.trade.OrderPayMap;
import com.gongsibao.entity.trade.Pay;

/**
 * @ClassName: ActionApplyPayVerify
 * @Description:TODO 回款验证
 * @author: 韩伟
 * @date: 2018年3月22日 下午5:44:49
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class ActionApplyPayVerify implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		Pay pay = (Pay) ctx.getItem();
		if (pay.getSetOfBooksId() == null || pay.getSetOfBooksId().equals(0)) {

			throw new BusinessException("请选择【付款账套】！");
		}

		if (pay.getU8BankId() == null || pay.getU8BankId().equals(0)) {

			throw new BusinessException("请选择【付款方式】！");
		}

		if (StringManager.isNullOrEmpty(pay.getOfflinePayerName())) {

			throw new BusinessException("请填写【付款账户名称】！");
		}

		if (StringManager.isNullOrEmpty(pay.getOfflineBankNo())) {

			throw new BusinessException("请填写【付款账号】！");
		}

		Integer payAmount = pay.getAmount();
		if (payAmount == null || payAmount.equals(0)) {

			throw new BusinessException("请填写【付款金额】！");
		}

		if (pay.getFiles() == null || pay.getFiles().size() == 0) {

			throw new BusinessException("请上传【付款凭证】！");
		}

		List<OrderPayMap> orderPayMaps = pay.getOrderPayMaps();
		if (orderPayMaps == null || orderPayMaps.size() == 0) {

			throw new BusinessException("请创建【关联订单】！");
		}

		Integer allotTotalAmount = 0;
		List<Integer> orderIdList = new ArrayList<Integer>();
		for (OrderPayMap payMap : orderPayMaps) {

			allotTotalAmount += payMap.getOrderPrice();
			orderIdList.add(payMap.getOrderId());
		}

		if (allotTotalAmount.compareTo(payAmount) != 0) {

			throw new BusinessException("【关联订单分配金额总和】与【付款金额】不相等！");
		}

		/**
		 * 校验订单是否已经处于审核状态是的话不能进行创建回款审核 1.结转、退款、分期 这里不用校验
		 */
	}

}
