package com.gongsibao.crm.web.report;

import java.util.HashMap;
import java.util.Map;

import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;

import com.gongsibao.entity.crm.report.BaseReportEntity;
import com.gongsibao.entity.crm.report.FunnelReportEntity;

public class FunelReportPart extends BaseReport {
	
	protected HashMap<String, String> getDate(HashMap<String, String> filterMap) {		
		HashMap<String, String> map = new HashMap<String, String>();
		String startDate = filterMap.get("date>");
		String endDate = filterMap.get("date<");
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return map;
	}
	
	@Override
	protected FunnelReportEntity getDataTable(BaseReportEntity entity,HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
		FunnelReportEntity resultEntity = new FunnelReportEntity();
		Map<String, String> getXSCountMap = getXSCount(filterMap,orgaId,salesmanId,isSalesman);
		Map<String, String> getCodeTaskCountMap = getCodeTaskCount(filterMap,orgaId,salesmanId,isSalesman);
		
		resultEntity.setId(entity.getId());
		resultEntity.setParentId(entity.getParentId());
		resultEntity.setSupplierId(entity.getSupplierId());
		resultEntity.setDepartmentName(entity.getDepartmentName());
		resultEntity.setIsLeaf(entity.getIsLeaf());
		resultEntity.setTaskCount(Integer.parseInt(getXSCountMap.get("taskCount")));
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
	 * 获取全部任务数、X类、S类任务数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	protected Map<String, String> getXSCount(HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
		Map<String, String> resultMap =new HashMap<>();
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		String sourceId = map.get("sourceId");
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) taskCount,");
		strSql.append("count(intention_category = 6 OR NULL) sCount,");
		strSql.append("count(intention_category = 5 OR NULL) xCount");
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
	protected Map<String, String> getCodeTaskCount(HashMap<String, String> filterMap,String orgaId,Integer salesmanId,Boolean isSalesman) {
		Map<String, String> resultMap =new HashMap<>();
		HashMap<String, String>  dataMap = this.getDate(filterMap);
		String startDate = dataMap.get("startDate").replace("'", "");
		String endDate = dataMap.get("endDate").replace("'", "");
		String sourceId = map.get("sourceId");
		
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
		if(isSalesman){
			strSql.append(" where owner_id =" + salesmanId);
		}else {
			strSql.append(" where department_id in ("+orgaId+")");
		}
		
		strSql.append(" and intention_category in (1,2,3,4)");
		if(sourceId != null && sourceId !=""){
			strSql.append(" AND t.source_id = " + sourceId);
		}
		strSql.append(" and create_time >= '"+startDate+"'");
		strSql.append(" and create_time<= '"+endDate+"')");
		
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