package com.gongsibao.franchisee.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.organization.entity.Organization;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.franchisee.FranchiseeReport;
import com.gongsibao.entity.franchisee.dic.FranchiseeReportType;
import com.gongsibao.franchisee.base.IFranchiseeReportService;
import com.gongsibao.franchisee.base.IFranchiseeService;

@Service
public class FranchiseeReportService extends PersistableService<FranchiseeReport> implements IFranchiseeReportService {

    public FranchiseeReportService(){
        super();
        this.type=FranchiseeReport.class;
    }

    /**
     * 处理时间
     * @param date
     * @return
     */
    public String FormatData(int date){
    	
    	return date<10 ? "0"+date : date + "";
    }
    
	@Override
	public void createDayReport(Map<Integer, List<Integer>> departmentMap) {
		// 获取当前时间
		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
		int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
		int hour = cal.get(Calendar.HOUR_OF_DAY);// 得到小时
		int minute = cal.get(Calendar.MINUTE);// 得到分钟
		int second = cal.get(Calendar.SECOND);// 得到秒
		String getCurrentTimeString = year + "-" + FormatData(month) + "-" + FormatData(day) + " "
				+ FormatData(hour) + ":" + FormatData(minute) + ":" + FormatData(second);
		String getDate = year + "" + FormatData(month) + "" + FormatData(day);
		
		// 供应商接口
		IFranchiseeService franchService = ServiceFactory
				.create(IFranchiseeService.class);
		// 报表接口
		IFranchiseeReportService reportService = ServiceFactory
				.create(IFranchiseeReportService.class);
		// 循环部门以及部门下的员工
		for (Map.Entry<Integer, List<Integer>> departItem : departmentMap.entrySet()) {
			Integer getDepartId = departItem.getKey();
			List<Integer> getEmployeeId = departItem.getValue();
			//1.生成员工的日报
			for (Integer item : getEmployeeId) {
				Map<Integer, Integer> getMap = franchService.getCustomersAllTotal(item, getCurrentTimeString);
				//删除当天已经存在的数据
				StringBuilder sqlDelBuilder = new StringBuilder();
				sqlDelBuilder.append("DELETE from bd_franchisee_report where ");
				sqlDelBuilder.append("owner_id =" + item +" and type ="+FranchiseeReportType.date.getValue()+ " AND DATE_FORMAT(date,'%Y%m%d') ="+ getDate);
				this.pm.executeNonQuery(sqlDelBuilder.toString(), null);
				
				if (getMap.isEmpty()) {
					FranchiseeReport entity = new FranchiseeReport();
					entity.setOrganizationId(getDepartId);
					entity.setOwnerId(item);
					entity.setType(FranchiseeReportType.date);
					entity.setDate(cal.getTime());
					entity.toNew();
					reportService.save(entity);
				} else {
					for (Map.Entry<Integer, Integer> entry : getMap.entrySet()) {
						Integer getOwnerId = entry.getKey();
						Integer getTotalCount = entry.getValue();
						FranchiseeReport entity = franchService.statistReport(getOwnerId,getCurrentTimeString);
						entity.setType(FranchiseeReportType.date);
						
						entity.setDate(cal.getTime());

						entity.setOrganizationId(getDepartId);
						entity.setOwnerId(getOwnerId);

						entity.setTotalCount(getTotalCount);
						entity.setUnTrackCount(getTotalCount.intValue()
								- entity.getTrackCount());
						entity.toNew();
						reportService.save(entity);
					}
				}
			}
			//2.生成部门的日报
			createDepartReport(getDepartId,FranchiseeReportType.date);
		}

	}

