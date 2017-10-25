//package org.netsharp.entity;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.netsharp.core.annotations.Column;
//import org.netsharp.core.annotations.Reference;
//import org.netsharp.core.annotations.Subs;
//import org.netsharp.core.annotations.Table;
//
//@Table(name="test_salesOrder")
//public class SalesOrder extends BizEntity {
//	
//	private static final long serialVersionUID = 1L;
//
//	@Column(name="price")
//    private BigDecimal amount;
//    
//    @Column(name="saved_amount")
//    private BigDecimal savedAmount;
//    private Long customerId;
//    @Reference(foreignKey="customerId")
//    private Customer customer;
//    
//    @Subs(subType=SalesOrderDetail.class,subName="销售订单明细", foreignKey="orderId", referenceCode="Order",referenceName="销售订单")
//    private List<SalesOrderDetail> details;
//
//	public BigDecimal getAmount() {
//		return amount;
//	}
//
//	public void setAmount(BigDecimal amount) {
//		this.amount = amount;
//	}
//
//	public List<SalesOrderDetail> getDetails() {
//		if(details==null){
//			details=new ArrayList<SalesOrderDetail>();
//		}
//		return details;
//	}
//
//	public void setDetails(List<SalesOrderDetail> details) {
//		this.details = details;
//	}
//
//	public Customer getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//		
//		if(this.customer==null){
//			this.setCustomerId(0L);
//		}
//		else{
//			this.setCustomerId(customer.getId());
//		}
//	}
//
//	public BigDecimal getSavedAmount() {
//		return savedAmount;
//	}
//
//	public void setSavedAmount(BigDecimal savedAmount) {
//		this.savedAmount = savedAmount;
//	}
//
//	public Long getCustomerId() {
//		return customerId;
//	}
//
//	public void setCustomerId(Long customerId) {
//		this.customerId = customerId;
//	}
//}
