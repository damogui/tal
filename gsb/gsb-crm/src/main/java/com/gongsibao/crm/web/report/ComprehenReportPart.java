package com.gongsibao.crm.web.report;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;

import com.gongsibao.entity.crm.report.BaseReportEntity;
import com.gongsibao.entity.crm.report.ComprehenReportEntity;

public class ComprehenReportPart extends BaseReport {

	
	protected HashMap<String, String> getDate(HashMap<String, String> filterMap) {

		HashMap<String, String> map = new HashMap<String, String>();
		String startDate = filterMap.get("date>");
		String endDate = filterMap.get("date<");
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return map;
	}
	
	@Override
	protected ComprehenReportEntity getDataTable(BaseReportEntity entity,HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
		ComprehenReportEntity resultEntity = new ComprehenReportEntity();
		
		Map<String, String> getTaskCustoCountMap = getTaskCustoCount(filterMap,orgaId,salesmanId,isSalesman);
		Map<String, String> getAllocationTaskCountMap = getAllocationTaskCount(filterMap,orgaId,salesmanId,isSalesman);
		Map<String, String> getRollOutTaskCountMap = getRollOutTaskCount(filterMap,orgaId,salesmanId,isSalesman);
		Map<String, String> getIntoTaskCountMap = getIntoTaskCount(filterMap,orgaId,salesmanId,isSalesman);
		Map<String, String> getReturnTaskCountMap = getReturnTaskCount(filterMap,orgaId,salesmanId,isSalesman);
		Map<String, String> getWithdrawTaskCountMap = getWithdrawTaskCount(filterMap,orgaId,salesmanId,isSalesman);
		Map<String, String> getFollowTaskCountMap = getFollowTaskCount(filterMap,orgaId,salesmanId,isSalesman);
		Map<String, String> getCheckAbnormalTaskCountMap = getCheckAbnormalTaskCount(filterMap,orgaId,salesmanId,isSalesman);
		Map<String, String> getUnSignTaskCountMap =getUnSignTaskCount(filterMap,orgaId,salesmanId,isSalesman);
		Map<String, String> getSignReturAmountMap =getSignReturAmount(filterMap,orgaId,salesmanId,isSalesman);
		
		resultEntity.setId(entity.getId());
		resultEntity.setParentId(entity.getParentId());
		resultEntity.setSupplierId(entity.getSupplierId());
		resultEntity.setDepartmentName(entity.getDepartmentName());
		resultEntity.setIsLeaf(entity.getIsLeaf());
		resultEntity.setCustomerCount(Integer.parseInt(getTaskCustoCountMap.get("customerCount")));
		resultEntity.setTaskCount(Integer.parseInt(getTaskCustoCountMap.get("taskCount")));
		/*entity.setSelfCustomerCount(2);
		entity.setSelfTaskCount(3);*/
		resultEntity.setAllocationTaskCount(Integer.parseInt(getAllocationTaskCountMap.get("allocationTaskCount")));
		resultEntity.setIntoTaskCount(Integer.parseInt(getIntoTaskCountMap.get("intoTaskCount")));
		resultEntity.setRollOutTaskCount(Integer.parseInt(getRollOutTaskCountMap.get("rollOutTaskCount")));
		resultEntity.setReturnTaskCount(Integer.parseInt(getReturnTaskCountMap.get("returnTaskCount")));
		resultEntity.setWithdrawTaskCount(Integer.parseInt(getWithdrawTaskCountMap.get("withdrawTaskCount")));
		resultEntity.setFollowTaskCount(Integer.parseInt(getFollowTaskCountMap.get("followTaskCount")));
		resultEntity.setUnSignTaskCount(Integer.parseInt(getUnSignTaskCountMap.get("unSignTaskCount")));
		resultEntity.setCheckAbnormalTaskCount(Integer.parseInt(getCheckAbnormalTaskCountMap.get("checkAbnormalTaskCount")));
		resultEntity.setSigningAmount(Integer.parseInt(getSignReturAmountMap.get("signingAmount") == null ? "0" : getSignReturAmountMap.get("signingAmount")));
		resultEntity.setReturnedAmount(Integer.parseInt(getSignReturAmountMap.get("returnedAmount") == null ? "0" : getSignReturAmountMap.get("returnedAmount")));
		
		return resultEntity;		
	}
	
	
	/**
	 * 获取全部客户数、全部任务数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected Map<String, String> getTaskCustoCount(HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
		Map<String, String> resultMap =new HashMap<>();
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		String sourceId = map.get("sourceId");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) taskCount,");
		strSql.append("count(distinct customer_id) customerCount");
		strSql.append(" from n_crm_customer_task");
		if(isSalesman){
			strSql.append(" where owner_id =" + salesmanId);
		}else {
			strSql.append(" where department_id in ("+orgaId+")");
		}
		
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
	protected Map<String, String> getAllocationTaskCount(HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
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
		if(isSalesman){
			strSql.append(" AND t.owner_id =" + salesmanId);
			
		}else {
			strSql.append(" AND l.form_department_id in ("+orgaId+")");
		}	
		
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
	protected Map<String, String> getRollOutTaskCount(HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
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
		if(isSalesman){
			strSql.append(" AND t.owner_id =" + salesmanId);
		}else {
			strSql.append(" AND l.form_department_id in ("+orgaId+")");
		}	
		
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
	protected Map<String, String> getIntoTaskCount(HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
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
		if(isSalesman){
			strSql.append(" AND t.owner_id =" + salesmanId);
		}else {
			strSql.append(" AND l.department_id in ("+orgaId+")");
		}
		
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
	protected Map<String, String> getReturnTaskCount(HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
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
		if(isSalesman){
			strSql.append(" AND t.owner_id =" + salesmanId);
		}else {
			strSql.append(" AND l.form_department_id in ("+orgaId+")");
		}
		
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
	protected Map<String, String> getWithdrawTaskCount(HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
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
		if(isSalesman){
			strSql.append(" AND t.owner_id =" + salesmanId);
		}else {
			strSql.append(" AND l.form_department_id in ("+orgaId+")");
		}
		
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
	protected Map<String, String> getFollowTaskCount(HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
		Map<String, String> resultMap =new HashMap<>();
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		String sourceId = map.get("sourceId");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(distinct id) followTaskCount");
		strSql.append(" from n_crm_customer_task");
		strSql.append(" where foolow_status = 3");
		if(isSalesman){
			strSql.append(" AND owner_id =" + salesmanId);
		}else {
			strSql.append(" AND department_id in ("+orgaId+")");
		}
		
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
	protected Map<String, String> getCheckAbnormalTaskCount(HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
		Map<String, String> resultMap =new HashMap<>();
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		String sourceId = map.get("sourceId");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(distinct id) checkAbnormalTaskCount");
		strSql.append(" from n_crm_customer_task");
		strSql.append(" where inspection_state in (3,4)");
		if(isSalesman){
			strSql.append(" AND owner_id =" + salesmanId);
		}else {
			strSql.append(" AND department_id in ("+orgaId+")");
		}
		
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
	protected Map<String, String> getUnSignTaskCount(HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
		Map<String, String> resultMap =new HashMap<>();
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		String sourceId = map.get("sourceId");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) unSignTaskCount");
		strSql.append(" from n_crm_customer_task");
		strSql.append(" where foolow_status = 4");
		if(isSalesman){
			strSql.append(" AND owner_id =" + salesmanId);
		}else {
			strSql.append(" AND department_id in ("+orgaId+")");
		}
		
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
	protected Map<String, String> getSignReturAmount(HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
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
		if(isSalesman){
			strSql.append(" where owner_id =" + salesmanId);
		}else {
			strSql.append(" where department_id in ("+orgaId+")");
		}
		
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