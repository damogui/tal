package org.netsharp.panda.commerce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.convertor.ITypeConvertor;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.util.CategoryUtil;

public class TreegridSerializer {

	private HashMap<String, ITypeConvertor> properties = new HashMap<String, ITypeConvertor>();
	private List<IPersistable> list;
	private CategoryUtil catUtil = new CategoryUtil();

	@SuppressWarnings("unchecked")
	public Object serialize(List<?> list,PDatagrid grid) {
		if (list == null) {
			return null;
		}
		
		this.list = (List<IPersistable>)list;
		
		String[] selects = grid.getSelectedFields().split(",");
		Mtable meta = MtableManager.getMtable(grid.getEntityId());

		for (String property : selects) {
			ITypeConvertor tc = meta.findProperty(property).getConvertor();
			properties.put(property.replace(".", "_"), tc);
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Object> rows = new ArrayList<Object>();

		map.put("total", list.size());
		map.put("rows", rows);

		
		List<IPersistable> tree = catUtil.listToTree(this.list);

		for (IPersistable obj : tree) {

			HashMap<String, Object> j = serialize(obj);

			rows.add(j);
		}

		return map;
	}

	private HashMap<String, Object> serialize(IPersistable obj) {
		
		HashMap<String, Object> j = new HashMap<String, Object>();
		
		for(String key : this.properties.keySet()){
			
			Object value = obj.get(key.replace("_", "."));

			j.put(key, value);
		}
		
		List<IPersistable> subs = catUtil.getChildren(obj, this.list);
		
		if(subs.size()==0){
			return j;
		}
		
		List<Object> jsubs = new ArrayList<Object>();

		j.put("children", jsubs);
		
		for (IPersistable sub : subs) {
			HashMap<String, Object> jsub = serialize(sub);

			jsubs.add(jsub);
		}

		j.put("state", "closed");

		return j;
	}
}
