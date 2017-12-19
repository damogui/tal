package com.gongsibao.franchisee.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.entity.Organization;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.franchisee.FranchiseeReport;
import com.gongsibao.entity.franchisee.dic.FranchiseeReportType;
import com.gongsibao.franchisee.base.IFranchiseeReportService;
import com.gongsibao.franchisee.base.IFranchiseeService;

@Service
public class FranchiseeReportService extends PersistableService<FranchiseeReport> implements IFranchiseeReportService {

	public FranchiseeReportService() {
		super();
		this.type = FranchiseeReport.class;
	}

	/**
	 * 处理时间
	 * 
	 * @param date
	 * @return
	 */
	public String FormatData(int date) {
		return date < 10 ? "0" + date : date + "";
	}

	@Override
	public void createStaffDayReport(Integer employeeId,String employeeName, Integer parentId,Integer OrgaId) {
		// 获取当前时间
		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
		int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
		int hour = cal.get(Calendar.HOUR_OF_DAY);// 得到小时
		int minute = cal.get(Calendar.MINUTE);// 得到分钟
		int second = cal.get(Calendar.SECOND);// 得到秒
		String getCurrentTimeString = year + "-" + FormatData(month) + "-"+ FormatData(day) + " " + FormatData(hour) + ":"+ FormatData(minute) + ":" + FormatData(second);
		String getDate = year + "" + FormatData(month) + "" + FormatData(day);

		// 供应商接口
		IFranchiseeService franchService = ServiceFactory.create(IFranchiseeService.class);
		// 报表接口
		IFranchiseeReportService reportService = ServiceFactory.create(IFranchiseeReportService.class);

		//获取员工的总客户数量
		Map<Integer, Integer> getMap = franchService.getCustomersAllTotal(employeeId, getCurrentTimeString);
		// 删除当天已经存在的数据
		StringBuilder sqlDelBuilder = new StringBuilder();
		sqlDelBuilder.append("DELETE from bd_franchisee_report where ");
		sqlDelBuilder.append("owner_id =" + employeeId + " and type ="+ FranchiseeReportType.date.getValue()+ " AND DATE_FORMAT(date,'%Y%m%d') =" + getDate);
		this.pm.executeNonQuery(sqlDelBuilder.toString(), null);

		if(getMap == null || getMap.size()<1){
			FranchiseeReport entity = new FranchiseeReport();
			entity.setType(FranchiseeReportType.date);
			entity.setDate(cal.getTime());
			entity.setOrganizationId(OrgaId);
			entity.setOwnerId(employeeId);
			entity.setParentId(parentId);
			entity.setTotalCount(0);
			entity.setTrackCount(0);
			entity.setUnTrackCount(0);
			entity.setShowOrganName(employeeName);
			entity.toNew();
			reportService.save(entity);
		}else{
			for (Map.Entry<Integer, Integer> entry : getMap.entrySet()) {
				Integer getOwnerId = entry.getKey();
				Integer getTotalCount = entry.getValue();
				//统计报表数据
				FranchiseeReport entity = franchService.statistReport(getOwnerId,getCurrentTimeString);
				entity.setType(FranchiseeReportType.date);
				entity.setDate(cal.getTime());
				entity.setOrganizationId(OrgaId);
				entity.setOwnerId(getOwnerId);
				entity.setParentId(parentId);
				entity.setTotalCount(getTotalCount);
				entity.setUnTrackCount(getTotalCount.intValue()- entity.getTrackCount());
				entity.setShowOrganName(employeeName);
				entity.toNew();
				reportService.save(entity);
			}
		}
	}
	
