package com.gongsibao.bd.service.auditLog;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.organization.base.IOrganizationService;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;
import com.gongsibao.utils.SalesmanOrganization;
import com.gongsibao.utils.SupplierSessionManager;

public abstract class AbstractAuditLogService {
	IOrganizationService organizationService = ServiceFactory.create(IOrganizationService.class);
	 private List<Integer> leaderIdList = new ArrayList<Integer>();
	
	public List<AuditLog> execute(AuditLogType type, Integer formId, Integer addUserId) {
		List<AuditLog> userAuditList = this.getUserAuditLogList(type, formId, addUserId);
		List<AuditLog> departAuditList =this.getDepartAuditLogList(type, formId, addUserId);
		List<AuditLog> extenAuditList =this.getExtenAuditLogList(type, formId, addUserId);
		
		List<AuditLog> allList = new ArrayList<AuditLog>();
		allList.addAll(userAuditList);
		if(departAuditList.size() > 0){
			allList.addAll(departAuditList);
		}
		if(extenAuditList.size() > 0){
			allList.addAll(extenAuditList);
		}
		return allList;
	}
	
	/**
	 * 获取业务员审核的集合
	 * @param context
	 */
	protected List<AuditLog> getUserAuditLogList(AuditLogType type, Integer formId, Integer addUserId){
		List<AuditLog> resList = new ArrayList<AuditLog>();
		AuditLog logEntity = new AuditLog();
		logEntity.setType(type);
		logEntity.setFormId(formId);
		logEntity.setCreatorId(addUserId);
		logEntity.setLevel(0);
		resList.add(logEntity);
		return resList;
	}
	/**
	 * 获取部门审核的集合
	 * @param context
	 */
	protected List<AuditLog> getDepartAuditLogList(AuditLogType type, Integer formId, Integer addUserId){
		List<AuditLog> resuList = new ArrayList<AuditLog>();
		
		recursiveLeaderId(addUserId);
		int i = 0;
		for (Integer item : leaderIdList) {
			i +=1;
			AuditLog logEntity = new AuditLog();
			logEntity.setType(type);
			logEntity.setFormId(formId);
			logEntity.setCreatorId(item);
			logEntity.setLevel(i);
			resuList.add(logEntity);
		}
		return resuList;
	}
	public abstract List<AuditLog> getExtenAuditLogList(AuditLogType type,Integer formId,Integer addUserId);
	
	/**
	 * 递归获取leaderId
	 * @param addUserId
	 */
	private void recursiveLeaderId(Integer addUserId) {
		SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(addUserId);
        if (organization.getDirectLeaderId() != null) {
        	Integer leaderId = organization.getDirectLeaderId();
        	leaderIdList.add(leaderId);
        	recursiveLeaderId(leaderId);
        }
	}
}
