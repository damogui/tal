package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.NDepReceivable;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.trade.base.IOrderService;

import java.util.List;

@Service
public class OrderService extends PersistableService<SoOrder> implements IOrderService {

    public OrderService(){
        super();
        this.type=SoOrder.class;
    }
    
	@Override
	public SoOrder save(SoOrder entity) {

        List<NDepReceivable> depList = entity.getDepReceivable ();
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
//		return builder.toString();
//	}
}