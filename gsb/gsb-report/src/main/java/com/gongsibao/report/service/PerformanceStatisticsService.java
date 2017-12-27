package com.gongsibao.report.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.MtableManager;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.entity.report.PerformanceStatistics;
import com.gongsibao.entity.uc.UserOrganizationMap;
import com.gongsibao.report.base.IPerformanceStatisticsService;
import com.gongsibao.report.service.perfrmance.PerfrmanceContext;
import com.gongsibao.report.service.perfrmance.salesman.PerfrmanceSalesmanDayService;
import com.gongsibao.uc.base.IUserOrganizationMapService;

@Service
public class PerformanceStatisticsService extends PersistableService<PerformanceStatistics> implements IPerformanceStatisticsService {

	public PerformanceStatisticsService() {
		super();
		this.type = PerformanceStatistics.class;
	}

	@Override
	public Boolean generate(Date date,int[] departmentIds) {

		List<UserOrganizationMap> allMapList =  new ArrayList<UserOrganizationMap>();
		IUserOrganizationMapService mapService = ServiceFactory.create(IUserOrganizationMapService.class);
		
		for (int i = 0; i < departmentIds.length; i++) {

			int departmentId = departmentIds[i];
			List<UserOrganizationMap> mapList = mapService.getMapList(departmentId);
			allMapList.addAll(mapList);
		}

		PerfrmanceContext context  = new PerfrmanceContext();{
			context.setDate(date);
			context.setMapList(allMapList);
			context.setDepartmentIds(departmentIds);
		}
		PerfrmanceSalesmanDayService generateService = new PerfrmanceSalesmanDayService();
		generateService.setContext(context);
		generateService.execute();
		return true;
	}
	
	public Boolean updateParentId(Integer parentId, List<Integer> idList) {

		String ids = StringManager.join(",", idList);
		UpdateBuilder updateBuilder = new UpdateBuilder();
		{
			updateBuilder.update(MtableManager.getMtable(PerformanceStatistics.class).getTableName());
			updateBuilder.set("parent_id", parentId);
			updateBuilder.where("id in(" + ids + ")");
		}
		return this.pm.executeNonQuery(updateBuilder.toSQL(), null) > 0;
	}
}