	@Override
	public void createStaffYearMonthReport(Integer employeeId,String employeeName, Integer parentId,Integer OrgaId,FranchiseeReportType reportType) {
		// 获取当前时间
		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1

		String getDate = year + "-" + FormatData(month);

		// 报表接口
		IFranchiseeReportService reportService = ServiceFactory.create(IFranchiseeReportService.class);
		// 删除当年、月已经存在的数据
		StringBuilder sqlDelBuilder = new StringBuilder();
		sqlDelBuilder.append("DELETE from bd_franchisee_report where ");
		// 判断统计类型 ：1-年、2-月、3-周、4-日
		switch (reportType.getValue()) {
		case 2:
			sqlDelBuilder.append("owner_id =" + employeeId + " and type ="+ reportType.getValue() + " and `month`=" + month);
			break;
		case 1:
			sqlDelBuilder.append("owner_id =" + employeeId + " and type ="+ reportType.getValue() + " and `year`=" + year);
			break;
		}
		this.pm.executeNonQuery(sqlDelBuilder.toString(), null);
		// 查询当年、月报表数据
		StringBuilder sqlSelectBuilder = new StringBuilder();
		sqlSelectBuilder.append("SELECT total_count,track_count,un_track_count,expected_sign_1_count,expected_sign_2_count,expected_sign_3_count,");
		sqlSelectBuilder.append("expected_sign_4_count,expected_sign_5_count,intIntention_degree1_count,intIntention_degree2_count,intIntention_degree3_count,");
		sqlSelectBuilder.append("track_progress1_count,track_progress2_count,track_progress3_count,track_progress4_count,track_progress5_count,");
		sqlSelectBuilder.append("track_progress6_count,track_progress7_count");
		sqlSelectBuilder.append(" from bd_franchisee_report");
		// 判断统计类型 ：1-年、2-月、3-周、4-日
		switch (reportType.getValue()) {
		case 2:
			sqlSelectBuilder.append(" where organization_id="+ OrgaId + " AND owner_id =" + employeeId+ " AND date like '%" + getDate+ "%' ORDER BY date desc LIMIT 0,1");
			break;
		case 1:
			sqlSelectBuilder.append(" where organization_id="+ OrgaId + " AND owner_id =" + employeeId+ " AND date like '%" + getDate+ "%' ORDER BY date desc LIMIT 0,1");
			break;
		}
		DataTable dataTable = this.pm.executeTable(sqlSelectBuilder.toString(), null);
		for (IRow row : dataTable) {
			Integer getTotalCout = Integer.parseInt(row.getString("total_count"));// 总客户数量
			Integer getTrackCount = Integer.parseInt(row.getString("track_count"));// 跟踪客户数量
			Integer getUnTrackCount = Integer.parseInt(row.getString("un_track_count"));// 未跟踪客户数量
			// 预计签单
			int getExpectedSign1 = Integer.parseInt(row.getString("expected_sign_1_count"));// 预计签单：两天内
			int getExpectedSign2 = Integer.parseInt(row.getString("expected_sign_2_count"));// 预计签单：一周内
			int getExpectedSign3 = Integer.parseInt(row.getString("expected_sign_3_count"));// 预计签单：一月内
			int getExpectedSign4 = Integer.parseInt(row.getString("expected_sign_4_count"));// 预计签单：三月内
			int getExpectedSign5 = Integer.parseInt(row.getString("expected_sign_5_count"));// 预计签单：三月以上
			// 意向度
			int getIntentionDegree1 = Integer.parseInt(row.getString("intIntention_degree1_count"));// 意向度：高
			int getIntentionDegree2 = Integer.parseInt(row.getString("intIntention_degree2_count"));// 意向度：中
			int getIntentionDegree3 = Integer.parseInt(row.getString("intIntention_degree3_count"));// 意向度：低
			
			// 进度
			Integer getTrackProgress1 = Integer.parseInt(row.getString("track_progress1_count"));// 进度：未拜访
			Integer getTrackProgress2 = Integer.parseInt(row.getString("track_progress2_count"));// 进度：电话拜访
			Integer getTrackProgress3 = Integer.parseInt(row.getString("track_progress3_count"));// 进度：陌拜
			Integer getTrackProgress4 = Integer.parseInt(row.getString("track_progress4_count"));// 进度：洽谈中
			Integer getTrackProgress5 = Integer.parseInt(row.getString("track_progress5_count"));// 进度：已合作
			Integer getTrackProgress6 = Integer.parseInt(row.getString("track_progress6_count"));// 进度：已中止
			Integer getTrackProgress7 = Integer.parseInt(row.getString("track_progress7_count"));// 进度：已合作中止
			
			FranchiseeReport entity = new FranchiseeReport();
			// 判断统计类型 ：1-年、2-月、3-周、4-日
			switch (reportType.getValue()) {
			case 2:
				entity.setType(FranchiseeReportType.month);
				break;
			case 1:
				entity.setType(FranchiseeReportType.year);
				break;
			}

			entity.setYear(year);
			entity.setMonth(month);
			entity.setDate(cal.getTime());
			entity.setOrganizationId(OrgaId);
			entity.setOwnerId(employeeId);
			entity.setParentId(parentId);
			entity.setTotalCount(getTotalCout);
			entity.setTrackCount(getTrackCount);
			entity.setUnTrackCount(getUnTrackCount);
			entity.setExpectedSign1Count(getExpectedSign1);
			entity.setExpectedSign2Count(getExpectedSign2);
			entity.setExpectedSign3Count(getExpectedSign3);
			entity.setExpectedSign4Count(getExpectedSign4);
			entity.setExpectedSign5Count(getExpectedSign5);
			entity.setIntentionDegree1Count(getIntentionDegree1);
			entity.setIntentionDegree2Count(getIntentionDegree2);
			entity.setIntentionDegree3Count(getIntentionDegree3);

			entity.setTrackProgress1Count(getTrackProgress1);
			entity.setTrackProgress2Count(getTrackProgress2);
			entity.setTrackProgress3Count(getTrackProgress3);
			entity.setTrackProgress4Count(getTrackProgress4);
			entity.setTrackProgress5Count(getTrackProgress5);
			entity.setTrackProgress6Count(getTrackProgress6);
			entity.setTrackProgress7Count(getTrackProgress7);
			entity.setShowOrganName(employeeName);
			entity.toNew();
			reportService.save(entity);
		}
	}

