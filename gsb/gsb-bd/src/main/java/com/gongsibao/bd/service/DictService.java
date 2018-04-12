package com.gongsibao.bd.service;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.entity.bd.Dict;

@Service
public class DictService extends PersistableService<Dict> implements IDictService {

	public DictService() {
		super();
		this.type = Dict.class;
	}

	@Override
	public List<Dict> byType(Integer type) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("type=? and enabled=1");
			oql.getParameters().add("type", type, Types.INTEGER);
		}
		return this.queryList(oql);
	}

	@Override
	public List<Dict> byParentId(Integer parentId) {

		Oql oql = new Oql();
		{
			oql.setType(this.type);
			oql.setSelects("*");
			oql.setFilter("parentId=? and enabled=1");
			oql.getParameters().add("parentId", parentId, Types.INTEGER);
		}
		return this.queryList(oql);
	}

	@Override
	public boolean delete(String ids) {
		String[] idsarr = ids.split("_");
		String isList = StringManager.join(",", idsarr);
		String sql = "delete from bd_dict where pkid in (" + isList + ")";
		return this.pm.executeNonQuery(sql, null) > 0;
	}


	@Override
	public List<Integer> findParentIds(Collection<Integer> dictIds) {
		List<Integer> cityIds = new ArrayList<>();
		if (CollectionUtils.isEmpty(dictIds)) {
			return new ArrayList<>();
		}
		cityIds.addAll(dictIds);
		List<Integer> parentIds = new ArrayList<>();
		parentIds.addAll(dictIds);
		while (true) {
			Oql oql = new Oql();
			{
				String isList = StringManager.join(",", Arrays.asList(parentIds.toArray()));
				oql.setType(this.type);
				oql.setSelects("Dict.parentId");
				oql.setFilter("id in   (" + isList + ") and parentId > 0");
			}
			List<Dict> list=this.queryList(oql);
			if (CollectionUtils.isEmpty(list)) {
				return cityIds;
			}
			parentIds=new ArrayList<>();
			for (Dict dict:list){
				parentIds.add(dict.getParentId());
				cityIds.add(dict.getParentId());
			}
		}
	}

	@Override
	public List<Integer> findChildIds(Collection<Integer> dictIds) {
		List<Integer> cityIds = new ArrayList<>();
		if (CollectionUtils.isEmpty(dictIds)) {
			return new ArrayList<>();
		}
		cityIds.addAll(dictIds);
		List<Integer> parentIds = new ArrayList<>();
		parentIds.addAll(dictIds);
		while (true) {
			Oql oql = new Oql();
			{
				String isList = StringManager.join(",", Arrays.asList(parentIds.toArray()));
				oql.setType(this.type);
				oql.setSelects("Dict.id");
				oql.setFilter("parentId in   (" + isList + ") and parentId > 0");
			}
			List<Dict> list=this.queryList(oql);
			if (CollectionUtils.isEmpty(list)) {
				return cityIds;
			}
			parentIds=new ArrayList<>();
			for (Dict dict:list){
				parentIds.add(dict.getId());
				cityIds.add(dict.getId());
			}
		}
	}

	@Override
	public List<Dict> findByIds(Collection<Integer> ids) {
		Oql oql = new Oql();
		{
			String isList = StringManager.join(",", Arrays.asList(ids.toArray()));
			oql.setType(this.type);
			oql.setSelects("Dict.*");
			oql.setFilter("id in   (" + isList + ") ");
			oql.setOrderby("id ASC");
		}
		return this.queryList(oql);
	}
}