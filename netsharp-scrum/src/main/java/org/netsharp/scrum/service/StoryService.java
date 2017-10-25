package org.netsharp.scrum.service;

import org.netsharp.communication.Service;
import org.netsharp.scrum.base.IStoryService;
import org.netsharp.scrum.entity.Story;
import org.netsharp.service.PersistableService;

@Service
public class StoryService extends PersistableService<Story> implements IStoryService {

	public StoryService() {
		super();
		this.type = Story.class;
	}

	@Override
	public Story save(Story entity) {

//		EntityState state = entity.getEntityState();

		super.save(entity);

		entity = this.pm.byId(entity);


//		List<String> ss = new ArrayList<String>();
//
//		String executor = UserPermissionManager.getUserPermission().getEmployee().getName();
//
//		ss.add(executor + state.getText() + "了用户任务");
//		ss.add(entity.getName());
//		ss.add(entity.getStatus().getText());
//		ss.add(DateManage.toLongString(new Date()));
//		ss.add("迭代:" + entity.getIteration().getName());
//		ss.add("负责人:" + entity.getOwner().getName());
//		ss.add("客户:" + entity.getBizUser().getName());
//		ss.add("提出人:" + entity.getCreator());
//		ss.add("紧急程度:" + entity.getUrgency().getText());
//		ss.add("估时:" + entity.getEstimateHours()+"小时");
//		ss.add(entity.getContent());
//
//		{
//			String content = StringManager.join(StringManager.NewLine, ss);
//			List<String> ls = new ArrayList<String>();
//			ls.add(UserPermissionManager.getUserPermission().getEmployee().getId().toString());
//			ls.add(entity.getOwnerId().toString());
//			ls.add(entity.getBizUserId().toString());
//			ls.add(entity.getBizUserId().toString());
//			ls.add(entity.getCreatorId().toString());			
//			for (StoryParticipant pp : entity.getParticipants()) {
//				ls.add(pp.getParticipantId().toString());
//			}			
//			INotifyervice wxpaService = ServiceFactory.create(INotifyervice.class);
//			wxpaService.send("WeChat", content, StringManager.join("|", ls));
//		}
		return entity;
	}
}
