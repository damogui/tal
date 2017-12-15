package com.gongsibao.report.service.perfrmance.department;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.MtableManager;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.SelectBuilder;

import com.gongsibao.entity.report.dic.ReportOrganizationType;
import com.gongsibao.entity.uc.Organization;
import com.gongsibao.report.service.perfrmance.AbstractPerfrmanceService;
import com.gongsibao.uc.base.IOrganizationService;

public abstract class AbstractPerfrmanceDepartmentService extends AbstractPerfrmanceService {

	protected IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
	
	protected ReportOrganizationType getReportOrganizationType(){
		
		return ReportOrganizationType.DEPARTMENT;
	}
	
	/**
	 * @Title: getParentDepartmentIdList
	 * @Description: TODO(根据子部门Id集合获取所有上级部门Id集合1)
	 * @param: @param ChildDepartmentIdList
	 * @param: @return
	 * @return: List<Integer>
	 * @throws
	 */
	protected List<Integer> getParentDepartmentIdList(List<Integer> childDepartmentIdList) {

		String childIds = StringManager.join(",", childDepartmentIdList);
		SelectBuilder builder = SelectBuilder.getInstance();
		{
			builder.select("pid");
			builder.from(MtableManager.getMtable(Organization.class).getTableName());
			builder.where("pid is not null and pid <>0 and pkid in (" + childIds + ")");
			builder.groupBy("pid");
		}

		List<Integer> parentIdList = new ArrayList<Integer>();
		DataTable dataTable = this.pm.executeTable(builder.toSQL(), null);
		for (IRow row : dataTable) {

			Integer pid = row.getInteger("pid");
			parentIdList.add(pid);
		}
		return parentIdList;
	}
	
	/**   
	 * @Title: getChildDepartmentIdList   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param departmentId
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws   
	 */
	protected List<Integer> getChildDepartmentIdList(Integer departmentId) {
		
		return organizationService.getChildDepartmentIdList(departmentId);
	}
}
