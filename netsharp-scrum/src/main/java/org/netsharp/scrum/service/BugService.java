package org.netsharp.scrum.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.notify.INotifyervice;
import org.netsharp.scrum.base.IBugService;
import org.netsharp.scrum.entity.Bug;
import org.netsharp.service.PersistableService;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;

@Service
public class BugService extends PersistableService<Bug> implements IBugService {
	public BugService() {
		super();

		this.type = Bug.class;
	}

	@Override
	public Bug save(Bug entity) {

		EntityState state = entity.getEntityState();

		super.save(entity);

		entity = this.pm.byId(entity);

		INotifyervice wxpa = ServiceFactory.create(INotifyervice.class);

		List<String> ss = new ArrayList<String>();

		String executor = UserPermissionManager.getUserPermission().getEmployee().getName();

		ss.add(executor + state.getText() + "了BUG");
		ss.add(entity.getName());
		ss.add(entity.getStatus().getText());
		ss.add(DateManage.toLongString(new Date()));
		ss.add("测试人:" + entity.getTestor().getName());
		ss.add("开发:" + entity.getDeveloper().getName());
		ss.add("提出人:" + entity.getCreator());
		ss.add("紧急程度:" + entity.getUrgency().getText());
		ss.add("BUG状态:" + entity.getStatus().getText());
		ss.add(entity.getContent());
		{
			String content = StringManager.join(StringManager.NewLine, ss);
			List<String> ls = new ArrayList<String>();
			ls.add(UserPermissionManager.getUserPermission().getEmployee().getId().toString());
			ls.add(entity.getTestorId().toString());
			ls.add(entity.getDeveloperId().toString());
			ls.add(entity.getCreatorId().toString());

			wxpa.send("WeChat", content, StringManager.join("|", ls));
		}

		return entity;
	}

}