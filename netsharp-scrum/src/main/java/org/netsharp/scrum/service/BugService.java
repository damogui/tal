package org.netsharp.scrum.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.organization.entity.Employee;
import org.netsharp.scrum.base.IBugService;
import org.netsharp.scrum.entity.Bug;
import org.netsharp.service.PersistableService;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;
import org.netsharp.wx.ea.base.IEaMessageService;

@Service
public class BugService extends PersistableService<Bug> implements IBugService {

	public BugService() {
		super();

		this.type = Bug.class;
	}

	public Bug newInstance() {

		Bug bug = super.newInstance();
		{

			Employee testor = new Employee();
			testor.setId(bug.getCreatorId());
			testor.setName(bug.getCreator());
			bug.setTestor(testor);
			bug.setTestorId(bug.getCreatorId());
		}

		return bug;
	}

	@Override
	public Bug save(Bug entity) {

		EntityState state = entity.getEntityState();
		super.save(entity);
		Bug bug = this.pm.byId(entity);
		if (state != EntityState.Deleted) {

			new Thread() {
				@Override
				public void run() {

					sendWxMessage(bug, state.getText());
				}
			}.start();
		}
		return bug;
	}

	private void sendWxMessage(Bug entity, String operation) {

		IEaMessageService eMessageService = ServiceFactory.create(IEaMessageService.class);
		List<String> ss = new ArrayList<String>();
		String executor = UserPermissionManager.getUserPermission().getEmployee().getName();
		ss.add("【BUG】" + executor + operation + "了BUG");
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
			ls.add(UserPermissionManager.getUserPermission().getEmployee().getMobile());
			ls.add(entity.getTestor().getMobile());
			ls.add(entity.getDeveloper().getMobile());

			// wxpa.send("SCRUM", content, StringManager.join("|", ls));
			eMessageService.send("SCRUM", content, StringManager.join("|", ls));
		}
	}

}