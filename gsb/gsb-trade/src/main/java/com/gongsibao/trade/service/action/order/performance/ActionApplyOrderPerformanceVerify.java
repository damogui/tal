package com.gongsibao.trade.service.action.order.performance;

import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.trade.dto.DepPayMapDTO;
import com.gongsibao.entity.trade.dto.OrderRelationDTO;
import com.gongsibao.trade.base.IOrderService;
import com.gongsibao.u8.base.ISoOrderService;
import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;

import java.util.List;

/*创建订单业绩校验*/
public class ActionApplyOrderPerformanceVerify  implements IAction{

	@Override
	public void execute(ActionContext ctx) {
		// TODO Auto-generated method stub

        DepPayMapDTO depPayMapDTO = (DepPayMapDTO) ctx.getItem();//进行校验金额
        List<OrderRelationDTO>  orderRelationDTOList=depPayMapDTO.getOrderRelations ();
        if (orderRelationDTOList.size ()==0){

            throw new BusinessException ("回款业绩必须分配！");

        }

        if (depPayMapDTO.getImgs ().size ()==0&&!depPayMapDTO.getOnlinePay ()){//线上支付不需要凭证

            throw new BusinessException ("凭证必须上传");

        }

        //根据订单Id获取订单实体
        IOrderService orderService = ServiceFactory.create(IOrderService.class);
        SoOrder order = orderService.getByOrderId(orderRelationDTOList.get (0).getOrderId ());

        if (order.getTotalPrice ()<depPayMapDTO.getAmount ()){


            throw new BusinessException ("付款金额不能大于订单金额");
        }




		
	}

}
