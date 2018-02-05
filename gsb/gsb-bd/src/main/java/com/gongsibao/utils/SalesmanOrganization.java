package com.gongsibao.utils;

/**   
 * @ClassName:  SalesmanPermission   
 * @Description:TODO 业务员组织机构上下文
 * 18.根据当前登录人获取部门负责人（增加一个实体：登录人Id,登录人姓名，部门Id,部门名称，服务商Id,服务商名称，直属领导Id，直属领导Name，隔级领导Id，隔级领导Name）
 * @author: 韩伟
 * @date:   2018年2月5日 上午10:30:38   
 *     
 * @Copyright: 2018 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class SalesmanOrganization {

	/**   
	 * @Fields employeeId : TODO(平台员工帐号Id)   
	 */   
	private Integer employeeId;
	
	/**   
	 * @Fields employeeName : TODO(平台员工帐号名称)   
	 */   
	private String employeeName;
	
	/**   
	 * @Fields employeeLoginName : TODO(平台员工帐号登录名称)   
	 */   
	private String employeeLoginName;
	
	/**   
	 * @Fields salessmanId : TODO(业务员Id)   
	 */   
	private Integer salessmanId;
	
	/**   
	 * @Fields salessmanName : TODO(业务员名称)   
	 */   
	private String salessmanName;
	
	/**   
	 * @Fields salessmanLoginName : TODO(业务员登录帐号)   
	 */   
	private String salessmanLoginName;
	
	/**   
	 * @Fields departmentId : TODO(业务员所在部门Id)   
	 */   
	private Integer departmentId;
	
	/**   
	 * @Fields departmentName : TODO(业务员所在部门名称)   
	 */   
	private String departmentName;
	
	/**   
	 * @Fields supplierId : TODO(业务员所在服务商Id)   
	 */   
	private Integer supplierId;
	
	/**   
	 * @Fields supplierName : TODO(业务员所在服务商名称)   
	 */   
	private String supplierName;
	
	/**   
	 * @Fields directLeaderId : TODO(业务员直属领导Id)   
	 */   
	private Integer directLeaderId;
	
	/**   
	 * @Fields directLeaderName : TODO(业务员直属领导名称)   
	 */   
	private String directLeaderName;
	
	/**   
	 * @Fields directLoginName : TODO(业务员直属领导登录名)   
	 */   
	private String directLoginName;
	
	/**   
	 * @Fields superiorLeaderId : TODO(业务员隔级领导Id)   
	 */   
	private Integer superiorLeaderId;
	
	/**   
	 * @Fields superiorLeaderName : TODO(业务员隔级领导名称)   
	 */   
	private String superiorLeaderName;
	
	/**   
	 * @Fields superiorLoginName : TODO(业务员隔级领导登录名)   
	 */   
	private String superiorLoginName;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeLoginName() {
		return employeeLoginName;
	}

	public void setEmployeeLoginName(String employeeLoginName) {
		this.employeeLoginName = employeeLoginName;
	}

	public Integer getSalessmanId() {
		return salessmanId;
	}

	public void setSalessmanId(Integer salessmanId) {
		this.salessmanId = salessmanId;
	}

	public String getSalessmanName() {
		return salessmanName;
	}

	public void setSalessmanName(String salessmanName) {
		this.salessmanName = salessmanName;
	}

	public String getSalessmanLoginName() {
		return salessmanLoginName;
	}

	public void setSalessmanLoginName(String salessmanLoginName) {
		this.salessmanLoginName = salessmanLoginName;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public Integer getDirectLeaderId() {
		return directLeaderId;
	}

	public void setDirectLeaderId(Integer directLeaderId) {
		this.directLeaderId = directLeaderId;
	}

	public String getDirectLeaderName() {
		return directLeaderName;
	}

	public void setDirectLeaderName(String directLeaderName) {
		this.directLeaderName = directLeaderName;
	}

	public String getDirectLoginName() {
		return directLoginName;
	}

	public void setDirectLoginName(String directLoginName) {
		this.directLoginName = directLoginName;
	}

	public Integer getSuperiorLeaderId() {
		return superiorLeaderId;
	}

	public void setSuperiorLeaderId(Integer superiorLeaderId) {
		this.superiorLeaderId = superiorLeaderId;
	}

	public String getSuperiorLeaderName() {
		return superiorLeaderName;
	}

	public void setSuperiorLeaderName(String superiorLeaderName) {
		this.superiorLeaderName = superiorLeaderName;
	}

	public String getSuperiorLoginName() {
		return superiorLoginName;
	}

	public void setSuperiorLoginName(String superiorLoginName) {
		this.superiorLoginName = superiorLoginName;
	}
}
