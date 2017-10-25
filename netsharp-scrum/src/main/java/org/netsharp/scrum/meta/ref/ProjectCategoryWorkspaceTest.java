package org.netsharp.scrum.meta.ref;

import org.junit.Before;
import org.junit.Test;
import org.netsharp.core.MtableManager;
import org.netsharp.meta.base.WorkspaceCreationBase;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.dic.PartType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.panda.entity.PForm;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.scrum.entity.ProjectCategory;

public class ProjectCategoryWorkspaceTest extends WorkspaceCreationBase {
	@Override
	@Before
	public void setup() {
		// 在子类中重定义
		urlList = "/scrum/cat/list";
		urlForm = "/scrum/cat/form";
		entity = ProjectCategory.class;
		listPartName =formPartName= "项目分类";

		this.listPartType = PartType.TREEGRID_PART.getId();
	}

	@Override
	@Test
	public void run() {
		this.createListWorkspace();
		this.createFormWorkspace();
	}

	@Override
	protected PDatagrid createDatagrid(ResourceNode node) {

		PDatagrid datagrid = super.createDatagrid(node);
		datagrid.setPageSize(25);

		addColumn(datagrid, "code", "编码", ControlTypes.TEXT_BOX, 100, true);
		addColumn(datagrid, "name", "名称", ControlTypes.TEXT_BOX, 300, true);
		addColumn(datagrid, "createTime", "创建时间", ControlTypes.DATE_BOX, 150);
		addColumn(datagrid, "creator", "创建人", ControlTypes.TEXT_BOX, 150);

		PDatagridColumn column = new PDatagridColumn();
		{
			column.toNew();
			column.setPropertyName("parentId");
			column.setHeader("parentId");
			column.setControlType(ControlTypes.TEXT_BOX);

			column.setSystem(true);
			column.setVisible(false);
		}
		datagrid.getColumns().add(column);

		return datagrid;
	}

	@Override
	protected PForm createForm(ResourceNode node) {
		PForm form = new PForm(node, MtableManager.getMtable(entity).getName());
		form.setColumnCount(1);

		addFormField(form, "code", "编码", ControlTypes.TEXT_BOX, true, false);
		addFormField(form, "name", "名称", ControlTypes.TEXT_BOX, true, false);

		return form;
	}
}
