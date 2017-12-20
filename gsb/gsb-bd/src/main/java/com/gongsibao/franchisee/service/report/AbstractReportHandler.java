package com.gongsibao.franchisee.service.report;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.MtableManager;
import org.netsharp.core.QueryParameters;
import org.netsharp.organization.entity.Organization;
import org.netsharp.organization.entity.OrganizationEmployee;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.DeleteBuilder;
import org.netsharp.util.sqlbuilder.SelectBuilder;

import com.gongsibao.entity.franchisee.FranchiseeReport;
import com.gongsibao.entity.report.dic.ReportDateType;
import com.gongsibao.entity.report.dic.ReportOrganizationType;
import com.gongsibao.franchisee.base.IFranchiseeReportService;

public abstract class AbstractReportHandler {

	protected IPersister<FranchiseeReport> pm = PersisterFactory.create();

	protected IFranchiseeReportService reportService = ServiceFactory.create(IFranchiseeReportService.class);
	
	protected ReportContext context;

	/**
	 * @Title: getReportType
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @return
	 * @return: FranchiseeReportType
	 * @throws
	 */
	protected abstract ReportDateType getReportDateType();

	protected abstract ReportOrganizationType getReportOrganizationType();
	
	/**
	 * @Title: getNextHandler
	 * @Description: TODO(获取下一个处理者)
	 * @param: @return
	 * @return: AbstractReportHandler
	 * @throws
	 */
	protected abstract AbstractReportHandler getNextHandler();

	/**
	 * @Title: execute
	 * @Description: TODO(执行)
	 * @param:
	 * @return: void
	 * @throws
	 */
	public void execute() {

		this.before();

		this.delete();

		this.doExecute();

		AbstractReportHandler handler = this.getNextHandler();
		if (handler != null) {
			handler.setContext(context);
			handler.execute();
		}
	}

	/**
	 * @Title: before
	 * @Description: TODO(补全)
	 * @param:
	 * @return: void
	 * @throws
	 */
	public void before(){
		
	}

	public abstract void doExecute();

	/**
	 * @Title: before
	 * @Description: TODO(补全)
	 * @param:
	 * @return: void
	 * @throws
	 */
	public void delete() {

		List<String> filterList = new ArrayList<String>();
		filterList.add("date_type=?");
		filterList.add("organization_type=?");
		filterList.add("year=?");
		QueryParameters qps = new QueryParameters();
		{
			qps.add("dateType", this.getReportDateType().getValue(), Types.INTEGER);
			qps.add("organizationType", this.getReportOrganizationType().getValue(), Types.INTEGER);
			qps.add("year", this.getContext().getYear(), Types.INTEGER);
		}

		if (this.getReportDateType() == ReportDateType.DAY) {

			filterList.add("month=?");
			filterList.add("day=?");
			
			qps.add("month", this.getContext().getMonth(), Types.INTEGER);
			qps.add("day", this.context.getDay(), Types.DATE);
			
		} else if (this.getReportDateType() == ReportDateType.WEEK) {

			filterList.add("week=?");
			qps.add("week", this.getContext().getWeek(), Types.INTEGER);
			
		} else if (this.getReportDateType() == ReportDateType.MONTH) {

			filterList.add("month=?");
			qps.add("month", this.getContext().getMonth(), Types.INTEGER);
			
		} else if (this.getReportDateType() == ReportDateType.SEASON) {
			
			filterList.add("season=?");
			qps.add("season", this.getContext().getSeason(), Types.INTEGER);
			
		} else if (this.getReportDateType() == ReportDateType.YEAR) {

		}

		String whereSql = StringManager.join(" and ",filterList);
		DeleteBuilder deleteBuilder = DeleteBuilder.getInstance();
		{
			deleteBuilder.deleteFrom(MtableManager.getMtable(FranchiseeReport.class).getTableName());
			deleteBuilder.where(whereSql);
		}
		
		String cmdText = deleteBuilder.toSQL();
		this.pm.executeNonQuery(cmdText, qps);
	}