	@Override
	public void createDepartReport(Integer departmentId,FranchiseeReportType reportType) {
		// 报表接口
		IFranchiseeReportService reportService = ServiceFactory.create(IFranchiseeReportService.class);
		// 获取当前时间
		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
		int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
		String getCurrentTime = year + "-" + FormatData(month) + "-" + FormatData(day);
		
		//1.删除已经存在的部门日报、月报、年报等
		StringBuilder sqlDelBuilder = new StringBuilder();
		sqlDelBuilder.append("DELETE from bd_franchisee_report WHERE organization_id="+ departmentId +" and type=" + reportType.getValue() + " and owner_id is null");
		this.pm.executeNonQuery(sqlDelBuilder.toString(), null);
		//2.生成部门的日报
		StringBuilder sqlSelectBuilder = new StringBuilder();
		sqlSelectBuilder.append("SELECT SUM(total_count) as total_count,SUM(track_count) as track_count,SUM(un_track_count) as un_track_count");
		sqlSelectBuilder.append(",SUM(expected_sign_1_count) as sign_1,SUM(expected_sign_2_count) as sign_2,SUM(expected_sign_3_count) as sign_3,SUM(expected_sign_4_count) as sign_4,SUM(expected_sign_5_count) as sign_5");
		sqlSelectBuilder.append(",SUM(intIntention_degree1_count) as degree1,SUM(intIntention_degree2_count) as degree2,SUM(intIntention_degree3_count) as degree3");
		sqlSelectBuilder.append(",SUM(track_progress1_count) as progress1,SUM(track_progress2_count) as progress2,SUM(track_progress3_count) as progress3,SUM(track_progress4_count) as progress4,SUM(track_progress5_count) as progress5,SUM(track_progress6_count) as progress6,SUM(track_progress7_count) as progress7");
		sqlSelectBuilder.append(" from ");
		//判断统计类型 ：1-年、2-月、3-周、4-日
		switch (reportType.getValue()) {
		case 4:
			sqlSelectBuilder.append("(SELECT * from bd_franchisee_report WHERE organization_id="+ departmentId+" and type="+reportType.getValue()+" and owner_id <> '' and date like '%"+getCurrentTime+"%' GROUP BY owner_id) as report");			
			break;
		case 2:
			sqlSelectBuilder.append("(SELECT * from bd_franchisee_report WHERE organization_id="+ departmentId+" and type="+reportType.getValue()+" and owner_id <> '' and `year`="+year+" and `month` ="+month+" GROUP BY owner_id) as report");						
			break;
		case 1:
			sqlSelectBuilder.append("(SELECT * from bd_franchisee_report WHERE organization_id="+ departmentId+" and type="+reportType.getValue()+" and owner_id <> '' and `year`="+year+" GROUP BY owner_id) as report");
			break;
		}
		
		DataTable dataTable = this.pm.executeTable(sqlSelectBuilder.toString(), null);
		for (IRow row : dataTable) {
			
			String totalCount = row.getString("total_count");
			Integer getTotalCout = Integer.parseInt(totalCount==null ? "0":totalCount);// 总客户数量
			String trackCount = row.getString("track_count");
			Integer getTrackCount = Integer.parseInt(trackCount==null?"0":trackCount);// 跟踪客户数量
			String unTrackCount = row.getString("un_track_count");
			Integer getUnTrackCount = Integer.parseInt(unTrackCount==null?"0":unTrackCount);// 未跟踪客户数量
			
			//预计签单
			int getExpectedSign1 = Integer.parseInt(row.getString("sign_1")==null?"0":row.getString("sign_1"));//预计签单：两天内
			int getExpectedSign2 = Integer.parseInt(row.getString("sign_2")==null?"0":row.getString("sign_2"));//预计签单：一周内
			int getExpectedSign3 = Integer.parseInt(row.getString("sign_3")==null?"0":row.getString("sign_3"));//预计签单：一月内
			int getExpectedSign4 = Integer.parseInt(row.getString("sign_4")==null?"0":row.getString("sign_4"));//预计签单：三月内
			int getExpectedSign5 = Integer.parseInt(row.getString("sign_5")==null?"0":row.getString("sign_5"));//预计签单：三月以上
			//意向度
			int getIntentionDegree1 = Integer.parseInt(row.getString("degree1")==null?"0":row.getString("degree1"));//意向度：高
			int getIntentionDegree2 = Integer.parseInt(row.getString("degree2")==null?"0":row.getString("degree2"));//意向度：中
			int getIntentionDegree3 = Integer.parseInt(row.getString("degree3")==null?"0":row.getString("degree3"));//意向度：低
			//进度
			Integer getTrackProgress1 = Integer.parseInt(row.getString("progress1")==null?"0":row.getString("progress1"));//进度：未拜访
			Integer getTrackProgress2 = Integer.parseInt(row.getString("progress2")==null?"0":row.getString("progress2"));//进度：电话拜访
			Integer getTrackProgress3 = Integer.parseInt(row.getString("progress3")==null?"0":row.getString("progress3"));//进度：陌拜
			Integer getTrackProgress4 = Integer.parseInt(row.getString("progress4")==null?"0":row.getString("progress4"));//进度：洽谈中
			Integer getTrackProgress5 = Integer.parseInt(row.getString("progress5")==null?"0":row.getString("progress5"));//进度：已合作
			Integer getTrackProgress6 = Integer.parseInt(row.getString("progress6")==null?"0":row.getString("progress6"));//进度：已中止
			Integer getTrackProgress7 = Integer.parseInt(row.getString("progress7")==null?"0":row.getString("progress7"));//进度：已合作中止
			FranchiseeReport entity=new FranchiseeReport();
			entity.setType(reportType);
			
			entity.setYear(year);
			entity.setMonth(month);
			entity.setDate(cal.getTime());
			
			entity.setOrganizationId(departmentId);
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
			entity.toNew();
			FranchiseeReport returnReport = reportService.save(entity);
			//3.修改上下级结构
			StringBuilder sqlUpdateBuilder = new StringBuilder();
			sqlUpdateBuilder.append("UPDATE bd_franchisee_report set parent_id=" + returnReport.getId());
			sqlUpdateBuilder.append(" WHERE organization_id=" + departmentId +" and type=" + reportType.getValue() + " and owner_id <> ''");
			String getSql = sqlUpdateBuilder.toString();
			this.pm.executeNonQuery(getSql, null);
		}
		
	}

