package com.gongsibao.report.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;

import com.gongsibao.uc.base.IOrganizationService;

public class RecursiveOrgaUtils {
	private final IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
	//临时存储前台传来的组织机构Id以及下属Id
	public String tempOrgaIds="";
	
	//获取组织机构Id
	public String getChildOragId(Integer parentId){
		tempOrgaIds += parentId+",";
		String getParentId = "SELECT pkid from uc_organization where pid=" + parentId;
		DataTable getDt = organizationService.executeTable(getParentId.toString(), null);
		if (getDt.size() > 0) {
			for (IRow row : getDt) {
				String getOrgaId = row.getString("pkid");
				getChildOragId(Integer.parseInt(getOrgaId));
			}
		}
		return tempOrgaIds;
	}	
}
