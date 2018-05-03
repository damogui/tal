package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.IcExLog;
import com.gongsibao.igirl.ic.base.IcExLogService;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;

import java.sql.Types;
import java.util.Date;
import java.util.List;

@Service
public class ExLogService extends GsbPersistableService<IcExLog> implements IcExLogService {
    public ExLogService() {
        super();
        this.type = IcExLog.class;
    }
    @Override
    public List<IcExLog> byExcId(Integer id) {
        Oql oql = new Oql();
        oql.setType(IcExLog.class);
        oql.setSelects("IcExLog.*");
        oql.setFilter("excId=?");
        oql.setOrderby("IcExLog.createTime desc");
        oql.getParameters().add("excId",id,Types.INTEGER);
        return this.pm.queryList(oql);
    }


}
