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
	
	/**
	 * 根据不同的审核类型返回审核日志的集合
	 * @param type 审核类型
	 * @param formId 来源Id
	 * @param addUserId 提交审核人Id
	 * @return
	 */
	public List<AuditLog> execute(AuditLogType type, Integer formId, Integer addUserId) {
		List<AuditLog> userAuditList = this.getUserAuditLogList(type, formId, addUserId);
		List<AuditLog> departAuditList =this.getDepartAuditLogList(type, formId, addUserId);
		List<AuditLog> extenAuditList =this.getExtenAuditLogList(type, formId, addUserId);
		
		List<AuditLog> allList = new ArrayList<AuditLog>();
		allList.addAll(userAuditList);
		if(departAuditList.size() > 0){
			allList.addAll(departAuditList);
		}
		if(extenAuditList!= null && extenAuditList.size() > 0){
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
		
		List<Integer> leaderIdList = recursiveLeaderId(addUserId);
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
	 * 递归获取leaderId,目前只取2级领导（直属领导、隔级领导）
	 * @param addUserId
	 */
	private List<Integer> recursiveLeaderId(Integer addUserId) {
		List<Integer> leaderIdList = new ArrayList<Integer>();
		
		SalesmanOrganization organization = SupplierSessionManager.getSalesmanOrganization(addUserId);
		//1.直级领导
        if (organization.getDirectLeaderId() != null && !organization.getDirectLeaderId().equals(addUserId)) {
        	leaderIdList.add(organization.getDirectLeaderId());
        }
        //2.隔级领导
        if (organization.getSuperiorLeaderId() != null && !organization.getSuperiorLeaderId().equals(addUserId)) {
        	leaderIdList.add(organization.getSuperiorLeaderId());
        }
        return leaderIdList;
	}
}
