package com.gongsibao.panda.ma.workspace.acquisition;

import org.junit.Before;
import org.netsharp.core.MtableManager;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.entity.ma.AcquisitionDemand;
import com.gongsibao.ma.web.AcquisitionDemandFormPart;

public class AcquisitionDemandMyWorkspaceTest extends AcquisitionDemandWorkspaceTest{

	@Override
	@Before
	public void setup() {

		urlList = "/ma/acquisition/my/list";
		urlForm = "/ma/acquisition/my/form";
		entity = AcquisitionDemand.class;
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName =  "我的收购需求";
		listFilter = "creatorId='{userId}'";
		
		resourceNodeCode = "My"+AcquisitionDemand.class.getSimpleName();
		formServiceController = AcquisitionDemandFormPart.class.getName();
		formJsController = AcquisitionDemandFormPart.class.getName();
		formJsImport = "/gsb/ma/js/acquisitionDemand.form.part.js";
	}
	
	public void doOperation() {

		ResourceNode node = resourceService.byCode(resourceNodeCode);
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
		operationService.addOperation(node, OperationTypes.update);
		operationService.addOperation(node, OperationTypes.delete);
	}
}
