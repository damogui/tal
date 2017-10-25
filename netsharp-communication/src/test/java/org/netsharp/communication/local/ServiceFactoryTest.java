//package org.netsharp.communication.local;
//
//import static org.junit.Assert.assertTrue;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.netsharp.base.IPersistableService;
//import org.netsharp.communication.ServiceFactory;
//import org.netsharp.core.NetsharpException;
//import org.netsharp.core.Oql;
//import org.netsharp.dataccess.DbSession;
//import org.netsharp.entity.Customer;
//import org.netsharp.entity.SalesOrder;
//import org.springframework.util.Assert;
//
//public class ServiceFactoryTest {
//	
//    @Test
//    public void create(){
//    	
//    	ISalesOrderService service= ServiceFactory.create(ISalesOrderService.class);
//    	assertTrue(service!=null);
//    }
//    
//    @Test
//    public void hello(){
//    	
//    	ISalesOrderService service=ServiceFactory.create(ISalesOrderService.class);
//    	assertTrue(service!=null);
//    	
//    	String value="yikuaixiu.com";
//    	
//    	String ret=service.hello(value);
//    	
//    	assertTrue(ret.equals("hello "+value));
//    	
//    	Assert.isNull( DbSession.getSession());
//    }
//    
//    @Test
//    public void transactionAnnocation() throws NetsharpException{
//    	
//    	ISalesOrderService service=ServiceFactory.create(ISalesOrderService.class);
//    	
//    	service.createTable();
//    	
//    	service.hello("yikuaixiu.com");
//    	Assert.isNull( DbSession.getSession());
//
//    	service.save();
//    	Assert.isNull( DbSession.getSession());
//
//    	service.hello("yikuaixiu.com");
//    	Assert.isNull( DbSession.getSession());
//
//    	service.save();
//    	Assert.isNull( DbSession.getSession());
//    	
//    	service.save();
//    	Assert.isNull( DbSession.getSession());
//    	
//    	service.save();
//    	Assert.isNull( DbSession.getSession());
//    }
//    
//    @Test
//    public void queryAndSaveTransaction() throws NetsharpException{
//    	
//    	ISalesOrderService service=ServiceFactory.create(ISalesOrderService.class);
//    	
//    	service.createTable();
//    	service.save();
//    	List<Customer> c=service.queryCustomers();
//    	
//    	Assert.isTrue(c.size()==10);
//    	
//    	service.save();
//    	c=service.queryCustomers();
//    	
//    	Assert.isTrue(c.size()==20);
//    }
//    
//    @Test
//    public void rullback() throws NetsharpException{
//    	
//    	ISalesOrderService service=ServiceFactory.create(ISalesOrderService.class);
//    	
//    	service.createTable();
//    	service.save();
//    	
//    	try{
//    		service.failSave();
//    	}
//    	catch(Exception ex){
//    		
//    	}
//    	List<Customer> c=service.queryCustomers();
//    	
//    	Assert.isTrue(c.size()==10);
//    	
//    	service.save();
//    	c=service.queryCustomers();
//    	
//    	Assert.isTrue(c.size()==20);
//    }
//    
//    @Test
//    public void serviceInvokeService() throws NetsharpException{
//    	
//    	ISalesOrderService service=ServiceFactory.create(ISalesOrderService.class);
//    	
//    	service.createTable();
//    	service.save();
//    	service.invokeService();
//    	
//    	List<Customer> c=service.queryCustomers();
//    	
//    	Assert.isTrue(c.size()==20);
//    }
//    
//    @Test
//    public void weekServiceTest(){
//    	
//    	IPersistableService<?> service=ServiceFactory.create(ISalesOrderService.class);
//    	
//    	Oql oql=new Oql();
//    	{
//    		oql.setType(SalesOrder.class);
//    		oql.setSelects("SalesOrder.*");
//    	}
//    	
//    	List<?> items= service.queryList(oql);
//    	
//    	Assert.isTrue(items!=null);
//    	
//    	System.out.println(items.size());
//    }
//    
//    @Test
//    public void primivateTest(){
//    	
//    	ISalesOrderService service=ServiceFactory.create(ISalesOrderService.class);
//    	int i = service.primivateTest(4f);
//    	
//    	Assert.isTrue( i==3 );
//    }
//}
