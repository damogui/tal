package com.gongsibao.crm.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.util.StringManager;
import org.netsharp.wx.ea.base.IEaMessageService;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.NotifyType;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.utils.sms.SmsHelper;

@Service
public class NCustomerTaskNotifyService extends SupplierPersistableService<NCustomerTaskNotify> implements INCustomerTaskNotifyService {

	public NCustomerTaskNotifyService() {
		super();
		this.type = NCustomerTaskNotify.class;
	}

	public NCustomerTaskNotify save(NCustomerTaskNotify entity) {

		EntityState state = entity.getEntityState();
		entity = super.save(entity);
		if (state == EntityState.New) {

			try {

				this.sendMessage(entity);

			} catch (Exception ex) {

				//还需要设置一些原因
				System.out.println("SendMessage：消息发送失败!");
			}
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

		if (!entity.getIsSend()) {

			return;
		}

		NotifyType notifyType = this.getNotifyType(entity.getReceivedId());
		if (notifyType == null) {

			return;
		}

		Employee received = this.getEmployee(entity.getReceivedId());
		if (received == null || received.getDisabled()) {
			
			//接收者不存在或已停用不发送
			return;
		}
		
		
		if (notifyType == NotifyType.WEIXIN) {

			this.sendWxMessage(entity,received);

		} else if (notifyType == NotifyType.SMS) {

			if(!StringManager.isNullOrEmpty(received.getMobile()) && received.getMobile().length() == 11){

				SmsHelper.send(received.getMobile(), entity.getContent());
			}

		} else if (notifyType == NotifyType.SYSTEM) {

			// PC端通知

		} else if (notifyType == NotifyType.DINGDING) {

		} else if (notifyType == NotifyType.ALL) {

			this.sendWxMessage(entity,received);

			if(!StringManager.isNullOrEmpty(received.getMobile()) && received.getMobile().length() == 11){

				SmsHelper.send(received.getMobile(), entity.getContent());
			}
		}
	}

	/**   
	 * @Title: getNotifyType   
	 * @Description: TODO(获取通知类型)   
	 * @param: @param employeeId
	 * @param: @return      
	 * @return: NotifyType      
	 * @throws   
	 */
	private NotifyType getNotifyType(Integer employeeId) {

		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		return salesmanService.getNotifyType(employeeId);
	}


	/**
	 * @Title: sendWxMessage
	 * @Description: TODO(发送微信通知)
	 * @param:
	 * @return: void
	 * @throws
	 */
	private void sendWxMessage(NCustomerTaskNotify entity,Employee received) {

		IEaMessageService eMessageService = ServiceFactory.create(IEaMessageService.class);
		if (received == null) {
			eMessageService.send("CRM", entity.getContent(), "");
		} else {
			eMessageService.send("CRM", entity.getContent(), received.getMobile());
		}
	}

	/**   
	 * @Title: getEmployee   
	 * @Description: TODO(获取可发送员工的帐号,条件：在sp_salesman中存在，并且打开接收通知开关)   
	 * @param: @param id
	 * @param: @return      
	 * @return: Employee      
	 * @throws
	 */
	private Employee getEmployee(Integer id) {

		Oql oql = new Oql();
		{
			oql.setType(Employee.class);
			oql.setSelects("id,loginName,mobile,email,disabled");
			oql.setFilter("id=? and (disabled = 0 or disabled is null) and id in (select employee_id from sp_salesman where is_notify=1 and employee_id = ?)");
			oql.getParameters().add("@id", id, Types.INTEGER);
			oql.getParameters().add("@employeeId", id, Types.INTEGER);
		}
		IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
		return employeeService.queryFirst(oql);
	}
}