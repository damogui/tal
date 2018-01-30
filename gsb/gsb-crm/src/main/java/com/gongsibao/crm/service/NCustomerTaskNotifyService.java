package com.gongsibao.crm.service;

import org.netsharp.communication.Service;
import org.netsharp.core.EntityState;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.entity.crm.NCustomerTaskNotify;

@Service
public class NCustomerTaskNotifyService extends SupplierPersistableService<NCustomerTaskNotify> implements INCustomerTaskNotifyService {

	public NCustomerTaskNotifyService() {
		super();
		this.type = NCustomerTaskNotify.class;
	}
	
	public NCustomerTaskNotify save(NCustomerTaskNotify entity) {
		
		EntityState state = entity.getEntityState();
		
		entity = super.save(entity);
		if(state == EntityState.New){
			
			this.sendMessage(entity);
		}
		return entity;
	}
	
	/**   
	 * @Title: sendMessage   
	 * @Description: TODO(发送消息)   
	 * @param: @param entity      
	 * @return: void      
	 * @throws   
	 */
	private void sendMessage(NCustomerTaskNotify entity) {

//		IEaMessageService eMessageService = ServiceFactory.create(IEaMessageService.class);
//		String executor = UserPermissionManager.getUserPermission().getEmployee().getName();
//
//		IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
//		Employee owner = employeeService.byId(ownerId);
//
//		for (String franchiseeId : ids) {
//
//			List<String> ss = new ArrayList<String>();
//			ss.add("【分配提醒】" + executor + "分配了1个客户给" + owner.getName());
//			ss.add("请及时跟进");
//			String content = StringManager.join("，", ss);
//			List<String> ls = new ArrayList<String>();
//			//ls.add(UserPermissionManager.getUserPermission().getEmployee().getMobile());
//			ls.add(owner.getMobile());
//			eMessageService.send("BD", content, StringManager.join("|", ls));
//		}
	}
}