package com.gongsibao.entity.franchisee;

import java.util.Date;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.CatEntity;
import org.netsharp.organization.entity.Employee;
import org.netsharp.organization.entity.Organization;

import com.gongsibao.entity.report.dic.ReportDateType;
import com.gongsibao.entity.report.dic.ReportOrganizationType;

@Table(name="report_bd_franchisee",header="供应商报表")
public class FranchiseeReport extends CatEntity{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -8758613644594941085L;

	@Reference(foreignKey = "departmentId", header = "部门")
	private Organization department;

	@Column(name = "department_id", header = "部门Id")
	private Integer departmentId;
	
    @Column(name="owner_id",header="业务员Id")
    private Integer ownerId;
    
    @Reference(foreignKey="ownerId")
    private Employee owner;
    
	@Column(name = "date_type", header = "统计时间类型")
	private ReportDateType dateType;
	
	@Column(name = "organization_type", header = "统计组织类型")
	private ReportOrganizationType organizationType;
    
    @Column(name="year",header="年")
    private int year;
    
    @Column(name="month",header="月")
    private int month;
    
    @Column(name="week",header="周")
    private int week;
    
    @Column(name="day",header="天")
    private int day;
    
    @Column(name="date",header="日期")
    private Date date;
    
    @Column(name="total_count",header="总客户数")
    private int totalCount = 0;
    
    @Column(name="track_count",header="已跟进数")
    private int trackCount = 0;
    
    @Column(name="un_track_count",header="未跟进数")
    private int unTrackCount = 0;
    
    //预计签单统计
    @Column(name="expected_sign_1_count",header="预计签单：两天内")
    private int expectedSign1Count = 0;
    
    @Column(name="expected_sign_2_count",header="预计签单：一周内")
    private int expectedSign2Count = 0;
    
    @Column(name="expected_sign_3_count",header="预计签单：一月内")
    private int expectedSign3Count = 0;
    
    @Column(name="expected_sign_4_count",header="预计签单：三月内")
    private int expectedSign4Count = 0;
    
    @Column(name="expected_sign_5_count",header="预计签单：三月以上")
    private int expectedSign5Count = 0;

    
    @Column(name="intIntention_degree1_count",header="意向度：高")
    private int intentionDegree1Count = 0;
    
    @Column(name="intIntention_degree2_count",header="意向度：中")
    private int intentionDegree2Count = 0;
    
    @Column(name="intIntention_degree3_count",header="意向度：低")
    private int intentionDegree3Count = 0;
    
    
    //进度统计
    @Column(name="track_progress1_count",header="进度：未拜访")
    private int trackProgress1Count = 0;
    
    @Column(name="track_progress2_count",header="进度：电话拜访")
    private int trackProgress2Count = 0;
    
    @Column(name="track_progress3_count",header="进度：陌拜")
    private int trackProgress3Count = 0;
    
    @Column(name="track_progress4_count",header="进度：洽谈中")
    private int trackProgress4Count = 0;
    
    @Column(name="track_progress5_count",header="进度：已合作")
    private int trackProgress5Count = 0;
    
    @Column(name="track_progress6_count",header="进度：已中止")
    private int trackProgress6Count = 0;
    
    @Column(name="track_progress7_count",header="进度：已合作中止")
    private int trackProgress7Count = 0;
    
	public ReportDateType getDateType() {
		return dateType;
	}

	public void setDateType(ReportDateType dateType) {
		this.dateType = dateType;
	}

	public ReportOrganizationType getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(ReportOrganizationType organizationType) {
		this.organizationType = organizationType;
	}

	public Organization getDepartment() {
		return department;
	}

	public void setDepartment(Organization department) {
		this.department = department;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Employee getOwner() {
		return owner;
	}

	public void setOwner(Employee owner) {
		this.owner = owner;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTrackCount() {
		return trackCount;
	}

	public void setTrackCount(int trackCount) {
		this.trackCount = trackCount;
	}

	public int getUnTrackCount() {
		return unTrackCount;
	}

	public void setUnTrackCount(int unTrackCount) {
		this.unTrackCount = unTrackCount;
	}

	public int getExpectedSign1Count() {
		return expectedSign1Count;
	}

	public void setExpectedSign1Count(int expectedSign1Count) {
		this.expectedSign1Count = expectedSign1Count;
	}

	public int getExpectedSign2Count() {
		return expectedSign2Count;
	}

	public void setExpectedSign2Count(int expectedSign2Count) {
		this.expectedSign2Count = expectedSign2Count;
	}

	public int getExpectedSign3Count() {
		return expectedSign3Count;
	}

	public void setExpectedSign3Count(int expectedSign3Count) {
		this.expectedSign3Count = expectedSign3Count;
	}

	public int getExpectedSign4Count() {
		return expectedSign4Count;
	}

	public void setExpectedSign4Count(int expectedSign4Count) {
		this.expectedSign4Count = expectedSign4Count;
	}

	public int getExpectedSign5Count() {
		return expectedSign5Count;
	}

	public void setExpectedSign5Count(int expectedSign5Count) {
		this.expectedSign5Count = expectedSign5Count;
	}

	public int getIntentionDegree1Count() {
		return intentionDegree1Count;
	}

	public void setIntentionDegree1Count(int intentionDegree1Count) {
		this.intentionDegree1Count = intentionDegree1Count;
	}

	public int getIntentionDegree2Count() {
		return intentionDegree2Count;
	}

	public void setIntentionDegree2Count(int intentionDegree2Count) {
		this.intentionDegree2Count = intentionDegree2Count;
	}

	public int getIntentionDegree3Count() {
		return intentionDegree3Count;
	}

	public void setIntentionDegree3Count(int intentionDegree3Count) {
		this.intentionDegree3Count = intentionDegree3Count;
	}

	public int getTrackProgress1Count() {
		return trackProgress1Count;
	}

	public void setTrackProgress1Count(int trackProgress1Count) {
		this.trackProgress1Count = trackProgress1Count;
	}

	public int getTrackProgress2Count() {
		return trackProgress2Count;
	}

	public void setTrackProgress2Count(int trackProgress2Count) {
		this.trackProgress2Count = trackProgress2Count;
	}

	public int getTrackProgress3Count() {
		return trackProgress3Count;
	}

	public void setTrackProgress3Count(int trackProgress3Count) {
		this.trackProgress3Count = trackProgress3Count;
	}

	public int getTrackProgress4Count() {
		return trackProgress4Count;
	}

	public void setTrackProgress4Count(int trackProgress4Count) {
		this.trackProgress4Count = trackProgress4Count;
	}

	public int getTrackProgress5Count() {
		return trackProgress5Count;
	}

	public void setTrackProgress5Count(int trackProgress5Count) {
		this.trackProgress5Count = trackProgress5Count;
	}

	public int getTrackProgress6Count() {
		return trackProgress6Count;
	}

	public void setTrackProgress6Count(int trackProgress6Count) {
		this.trackProgress6Count = trackProgress6Count;
	}

	public int getTrackProgress7Count() {
		return trackProgress7Count;
	}

	public void setTrackProgress7Count(int trackProgress7Count) {
		this.trackProgress7Count = trackProgress7Count;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	
}
