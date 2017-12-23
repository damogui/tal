package com.gongsibao.entity.report.customer;

import org.netsharp.core.annotations.Id;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Persistable;

import com.gongsibao.entity.uc.Organization;

@Table(isView=true, name = "")
public class BaseCustomerReportEntity extends Persistable{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -8435478175074534778L;

	/**   
	 * @Fields id : TODO(Id)   
	 */
	@Id
	private Integer id;
	
	/**   
	 * @Fields parentId : TODO(上级Id)   
	 */   
	private Integer parentId;
	
	/**   
	 * @Fields orgName : TODO(组织机构名称)   
	 */   
	private String orgName;
	
    @Reference(foreignKey="departmentId")
	private Organization department;

	private Integer departmentId;
	
    /**   
     * @Fields year : TODO(年)   
     */   
    private Integer year;
    
    /**   
     * @Fields season : TODO(季)   
     */
    private Integer season;
    
    /**   
     * @Fields month : TODO(月)   
     */
    private Integer month;
    
    /**   
     * @Fields week : TODO(周)   
     */
    private Integer week;
    
    /**   
     * @Fields day : TODO(日)   
     */
    private Integer day;
    
    /**   
     * @Fields date : TODO(日期)   
     */   
    private String date;
	
	/**   
	 * @Fields isLeaf : TODO(是否末节点)   
	 */   
	private Boolean isLeaf;
	
	/**   
	 * @Fields newCount : TODO(新增用户数)   
	 */   
	private Integer newCount = 0;
	
	/**   
	 * @Fields newShareCount : TODO(新增分享数)   
	 */   
	private Integer newShareCount = 0;

	/**   
     * @Fields date : TODO(客户状态名称)   
     */   
    private String statusName;
    /**   
     * @Fields date : TODO(客户来源名称)   
     */   
    private String sourceName;
    /**   
     * @Fields date : TODO(客户来源线上、线下)   
     */   
    private String lineName;
    
    
	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Integer getNewCount() {
		return newCount;
	}

	public void setNewCount(Integer newCount) {
		this.newCount = newCount;
	}

	public Integer getNewShareCount() {
		return newShareCount;
	}

	public void setNewShareCount(Integer newShareCount) {
		this.newShareCount = newShareCount;
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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
