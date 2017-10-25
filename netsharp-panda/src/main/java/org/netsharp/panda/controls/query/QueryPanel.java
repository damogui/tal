package org.netsharp.panda.controls.query;

import java.util.ArrayList;

import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.form.Form;
import org.netsharp.panda.controls.other.Linkbutton;
import org.netsharp.panda.controls.table.TD;
import org.netsharp.panda.controls.table.TR;
import org.netsharp.panda.controls.table.Table;
import org.netsharp.panda.dic.QueryProjectType;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.panda.entity.PQueryProject;

public class QueryPanel extends Form {
	private PQueryProject queryProject;
	private String datagridId;// datagrid part js对象的变量名称
	protected PDatagrid pdatagrid;

	@Override
	public void initialize() {
		this.setId("queryFrom");
		String entityId = this.queryProject.getEntityId();
		Mtable meta = MtableManager.getMtable(entityId);
		Table table = new Table();
		{

			table.setClassName("query-panel");
			table.setCellPadding(3);
		}

		ArrayList<PQueryItem> items = new ArrayList<PQueryItem>();
		for (PQueryItem x : getQueryProject().getQueryItems()) {
			if (x.isVisible()) {
				items.add(x);
			}
		}

		// double d = (double)items.size() /
		// (double)getQueryProject().getColumnCount();
		double d = (double) items.size() / (double) getQueryProject().getColumnCount();
		int rowsCount = (int) Math.ceil(d);
		int index = 0;
		for (int i = 0; i < rowsCount; i++) {
			TR tr = new TR();
			table.getControls().add(tr);

			for (int c = 0; c < getQueryProject().getColumnCount(); c++) {
				if (index >= items.size()) {
					tr.getControls().add(new TD());
					tr.getControls().add(new TD());
				} else {
					TD header = new TD();
					{
						header.setClassName("title");
					}
					;
					header.value = items.get(index).getHeader();
					tr.getControls().add(header);

					TD control = new TD();

					PQueryItem queryItem = items.get(index);
					IPropertyQueryControl propertyQueryControl = PropertyQueryControlFactory.create(queryItem);
					Control queryControl = propertyQueryControl.create(queryItem, meta);
					control.getControls().add(queryControl);
					tr.getControls().add(control);
				}

				index++;
			}
		}

		this.createQueryButton(table);
		this.controls.add(table);
		super.initialize();
	}

	private void createQueryButton(Table table) {

		if (table.getControls().size() > 0) {

			TR tr = (TR) table.getControls().get(table.getControls().size() - 1);
			ArrayList<Control> allTdList = tr.getControls();
			ArrayList<Control> emptyTdList = new ArrayList<Control>();
			for (Control td : allTdList) {

				if (td.getClassName() != "title" && td.getControls().size() == 0) {
					emptyTdList.add(td);
				}
			}

			Control emptyTd = null;
			if (emptyTdList.size() > 1) {

				emptyTd = emptyTdList.get(1);
			} else {

				emptyTd = new TD();
				tr.getControls().add(emptyTd);
			}

			if (queryProject.getType() == QueryProjectType.SIMPLE) {

				Linkbutton button = new Linkbutton();
				{
					button.setId("btn_query");
					button.setClassName("easyui-linkbutton btn primary");
					button.value = "查 询";
					button.iconCls = "fa fa-search";
					button.href = "javascript:" + this.datagridId + ".query();";
				}
				emptyTd.getControls().add(button);
				
				button = new Linkbutton();
				{
					button.setId("btn_reset");
					button.setClassName("easyui-linkbutton btn");
					button.value = "重 置";
					//button.iconCls = "fa fa-reply";
					button.href = "javascript:" + this.datagridId + ".resetQuery();";
				}
				emptyTd.getControls().add(button);
			}

			if (this.pdatagrid != null && this.pdatagrid.getAdvancedQueryProjectId() != null) {

				Linkbutton button = new Linkbutton();
				{
					button.setId("btn_advanced");
					button.iconCls = "fa fa-search-plus";
					button.setClassName("easyui-linkbutton btn primary");
					button.value = "高 级";
					button.href = "javascript:" + this.datagridId + ".showAdvancedQuery('" + this.pdatagrid.getAdvancedQueryProjectId() + "', '" + datagridId + "');";
				}
				emptyTd.getControls().add(button);
			}
		}
	}

	public PQueryProject getQueryProject() {
		return queryProject;
	}

	public void setQueryProject(PQueryProject queryProject) {
		this.queryProject = queryProject;
	}

	public String getDatagridId() {
		return datagridId;
	}

	public void setDatagridId(String datagridId) {
		this.datagridId = datagridId;
	}

	public PDatagrid getPdatagrid() {
		return pdatagrid;
	}

	public void setPdatagrid(PDatagrid pdatagrid) {
		this.pdatagrid = pdatagrid;
	}

}
