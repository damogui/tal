package com.gongsibao.crm.web.report;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;

import com.gongsibao.entity.crm.report.BaseReportEntity;
import com.gongsibao.entity.crm.report.FollowReportEntity;

public class FollowReportPart extends BaseReport{

	protected HashMap<String, String> getDate(HashMap<String, String> filterMap) {

		HashMap<String, String> map = new HashMap<String, String>();
		String startDate = filterMap.get("date>");
		startDate = startDate.substring(0, startDate.indexOf("and")-1);
		map.put("startDate", startDate);
		
		return map;
	}
	
	@Override
	protected FollowReportEntity getDataTable(BaseReportEntity entity,HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
		FollowReportEntity resultEntity = new FollowReportEntity();
		
		Integer taskCount = getTaskCount(filterMap,orgaId,salesmanId,isSalesman); 
		Integer unfoolowCount = getUnfoolowCount(filterMap,orgaId,salesmanId,isSalesman);
		Integer timeOutCount = getTimeOutCount(filterMap,orgaId,salesmanId,isSalesman);
		Integer foolowCount = getFoolowCount(filterMap,orgaId,salesmanId,isSalesman);
		Map<String, String> qualityCountMap = getQualityCount(filterMap,orgaId,salesmanId,isSalesman);
		
		resultEntity.setId(entity.getId());
		resultEntity.setParentId(entity.getParentId());
		resultEntity.setSupplierId(entity.getSupplierId());
		resultEntity.setDepartmentName(entity.getDepartmentName());
		resultEntity.setIsLeaf(entity.getIsLeaf());
		
		resultEntity.setTaskCount(taskCount);
		resultEntity.setUnfoolowCount(unfoolowCount);
		resultEntity.setTimeOutCount(timeOutCount);
		resultEntity.setFoolowCount(foolowCount);
		resultEntity.setQualityDeclinetaskCount(Integer.parseInt(qualityCountMap.get("qualityDeclinetaskCount")));
		resultEntity.setQualityRisetaskCount(Integer.parseInt(qualityCountMap.get("qualityRisetaskCount")));
		
		return resultEntity;		
	}
	
	/**
	 * 获取全部任务数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected Integer getTaskCount(HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
		Integer taskCount = 0;
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) taskCount");
		strSql.append(" from n_crm_customer_task");
		if(isSalesman){
			strSql.append(" where owner_id =" + salesmanId);
		}else {
			strSql.append(" where department_id in ("+orgaId+")");
		}
		
		strSql.append(" AND create_time <= "+startDate);
		
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			taskCount = Integer.parseInt(row.getString("taskCount"));
		}
		return taskCount;
	}
	/**
	 * 获取待跟进任务数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected Integer getUnfoolowCount(HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
		Integer unfoolowCount = 0;
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) unfoolowCount");
		strSql.append(" from n_crm_customer_task");
		if(isSalesman){
			strSql.append(" where owner_id =" + salesmanId);
		}else {
			strSql.append(" where department_id in ("+orgaId+")");
		}
		
		strSql.append(" AND next_foolow_time is not null");
		strSql.append(" AND next_foolow_time = " + startDate);
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			unfoolowCount = Integer.parseInt(row.getString("unfoolowCount"));
		}
		return unfoolowCount;
	}
	/**
	 * 获取超时任务数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected Integer getTimeOutCount(HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
		Integer timeOutCount = 0;
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate");
		
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) timeOutCount");
		strSql.append(" from n_crm_customer_task");
		if(isSalesman){
			strSql.append(" where owner_id =" + salesmanId);
		}else {
			strSql.append(" where department_id in ("+orgaId+")");
		}
		
		strSql.append(" AND foolow_status = 3");
		strSql.append(" AND "+startDate+"> next_foolow_time");
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			timeOutCount = Integer.parseInt(row.getString("timeOutCount"));
		}
		return timeOutCount;
	}
	/**
	 * 获取跟进任务数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected Integer getFoolowCount(HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
		Integer foolowCount = 0;
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate");
		
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) foolowCount");
		strSql.append(" from n_crm_customer_task");
		if(isSalesman){
			strSql.append(" where owner_id =" + salesmanId);
		}else {
			strSql.append(" where department_id in ("+orgaId+")");
		}
		
		strSql.append(" AND next_foolow_time <= "+startDate);
		strSql.append(" AND foolow_status = 3");
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			foolowCount = Integer.parseInt(row.getString("foolowCount"));
		}
		return foolowCount;
	}
	
	/**
	 * 获取质量上升、下降任务数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected Map<String, String> getQualityCount(HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
		Map<String, String> resultMap =new HashMap<>();
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT count(quality_progress = 1 OR NULL) qualityRisetaskCount,");
		strSql.append("count(intention_category = 2 OR NULL) qualityDeclinetaskCount");
		strSql.append(" from n_crm_customer_task");
		if(isSalesman){
			strSql.append(" where owner_id =" + salesmanId);
		}else {
			strSql.append(" where department_id in ("+orgaId+")");
		}
		strSql.append(" AND next_foolow_time <= "+startDate);
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("qualityRisetaskCount", row.getString("qualityRisetaskCount"));
			resultMap.put("qualityDeclinetaskCount", row.getString("qualityDeclinetaskCount"));
		}
		return resultMap;
	}
}
