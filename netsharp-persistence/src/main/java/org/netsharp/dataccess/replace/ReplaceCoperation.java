package org.netsharp.dataccess.replace;

import org.netsharp.persistence.session.SessionManager;
import org.netsharp.util.StringManager;

/*当前登录用户的所有部门*/
public class ReplaceCoperation implements IReplace {
	
	private String key = "{cId}";

	@Override
	public String execute(String cmdText) {

		//System.out.print(cmdText);
		if(cmdText.indexOf(this.key)<0){
			return cmdText;
		}
		
		Integer cid = SessionManager.getCoperationId();
		if (StringManager.isNullOrEmpty(cid.toString())) {
			return cmdText;
		} else {
			return cmdText.replace(this.key, cid.toString());
		}
	}
}
