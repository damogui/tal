package com.gongsibao.igirl.ic.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.ic.dict.CorpRegStatue;
import com.gongsibao.entity.igirl.ic.ex.IcExRegisterCase;
import com.gongsibao.entity.igirl.ic.ex.baseinfo.IcExLog;
import com.gongsibao.igirl.ic.base.IcExLogService;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;

import java.sql.Types;
import java.util.Date;

@Service
public class ExLogService extends GsbPersistableService<IcExLog> implements IcExLogService {
    public ExLogService() {
        super();
        this.type = IcExLog.class;
    }
}
