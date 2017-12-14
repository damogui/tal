package com.gongsibao.uc.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.application.Application;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.EntityState;
import org.netsharp.core.IRow;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.service.PersistableService;
import org.netsharp.util.EncrypUtil;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.SelectBuilder;

import com.gongsibao.entity.uc.User;
import com.gongsibao.entity.uc.UserOrganizationMap;
import com.gongsibao.uc.base.IOrganizationService;
import com.gongsibao.uc.base.IUserService;

@Service
public class UserService extends PersistableService<User> implements IUserService {

	public UserService() {
		super();
		this.type = User.class;
	}

	@Override
	public User save(User entity) {

		EntityState entityState = entity.getEntityState();

		if (StringManager.isNullOrEmpty(entity.getPasswd())) {

			String password = EncrypUtil.md5(Application.getContext().getDefaultPassword() + "user!@#123").substring(8, 24);
			;
			entity.setPasswd(password);
		}

		entity = super.save(entity);
		if (entityState == EntityState.New) {

			// 同步NetSharp组织机构
			this.createEmployee(entity);
		}
		return entity;
	}

	/**
	 * @Title: createEmployee
	 * @Description: TODO(同步NetSharp组织机构)
	 * @param: @param user
	 * @return: void
	 * @throws
	 */
	private void createEmployee(User user) {

		if (isHas(user.getMobilePhone())) {

			return;
		}
		Employee employee = new Employee();
		{
			employee.toNew();
			employee.setLoginName(user.getMobilePhone());
			employee.setMobile(user.getMobilePhone());

			String name = user.getName();
			if (StringManager.isNullOrEmpty(name)) {
				name = user.getMobilePhone();
			}
			employee.setName(name);
			employee.setEmail(user.getEmail());
			employee.setDisabled(!(user.getEnabled()));
		}
		IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
		employeeService.save(employee);
	}

	/**
	 * @Title: isHas
	 * @Description: TODO(判断是否已存在)
	 * @param: @param mobile
	 * @param: @return
	 * @return: boolean
	 * @throws
	 */
	private boolean isHas(String mobile) {

		String cmdText = "select count(0) from sys_permission_employee where mobile='" + mobile + "'";

		int count = pm.executeInt(cmdText, null);

		return count > 0;
	}

	@Override
	public User byMobilePhone(String mobilePhone) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("user.{id,mobilePhone}");
			oql.setFilter(" mobilePhone=? ");
			oql.getParameters().add("mobilePhone", mobilePhone, Types.VARCHAR);
		}

		User entity = this.pm.queryFirst(oql);
		return entity;
	}

	@Override
	public Boolean hasMobile(Integer id, String mobile) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
		}

		List<String> ss = new ArrayList<String>();
		ss.add("mobilePhone =?");
		oql.getParameters().add("mobile", mobile, Types.VARCHAR);
		if (id != null) {
			ss.add("id<>?");
			oql.getParameters().add("id", id, Types.INTEGER);
		}
		String filter = StringManager.join(" AND ", ss);
		oql.setFilter(filter);

		return this.queryCount(oql) > 0;
	}

	IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);

	@Override
	public List<Integer> getIdList(Integer departmentId) {

		List<Integer> userIdList = new ArrayList<Integer>();

		Boolean isHasChild = organizationService.hasChildDepartment(departmentId);
		if (isHasChild) {

			List<Integer> childDepartmentIdList = organizationService.getChildDepartmentIdList(departmentId);
			for (Integer childDepartmentId : childDepartmentIdList) {

				//递归获取UserId
				List<Integer> childUserIdList = getIdList(childDepartmentId);
				userIdList.addAll(childUserIdList);
			}

		} else {

			SelectBuilder builder = SelectBuilder.getInstance();
			{
				builder.select("user_id");
				builder.from(MtableManager.getMtable(UserOrganizationMap.class).getTableName());
				builder.where("organization_id=?");
			}

			QueryParameters qps = new QueryParameters();
			qps.add("departmentId", departmentId, Types.INTEGER);

			DataTable dataTable = this.pm.executeTable(builder.toSQL(), qps);
			for (IRow row : dataTable) {

				Integer userId = row.getInteger("user_id");
				userIdList.add(userId);
			}
		}
		return userIdList;
	}
}