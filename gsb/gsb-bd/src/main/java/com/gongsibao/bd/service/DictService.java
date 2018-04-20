package com.gongsibao.bd.service;

import com.gongsibao.bd.base.IDictService;
import com.gongsibao.entity.bd.Dict;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.netsharp.communication.Service;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

import java.sql.Types;
import java.util.*;

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
            oql.getParameters().add("parenetId", parentId, Types.INTEGER);
        }
        return this.queryList(oql);
    }

    @Override
    public List<Dict> byTypeParentId(Integer type, Collection<Integer> parentIds) {
        StringBuffer sql = new StringBuffer(" type = ? and enabled = 1 ");
        Oql oql = new Oql();
        oql.setType(this.type);
        oql.setSelects("*");
        if(parentIds!=null&&parentIds.size()>0){
            sql.append(String.format(" and pid in(%s) ", StringUtils.join(parentIds,",")));
        }
        oql.setFilter(sql.toString());
        oql.getParameters().add("type",type,Types.INTEGER);
        oql.setOrderby(" pid ASC, pkid ASC ");
        return this.pm.queryList(oql);
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
        List<Integer> parentIds = new ArrayList<>();
        parentIds.addAll(dictIds);
        while (true) {
            Oql oql = new Oql();
            {
                String isList = StringManager.join(",", Arrays.asList(parentIds.toArray()));
                oql.setType(this.type);
                oql.setSelects("Dict.{id,parentId}");
                oql.setFilter("id in (" + isList + ") and parentId > 0");
            }
            List<Dict> list = this.queryList(oql);
            if (CollectionUtils.isEmpty(list)) {
                return cityIds;
            }
            parentIds.clear();
            for (Dict dict : list) {
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
        List<Integer> parentIds = new ArrayList<>();
        parentIds.addAll(dictIds);
        while (true) {
            Oql oql = new Oql();
            {
                String isList = StringManager.join(",", Arrays.asList(parentIds.toArray()));
                oql.setType(this.type);
                oql.setSelects("Dict.{id,parentId}");
                oql.setFilter("parentId in   (" + isList + ") and parentId > 0");
            }
            List<Dict> list = this.queryList(oql);
            if (CollectionUtils.isEmpty(list)) {
                return cityIds;
            }
            parentIds.clear();
            for (Dict dict : list) {
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

    @Override
    public Map<Integer, Dict> findMapByIds(Collection<Integer> ids) {
        Map<Integer, Dict> result = new HashMap<>();
        List<Dict> list = findByIds(ids);
        if (null == list || list.isEmpty()) {
            return result;
        }

        for (Dict dict : list) {
            result.put(dict.getId(), dict);
        }
        return result;
    }

    @Override
    public Map<Integer, Dict> mapByType(Integer type) {
        Map<Integer, Dict> result = new HashMap<>();
        List<Dict> dicts = byType(type);
        if (null == dicts) {
            return result;
        }
        for (Dict dict : dicts) {
            result.put(dict.getId(), dict);
        }
        return result;
    }
}