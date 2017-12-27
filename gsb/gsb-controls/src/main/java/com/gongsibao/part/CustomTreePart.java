package com.gongsibao.part;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.TreePart;
import org.netsharp.panda.controls.tree.TreeNode;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import com.gongsibao.json.CustomTreeResultJson;

public class CustomTreePart extends TreePart{
	
	public List<TreeNode> query() throws UnsupportedEncodingException{
		
		
		String entityId = this.context.getEntityId();
		Mtable meta = MtableManager.getMtable(entityId);

		Oql oql = new Oql();
		{
			oql.setEntityId(meta.getEntityId());
			oql.setSelects("*");
			oql.setOrderby(meta.getOrderby());
		}

		String filter = getRequest("filter");
		if (!StringManager.isNullOrEmpty(filter)) {
			filter = URLDecoder.decode(filter, "UTF-8");
			filter = filter.replace('|', '=');
		}

		ArrayList<String> filters = new ArrayList<String>();
		if (!StringManager.isNullOrEmpty(filter)) {
			filters.add(filter);
		}

		String defaultFilter = this.getDefaultFilter();
		if (!StringManager.isNullOrEmpty(defaultFilter)) {
			filters.add(defaultFilter);
		}
		
		String id = this.getRequest("id");
		if (StringManager.isNullOrEmpty(id)) {
			filters.add("pid=0 or pid is null");
		} else {
			filters.add("pid=?");
			oql.getParameters().add("@pid", id, Types.INTEGER);
		}

		oql.setFilter(StringManager.join(" and ", filters));

		Class<?> serviceType = ReflectManager.getType(this.context.getService());
		IPersistableService<?> service = (IPersistableService<?>) ServiceFactory.create(serviceType);
		List<?> rows = service.queryList(oql);

		List<TreeNode> nodes = this.serialize(rows);
				
		return nodes;
	}

	protected List<TreeNode> serialize(List<?> rows){
		
		String entityId = this.context.getEntityId();
		CustomTreeResultJson json = new CustomTreeResultJson(rows, entityId);
		List<TreeNode> nodes = json.parse();
		return nodes;
	}
}
