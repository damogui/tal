package com.gongsibao.u8.base;

import org.netsharp.base.IPersistableService;

import com.gongsibao.entity.u8.U8Bank;

public interface IU8BankService extends IPersistableService<U8Bank> {

    U8Bank getByOnlineBankCode(String onlineBankCodeId);
}
