package org.netsharp.scrum.service;

import org.netsharp.communication.Service;
import org.netsharp.scrum.base.IStoryTraceService;
import org.netsharp.scrum.entity.StoryTrace;
import org.netsharp.service.PersistableService;

@Service
public class StoryTraceService extends PersistableService<StoryTrace> implements IStoryTraceService {
	
	public StoryTraceService(){
		super();
		this.type=StoryTrace.class;
	}
	
	@Override
	public StoryTrace save(StoryTrace entity) {
		entity = super.save(entity);
//		List<String> ss = new ArrayList<String>();
//		String executor = UserPermissionManager.getUserPermission().getEmployee().getName();
//		ss.add(executor +"添加了用户任务跟进");
//		ss.add(DateManage.toLongString(entity.getCreateTime()));
//		ss.add("用户任务："+entity.getStory().getName());
//		ss.add(entity.getContent());
//		{
//			String content = StringManager.join(StringManager.NewLine, ss);
//			INotifyervice wxpaService = ServiceFactory.create(INotifyervice.class);
//			wxpaService.send("WeChat", content,  UserPermissionManager.getUserPermission().getEmployee().getId().toString());
//		}
		return entity;
	}
}
