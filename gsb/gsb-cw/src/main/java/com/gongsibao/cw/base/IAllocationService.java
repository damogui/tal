package com.gongsibao.cw.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.cw.Allocation;
import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.Payment;

public interface IAllocationService extends IPersistableService<Allocation>{

	/**
	 * 保存调拨单数据
	* @Title: saveAllocation  
	* @Description: TODO
	* @param @param alloction
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	public Boolean saveAllocation(Allocation alloction);
	
	 /**
	 * 通过表单id  表单类型获取表单数据
	* @Title: getBillByFormId  
	* @Description: TODO
	* @param @param formId
	* @param @param formType
	* @param @return    参数  
	* @return T    返回类型  
	* @throws
	 */
	public  Allocation  getBillByFormId(Integer formId);
	
	/**
	 * 财务办理完成修改订单状态
	* @Title: updateStatus  
	* @Description: TODO
	* @param @param auditRecord
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	public Boolean updateStatus(AuditRecord auditRecord);
}
