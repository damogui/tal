package org.netsharp.persistence.session;

import java.util.List;

public interface ISessionManager {
	
	Integer getUserId();
	String getUserName();
	String getDepartments();
	List<String> getDepartmentPathCodes();
	/**
	 * 获取当前登录人所在公司的id
	 * @return
	 */
	Integer getCoperationId();
}