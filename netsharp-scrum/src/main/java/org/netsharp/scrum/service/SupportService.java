package org.netsharp.scrum.service;

import org.netsharp.communication.Service;
import org.netsharp.scrum.base.ISupportService;
import org.netsharp.scrum.entity.Support;
import org.netsharp.service.PersistableService;

@Service
public class SupportService extends PersistableService<Support> implements ISupportService {

	public SupportService() {
		super();
		this.type = Support.class;
	}

	@Override
	public Support save(Support entity) {

//		EntityState state = entity.getEntityState();

		super.save(entity);

		entity = this.pm.byId(entity);


//		List<String> ss = new ArrayList<String>();
//
//		// ss.add(EmployeeContextHolder.getEmployeeContext().getEmployeeName() +
//		// state + "了支持");
//		String executor = UserPermissionManager.getUserPermission().getEmployee().getName();
//		ss.add(executor + state.getText() + "了支持");
//		ss.add(entity.getName());
//		ss.add(entity.getStatus().getText());
//		ss.add(DateManage.toLongString(new Date()));
//		ss.add("负责人:" + entity.getOwner().getName());
//		ss.add("提出人:" + entity.getPutor().getName());
//		ss.add("紧急程度:" + entity.getUrgency().getText());
//		ss.add("估时:" + entity.getEstimateHours()+"小时");
//		ss.add(entity.getContent());
//		{
//			String content = StringManager.join(StringManager.NewLine, ss);
//			List<String> ls = new ArrayList<String>();
//			ls.add(UserPermissionManager.getUserPermission().getEmployee().getId().toString());
//			ls.add(entity.getOwnerId().toString());
//			
//			if(entity.getCreatorId() != null){
//				ls.add(entity.getCreatorId().toString());
//			}
//			if(entity.getPutorId()!=null){
//				ls.add(entity.getPutorId().toString());
//			}
//			
//			INotifyervice wxpaService = ServiceFactory.create(INotifyervice.class);
//			wxpaService.send("WeChat", content,  StringManager.join("|", ls));
//		}
		return entity;
	}
}
