package com.gongsibao.crm.service.action.task.transfer;

import com.gongsibao.entity.crm.NCustomerTask;

/**
 * 处理转移的通知
 * @author Administrator
 *
 */
public class ProcessNotice {

	/**
	 * 判断任务转移的类型
	 * @param formSupplierId 被转移任务的服务商
	 * @param formDepartmentId 被转移任务的部门
	 * @param formUserId 被转移任务的业务员
	 * @param toSupplierId 接收任务的服务商
	 * @param toDepartmentId 接收任务的部门
	 * @param toUserId 接收任务的业务员
	 * @return
	 */
	public static ProcessNoticeEnum noticeType(Integer formSupplierId,Integer formDepartmentId,Integer formUserId,Integer toSupplierId,Integer toDepartmentId,Integer toUserId){
		ProcessNoticeEnum resultEnum = null;		
		if(formUserId != null && toUserId != null){
			resultEnum = ProcessNoticeEnum.salesmanTosalesman; 
		}else if (formUserId != null && toUserId == null) {
			resultEnum = ProcessNoticeEnum.salesmanToseas; 
		}else if (formUserId == null && toUserId != null) {
			resultEnum = ProcessNoticeEnum.seasTosalesman; 
		}else if (formUserId == null && toUserId == null) {
			resultEnum = ProcessNoticeEnum.seasToseas; 
		}
		return resultEnum;
	}
	public static ProcessNoticeEnum noticeType(NCustomerTask entity,Integer toSupplierId,Integer toDepartmentId,Integer toUserId){
		ProcessNoticeEnum resultEnum = null;		
		if(entity.getOwnerId() != null && toUserId != null){
			resultEnum = ProcessNoticeEnum.salesmanTosalesman; 
		}else if (entity.getOwnerId() != null && toUserId == null) {
			resultEnum = ProcessNoticeEnum.salesmanToseas; 
		}else if (entity.getOwnerId() == null && toUserId != null) {
			resultEnum = ProcessNoticeEnum.seasTosalesman; 
		}else if (entity.getOwnerId() == null && toUserId == null) {
			resultEnum = ProcessNoticeEnum.seasToseas; 
		}
		return resultEnum;
	}
}
