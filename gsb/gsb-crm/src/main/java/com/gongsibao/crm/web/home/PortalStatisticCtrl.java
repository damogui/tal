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

public class PortalStatisticCtrl {
	
   ISupplierDepartmentService departService = ServiceFactory.create(ISupplierDepartmentService.class);	
	/**
	 * 获取当前登录人实体
	 * @return
	 */
	public Salesman currentSalesMan(){
		ISalesmanService manSerice = ServiceFactory.create(ISalesmanService.class);
		Integer currentLogin = SessionManager.getUserId();
		Salesman salesman = manSerice.byEmployeeId(currentLogin);
		return salesman;
	}
	/**
	 * 返回销售简报详情
	 * @param portalLevel 1-售前、0-服务商
	 * @param dateType 1-今日、2-本周、3-本月、4-本年
	 * @return
	 */
	public PortalStatisticDTO salesPresentation(Integer isPlatform,Integer dateType){
		PortalStatisticDTO statisticDTO = new PortalStatisticDTO();
		//Integer newTasksCount = getNewTasksCount(isPlatform,dateType);
		Integer unStartTasksCount = getUnStartTasksCount(isPlatform,dateType);
		Integer unfoolowTasksCount = getUnfoolowTasksCount();
		Integer timeOutTasksCount = getTimeOutTasksCount();
		Integer exceptUntreatedTasksCount = getExceptUntreatedTasksCount();
		
		//领导可查看公海数量，业务员看不到数据（门户统计过滤业务员），由于该方法公用（统计报表用），门户不需要过滤日期
		Salesman salesman = currentSalesMan();
		Integer highSeasCount = 0;
		if(salesman != null && salesman.getIsLeader()!=null){
			if(salesman.getIsLeader()){
				highSeasCount = getHighSeasCount(isPlatform,-1);
			}
		}
		
		//statisticDTO.setNewTasksCount(newTasksCount);
		statisticDTO.setUnStartTasksCount(unStartTasksCount);
		statisticDTO.setUnfoolowTasksCount(unfoolowTasksCount);
		statisticDTO.setTimeOutTasksCount(timeOutTasksCount);
		statisticDTO.setExceptUntreatedTasksCount(exceptUntreatedTasksCount);
		statisticDTO.setHighSeasCount(highSeasCount);
		return statisticDTO;
	}
	/**
	 * 获取新增商机数
	 * @param isPlatform 1-售前、0-服务商
	 * @param dateType 1-今日、2-本周、3-本月、4-本年
	 * @return
	 */
	public Integer getNewTasksCount(Integer isPlatform,Integer dateType) {
		
		Integer returnInteger = 0;
		Salesman salesman = currentSalesMan();
		if(salesman == null){
			return returnInteger;
		}
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) newTasksCount");
		strSql.append(" from n_crm_customer_task");
		//门户级别
		if(isPlatform.equals(1)){
			strSql.append(" where 1=1");
		}else{
			if(salesman.getIsLeader()!= null && salesman.getIsLeader()){
				strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
			}else {
				strSql.append(" where owner_id =" + salesman.getEmployeeId());
			}
		}
		switch(dateType){
			case 1:
				strSql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') = CURDATE()");
				break;
			case 2:
				strSql.append(" and YEARWEEK(date_format(create_time,'%Y-%m-%d')) = YEARWEEK(now())");
				break;
			case 3:
				strSql.append(" and date_format(create_time,'%Y-%m') = date_format(now(),'%Y-%m')");
				break;
			case 4:
				strSql.append(" and date_format(create_time,'%Y') = date_format(now(),'%Y')");
				break;
		}
		
		
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			returnInteger = Integer.valueOf(row.getString("newTasksCount"));
		}
		return returnInteger;
	}
	/**
	 * 获取未启动商机数
	 * @param isPlatform 1-售前、0-服务商
	 * @param dateType 1-今日、2-本周、3-本月、4-本年
	 * @return
	 */
	public Integer getUnStartTasksCount(Integer isPlatform,Integer dateType) {
		Integer returnInteger = 0;
		Salesman salesman = currentSalesMan();
		if(salesman == null){
			return returnInteger;
		}
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT count(id) unStartTasksCount");
		strSql.append(" from n_crm_customer_task");
		if(isPlatform.equals(1)){
			switch(dateType){
			case 1:
				strSql.append(" where DATE_FORMAT(create_time,'%Y-%m-%d') = CURDATE()");
				break;
			case 2:
				strSql.append(" where YEARWEEK(date_format(create_time,'%Y-%m-%d')) = YEARWEEK(now())");
				break;
			case 3:
				strSql.append(" where date_format(create_time,'%Y-%m') = date_format(now(),'%Y-%m')");
				break;
			case 4:
				strSql.append(" where date_format(create_time,'%Y') = date_format(now(),'%Y')");
				break;
			}
		}else {
			if(salesman.getIsLeader()!= null && salesman.getIsLeader()){
				strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
			}else {
				strSql.append(" where owner_id =" + salesman.getEmployeeId());
			}
			strSql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') <= CURDATE()");
		}
		strSql.append(" AND foolow_status = 6");
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			returnInteger = Integer.valueOf(row.getString("unStartTasksCount"));
		}
		return returnInteger;
	}
	/**
	 * 获取待跟进商机数
	 * @return
	 */
	public Integer getUnfoolowTasksCount() {
		Integer returnInteger = 0;
		Salesman salesman = currentSalesMan();
		if(salesman == null){
			return returnInteger;
		}
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) unfoolowTasksCount");
		strSql.append(" from n_crm_customer_task");
		
		if(salesman.getIsLeader()!= null && salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getEmployeeId());
		}
		strSql.append(" AND next_foolow_time = CURDATE()");
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			returnInteger = Integer.valueOf(row.getString("unfoolowTasksCount"));
		}
		return returnInteger;
	}
	/**
	 * 获取超时商机数
	 * @return
	 */
	public Integer getTimeOutTasksCount() {
		Integer returnInteger = 0;
		Salesman salesman = currentSalesMan();
		if(salesman == null){
			return returnInteger;
		}
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) timeOutTasksCount");
		strSql.append(" from n_crm_customer_task");
		
		if(salesman.getIsLeader()!= null && salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getEmployeeId());
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
	 * 获取异常未处理商机数
	 * @return
	 */
	public Integer getExceptUntreatedTasksCount() {
		Integer returnInteger = 0;
		Salesman salesman = currentSalesMan();
		if(salesman == null){
			return returnInteger;
		}
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) exceptUntreatedTasksCount");
		strSql.append(" from n_crm_customer_task");
		
		if(salesman.getIsLeader()!= null && salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getEmployeeId());
		}
		strSql.append(" AND inspection_state = 3");
		strSql.append(" AND DATE_FORMAT(last_inspection_time,'%Y-%m-%d') <= CURDATE()");
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			returnInteger = Integer.valueOf(row.getString("exceptUntreatedTasksCount"));
		}
		return returnInteger;
	}
	/**
	 * 获取公海商机数(门户有过滤)
	 * @param isPlatform 1-售前、0-服务商
	 * @param dateType 1-今日、2-本周、3-本月
	 * @return
	 */
	public Integer getHighSeasCount(Integer isPlatform,Integer dateType) {
		Integer returnInteger = 0;
		Salesman salesman = currentSalesMan();
		if(salesman == null){
			return returnInteger;
		}
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) highSeasCount");
		strSql.append(" from n_crm_customer_task");
		strSql.append(" WHERE (owner_id is null or owner_id=0)");
		if(isPlatform.equals(0)){
			strSql.append(" and department_id in ("+salesman.getDepartmentId()+")");
			strSql.append(" and DATE_FORMAT(create_time,'%Y-%m-%d') <= CURDATE()");
		}else {
			switch(dateType){
			case 1:
				strSql.append(" and DATE_FORMAT(create_time,'%Y-%m-%d') = CURDATE()");
				break;
			case 2:
				strSql.append(" and YEARWEEK(date_format(create_time,'%Y-%m-%d')) = YEARWEEK(now())");
				break;
			case 3:
				strSql.append(" and date_format(create_time,'%Y-%m') = date_format(now(),'%Y-%m')");
				break;
			case 4:
				strSql.append(" and date_format(create_time,'%Y') = date_format(now(),'%Y')");
				break;
			}
		}
		
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			returnInteger = Integer.valueOf(row.getString("highSeasCount"));
		}
		return returnInteger;
	}
	/**
	 * 获取跟进统计（跟进商机数、质量上升、下降商机数）
	 * @return
	 */
	public Map<String, String> getFoolowSatatistic() {
		Map<String, String> resultMap =new HashMap<>();
		Salesman salesman = currentSalesMan();
		if(salesman == null){
			resultMap.put("跟进商机数", "0");
			resultMap.put("质量上升商机数", "0");
			resultMap.put("质量下降商机数", "0");
			return resultMap;
		}
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(foolow_status = 3 OR NULL) foolowTasksCount,");
		strSql.append("count(quality_progress = 1 OR NULL) qualityRisetaskCount,");
		strSql.append("count(intention_category = 2 OR NULL) qualityDeclinetaskCount");
		
		strSql.append(" from n_crm_customer_task");
		if(salesman.getIsLeader()!= null && salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getEmployeeId());
		}
		strSql.append(" AND DATE_FORMAT(next_foolow_time,'%Y-%m-%d') = CURDATE()");
		
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("跟进商机数", row.getString("foolowTasksCount"));
			resultMap.put("质量上升商机数", row.getString("qualityRisetaskCount"));
			resultMap.put("质量下降商机数", row.getString("qualityDeclinetaskCount"));
		}
		return resultMap;
	}
	/**
	 * 获取预估签单金额、预估回款金额
	 * @param dateType 1-今日、2-本周、3-本月
	 * @return
	 */
	public Map<String, String> getForecastAmount(Integer dateType) {
		Map<String, String> resultMap =new HashMap<>();
		Salesman salesman = currentSalesMan();
		if(salesman == null){
			resultMap.put("预估签单金额", "0");
			resultMap.put("预估回款金额", "0");
			return resultMap;
		}
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT ifnull(SUM(signing_amount),0) signingAmount,");
		strSql.append("ifnull(SUM(returned_amount),0) returnedAmount");
		
		strSql.append(" from n_crm_task_foolow");
		strSql.append(" where task_id in (");
		strSql.append("SELECT id from n_crm_customer_task");
		if(salesman.getIsLeader()!= null && salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getEmployeeId());
		}
		if(dateType.equals(1)){
			strSql.append(" and date_format(last_follow_time,'%Y-%m-%d') = CURDATE())");
		}else if (dateType.equals(2)) {
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
	 * 获取全部商机数、X类、S类商机数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	public Map<String, String> getXSCount() {
		Map<String, String> resultMap =new HashMap<>();
		Salesman salesman = currentSalesMan();
		if(salesman == null){
			resultMap.put("全部商机", "0");
			resultMap.put("S类", "0");
			resultMap.put("X类", "0");
			return resultMap;
		}
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) taskCount,");
		strSql.append("count(intention_category = 6 OR NULL) sCount,");
		strSql.append("count(intention_category = 5 OR NULL) xCount");
		strSql.append(" from n_crm_customer_task");
		
		if(salesman.getIsLeader()!= null && salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getEmployeeId());
		}
		
		strSql.append(" AND DATE_FORMAT(create_time,'%Y-%m-%d') <= CURDATE()");
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			resultMap.put("全部商机", row.getString("taskCount"));
			resultMap.put("S类", row.getString("sCount"));
			resultMap.put("X类", row.getString("xCount"));
		}
		return resultMap;
	}
	/**
	 * 获取商机质量数
	 * @param filterMap 
	 * @param orgaId
	 * @return
	 */
	public Map<String, String> getCodeTaskCount() {
		Map<String, String> resultMap =new HashMap<>();
		Salesman salesman = currentSalesMan();
		if(salesman == null){
			resultMap.put("A0", "0");
			resultMap.put("A1", "0");
			resultMap.put("A2", "0");
			resultMap.put("A3", "0");
			resultMap.put("A4", "0");
			resultMap.put("B1", "0");
			resultMap.put("B2", "0");
			resultMap.put("C1", "0");
			resultMap.put("C2", "0");
			resultMap.put("C3", "0");
			resultMap.put("C4", "0");
			resultMap.put("D1", "0");
			resultMap.put("D2", "0");
			return resultMap;
		}
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
		if(salesman.getIsLeader()!= null && salesman.getIsLeader()){
			strSql.append(" where department_id in ("+salesman.getDepartmentId()+")");
		}else {
			strSql.append(" where owner_id =" + salesman.getEmployeeId());
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
	/**
	 * 获取新增客户数
	 * @param dateType 1-今日、2-本周、3-本月、4-本年
	 * @return
	 */
	public Integer getNewCustomerCount(Integer dateType) {
		Integer returnInteger = 0;
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(distinct customer_id) newCustomerCount");
		strSql.append(" from n_crm_customer_task");
		switch(dateType){
			case 1:
				strSql.append(" where DATE_FORMAT(create_time,'%Y-%m-%d') = CURDATE()");
				break;
			case 2:
				strSql.append(" where YEARWEEK(date_format(create_time,'%Y-%m-%d')) = YEARWEEK(now())");
				break;
			case 3:
				strSql.append(" where date_format(create_time,'%Y-%m') = date_format(now(),'%Y-%m')");
				break;
			case 4:
				strSql.append(" where date_format(create_time,'%Y') = date_format(now(),'%Y')");
				break;
		}
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			returnInteger = Integer.valueOf(row.getString("newCustomerCount"));
		}
		return returnInteger;
	}
	/**
	 * 获取未分配商机数
	 * @param dateType 1-今日、2-本周、3-本月、4-本年
	 * @return
	 */
	public Integer getUndistributed(Integer dateType) {
		Integer returnInteger = 0;
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(id) undistributed");
		strSql.append(" from n_crm_customer_task");
		strSql.append(" where (distribut is NULL or distribut = 0)");
		switch(dateType){
			case 1:
				strSql.append(" and DATE_FORMAT(create_time,'%Y-%m-%d') = CURDATE()");
				break;
			case 2:
				strSql.append(" and YEARWEEK(date_format(create_time,'%Y-%m-%d')) = YEARWEEK(now())");
				break;
			case 3:
				strSql.append(" and date_format(create_time,'%Y-%m') = date_format(now(),'%Y-%m')");
				break;
			case 4:
				strSql.append(" and date_format(create_time,'%Y') = date_format(now(),'%Y')");
				break;
		}
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			returnInteger = Integer.valueOf(row.getString("undistributed"));
		}
		return returnInteger;
	}
	/**
	 * 获取无商机的客户数
	 * @param dateType 1-今日、2-本周、3-本月、4-本年
	 * @return
	 */
	public Integer getNotTaskCount(Integer dateType) {
		Integer returnInteger = 0;
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(*) notTaskCount");
		strSql.append(" from  crm_customer");
		strSql.append(" where id not IN(");
		strSql.append("SELECT distinct customer_id");
		strSql.append(" from n_crm_customer_task");
		switch(dateType){
			case 1:
				strSql.append(" where DATE_FORMAT(create_time,'%Y-%m-%d') = CURDATE())");
				break;
			case 2:
				strSql.append(" where YEARWEEK(date_format(create_time,'%Y-%m-%d')) = YEARWEEK(now()))");
				break;
			case 3:
				strSql.append(" where date_format(create_time,'%Y-%m') = date_format(now(),'%Y-%m'))");
				break;
			case 4:
				strSql.append(" where date_format(create_time,'%Y') = date_format(now(),'%Y'))");
				break;
		}
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			returnInteger = Integer.valueOf(row.getString("notTaskCount"));
		}
		return returnInteger;
	}
	/**
	 * 获取无法签单商机数
	 * @param dateType 1-今日、2-本周、3-本月、4-本年
	 * @return
	 */
	public Integer getDefeatedCount(Integer dateType) {
		Integer returnInteger = 0;
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(*) defeatedCount");
		strSql.append(" FROM n_crm_customer_task");
		strSql.append(" where foolow_status = 4");
		switch(dateType){
			case 1:
				strSql.append(" and DATE_FORMAT(create_time,'%Y-%m-%d') = CURDATE()");
				break;
			case 2:
				strSql.append(" and YEARWEEK(date_format(create_time,'%Y-%m-%d')) = YEARWEEK(now())");
				break;
			case 3:
				strSql.append(" and date_format(create_time,'%Y-%m') = date_format(now(),'%Y-%m')");
				break;
			case 4:
				strSql.append(" and date_format(create_time,'%Y') = date_format(now(),'%Y')");
				break;
		}
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			returnInteger = Integer.valueOf(row.getString("defeatedCount"));
		}
		return returnInteger;
	}
	/**
	 * 获取抽查异常商机数
	 * @param dateType 1-今日、2-本周、3-本月、4-本年
	 * @return
	 */
	public Integer getCheckAbnormalCount(Integer dateType) {
		Integer returnInteger = 0;
		
		StringBuilder strSql=new StringBuilder();
		strSql.append("SELECT COUNT(*) checkAbnormalCount");
		strSql.append(" FROM n_crm_customer_task");
		strSql.append(" where inspection_state in (3,4)");
		switch(dateType){
			case 1:
				strSql.append(" and DATE_FORMAT(create_time,'%Y-%m-%d') = CURDATE()");
				break;
			case 2:
				strSql.append(" and YEARWEEK(date_format(create_time,'%Y-%m-%d')) = YEARWEEK(now())");
				break;
			case 3:
				strSql.append(" and date_format(create_time,'%Y-%m') = date_format(now(),'%Y-%m')");
				break;
			case 4:
				strSql.append(" and date_format(create_time,'%Y') = date_format(now(),'%Y')");
				break;
		}
		DataTable dtNewCount = departService.executeTable(strSql.toString(), null);
		for (IRow row : dtNewCount) {
			returnInteger = Integer.valueOf(row.getString("checkAbnormalCount"));
		}
		return returnInteger;
	}
}
