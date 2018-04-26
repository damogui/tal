package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.dic.AuditStatusType;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.NDepReceivable;
import com.gongsibao.trade.base.INDepReceivableService;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import java.sql.Types;
import java.util.Date;
import java.util.List;

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
            updateBuilder.set ("audit_time", new Date ());//回款审核通过时间
            updateBuilder.where ("order_id=?");
        }
        String sql = updateBuilder.toSQL ();
        QueryParameters qps = new QueryParameters ();
        qps.add ("@order_id", id, Types.INTEGER);
        this.pm.executeNonQuery (sql, qps);
    }


    /*根据订单id获取实体信息*/
    @Override
    public List<NDepReceivable> getNDepsByOrderId(Integer id) {
        Oql oql = new Oql();
        oql.setType(this.type);
        oql.setSelects("*");
        oql.setFilter(" order_id=?");
        oql.getParameters().add("@order_id", id, Types.INTEGER);
        List<NDepReceivable> ndeps = queryList(oql);
        return   ndeps;
    }


}
