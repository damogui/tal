//package org.netsharp.communication.local;
//
//import java.util.List;
//
//import org.netsharp.communication.Service;
//import org.netsharp.communication.ServiceFactory;
//import org.netsharp.core.Mtable;
//import org.netsharp.core.MtableManager;
//import org.netsharp.core.NetsharpException;
//import org.netsharp.core.Oql;
//import org.netsharp.core.annotations.Transaction;
//import org.netsharp.db.Db;
//import org.netsharp.entity.Customer;
//import org.netsharp.entity.SalesOrder;
//import org.netsharp.persistence.IPersister;
//import org.netsharp.persistence.PersisterFactory;
//
//@Service
//public class SalesOrderService extends PersistableService<SalesOrder> implements ISalesOrderService {
//	
//	public String hello(String value){
//		
//    	value= "hello "+value;
//    	
//    	System.out.println(value);
//    	
//    	return value;
//    }
//	
//	public void createTable() throws NetsharpException{
//		
//		Db db=new Db();
//    	Mtable meta= MtableManager.getMtable(Customer.class);
//    	if(db.isTableExsist(meta.getTableName())){
//    		db.dropTable(meta.getTableName());
//    	}
//    	db.createTable(meta);
//	}
//
//	@Override
//	@Transaction
//	public void save() throws NetsharpException {
//
//		IPersister<Customer> pm=PersisterFactory.create();
//		
//		for(int i=0;i<10;i++){
//			Customer c=new Customer();
//			c.setId(-1L);
//			c.setCode("000"+i);
//			c.toNew();
//			
//			pm.save(c);
//		}
//	}
//	
//	 @Transaction
//	 public void failSave() throws NetsharpException{
//		 this.save();
//		 
//		 IPersister<Customer> pm=PersisterFactory.create();
//		 
//		 pm.executeNonQuery("asdflarutirty", null);
//	 }
//	
//	public List<Customer> queryCustomers() throws NetsharpException{
//		
//		Oql oql=new Oql();
//		oql.setType(Customer.class);
//		oql.setSelects("Customer.*");
//		
//		IPersister<Customer> pm=PersisterFactory.create();
//		
//		List<Customer> cs=pm.queryList(oql);
//		
//		return cs;
//	}
//	
//	public void invokeService() throws NetsharpException{
//		
//		ISalesOrderService service= ServiceFactory.create(ISalesOrderService.class);
//		
//		service.save();
//		service.queryCustomers();
//	}
//	
//	
//	
//	public void saveCollect(SalesOrder order) throws NetsharpException{
//		IPersister<SalesOrder> pst=PersisterFactory.create();
//		pst.save(order);
//	}
//	
//	public int primivateTest(float f){
//		return 3;
//	}
//}
