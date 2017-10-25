package org.netsharp.panda.rest;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.base.IPersistableService;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.base.IPReferenceService;
import org.netsharp.panda.core.HttpContext;
import org.netsharp.panda.entity.PReference;
import org.netsharp.panda.json.TreeResultJson;
import org.netsharp.util.JsonManage;
import org.netsharp.util.StringManager;

public class RestComboxTreeService {
	
	private HttpContext context;
	private PReference pr;

	public void processRequest(HttpContext context) throws IOException {

		this.context = context;

		String id = context.getRequest().getParameter("id2");
		
		//id="9";
		IPReferenceService referenceService = ServiceFactory.create(IPReferenceService.class);
		pr = referenceService.byId(id);

		// ui条件
		String filter = context.getRequest().getParameter("filter");
		List<String> ss = new ArrayList<String>();
		if (!StringManager.isNullOrEmpty(filter) && !StringManager.equals("null", filter,true)) {
			filter = URLDecoder.decode(filter, "UTF-8");
			filter=filter.replaceAll("----","'");
			filter=filter.replaceAll("____","=");//QueryControlFactory.referenceBox
			ss.add(filter);
		}

		// 参照配置条件
		if (!StringManager.isNullOrEmpty(pr.getFilter())) {
			ss.add(pr.getFilter());
		}

		Oql oql = new Oql();
		{
			oql.setEntityId(pr.getEntityId());
			oql.setSelects("id,"+pr.getIntelligentFields());
			
			if(ss.size()>0){
				oql.setFilter(StringManager.join(" AND ", ss));
			}
		}
		
		//父节ID
		String parentId = context.getRequest().getParameter("parentId");
		
		//处理异步加载
		if(!StringManager.isNullOrEmpty(parentId) && !StringManager.equals("null", parentId,true)){
			
			//当前oql的过滤条件
			filter=oql.getFilter();
			oql.setFilter(filter+" AND parentId="+parentId);
		}else{
			if(!StringManager.isNullOrEmpty(filter) ){
				oql.setFilter(filter+" AND parentId =0");
			}else{
				filter=oql.getFilter();
				oql.setFilter(filter+" AND parentId =0");
			}	
		}

		// 排序
		if (context.getRequest().getParameter("order") != null) {
			String orderby = context.getRequest().getParameter("order") + " " + context.getRequest().getParameter("sort").toUpperCase();
			oql.setOrderby(orderby);
		}

		IPersistableService<?> service = ServiceFactory.create(pr.getService());

		List<?> list = service.queryList(oql);
		this.render(list, oql);
	}
	
	private void render(List<?> list, Oql oql) throws IOException {

		TreeResultJson result = new TreeResultJson(list,oql.getEntityId());
		Object obj = result.parse();
		String json = JsonManage.serialize2(obj);
		context.getResponse().write(json);
	}
}
