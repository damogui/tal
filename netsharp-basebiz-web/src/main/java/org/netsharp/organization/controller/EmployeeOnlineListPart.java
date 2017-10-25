package org.netsharp.organization.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.authorization.EmployeeOnlineManager;
import org.netsharp.authorization.UserPermission;
import org.netsharp.panda.commerce.ListPart;

public class EmployeeOnlineListPart extends ListPart {

	public List<?> query() {
		List<UserPermission> list = EmployeeOnlineManager.getInstance().getOnLineEmployees();
		List<EmployeeOnline> os = new ArrayList<EmployeeOnline>();
		EmployeeOnline e = null;
		{
			for (UserPermission u : list) {
				e = new EmployeeOnline();
				e.setLoginName(u.getEmployee().getLoginName());
				e.setName(u.getEmployee().getName());
				e.setPosition(u.getEmployee().getPost() == null ? "" : u.getEmployee().getPost().getName());
				e.setLoginTime(u.getLoginTime());

				e.setIpAddress(u.getUserClient().getIpAddress());
				e.setPcName(u.getUserClient().getPcName());
				e.setExplorer(u.getUserClient().getExplorer());
				os.add(e);
			}
		}
		return os;
	}
}

class EmployeeOnline {
	private String name;
	private String loginName;
	private String position;
	private String ipAddress;
	private String pcName;
	private String explorer;

	private Date loginTime;

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getExplorer() {
		return explorer;
	}

	public void setExplorer(String explorer) {
		this.explorer = explorer;
	}
}