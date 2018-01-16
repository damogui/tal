package com.gongsibao.igirl.base;
import com.gongsibao.entity.igirl.baseinfo.NCLTwo;
import org.netsharp.base.IPersistableService;

import java.util.List;

public interface INCLTwoService extends IPersistableService<NCLTwo> {
    public List<NCLTwo> findSubsByNclOneId(int ncloneid);
}