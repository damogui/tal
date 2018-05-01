package com.gongsibao.bd.service.auditLog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IEmployeeService;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.organization.entity.Employee;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogStatusType;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.entity.supplier.Salesman;
import com.gongsibao.entity.supplier.Supplier;
import com.gongsibao.entity.supplier.SupplierCategoryOwnerMap;
import com.gongsibao.entity.supplier.dict.SupplierType;
import com.gongsibao.supplier.base.ISalesmanService;
import com.gongsibao.supplier.base.ISupplierCategoryOwnerMapService;
import com.gongsibao.supplier.base.ISupplierService;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;


public abstract class AbstractAuditService {
	
    IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
    IAuditLogService logService = ServiceFactory.create(IAuditLogService.class);
    ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
    ISupplierCategoryOwnerMapService supplierCateService = ServiceFactory.create(ISupplierCategoryOwnerMapService.class);
    ISupplierService supplierService = ServiceFactory.create(ISupplierService.class);
    IEmployeeService employeeService = ServiceFactory.create(IEmployeeService.class);
    
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

    /*
     * 审核(扩展参数)
     * */
    public boolean audit(AuditState state, Integer auditLogId, String remark, Object obj) {

        String actionPath = setActionPath();

        AuditContext auditContext = new AuditContext();
        {
            // 这里根据传入的参数构造;
            auditContext.setState(state);
            auditContext.setAuditLogId(auditLogId);
            auditContext.setremark(remark);
            if (obj != null) {
                auditContext.setOtherInfo(obj);

            }

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
        List<AuditLog> platformAuditList = this.getPlatformOperationAudit(formId, addUserId);
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
        if (platformAuditList != null && platformAuditList.size() > 0) {
            allList.addAll(platformAuditList);
        }
        if (extenAuditList != null && extenAuditList.size() > 0) {
            allList.addAll(extenAuditList);
        }

        //插入数据
        for (AuditLog item : allList) {
            item.toNew();
            item.setMaxLevel(getCurrentLevel());
            item.setRemark("");
            //当没有其他审核流程（自己审核通过即全部审核通过），需要设置审核为待审核（否则审核流程不走，无法走审核回写）
            if (allList.size() == 1) {
                item.setStatus(AuditLogStatusType.TOAUDIT);
            } else {
                if (item.getStatus().equals(AuditLogStatusType.AUDITPASS)) {
                    item.setAuditTime(new Date());
                }
            }
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
            AuditLog logEntity = addAuditLog(formId, "服务商领导人审核", organization.getSuperiorLeaderId(), level);
            return logEntity;
        }
        return null;
    }

    /**
     * 添加平台运营审核（xl部门用）
     *
     * @param formId
     * @param addUserId
     * @return
     */
    protected List<AuditLog> getPlatformOperationAudit(Integer formId, Integer addUserId) {
        List<AuditLog> auditLogList = new ArrayList<AuditLog>();
        //1.判断当前提交审核的业务员 是否属于平台
        Salesman salesmanEntity = salesmanService.byEmployeeId(addUserId);
        if (salesmanEntity != null && salesmanEntity.getType().equals(SupplierType.PLATFORM)) {
            //2.平台服务商属于哪个分类
            List<Integer> yysIdList = new ArrayList<Integer>();
            Supplier supplierEntity = (Supplier) supplierService.byId(salesmanEntity.getSupplierId());
            if (supplierEntity != null) {
                List<SupplierCategoryOwnerMap> cateList = supplierCateService.getListByCategoryId(supplierEntity.getCategoryId());
                for (SupplierCategoryOwnerMap item : cateList) {
                    //3.判断运营专员(只取sys_permission_employee中专员)是否离职
                    Employee employee = employeeService.byId(item.getOwnerId());
                    if (employee != null && !employee.getDisabled()) {
                        yysIdList.add(item.getOwnerId());
                    }
                }
            }
            //4.添加相应分类的运营专员审核
            Integer level = getCurrentLevel() + 1;
            for (Integer item : yysIdList) {
                auditLogList.add(addAuditLog(formId, "运营专员审核", item, level));
            } 
           /* //4.获取相应分类的平台服务商领导,并且没有离职
            ISalesmanService salesmanService = ServiceFactory.create(ISalesmanService.class);
            List<Integer> yyIds = salesmanService.getEmployeeIdListByRoleCodes(Arrays.asList("Platform_Operation_Leader"));
            Integer level = getCurrentLevel() + 1;
            for (Integer item : yyIds) {
                auditLogList.add(addAuditLog(formId, "运营审核", item, level));
            }*/
        }

        return auditLogList;
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
    protected AuditLog addAuditLog(Integer formId, String content, Integer creatorId, Integer level) {
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
