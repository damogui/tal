package com.gongsibao.screen.service;

import com.gongsibao.entity.screen.Datav;
import com.gongsibao.screen.base.IScreenDatavService;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import java.lang.reflect.Type;
import java.sql.Types;

@Service
public class ScreenDatavService extends PersistableService<Datav> implements IScreenDatavService {

    @Override
    public String getContentById(Integer pkid) {

        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("pkid = ?");
            oql.getParameters().add("id", pkid, Types.INTEGER);
        }

        Datav datav = this.pm.queryFirst(oql);
        String res = "";
        if (datav != null) {
            res = datav.getContent();
        }
        return res;
    }
}
