package com.gongsibao.part;

import java.util.List;

import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.TreegridPart;
import org.netsharp.panda.commerce.TreegridSerializer;

public class CustomTreegridPart extends TreegridPart{

	@Override
	protected Object serialize(List<?> table, Oql oql) {

		TreegridSerializer jsonSerializor = new TreegridSerializer();
		Object json = jsonSerializor.serialize(table, this.context.getDatagrid());
		return json;
	}
}
