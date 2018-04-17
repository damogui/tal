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

		if (notifyType == NotifyType.WEIXIN) {

			this.sendWxMessage(entity);

		} else if (notifyType == NotifyType.SMS) {

			this.sendSMSMessage(entity);

		} else if (notifyType == NotifyType.SYSTEM) {

			// PC端通知

		} else if (notifyType == NotifyType.DINGDING) {

		} else if (notifyType == NotifyType.ALL) {

			this.sendWxMessage(entity);

			this.sendSMSMessage(entity);
		}
	}

	private NotifyType getNotifyType(Integer employeeId) {

		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		return salesmanService.getNotifyType(employeeId);
	}

	private void sendSMSMessage(NCustomerTaskNotify entity) {

		Employee received = this.getEmployee(entity.getReceivedId());

		if (received != null && !StringManager.isNullOrEmpty(received.getMobile()) && received.getMobile().length() == 11) {

			SmsHelper.send(received.getMobile(), entity.getContent());
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
		if (received == null) {
			eMessageService.send("CRM", entity.getContent(), "");
		} else {
			eMessageService.send("CRM", entity.getContent(), received.getMobile());
		}
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