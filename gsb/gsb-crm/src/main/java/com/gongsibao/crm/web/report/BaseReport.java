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
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.supplier.base.ISupplierDepartmentService;
import com.gongsibao.utils.SupplierSessionManager;

public class BaseReport extends TreegridPart {

	ISupplierDepartmentService departService = ServiceFactory.create(ISupplierDepartmentService.class);
	ISalesmanService salesManSerice = ServiceFactory.create(ISalesmanService.class);
	
	HashMap<String, String> map;
	//统计级别 1-服务商；
	Integer statistLevel;	
	//是否执行点击行的节点
	Boolean isPerform = false;
	@Override
	public Object query() throws IOException {
		List<BaseReportEntity> rows = new ArrayList<BaseReportEntity>();
		
		//从工作区获取Filter值，区分平台和服务商 ，使用完重新设置为空
		if(!this.context.getDatagrid().getFilter().isEmpty()){
			statistLevel = Integer.parseInt(this.context.getDatagrid().getFilter());
			this.context.getDatagrid().setFilter("");
		}
		List<String> ss = new ArrayList<String>();
		this.map = getMapFilters();
		
		if (this.map.size() > 0) {
			//过滤业务员，其他级别不需要再显示
			if(!StringManager.isNullOrEmpty(map.get("ownerId"))){
				Integer salesManIdInteger = Integer.parseInt(map.get("ownerId").replace("'", ""));
				Salesman salesman=  salesManSerice.byEmployeeId(salesManIdInteger);
				//业务员不为空，并且查询的业务员部门和当前登录属于同部门
				if(salesman !=null && salesman.getDepartmentId().equals(SupplierSessionManager.getDepartmentId())){
					BaseReportEntity entity1 = new BaseReportEntity();
					{
						entity1.setId(salesman.getEmployeeId());
						entity1.setParentId(salesman.getDepartmentId());
						entity1.setSupplierId(salesman.getSupplierId());
						entity1.setDepartmentName(salesman.getName());
						entity1.setIsLeaf(true);
					}
				    BaseReportEntity baseEntity1 = getDataTable(entity1,map,null,salesman.getEmployeeId(),true);
					rows.add(baseEntity1);
				}
				Object json = this.serialize(rows, null);
				return json;
			}
			String departmentId = map.get("departmentId");
			if(statistLevel == null){
				String supplierId = map.get("supplierId");			
				//过滤部门
				if (!StringManager.isNullOrEmpty(departmentId)) {
					ss.add("id=" + departmentId);
				}
				//过滤服务商
				if (!StringManager.isNullOrEmpty(supplierId)) {
					ss.add("supplierId=" + supplierId);
				}else {
					//服务商为空，获取当前登录人所在的服务商
					ss.add("supplierId=" + SupplierSessionManager.getSupplierId());
				}
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
			//本身的id+下属id
		    String tempDepartIds = o.getId().toString() + "," + StringManager.join(",", departIds);
			
		    BaseReportEntity baseEntity = getDataTable(entity,map,tempDepartIds,null,false);
			rows.add(baseEntity);
			//添加员工的各项统计
			if(statistLevel != null && statistLevel.equals(1) && isPerform){
				isPerform = false;
				List<Salesman> manList =  salesManSerice.getByDepartmentId(Integer.parseInt(this.getRequest("id")));
				for (Salesman salesman : manList) {
					BaseReportEntity entity1 = new BaseReportEntity();
					{
						entity1.setId(salesman.getEmployeeId());
						entity1.setParentId(salesman.getDepartmentId());
						entity1.setSupplierId(salesman.getSupplierId());
						entity1.setDepartmentName(salesman.getName());
						entity1.setIsLeaf(true);
					}
				    BaseReportEntity baseEntity1 = getDataTable(entity1,map,tempDepartIds,salesman.getEmployeeId(),true);
					rows.add(baseEntity1);
				}
			}
		}
		
		Object json = this.serialize(rows, oql);
		return json;
	}
	
	/**
	 * 获取统计数据
	 * @param entity 层级字段
	 * @param filterMap 过滤集合
	 * @param orgaId 部门Ids
	 * @param salesmanId 业务员id
	 * @param isSalesman true-过滤业务员数据、false-过滤部门数据
	 * @return
	 */
	protected BaseReportEntity getDataTable(BaseReportEntity entity, HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
		
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
		if(statistLevel != null && statistLevel.equals(1)){
			if (StringManager.isNullOrEmpty(id)) {
				return " id =" + SupplierSessionManager.getDepartmentId();
			}else if (!StringManager.isNullOrEmpty(id)) {
				isPerform = true;
				return " parent_id=" + id;
			}
		}else{
			if (StringManager.isNullOrEmpty(id) && StringManager.isNullOrEmpty(map.get("departmentId"))) {
				return " (parent_id = 0 or parent_id is NULL)";
			}else if (!StringManager.isNullOrEmpty(id) && StringManager.isNullOrEmpty(map.get("departmentId"))) {
				return " parent_id=" + id;
			}
		}
		return "";
	}
}
