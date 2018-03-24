package com.gongsibao.igirl.base;


import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.igirl.baseinfo.NclMap;

public interface INclMapService extends IPersistableService<NclMap> {
	public String getTmplByNclOneId(int ncloneid);
}
