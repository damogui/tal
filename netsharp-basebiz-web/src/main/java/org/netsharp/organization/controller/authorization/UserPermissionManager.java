package org.netsharp.organization.controller.authorization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.netsharp.application.NetSharpConstant;
import org.netsharp.authorization.LoginException;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.NetsharpException;
import org.netsharp.core.Oql;
import org.netsharp.entity.CatEntity;
import org.netsharp.organization.base.IAuthorizationPrincipalService;
import org.netsharp.organization.base.IOrganizationEmployeeService;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.entity.AuthorizationPrincipal;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.FieldGeteway;
import org.netsharp.organization.entity.Operation;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.entity.OrganizationEmployee;
import org.netsharp.organization.entity.PrincipalOperation;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.core.comunication.IRequest;
import org.netsharp.util.StringManager;

public class UserPermissionManager {

	public static void resetUserPermission() {

		IRequest request = HttpContext.getCurrent().getRequest();
		UserPermission up = (UserPermission) request.getSession(NetSharpConstant.PANDA_USER);
		if (up == null) {
			return;
		}

		up.reset();
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
	public static void removeSession(HttpSession session) {
		HttpContext ctx = HttpContext.getCurrent();
		if (ctx == null) {
			return;
		}

		IRequest request = ctx.getRequest();
		UserPermission up = (UserPermission) request.getSession(NetSharpConstant.PANDA_USER);
		if (up != null) {
			up.desctroy();
		}
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
			up.desctroy();
			up = null;
		}
	}

	// 根据员工添加Session
	public static void addSession(Employee employee) {

		IRequest request = HttpContext.getCurrent().getRequest();

		UserPermission up = new UserPermission();
		{
			up.setEmployee(employee);
		}

		request.setSession(NetSharpConstant.PANDA_USER, up);

		addEmployeeContext(employee, request);

	}

	private static void addEmployeeContext(Employee employee, IRequest request) {
		
		throw new NetsharpException("not implements");
		
//		Long userId = employee.getId();
//		// 创建TokenSession
//		TokenSessionStore tokenSessionStore = EmployeeContextHolder.getTokenSessionStore();
//		TokenSession tokenSession = tokenSessionStore.createTokenSession(userId, TokenConstants.CPRD, TokenConstants.Cprd_Visi_Web);
//		String token = tokenSession.getToken();
//		//
//		EmployeeContext employeeContext = EmployeeContextHolder.getEmployeeContext();
//
//		employeeContext.setEmployeeId(userId);
//		employeeContext.setEmployeeName(employee.getName());
//		employeeContext.setPhoneNumber(employee.getMobile());
//		employeeContext.setAppId(TokenConstants.Cprd_Visi_Web);
//		employeeContext.setDerivedToken(token);
//
//		IOrganizationService orgS = ServiceFactory.create(IOrganizationService.class);
//		List<Organization> org = orgS.getDirectDepartmentByEmployeeId(userId);
//
//		if (org != null && org.size() != 0) {
//			employeeContext.setDepartmentId(org.get(0).getId());
//		}
//
//		//
//		request.setSession(EmployeeContextHolder.SESSION_KEY_EMPLOYEE_CONTEXT, employeeContext);
	}

	private static UserPermission getUserPermission(UserPermission up) {
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
			oql.setSelects("id");
			oql.setFilter("pathcode in (" + StringManager.join(",", pathCodes) + ") and organizationType='Post'");
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

		up.setOperations(operations);
		up.setFieldGeteways(fieldGeteways);
		up.setPermission(true);
		return up;
	}

	private static List<String> getPathCodes(List<OrganizationEmployee> oes, UserPermission up) {

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
}
