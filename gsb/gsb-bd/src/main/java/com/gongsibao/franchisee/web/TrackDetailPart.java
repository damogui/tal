package com.gongsibao.franchisee.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.DetailPart;

import com.gongsibao.entity.franchisee.FranchiseeTrack;
import com.gongsibao.franchisee.base.IFranchiseeTrackService;

public class TrackDetailPart extends DetailPart{

	IFranchiseeTrackService service = ServiceFactory.create(IFranchiseeTrackService.class);
	public FranchiseeTrack save(FranchiseeTrack entity){
		
		entity = service.save(entity);
		return entity;
	}
}
