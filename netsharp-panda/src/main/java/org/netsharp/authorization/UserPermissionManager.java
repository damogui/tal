package org.netsharp.authorization;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.netsharp.application.NetSharpConstant;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.BusinessException;
import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.entity.CatEntity;
import org.netsharp.organization.base.IAuthorizationPrincipalService;
import org.netsharp.organization.base.IOrganizationEmployeeService;
import org.netsharp.organization.base.IOrganizationFunctionService;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.dic.OrganizationType;
import org.netsharp.organization.dic.PrincipalType;
import org.netsharp.organization.entity.AuthorizationPrincipal;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.FieldGeteway;
import org.netsharp.organization.entity.Operation;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.entity.OrganizationEmployee;
import org.netsharp.organization.entity.PrincipalOperation;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.comunication.IRequest;
import org.netsharp.util.DateManage;
import org.netsharp.util.StringManager;

public class UserPermissionManager {

	public static void resetUserPermission() {

		IRequest request = HttpContext.getCurrent().getRequest();

		UserPermission up = (UserPermission) request.getSession(NetSharpConstant.PANDA_USER);
		if (up == null) {
			return;
		}

		up.reset();
		fixIssueOfUpdateSessionInRedis();
	}

	// 从Session中得到用户权限设置
	public static UserPermission getUserPermission() {

		IRequest request = HttpContext.getCurrent().getRequest();
		UserPermission up = (UserPermission) request.getSession(NetSharpConstant.PANDA_USER);
		if (up == null) {
			throw new LoginException();
		}

		if (!up.isPermission()) {
			UserPermissionManager.getUserPermission(up);
			// 加载权限后，重新设置 用户 的session
			request.setSession(NetSharpConstant.PANDA_USER, up);
			fixIssueOfUpdateSessionInRedis();
		}

		return up;
	}

	public static UserPermission getUserPermissionWithoutException() {

		HttpContext ctx = HttpContext.getCurrent();
		if (ctx == null) {
			return null;
		}

		IRequest request = ctx.getRequest();

		if (request == null) {
			return null;
		}

		Object obj = request.getSession(NetSharpConstant.PANDA_USER);
		if (obj == null) {
			return null;
		}
		UserPermission up = (UserPermission) obj;

		if (!up.isPermission()) {
			return null;
		}

		return up;
	}

	// 释放用户权限设置
	// session注销或者注销时执行
	public static void removeSession() {

		HttpContext ctx = HttpContext.getCurrent();
		if (ctx == null) {
			return;
		}

		IRequest request = ctx.getRequest();
		UserPermission up = (UserPermission) request.getSession(NetSharpConstant.PANDA_USER);

		if (up != null) {

			Employee employee = up.getEmployee();
			if (employee != null) {

				EmployeeOnlineManager.getInstance().removeSession(employee.getId());
			}
			up.desctroy();
			up = null;
		}
		request.removeSession(NetSharpConstant.PANDA_USER);
	}

	// 根据员工添加Session
	public static void addSession(Employee employee) {

		IRequest request = HttpContext.getCurrent().getRequest();

		UserPermission up = new UserPermission();
		{
			up.setEmployee(employee);
		}

		UserClient userClient = new UserClient();
		{
			List<String> list = request.getClient();
			if (list.size() > 0) {
				userClient.setExplorer(list.get(0));
				userClient.setIpAddress(list.get(1));
				userClient.setPcName(list.get(2));
			}

		}
		up.setUserClient(userClient);
		EmployeeOnlineManager.getInstance().addSession(up);

		request.setSession(NetSharpConstant.PANDA_USER, up);
	}

