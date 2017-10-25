package com.ykx.panda;

import java.sql.Types;
import java.util.List;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.organization.base.IAuthorizationPrincipalService;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.dic.PrincipalType;
import org.netsharp.organization.entity.AuthorizationPrincipal;
import org.netsharp.organization.entity.Organization;
import org.netsharp.util.JsonManage;

public class MyCat {
	

	IAuthorizationPrincipalService principalService = ServiceFactory.create(IAuthorizationPrincipalService.class);
	@Test
	public void test(){
		
//		IDataAccess dao = DataAccessFactory.create();
//		
//		String cmdText = "select * from sys_permission_employee";
//		
//		DataTable table = dao.executeTable(cmdText, null);
//		
//		System.out.println(table.size());

		IOrganizationService service = ServiceFactory.create(IOrganizationService.class);
		Oql oql = new Oql();
		{
			oql.setType(Organization.class);
			oql.setSelects("*");
		}

		List<Organization> list = service.queryList(oql);
		for (Organization organization : list) {
			
			principalService.addByPost(organization);
		}
		
//		String aString = JsonManage.serialize2(OrganizationType.CATETORY);
//		System.out.println(aString);
//		
//		OrganizationType type;
//		try {
//			type = (OrganizationType) JsonManage.deSerialize(OrganizationType.class, "5");
//			System.out.println(type);
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	
	public AuthorizationPrincipal queryFirst(Organization organization) {

		Oql oql = new Oql();
		{
			oql.setType(AuthorizationPrincipal.class);
			oql.setSelects("*");
			oql.setFilter("principalId = ?");

			oql.getParameters().add("@principalId", organization.getId(), Types.INTEGER);
		}

		AuthorizationPrincipal principal = principalService.queryFirst(oql);

		return principal;
	}
}
