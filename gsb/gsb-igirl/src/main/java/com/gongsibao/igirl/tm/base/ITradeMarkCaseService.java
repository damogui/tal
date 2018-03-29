package com.gongsibao.igirl.tm.base;
import com.gongsibao.entity.igirl.tm.TradeMarkCase;
import org.netsharp.base.IPersistableService;
import org.netsharp.core.annotations.Transaction;

public interface ITradeMarkCaseService extends IPersistableService<TradeMarkCase> {
	public TradeMarkCase getTradeMarkCaseModelByMobile(String mobile);
	public String fetchQrCodeUrl(String url,String casecode);
    public int attachmentMake(String caseid);
	public int denyAdvice(String caseid,String advice);
	public int confirmCase(String caseid);
	public TradeMarkCase updateOwner(Integer ttmId,Integer ownerId);
	public int fetchCaseState(String casecode);
	public int updateCaseState(String casecode,int state);

    /**
     * 通过方案生成订单
     *
     * @param caseid
     * @return
     */
    @Transaction
    int convertToOrder(String caseid);
}