package com.gongsibao.trade.service.action.order.save;

import java.sql.Types;

import org.netsharp.action.ActionContext;
import org.netsharp.action.IAction;
import org.netsharp.base.IPersistableService;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.ReflectManager;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.service.OrderService;

/**
 * @ClassName: ActionSaveOrderPersist
 * @Description:TODO 订单保存
 * 执行顺序：6
 * @author: 韩伟
 * @date: 2018年3月2日 下午5:03:01
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class ActionSaveOrderPersist implements IAction {

	@Override
	public void execute(ActionContext ctx) {

		SoOrder soOrder = (SoOrder) ctx.getItem();

		@SuppressWarnings("unchecked")
		IPersistableService<SoOrder> service = (IPersistableService<SoOrder>) ReflectManager.newInstance(OrderService.class.getSuperclass());
		soOrder = service.save(soOrder);
		ctx.setItem(soOrder);

		this.updateSorderNo(soOrder);

	}

	/**
	 * @Title: updateSorderNo
	 * @Description: TODO(更新订单编号)
	 * @param: @param soOrder
	 * @return: void
	 * @throws
	 */
	private void updateSorderNo(SoOrder soOrder) {

		Integer pkid = soOrder.getId();
		String no = String.valueOf((100000000 + pkid));
		String cmdText = "UPDATE so_order SET no = ? WHERE pkid = ? ";
		QueryParameters qps = new QueryParameters();
		{
			qps.add("no", no, Types.VARCHAR);
			qps.add("pkid", pkid, Types.INTEGER);
		}
		IPersister<SoOrder> pm = PersisterFactory.create();
		pm.executeNonQuery(cmdText, qps);
		soOrder.setNo(no);
	}
}
