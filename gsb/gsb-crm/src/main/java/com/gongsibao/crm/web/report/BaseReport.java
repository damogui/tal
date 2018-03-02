package com.gongsibao.crm.web.report;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.panda.commerce.TreegridPart;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.crm.report.BaseReportEntity;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.supplier.base.ISupplierDepartmentService;
import com.gongsibao.utils.SupplierSessionManager;

public class BaseReport extends TreegridPart {

	ISupplierDepartmentService departService = ServiceFactory.create(ISupplierDepartmentService.class);	
	HashMap<String, String> map;
	
	@Override
	public Object query() throws IOException {

		// departmentId='324' AND year ='2017'
		List<String> ss = new ArrayList<String>();
		this.map = getMapFilters();
		if (this.map.size() > 0) {
			String departmentId = map.get("departmentId");
			String supplierId = map.get("supplierId");
			
			//过滤部门
			if (!StringManager.isNullOrEmpty(departmentId)) {
				ss.add("id=" + departmentId);
			}
			//过滤服务商
			if (!StringManager.isNullOrEmpty(supplierId)) {
				ss.add("supplierId=" + supplierId);
			}else {
				ss.add("supplierId=" + SupplierSessionManager.getSupplierId());
			}
			
		}
		
		String extraFilter = this.getExtraFilter();
		if (!StringManager.isNullOrEmpty(extraFilter)) {
			ss.add(extraFilter);
		}
		String filter = StringManager.join(" and ", ss);
		
		Oql oql = new Oql();
		{
			oql.setSelects("id,parent_id,name,is_leaf");
			oql.setType(SupplierDepartment.class);
			oql.setFilter(filter);
		}
		
		List<BaseReportEntity> rows = new ArrayList<BaseReportEntity>();
		List<SupplierDepartment> list = departService.queryList(oql);
		for (SupplierDepartment o : list) {
			BaseReportEntity entity = new BaseReportEntity();
		    {
		    	entity.setId(o.getId());
				entity.setParentId(o.getParentId());
				entity.setSupplierId(o.getSupplierId());
				entity.setDepartmentName(o.getName());
				entity.setIsLeaf(o.getIsLeaf());
		    }
		   //递归组织机构Id以及下属Id
			List<Integer> departIds =  departService.getSubDepartmentIdList(o.getId());
		    String tempDepartIds = StringManager.join(",", departIds);
			//如果没有下属，获取本身值
		    if(tempDepartIds == null){
				tempDepartIds = o.getId().toString();
			}
		    
		    //FunnelReportEntity funnelEntity = getDataTable(entity,map,tempDepartIds);
		    BaseReportEntity baseEntity = getDataTable(entity,map,tempDepartIds);
			rows.add(baseEntity);
		}
		
		Object json = this.serialize(rows, oql);
		return json;
	}
	
	protected BaseReportEntity getDataTable(BaseReportEntity entity, HashMap<String, String> filterMap,String orgaId) {
		
		return null;
	}
	
	protected HashMap<String, String> getMapFilters() throws UnsupportedEncodingException {

		HashMap<String, String> map = new HashMap<String, String>();
		String filter = getRequest("filter");
		if (!StringManager.isNullOrEmpty(filter)) {

			filter = URLDecoder.decode(filter, "UTF-8");
			filter = filter.replace('|', '=');
			String[] ss = filter.split(" AND ");
			if (ss.length > 0) {

				for (String s : ss) {

					String[] a = s.split("=");
					map.put(a[0].trim(), a[1].trim());
				}
				return map;
			}
		}
		return map;
	}

	@Override
	protected String getExtraFilter() {
		String id = this.getRequest("id");
		if (StringManager.isNullOrEmpty(id) && StringManager.isNullOrEmpty(map.get("departmentId"))) {
			return " (parent_id = 0 or parent_id is NULL)";
		}else if (!StringManager.isNullOrEmpty(id) && StringManager.isNullOrEmpty(map.get("departmentId"))) {
			return " parent_id=" + id;
		}
		return "";
	}
}
