package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.dic.AuditStatusType;
import org.netsharp.communication.Service;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.trade.base.INDepReceivableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import java.sql.Types;

/**
 * Created by win on 2018/2/27.
 */
@Service
public class NDepReceivableService extends PersistableService<NDepReceivable> implements INDepReceivableService {

    public NDepReceivableService() {
        super ();
        this.type = NDepReceivable.class;
    }


    @Override
    public void updateStatus(Integer id, AuditStatusType auditStatusType) {

        UpdateBuilder updateBuilder = new UpdateBuilder ();
        {
            updateBuilder.update ("n_dep_receivable");
            updateBuilder.set ("status", auditStatusType.getValue ());
            updateBuilder.where ("order_id=?");
        }
        String sql = updateBuilder.toSQL ();
        QueryParameters qps = new QueryParameters ();
        qps.add ("@order_id", id, Types.INTEGER);
        this.pm.executeNonQuery (sql, qps);
    }
}
