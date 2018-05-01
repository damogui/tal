package com.gongsibao.bd.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.core.QueryParameters;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;
import org.netsharp.util.sqlbuilder.UpdateBuilder;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogStatusType;
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
			oql.setSelects("auditLog.*,employee.{id,name}");
			oql.setFilter("form_id=? and type_id=?"); 
			oql.setOrderby("level asc");//级别升序排列
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

	/* 根据表单id 类型typeId和等级 查询出来下一级审核的人 */
	@Override
	public List<AuditLog> getNextLevelUserIds(Integer fromId, int typeId, int level) {
		// String sql =
		// "SELECT  *  FROM bd_audit_log WHERE  form_id=? AND  type_id=?  AND status_id  NOT IN(1053,1054,1056) AND LEVEL=?";

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
	
	@Override
	public AuditLog getById(Integer id) {
		Oql oql = new Oql();
        {
            oql.setType(this.type);
            oql.setSelects("*");
            oql.setFilter("pkid = ?");
            oql.getParameters().add("pkid", id, Types.INTEGER);
        }

       AuditLog auditLogs = this.pm.queryFirst(oql);
        return auditLogs;
	}
	
    //审核通过
    @Override
    public Boolean auditApproved(Integer auditId, String remark) {
    	
        AuditLog auditLog = byId(auditId);
        if (auditLog == null) {
            return false;
        }

        //将自己的状态改为【审核通过】
        updateStatus(auditLog.getId(), AuditLogStatusType.AUDITPASS, remark);
        //将等于自己同级别的审核记录修改成【关闭】,且不包括自己
        updateStatusToClose(auditLog, "=");
        //将下一级别的审核记录修改成【待审核】
        updateStatusTOAUDITByNextLevel(auditLog);

        return true;
    }

    //审核驳回
    @Override
    public Boolean auditRejected(Integer auditId, String remark) {

        AuditLog auditLog = byId(auditId);
        //将自己的状态改为【审核驳回】
        updateStatus(auditLog.getId(), AuditLogStatusType.AUDITREJECT, remark);
        //将大于自己同级别的审核记录修改成【关闭】,且不包括自己
        updateStatusToClose(auditLog, ">=");
        return true;
    }
    
    //region 私有方法
    //修改审核状态,和审批记录
    private void updateStatus(Integer id, AuditLogStatusType auditLogStatusType, String remark) {
        remark = StringUtils.trimToEmpty(remark);
        UpdateBuilder updateBuilder = new UpdateBuilder();
        {
            updateBuilder.update("bd_audit_log");
            updateBuilder.set("status_id", auditLogStatusType.getValue());
            updateBuilder.set("audit_time", new Date());
            updateBuilder.set("remark", remark);
            updateBuilder.where("pkid=?");
        }

        String sql = updateBuilder.toSQL();
        QueryParameters qps = new QueryParameters();
        qps.add("id", id, Types.INTEGER);
        this.pm.executeNonQuery(sql, qps);
    }

    //将等于（或大于）自己同级别的审核记录修改成【关闭】,且不包括自己
    private void updateStatusToClose(AuditLog auditLog, String operationStr) {
        UpdateBuilder updateBuilder = new UpdateBuilder();
        {
            updateBuilder.update("bd_audit_log");
            updateBuilder.set("status_id", AuditLogStatusType.Close.getValue());
            updateBuilder.where("level " + operationStr + " " + auditLog.getLevel() + " " +
                    "and type_id=" + auditLog.getType().getValue() + " " +
                    "and form_id = " + auditLog.getFormId() + " " +
                    "and pkid !=" + auditLog.getId() + " ");
        }
        String sql = updateBuilder.toSQL();
        this.pm.executeNonQuery(sql, null);
    }

    //将下一级别的审核记录修改成【待审核】
    private void updateStatusTOAUDITByNextLevel(AuditLog auditLog) {
        if (!auditLog.getLevel().equals(auditLog.getMaxLevel())) {
            Integer nextLevel = auditLog.getLevel() + 1;
            UpdateBuilder updateBuilder = new UpdateBuilder();
            {
                updateBuilder.update("bd_audit_log");
                updateBuilder.set("status_id", AuditLogStatusType.TOAUDIT.getValue());
                updateBuilder.where("level = " + nextLevel + " and form_id = " + auditLog.getFormId() + " and type_id=" + auditLog.getType().getValue() + " ");
            }

            String sql = updateBuilder.toSQL();
            this.pm.executeNonQuery(sql, null);
        }
    }
}