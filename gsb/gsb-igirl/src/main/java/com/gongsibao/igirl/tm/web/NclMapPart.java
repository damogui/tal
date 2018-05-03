package com.gongsibao.igirl.tm.web;

import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.entity.igirl.tm.baseinfo.NCLTwo;
import com.gongsibao.igirl.tm.base.INCLTwoService;

public class NclMapPart extends FormPart{
	INCLTwoService nclTwoService= ServiceFactory.create(INCLTwoService.class);
	public List<NCLTwo> findSubsByNclOneId(int ncloneId){
		List<NCLTwo> ncls=nclTwoService.findSubsByNclOneId(ncloneId);
		return  ncls;
	}
}
