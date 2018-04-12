package com.gongsibao.igirl.settle.base;

import com.gongsibao.entity.Result;
import com.gongsibao.entity.igirl.settle.Settle;
import com.gongsibao.entity.igirl.settle.SettleOrder;
import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

import java.util.List;

public interface ISettleService extends IPersistableService<Settle> {

    @Transaction
    Result<Settle> saveSettle(List<Integer> orderProdCaseIds);
}
