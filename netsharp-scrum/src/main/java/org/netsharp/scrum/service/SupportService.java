package org.netsharp.scrum.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.scrum.base.ISupportService;
import org.netsharp.scrum.entity.Support;
import org.netsharp.service.PersistableService;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;
import org.netsharp.wx.ea.base.IEaMessageService;

@Service
public class SupportService extends PersistableService<Support> implements
		ISupportService {

	public SupportService() {
		super();
		this.type = Support.class;
	}

	@Override
	public Support save(Support entity) {

		EntityState state = entity.getEntityState();

		super.save(entity);

		entity = this.pm.byId(entity);

		if (entity.getEntityState() != EntityState.Deleted) {

			List<String> ss = new ArrayList<String>();

			// ss.add(EmployeeContextHolder.getEmployeeContext().getEmployeeName()
			// +
			// state + "了支持");
			String executor = UserPermissionManager.getUserPermission()
					.getEmployee().getName();
			ss.add("【支持】" + executor + state.getText() + "了支持");
			ss.add(entity.getName());
			ss.add(entity.getStatus().getText());
			ss.add(DateManage.toLongString(new Date()));
			ss.add("负责人:" + entity.getOwner().getName());
			ss.add("提出人:" + entity.getPutor().getName());
			
			if(entity.getSender() != null){
				
				ss.add("抄送人:" + entity.getSender().getName());
			}
			
			ss.add("紧急程度:" + entity.getUrgency().getText());
			ss.add("估时:" + entity.getEstimateHours() + "小时");
			ss.add(entity.getContent());
			{
				String content = StringManager.join(StringManager.NewLine, ss);
				List<String> ls = new ArrayList<String>();
				ls.add(UserPermissionManager.getUserPermission().getEmployee()
						.getMobile());
				ls.add(entity.getOwner().getMobile());

				if (entity.getPutorId() != null) {
					ls.add(entity.getPutor().getMobile());
				}
				
				if (entity.getSenderId() != null) {
					ls.add(entity.getSender().getMobile());
				}

				IEaMessageService eMessageService = ServiceFactory
						.create(IEaMessageService.class);
				eMessageService.send("SCRUM", content,
						StringManager.join("|", ls));
			}
		}
		return entity;
	}
}
