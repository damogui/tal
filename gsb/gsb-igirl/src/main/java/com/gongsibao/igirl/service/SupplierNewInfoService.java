package com.gongsibao.igirl.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.baseinfo.SupplierNewInfo;
import com.gongsibao.igirl.base.ISupplierNewInfoService;
import com.gongsibao.utils.SupplierSessionManager;

import org.netsharp.communication.Service;

@Service
public class SupplierNewInfoService extends GsbPersistableService<SupplierNewInfo> implements ISupplierNewInfoService {

	public SupplierNewInfoService() {
		super();
		this.type = SupplierNewInfo.class;
	}
	
	@Override
	public SupplierNewInfo save(SupplierNewInfo entity) {
		// TODO Auto-generated method stub
		//设置服务商信息
		Integer sid = SupplierSessionManager.getSupplierId();
		entity.setSupplierId(sid);
		return super.save(entity);
	}

}