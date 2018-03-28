package com.gongsibao.igirl.tm.base;
import com.gongsibao.entity.igirl.tm.baseinfo.NCLTwo;
import org.netsharp.base.IPersistableService;

import java.util.List;

public interface INCLTwoService extends IPersistableService<NCLTwo> {
    public List<NCLTwo> findSubsByNclOneId(int ncloneid);
    public NCLTwo findNclTwoByCode(String Code,String name);
}