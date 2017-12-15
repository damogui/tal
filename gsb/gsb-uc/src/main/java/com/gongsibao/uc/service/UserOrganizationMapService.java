package com.gongsibao.uc.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.uc.UserOrganizationMap;
import com.gongsibao.uc.base.IOrganizationService;
import com.gongsibao.uc.base.IUserOrganizationMapService;

@Service
public class UserOrganizationMapService extends PersistableService<UserOrganizationMap> implements IUserOrganizationMapService {

    public UserOrganizationMapService(){
        super();
        this.type=UserOrganizationMap.class;
    }

    IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
    
	@Override
	public List<UserOrganizationMap> getMapList(Integer departmentId) {

		List<UserOrganizationMap> mapList = new ArrayList<UserOrganizationMap>();

		Boolean isHasChild = organizationService.hasChildDepartment(departmentId);
		if (isHasChild) {

			List<Integer> childDepartmentIdList = organizationService.getChildDepartmentIdList(departmentId);
			for (Integer childDepartmentId : childDepartmentIdList) {

				//递归获取UserId
				List<UserOrganizationMap> childUserList = getMapList(childDepartmentId);
				mapList.addAll(childUserList);
			}

		} else {

			Oql oql = new Oql();
			{
				oql.setType(UserOrganizationMap.class);
				oql.setSelects("*");
				oql.setFilter("organization_id=? and user.enabled=1");
				
				QueryParameters qps = new QueryParameters();
				qps.add("departmentId", departmentId, Types.INTEGER);
				oql.setParameters(qps);
				
			}
			mapList = this.queryList(oql);
		}
		return mapList;
	}
}