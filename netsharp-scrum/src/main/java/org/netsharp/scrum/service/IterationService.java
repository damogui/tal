package org.netsharp.scrum.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.notify.INotifyervice;
import org.netsharp.scrum.base.IIterationService;
import org.netsharp.scrum.entity.Iteration;
import org.netsharp.service.PersistableService;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;

@Service
public class IterationService extends PersistableService<Iteration> implements IIterationService {

	public IterationService() {
		super();
		this.type = Iteration.class;
	}

	@Override
	public Iteration save(Iteration entity) {

		EntityState state = entity.getEntityState();

		super.save(entity);

		entity = this.pm.byId(entity);

		INotifyervice wxpa = ServiceFactory.create(INotifyervice.class);

		List<String> ss = new ArrayList<String>();

		String executor = UserPermissionManager.getUserPermission().getEmployee().getName();
		ss.add(executor + state.getText() + "了迭代");
		ss.add(entity.getName());
		ss.add(DateManage.toLongString(new Date()));
		ss.add("起始日期:" + DateManage.toLongString(entity.getStartTime()));
		ss.add("结束日期:" + DateManage.toLongString(entity.getEndTime()));
		ss.add("当前迭代:" + entity.getIsCurrent());
		ss.add("迭代目标:" + entity.getContent());
		ss.add(entity.getContent());
		{
			String content = StringManager.join(StringManager.NewLine, ss);

			wxpa.sendAll("SCRUM", content);
		}

		return entity;
	}
}
