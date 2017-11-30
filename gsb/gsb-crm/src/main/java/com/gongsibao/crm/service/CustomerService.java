package com.gongsibao.crm.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.crm.base.ICustomerService;
import com.gongsibao.crm.base.ICustomerServiceConfigService;
import com.gongsibao.entity.crm.Customer;
import com.gongsibao.entity.crm.CustomerProdMap;
import com.gongsibao.entity.crm.dic.ServiceType;
import com.gongsibao.entity.trade.SoOrder;
import com.gongsibao.entity.uc.User;
import com.gongsibao.uc.base.IUserService;

@Service
public class CustomerService extends GsbPersistableService<Customer> implements ICustomerService {

	public CustomerService() {
		super();
		this.type = Customer.class;
	}

	@Override
	public Customer save(Customer entity) {

		
		//获取当前跟进人对应UC体系的Id
		Employee employee = this.getEmployee();
		IUserService userService = ServiceFactory.create(IUserService.class);
		User user = userService.byMobilePhone(employee.getLoginName());
		if(user == null){
			
			throw new BusinessException("帐号体系不一致，请联系技术部处理");
		}
		
		if (entity.getEntityState() != EntityState.Deleted) {

			if(entity.getEntityState() == EntityState.New){

				entity.setCreatorId(user.getId());
				
				ICustomerServiceConfigService  configService = ServiceFactory.create(ICustomerServiceConfigService.class);
				ServiceType type = configService.getTypeByEmployeeId(employee.getId());
				if(type == ServiceType.AFTER_SALES){
					
					entity.setFollowUserId(user.getId());
				}
			}
			
			if (entity.getfProvinceId() != null) {

				entity.setCityId(entity.getfProvinceId());
			}

			if (entity.getfCityId() != null) {

				entity.setCityId(entity.getfCityId());
			}

			if (entity.getfCountyId() != null) {

				entity.setCityId(entity.getfCountyId());
			}

			if (entity.getProdDetails() != null) {

				for (CustomerProdMap prod : entity.getProdDetails()) {

					if (prod.getdProvinceId() != null) {

						prod.setCityId(prod.getdProvinceId());
					}

					if (prod.getdCityId() != null) {

						prod.setCityId(prod.getdCityId());
					}

					if (prod.getdCountyId() != null) {

						prod.setCityId(prod.getdCountyId());
					}
				}
			}

		}

		entity = super.save(entity);
		return entity;
	}
	
	private Employee getEmployee(){
		
		Oql oql = new Oql();
		{
			oql.setType(Employee.class);
			oql.setSelects("employee.{id,loginName}");
			oql.setFilter(" id=? ");
			oql.getParameters().add("id", SessionManager.getUserId(), Types.INTEGER);
		}
		IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
		Employee employee = employeeService.queryFirst(oql);
		return employee;
	}

	@Override
	public Customer validationContactWay(Integer id, String contactWay, String type) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
		}

		List<String> ss = new ArrayList<String>();
		ss.add(type + "=?");
		oql.getParameters().add("contactWay", contactWay, Types.VARCHAR);
		if (id != null) {
			ss.add("id<>?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		String filter = StringManager.join(" AND ", ss);
		oql.setFilter(filter);

		return this.queryFirst(oql);
	}
	
	@Override
	public Customer byId(Object id) {

		String selectFields = getSelectFullFields();
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects(selectFields);
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}

		Customer entity = this.queryFirst(oql);
		if(entity.getAccountId() !=null && entity.getAccountId()!=0){
			
			 List<SoOrder> orders = getOrderList(entity.getAccountId());
			 entity.setOrders(orders);
		}
		return entity;
	} 

	@Override
	public Customer bySwtCustomerId(String swtCustomerId) {

		String selectFields = getSelectFullFields();
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects(selectFields);
			oql.setFilter("swtCustomerId=?");
			oql.getParameters().add("swtCustomerId", swtCustomerId, Types.VARCHAR);
		}

		Customer entity = this.queryFirst(oql);

		if(entity != null && entity.getAccountId() !=null && entity.getAccountId()!=0){
			
			 List<SoOrder> orders = getOrderList(entity.getAccountId());
			 entity.setOrders(orders);
		}
		return entity;
	}
	
	private List<SoOrder> getOrderList(int accountId){
		
		Oql oql = new Oql();
		{
			oql.setType(SoOrder.class);
			oql.setSelects("*");
			oql.setFilter("accountId=?");
			oql.getParameters().add("accountId", accountId, Types.INTEGER);
		}
		
		IPersister<SoOrder> orderPm = PersisterFactory.create();
		return orderPm.queryList(oql);
	}

	@Override
	public Customer byContactWay(String contactWay, String type) {
		
		String selectFields = getSelectFullFields();
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects(selectFields);
			oql.setFilter(type+"=?");
			oql.getParameters().add("contactWay", contactWay, Types.VARCHAR);
		}

		Customer entity = this.queryFirst(oql);

		if(entity != null && entity.getAccountId() !=null && entity.getAccountId()!=0){
			
			 List<SoOrder> orders = getOrderList(entity.getAccountId());
			 entity.setOrders(orders);
		}
		return entity;
	}

	@Override
	public Customer bindSwtCustomerId(String swtCustomerId, int customerId) {
		
		Customer customer = byId(customerId);
		customer.setSwtCustomerId(swtCustomerId);
		customer = this.save(customer);
		return customer;
	}
	
	private String getSelectFullFields(){
		
		StringBuilder builder = new StringBuilder();
		builder.append("Customer.*,");
		builder.append("Customer.allocationOrg.*,");
		builder.append("Customer.prodDetails.*,");
		builder.append("Customer.prodDetails.product.{id,name},");
		builder.append("Customer.prodDetails.dProvince.*,");
		builder.append("Customer.prodDetails.dCity.*,");
		builder.append("Customer.prodDetails.dCounty.*,");
		builder.append("Customer.companys.*,");
		builder.append("Customer.companys.company.{id,companyName},");
		builder.append("Customer.follows.*,");
		builder.append("Customer.orders.*");
		return builder.toString();
	}
}