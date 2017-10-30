package org.netsharp.scrum.service;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.scrum.base.IStoryTraceService;
import org.netsharp.scrum.entity.StoryTrace;
import org.netsharp.service.PersistableService;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;
import org.netsharp.wx.ea.base.IEaMessageService;

@Service
public class StoryTraceService extends PersistableService<StoryTrace> implements IStoryTraceService {
	
	public StoryTraceService(){
		super();
		this.type=StoryTrace.class;
	}
	
	@Override
	public StoryTrace save(StoryTrace entity) {
		
		EntityState state = entity.getEntityState();
		entity = super.save(entity);
		if(state != EntityState.Deleted){

			List<String> ss = new ArrayList<String>();
			String executor = UserPermissionManager.getUserPermission().getEmployee().getName();
			ss.add("【跟进】"+executor +"添加了用户任务跟进");
			ss.add(DateManage.toLongString(entity.getCreateTime()));
			ss.add("用户任务："+entity.getStory().getName());
			ss.add(entity.getContent());
			{
				String content = StringManager.join(StringManager.NewLine, ss);
				IEaMessageService eMessageService = ServiceFactory.create(IEaMessageService.class);
				eMessageService.send("SCRUM", content,  UserPermissionManager.getUserPermission().getEmployee().getMobile());
			}
		}
		return entity;
	}
}
