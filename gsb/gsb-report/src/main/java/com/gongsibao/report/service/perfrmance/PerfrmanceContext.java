package com.gongsibao.report.service.perfrmance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.util.StringManager;

import com.gongsibao.entity.uc.UserOrganizationMap;
import com.gongsibao.report.utils.DateUtils;

/**
 * @ClassName: PerfrmanceContext
 * @Description:TODO 业绩统计上下文
 * @author: 韩伟
 * @date: 2017年12月13日 下午8:50:13
 * 
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved.
 */
public class PerfrmanceContext {

	/**
	 * @Fields userId : TODO(业务员ID)
	 */
	private Integer salesmanId;

	private String salesmanIds;

	/**
	 * @Fields departmentId : TODO(部门Id)
	 */
	private Integer departmentId;

	/**
	 * @Fields date : TODO(统计时间)
	 */
	private Date date;


	private List<UserOrganizationMap> mapList;

	public Integer getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(Integer salesmanId) {
		this.salesmanId = salesmanId;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<UserOrganizationMap> getMapList() {
		return mapList;
	}

	public void setMapList(List<UserOrganizationMap> mapList) {
		this.mapList = mapList;
	}

	public String getSalesmanIds() {

		if (StringManager.isNullOrEmpty(salesmanIds)) {

			List<String> ss = new ArrayList<String>();
			for (UserOrganizationMap map : mapList) {

				ss.add(map.getUserId().toString());
			}
			salesmanIds = StringManager.join(",", ss);
		}

		return salesmanIds;
	}

	public void setSalesmanIds(String salesmanIds) {
		this.salesmanIds = salesmanIds;
	}

	public Integer getYear() {
		return DateUtils.getYear(date);
	}

	public Integer getSeason() {
		return DateUtils.getSeason(date);
	}

	public Integer getMonth() {
		return DateUtils.getMonth(date);
	}

	public Integer getWeek() {
		return DateUtils.getWeekOfYear(date);
	}

	public Integer getDay() {
		return DateUtils.getPassDayOfMonth(date);
	}

}
