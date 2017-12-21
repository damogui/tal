package com.gongsibao.franchisee.web;

import org.netsharp.base.IReferenceFilterBuilder;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.entity.Organization;


public class BdOrganizationReferenceFilter implements IReferenceFilterBuilder {

	@Override
	public String builderFilter() {

		String pathCode = this.getChannelPathCode();
		String filterString = "path_code like '"+pathCode+"%'";
		return filterString;
	}

	/**
	 * @return
	 */
	private String getChannelPathCode() {

		IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
		Oql oql = new Oql();{
			oql.setType(Organization.class);
			oql.setSelects("id,pathCode");
			oql.setFilter("organizationFunction.code='Channel'");
		}
		Organization organization = organizationService.queryFirst(oql);
		if(organization != null){
			
			return organization.getPathCode();
		}
		return null;
	}
}
