package com.gongsibao.bd.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.trade.Contract;
import com.gongsibao.entity.trade.OrderProd;
import com.gongsibao.entity.trade.SoOrder;

@Service
public class AuditLogService extends PersistableService<AuditLog> implements IAuditLogService {

    public AuditLogService() {
        super();
        this.type = AuditLog.class;
    }

    @Override
    public List<AuditLog> queryList(Oql oql) {

        List<AuditLog> resList = super.queryList(oql);

        if (CollectionUtils.isNotEmpty(resList)) {
            AuditLog auditLog = resList.get(0);
            // 合同审核时
            if (auditLog.getType().equals(AuditLogType.Htsq)) {
                for (AuditLog auditItem : resList) {
                    SoOrder order = auditItem.getSoOrder();
                    Integer contractPrice = 0;
                    if (order != null) {
                        List<OrderProd> orderProducts = auditItem.getSoOrder().getProducts();
                        if (CollectionUtils.isNotEmpty(orderProducts)) {
                            for (OrderProd orderProd : orderProducts) {
                                contractPrice = contractPrice + orderProd.getPrice();
                            }
                            Contract contract = auditItem.getContract();
                            if (contract != null) {
                                contract.setContractPrice(contractPrice);
                            }
                        }
                    }
                }
            }
        }

        // return super.queryList(oql);
        return resList;
    }

    @Override
    public int updateStatus(Integer id, Integer status, Integer oldStatus, String remark) {
        UpdateBuilder updateSql = UpdateBuilder.getInstance();
        {
            updateSql.update("bd_audit_log");
            updateSql.set("status_id", status);
            updateSql.set("add_time", new Date());
            updateSql.set("remark", remark);
            updateSql.where("pkid=" + id + " AND status_id=" + oldStatus);
        }
        String cmdText = updateSql.toSQL();
        return this.pm.executeNonQuery(cmdText, null);
    }

    @Override
    public int updateStatusByFidTidLev(Integer formId, Integer typeId, Integer level, Integer status, String levelLogic, Integer exceptId) {
        String exceptIdWhereString = exceptId.equals(0) ? " " : " AND pkid !=" + exceptId + " ";
        UpdateBuilder updateSql = UpdateBuilder.getInstance();
        {
            updateSql.update("bd_audit_log");
            updateSql.set("status_id", status);
            updateSql.where("form_id=" + formId + " AND type_id=" + typeId + " AND level " + levelLogic + " " + level + " " + exceptIdWhereString + "");
        }
        String cmdText = updateSql.toSQL();
        return this.pm.executeNonQuery(cmdText, null);
    }

    @Override
    public List<AuditLog> queryByFormId(Integer orderId, AuditLogType type) {

        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("formId=? and typeId=?");
            oql.getParameters().add("formId", orderId, Types.INTEGER);
            oql.getParameters().add("typeId", type.getValue(), Types.INTEGER);
        }
        return this.queryList(oql);
    }

    @Override
    public int queryUnPassCountByFormId(List<Integer> orderIdList) {

        String orderIds = StringManager.join(",", orderIdList);

        List<Integer> typeIdList = new ArrayList<Integer>();
        typeIdList.add(AuditLogType.Sksq.getValue());
        typeIdList.add(AuditLogType.Tdsq.getValue());
        typeIdList.add(AuditLogType.Fqsq.getValue());
        typeIdList.add(AuditLogType.Ddgj.getValue());
        String typeIds = StringManager.join(",", typeIdList);

        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setFilter("form_id in (" + orderIds + ") and type_id in (" + typeIds + ")");
        }

        return this.queryCount(oql);
    }

    @Override
    public List<AuditLog> createAuditLog(AuditLogType type, Integer formId, Integer addUserId) {

        List<AuditLog> list = new ArrayList<AuditLog>();

        return list;
    }


    /*根据表单id 类型typeId和等级 查询出来下一级审核的人*/
    @Override
    public List<AuditLog> getNextLevelUserIds(Integer fromId, int typeId, int level) {
//        String sql = "SELECT  *  FROM bd_audit_log WHERE  form_id=? AND  type_id=?  AND status_id  NOT IN(1053,1054,1056) AND LEVEL=?";

        Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("form_id=? and type_id=? and LEVEL=? ");
            oql.getParameters().add("@form_id", fromId, Types.INTEGER);
            oql.getParameters().add("@type_id", typeId, Types.INTEGER);
            oql.getParameters().add("@LEVEL", level, Types.INTEGER);
        }
        List<AuditLog> audits = super.queryList(oql);
        return audits;
    }
}