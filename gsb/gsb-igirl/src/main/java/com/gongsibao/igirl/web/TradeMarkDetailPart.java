package com.gongsibao.igirl.web;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.DetailPart;

import com.gongsibao.entity.igirl.baseinfo.NCLTwo;
import com.gongsibao.igirl.base.INCLTwoService;
import com.gongsibao.igirl.base.INclMapService;
import com.gongsibao.igirl.service.NclMapService;

public class TradeMarkDetailPart extends DetailPart{
	INCLTwoService nclTwoService= ServiceFactory.create(INCLTwoService.class);
	INclMapService nclMapService = ServiceFactory.create(INclMapService.class);
	public List<NCLTwo> findSubsByNclOneId(int ncloneId){
		List<NCLTwo> ncls=nclTwoService.findSubsByNclOneId(ncloneId);
		return  ncls;
	}
	public String getTmplByNclOneId(int ncloneId){
		
		String ncls=nclMapService.getTmplByNclOneId(ncloneId);
		return  ncls;
	}

//	ICustomerFollowService service = ServiceFactory.create(ICustomerFollowService.class);
//	public CustomerFollow save(CustomerFollow entity){
//
//		entity = service.save(entity);
//		return entity;
//	}

}
