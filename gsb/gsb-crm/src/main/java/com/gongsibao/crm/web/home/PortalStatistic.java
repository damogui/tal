package com.gongsibao.crm.web.home;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.entity.crm.home.HomeSupplierEntity;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.supplier.base.ISupplierDepartmentService;

public class PortalStatistic {
	static ISupplierDepartmentService departService = ServiceFactory.create(ISupplierDepartmentService.class);
	//ISalesmanService manSerice = ServiceFactory.create(ISalesmanService.class);
	
	/**
	 * 获取服务商的门户的统计
	 * @return
	 */
	public static HomeSupplierEntity getStatisticDate(){
		HomeSupplierEntity resultEntity  = new HomeSupplierEntity();
		
		//部门简报
		resultEntity.setNewTasksCount(getNewTasksCount());
		resultEntity.setUnStartTasksCount(getUnStartTasksCount());
		resultEntity.setUnfoolowTasksCount(getUnfoolowTasksCount());
		resultEntity.setTimeOutTasksCount(getTimeOutTasksCount());
		resultEntity.setExceptUntreatedTasksCount(getExceptUntreatedTasksCount());
		resultEntity.setHighSeasCount(getHighSeasCount());
		//跟进统计（今日）
		Map<String, String> getFoolowCountMap = getFoolowSatatistic();
		resultEntity.setFoolowTasksCount(Integer.parseInt(getFoolowCountMap.get("foolowTasksCount")));
		resultEntity.setQualityDeclinetaskCount(Integer.parseInt(getFoolowCountMap.get("qualityDeclinetaskCount")));
		resultEntity.setQualityRisetaskCount(Integer.parseInt(getFoolowCountMap.get("qualityRisetaskCount")));
		//预估业绩
		Map<String, String> getDayAmountMap = getDayAmount();
		Map<String, String> getWeekAmountMap = getWeekAmount();
		Map<String, String> getMonthAmountMap = getMonthAmount();
		resultEntity.setDaySigningAmount(Integer.parseInt(getDayAmountMap.get("daySigningAmount")));
		resultEntity.setDayReturnedAmount(Integer.parseInt(getDayAmountMap.get("dayReturnedAmount")));
		resultEntity.setWeekSigningAmount(Integer.parseInt(getWeekAmountMap.get("weekSigningAmount")));
		resultEntity.setWeekReturnedAmount(Integer.parseInt(getWeekAmountMap.get("weekReturnedAmount")));
		resultEntity.setMonthSigningAmount(Integer.parseInt(getMonthAmountMap.get("monthSigningAmount")));
		resultEntity.setMonthReturnedAmount(Integer.parseInt(getMonthAmountMap.get("monthReturnedAmount")));
		//漏斗统计
		Map<String, String> getXSCountMap = getXSCount();
		Map<String, String> getCodeTaskCountMap = getCodeTaskCount();
		resultEntity.setSCount(Integer.parseInt(getXSCountMap.get("sCount")));
		resultEntity.setXCount(Integer.parseInt(getXSCountMap.get("xCount")));
		resultEntity.setA0Count(Integer.parseInt(getCodeTaskCountMap.get("A0")));
		resultEntity.setA1Count(Integer.parseInt(getCodeTaskCountMap.get("A1")));
		resultEntity.setA2Count(Integer.parseInt(getCodeTaskCountMap.get("A2")));
		resultEntity.setA3Count(Integer.parseInt(getCodeTaskCountMap.get("A3")));
		resultEntity.setA4Count(Integer.parseInt(getCodeTaskCountMap.get("A4"))); 
		resultEntity.setB1Count(Integer.parseInt(getCodeTaskCountMap.get("B1")));
		resultEntity.setB2Count(Integer.parseInt(getCodeTaskCountMap.get("B2")));
		resultEntity.setC1Count(Integer.parseInt(getCodeTaskCountMap.get("C1")));
		resultEntity.setC2Count(Integer.parseInt(getCodeTaskCountMap.get("C2")));
		resultEntity.setC3Count(Integer.parseInt(getCodeTaskCountMap.get("C3")));
		resultEntity.setC4Count(Integer.parseInt(getCodeTaskCountMap.get("C4")));
		resultEntity.setD1Count(Integer.parseInt(getCodeTaskCountMap.get("D1")));
		resultEntity.setD2Count(Integer.parseInt(getCodeTaskCountMap.get("D2")));
		return resultEntity;
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
	private static Integer getNewTasksCount() {
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
	private static Integer getUnStartTasksCount() {
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
	private static Integer getUnfoolowTasksCount() {
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
	private static Integer getTimeOutTasksCount() {
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
	private static Integer getExceptUntreatedTasksCount() {
		Integer returnInteger = 1111;
		return returnInteger;
	}
	/**
	 * 获取公海任务数
	 * @return
	 */
	private static Integer getHighSeasCount() {
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
	private static Map<String, String> getFoolowSatatistic() {
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
			resultMap.put("foolowTasksCount", row.getString("foolowTasksCount"));
			resultMap.put("qualityRisetaskCount", row.getString("qualityRisetaskCount"));
			resultMap.put("qualityDeclinetaskCount", row.getString("qualityDeclinetaskCount"));
		}
		return resultMap;
	}
	/**
	 * 获取今日预估签单金额、预估回款金额
	 * @return
	 */
	private static Map<String, String> getDayAmount() {
		Map<String, String> resultMap =new HashMap<>();
		Salesman salesman = CurrentSalesMan();
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT ifnull(SUM(signing_amount),0) daySigningAmount,");
		strSql.append("ifnull(SUM(returned_amount),0) dayReturnedAmount");
		
		strSql.append(" from n_crm_task_foolow");
		strSql.append(" where task_id in (");
		strSql.append("SELECT id from n_crm_customer_task");
		if(salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getId());
		}
		strSql.append(" and date_format(last_follow_time,'%Y-%m-%d') = CURDATE())");
		
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("daySigningAmount", row.getString("daySigningAmount"));
			resultMap.put("dayReturnedAmount", row.getString("dayReturnedAmount"));
		}
		return resultMap;
	}
	/**
	 * 获取本周预估签单金额、预估回款金额
	 * @return
	 */
	private static Map<String, String> getWeekAmount() {
		Map<String, String> resultMap =new HashMap<>();
		Salesman salesman = CurrentSalesMan();
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT ifnull(SUM(signing_amount),0) weekSigningAmount,");
		strSql.append("ifnull(SUM(returned_amount),0) weekReturnedAmount");
		
		strSql.append(" from n_crm_task_foolow");
		strSql.append(" where task_id in (");
		strSql.append("SELECT id from n_crm_customer_task");
		if(salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getId());
		}
		strSql.append(" and YEARWEEK(date_format(last_follow_time,'%Y-%m-%d')) = YEARWEEK(now()))");
		
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("weekSigningAmount", row.getString("weekSigningAmount"));
			resultMap.put("weekReturnedAmount", row.getString("weekReturnedAmount"));
		}
		return resultMap;
	}
	/**
	 * 获取本月预估签单金额、预估回款金额
	 * @return
	 */
	private static Map<String, String> getMonthAmount() {
		Map<String, String> resultMap =new HashMap<>();
		Salesman salesman = CurrentSalesMan();
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT ifnull(SUM(signing_amount),0) monthSigningAmount,");
		strSql.append("ifnull(SUM(returned_amount),0) monthReturnedAmount");
		
		strSql.append(" from n_crm_task_foolow");
		strSql.append(" where task_id in (");
		strSql.append("SELECT id from n_crm_customer_task");
		if(salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getId());
		}
		strSql.append(" and date_format(last_follow_time,'%Y-%m') = date_format(now(),'%Y-%m'))");
		
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("monthSigningAmount", row.getString("monthSigningAmount"));
			resultMap.put("monthReturnedAmount", row.getString("monthReturnedAmount"));
		}
		return resultMap;
	}
	/**
	 * 获取全部任务数、X类、S类任务数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected static Map<String, String> getXSCount() {
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
			resultMap.put("taskCount", row.getString("taskCount"));
			resultMap.put("sCount", row.getString("sCount"));
			resultMap.put("xCount", row.getString("xCount"));
		}
		return resultMap;
	}
	/**
	 * 获取任务质量数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected static Map<String, String> getCodeTaskCount() {
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
