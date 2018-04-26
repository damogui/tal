package com.gongsibao.cw.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.CostDetail;

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
	/**
	 * 通过单据id和单据类型查询
	* @Title: getCostDetailItem  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param formId
	* @param @param formType
	* @param @return    参数  
	* @return List<CostDetail>    返回类型  
	* @throws
	 */
	public List<AuditRecord> getAuditRecordList(Integer formId,Integer formType);
	/**
	 * 删除审核记录
	* @Title: deleteAuditByFormId  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param formId
	* @param @param formType
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	public Boolean deleteAuditByFormId(Integer formId,Integer formType);
	/**
	 * 保存财务办理意见
	* @Title: saveFinance  
	* @Description: TODO
	* @param @param auditRecord
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	public void saveFinance(AuditRecord auditRecord);
	/**
	 * 查询待审核记录
	 * @param formId
	 * @param formType
	 * @param auditUserId
	 * @param status
	 * @return
	 */
	public AuditRecord getAuditRecordByParam(Integer formId,Integer formType,Integer auditUserId,Integer status);
}
