package com.gongsibao.franchisee.base;

import java.util.Map;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.entity.franchisee.FranchiseeReport;

public interface IFranchiseeService extends IPersistableService<Franchisee>{
	
	/**
	 * 获取当前时间截止的客户总数量
	 * @param ownerId 业务员Id
	 * @param currentTime 当前时间
	 * @return 业务员Id和对应的总客户 的集合
	 */
	public Map<Integer, Integer> getCustomersAllTotal(Integer ownerId,String currentTime);
	
	/**
	 * 根据条件统计报表数据的集合
	 * @param ownerId 业务员Id
	 * @param currentTime 当前时间
	 * @return  
	 */
	public FranchiseeReport statistReport(Integer ownerId,String currentTime);
	
	
	/**
	 * 分配客户给业务员
	 * @param ids
	 * @param departmentId
	 * @param ownerId
	 * @return
	 */
	Boolean allot(String[] ss,Integer departmentId,Integer ownerId);
	
}
