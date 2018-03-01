package com.gongsibao.crm.web.report;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;

public class ComprehenReportPart extends CRMReportPart {

	
	protected HashMap<String, String> getDate(HashMap<String, String> filterMap) {

		HashMap<String, String> map = new HashMap<String, String>();
		String startDate = filterMap.get("date>");
		String endDate = filterMap.get("date<");
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return map;
	}


	@Override
	protected Map<String, Integer> getDataTable(HashMap<String, String> filterMap,String orgaId) {
		
		Map<String, Integer> resultMap =new HashMap<String, Integer>();
		Map<String, String> getTaskCustoCountMap = getTaskCustoCount(filterMap,orgaId);
		Map<String, String> getAllocationTaskCountMap = getAllocationTaskCount(filterMap,orgaId);
		Map<String, String> getRollOutTaskCountMap = getRollOutTaskCount(filterMap,orgaId);
		Map<String, String> getIntoTaskCountMap = getIntoTaskCount(filterMap,orgaId);
		Map<String, String> getReturnTaskCountMap = getReturnTaskCount(filterMap,orgaId);
		Map<String, String> getWithdrawTaskCountMap = getWithdrawTaskCount(filterMap,orgaId);
		Map<String, String> getFollowTaskCountMap = getFollowTaskCount(filterMap,orgaId);
		Map<String, String> getCheckAbnormalTaskCountMap = getCheckAbnormalTaskCount(filterMap,orgaId);
		Map<String, String> getUnSignTaskCountMap =getUnSignTaskCount(filterMap,orgaId);
		Map<String, String> getSignReturAmountMap =getSignReturAmount(filterMap,orgaId);
		
		resultMap.put("taskCount", Integer.parseInt(getTaskCustoCountMap.get("taskCount")));
		resultMap.put("customerCount", Integer.parseInt(getTaskCustoCountMap.get("customerCount")));
		resultMap.put("allocationTaskCount", Integer.parseInt(getAllocationTaskCountMap.get("allocationTaskCount")));
		resultMap.put("rollOutTaskCount", Integer.parseInt(getRollOutTaskCountMap.get("rollOutTaskCount")));
		resultMap.put("intoTaskCount", Integer.parseInt(getIntoTaskCountMap.get("intoTaskCount")));
		resultMap.put("returnTaskCount", Integer.parseInt(getReturnTaskCountMap.get("returnTaskCount")));
		resultMap.put("withdrawTaskCount", Integer.parseInt(getWithdrawTaskCountMap.get("withdrawTaskCount")));
		resultMap.put("followTaskCount", Integer.parseInt(getFollowTaskCountMap.get("followTaskCount")));
		resultMap.put("checkAbnormalTaskCount", Integer.parseInt(getCheckAbnormalTaskCountMap.get("checkAbnormalTaskCount")));
		resultMap.put("unSignTaskCount", Integer.parseInt(getUnSignTaskCountMap.get("unSignTaskCount")));
		resultMap.put("signingAmount", Integer.parseInt(getSignReturAmountMap.get("signingAmount") == null ? "0" : getSignReturAmountMap.get("signingAmount")));
		resultMap.put("returnedAmount", Integer.parseInt(getSignReturAmountMap.get("returnedAmount") == null ? "0" : getSignReturAmountMap.get("returnedAmount")));
		return resultMap;		
	}
	/**
	 * 获取全部客户数、全部任务数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected Map<String, String> getTaskCustoCount(HashMap<String, String> filterMap,String orgaId) {
		Map<String, String> resultMap =new HashMap<>();
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		String sourceId = map.get("sourceId");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) taskCount,");
		strSql.append("count(distinct customer_id) customerCount");
		strSql.append(" from n_crm_customer_task");
		strSql.append(" where department_id in ("+orgaId+")");
		strSql.append(" AND create_time >= '"+startDate+"'");
		strSql.append(" AND create_time <= '"+endDate+"'");
		if(sourceId != null && sourceId !=""){
			strSql.append(" AND source_id = " + sourceId);
		}
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("taskCount", row.getString("taskCount"));
			resultMap.put("customerCount", row.getString("customerCount"));
		}
		return resultMap;
	}
	/**
	 * 获取分配任务数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected Map<String, String> getAllocationTaskCount(HashMap<String, String> filterMap,String orgaId) {
		Map<String, String> resultMap =new HashMap<>();
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		String sourceId = map.get("sourceId");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(l.task_id) allocationTaskCount");
		strSql.append(" FROM n_crm_customer_operation_log l");
		strSql.append(" LEFT JOIN n_crm_customer_task t");
		strSql.append(" on l.task_id = t.id");
		
		strSql.append(" WHERE l.change_type = 2");
		strSql.append(" AND l.form_department_id in ("+orgaId+")");
		strSql.append(" AND l.create_time >= '"+startDate+"'");
		strSql.append(" AND l.create_time <= '"+endDate+"'");
		if(sourceId != null && sourceId !=""){
			strSql.append(" AND t.source_id = " + sourceId);
		}
		
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("allocationTaskCount", row.getString("allocationTaskCount"));
		}
		return resultMap;
	}
	/**
	 * 获取转出任务数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected Map<String, String> getRollOutTaskCount(HashMap<String, String> filterMap,String orgaId) {
		Map<String, String> resultMap =new HashMap<>();
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		String sourceId = map.get("sourceId");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(distinct l.task_id) as rollOutTaskCount");
		strSql.append(" from n_crm_customer_operation_log l");
		strSql.append(" LEFT JOIN n_crm_customer_task t");
		strSql.append(" on l.task_id = t.id");
		strSql.append(" where l.change_type = 5");
		strSql.append(" AND l.form_department_id in ("+orgaId+")");
		strSql.append(" AND l.create_time >= '"+startDate+"'");
		strSql.append(" AND l.create_time <= '"+endDate+"'");
		if(sourceId != null && sourceId !=""){
			strSql.append(" AND t.source_id = " + sourceId);
		}
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("rollOutTaskCount", row.getString("rollOutTaskCount"));
		}
		return resultMap;
	}
	/**
	 * 获取转入任务数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected Map<String, String> getIntoTaskCount(HashMap<String, String> filterMap,String orgaId) {
		Map<String, String> resultMap =new HashMap<>();
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		String sourceId = map.get("sourceId");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(distinct l.task_id) as intoTaskCount");
		strSql.append(" from n_crm_customer_operation_log l");
		strSql.append(" LEFT JOIN n_crm_customer_task t");
		strSql.append(" on l.task_id = t.id");
		strSql.append(" where l.change_type = 5");
		strSql.append(" AND l.department_id in ("+orgaId+")");
		strSql.append(" AND l.create_time >= '"+startDate+"'");
		strSql.append(" AND l.create_time <= '"+endDate+"'");
		if(sourceId != null && sourceId !=""){
			strSql.append(" AND t.source_id = " + sourceId);
		}
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("intoTaskCount", row.getString("intoTaskCount"));
		}
		return resultMap;
	}
	/**
	 * 获取退回任务数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected Map<String, String> getReturnTaskCount(HashMap<String, String> filterMap,String orgaId) {
		Map<String, String> resultMap =new HashMap<>();
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		String sourceId = map.get("sourceId");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(distinct l.task_id) returnTaskCount");
		strSql.append(" from n_crm_customer_operation_log l");
		strSql.append(" LEFT JOIN n_crm_customer_task t");
		strSql.append(" on l.task_id = t.id");
		strSql.append(" where l.change_type = 4");
		strSql.append(" AND l.form_department_id in ("+orgaId+")");
		strSql.append(" AND l.create_time >= '"+startDate+"'");
		strSql.append(" AND l.create_time <= '"+endDate+"'");
		if(sourceId != null && sourceId !=""){
			strSql.append(" AND t.source_id = " + sourceId);
		}
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("returnTaskCount", row.getString("returnTaskCount"));
		}
		return resultMap;
	}
	/**
	 * 获取任务收回数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected Map<String, String> getWithdrawTaskCount(HashMap<String, String> filterMap,String orgaId) {
		Map<String, String> resultMap =new HashMap<>();
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		String sourceId = map.get("sourceId");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(distinct l.task_id) as withdrawTaskCount");
		strSql.append(" from n_crm_customer_operation_log l");
		strSql.append(" LEFT JOIN n_crm_customer_task t");
		strSql.append(" on l.task_id = t.id");
		strSql.append(" where l.change_type = 6");
		strSql.append(" AND l.form_department_id in ("+orgaId+")");
		strSql.append(" AND l.create_time >= '"+startDate+"'");
		strSql.append(" AND l.create_time <= '"+endDate+"'");
		if(sourceId != null && sourceId !=""){
			strSql.append(" AND t.source_id = " + sourceId);
		}
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("withdrawTaskCount", row.getString("withdrawTaskCount"));
		}
		return resultMap;
	}
	/**
	 * 获取跟进任务数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected Map<String, String> getFollowTaskCount(HashMap<String, String> filterMap,String orgaId) {
		Map<String, String> resultMap =new HashMap<>();
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		String sourceId = map.get("sourceId");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(distinct id) followTaskCount");
		strSql.append(" from n_crm_customer_task");
		strSql.append(" where foolow_status = 3");
		strSql.append(" AND department_id in ("+orgaId+")");
		strSql.append(" AND last_follow_time >= '"+startDate+"'");
		strSql.append(" AND last_follow_time <= '"+endDate+"'");
		if(sourceId != null && sourceId !=""){
			strSql.append(" AND source_id = " + sourceId);
		}
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("followTaskCount", row.getString("followTaskCount"));
		}
		return resultMap;
	}
	/**
	 * 获取抽查异常任务数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected Map<String, String> getCheckAbnormalTaskCount(HashMap<String, String> filterMap,String orgaId) {
		Map<String, String> resultMap =new HashMap<>();
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		String sourceId = map.get("sourceId");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(distinct id) checkAbnormalTaskCount");
		strSql.append(" from n_crm_customer_task");
		strSql.append(" where inspection_state in (3,4)");
		strSql.append(" AND department_id in ("+orgaId+")");
		strSql.append(" AND last_inspection_time >= '"+startDate+"'");
		strSql.append(" AND last_inspection_time <= '"+endDate+"'");
		if(sourceId != null && sourceId !=""){
			strSql.append(" AND source_id = " + sourceId);
		}
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("checkAbnormalTaskCount", row.getString("checkAbnormalTaskCount"));
		}
		return resultMap;
	}
	/**
	 * 获取无法签单任务数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected Map<String, String> getUnSignTaskCount(HashMap<String, String> filterMap,String orgaId) {
		Map<String, String> resultMap =new HashMap<>();
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		String sourceId = map.get("sourceId");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) unSignTaskCount");
		strSql.append(" from n_crm_customer_task");
		strSql.append(" where foolow_status = 4");
		strSql.append(" AND department_id in ("+orgaId+")");
		strSql.append(" AND last_follow_time >= '"+startDate+"'");
		strSql.append(" AND last_follow_time <= '"+endDate+"'");
		if(sourceId != null && sourceId !=""){
			strSql.append(" AND source_id = " + sourceId);
		}
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("unSignTaskCount", row.getString("unSignTaskCount"));
		}
		return resultMap;
	}
	/**
	 * 获取估计签单金额 和 估计回款金额
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected Map<String, String> getSignReturAmount(HashMap<String, String> filterMap,String orgaId) {
		Map<String, String> resultMap =new HashMap<>();
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		String sourceId = map.get("sourceId");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT SUM(signing_amount) signingAmount,");
		strSql.append("SUM(returned_amount) returnedAmount");
		strSql.append(" from n_crm_task_foolow");
		strSql.append(" where task_id in (");
		strSql.append("SELECT id");
		strSql.append(" from n_crm_customer_task");
		strSql.append(" where department_id in ("+orgaId+")");
		strSql.append(" AND last_follow_time >= '"+startDate+"'");
		if(sourceId != null && sourceId !=""){
			strSql.append(" AND source_id = " + sourceId);
		}
		strSql.append(" AND last_follow_time<= '"+endDate+"')");
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("signingAmount", row.getString("signingAmount"));
			resultMap.put("returnedAmount", row.getString("returnedAmount"));
		}
		return resultMap;
	}
}