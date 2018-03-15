package com.gongsibao.bd.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.bd.AuditLog;
import com.gongsibao.entity.bd.dic.AuditLogType;

public interface IAuditLogService extends IPersistableService<AuditLog> {

	int updateStatus(Integer id, Integer status, Integer oldStatus, String remark);

	int updateStatusByFidTidLev(Integer formId, Integer typeId, Integer level, Integer status, String levelLogic, Integer exceptId);

	List<AuditLog> queryByFormId(Integer orderId, AuditLogType type);


	/**   
	 * @Title: createAuditLog   
	 * @Description: TODO(1.根据类型不同构建不同数量的记录 2.addUserId为提交审核人Id 3.需要返回集合，用作发送通知)   
	 * @param: @param type
	 * @param: @param formId
	 * @param: @param addUserId
	 * @param: @return      
	 * @return: List<AuditLog>      
	 * @throws   
	 */
	List<AuditLog> createAuditLog(AuditLogType type, Integer formId, Integer addUserId);
}