package com.gongsibao.crm.web;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.entity.bd.Dict;

public class SalesmanProductDetailPart extends TaskProductDetailPart{


	public List<Dict> queryProductFirstCategory(Integer departmentId) {

		//1.范围必须在上级部门已经有的
		//2.如果是顶级部门，则取服务商的
		
		IDictService service = ServiceFactory.create(IDictService.class);
		Oql oql = new Oql();
		{
			oql.setType(Dict.class);
			oql.setSelects("*");
			oql.setFilter("type=201 and parentId=0 and enabled=1 and id in (select product_category_id_1 from sp_department_product where department_id=?)");
			oql.getParameters().add("departmentId", departmentId, Types.INTEGER);
		}
		
		List<Dict> list = service.queryList(oql);
		return list;
	}
	
	public List<Dict> queryProductSecondCategory(Integer departmentId,Integer parentId) {

		//1.范围必须在上级部门已经有的
		//2.如果是顶级部门，则取服务商的
		
		IDictService service = ServiceFactory.create(IDictService.class);
		Oql oql = new Oql();
		{
			oql.setType(Dict.class);
			oql.setSelects("*");
			oql.setFilter("parentId=? and type=201 and enabled=1 and id in (select product_category_id_2 from Depart_service_product where department_id=?)");
			oql.getParameters().add("parentId", parentId, Types.INTEGER);
			oql.getParameters().add("departmentId", departmentId, Types.INTEGER);
		}
		
		List<Dict> list = service.queryList(oql);
		return list;
	}
	
	public List<Dict> queryProvince(Integer departmentId){
		
		//1.范围必须在上级部门已经有的
		//2.如果是顶级部门，则取服务商的
		
		IDictService service = ServiceFactory.create(IDictService.class);
		Oql oql = new Oql();
		{
			oql.setType(Dict.class);
			oql.setSelects("*");
			oql.setFilter("type=101 and parentId=0 and enabled=1 and id in (select province_id from sp_department_product where department_id=?)");
			oql.getParameters().add("departmentId", departmentId, Types.INTEGER);
		}
		
		List<Dict> list = service.queryList(oql);
		return list;
	}
	
	
	public List<Dict> queryCity(Integer departmentId,Integer provinceId){
		
		//1.范围必须在上级部门已经有的
		//2.如果是顶级部门，则取服务商的
		
		IDictService service = ServiceFactory.create(IDictService.class);
		Oql oql = new Oql();
		{
			oql.setType(Dict.class);
			oql.setSelects("*");
			oql.setFilter("type=101 and enabled=1 and id in (select city_id from sp_department_product where department_id=?)");
			oql.getParameters().add("departmentId", departmentId, Types.INTEGER);
		}
		
		List<Dict> list = service.queryList(oql);
		return list;
	}
	
	
	public List<Dict> queryCounty(Integer departmentId,Integer cityId){
		
		//1.范围必须在上级部门已经有的
		//2.如果是顶级部门，则取服务商的
		
		IDictService service = ServiceFactory.create(IDictService.class);
		Oql oql = new Oql();
		{
			oql.setType(Dict.class);
			oql.setSelects("*");
			oql.setFilter("type=101 and enabled=1 and id in (select county_id from sp_department_product where department_id=?)");
			oql.getParameters().add("departmentId", departmentId, Types.INTEGER);
		}
		
		
		List<Dict> list = service.queryList(oql);
		return list;
	}
}
