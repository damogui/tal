package com.gongsibao.trade.service.action.order.stage;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;

import com.gongsibao.entity.trade.NOrderStage;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dic.AuditStatusType;
import com.gongsibao.u8.base.ISoOrderService;

/**   
 * @ClassName:  ActionApplyStageVerify   
 * @Description:TODO 申请分期校验
 * @author: 韩伟
 * @date:   2018年3月12日 下午4:53:02   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class ActionApplyStageVerify implements IAction{
	@Override
	public void execute(ActionContext ctx) {
		SoOrder order = (SoOrder) ctx.getItem();
		//1.是否改价申请
		if(order.getIsChangePrice()){
			if(order.getChangePriceAuditStatus().getValue().equals(AuditStatusType.Bhsh.getValue())){
				throw new BusinessException("订单改价审核未通过，请核实");
            }else if(order.getChangePriceAuditStatus().getValue().equals(AuditStatusType.Shz.getValue())){
            	throw new BusinessException("订单改价还未审核通过，请审核通过后再创建");
            } 
        }
		//2.分期审核
		if (order.getIsInstallment()) {
			throw new BusinessException("该订单已申请分期，并审核通过，请知悉");
			
		} else {
			if (order.getInstallmentAuditStatusId().getValue().equals(AuditStatusType.Shz.getValue())) {
				throw new BusinessException("该订单已申请分期，目前待审核，请知悉");
			}
		}
		//3.分期总额不等于订单应付金额，请核实----不用验证，自动补全的金额
	}

}