	/**
	 * @Title: getChildDepartmentIds
	 * @Description: TODO(获取一个部门下的所有子部门Id)
	 * @param: @param parentPathCode
	 * @param: @return
	 * @return: List<Integer>
	 * @throws
	 */
	protected List<Integer> getChildDepartmentIdList(String parentPathCode) {

		SelectBuilder selectBuilder = SelectBuilder.getInstance();
		{
			selectBuilder.select("id");
			selectBuilder.from("sys_permission_organization");
			selectBuilder.where("path_code LIKE '" + parentPathCode + "%'", "organization_type = 3 ");
			selectBuilder.orderBy("path_code");
		}
		String cmdText = selectBuilder.toSQL();
		DataTable dataTable = this.pm.executeTable(cmdText, null);
		List<Integer> idList = new ArrayList<Integer>();
		for (IRow row : dataTable) {

			Integer id = row.getInteger("id");
			idList.add(id);
		}
		return idList;
	}

	/**
	 * @Title: getOrganizationEmployeeList
	 * @Description: TODO(获取一个部门下的所有组员对应与岗位对应关系)
	 * @param: @param parentPathCode
	 * @param: @return
	 * @return: List<OrganizationEmployee> 只有organizationId，employeeId
	 * @throws
	 */
	protected List<OrganizationEmployee> getOrganizationEmployeeList(String parentPathCode) {

		SelectBuilder selectBuilder = SelectBuilder.getInstance();
		{
			selectBuilder.select("organization_id", "employee_id");
			selectBuilder.from("sys_permission_organization_employee");
			selectBuilder.where("organization_id in (SELECT id FROM sys_permission_organization WHERE path_code LIKE '" + parentPathCode + "%' AND position_id = 4)");
		}
		String cmdText = selectBuilder.toSQL();
		DataTable dataTable = this.pm.executeTable(cmdText, null);
		List<OrganizationEmployee> oeList = new ArrayList<OrganizationEmployee>();
		OrganizationEmployee oe = null;
		for (IRow row : dataTable) {

			Integer organizationId = row.getInteger("organization_id");
			Integer employeeId = row.getInteger("employee_id");
			oe = new OrganizationEmployee();
			{
				oe.setOrganizationId(organizationId);
				oe.setEmployeeId(employeeId);
			}
			oeList.add(oe);
		}
		return oeList;
	}

	/**
	 * @Title: getOrganizationList
	 * @Description: TODO(获取一个部门下的所有组员岗位 )
	 * @param: @param parentPathCode
	 * @param: @return
	 * @return: List<Organization>只有id,parentId值
	 * @throws
	 */
	protected List<Organization> getOrganizationList(String parentPathCode) {

		SelectBuilder selectBuilder = SelectBuilder.getInstance();
		{
			selectBuilder.select("id", "parent_id");
			selectBuilder.from("sys_permission_organization");
			selectBuilder.where("path_code LIKE '" + parentPathCode + "%'", "organization_type = 6", "AND position_id = 4");
			selectBuilder.orderBy("path_code");
		}
		String cmdText = selectBuilder.toSQL();
		DataTable dataTable = this.pm.executeTable(cmdText, null);
		List<Organization> oList = new ArrayList<Organization>();
		Organization o = null;
		for (IRow row : dataTable) {

			Integer id = row.getInteger("id");
			Integer parentId = row.getInteger("parent_id");
			o = new Organization();
			{
				o.setId(id);
				o.setParentId(parentId);
			}
			oList.add(o);
		}
		return oList;
	}

