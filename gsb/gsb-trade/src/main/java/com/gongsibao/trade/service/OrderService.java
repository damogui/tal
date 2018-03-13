package com.gongsibao.trade.service;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.Refund;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderService;

@Service
public class OrderService extends PersistableService<SoOrder> implements IOrderService {

    public OrderService(){
        super();
        this.type=SoOrder.class;
    }
    
	@Override
	public SoOrder save(SoOrder entity) {

		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/crm/order/save");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
		}
		ActionManager action = new ActionManager();
		action.execute(ctx);

		entity = (SoOrder) ctx.getItem();
		return entity;
	}
//    
//    @Override
//	public SoOrder byId(Object id) {
//
//		String selectFields = getSelectFullFields();
//		Oql oql = new Oql();
//		{
//			oql.setType(this.type);
//			oql.setSelects(selectFields);
//			oql.setFilter("id=?");
//			oql.getParameters().add("id", id, Types.INTEGER);
//		}
//
//		SoOrder entity = this.queryFirst(oql);
//		return entity;
//	}
//
//	private String getSelectFullFields() {
//
//		StringBuilder builder = new StringBuilder();
//		builder.append("SoOrder.*");
//		builder.append("SoOrder.products.*");
//		builder.append("SoOrder.products.items.*");
//		return builder.toString();
//	}

	@Override
	public Boolean applyStage(SoOrder entity) {

		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/crm/order/stage");
			ctx.setItem(entity);
			ctx.setState(entity.getEntityState());
		}
		ActionManager action = new ActionManager();
		action.execute(ctx);
		return true;
	}

	@Override
	public Boolean applyRefund(Refund refund) {

		ActionContext ctx = new ActionContext();
		{
			ctx.setPath("gsb/crm/order/refund");
			ctx.setItem(refund);
			ctx.setState(refund.getEntityState());
		}
		ActionManager action = new ActionManager();
		action.execute(ctx);
		return true;
	}
}