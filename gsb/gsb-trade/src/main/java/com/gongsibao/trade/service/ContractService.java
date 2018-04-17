package com.gongsibao.trade.service;

import com.gongsibao.entity.trade.dic.AuditStatusType;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.trade.Contract;
import com.gongsibao.trade.base.IContractService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContractService extends PersistableService<Contract> implements IContractService {

    public ContractService() {
        super();
        this.type = Contract.class;
    }

    @Override
    public Contract saveContract(Contract contract) {
        ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/crm/contract/save");
            ctx.setItem(contract);
            ctx.setState(contract.getEntityState());
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);

        contract = (Contract) ctx.getItem();
        return contract;
    }

    @Override
    public void updateStatus(Integer id, AuditStatusType auditStatusType) {

        UpdateBuilder updateBuilder = new UpdateBuilder();
        {
            updateBuilder.update("so_contract");
            updateBuilder.set("audit_status_id", auditStatusType.getValue());
            updateBuilder.where("pkid=?");
        }
        String sql = updateBuilder.toSQL();
        QueryParameters qps = new QueryParameters();
        qps.add("id", id, Types.INTEGER);
        this.pm.executeNonQuery(sql, qps);
    }

    @Override
    public Contract getById(Integer id) {

        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("id=?");
            oql.getParameters().add("id", id, Types.INTEGER);
        }
        Contract contract = this.pm.queryFirst(oql);

        return contract;
    }

    @Override
    public Map<Integer, Boolean> getIsUrgeneyByOrderProdIdList(List<Integer> orderProdIdList) {
        String orderProdIds = StringManager.join(",", orderProdIdList);
        StringBuffer sqlSb = new StringBuffer();
        sqlSb.append("SELECT od.`pkid` 'orderProdId', c.`is_urgeney` 'isUrgeney' FROM so_contract c ");
        sqlSb.append("JOIN so_order_prod od ON od.order_id = c.order_id ");
        sqlSb.append("WHERE od.order_id IN (" + orderProdIds + ") ");
        DataTable rows = this.pm.executeTable(sqlSb.toString(), null);
        Map<Integer, Boolean> resMap = new HashMap<>();
        for (IRow row : rows) {
            resMap.put(row.getInteger("orderProdId"), row.getBoolean("isUrgeney"));
        }
        return resMap;
    }
}