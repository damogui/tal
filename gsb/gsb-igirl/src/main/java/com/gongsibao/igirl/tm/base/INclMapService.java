package com.gongsibao.igirl.tm.base;


import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.igirl.tm.baseinfo.NclMap;

public interface INclMapService extends IPersistableService<NclMap> {
	public String getTmplByNclOneId(int ncloneid);
}
