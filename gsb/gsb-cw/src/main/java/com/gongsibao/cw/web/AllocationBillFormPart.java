package com.gongsibao.cw.web;

import org.netsharp.communication.ServiceFactory;
import org.netsharp.panda.commerce.FormPart;

import com.gongsibao.cw.base.IAllocationService;
import com.gongsibao.entity.cw.Allocation;

public class AllocationBillFormPart  extends FormPart{
	
	   IAllocationService allocationService = ServiceFactory.create(IAllocationService.class);
		
		/**
		 * 保存调拨单申请数据
		* @Title: saveLoan  
		* @Description: TODO
		* @param @param loan
		* @param @return    参数  
		* @return Boolean    返回类型  
		* @throws
		 */
		public Boolean saveAllocation(Allocation alloction) {
			return allocationService.saveAllocation(alloction);
		}

}