	/**
	 * @Title: getEmployeeMap
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param: @param parentPathCode
	 * @param: @return
	 * @return: DataTable
	 * @throws
	 */
	protected DataTable getEmployeeMap(String parentPathCode) {

		StringBuilder builder = new StringBuilder();
		{
			builder.append("SELECT");
			builder.append("	o.id AS postId,");
			builder.append("	o.parent_id AS departmentId,");
			builder.append("	e.employee_id AS employeeId");
			builder.append(" FROM");
			builder.append("	sys_permission_organization_employee e");
			builder.append(" LEFT JOIN (");
			builder.append("	SELECT");
			builder.append("		id,");
			builder.append("		parent_id");
			builder.append("	FROM");
			builder.append("		sys_permission_organization o");
			builder.append("	WHERE");
			builder.append("		path_code LIKE '" + parentPathCode + "%'");
			builder.append("	AND organization_type = 6");
			builder.append("	AND position_id = 4");
			builder.append(") o ON o.id = e.organization_id");
			builder.append(" WHERE");
			builder.append("	organization_id IN (");
			builder.append("		SELECT");
			builder.append("			id");
			builder.append("		FROM");
			builder.append("			sys_permission_organization");
			builder.append("	WHERE");
			builder.append("		path_code LIKE '" + parentPathCode + "%'");
			builder.append("	AND position_id = 4");
			builder.append(")");
		}

		String cmdText = builder.toString();
		DataTable dataTable = this.pm.executeTable(cmdText, null);
		return dataTable;
	}

	public ReportContext getContext() {
		return context;
	}

	public void setContext(ReportContext context) {
		this.context = context;
	}
	
	/**   
	 * @Title: completionEntity   
	 * @Description: TODO(补全)   
	 * @param: @param entity
	 * @param: @param dRow      
	 * @return: void      
	 * @throws   
	 */
	protected void completionEntity(FranchiseeReport entity,IRow dRow){
		
		Integer totalCount = Integer.parseInt(dRow.getString("totalCount"));
		Integer trackCount = Integer.parseInt(dRow.getString("trackCount"));
		Integer unTrackCount = Integer.parseInt(dRow.getString("unTrackCount"));
		Integer expectedSign1Count = Integer.parseInt(dRow.getString("expectedSign1Count"));
		Integer expectedSign2Count = Integer.parseInt(dRow.getString("expectedSign2Count"));
		Integer expectedSign3Count = Integer.parseInt(dRow.getString("expectedSign3Count"));
		Integer expectedSign4Count = Integer.parseInt(dRow.getString("expectedSign4Count"));
		Integer expectedSign5Count = Integer.parseInt(dRow.getString("expectedSign5Count"));
		
		Integer intentionDegree1Count = Integer.parseInt(dRow.getString("intentionDegree1Count"));
		Integer intentionDegree2Count = Integer.parseInt(dRow.getString("intentionDegree2Count"));
		Integer intentionDegree3Count = Integer.parseInt(dRow.getString("intentionDegree3Count"));
		
		Integer trackProgress1Count = Integer.parseInt(dRow.getString("trackProgress1Count"));
		Integer trackProgress2Count = Integer.parseInt(dRow.getString("trackProgress2Count"));
		Integer trackProgress3Count = Integer.parseInt(dRow.getString("trackProgress3Count"));
		Integer trackProgress4Count = Integer.parseInt(dRow.getString("trackProgress4Count"));
		Integer trackProgress5Count = Integer.parseInt(dRow.getString("trackProgress5Count"));
		Integer trackProgress6Count = Integer.parseInt(dRow.getString("trackProgress6Count"));
		Integer trackProgress7Count = Integer.parseInt(dRow.getString("trackProgress7Count"));
		
		entity.setTotalCount(totalCount);
		entity.setTrackCount(trackCount);
		entity.setUnTrackCount(unTrackCount);
		entity.setExpectedSign1Count(expectedSign1Count);
		entity.setExpectedSign2Count(expectedSign2Count);
		entity.setExpectedSign3Count(expectedSign3Count);
		entity.setExpectedSign4Count(expectedSign4Count);
		entity.setExpectedSign5Count(expectedSign5Count);
		
		entity.setIntentionDegree1Count(intentionDegree1Count);
		entity.setIntentionDegree2Count(intentionDegree2Count);
		entity.setIntentionDegree3Count(intentionDegree3Count);
		
		entity.setTrackProgress1Count(trackProgress1Count);
		entity.setTrackProgress2Count(trackProgress2Count);
		entity.setTrackProgress3Count(trackProgress3Count);
		entity.setTrackProgress4Count(trackProgress4Count);
		entity.setTrackProgress5Count(trackProgress5Count);
		entity.setTrackProgress6Count(trackProgress6Count);
		entity.setTrackProgress7Count(trackProgress7Count);
	}
}
