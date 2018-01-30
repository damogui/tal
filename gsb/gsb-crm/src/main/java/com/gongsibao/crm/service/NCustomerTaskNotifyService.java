package com.gongsibao.crm.service;

import java.sql.Types;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.wx.ea.base.IEaMessageService;

import com.gongsibao.bd.service.SupplierPersistableService;
import com.gongsibao.crm.base.INCustomerTaskNotifyService;
import com.gongsibao.entity.crm.NCustomerTaskNotify;
import com.gongsibao.entity.crm.dic.NotifyType;

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

		if (entity.getType() == NotifyType.WEIXIN) {

			this.sendWxMessage(entity);
			
		} else if (entity.getType() == NotifyType.DINGDING) {

			
		} else if (entity.getType() == NotifyType.SMS) {

			
		} else if (entity.getType() == NotifyType.SYSTEM) {

			
		}
	}

	/**
	 * @Title: sendWxMessage
	 * @Description: TODO(发送微信通知)
	 * @param:
	 * @return: void
	 * @throws
	 */
	private void sendWxMessage(NCustomerTaskNotify entity) {

		Employee received = this.getEmployee(entity.getReceivedId());
		IEaMessageService eMessageService = ServiceFactory.create(IEaMessageService.class);
		eMessageService.send("CRM", entity.getContent(), received.getMobile());
	}

	private Employee getEmployee(Integer id) {

		Oql oql = new Oql();
		{
			oql.setType(Employee.class);
			oql.setSelects("id,loginName,mobile,email");
			oql.setFilter("id=?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
		return employeeService.queryFirst(oql);
	}
}