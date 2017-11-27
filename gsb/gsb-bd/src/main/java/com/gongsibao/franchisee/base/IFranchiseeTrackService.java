package com.gongsibao.franchisee.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.franchisee.FranchiseeTrack;

public interface IFranchiseeTrackService extends IPersistableService<FranchiseeTrack>{

	List<FranchiseeTrack> getTrackByFranchiseeId(Integer franchiseeId);
	
	List<FranchiseeTrack> getTrackByOwnerId(Integer ownerId);
}
