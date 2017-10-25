package org.netsharp.authorization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在线列表
 * 
 * @ClassName: EmployeeOnlineManager
 * @Description: TODO
 * @author 王阳
 * @date 2016年1月9日 下午4:05:53
 *
 */
public class EmployeeOnlineManager {
	private static EmployeeOnlineManager om;
	private Map<Integer, UserPermission> sessions;

	private EmployeeOnlineManager() {
		sessions = new HashMap<Integer, UserPermission>();
	}

	public static EmployeeOnlineManager getInstance() {
		if (om == null) {
			om = new EmployeeOnlineManager();
		}
		return om;
	}

	public void addSession(UserPermission p) {

		sessions.put(p.getEmployee().getId(), p);

	}

	public void removeSession(Integer empId) {

		sessions.remove(empId);

	}

	public List<UserPermission> getOnLineEmployees() {
		List<UserPermission> emps = new ArrayList<UserPermission>();
		if (om != null) {

			for (Map.Entry<Integer, UserPermission> entity : om.sessions.entrySet()) {

				emps.add(entity.getValue());
			}
		}
		return emps;
	}

}
