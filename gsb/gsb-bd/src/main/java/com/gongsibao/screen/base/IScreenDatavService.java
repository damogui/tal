package com.gongsibao.screen.base;

import com.gongsibao.entity.screen.Datav;
import org.netsharp.base.IPersistableService;

public interface IScreenDatavService extends IPersistableService<Datav> {

    String getContentById(Integer pkid);
}
