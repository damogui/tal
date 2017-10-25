package org.netsharp.persistence.session;

import java.util.List;

public class SessionManager{

	private static ISessionManager manager;

	public static void initialize(ISessionManager sm) {
		manager = sm;
	}

	public static Integer getUserId() {
		if(manager == null){
			return  null;
		}
		else {
			return manager.getUserId();
		}
	}

	public static String getUserName() {
		if(manager == null){
			return  null;
		}
		else {
			return manager.getUserName();
		}
	}

	public static String getDepartments() {
		if(manager == null){
			return  null;
		}
		else {
			return manager.getDepartments();
		}
	}

	public static List<String> getDepartmentPathCodes() {
		if(manager == null){
			return  null;
		}
		else {
			return manager.getDepartmentPathCodes();
		}
	}
	
	public static Integer  getCoperationId(){
		return manager.getCoperationId();
	}
}
