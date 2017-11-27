package com.gongsibao.franchisee.service;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.entity.franchisee.FranchiseeLinkman;
import com.gongsibao.franchisee.base.IFranchiseeService;

@Service
public class FranchiseeService extends PersistableService<Franchisee> implements IFranchiseeService {

    public FranchiseeService(){
        super();
        this.type=Franchisee.class;
    }
    
	@Override
	public Franchisee save(Franchisee entity) {
		
		EntityState entityState = entity.getEntityState();
		entity = super.save(entity);
		if(entityState == EntityState.New){
			
			this.createMainLinkMan(entity);
		}
		return entity;
	}

	
	/**   
	 * @Title: createMainLinkMan   
	 * @Description: 创建主联系人
	 * @param: @param entity      
	 * @return: void      
	 * @throws   
	 */
	private void createMainLinkMan(Franchisee entity){
		
		if(!StringManager.isNullOrEmpty(entity.getLinkmanName())){

			FranchiseeLinkmanService linkmanService = ServiceFactory.create(FranchiseeLinkmanService.class);
			FranchiseeLinkman linkman = new FranchiseeLinkman();{
				
				linkman.toNew();
				linkman.setFranchiseeId(entity.getId());
				linkman.setName(entity.getLinkmanName());
				linkman.setWeixin(entity.getWeixin());
				linkman.setMobile(entity.getMobile());
				linkman.setMain(true);
			}
			linkmanService.save(linkman);
		}
	}
}