	@Override
	public void createYearMonthReport(Map<Integer, List<Integer>> departmentMap,FranchiseeReportType reportType) {
		// 获取当前时间
		Calendar cal = Calendar.getInstance();// 使用日历类
		int year = cal.get(Calendar.YEAR);// 得到年
		int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
		
		String getDate = year + "-" + FormatData(month);
		
		// 报表接口
		IFranchiseeReportService reportService = ServiceFactory
				.create(IFranchiseeReportService.class);
		// 循环部门以及部门下的员工
		for (Map.Entry<Integer, List<Integer>> departItem : departmentMap.entrySet()) {
			Integer getDepartId = departItem.getKey();
			List<Integer> getEmployeeId = departItem.getValue();
			//1.生成员工的月报
			for (Integer item : getEmployeeId) {
				
				//删除当年、月已经存在的数据
				StringBuilder sqlDelBuilder = new StringBuilder();
				sqlDelBuilder.append("DELETE from bd_franchisee_report where ");
				//判断统计类型 ：1-年、2-月、3-周、4-日
				switch (reportType.getValue()) {
				case 2:
					sqlDelBuilder.append("owner_id =" + item +" and type ="+reportType.getValue()+ " and `month`="+ month);
					break;
				case 1:
					sqlDelBuilder.append("owner_id =" + item +" and type ="+reportType.getValue()+ " and `year`="+ year);
					break;
				}
				this.pm.executeNonQuery(sqlDelBuilder.toString(), null);
				//查询当年、月报表数据
				StringBuilder sqlSelectBuilder = new StringBuilder();
				sqlSelectBuilder.append("SELECT total_count,track_count,un_track_count,expected_sign_1_count,expected_sign_2_count,expected_sign_3_count,");
				sqlSelectBuilder.append("expected_sign_4_count,expected_sign_5_count,intIntention_degree1_count,intIntention_degree2_count,intIntention_degree3_count,");
				sqlSelectBuilder.append("track_progress1_count,track_progress2_count,track_progress3_count,track_progress4_count,track_progress5_count,");
				sqlSelectBuilder.append("track_progress6_count,track_progress7_count");
				sqlSelectBuilder.append(" from bd_franchisee_report");
				//判断统计类型 ：1-年、2-月、3-周、4-日
				switch (reportType.getValue()) {
				case 2:
					sqlSelectBuilder.append(" where organization_id="+getDepartId+" AND owner_id ="+item+" AND date like '%"+getDate+"%' ORDER BY date desc LIMIT 0,1");
					break;
				case 1:
					sqlSelectBuilder.append(" where organization_id="+getDepartId+" AND owner_id ="+item+" AND date like '%"+getDate+"%' ORDER BY date desc LIMIT 0,1");
					break;
				}
				DataTable dataTable = this.pm.executeTable(sqlSelectBuilder.toString(), null);
				for (IRow row : dataTable) {					
					Integer getTotalCout = Integer.parseInt(row.getString("total_count"));// 总客户数量
					Integer getTrackCount = Integer.parseInt(row.getString("track_count"));// 跟踪客户数量
					Integer getUnTrackCount = Integer.parseInt(row.getString("un_track_count"));// 未跟踪客户数量
					//预计签单
					int getExpectedSign1 = Integer.parseInt(row.getString("expected_sign_1_count"));//预计签单：两天内
					int getExpectedSign2 = Integer.parseInt(row.getString("expected_sign_2_count"));//预计签单：一周内
					int getExpectedSign3 = Integer.parseInt(row.getString("expected_sign_3_count"));//预计签单：一月内
					int getExpectedSign4 = Integer.parseInt(row.getString("expected_sign_4_count"));//预计签单：三月内
					int getExpectedSign5 = Integer.parseInt(row.getString("expected_sign_5_count"));//预计签单：三月以上
					//意向度
					int getIntentionDegree1 = Integer.parseInt(row.getString("intIntention_degree1_count"));//意向度：高
					int getIntentionDegree2 = Integer.parseInt(row.getString("intIntention_degree2_count"));//意向度：中
					int getIntentionDegree3 = Integer.parseInt(row.getString("intIntention_degree3_count"));//意向度：低
					//进度
					Integer getTrackProgress1 = Integer.parseInt(row.getString("track_progress1_count"));//进度：未拜访
					Integer getTrackProgress2 = Integer.parseInt(row.getString("track_progress2_count"));//进度：电话拜访
					Integer getTrackProgress3 = Integer.parseInt(row.getString("track_progress3_count"));//进度：陌拜
					Integer getTrackProgress4 = Integer.parseInt(row.getString("track_progress4_count"));//进度：洽谈中
					Integer getTrackProgress5 = Integer.parseInt(row.getString("track_progress5_count"));//进度：已合作
					Integer getTrackProgress6 = Integer.parseInt(row.getString("track_progress6_count"));//进度：已中止
					Integer getTrackProgress7 = Integer.parseInt(row.getString("track_progress7_count"));//进度：已合作中止
					FranchiseeReport entity=new FranchiseeReport();
					//判断统计类型 ：1-年、2-月、3-周、4-日
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
					entity.setOrganizationId(getDepartId);
					entity.setOwnerId(item);
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
					entity.toNew();
					reportService.save(entity);
				}
			}
			//2.生成部门的年、月报
			//判断统计类型 ：1-年、2-月、3-周、4-日
			switch (reportType.getValue()) {
			case 2:
				createDepartReport(getDepartId,FranchiseeReportType.month);
				break;
			case 1:
				createDepartReport(getDepartId,FranchiseeReportType.year);
				break;
			}
			
		}

	}

	@Override
	public List<Organization> getOListByParentId(Integer parentId) {
		List<Organization> returnList =new ArrayList<Organization>();
		StringBuilder sqlSelBuilder = new StringBuilder();
		sqlSelBuilder.append("SELECT parent_id,path_name,name,id,is_leaf ");
		sqlSelBuilder.append("from sys_permission_organization where parent_id="+parentId);
		DataTable dataTable = this.pm.executeTable(sqlSelBuilder.toString(), null);
		for (IRow row : dataTable) {					
			Integer getParentId = Integer.parseInt(row.getString("parent_id"));
			String getPathName = row.getString("path_name");
			String getName = row.getString("name");
			Integer getId = Integer.parseInt(row.getString("id"));
			int getIsLeaf = Integer.parseInt(row.getString("is_leaf"));
			Organization orgaEntity =new Organization();
			orgaEntity.setParentId(getParentId);
			orgaEntity.setPathName(getPathName);
			orgaEntity.setName(getName);
			orgaEntity.setId(getId);
			orgaEntity.setIsLeaf(getIsLeaf==1?true:false);
			returnList.add(orgaEntity);
		}
		return returnList;
	}
}