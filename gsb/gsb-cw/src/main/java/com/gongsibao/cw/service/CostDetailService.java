package com.gongsibao.cw.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.cw.base.ICostDetailService;
import com.gongsibao.entity.cw.CostDetail;

@Service
public class CostDetailService extends PersistableService<CostDetail> implements ICostDetailService {

	public CostDetailService() {
		super();
		this.type = CostDetail.class;
	}

}
