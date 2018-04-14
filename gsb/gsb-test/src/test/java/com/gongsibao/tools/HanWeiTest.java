package com.gongsibao.tools;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.SalesmanRole;
import com.gongsibao.entity.uc.Organization;
import com.gongsibao.supplier.base.ISalesmanService;
//UPDATE sp_salesman s
//LEFT JOIN sp_department d on s.department_id=d.id
//set s.supplier_id = d.supplier_id
//where s.supplier_id = 0;
public class HanWeiTest {

	ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
	IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);

	@Test
	public void run() {

		IPersister<Organization> pm = PersisterFactory.create();

		String sql = "SELECT s.*,e.id from test_user_salesman s LEFT JOIN sys_permission_employee e on s.pkid = e.id ORDER BY organization_id;";
		DataTable dataTable = pm.executeTable(sql, null);
		int c = dataTable.size();
		System.out.println("共" + dataTable.size() + "条");
		for (IRow row : dataTable) {

			Integer pkid = row.getInteger("pkid");
			String realName = row.getString("real_name");
			String mobilePhone = row.getString("mobile_phone");
			Integer organizationId = row.getInteger("organization_id");
			String shortName = row.getString("short_name");
			Integer departmentId = row.getInteger("department_id");
			Integer employeeId = row.getInteger("id");

			// 手机号码存在2个以上的不处理
			if (getEmployeeCount(mobilePhone) > 1 || pkid.equals(3459)) {

				continue;
			}

			boolean isHas = hasMobile(mobilePhone);
			System.out.println("【" + realName + "】----开始");
			if (isHas) {

				System.out.println("【" + realName + "】----更新");
				// 更新新的employeeId
				UpdateBuilder updateBuilder = new UpdateBuilder();
				{
					updateBuilder.update(MtableManager.getMtable(Salesman.class).getTableName());
					updateBuilder.set("employee_id", employeeId);
					updateBuilder.where("mobile=?");
				}

				QueryParameters qps = new QueryParameters();
				qps.add("mobile", mobilePhone, Types.VARCHAR);
				pm.executeNonQuery(updateBuilder.toSQL(), qps);

			} else {

				Employee employee = employeeService.byId(pkid);
				Salesman salesman = new Salesman();
				{
					salesman.toNew();
					salesman.setEmployeeId(employeeId);
					salesman.setDepartmentId(departmentId);
					salesman.setDisabled(employee.getDisabled());
					salesman.setName(employee.getName());
					salesman.setMobile(employee.getMobile());
					salesman.setLoginName(employee.getLoginName());
					salesman.setRoles(getRoles());
				}
				salesmanService.save(salesman);
			}

			c = c - 1;
			System.out.println("【" + realName + "】----完成------还有" + c + "条");

		}
	}

	public List<SalesmanRole> getRoles() {
		List<SalesmanRole> salesmanRoleList = new ArrayList<SalesmanRole>();

		SalesmanRole sr = new SalesmanRole();
		sr.toNew();
		sr.setRoleId(3);
		salesmanRoleList.add(sr);

		sr = new SalesmanRole();
		sr.toNew();
		sr.setRoleId(6);
		salesmanRoleList.add(sr);

		sr = new SalesmanRole();
		sr.toNew();
		sr.setRoleId(24);
		salesmanRoleList.add(sr);

		return salesmanRoleList;
	}

	public boolean hasMobile(String mobile) {

		Oql oql = new Oql();
		{
			oql.setType(Salesman.class);
			oql.setSelects("*");
			oql.setFilter("mobile=?");
			oql.getParameters().add("@mobile", mobile, Types.VARCHAR);
		}
		return salesmanService.queryCount(oql) > 0;
	}

	public int getEmployeeCount(String mobile) {

		Oql oql = new Oql();
		{
			oql.setType(Employee.class);
			oql.setSelects("*");
			oql.setFilter("loginName=?");
			oql.getParameters().add("@loginName", mobile, Types.VARCHAR);
		}
		return employeeService.queryCount(oql);
	}

}
// SELECT
// u.pkid,u.real_name,u.mobile_phone,map.organization_id
// FROM
// uc_user_organization_map map
// LEFT JOIN uc_user u on map.user_id = u.pkid
// WHERE
// organization_id IN (
// SELECT
// id
// FROM
// uc_organization
// WHERE
// pid IN (
// SELECT
// pkid
// FROM
// uc_organization
// WHERE
// short_name IN (
// '北分公司宝',
// '北分知产',
// '北分基础',
// '广州分公司',
// '福州分公司',
// '深圳分公司',
// '成都分公司',
// '上海分公司',
// '杭州分公司',
// '天津分公司',
// '郑州分公司'
// )
// )
// );

/***
 *************************************************
 15 公司宝北分基础
 * 
 * 22 老客户-关松 23 新客组-张芳芳 24 新客组-高建辉 25 新客组-赵明月
 * 
 *************************************************
 * 1 北分知产 2 北分知产-业务组
 * 
 * 3 老客组-刘文悦 4 新客1组-巨龙 5 新客2组-安昌日 6 新客3组-姜丽 7 后期组
 *************************************************
 * 16 公司宝北分 36 老客组-邵慧敏 37 新客组-胡少川 38 新客组-王驰
 * 
 *************************************************
 * 12 公司宝深分
 * 
 *************************************************
 * 21 公司宝广分
 * 
 *************************************************
 * 34 公司宝福分 39 福分一组
 *************************************************
 * 19 公司宝成分
 * 
 *************************************************
 * 33 公司宝二本
 * 
 *************************************************
 * 18 公司宝上分 20 上分知产
 * 
 *************************************************
 * 13 公司宝天分
 *************************************************
 * 14 公司宝郑分
 * 
 *************************************************
 *
 * UPDATE sp_salesman SET login_name = SUBSTR(login_name, 1, 11) WHERE
 * CHAR_LENGTH(login_name) = 12
 ***/

