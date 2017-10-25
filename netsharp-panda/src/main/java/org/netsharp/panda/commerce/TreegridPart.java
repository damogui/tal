package org.netsharp.panda.commerce;

import java.util.List;

import org.netsharp.core.Oql;
import org.netsharp.panda.controls.treegrid.TreeGrid;
import org.netsharp.panda.controls.utility.UrlHelper;
import org.netsharp.panda.core.comunication.IHtmlWriter;

public class TreegridPart extends ListPart {

	@Override
	protected void onRendering() {
		
		datagrid = new TreeGrid();
		{
			datagrid.pagination=false;
		}
	}
	
	@Override
	protected Object serialize(List<?> table, Oql oql) {

		TreegridSerializer jsonSerializor = new TreegridSerializer();
		Object json = jsonSerializor.serialize(table, this.context.getDatagrid());
		return json;
	}
	
	@Override
	protected void importJs(IHtmlWriter writer) {

		super.importJs(writer);
		writer.write(UrlHelper.getVersionScript("/panda-res/js/panda.tree.list.js"));
	}
}
