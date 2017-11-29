package com.gongsibao.crm.service;

import java.sql.Types;
import java.util.Date;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.service.PersistableService;

import com.gongsibao.crm.base.ICustomerFollowService;
import com.gongsibao.crm.base.ICustomerService;
import com.gongsibao.entity.crm.Customer;
import com.gongsibao.entity.crm.CustomerFollow;
import com.gongsibao.entity.crm.dic.FollowStatus;
import com.gongsibao.entity.uc.User;
import com.gongsibao.uc.base.IUserService;

@Service
public class CustomerFollowService extends PersistableService<CustomerFollow> implements ICustomerFollowService {

    public CustomerFollowService(){
        super();
        this.type=CustomerFollow.class;
    }
    
	public CustomerFollow newInstance() {

		CustomerFollow entity = new CustomerFollow();
		entity.toNew();
		entity.setCreateTime(new Date());
		entity.setCreateTime(new Date());
		entity.setCreator(SessionManager.getUserName());
		entity.setCreatorId(SessionManager.getUserId());
		
		return entity;
	}
	
	public CustomerFollow save(CustomerFollow entity){
		
		if(entity.getEntityState() == EntityState.New){
			
			ICustomerService customerService = ServiceFactory.create(ICustomerService.class);
			Customer customer = customerService.byId(entity.getCustomerId());
			
			//当有跟进记录，并且跟进状态为【未跟进】时，将跟进状态更新为【初步跟进】
			if(customer.getFollows() != null && customer.getFollows().size()>0){
				
				if(customer.getFollowStatus() == FollowStatus.FOLLOW_STATUS_1){

					customer.setFollowStatus(FollowStatus.FOLLOW_STATUS_2);
					customerService.save(customer);
				}
				Employee employee = this.getEmployee();
				IUserService userService = ServiceFactory.create(IUserService.class);
				User user = userService.byMobilePhone(employee.getLoginName());
				entity.setCreatorId(user.getId());
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
}