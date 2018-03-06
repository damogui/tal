package com.gongsibao.crm.web.home;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.supplier.base.ISupplierDepartmentService;

public class PortalStatistic {
	static ISupplierDepartmentService departService = ServiceFactory.create(ISupplierDepartmentService.class);	
	/**
	 * 获取当前登录人实体
	 * @return
	 */
	public Salesman CurrentSalesMan(){
		ISalesmanService manSerice = ServiceFactory.create(ISalesmanService.class);
		Integer currentLogin = SessionManager.getUserId();
		Salesman salesman = manSerice.byEmployeeId(currentLogin);
		return salesman;
	}
	/**
	 * 获取新增任务数
	 * @return
	 */
	public Integer getNewTasksCount() {
		Integer returnInteger = 0;
		Salesman salesman = CurrentSalesMan();
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) newTasksCount");
		strSql.append(" from n_crm_customer_task");
		
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
	public Integer getUnStartTasksCount() {
		Integer returnInteger = 0;
		Salesman salesman = CurrentSalesMan();
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT count(id) unStartTasksCount");
		strSql.append(" from n_crm_customer_task");
		
		if(salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getId());
		}
		strSql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') <= CURDATE()");
		strSql.append(" AND foolow_status = 6");
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			returnInteger = Integer.valueOf(row.getString("unStartTasksCount"));
		}
		return returnInteger;
	}
	/**
	 * 获取待跟进任务数
	 * @return
	 */
	public Integer getUnfoolowTasksCount() {
		Integer returnInteger = 0;
		Salesman salesman = CurrentSalesMan();
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) unfoolowTasksCount");
		strSql.append(" from n_crm_customer_task");
		
		if(salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getId());
		}
		strSql.append(" AND next_foolow_time = CURDATE()");
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			returnInteger = Integer.valueOf(row.getString("unfoolowTasksCount"));
		}
		return returnInteger;
	}
	/**
	 * 获取超时任务数
	 * @return
	 */
	public Integer getTimeOutTasksCount() {
		Integer returnInteger = 0;
		Salesman salesman = CurrentSalesMan();
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) timeOutTasksCount");
		strSql.append(" from n_crm_customer_task");
		
		if(salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getId());
		}
		strSql.append(" AND foolow_status = 3");
		strSql.append(" AND NOW()>next_foolow_time");
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			returnInteger = Integer.valueOf(row.getString("timeOutTasksCount"));
		}
		return returnInteger;
	}
	/**
	 * 获取异常未处理任务数----------????????????
	 * @return
	 */
	public static Integer getExceptUntreatedTasksCount() {
		Integer returnInteger = 1111;
		return returnInteger;
	}
	/**
	 * 获取公海任务数
	 * @return
	 */
	public Integer getHighSeasCount() {
		Integer returnInteger = 0;
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) highSeasCount");
		strSql.append(" from n_crm_customer_task");
		strSql.append(" WHERE (owner_id is null or owner_id=0)");
		strSql.append(" and DATE_FORMAT(create_time,'%Y-%m-%d') <= CURDATE()");
		
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			returnInteger = Integer.valueOf(row.getString("highSeasCount"));
		}
		return returnInteger;
	}
	/**
	 * 获取跟进统计（跟进任务数、质量上升、下降任务数）
	 * @return
	 */
	public Map<String, String> getFoolowSatatistic() {
		Map<String, String> resultMap =new HashMap<>();
		Salesman salesman = CurrentSalesMan();
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(foolow_status = 3 OR NULL) foolowTasksCount,");
		strSql.append("count(quality_progress = 1 OR NULL) qualityRisetaskCount,");
		strSql.append("count(intention_category = 2 OR NULL) qualityDeclinetaskCount");
		
		strSql.append(" from n_crm_customer_task");
		if(salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getId());
		}
		strSql.append(" AND DATE_FORMAT(next_foolow_time,'%Y-%m-%d') = CURDATE()");
		
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("跟进任务数", row.getString("foolowTasksCount"));
			resultMap.put("质量上升任务数", row.getString("qualityRisetaskCount"));
			resultMap.put("质量下降任务数", row.getString("qualityDeclinetaskCount"));
		}
		return resultMap;
	}
	/**
	 * 获取预估签单金额、预估回款金额
	 * @param type 1-今日、2-本周、3-本月
	 * @return
	 */
	public Map<String, String> getForecastAmount(Integer type) {
		Map<String, String> resultMap =new HashMap<>();
		Salesman salesman = CurrentSalesMan();
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT ifnull(SUM(signing_amount),0) signingAmount,");
		strSql.append("ifnull(SUM(returned_amount),0) returnedAmount");
		
		strSql.append(" from n_crm_task_foolow");
		strSql.append(" where task_id in (");
		strSql.append("SELECT id from n_crm_customer_task");
		if(salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getId());
		}
		if(type.equals(1)){
			strSql.append(" and date_format(last_follow_time,'%Y-%m-%d') = CURDATE())");
		}else if (type.equals(2)) {
			strSql.append(" and YEARWEEK(date_format(last_follow_time,'%Y-%m-%d')) = YEARWEEK(now()))");
		}else {
			strSql.append(" and date_format(last_follow_time,'%Y-%m') = date_format(now(),'%Y-%m'))");
		}
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("预估签单金额", row.getString("signingAmount"));
			resultMap.put("预估回款金额", row.getString("returnedAmount"));
		}
		return resultMap;
	}

	/**
	 * 获取全部任务数、X类、S类任务数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	public Map<String, String> getXSCount() {
		Map<String, String> resultMap =new HashMap<>();
		Salesman salesman = CurrentSalesMan();
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) taskCount,");
		strSql.append("count(intention_category = 6 OR NULL) sCount,");
		strSql.append("count(intention_category = 5 OR NULL) xCount");
		strSql.append(" from n_crm_customer_task");
		
		if(salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getId());
		}
		
		strSql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') <= CURDATE()");
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("全部任务", row.getString("taskCount"));
			resultMap.put("S类", row.getString("sCount"));
			resultMap.put("X类", row.getString("xCount"));
		}
		return resultMap;
	}
	/**
	 * 获取任务质量数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	public Map<String, String> getCodeTaskCount() {
		Map<String, String> resultMap =new HashMap<>();
		Salesman salesman = CurrentSalesMan();
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(q.`code` = 'A0' OR NULL) A0,");
		strSql.append("COUNT(q.`code` = 'A1' OR NULL) A1,");
		strSql.append("COUNT(q.`code` = 'A2' OR NULL) A2,");
		strSql.append("COUNT(q.`code` = 'A3' OR NULL) A3,");
		strSql.append("COUNT(q.`code` = 'A4' OR NULL) A4,");
		strSql.append("COUNT(q.`code` = 'B1' OR NULL) B1,");
		strSql.append("COUNT(q.`code` = 'B2' OR NULL) B2,");
		strSql.append("COUNT(q.`code` = 'C1' OR NULL) C1,");
		strSql.append("COUNT(q.`code` = 'C2' OR NULL) C2,");
		strSql.append("COUNT(q.`code` = 'C3' OR NULL) C3,");
		strSql.append("COUNT(q.`code` = 'C4' OR NULL) C4,");
		strSql.append("COUNT(q.`code` = 'D1' OR NULL) D1,");
		strSql.append("COUNT(q.`code` = 'D2' OR NULL) D2");
		strSql.append(" from n_crm_task_foolow f");
		strSql.append(" LEFT JOIN n_crm_task_quality q");
		strSql.append(" on f.quality_id = q.id");
		strSql.append(" where f.task_id in (");
		strSql.append("SELECT id from n_crm_customer_task");
		if(salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getId());
		}
		
		strSql.append(" and intention_category in (1,2,3,4)");
		strSql.append(" and DATE_FORMAT(create_time,'%Y-%m-%d') <= CURDATE())");
		
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("A0", row.getString("A0"));
			resultMap.put("A1", row.getString("A1"));
			resultMap.put("A2", row.getString("A2"));
			resultMap.put("A3", row.getString("A3"));
			resultMap.put("A4", row.getString("A4"));
			resultMap.put("B1", row.getString("B1"));
			resultMap.put("B2", row.getString("B2"));
			resultMap.put("C1", row.getString("C1"));
			resultMap.put("C2", row.getString("C2"));
			resultMap.put("C3", row.getString("C3"));
			resultMap.put("C4", row.getString("C4"));
			resultMap.put("D1", row.getString("D1"));
			resultMap.put("D2", row.getString("D2"));
		}
		return resultMap;
	}
}
