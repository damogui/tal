package com.gongsibao.bd.base;

import java.util.Collection;
import java.util.List;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.bd.Dict;

public interface IDictService extends IPersistableService<Dict> {

	/**
	 * @Title: byType
	 * @Description: TODO(根据类型查询)
	 * @param: @param type
	 * @param: @return
	 * @return: List<Dict>
	 * @throws
	 */
	List<Dict> byType(Integer type);

	/**
	 * @Title: byParentId
	 * @Description: TODO(根据上级查询)
	 * @param: @param parentId
	 * @param: @return
	 * @return: List<Dict>
	 * @throws
	 */
	List<Dict> byParentId(Integer parentId);

	boolean delete(String ids);

	List<Integer> findParentIds(Collection<Integer> dictIds);

	List<Integer> findChildIds(Collection<Integer> dictIds);

	List<Dict> findByIds(Collection<Integer> ids);

}