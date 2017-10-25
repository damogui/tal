package org.netsharp.organization.filter;

import java.util.List;

import org.netsharp.base.IReferenceFilterBuilder;
import org.netsharp.persistence.session.SessionManager;

/**当前组织下的岗位参照动态过滤 */
public class PositionReferenceFilter implements IReferenceFilterBuilder {

	@Override
	public String builderFilter() {
		
		StringBuilder sb=new StringBuilder();
		List<String> pathCodes= SessionManager.getDepartmentPathCodes();
		sb.append("EXISTS (SELECT 1 FROM sys_permission_organization org WHERE org.positionId=Position.id AND (org.path_code like '").append(pathCodes.get(0)).append("%' ");
		for(int i=1,len=pathCodes.size();i<len;i++){
			sb.append("OR org.path_code like '").append(pathCodes.get(i)).append("%' ");
		}
		sb.append("))");
		return sb.toString();
	}

}
