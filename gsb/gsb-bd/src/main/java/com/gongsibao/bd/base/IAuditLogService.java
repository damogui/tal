package com.gongsibao.bd.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;

public interface IAuditLogService extends IPersistableService<AuditLog> {
	
	int updateStatus(Integer id, Integer status, Integer oldStatus, String remark);
	
	int updateStatusByFidTidLev(Integer formId, Integer typeId, Integer level, Integer status,String levelLogic,Integer exceptId);
	
	List<AuditLog> queryByFormId(Integer orderId,AuditLogType type);
	
}