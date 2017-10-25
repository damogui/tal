package org.netsharp.dataccess.replace;

import org.netsharp.persistence.session.SessionManager;

/*当前登录用户的Id*/
public class ReplaceUserId implements IReplace {
	
	private String key = "{userId}";

	public String execute(String cmdText) {
		
		if(cmdText.indexOf(this.key)<0){
			return cmdText;
		}
		
		Integer userId = SessionManager.getUserId();
		
		if (userId == null) {
			return cmdText;
		} else {
			return cmdText.replace(this.key,userId.toString());
		}
	}
}
