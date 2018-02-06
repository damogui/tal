package com.gongsibao.crm.web;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.entity.bd.Dict;

/**   
 * @ClassName:  DepartmentProductDetailPart   
 * @Description:TODO 需要优化
 * @author: 韩伟
 * @date:   2018年1月31日 下午2:39:57   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class DepartmentProductDetailPart extends TaskProductDetailPart{

	public List<Dict> queryProductFirstCategory(Integer departmentId,Integer supplierId) {

		//1.范围必须在上级部门已经有的
		//2.如果是顶级部门，则取服务商的
		
		IDictService service = ServiceFactory.create(IDictService.class);
		Oql oql = new Oql();
		{
			oql.setType(Dict.class);
			oql.setSelects("*");
		}
		
		if(departmentId == null||departmentId.equals(0)){

			oql.setFilter("type=201 and parentId=0 and enabled=1 and id in (select product_category_id_1 from sp_supplier_product where id=?)");
			oql.getParameters().add("supplierId", supplierId, Types.INTEGER);
		}else{

			oql.setFilter("type=201 and parentId=0 and enabled=1 and id in (select product_category_id_1 from sp_department_product where department_id=?)");
			oql.getParameters().add("departmentId", departmentId, Types.INTEGER);
		}
		
		List<Dict> list = service.queryList(oql);
		return list;
	}
	
	public List<Dict> queryProductSecondCategory(Integer departmentId,Integer supplierId,Integer parentId) {

		//1.范围必须在上级部门已经有的
		//2.如果是顶级部门，则取服务商的
		
		IDictService service = ServiceFactory.create(IDictService.class);
		Oql oql = new Oql();
		{
			oql.setType(Dict.class);
			oql.setSelects("*");
		}
		
		if(departmentId == null||departmentId.equals(0)){

			oql.setFilter("parentId=? and type=201 and enabled=1 and id in (select product_category_id_2 from sp_supplier_product where id=?)");
			oql.getParameters().add("parentId", parentId, Types.INTEGER);
			oql.getParameters().add("supplierId", supplierId, Types.INTEGER);
		}else{

			oql.setFilter("parentId=? and type=201 and enabled=1 and id in (select product_category_id_2 from sp_department_product where department_id=?)");
			oql.getParameters().add("parentId", parentId, Types.INTEGER);
			oql.getParameters().add("departmentId", departmentId, Types.INTEGER);
		}
		
		List<Dict> list = service.queryList(oql);
		return list;
	}
	
	public List<Dict> queryProvince(Integer departmentId,Integer supplierId){
		
		//1.范围必须在上级部门已经有的
		//2.如果是顶级部门，则取服务商的
		
		IDictService service = ServiceFactory.create(IDictService.class);
		Oql oql = new Oql();
		{
			oql.setType(Dict.class);
			oql.setSelects("*");
		}
		
		if(departmentId == null||departmentId.equals(0)){

			oql.setFilter("type=101 and parentId=0 and enabled=1 and id in (select province_id from sp_supplier_product where id=?)");
			oql.getParameters().add("supplierId", supplierId, Types.INTEGER);
		}else{

			oql.setFilter("type=101 and parentId=0 and enabled=1 and id in (select province_id from sp_department_product where department_id=?)");
			oql.getParameters().add("departmentId", departmentId, Types.INTEGER);
		}
		
		List<Dict> list = service.queryList(oql);
		return list;
	}
	
	
	public List<Dict> queryCity(Integer departmentId,Integer supplierId,Integer provinceId){
		
		//1.范围必须在上级部门已经有的
		//2.如果是顶级部门，则取服务商的
		
		IDictService service = ServiceFactory.create(IDictService.class);
		Oql oql = new Oql();
		{
			oql.setType(Dict.class);
			oql.getParameters().add("parentId", provinceId, Types.INTEGER);
			oql.setSelects("*");
		}
		
		if(departmentId == null||departmentId.equals(0)){

			oql.setFilter("type=101 and enabled=1 and parentId=? and id in (select city_id from sp_supplier_product where id=?)");
			oql.getParameters().add("supplierId", supplierId, Types.INTEGER);
		}else{

			oql.setFilter("type=101 and enabled=1 and parentId=? and id in (select city_id from sp_department_product where department_id=?)");
			oql.getParameters().add("departmentId", departmentId, Types.INTEGER);
		}
		
		List<Dict> list = service.queryList(oql);
		return list;
	}
	
	
	public List<Dict> queryCounty(Integer departmentId,Integer supplierId,Integer cityId){
		
		//1.范围必须在上级部门已经有的
		//2.如果是顶级部门，则取服务商的
		
		IDictService service = ServiceFactory.create(IDictService.class);
		Oql oql = new Oql();
		{
			oql.setType(Dict.class);
			oql.setSelects("*");
			oql.getParameters().add("parentId", cityId, Types.INTEGER);
		}
		
		if(departmentId == null||departmentId.equals(0)){

			oql.setFilter("type=101 and enabled=1 and parentId=? and id in (select county_id from sp_supplier_product where id=?)");
			oql.getParameters().add("supplierId", supplierId, Types.INTEGER);
		}else{

			oql.setFilter("type=101 and enabled=1 and parentId=? and id in (select county_id from sp_department_product where department_id=?)");
			oql.getParameters().add("departmentId", departmentId, Types.INTEGER);
		}
		
		List<Dict> list = service.queryList(oql);
		return list;
	}
}
