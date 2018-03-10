package com.gongsibao.panda.platform.operation.workspace.crm.form;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.organization.dic.OperationTypes;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.web.NCustomerFollowFormPart;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;
import com.gongsibao.entity.crm.NCustomerTaskQuality;

public class TaskFollowFormWorkspaceTest extends WorkspaceCreationBase {

	@Override
	@Before
	public void setup() {
		entity = NCustomerTaskFoolow.class;
		// 配置表单路径
		urlForm = "/crm/platform/task/follow";
		listPartName = formPartName = "跟进";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = "Operation_CRM_Foolow_Edit";

		formJsImport = "/gsb/platform/operation/crm/js/follow-form.part.js|/gsb/panda-extend/gsb.customer.controls.js";
		formJsController = NCustomerFollowFormPart.class.getName();
		formServiceController = NCustomerFollowFormPart.class.getName();
	}

	@Test
	public void run() {
		createFormWorkspace();
	}

	// 创建表单。须配置urlForm路径
	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(1);
		}
		PFormField formField = null;
		String groupName = null;

		formField = addFormFieldRefrence(form, "quality.name", "任务质量", groupName, NCustomerTaskQuality.class.getSimpleName(), true, false);
		{
			formField.setTroikaTrigger("controllernCustomerTaskFoolow.qualityChange(newValue, oldValue);");
		}
		addFormField(form, "nextFoolowTime", "下次跟进时间", groupName, ControlTypes.DATE_BOX, false, true);
		addFormField(form, "signingAmount", "估计签单金额", groupName, ControlTypes.DECIMAL_FEN_BOX, false, true);
		addFormField(form, "returnedAmount", "估计回款金额", groupName, ControlTypes.DECIMAL_FEN_BOX, false, true);
		formField = addFormField(form, "content", "跟进内容", groupName, ControlTypes.TEXTAREA, true, false);
		{
			formField.setFullColumn(false);
			formField.setHeight(150);
			formField.setWidth(350);
		}
		return form;
	}

	@Override
	protected void doOperation() {

		ResourceNode node = this.getResourceNode();
		operationService.addOperation(node, OperationTypes.view);
		operationService.addOperation(node, OperationTypes.add);
	}
}
