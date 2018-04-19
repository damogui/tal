package com.gongsibao.bd.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.bd.PreferentialDataMap;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IPreferentialDataMapService extends IPersistableService<PreferentialDataMap> {

    List<PreferentialDataMap> byPreferentialIds(Collection<Integer> preferentialIds);

    Map<Integer, List<PreferentialDataMap>> mapByPreferentialIds(Collection<Integer> preferentialIds);

}