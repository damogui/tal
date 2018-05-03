package com.gongsibao.igirl.ic.base;


import com.gongsibao.entity.igirl.ic.ex.baseinfo.IcExLog;
import org.netsharp.base.IPersistableService;

import java.util.List;

public interface IcExLogService extends IPersistableService<IcExLog> {
    List<IcExLog> byExcId(Integer id);
}
