package org.netsharp.panda.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.panda.entity.PWorkspace;

public interface IPWorkspaceService extends IPersistableService<PWorkspace> {
	
	PWorkspace byUrl(String url);
	int removeByResourceCode(String code);
	int queryCount();
	
}
