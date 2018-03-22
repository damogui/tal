package com.gongsibao.bd.service.auditLog;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IOrganizationService;
import org.netsharp.persistence.session.SessionManager;

import com.gongsibao.bd.base.IAuditLogService;
import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogStatusType;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

public abstract class AbstractAuditLogService {
	IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
	IAuditLogService logService = ServiceFactory.create(IAuditLogService.class);
	private Integer currentLevel;
	
	/**
	 * 根据不同的审核类型返回审核日志的集合
	 * @param type 审核类型
	 * @param formId 来源Id
	 * @param addUserId 提交审核人Id
	 * @return
	 */
	public List<AuditLog> execute(Integer formId, Integer addUserId) {
		List<AuditLog> allList = auditLogAllList(formId, addUserId);
		return allList;
	}
	/**
	 * 根据不同的审核类型返回审核日志的集合
	 * @param type 审核类型
	 * @param formId 来源Id
	 * @return
	 */
	public List<AuditLog> execute(Integer formId) {
		Integer addUserId = SessionManager.getUserId();
		List<AuditLog> allList = auditLogAllList(formId, addUserId);
		return allList;
	}
	/**
	 * 整合返回集合
	 * @param type
	 * @param formId
	 * @param addUserId
	 * @return
	 */
	private List<AuditLog> auditLogAllList(Integer formId, Integer addUserId){
		List<AuditLog> allList = new ArrayList<AuditLog>();
		AuditLog userAudit = this.getUserAuditLog(formId, addUserId);
		AuditLog directLeader = this.getDirectLeaderAudit(formId, addUserId);
		AuditLog superiorLeader = this.getSuperiorLeaderAudit(formId, addUserId);
		List<AuditLog> extenAuditList = this.getExtenAuditLogList(formId, addUserId);
		
		if(userAudit != null ){
			allList.add(userAudit);
		}
		if(directLeader != null){
			allList.add(directLeader);
		}
		if(superiorLeader != null){
			allList.add(superiorLeader);
		}
		if(extenAuditList != null && extenAuditList.size() > 0){
			allList.addAll(extenAuditList);
		}
		//插入数据
		for (AuditLog item : allList) {
			item.toNew();
			item.setMaxLevel(allList.size());
			item.setRemark("");
			logService.save(item);
		}
		return allList;
	}
	
	
	/**
	 * 获取业务员审核的集合
	 * @param context
	 */
	protected AuditLog getUserAuditLog(Integer formId, Integer addUserId){
		AuditLog logEntity = new AuditLog();
		logEntity.setType(setAuditLogType());
		logEntity.setFormId(formId);
		logEntity.setCreatorId(addUserId);
		logEntity.setStatus(AuditLogStatusType.AUDITPASS);
		logEntity.setContent("提交" + setAuditLogType().getText());
		logEntity.setLevel(0);
		setCurrentLevel(0);
		return logEntity;
	}
	/**
	 * 获取业务员'直属领导'审核的集合
	 * @param context
	 */
	protected AuditLog getDirectLeaderAudit(Integer formId, Integer addUserId){
		SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(addUserId);
		//1.直级领导
        if (organization.getDirectLeaderId() != null && !organization.getDirectLeaderId().equals(addUserId)) { 
        	AuditLog logEntity = new AuditLog();
			logEntity.setType(setAuditLogType());
			logEntity.setFormId(formId);
			logEntity.setStatus(AuditLogStatusType.TOAUDIT);
			logEntity.setCreatorId(organization.getDirectLeaderId());
			logEntity.setContent("部门领导人审核");
			Integer level = getCurrentLevel() + 1;
			logEntity.setLevel(level);
			setCurrentLevel(level);
			return logEntity;
        }
		return null;
	}
	/**
	 * 获取业务员'隔级领导'审核的集合
	 * @param context
	 */
	protected AuditLog getSuperiorLeaderAudit(Integer formId, Integer addUserId){
		
		SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(addUserId);
		//2.隔级领导
        if (organization.getSuperiorLeaderId() != null && !organization.getSuperiorLeaderId().equals(addUserId)) {
        	AuditLog logEntity = new AuditLog();
        	logEntity.setType(setAuditLogType());
			logEntity.setFormId(formId);
			logEntity.setStatus(AuditLogStatusType.TOAUDIT);
			logEntity.setCreatorId(organization.getSuperiorLeaderId());
			logEntity.setContent("服务商领导人审核");
			Integer level = getCurrentLevel() + 1;
			logEntity.setLevel(level);
			setCurrentLevel(level);
			return logEntity;
        }
		return null;
	}
	/**
	 * 扩展审核 例如退款需要法务审核
	 * @param type
	 * @param formId
	 * @param addUserId
	 * @return
	 */
	protected abstract List<AuditLog> getExtenAuditLogList(Integer formId,Integer addUserId);
	/**
	 * 设置审核类型
	 * @return
	 */
	protected abstract AuditLogType setAuditLogType();
	
	
	public Integer getCurrentLevel() {
		return currentLevel;
	}
	public void setCurrentLevel(Integer currentLevel) {
		this.currentLevel = currentLevel;
	}

}
