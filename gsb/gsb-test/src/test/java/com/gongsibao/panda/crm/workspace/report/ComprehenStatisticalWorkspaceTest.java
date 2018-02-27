package com.gongsibao.panda.crm.workspace.report;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.crm.report.ComprehenReportEntity;


/**
 * 综合统计
 * @author Administrator
 *
 */
public class ComprehenStatisticalWorkspaceTest extends WorkspaceCreationBase{

	@Override
	@Before
	public void setup() {
		entity = ComprehenReportEntity.class;
		urlList = "/crm/statistical/comprehen/list";
		listPartName = formPartName = "综合统计";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "CRM_STATISTICAL_Comprehen";
		
	}

	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
	}
}
