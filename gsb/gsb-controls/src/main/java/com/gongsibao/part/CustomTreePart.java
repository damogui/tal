package com.gongsibao.part;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.TreePart;
import org.netsharp.panda.controls.tree.TreeNode;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.uc.Organization;
import com.gongsibao.json.CustomTreeResultJson;

public class CustomTreePart extends TreePart {

	public List<TreeNode> query() throws UnsupportedEncodingException {

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

		String defaultFilter = this.getExtraFilter();
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

	protected List<TreeNode> serialize(List<?> rows) {

		String entityId = this.context.getEntityId();
		CustomTreeResultJson json = new CustomTreeResultJson(rows, entityId);
		List<TreeNode> nodes = json.parse();
		return nodes;
	}

	IPersister<Organization> pm = PersisterFactory.create();

	public void pathCode() {

		String getParentId = "SELECT pkid from uc_organization where pid=0";
		DataTable getDt = pm.executeTable(getParentId.toString(), null);
		for (IRow row : getDt) {
			
			Integer getPkid = Integer.parseInt(row.getString("pkid"));
			setOrfanization(getPkid);
		}
	}

	private void setOrfanization(Integer pid) {
		
		String getParentId = "SELECT pkid from uc_organization where pid=" + pid;
		DataTable getDt = pm.executeTable(getParentId.toString(), null);
		if (getDt.size() > 0) {
			
			for (IRow row : getDt) {
				
				String updateSql = "UPDATE uc_organization set is_leaf=0 where pkid=" + pid;
				pm.executeNonQuery(updateSql.toString(), null);
				setOrfanization(Integer.parseInt(row.getString("pkid")));
			}
		} else {
			
			String updateSql = "UPDATE uc_organization set is_leaf=1 where pkid=" + pid;
			pm.executeNonQuery(updateSql.toString(), null);
		}
	}
}
