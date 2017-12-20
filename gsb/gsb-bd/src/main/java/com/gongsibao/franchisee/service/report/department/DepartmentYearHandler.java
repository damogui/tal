package com.gongsibao.franchisee.service.report.department;

import java.sql.Types;

import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.MtableManager;
import org.netsharp.core.QueryParameters;
import org.netsharp.util.sqlbuilder.SelectBuilder;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.franchisee.FranchiseeReport;
import com.gongsibao.entity.report.dic.ReportDateType;
import com.gongsibao.entity.report.dic.ReportOrganizationType;
import com.gongsibao.franchisee.service.report.AbstractReportHandler;

public class DepartmentYearHandler extends AbstractReportHandler{

	@Override
	protected ReportDateType getReportDateType() {

		return ReportDateType.YEAR;
	}
	
	@Override
	protected ReportOrganizationType getReportOrganizationType() {

		return ReportOrganizationType.DEPARTMENT;
	}

	@Override
	protected AbstractReportHandler getNextHandler() {

		return null;
	}

	@Override
	public void doExecute() {
		
		SelectBuilder selectBuilder = SelectBuilder.getInstance();
		{
			selectBuilder.select(
			"department_id as departmentId",
			"total_count as totalCount",
			"track_count as trackCount",
			"un_track_count as unTrackCount",
			"expected_sign_1_count as expectedSign1Count",
			"expected_sign_2_count as expectedSign2Count",
			"expected_sign_3_count as expectedSign3Count",
			"expected_sign_4_count as expectedSign4Count",
			"expected_sign_5_count as expectedSign5Count",
			"intIntention_degree1_count as intentionDegree1Count",
			"intIntention_degree2_count as intentionDegree2Count",
			"intIntention_degree3_count as intentionDegree3Count",
			"track_progress1_count as trackProgress1Count",
			"track_progress2_count as trackProgress2Count",
			"track_progress3_count as trackProgress3Count",
			"track_progress4_count as trackProgress4Count",
			"track_progress5_count as trackProgress5Count",
			"track_progress6_count as trackProgress6Count",
			"track_progress7_count as trackProgress7Count");
			selectBuilder.from(MtableManager.getMtable(FranchiseeReport.class).getTableName());
			selectBuilder.where("year=?", "month=?", "date_type=?","organization_type=?");
			selectBuilder.groupBy("department_id");
		}

		QueryParameters qps = new QueryParameters();{

			qps.add("year", this.context.getYear(), Types.INTEGER);
			qps.add("month", this.context.getMonth(), Types.INTEGER);
			qps.add("dateType", ReportDateType.MONTH.getValue(), Types.INTEGER);
			qps.add("organizationType", ReportOrganizationType.DEPARTMENT.getValue(), Types.INTEGER);
		}
		DataTable dataTable = this.pm.executeTable(selectBuilder.toSQL(), qps);
		for (IRow row : dataTable) {

			Integer departmentId = row.getInteger("departmentId");
			Integer ownerId = row.getInteger("ownerId");
			FranchiseeReport entity = new FranchiseeReport();{
				entity.toNew();
				entity.setOwnerId(ownerId);
				entity.setDepartmentId(departmentId);
				entity.setDateType(ReportDateType.YEAR);
				entity.setOrganizationType(ReportOrganizationType.DEPARTMENT);
				entity.setYear(this.context.getYear());
				entity.setMonth(this.context.getMonth());
			}
			completionEntity(entity,row);
			entity = reportService.save(entity);
			updateParentId(entity);
		}
	}
	
	public void updateParentId(FranchiseeReport entity) {
		
		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(FranchiseeReport.class).getTableName());
			updateBuilder.set("parent_id", entity.getId());
			updateBuilder.where("year=?", "date_type=?","organization_type=?","department_id=?");
		}

		QueryParameters qps = new QueryParameters();
		qps.add("year", this.context.getYear(), Types.INTEGER);
		qps.add("dateType", ReportDateType.YEAR.getValue(), Types.INTEGER);
		qps.add("organizationType", ReportOrganizationType.SALESMAN.getValue(), Types.INTEGER);
		qps.add("departmentId", entity.getDepartmentId(), Types.INTEGER);
		this.pm.executeNonQuery(updateBuilder.toSQL(), qps);
	}
}
