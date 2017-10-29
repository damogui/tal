package org.netsharp.scrum.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.authorization.UserPermissionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.scrum.base.IStoryService;
import org.netsharp.scrum.entity.Story;
import org.netsharp.scrum.entity.StoryParticipant;
import org.netsharp.service.PersistableService;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;
import org.netsharp.wx.ea.base.IEaMessageService;

@Service
public class StoryService extends PersistableService<Story> implements IStoryService {

	public StoryService() {
		super();
		this.type = Story.class;
	}

	@Override
	public Story save(Story entity) {

		EntityState state = entity.getEntityState();
		
		if(entity.getEntityState() != EntityState.Deleted){

			if(entity.getParticipants()!= null && entity.getParticipants().size() >0){
				
				List<String> ss = new ArrayList<String>();
				for(StoryParticipant sp :entity.getParticipants()){
					
					ss.add(sp.getParticipant().getName());
				}
				
				String participants = StringManager.join(",",ss);
				entity.setParticipantStr(participants);
			}
		}
		

		super.save(entity);

		entity = this.pm.byId(entity);


		List<String> ss = new ArrayList<String>();

		String executor = UserPermissionManager.getUserPermission().getEmployee().getName();

		ss.add("【任务】"+executor + state.getText() + "了用户任务");
		ss.add(entity.getName());
		ss.add(entity.getStatus().getText());
		ss.add(DateManage.toLongString(new Date()));
		ss.add("迭代:" + entity.getIteration().getName());
		ss.add("负责人:" + entity.getOwner().getName());
		ss.add("客户:" + entity.getBizUser().getName());
		ss.add("提出人:" + entity.getCreator());
		ss.add("紧急程度:" + entity.getUrgency().getText());
		ss.add("估时:" + entity.getEstimateHours()+"小时");
		ss.add(entity.getContent());

		{
			String content = StringManager.join(StringManager.NewLine, ss);
			List<String> ls = new ArrayList<String>();
			ls.add(UserPermissionManager.getUserPermission().getEmployee().getMobile());
			ls.add(entity.getOwner().getMobile());
			ls.add(entity.getBizUser().getMobile());
			ls.add(entity.getBizUser().getMobile());		
			for (StoryParticipant pp : entity.getParticipants()) {
				ls.add(pp.getParticipantId().toString());
			}			
			IEaMessageService eMessageService = ServiceFactory.create(IEaMessageService.class);
			eMessageService.send("SCRUM", content, StringManager.join("|", ls));
		}
		return entity;
	}
}
