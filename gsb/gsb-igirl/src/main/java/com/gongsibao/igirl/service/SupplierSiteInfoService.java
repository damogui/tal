package com.gongsibao.igirl.service;

import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.baseinfo.SupplierSiteInfo;
import com.gongsibao.igirl.base.ISupplierSiteInfoService;
import com.gongsibao.utils.SupplierSessionManager;

import org.netsharp.communication.Service;

@Service
public class SupplierSiteInfoService extends GsbPersistableService<SupplierSiteInfo> implements ISupplierSiteInfoService {

	public SupplierSiteInfoService() {
		super();
		this.type = SupplierSiteInfo.class;
	}
	@Override
	public SupplierSiteInfo save(SupplierSiteInfo entity) {
		// TODO Auto-generated method stub
		//设置服务商信息
		Integer sid = SupplierSessionManager.getSupplierId();
		entity.setSupplierId(sid);
		return super.save(entity);
	}
}