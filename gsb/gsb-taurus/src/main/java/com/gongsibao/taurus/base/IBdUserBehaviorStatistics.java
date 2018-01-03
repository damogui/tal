package com.gongsibao.taurus.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.taurus.JnzUserBehaviorStatistics;

public interface IBdUserBehaviorStatistics extends IPersistableService<JnzUserBehaviorStatistics>{

	void saveJnzUserBehaviorStatistics();
	
}
