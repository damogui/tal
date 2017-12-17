package com.gongsibao.panda.franchisee.reference;

import org.junit.Before;
import org.netsharp.meta.base.ReferenceCreationBase;
import org.netsharp.organization.dic.OrganizationType;
import org.netsharp.organization.entity.Organization;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.IntelligentMode;
import org.netsharp.panda.dic.OrderbyMode;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.franchisee.web.BdOrganizationReferenceFilter;

public class BdOrganizationFilterReferenceTest  extends ReferenceCreationBase{


	@Before
	public void setup() {
		
		resourceNodeCode =  Organization.class.getSimpleName();
		datagridName = referenceName = "BD部门参照【自定义条件】";
		referenceCode = "BD-Organization-Department-Filter";
		intelligentMode = IntelligentMode.LIKE;
		intelligentFields = "pathName";
		gridFilter = "organizationType = "+OrganizationType.DEPARTMENT.getValue();
		filterBuilder = BdOrganizationReferenceFilter.class.getName();
	}

	public PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setOrderby("pathCode");
		addColumn( datagrid,"pathName", "名称", ControlTypes.TEXT_BOX,200,null,false);
		PDatagridColumn column = addColumn( datagrid,"pathCode", "编码", ControlTypes.TEXT_BOX,200,null,false);{
			column.setOrderbyMode(OrderbyMode.ASC);
			column.setVisible(false);
		}
		return datagrid;
	}
}