	public static UserPermission getUserPermission(UserPermission up) {
		Object userId = up.getEmployee().getId();

		// 得到当前用户的所有岗位
		Mtable meta = MtableManager.getMtable(OrganizationEmployee.class);
		Column column = meta.getPropertyOrColumn("employeeId");

		Oql oql = new Oql();
		{
			oql.setType(OrganizationEmployee.class);
			oql.setSelects("organization.{id,code,name,pathCode,parentId}");
			// oql.setSelects("organization.pathCode");
			oql.setFilter("employeeId=?");
			oql.getParameters().add("@employeeId", userId, column.getDataType().getJdbcType());
		}

		IOrganizationEmployeeService organizationEmployeeService = ServiceFactory.create(IOrganizationEmployeeService.class);
		List<OrganizationEmployee> oes = organizationEmployeeService.queryList(oql);

		// 根据岗位得到所有上级部门的pathCode
		List<String> pathCodes = UserPermissionManager.getPathCodes(oes, up);

		// 根据pathCode得到id
		oql = new Oql();
		{
			oql.setType(Organization.class);
			oql.setSelects("id,positionId");
			oql.setFilter("pathcode in (" + StringManager.join(",", pathCodes) + ") and organizationType=" + OrganizationType.POST.getValue());
		}

		IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
		List<Organization> orgs = organizationService.queryList(oql);

		List<Integer> orgIds = new ArrayList<Integer>();
		List<Integer> posIds = new ArrayList<Integer>();
		for (Organization org : orgs) {
			orgIds.add(org.getId());
			if (org.getPositionId() != null) {
				posIds.add(org.getPositionId());
			}
		}

		// 根据组织结构查询授权主体和授权主体的操作
		oql = new Oql();
		{
			oql.setType(AuthorizationPrincipal.class);
			oql.setSelects("id,principalId,principalOperations.{id,principalId,operationId},principalOperations.operation.operationtype.{name},principalOperations.operation.resourceNode.{pathName,name}, principalOperations.operation.{id,name,operationTypeId,resourceNodeId},principalOperations.operation.fieldGeteways.{id,operationId,entityId,propertyName}");
			oql.setFilter("principalType="+OrganizationType.POST.getValue()+" AND principalId in (" + StringManager.join(",", orgIds) + ")");
		}

		IAuthorizationPrincipalService apService = ServiceFactory.create(IAuthorizationPrincipalService.class);
		List<AuthorizationPrincipal> aps = apService.queryList(oql);

		// 根据职务查询授权主体和授权主体的操作
		oql = new Oql();
		{
			oql.setType(AuthorizationPrincipal.class);
			oql.setSelects("id,principalId,principalOperations.{id,principalId,operationId},principalOperations.operation.operationtype.{name},principalOperations.operation.resourceNode.{pathName,name}, principalOperations.operation.{id,name,operationTypeId,resourceNodeId},principalOperations.operation.fieldGeteways.{id,operationId,entityId,propertyName}");
			oql.setFilter("principalType="+PrincipalType.STATION.getValue()+" AND principalId in (" + StringManager.join(",", posIds) + ")");
		}

		List<AuthorizationPrincipal> apposs = apService.queryList(oql);
		if (apposs != null && apposs.size() > 0) {
			aps.addAll(apposs);
		}

		// 得到最终的操作权限集合
		List<Operation> operations = new ArrayList<Operation>();
		Map<String, List<String>> fieldGeteways = new HashMap<String, List<String>>();

		Set<Integer> operationId = new HashSet<Integer>();

		for (AuthorizationPrincipal ap : aps) {
			for (PrincipalOperation po : ap.getPrincipalOperations()) {

				Operation operation = po.getOperation();

				if (operation == null || operationId.contains(operation.getId())) {
					continue;
				}
				operations.add(operation);
				operationId.add(operation.getId());

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

		up.setOperations(operations);
		up.setFieldGeteways(fieldGeteways);
		up.setPermission(true);
		return up;
	}

	private static List<String> getPathCodes(List<OrganizationEmployee> oes, UserPermission up) {

		IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);

		List<String> pathCodes = new ArrayList<String>();
		List<String> posts = new ArrayList<String>();
		List<String> departments = new ArrayList<String>();
		List<String> departmentPathCodes = new ArrayList<String>();

		for (OrganizationEmployee oe : oes) {

			Organization orgnizaiton = oe.getOrganization();
			if (orgnizaiton == null) {
				continue;
			}

			//
			posts.add(oe.getOrganizationId().toString());
			departments.add(oe.getOrganization().getParentId().toString());
			departmentPathCodes.add(UserPermissionManager.getParentPathCode(oe.getOrganization().getPathCode()));

			List<String> ss = UserPermissionManager.getPathCodes(orgnizaiton.getPathCode());
			for (String pathCode : ss) {
				String str = "'" + pathCode + "'";
				if (!pathCodes.contains(str)) {
					pathCodes.add(str);
				}
			}
		}
		up.setPosts(StringManager.join(",", posts));
		up.setDepartments(StringManager.join(",", departments));
		up.setDepartmentPathCodes(departmentPathCodes);
		if (departments.size() == 1) {
			up.setDepartmentId(Integer.parseInt(departments.get(0)));
		}

		// 查询出所在公司的id,设置到up中，按照公司的枚举查询出所有的公司的id,pathCode的实体列表,然后
		// 遍历公司的pathCode,过滤出公司，然后把Id设置到会话中
		if (departmentPathCodes.size() > 0) {
			Organization comp = organizationService.getCorprationByDepartment(departmentPathCodes.get(0));
			if (comp != null) {
				up.setCorperation(comp.getId());
				up.setCorperationShortCode(comp.getShorthand());
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
		// codes.add(pathCode);
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

	/**
	 * @param employeeId
	 * @param type
	 * @param function
	 * @return
	 */
	public static Organization getUserDepartment(Integer employeeId, OrganizationType type, String organizationFunctionType) {
		// 得到当前用户的所有岗位
		Mtable meta = MtableManager.getMtable(OrganizationEmployee.class);
		Column column = meta.getPropertyOrColumn("employeeId");

		Oql oql = new Oql();
		{
			oql.setType(OrganizationEmployee.class);
			oql.setSelects("organization.{id,code,name,pathCode,parentId,organizationFunctionId}");
			// oql.setSelects("organization.pathCode");
			oql.setFilter("employeeId=?");
			oql.getParameters().add("@employeeId", employeeId, column.getDataType().getJdbcType());
		}

		IOrganizationEmployeeService organizationEmployeeService = ServiceFactory.create(IOrganizationEmployeeService.class);
		List<OrganizationEmployee> oes = organizationEmployeeService.queryList(oql);

		IOrganizationFunctionService organizationFunctionService = ServiceFactory.create(IOrganizationFunctionService.class);
		for (OrganizationEmployee oe : oes) {
			oe.getOrganization().setOrganizationFunction(organizationFunctionService.byId(oe.getOrganization().getOrganizationFunctionId()));
		}

		// 根据岗位得到所有上级部门的pathCode
		List<String> pathCodes = getPathCodes(oes);

		String filter = " and pathCode!='0001' and organizationtype=" + type.getValue() + " and organizationFunction.code='" + organizationFunctionType + "' ";

		// 根据pathCode得到id
		oql = new Oql();
		{
			oql.setType(Organization.class);
			oql.setSelects("Organization.*,organizationFunction.{id,name,code}");
			oql.setFilter("pathcode in (" + StringManager.join(",", pathCodes) + ") " + filter);
		}

		IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
		List<Organization> orgs = organizationService.queryList(oql);

		if (orgs != null && orgs.size() > 0) {

			return orgs.get(0);
		}
		// for (Organization org : orgs) {
		// return org;
		// }
		return null;

	}

	public static Organization getUserDepartment(OrganizationType type, String organizationFunctionType) {

		Employee employee = getUserPermission().getEmployee();
		if (employee == null) {

			throw new BusinessException("获取用户信息失败！");
		}
		Organization Organization = getUserDepartment(employee.getId(), type, organizationFunctionType);
		return Organization;
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



	private static void fixIssueOfUpdateSessionInRedis() {
		
		HttpContext.getCurrent().getRequest().setSession("null-null", DateManage.toLongString(new Date()));
	}
}
