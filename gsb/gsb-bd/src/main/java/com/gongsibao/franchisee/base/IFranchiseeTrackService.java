package com.gongsibao.franchisee.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.franchisee.FranchiseeTrack;

public interface IFranchiseeTrackService extends IPersistableService<FranchiseeTrack>{

	/**
	 * @param franchiseeId
	 * @return
	 */
	List<FranchiseeTrack> getTrackByFranchiseeId(Integer franchiseeId);
	
	/**
	 * @param ownerId
	 * @return
	 */
	List<FranchiseeTrack> getTrackByOwnerId(Integer ownerId);
	
	/**
	 * @param ids
	 * @param departmentId
	 * @param ownerId
	 */
	void addAllotTrack(String[] ss, Integer departmentId, Integer ownerId);
}
