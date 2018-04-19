package com.gongsibao.bd.base;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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

	/**
	 * 根据字典类型 & 父ID查询相关数据字典(默认只查询激活状态)
	 * @param type 字典类型 *
	 * @param parentId 父ID
	 * @return List<Dict>
	 */
	List<Dict> byTypeParentId(Integer type,Collection<Integer> parentId);

	boolean delete(String ids);

	List<Integer> findParentIds(Collection<Integer> dictIds);

	List<Integer> findChildIds(Collection<Integer> dictIds);

	List<Dict> findByIds(Collection<Integer> ids);

	Map<Integer, Dict> findMapByIds(Collection<Integer> ids);

	Map<Integer, Dict> mapByType(Integer type);

}