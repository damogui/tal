package com.gongsibao.franchisee.service.report.salesman;

import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.entity.Organization;
import org.netsharp.util.sqlbuilder.SelectBuilder;

import com.gongsibao.entity.franchisee.FranchiseeReport;
import com.gongsibao.entity.report.dic.ReportDateType;
import com.gongsibao.entity.report.dic.ReportOrganizationType;
import com.gongsibao.franchisee.service.report.AbstractReportHandler;

public class SalesmanDayHandler extends AbstractReportHandler{

	@Override
	protected ReportDateType getReportDateType() {

		return ReportDateType.DAY;
	}
	
	@Override
	protected ReportOrganizationType getReportOrganizationType() {

		return ReportOrganizationType.SALESMAN;
	}

	@Override
	protected AbstractReportHandler getNextHandler() {
		
		return new SalesmanMonthHandler();
	}

	private DataTable getDateTable(){

		SelectBuilder selectBuilder = SelectBuilder.getInstance();
		{
			selectBuilder.select("owner_id as ownerId",
					"count(0) AS totalCount",
					"count(owner_id=last_tracker_id or NULL) as trackCount",
					"count((owner_id<>last_tracker_id or last_tracker_id is null) or NULL) as unTrackCount",
					"count(expected_sign=1 or NULL) as expectedSign1Count",
					"count(expected_sign=2 or NULL) as expectedSign2Count",
					"count(expected_sign=3 or NULL) as expectedSign3Count",
					"count(expected_sign=4 or NULL) as expectedSign4Count",
					"count(expected_sign=5 or NULL) as expectedSign5Count",
					"count(intention_degree=1 or NULL) as intentionDegree1Count",
					"count(intention_degree=2 or NULL) as intentionDegree2Count",
					"count(intention_degree=3 or NULL) as intentionDegree3Count",
					"count(track_progress=1 or NULL) as trackProgress1Count",
					"count(track_progress=2 or NULL) as trackProgress2Count",
					"count(track_progress=3 or NULL) as trackProgress3Count",
					"count(track_progress=4 or NULL) as trackProgress4Count",
					"count(track_progress=5 or NULL) as trackProgress5Count",
					"count(track_progress=6 or NULL) as trackProgress6Count",
					"count(track_progress=7 or NULL) as trackProgress7Count");
			selectBuilder.from("bd_franchisee");
			selectBuilder.where("owner_id is not null");
			selectBuilder.groupBy("owner_id");
		}
		String cmdText = selectBuilder.toSQL();
		DataTable dataTable = this.pm.executeTable(cmdText, null);
		return dataTable;
	}

	@Override
	public void doExecute() {

		IOrganizationService organizaService = ServiceFactory.create(IOrganizationService.class);
		List<Organization> getOrganList = organizaService.getByFunction("Channel");
		Organization department = getOrganList.get(0);
		
		DataTable employeeMaps =  getEmployeeMap(department.getPathCode());
		DataTable dataTable = this.getDateTable();
		
		for(IRow eRow:employeeMaps){
			
			Integer departmentId = eRow.getInteger("departmentId");
			Integer employeeId = eRow.getInteger("employeeId");
			FranchiseeReport entity = new FranchiseeReport();{
				entity.toNew();
				entity.setOwnerId(employeeId);
				entity.setDepartmentId(departmentId);
				entity.setDateType(ReportDateType.DAY);
				entity.setOrganizationType(ReportOrganizationType.SALESMAN);
				entity.setWeek(this.context.getWeek());
				entity.setMonth(this.context.getMonth());
				entity.setYear(this.context.getYear());
				entity.setDay(this.context.getDay());
				entity.setDate(this.context.getDate());
			}
			
			for(IRow dRow:dataTable){
				
				Integer ownerId = dRow.getInteger("ownerId");
				if(ownerId.equals(employeeId)){
					
					completionEntity(entity,dRow);
				}
			}
			
			pm.save(entity);
		}
	}
}
