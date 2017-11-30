package com.gongsibao.controls;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.util.StringManager;

import com.gongsibao.bd.base.IDictService;
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
		IDictService service = ServiceFactory.create(IDictService.class);
		return service.queryList(oql);
	}
}
