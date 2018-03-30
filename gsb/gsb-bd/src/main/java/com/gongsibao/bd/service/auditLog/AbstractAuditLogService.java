package com.gongsibao.bd.service.auditLog;

import java.util.ArrayList;
import java.util.List;

import com.gongsibao.entity.bd.dic.AuditLogStatusType;
import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

public abstract class AbstractAuditLogService {
    IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
    IAuditLogService logService = ServiceFactory.create(IAuditLogService.class);
    private Integer currentLevel;

    /**
     * 根据不同的审核类型返回审核日志的集合
     *
     * @param formId    来源Id
     * @param addUserId 提交审核人Id
     * @return
     */
    public List<AuditLog> execute(Integer formId, Integer addUserId) {
        List<AuditLog> allList = auditLogAllList(formId, addUserId);
        return allList;
    }

    /**
     * 根据不同的审核类型返回审核日志的集合
     *
     * @param formId 来源Id
     * @return
     */
    public List<AuditLog> execute(Integer formId) {
        Integer addUserId = SessionManager.getUserId();
        List<AuditLog> allList = auditLogAllList(formId, addUserId);
        return allList;
    }

    /*
    * 审核
    * */
    public boolean audit(AuditState state, Integer auditLogId, String remark) {

        String actionPath = setActionPath();

        AuditContext auditContext = new AuditContext();
        {
            // 这里根据传入的参数构造;
            auditContext.setState(state);
            auditContext.setAuditLogId(auditLogId);
            auditContext.setremark(remark);
        }
        ActionContext ctx = new ActionContext();
        {
            ctx.setPath(actionPath);
            ctx.setItem(auditContext);
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
    }

    /**
     * 整合返回集合
     *
     * @param formId
     * @param addUserId
     * @return
     */
    private List<AuditLog> auditLogAllList(Integer formId, Integer addUserId) {
        List<AuditLog> allList = new ArrayList<AuditLog>();
        AuditLog userAudit = this.getUserAuditLog(formId, addUserId);
        AuditLog directLeader = this.getDirectLeaderAudit(formId, addUserId);
        AuditLog superiorLeader = this.getSuperiorLeaderAudit(formId, addUserId);
        List<AuditLog> extenAuditList = this.getExtenAuditLogList(formId, addUserId);

        if (userAudit != null) {
            allList.add(userAudit);
        }
        if (directLeader != null) {
            allList.add(directLeader);
        }
        if (superiorLeader != null) {
            allList.add(superiorLeader);
        }
        if (extenAuditList != null && extenAuditList.size() > 0) {
            allList.addAll(extenAuditList);
        }

        //插入数据
        for (AuditLog item : allList) {
            item.toNew();
            item.setMaxLevel(getCurrentLevel());
            item.setRemark("");
            logService.save(item);
        }
        return allList;
    }


    /**
     * 获取业务员审核的集合
     */
    protected AuditLog getUserAuditLog(Integer formId, Integer addUserId) {
        AuditLog logEntity = addAuditLog(formId, "提交" + setAuditLogType().getText(), addUserId, 0);
        return logEntity;
    }

    /**
     * 获取业务员'直属领导'审核的集合
     */
    protected AuditLog getDirectLeaderAudit(Integer formId, Integer addUserId) {
        SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(addUserId);
        //1.直级领导
        if (organization.getDirectLeaderId() != null && !organization.getDirectLeaderId().equals(addUserId)) {
            Integer level = getCurrentLevel() + 1;
            AuditLog logEntity = addAuditLog(formId, "部门领导人审核", organization.getDirectLeaderId(), level);
            return logEntity;
        }
        return null;
    }

    /**
     * 获取业务员'隔级领导'审核的集合
     */
    protected AuditLog getSuperiorLeaderAudit(Integer formId, Integer addUserId) {

        SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(addUserId);
        //2.隔级领导
        if (organization.getSuperiorLeaderId() != null && !organization.getSuperiorLeaderId().equals(addUserId)) {
            Integer level = getCurrentLevel() + 1;
            AuditLog logEntity = addAuditLog(formId, "服务商领导人审核", organization.getSuperiorLeaderId(),level);
            return logEntity;
        }
        return null;
    }

    /**
     * 扩展审核 例如退款需要法务审核
     *
     * @param formId
     * @param addUserId
     * @return
     */
    protected abstract List<AuditLog> getExtenAuditLogList(Integer formId, Integer addUserId);

    /**
     * 设置审核类型
     *
     * @return
     */
    protected abstract AuditLogType setAuditLogType();

    //设置每个审核的actionPath
    protected abstract String setActionPath();

    /*
    * 添加审核记录
    * */
    protected AuditLog addAuditLog(Integer formId, String content, Integer creatorId,Integer level) {
        AuditLog auditLog = new AuditLog();
        AuditLogStatusType auditLogStatus = getAuditLogStatusType(level);
        auditLog.setType(setAuditLogType());
        auditLog.setFormId(formId);
        auditLog.setStatus(auditLogStatus);
        auditLog.setCreatorId(creatorId);
        auditLog.setContent(content);
        auditLog.setLevel(level);
        setCurrentLevel(level);
        return auditLog;
    }


    //获取审核状态
    private AuditLogStatusType getAuditLogStatusType(Integer level) {
        AuditLogStatusType auditLogStatusType;
        switch (level) {
            case 0:
                auditLogStatusType = AuditLogStatusType.AUDITPASS;
                break;
            case 1:
                auditLogStatusType = AuditLogStatusType.TOAUDIT;
                break;
            default:
                auditLogStatusType = AuditLogStatusType.Paidui;
                break;
        }
        return auditLogStatusType;
    }


    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

}
