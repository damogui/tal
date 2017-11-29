package com.gongsibao.franchisee.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.EntityState;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.entity.franchisee.FranchiseeTrack;
import com.gongsibao.franchisee.base.IFranchiseeService;
import com.gongsibao.franchisee.base.IFranchiseeTrackService;
	
@Service
public class FranchiseeTrackService extends PersistableService<FranchiseeTrack> implements IFranchiseeTrackService {

    public FranchiseeTrackService(){
        super();
        this.type=FranchiseeTrack.class;
    }
    
	@Override
	public FranchiseeTrack save(FranchiseeTrack entity) {
		
		EntityState entityState = entity.getEntityState();
		entity = super.save(entity);
		if(entityState == EntityState.New){
			
			this.updateFranchiseeTrack(entity);
		}
		return entity;
	}
	
	/**   
	 * @Title: updateFranchiseeTrack   
	 * @Description: 更新供应商跟进信息
	 * @param: @param entity      
	 * @return: void      
	 * @throws   
	 */
	private void updateFranchiseeTrack(FranchiseeTrack entity) {
		
		IFranchiseeService franchiseeService = ServiceFactory.create(IFranchiseeService.class);
		Franchisee franchisee =  franchiseeService.byId(entity.getFranchiseeId());
		franchisee.setIntentionDegree(entity.getIntentionDegree());
		franchisee.setExpectedSign(entity.getExpectedSign());
		franchisee.setTrackProgress(entity.getTrackProgress());
		franchisee.setNextTrackDate(entity.getNextTrackDate());
		franchisee.setLastTrackContent(entity.getContent());
		franchisee.setLastTrackerId(entity.getCreatorId());
		franchisee.setLastTrackTime(entity.getCreateTime());
		franchiseeService.save(franchisee);
	}

	@Override
	public List<FranchiseeTrack> getTrackByFranchiseeId(Integer franchiseeId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("franchiseeId=?");
			oql.setOrderby("createTime Desc");
			oql.getParameters().add("franchiseeId", franchiseeId, Types.INTEGER);
		}
		return this.queryList(oql);
	}

	@Override
	public List<FranchiseeTrack> getTrackByOwnerId(Integer ownerId) {
		
		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("franchisee.ownerId=?");
			oql.setOrderby("createTime Desc");
			oql.getParameters().add("ownerId", ownerId, Types.INTEGER);
		}
		return this.queryList(oql);
	}
}