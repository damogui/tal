package org.netsharp.employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.authorization.UserPermission;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.entity.CatEntity;
import org.netsharp.organization.base.IAuthorizationPrincipalService;
import org.netsharp.organization.base.IOrganizationEmployeeService;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.entity.AuthorizationPrincipal;
import org.netsharp.organization.entity.FieldGeteway;
import org.netsharp.organization.entity.Operation;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.entity.OrganizationEmployee;
import org.netsharp.organization.entity.PrincipalOperation;
import org.netsharp.util.StringManager;

/**
 * 获取当前用户的UserPermission ，对象中只有fieldGeteways属性和operations属性的值
 * 
 * */
public class UserOperationManager {

	public static UserPermission getUserOperations(Integer employeeId) {
		// 得到当前用户的所有岗位
		Mtable meta = MtableManager.getMtable(OrganizationEmployee.class);
		Column column = meta.getPropertyOrColumn("employeeId");

		Oql oql = new Oql();
		{
			oql.setType(OrganizationEmployee.class);
			oql.setSelects("organization.{id,code,name,pathCode,parentId}");
			// oql.setSelects("organization.pathCode");
			oql.setFilter("employeeId=?");
			oql.getParameters().add("@employeeId", employeeId, column.getDataType().getJdbcType());
		}

		IOrganizationEmployeeService organizationEmployeeService = ServiceFactory.create(IOrganizationEmployeeService.class);
		List<OrganizationEmployee> oes = organizationEmployeeService.queryList(oql);

		// 根据岗位得到所有上级部门的pathCode
		List<String> pathCodes = getPathCodes(oes);

		// 根据pathCode得到id
		oql = new Oql();
		{
			oql.setType(Organization.class);
			oql.setSelects("id");
			oql.setFilter("pathcode in (" + StringManager.join(",", pathCodes) + ")");
		}

		IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
		List<Organization> orgs = organizationService.queryList(oql);
		List<Integer> ids = new ArrayList<Integer>();
		for (Organization org : orgs) {
			ids.add(org.getId());
		}

		// 根据组织结构查询授权主体和授权主体的操作
		oql = new Oql();
		{
			oql.setType(AuthorizationPrincipal.class);
			oql.setSelects("id,principalId,principalOperations.{id,principalId,operationId},principalOperations.operation.{id,operationTypeId,resourceNodeId},principalOperations.operation.fieldGeteways.{id,operationId,entityId,propertyName}");
			oql.setFilter("principalId in (" + StringManager.join(",", ids) + ")");
		}

		System.out.println(oql.toString());

		IAuthorizationPrincipalService apService = ServiceFactory.create(IAuthorizationPrincipalService.class);
		List<AuthorizationPrincipal> aps = apService.queryList(oql);

		// 得到最终的操作权限集合
		List<Operation> operations = new ArrayList<Operation>();
		Map<String, List<String>> fieldGeteways = new HashMap<String, List<String>>();

		for (AuthorizationPrincipal ap : aps) {
			for (PrincipalOperation po : ap.getPrincipalOperations()) {

				Operation operation = po.getOperation();

				if (operation == null) {
					continue;
				}
				operations.add(operation);

				for (FieldGeteway fieldGetway : operation.getFieldGeteways()) {
					String entityId = fieldGetway.getEntityId();
					List<String> fields = fieldGeteways.get(entityId);
					if (fields == null) {
						fields = new ArrayList<String>();

						fieldGeteways.put(entityId, fields);
					}

					fields.add(fieldGetway.getPropertyName());
				}
			}
		}
		UserPermission upPermission = new UserPermission();
		upPermission.setFieldGeteways(fieldGeteways);
		upPermission.setOperations(operations);
		return upPermission;
	}

	// 获取人员岗位ID（部门级的）
	public static Integer getUserOrganizationId(Integer employeeId) {
		// 得到当前用户的所有岗位
		Mtable meta = MtableManager.getMtable(OrganizationEmployee.class);
		Column column = meta.getPropertyOrColumn("employeeId");

		Oql oql = new Oql();
		{
			oql.setType(OrganizationEmployee.class);
			oql.setSelects("organization.{id,code,name,pathCode,parentId}");
			// oql.setSelects("organization.pathCode");
			oql.setFilter("employeeId=?");
			oql.getParameters().add("@employeeId", employeeId, column.getDataType().getJdbcType());
		}

		IOrganizationEmployeeService organizationEmployeeService = ServiceFactory.create(IOrganizationEmployeeService.class);
		List<OrganizationEmployee> oes = organizationEmployeeService.queryList(oql);

		// 根据岗位得到所有上级部门的pathCode
		List<String> pathCodes = getPathCodes(oes);

		// 根据pathCode得到id
		oql = new Oql();
		{
			oql.setType(Organization.class);
			oql.setSelects("id");
			oql.setFilter("pathcode in (" + StringManager.join(",", pathCodes) + ") and organizationtype='Department' and parentId=1 ");
		}

		IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
		List<Organization> orgs = organizationService.queryList(oql);

		for (Organization org : orgs) {
			return org.getId();
		}
		return null;

	}

	private static List<String> getPathCodes(List<OrganizationEmployee> oes) {

		List<String> pathCodes = new ArrayList<String>();
		List<String> posts = new ArrayList<String>();
		List<String> departments = new ArrayList<String>();
		List<String> departmentPathCodes = new ArrayList<String>();

		for (OrganizationEmployee oe : oes) {

			Organization orgnizaiton = oe.getOrganization();
			if (orgnizaiton == null) {
				continue;
			}
			posts.add(oe.getOrganizationId().toString());
			departments.add(oe.getOrganization().getParentId().toString());
			departmentPathCodes.add(getParentPathCode(oe.getOrganization().getPathCode()));

			List<String> ss = getPathCodes(orgnizaiton.getPathCode());

			for (String pathCode : ss) {
				String str = "'" + pathCode + "'";
				if (!pathCodes.contains(str)) {
					pathCodes.add(str);
				}
			}
		}

		return pathCodes;
	}

	private static List<String> getPathCodes(String pathCode) {
		if (pathCode == null || pathCode == "")
			return null;
		// 根据pathCode得到上级所有几次的pathCode
		// 输入参数为：010103
		// 返回结果为：01,0101,010103
		List<String> codes = new ArrayList<String>();
		// 分类码的长度从：CatEntity.PATH_CODE_SIZE;
		for (int i = 0; i < pathCode.length(); i = i + CatEntity.PATH_CODE_SIZE) {
			codes.add(pathCode.substring(0, i + CatEntity.PATH_CODE_SIZE));
		}
		return codes;
	}

	private static String getParentPathCode(String pathCode) {
		if (pathCode == null) {
			return "";
		}

		if (pathCode.length() <= CatEntity.PATH_CODE_SIZE) {
			return "";
		}

		pathCode = pathCode.substring(0, pathCode.length() - CatEntity.PATH_CODE_SIZE);

		return pathCode;
	}
}
