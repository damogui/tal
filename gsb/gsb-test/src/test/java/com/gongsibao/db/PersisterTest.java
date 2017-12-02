//package com.gongsibao.db;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.netsharp.core.Oql;
//import org.netsharp.core.Paging;
//import org.netsharp.persistence.IPersister;
//import org.netsharp.persistence.PersisterFactory;
//
//import com.gongsibao.ma.entity.SellingDemand;
//import com.gongsibao.trade.entity.SoOrder;
//
//public class PersisterTest {
//
//	@Test
//	public void query() {
//		IPersister<SoOrder> pm = PersisterFactory.create();
//
//		Oql oql = new Oql();
//		{
//			oql.setType(SoOrder.class);
//			oql.setSelects("*");
//			oql.setPaging(new Paging(1, 100));
//		}
//
//		List<SoOrder> orders = pm.queryList(oql);
//
//		System.out.println(orders.size());
//	}
//
//	@Test
//	public void byId() {
//		
//		IPersister<SoOrder> pm = PersisterFactory.create();
//
//		SoOrder order = pm.byId(SoOrder.class.getName(), 257533);
//		
//		System.out.println(order.getProducts().size());
//	}
//	
//	@Test
//	public void queryFirst() {
//		
//		IPersister<SellingDemand> pm = PersisterFactory.create();
//
//		Oql oql = new Oql();
//		{
//			oql.setType(SellingDemand.class);
//			oql.setSelects("*");
//		}
//
//		SellingDemand sd = pm.queryFirst(oql);
//		
//		pm.byId(sd);
//	}
//	
//	@Test
//	public void queryIndex() {
//		
//		IPersister<SellingDemand> pm = PersisterFactory.create();
//
//		Oql oql = new Oql();
//		{
//			oql.setType(SellingDemand.class);
//			oql.setSelects("*");
//			oql.setOrderby("createTime DESC");
//		}
//		
//		SellingDemand sd = pm.queryFirst(oql);
//		oql.tag=sd.getId();
//
//		Paging page = pm.queryIndex(oql);
//	}
//}
