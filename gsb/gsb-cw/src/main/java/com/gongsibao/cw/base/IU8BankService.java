package com.gongsibao.cw.base;

import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.u8.U8Bank;

public interface IU8BankService extends IPersistableService<U8Bank>{
	/**
	 * 获取付款单位下的银行科目
	* @Title: getU8BankList  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param setOfBooksId
	* @param @return    参数  
	* @return List<U8Bank>    返回类型  
	* @throws
	 */
	public List<U8Bank> getU8BankList(Integer setOfBooksId);
}
