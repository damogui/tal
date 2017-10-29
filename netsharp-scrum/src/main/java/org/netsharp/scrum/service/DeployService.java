package org.netsharp.scrum.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.scrum.base.IDeployService;
import org.netsharp.scrum.entity.Deploy;
import org.netsharp.service.PersistableService;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;
import org.netsharp.wx.ea.base.IEaMessageService;

@Service
public class DeployService extends PersistableService<Deploy> implements IDeployService {
	public DeployService() {
		super();
		this.type = Deploy.class;
	}

	@Override
	public Deploy save(Deploy entity) {

		EntityState state = entity.getEntityState();

		super.save(entity);

		entity = this.pm.byId(entity);

		IEaMessageService eMessageService = ServiceFactory.create(IEaMessageService.class);

		List<String> ss = new ArrayList<String>();

		String executor = UserPermissionManager.getUserPermission().getEmployee().getName();
		ss.add(executor + state.getText() + "了部署计划");
		ss.add(entity.getName());
		ss.add(DateManage.toLongString(new Date()));
		ss.add("部署日期:"+DateManage.toString( entity.getDeployTime() ) + "  周"+DateManage.getDayofWeekUpper(entity.getDeployTime()));
		ss.add(entity.getContent());

		String content = StringManager.join(StringManager.NewLine, ss);

		eMessageService.sendAll("SCRUM", content);

		return entity;
	}
}
