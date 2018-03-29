package com.gongsibao.cw.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.cw.AuditRecord;

public interface IAuditRecordService extends IPersistableService<AuditRecord>{

	/**
	 * 保存审核意见
	* @Title: saveAudit  
	* @Description: TODO
	* @param @param auditRecord
	* @param @param formId
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	public Boolean saveAudit(AuditRecord auditRecord);
}
