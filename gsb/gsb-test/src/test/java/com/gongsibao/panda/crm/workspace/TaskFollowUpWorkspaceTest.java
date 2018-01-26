package com.gongsibao.panda.crm.workspace;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.crm.web.NCustomerTaskEditFormPart;
import com.gongsibao.entity.crm.NCustomerTaskFoolow;


/**
 * js中引用任务跟进表单的弹框
 * @author Administrator
 *
 */
public class TaskFollowUpWorkspaceTest extends WorkspaceCreationBase{
	@Override
	@Before
	public void setup() {
		entity = NCustomerTaskFoolow.class;
		//配置表单路径
		urlForm = "/crm/my/task/followUp/from";
		listPartName = formPartName = "沟通日志";
		meta = MtableManager.getMtable(entity);
		formPartName = listPartName = meta.getName();
		resourceNodeCode = NCustomerTaskFoolow.class.getSimpleName();
	}
	@Test
	public void run() {
		createFormWorkspace();
	}
	
	//创建表单。须配置urlForm路径
	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm();
		{
			form.toNew();
			form.setResourceNode(node);
			form.setName(this.meta.getName() + "表单");
			form.setColumnCount(2);
		}
		PFormField formField = null;
		String groupName = null;	
		formField = addFormField(form, "foolowStatus", "跟进状态", groupName, ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "nextFoolowTime", "下次跟进时间", groupName, ControlTypes.DATE_BOX, false, false);
		addFormField(form, "qualityCategory", "质量分类", groupName, ControlTypes.ENUM_BOX, false, false);
		addFormField(form, "estimateAmount", "估计签单金额", groupName, ControlTypes.DECIMAL_FEN_BOX, false, false);
		formField = addFormField(form, "content", "跟进内容", groupName, ControlTypes.TEXTAREA, false, false);{			
			formField.setFullColumn(true);
	    }
		return form;
	}
}

