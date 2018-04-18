package com.gongsibao.cw.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cw.base.ICostTypeService;
import com.gongsibao.entity.cw.CostType;

@Service
public class CostTypeService extends PersistableService<CostType> implements ICostTypeService{

}
