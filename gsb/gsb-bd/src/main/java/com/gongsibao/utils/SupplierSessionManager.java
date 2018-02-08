package com.gongsibao.utils;

import java.sql.Types;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierDepartment;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.supplier.base.ISupplierDepartmentService;
import com.gongsibao.supplier.base.ISupplierService;

/**
 * @ClassName: EmployeeSupplierSessionManager
 * @Description:TODO 
 *                   处理与服务商相关（后面处理成将supplierId和departmentId放到Session中，不要每次都查询数据库）
 * @author: 韩伟
 * @date: 2018年1月17日 下午12:12:44
 * 
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved.
 */
public class SupplierSessionManager {

	/**
	 * @Title: getSupplierId
	 * @Description: TODO(获取当前登录人所在服务商Id)
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	public static Integer getSupplierId() {

		Integer employeeId = SessionManager.getUserId();
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		Integer supplierId = salesmanService.getSupplierId(employeeId);
		return supplierId;
	}

	/**   
	 * @Title: getSupplier   
	 * @Description: TODO(获取服务商)   
	 * @param: @return      
	 * @return: Supplier      
	 * @throws   
	 */
	public static Supplier getSupplier() {

		Integer supplierId = SupplierSessionManager.getSupplierId();
		ISupplierService supplierService = ServiceFactory.create(ISupplierService.class);
		Supplier supplier = supplierService.byId(supplierId);
		return supplier;
	}

	/**
	 * @Title: getDepartmentId
	 * @Description: TODO(获取当前登录人所在服务商下部门Id)
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	public static Integer getDepartmentId() {

		Integer employeeId = SessionManager.getUserId();
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		Integer departmentId = salesmanService.getDepartmentId(employeeId);
		return departmentId;
	}
	
	public static SupplierDepartment getDepartment() {

		ISupplierDepartmentService departmentService = ServiceFactory.create(ISupplierDepartmentService.class);
		Integer departmentId = SupplierSessionManager.getDepartmentId();
		SupplierDepartment department =departmentService.byId(departmentId);
		return department;
	}

	public static List<Integer> getDepartmentIdList() {

		Integer employeeId = SessionManager.getUserId();
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		List<Integer> list = salesmanService.getDepartmentIdList(employeeId);
		return list;
	}

	/**
	 * 获取当前登录人所在部门的子部门
	 * 
	 * @return
	 */
	public static String getSubDepartmentIdsStr() {

		List<Integer> list = SupplierSessionManager.getDepartmentIdList();
		String ids = StringManager.join(",", list);
		return ids;
	}

	/**
	 * @Title: getSalesmanEmployeeId
	 * @Description: TODO(获取业务员对应的EmployeeId,如果当前登录人是业务员，则返回EmployeeID,否则返回null)
	 * @param: @return
	 * @return: Integer
	 * @throws
	 */
	public static Integer getSalesmanEmployeeId() {

		Integer employeeId = SessionManager.getUserId();
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
		Boolean isSalesman = salesmanService.hasEmployeeId(employeeId);
		if (isSalesman) {

			return employeeId;
		}
		return null;
	}
	
	/**   
	 * @Title: getSalesmanOrganization   
	 * @Description: TODO(根据当前登录人员Id，获取业务员对应的组织机构上下文)   
	 * @param: @param employeeId
	 * @param: @return      
	 * @return: SalesmanOrganization      
	 * @throws   
	 */
	public static SalesmanOrganization getSalesmanOrganization(Integer employeeId){
		
		SalesmanOrganization so = new SalesmanOrganization();
		ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
        Oql oql = new Oql();
        {
            oql.setType(Salesman.class);
            oql.setSelects("salesman.{id,name,loginName},salesman.employee.{id,name,loginName},salesman.department.{id,name},salesman.supplier.{id,admin_id,name}");
            oql.setFilter("employeeId=?");
            oql.getParameters().add("@employeeId", employeeId, Types.INTEGER);
        }
        Salesman salesman = salesmanService.queryFirst(oql);
        
        if(salesman != null){
        	
        	so.setSalessmanId(salesman.getId());
        	so.setSalessmanName(salesman.getName());
        	so.setSalessmanLoginName(salesman.getLoginName());
        	
        	so.setEmployeeId(salesman.getEmployeeId());
        	so.setEmployeeName(salesman.getEmployee().getName());
        	so.setEmployeeLoginName(salesman.getEmployee().getLoginName());
        	
        	so.setDepartmentId(salesman.getDepartmentId());
        	so.setDepartmentName(salesman.getDepartment().getName());
        	
        	so.setSupplierId(salesman.getSupplierId());
        	so.setAdminId(salesman.getSupplier().getAdminId());
        	so.setSupplierName(salesman.getSupplier().getName());
        	
        	//直属领导
        	Salesman directLeader = salesmanService.getDirectLeader(salesman.getId());
        	if(directLeader != null){
        		
        		so.setDirectLeaderId(directLeader.getEmployeeId());
        		so.setDirectLeaderName(directLeader.getName());
        		so.setDirectLoginName(directLeader.getLoginName());
        	}
        	
        	//隔级领导
        	Salesman superiorLeader = salesmanService.getSuperiorLeader(salesman.getId());
        	if(superiorLeader != null){
        		
        		so.setSuperiorLeaderId(superiorLeader.getEmployeeId());
        		so.setSuperiorLeaderName(superiorLeader.getName());
        		so.setSuperiorLoginName(superiorLeader.getLoginName());
        	}
        }
		return so;
	}
}
