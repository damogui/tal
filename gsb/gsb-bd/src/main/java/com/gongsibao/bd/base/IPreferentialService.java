package com.gongsibao.bd.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.bd.Preferential;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IPreferentialService extends IPersistableService<Preferential> {

    List<Preferential> byIds(Collection<Integer> ids);

    Map<Integer, Preferential> mapByIds(Collection<Integer> ids);
}