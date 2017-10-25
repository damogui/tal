//package org.netsharp.communication.local;
//
//import java.util.List;
//
//import org.netsharp.base.IPersistableService;
//import org.netsharp.communication.annotations.TestCollect;
//import org.netsharp.core.NetsharpException;
//import org.netsharp.core.annotations.Transaction;
//import org.netsharp.entity.Customer;
//import org.netsharp.entity.SalesOrder;
//
//public interface ISalesOrderService extends IPersistableService<SalesOrder> {
//	
//    String hello(String value);
//    
//    void createTable() throws NetsharpException;
//    
//    @Transaction
//    void save() throws NetsharpException;
//    
//    @Transaction
//    void failSave() throws NetsharpException;
//    
//    @Transaction
//    void invokeService() throws NetsharpException;
//    
//    List<Customer> queryCustomers() throws NetsharpException;
//    
//    @Transaction
//    @TestCollect
//    void saveCollect(SalesOrder order) throws NetsharpException;
//    
//    int primivateTest(float f);
//}
