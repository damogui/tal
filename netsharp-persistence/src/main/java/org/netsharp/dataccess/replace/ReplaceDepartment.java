package org.netsharp.dataccess.replace;

import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

/*当前登录用户的所有部门*/
public class ReplaceDepartment implements IReplace {
	
	private String key = "{departments}";

	@Override
	public String execute(String cmdText) {
		
		if(cmdText.indexOf(this.key)<0){
			return cmdText;
		}
		
		String departments = SessionManager.getDepartments();
		if (StringManager.isNullOrEmpty(departments)) {
			return cmdText;
		} else {
			return cmdText.replace(this.key, departments);
		}
	}

}