	@Override
	public List<Organization> getOranListByOrgaId(Integer orgaId) {
		List<Organization> returnList = new ArrayList<Organization>();
		StringBuilder sqlSelBuilder = new StringBuilder();
		sqlSelBuilder.append("SELECT parent_id,path_name,name,id,is_leaf ");
		sqlSelBuilder.append("from sys_permission_organization where parent_id="+ orgaId);
		DataTable dataTable = this.pm.executeTable(sqlSelBuilder.toString(),null);
		for (IRow row : dataTable) {
			Integer getParentId = Integer.parseInt(row.getString("parent_id"));
			String getPathName = row.getString("path_name");
			String getName = row.getString("name");
			Integer getId = Integer.parseInt(row.getString("id"));
			boolean getIsLeaf = row.getString("is_leaf") == "true" ? true: false;

			Organization orgaEntity = new Organization();
			orgaEntity.setParentId(getParentId);
			orgaEntity.setPathName(getPathName);
			orgaEntity.setName(getName);
			orgaEntity.setId(getId);
			orgaEntity.setIsLeaf(getIsLeaf);
			returnList.add(orgaEntity);
		}
		return returnList;
	}

	@Override
	public Map<Integer, Integer> recursiveByOrgaId(Map<Integer, Integer> map,Integer OrgaId) {
		try {
			List<Organization> list = getOranListByOrgaId(OrgaId);
			if (null != list && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Organization organization = list.get(i);
					// 获取组织结构的父Id,查询报表中是否存在(配置组织结构)
					Integer dayReportId = getReportIdByOrganId(organization.getParentId(),FranchiseeReportType.date);
					Integer monthReportId = getReportIdByOrganId(organization.getParentId(),FranchiseeReportType.month);
					Integer yearReportId = getReportIdByOrganId(organization.getParentId(),FranchiseeReportType.year);
					// 是否是叶子节点 true-是、false-否
					if (organization.getIsLeaf()) {
						// 根据是叶子节点的组织机构Id,获取员工Id
						Map<Integer, String> employMap = getEmployeeIdByOrganId(organization.getId());
						Integer getEmployId = null; 
						String getEmployName = null;
						for(Integer key : employMap.keySet()){ 
							getEmployId = key;
							getEmployName = employMap.get(key);
					     }  
						//创建员工的日报、月报、年报
						createStaffDayReport(getEmployId,getEmployName, dayReportId,organization.getParentId());
						createStaffYearMonthReport(getEmployId,getEmployName, monthReportId,organization.getParentId(),FranchiseeReportType.month);
						createStaffYearMonthReport(getEmployId,getEmployName, yearReportId,organization.getParentId(),FranchiseeReportType.year);
						
						System.out.println("员工:" + getEmployName+ "---" + organization.getId() + "---"+ organization.getParentId());
					} else {
						FranchiseeReport reportEntity = new FranchiseeReport();
						reportEntity.setOrganizationId(organization.getId());
						reportEntity.setShowOrganName(organization.getName());
						//创建组织机构的日报、月报、年报
						createDirectoryReport(dayReportId,reportEntity,FranchiseeReportType.date);
						createDirectoryReport(monthReportId,reportEntity,FranchiseeReportType.month);
						createDirectoryReport(yearReportId,reportEntity,FranchiseeReportType.year);
						
						System.out.println(organization.getName() + "---"+ organization.getId() + "---"+ organization.getParentId());
						map.put(organization.getId(), organization.getParentId());
					}
					recursiveByOrgaId(map,organization.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Integer getReportIdByOrganId(Integer OrgaId,FranchiseeReportType reportType) {
		Integer returnId = null;
		StringBuilder sqlSelBuilder = new StringBuilder();
		sqlSelBuilder.append("SELECT id from bd_franchisee_report WHERE owner_id is null and organization_id="+ OrgaId);
		sqlSelBuilder.append(" and type="+ reportType.getValue());
		DataTable dataTable = this.pm.executeTable(sqlSelBuilder.toString(),null);
		for (IRow row : dataTable) {
			returnId = Integer.parseInt(row.getString("id"));
		}
		return returnId;
	}

	@Override
	public Map<Integer, String> getEmployeeIdByOrganId(Integer OrgaId) {
		Map<Integer, String> returnMap =new HashMap<Integer, String>();
		StringBuilder sqlSelBuilder = new StringBuilder();
		sqlSelBuilder.append("SELECT spe.id as id,spe.`name` as name from sys_permission_organization_employee spoe");
		sqlSelBuilder.append(" LEFT JOIN sys_permission_employee spe ON spoe.employee_id=spe.id");
		sqlSelBuilder.append(" where spoe.organization_id=" + OrgaId);
		DataTable dataTable = this.pm.executeTable(sqlSelBuilder.toString(),null);
		for (IRow row : dataTable) {
			Integer returnId = Integer.parseInt(row.getString("id"));
			String returnName = row.getString("name");
			returnMap.put(returnId, returnName);
		}
		return returnMap;
	}

	@Override
	public void createDirectoryReport(Integer parentId,FranchiseeReport reportEntity,FranchiseeReportType reportType) {
		// 删除已经存在的数据
		StringBuilder sqlDelBuilder = new StringBuilder();
		sqlDelBuilder.append("DELETE from bd_franchisee_report WHERE owner_id is null and");
		sqlDelBuilder.append(" organization_id="+ reportEntity.getOrganizationId()+ " and type="+ reportType.getValue());
		this.pm.executeNonQuery(sqlDelBuilder.toString(), null);
		
		// 获取当前时间
		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
		reportEntity.setType(reportType);
		reportEntity.setParentId(parentId);
		reportEntity.setDate(cal.getTime());
		reportEntity.setYear(year);
		reportEntity.setMonth(month);
		// 报表接口
		IFranchiseeReportService reportService = ServiceFactory.create(IFranchiseeReportService.class);
		reportEntity.toNew();
		reportService.save(reportEntity);
	}

	@Override
	public void statDireReportNo(FranchiseeReportType reportType) {
		//1.先计算员工的上一级统计
		StringBuilder sqlSelBuilder = new StringBuilder();
		sqlSelBuilder.append("SELECT SUM(total_count) as total_count,SUM(track_count) as track_count,SUM(un_track_count) as un_track_count,");
		sqlSelBuilder.append("SUM(expected_sign_1_count) as sign_1,SUM(expected_sign_2_count) as sign_2,SUM(expected_sign_3_count) as sign_3,");
		sqlSelBuilder.append("SUM(expected_sign_4_count) as sign_4,SUM(expected_sign_5_count) as sign_5,");
		sqlSelBuilder.append("SUM(intIntention_degree1_count) as degree1,SUM(intIntention_degree2_count) as degree2,SUM(intIntention_degree3_count) as degree3,");
		sqlSelBuilder.append("SUM(track_progress1_count) as progress1,SUM(track_progress2_count) as progress2,SUM(track_progress3_count) as progress3,");
		sqlSelBuilder.append("SUM(track_progress4_count) as progress4,SUM(track_progress5_count) as progress5,SUM(track_progress6_count) as progress6,");
		sqlSelBuilder.append("SUM(track_progress7_count) as progress7,parent_id,id");
		sqlSelBuilder.append(" from bd_franchisee_report where type="+reportType.getValue());
		sqlSelBuilder.append(" GROUP BY organization_id HAVING parent_id is not null");
		DataTable dataTable = this.pm.executeTable(sqlSelBuilder.toString(), null);
		for (IRow row : dataTable) {
			Integer getId = Integer.parseInt(row.getString("id"));// 总客户数量
			
			String totalCount = row.getString("total_count");
			Integer getTotalCout = Integer.parseInt(totalCount == null ? "0": totalCount);// 总客户数量
			String trackCount = row.getString("track_count");
			Integer getTrackCount = Integer.parseInt(trackCount == null ? "0": trackCount);// 跟踪客户数量
			String unTrackCount = row.getString("un_track_count");
			Integer getUnTrackCount = Integer.parseInt(unTrackCount == null ? "0" : unTrackCount);// 未跟踪客户数量

			// 预计签单
			int getExpectedSign1 = Integer.parseInt(row.getString("sign_1") == null ? "0" : row.getString("sign_1"));// 预计签单：两天内
			int getExpectedSign2 = Integer.parseInt(row.getString("sign_2") == null ? "0" : row.getString("sign_2"));// 预计签单：一周内
			int getExpectedSign3 = Integer.parseInt(row.getString("sign_3") == null ? "0" : row.getString("sign_3"));// 预计签单：一月内
			int getExpectedSign4 = Integer.parseInt(row.getString("sign_4") == null ? "0" : row.getString("sign_4"));// 预计签单：三月内
			int getExpectedSign5 = Integer.parseInt(row.getString("sign_5") == null ? "0" : row.getString("sign_5"));// 预计签单：三月以上
			// 意向度
			int getIntentionDegree1 = Integer.parseInt(row.getString("degree1") == null ? "0" : row.getString("degree1"));// 意向度：高
			int getIntentionDegree2 = Integer.parseInt(row.getString("degree2") == null ? "0" : row.getString("degree2"));// 意向度：中
			int getIntentionDegree3 = Integer.parseInt(row.getString("degree3") == null ? "0" : row.getString("degree3"));// 意向度：低
			// 进度
			Integer getTrackProgress1 = Integer.parseInt(row.getString("progress1") == null ? "0" : row.getString("progress1"));// 进度：未拜访
			Integer getTrackProgress2 = Integer.parseInt(row.getString("progress2") == null ? "0" : row.getString("progress2"));// 进度：电话拜访
			Integer getTrackProgress3 = Integer.parseInt(row.getString("progress3") == null ? "0" : row.getString("progress3"));// 进度：陌拜
			Integer getTrackProgress4 = Integer.parseInt(row.getString("progress4") == null ? "0" : row.getString("progress4"));// 进度：洽谈中
			Integer getTrackProgress5 = Integer.parseInt(row.getString("progress5") == null ? "0" : row.getString("progress5"));// 进度：已合作
			Integer getTrackProgress6 = Integer.parseInt(row.getString("progress6") == null ? "0" : row.getString("progress6"));// 进度：已中止
			Integer getTrackProgress7 = Integer.parseInt(row.getString("progress7") == null ? "0" : row.getString("progress7"));// 进度：已合作中止
			
			StringBuilder sqlUpdateBuilder = new StringBuilder();
			sqlUpdateBuilder.append("UPDATE bd_franchisee_report set total_count="+getTotalCout+",track_count ="+getTrackCount+",un_track_count="+getUnTrackCount);
			sqlUpdateBuilder.append(",expected_sign_1_count="+getExpectedSign1+",expected_sign_2_count="+getExpectedSign2+",expected_sign_3_count="+getExpectedSign3);
			sqlUpdateBuilder.append(",expected_sign_4_count="+getExpectedSign4+",expected_sign_5_count="+getExpectedSign5);
			sqlUpdateBuilder.append(",intIntention_degree1_count ="+getIntentionDegree1+",intIntention_degree2_count="+getIntentionDegree2);
			sqlUpdateBuilder.append(",intIntention_degree3_count ="+getIntentionDegree3+",track_progress1_count = "+getTrackProgress1);
			sqlUpdateBuilder.append(",track_progress2_count = "+getTrackProgress2+",track_progress3_count = "+getTrackProgress3+",track_progress4_count = "+getTrackProgress4);
			sqlUpdateBuilder.append(",track_progress5_count ="+getTrackProgress5+",track_progress6_count = "+getTrackProgress6+",track_progress7_count = "+getTrackProgress7);
			sqlUpdateBuilder.append(" WHERE id=" + getId);
			String getSql = sqlUpdateBuilder.toString();
			this.pm.executeNonQuery(getSql, null);
		}
		//2.计算頂级统计
		StringBuilder sqlSelBuilder1 = new StringBuilder();
		sqlSelBuilder1.append("SELECT SUM(total_count) as total_count,SUM(track_count) as track_count,SUM(un_track_count) as un_track_count,");
		sqlSelBuilder1.append("SUM(expected_sign_1_count) as sign_1,SUM(expected_sign_2_count) as sign_2,SUM(expected_sign_3_count) as sign_3,");
		sqlSelBuilder1.append("SUM(expected_sign_4_count) as sign_4,SUM(expected_sign_5_count) as sign_5,");
		sqlSelBuilder1.append("SUM(intIntention_degree1_count) as degree1,SUM(intIntention_degree2_count) as degree2,SUM(intIntention_degree3_count) as degree3,");
		sqlSelBuilder1.append("SUM(track_progress1_count) as progress1,SUM(track_progress2_count) as progress2,SUM(track_progress3_count) as progress3,");
		sqlSelBuilder1.append("SUM(track_progress4_count) as progress4,SUM(track_progress5_count) as progress5,SUM(track_progress6_count) as progress6,");
		sqlSelBuilder1.append("SUM(track_progress7_count) as progress7,parent_id");
		sqlSelBuilder1.append(" from bd_franchisee_report where type="+reportType.getValue()+" and parent_id in(");
		sqlSelBuilder1.append("SELECT id from bd_franchisee_report");
		sqlSelBuilder1.append(" where parent_id is null)");
		sqlSelBuilder1.append(" GROUP BY parent_id");
		DataTable dataTable1 = this.pm.executeTable(sqlSelBuilder1.toString(), null);
		for (IRow row : dataTable1) {
			Integer getId = Integer.parseInt(row.getString("parent_id"));// 总客户数量
			String totalCount = row.getString("total_count");
			Integer getTotalCout = Integer.parseInt(totalCount == null ? "0": totalCount);// 总客户数量
			String trackCount = row.getString("track_count");
			Integer getTrackCount = Integer.parseInt(trackCount == null ? "0": trackCount);// 跟踪客户数量
			String unTrackCount = row.getString("un_track_count");
			Integer getUnTrackCount = Integer.parseInt(unTrackCount == null ? "0" : unTrackCount);// 未跟踪客户数量

			// 预计签单
			int getExpectedSign1 = Integer.parseInt(row.getString("sign_1") == null ? "0" : row.getString("sign_1"));// 预计签单：两天内
			int getExpectedSign2 = Integer.parseInt(row.getString("sign_2") == null ? "0" : row.getString("sign_2"));// 预计签单：一周内
			int getExpectedSign3 = Integer.parseInt(row.getString("sign_3") == null ? "0" : row.getString("sign_3"));// 预计签单：一月内
			int getExpectedSign4 = Integer.parseInt(row.getString("sign_4") == null ? "0" : row.getString("sign_4"));// 预计签单：三月内
			int getExpectedSign5 = Integer.parseInt(row.getString("sign_5") == null ? "0" : row.getString("sign_5"));// 预计签单：三月以上
			// 意向度
			int getIntentionDegree1 = Integer.parseInt(row.getString("degree1") == null ? "0" : row.getString("degree1"));// 意向度：高
			int getIntentionDegree2 = Integer.parseInt(row.getString("degree2") == null ? "0" : row.getString("degree2"));// 意向度：中
			int getIntentionDegree3 = Integer.parseInt(row.getString("degree3") == null ? "0" : row.getString("degree3"));// 意向度：低
			// 进度
			Integer getTrackProgress1 = Integer.parseInt(row.getString("progress1") == null ? "0" : row.getString("progress1"));// 进度：未拜访
			Integer getTrackProgress2 = Integer.parseInt(row.getString("progress2") == null ? "0" : row.getString("progress2"));// 进度：电话拜访
			Integer getTrackProgress3 = Integer.parseInt(row.getString("progress3") == null ? "0" : row.getString("progress3"));// 进度：陌拜
			Integer getTrackProgress4 = Integer.parseInt(row.getString("progress4") == null ? "0" : row.getString("progress4"));// 进度：洽谈中
			Integer getTrackProgress5 = Integer.parseInt(row.getString("progress5") == null ? "0" : row.getString("progress5"));// 进度：已合作
			Integer getTrackProgress6 = Integer.parseInt(row.getString("progress6") == null ? "0" : row.getString("progress6"));// 进度：已中止
			Integer getTrackProgress7 = Integer.parseInt(row.getString("progress7") == null ? "0" : row.getString("progress7"));// 进度：已合作中止
			
			StringBuilder sqlUpdateBuilder = new StringBuilder();
			sqlUpdateBuilder.append("UPDATE bd_franchisee_report set total_count="+getTotalCout+",track_count ="+getTrackCount+",un_track_count="+getUnTrackCount);
			sqlUpdateBuilder.append(",expected_sign_1_count="+getExpectedSign1+",expected_sign_2_count="+getExpectedSign2+",expected_sign_3_count="+getExpectedSign3);
			sqlUpdateBuilder.append(",expected_sign_4_count="+getExpectedSign4+",expected_sign_5_count="+getExpectedSign5);
			sqlUpdateBuilder.append(",intIntention_degree1_count ="+getIntentionDegree1+",intIntention_degree2_count="+getIntentionDegree2);
			sqlUpdateBuilder.append(",intIntention_degree3_count ="+getIntentionDegree3+",track_progress1_count = "+getTrackProgress1);
			sqlUpdateBuilder.append(",track_progress2_count = "+getTrackProgress2+",track_progress3_count = "+getTrackProgress3+",track_progress4_count = "+getTrackProgress4);
			sqlUpdateBuilder.append(",track_progress5_count ="+getTrackProgress5+",track_progress6_count = "+getTrackProgress6+",track_progress7_count = "+getTrackProgress7);
			sqlUpdateBuilder.append(" WHERE id=" + getId);
			String getSql = sqlUpdateBuilder.toString();
			this.pm.executeNonQuery(getSql, null);
		}
		
	}

	@Override
	public Boolean execute(Date date) {
		
		// 1.先查询招商部员工信息
		IOrganizationService organizaService = ServiceFactory.create(IOrganizationService.class);
		List<Organization> getOrganList = organizaService.getByFunction("Channel");
		IFranchiseeReportService reportService = ServiceFactory.create(IFranchiseeReportService.class);
		Map<Integer, Integer> getMap = new HashMap<>();
		
		// 2.根据员工信息查询对应客户状态的数据，保存FranchiseeReport信息
		getMap = reportService.recursiveByOrgaId(getMap, getOrganList.get(0).getId());
		//getMap = reportService.recursiveByOrgaId(getMap, 2101204994);
		
		// 3.注意：这里需要处理上下级状态，数据展现出来是树结构
		reportService.statDireReportNo(FranchiseeReportType.date);
		reportService.statDireReportNo(FranchiseeReportType.month);
		reportService.statDireReportNo(FranchiseeReportType.year);
		// 4.注意:类，代码，方法的设计，不要堆在一起
		// 5.这里只是一个入口
		
		return true;
	}
}