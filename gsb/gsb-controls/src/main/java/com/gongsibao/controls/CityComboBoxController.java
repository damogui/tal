package com.gongsibao.controls;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.Oql;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.bd.Dict;

public class CityComboBoxController {


	public List<Dict> queryPcc(Integer parentId) {

		Oql oql = new Oql();
		{
			oql.setType(Dict.class);
			oql.setSelects("id,name");
		}
		
		List<String> ss = new ArrayList<String>();
		ss.add("type='101'");
		if(parentId != null){
			ss.add("pid=?");
			oql.getParameters().add("parentId", parentId, Types.INTEGER);
		}else{
			
			ss.add("(pid is null or pid = 0)");
		}

		String filter = StringManager.join(" and ",ss);
		oql.setFilter(filter);
		 IPersister<Dict> pm = PersisterFactory.create();
		return pm.queryList(oql);
	}
}
