package com.gongsibao.igirl.base;

import com.gongsibao.entity.igirl.TradeMarkCase;
import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

public interface ITradeMarkCaseService extends IPersistableService<TradeMarkCase> {
    TradeMarkCase getTradeMarkCaseModelByMobile(String mobile);

    String fetchQrCodeUrl(String url, String casecode);

    int attachmentMake(String caseid);

    int denyAdvice(String caseid, String advice);

    int confirmCase(String caseid);

    TradeMarkCase updateOwner(Integer ttmId, Integer ownerId);

    int fetchCaseState(String casecode);

    int updateCaseState(String casecode, int state);

    /**
     * 通过方案生成订单
     *
     * @param caseid
     * @return
     */
    @Transaction
    int convertToOrder(String caseid);
}