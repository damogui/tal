package com.gongsibao.u8.service;

import org.netsharp.communication.Service;
import org.netsharp.service.PersistableService;

import com.gongsibao.entity.u8.VoucherLog;
import com.gongsibao.u8.base.IVoucherLogService;

@Service
public class VoucherLogService extends PersistableService<VoucherLog> implements
		IVoucherLogService {

	public VoucherLogService() {
		super();
		this.type = VoucherLog.class;
	}

}