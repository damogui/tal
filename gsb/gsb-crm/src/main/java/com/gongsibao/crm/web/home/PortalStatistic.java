package com.gongsibao.crm.web.home;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.entity.crm.home.BaseHomeSupplierEntity;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.supplier.base.ISupplierDepartmentService;

public class PortalStatistic {
	ISupplierDepartmentService departService = ServiceFactory.create(ISupplierDepartmentService.class);
	//ISalesmanService manSerice = ServiceFactory.create(ISalesmanService.class);
	
	/**
	 * 获取服务商的门户统计
	 * @return
	 */
	public static BaseHomeSupplierEntity getDate(){
		BaseHomeSupplierEntity homeEntity  = new BaseHomeSupplierEntity();
		
		return homeEntity;
	}
	/**
	 * 获取当前登录人实体
	 * @return
	 */
	public static Salesman CurrentSalesMan(){
		ISalesmanService manSerice = ServiceFactory.create(ISalesmanService.class);
		Integer currentLogin = SessionManager.getUserId();
		Salesman salesman = manSerice.byEmployeeId(currentLogin);
		return salesman;
	}
	/**
	 * 获取新增任务数
	 * @return
	 */
	private Integer getNewTasksCount() {
		Integer returnInteger = 0;
		Salesman salesman = CurrentSalesMan();
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) newTasksCount");
		strSql.append("from n_crm_customer_task");
		
		if(salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getId());
		}
		strSql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = CURDATE()");
		
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			returnInteger = Integer.valueOf(row.getString("newTasksCount"));
		}
		return returnInteger;
	}
	/**
	 * 获取未启动任务数
	 * @return
	 */
	private Integer getUnStartTasksCount() {
		Integer returnInteger = 0;
		Salesman salesman = CurrentSalesMan();
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) newTasksCount");
		strSql.append("from n_crm_customer_task");
		
		if(salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getId());
		}
		strSql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = CURDATE()");
		
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			returnInteger = Integer.valueOf(row.getString("newTasksCount"));
		}
		return returnInteger;
	}
}
