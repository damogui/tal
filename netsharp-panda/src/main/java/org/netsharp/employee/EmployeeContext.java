package org.netsharp.employee;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.annotate.JsonIgnore;

/*
 * 员工上下文对象 ----session对象 需要转化为json格式保存
 */
public class EmployeeContext implements Serializable {

	private static final long serialVersionUID = 4501460884086603584L;
	private Integer employeeId;
	private Integer departmentId;

	private String employeeName;
	private String phoneNumber;
	private String mail;

	private Integer appId;

	@JsonIgnore
	private HttpSession httpSession;
	private String clientIp;

	private Date ts = new Date();

	// 已经认证过
	public boolean isAuthenticated() {
		return employeeId != null && employeeId.intValue() > 0;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

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

	public HttpSession getHttpSession() {
		return httpSession;
	}

	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	public void clear() {
		this.employeeId = null;
		this.employeeName = null;
		this.httpSession = null;
		this.clientIp = null;

		this.appId = null;
		this.ts = new Date();
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

}
