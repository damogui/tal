package com.gongsibao.franchisee.base;

import java.util.Map;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.franchisee.Franchisee;
import com.gongsibao.entity.franchisee.dto.FranchiseeReportDto;

public interface IFranchiseeService extends IPersistableService<Franchisee>{

	/**
	 * 获取当前时间截止的客户总数量
	 * @param currentTime 当前时间
	 * @return 业务员Id和对应的总客户 的集合
	 */
	public Map<Integer, Integer> getCustomersAllTotal(String currentTime);
	
	/**
	 * 根据条件查询并返回统计后的数据
	 * @param ownerId 业务员Id
	 * @param currentTime 当前时间
	 * @return
	 */
	public FranchiseeReportDto getFranReport(Integer ownerId,String currentTime);
}
