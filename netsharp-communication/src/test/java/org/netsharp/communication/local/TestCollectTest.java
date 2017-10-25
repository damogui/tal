//package org.netsharp.communication.local;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.netsharp.communication.ServiceFactory;
//import org.netsharp.communication.entity.ServiceInvoke;
//import org.netsharp.communication.entity.ServiceInvokeParameter;
//import org.netsharp.core.Mtable;
//import org.netsharp.core.MtableManager;
//import org.netsharp.core.Oql;
//import org.netsharp.db.DbFactory;
//import org.netsharp.db.IDb;
//import org.netsharp.entity.SalesOrder;
//import org.netsharp.persistence.IPersister;
//import org.netsharp.persistence.PersisterFactory;
//
//public class TestCollectTest {
//	
//    @Test
//    public void run(){
//    	
//    	IDb db=DbFactory.create();
//    	Mtable meta=MtableManager.getMtable(ServiceInvoke.class);
//    	Mtable meta2=MtableManager.getMtable(ServiceInvokeParameter.class);
//    	
//    	if(db.isTableExsist(meta.getTableName())){
//    		db.dropTable(meta.getTableName());
//    		db.dropTable(meta2.getTableName());
//    	}
//    	
//    	db.createTable(meta);
//    	db.createTable(meta2);
//    	
//    	SalesOrder order=new SalesOrder();{
//    		order.toNew();
//    	}
//    	
//    	ISalesOrderService service = ServiceFactory.create(ISalesOrderService.class);
//    	service.saveCollect(order);
//    	
//    	IPersister<ServiceInvoke> pst=PersisterFactory.create();
//    	Oql oql=new Oql();
//    	{
//    		oql.setType(ServiceInvoke.class);
//    	}
//    	
//    	int count= pst.queryCount(oql);
//    	
//    	Assert.assertTrue(count==1);
//    }
//}
